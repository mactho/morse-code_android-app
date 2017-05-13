package com.mactho.sailormorse;

import android.hardware.Camera;


/**
 * Created by thomas on 13/05/17.
 */
public class CameraOperator implements Runnable{

    @Override
    public void run(){

        Camera camera = Camera.open();
        Camera.Parameters params = camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(params);
        camera.startPreview();
        try{
            Thread.sleep(3000);
        } catch( Exception e ){

        }
        camera.stopPreview();
        camera.release();
    }
}
