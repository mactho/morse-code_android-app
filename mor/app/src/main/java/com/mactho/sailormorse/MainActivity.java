package com.mactho.sailormorse;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private final int CAMERA_PERMISSION_CODE = 1;

    FragmentManager fragmentManager = getFragmentManager();
    private CameraOperator cameraOperator = new CameraOperator(300);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.quick_messsage);
        SpinnerHandler spinnerHandler = new SpinnerHandler();
        spinner.setOnItemSelectedListener(spinnerHandler);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(spinnerHandler,"spinnerHandler");
        fragmentTransaction.commit();
    }

    /** The main functionality of the application
     * Transforms the message into a morse code then displays the translated
     * message in the TextView and initiates the CameraOperator to send the
     * morse coded message via the camera flash
     * @param view
     * The view object that triggered the function */
    public void sendMessage(View view) {
        StringToMorseTransformer transformer = new StringToMorseTransformer();

        // Get message from the message EditText
        EditText messageTextView = (EditText) findViewById(R.id.message);
        String message = messageTextView.getText().toString();

        // Transform the human readable message into morse code
        ArrayList<String> morseCodes = transformer.transform(message);

        // Display the morse code in the morse code TextView
        printMorseCodes(morseCodes);

        // Operate camera
        cameraOperator.setMessage(morseCodes);
        requestPermissions();
    }

    /** A function that stops the camera from sending the message
     * before it finishes sending normally
     * @param view
     * The view object that triggered the function */
    public void stopMessage(View view){
        //TODO: make the message stop sending
    }

    /** A function that clears the editable view of text
     * @param view
     * The view object that triggered the function */
    public void clearMessage(View view) {
        EditText messageTextView = (EditText) findViewById(R.id.message);
        messageTextView.setText("");
    }

    /** Self explained toggles the repeat functionality of the camera to loop the message sent
     * @param view
     * The view object that triggered the function */
    public void toggleRepeat(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            cameraOperator.setRepeating(true);
        } else{
            cameraOperator.setRepeating(false);
        }
    }

    /** sets the morse code TextView to display the morse coded message
     * after it has been transformed
     * @param list
     * The list of words transformed into morse code */
    private void printMorseCodes( ArrayList<String> list){
        TextView morseTextView = (TextView) findViewById(R.id.morse_viewer);
        morseTextView.setText("");
        System.out.println( list.size());
        for(String word : list){
            morseTextView.append(word);
            morseTextView.append("(break)");
        }
    }

    /** Requests user permission to use the camera device */
    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                // TODO: Make an explaination dialog box with ASyncTask....
                System.out.println("explain");

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
            }
        }
    }

    /** Handles the results of the permission request */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(cameraOperator);
                } else {
                    // TODO: Permission denied path....
                }
                return;

            // Other permissions (cases)...
        }
    }
}
