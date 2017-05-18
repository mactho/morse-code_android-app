package com.mactho.sailormorse;

import android.hardware.Camera;

import java.util.ArrayList;


/**
 * Created by thomas on 13/05/17.
 */
public class CameraOperator implements Runnable {

    private int ditLength = 300;
    private ArrayList<String> message;
    private boolean repeating = false;

    public CameraOperator(int ditLength) {
        this.ditLength = ditLength;
    }

    public CameraOperator(int ditLength, ArrayList<String> message) {
        this.ditLength = ditLength;
        this.message = message;
    }

    /** This method does the actual manipulation of the camera flashing */
    @Override
    public void run() {

        Camera camera = Camera.open();
        camera.startPreview();
        for (String word : message) {
            for (int counter = 0; counter < word.length(); counter++) {
                if (word.charAt(counter) == '0') {
                    flash(camera, getDitLength());
                } else if (word.charAt(counter) == '1') {
                    flash(camera, dahLength());
                } else if (word.charAt(counter) == '-') {
                    try{
                        Thread.sleep(dahLength() - getDitLength());
                    } catch( Exception e ){
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(getDitLength());
                } catch( Exception e ){
                    e.printStackTrace();
                }
            }
            try{
                Thread.sleep(breakLength());
            } catch( Exception e ){
                e.printStackTrace();
            }
        }
        camera.stopPreview();
        camera.release();

        // If repeat is on call run again until repeating is unchecked
        if(isRepeating()){
            run();
        }
    }

    /** Triggers the flash for the given amount of time */
    private void flash(Camera camera, int time){
        Camera.Parameters params = camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(params);

        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(params);
    }

    private int dahLength() {
        return getDitLength() * 3;
    }

    private int breakLength() {
        return getDitLength() * 7;
    }

    public int getDitLength() {
        return ditLength;
    }

    public void setDitLength(int ditLength) {
        this.ditLength = ditLength;
    }

    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }
}
