package com.radbee.radbeeapp;

import android.os.Parcel;
import android.os.Parcelable;

public class RbAdbCommand implements Parcelable {

    private String rbAdbCommandName;
    private String rbAdbCommandDescription;
    private String[] rbAdbCommandArguments;
    private String[] rbAdbCommandArgumentValues;
    private String adbCommand;

    public RbAdbCommand() {


    }

    public RbAdbCommand(String rbAdbCommandName,
                        String rbAdbCommandDescription,
                        String[] rbAdbCommandArguments,
                        String[] rbAdbCommandArgumentValues,
                        String adbCommand) {
        this.rbAdbCommandName = rbAdbCommandName;
        this.rbAdbCommandDescription = rbAdbCommandDescription;
        this.rbAdbCommandArguments = rbAdbCommandArguments;
        this.rbAdbCommandArgumentValues = rbAdbCommandArgumentValues;
        this.adbCommand = adbCommand;
    }

    public String buildAdbCommand() {

        StringBuilder builtAdbCommand = new StringBuilder();

        // TODO: Inject the argument values into the adbCommand

        return builtAdbCommand.toString();
    }

    // region PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rbAdbCommandName);
        dest.writeString(rbAdbCommandDescription);
        dest.writeStringArray(rbAdbCommandArguments);
        dest.writeStringArray(rbAdbCommandArgumentValues);
        dest.writeString(adbCommand);
    }

    public static final Parcelable.Creator<RbAdbCommand> CREATOR =
            new Parcelable.Creator<RbAdbCommand>() {
                @Override
                public RbAdbCommand createFromParcel(Parcel source) {
                    return new RbAdbCommand(source);
                }

                @Override
                public RbAdbCommand[] newArray(int size) {
                    return new RbAdbCommand[0];
                }
            };

    @Override
    public String toString() {
        StringBuilder rbAdbCommandStr = new StringBuilder();
        rbAdbCommandStr.append("RbAdbCommand{");
        rbAdbCommandStr.append("rbAdbCommandName='" + rbAdbCommandName + '\'');
        rbAdbCommandStr.append("adbCommand='" + adbCommand + '\'');
        rbAdbCommandStr.append("rbAdbCommandArguments='");
        for (String commandArg : rbAdbCommandArguments) {
            // TODO: Complete string build of RbAdbCommand
        }
        rbAdbCommandStr.append('}');
    }
// endregion

    // region GETTERS AND SETTERS
    public String getRbAdbCommandName() {
        return rbAdbCommandName;
    }

    public void setRbAdbCommandName(String rbAdbCommandName) {
        this.rbAdbCommandName = rbAdbCommandName;
    }

    public String getRbAdbCommandDescription() {
        return rbAdbCommandDescription;
    }

    public void setRbAdbCommandDescription(String rbAdbCommandDescription) {
        this.rbAdbCommandDescription = rbAdbCommandDescription;
    }

    public String[] getRbAdbCommandArguments() {
        return rbAdbCommandArguments;
    }

    public void setRbAdbCommandArguments(String[] rbAdbCommandArguments) {
        this.rbAdbCommandArguments = rbAdbCommandArguments;
    }

    public String[] getRbAdbCommandArgumentValues() {
        return rbAdbCommandArgumentValues;
    }

    public void setRbAdbCommandArgumentValues(String[] rbAdbCommandArgumentValues) {
        this.rbAdbCommandArgumentValues = rbAdbCommandArgumentValues;
    }

    public String getAdbCommand() {
        return adbCommand;
    }

    public void setAdbCommand(String adbCommand) {
        this.adbCommand = adbCommand;
    }
    // endregion
}

