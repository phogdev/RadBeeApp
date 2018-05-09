package com.radbee.radbeeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RbAdbCommandActivity extends AppCompatActivity {

    private TextView adbCommandNameTextView;
    private TextView adbCommandDescriptionTextView;
    private TextView adbCommandTextView;
    private Button runAdbCommandButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rb_adb_command);

        //
        Intent intent = getIntent();
        RbAdbCommand rbAdbCommand = intent.get

        // Handle the onClick event for runAdbCommandButton
        runAdbCommandButton = (Button) findViewById(R.id.runAdbCommandButton);
        runAdbCommandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRbAdbCommandButtonClicked(v);
            }
        });
    }

    public void onRbAdbCommandButtonClicked(View v) {

    }
}
