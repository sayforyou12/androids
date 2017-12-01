package com.android.camerecapture;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

// 카메라 미리보기를 위한 서피스뷰 클래스.
public class CameraSurfaceView extends SurfaceView
        implements SurfaceHolder.Callback {

    private SurfaceHolder holder = null;
    private Camera camera = null;

    public CameraSurfaceView(Context context) {
        super(context);

        this.holder = getHolder();
        this.holder.addCallback( this );
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(this.holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public boolean capture( Camera.PictureCallback handler){
        if( this.camera != null ) {
            camera.takePicture( null, null, handler);
            return true;
        }
        else {
            return false;
        }
    }
}