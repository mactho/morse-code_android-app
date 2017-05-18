package com.mactho.sailormorse;

import android.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

/**
 * Created by thomas on 14/05/17.
 */
public class SpinnerHandler extends Fragment implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        EditText messageTextBox = (EditText) getActivity().findViewById(R.id.message);
        if (pos == 0) {
            onNothingSelected(parent);
        } else {
            messageTextBox.setText(parent.getSelectedItem().toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        EditText messageTextBox = (EditText) getActivity().findViewById(R.id.message);
        messageTextBox.setText(getString(R.string.message_default));
    }
}
