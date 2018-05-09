package com.radbee.radbeeapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class RbAccessoryManager {

    private UsbManager rbUsbManager;
    private UsbAccessory rbUsbAccessory;
    private BroadcastReceiver rbUsbReceiver;
    private ParcelFileDescriptor rbParcelFileDescriptor;
    private FileDescriptor rbFileDescriptor;
    private FileInputStream rbFileInputStream;
    private FileOutputStream rbFileOutputStream;
    private Thread rbWritingThread;
    private Thread rbReadingThread;
    private boolean isConnected;
    private static final String RB_ACCESSORY_NAME = "RB_DEVICE";
    private static final String MESSAGE_DELIMETER = ",";
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

    public RbAccessoryManager() {
        rbUsbAccessory = null;
        listenForRbAccessory();
    }

    private void listenForRbAccessory() {

        if (rbUsbReceiver == null) return;
        rbUsbReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (ACTION_USB_PERMISSION.equals(action)) {
                    synchronized (this) {
                        UsbAccessory accessory = (UsbAccessory) intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);

                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                            if(accessory != null) {
                                connectToRbAccessory(accessory);
                            }
                        }
                        else {
                            Log.d(TAG, "permission denied for accessory " + accessory);
                        }
                    }
                }
                else if (UsbManager.ACTION_USB_ACCESSORY_ATTACHED.equals(action)) {
                    UsbAccessory accessory = (UsbAccessory) intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
                    connectToRbAccessory(accessory);
                }
                else if (UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)) {
                    UsbAccessory accessory = (UsbAccessory) intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
                    if (accessory != null && rbUsbAccessory.getModel().compareTo(accessory.getModel()) == 0) {
                        rbUsbAccessory = null;
                    }
                }
            }
        };
    }

    private void connectToRbAccessory(UsbAccessory accessory) {

        if (accessory == null) return;
        rbUsbAccessory = accessory;
        if (rbUsbAccessory.getModel().compareTo(accessory.getModel()) == 0) {
            rbParcelFileDescriptor = rbUsbManager.openAccessory(rbUsbAccessory);
        }
    }

    public boolean isRbAccessoryConnected() {
        if (rbUsbAccessory == null) return false;
        return true;
    }

    public void sendRbMessage(String rbMessage, RbMessageType rbMessageType) {

        prepareAdbMessaging();
        final byte[] messageBuffer = createAdbCommandMessage(rbMessage);
        rbWritingThread = createMessageWritingThread(messageBuffer);
        rbWritingThread.start();
    }

    public void sendAdbCommand(String adbCommand) {

        sendRbMessage(adbCommand, RbMessageType.ADB_COMMAND);
    }

    private Thread createMessageWritingThread(final byte[] messageBuffer) {

        return new Thread(new Runnable() {
            public void run() {
                try {
                    rbFileOutputStream.write(messageBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Thread createMessageReadingThread(final byte[] messageBuffer) {

        return new Thread(new Runnable() {
            public void run() {
                try {
                    rbFileInputStream.read(messageBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void prepareAdbMessaging() {

        if (rbFileDescriptor == null) {
            rbFileDescriptor = rbParcelFileDescriptor.getFileDescriptor();
        }
        if (rbFileInputStream == null) {
            rbFileInputStream = new FileInputStream(rbFileDescriptor);
        }
        if (rbFileOutputStream == null) {
            rbFileOutputStream = new FileOutputStream(rbFileDescriptor);
        }
    }

    private byte[] createAdbCommandMessage(String adbCommand) {

        StringBuffer messageBuffer = new StringBuffer();
        messageBuffer.append(RbMessageType.ADB_COMMAND.toString());
        messageBuffer.append(MESSAGE_DELIMETER);
        messageBuffer.append(adbCommand);
        return messageBuffer.toString().getBytes();
    }
}

