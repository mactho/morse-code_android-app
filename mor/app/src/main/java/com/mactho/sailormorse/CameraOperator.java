package com.mactho.sailormorse;

import android.hardware.Camera;

import java.util.ArrayList;


/**
 * Created by thomas on 13/05/17.
 */
public class CameraOperator implements Runnable {

    private int ditLength = 300;
    private ArrayList<String> message;

    public CameraOperator(int ditLength) {
        this.ditLength = ditLength;
    }

    public CameraOperator(int ditLength, ArrayList<String> message) {
        this.ditLength = ditLength;
        this.message = message;
    }

    @Override
    public void run() {

        Camera camera = Camera.open();
        //Camera.Parameters params = camera.getParameters();
        //camera.setParameters(params);
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

                    }
                }
                try {
                    Thread.sleep(getDitLength());
                } catch( Exception e ){

                }
            }
            try{
                Thread.sleep(breakLength());
            } catch( Exception e ){

            }
        }
        camera.stopPreview();
        camera.release();
    }

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
        //camera.stopPreview();
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
}
