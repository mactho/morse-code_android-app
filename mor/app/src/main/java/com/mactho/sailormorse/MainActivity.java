package com.mactho.sailormorse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void sendMessage(View view){
        StringToMorseTransformer transformer = new StringToMorseTransformer();

        // Get message from the message EditText
        EditText messageTextView = (EditText)findViewById(R.id.message);
        String message = messageTextView.getText().toString();

        // Transform the human readable message into morse code
        String morseCode = transformer.transform(message);

        // Display the morse code in the morse code TextView
        TextView morseTextView = (TextView)findViewById(R.id.morse_viewer);
        morseTextView.setText(morseCode);

    }

    public void clearMessage(View view){
        EditText messageTextView = (EditText)findViewById(R.id.message);
        messageTextView.setText("");
    }
}
