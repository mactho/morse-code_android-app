package com.mactho.sailormorse;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private final int CAMERA_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                // TODO: Make an explaination dialog box
                System.out.println("explain");

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, CAMERA_PERMISSION_CODE);
            }
        }

        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        StringToMorseTransformer transformer = new StringToMorseTransformer();

        // Get message from the message EditText
        EditText messageTextView = (EditText) findViewById(R.id.message);
        String message = messageTextView.getText().toString();

        // Transform the human readable message into morse code
        String morseCode = transformer.transform(message);

        // Display the morse code in the morse code TextView
        TextView morseTextView = (TextView) findViewById(R.id.morse_viewer);
        morseTextView.setText(morseCode);

        requestPermissions();
    }

    public void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                // TODO: Make an explaination dialog box
                System.out.println("explain");

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
            }
        }
    }

    public void clearMessage(View view) {
        EditText messageTextView = (EditText) findViewById(R.id.message);
        messageTextView.setText("");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CameraOperator cameraOperator = new CameraOperator();
                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(cameraOperator);
                } else {
                    // Permission denied path....
                }
                return;

            // Other permissions (cases)...
        }
    }
}
