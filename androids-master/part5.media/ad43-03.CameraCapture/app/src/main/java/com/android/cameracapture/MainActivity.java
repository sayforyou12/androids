package com.android.cameracapture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String            IMAGE_FILE   = "capture.jpg";
    private CameraSurfaceView cameraView   = null;
    private FrameLayout       previewFrame = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        cameraView  = new CameraSurfaceView( getApplicationContext() );

        previewFrame = (FrameLayout) findViewById(R.id.previewFrame);
        previewFrame.addView( cameraView );

        // 버튼 이벤트 처리
        Button btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Camera.PictureCallback callback = new Camera.PictureCallback() {

                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                            saveGallery(bitmap);
                            saveFolder(bitmap);

                            Toast.makeText(getApplicationContext(), "카메라로 찍은 사진을 앨범에 저장", Toast.LENGTH_LONG).show();

                            // restart
                            camera.startPreview();
                        } catch (Exception e) {
                            Log.e("SampleCapture", "Failed to insert image.", e);
                        }
                    }
                };

                cameraView.capture( callback );
            }
        });
    }

    private void saveGallery(Bitmap bitmap) {
        String outUriStr = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Captured Image", "Captured Image using Camera.");

        if (outUriStr == null) {
            Log.d("SampleCapture", "Image insert failed.");
        } else {
            Uri outUri = Uri.parse(outUriStr);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri));
        }
    }

    private void saveFolder(Bitmap bitmap) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/req_images");

        if( !myDir.exists() ) myDir.mkdirs();

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        Log.i("saveFolder", "" + file);

        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
