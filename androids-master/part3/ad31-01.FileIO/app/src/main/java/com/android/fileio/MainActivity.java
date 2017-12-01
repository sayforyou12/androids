package com.android.fileio;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static Uri PROVIDER_URI = Uri.parse(
            "content://com.superdroid.StudentsProvider/students");
    ListView mListView = null;
    CursorAdapter mAdapter = null;

    TextView txtview;
    EditText edittxt;
    private  View view;
    Button btn10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtview = (TextView) findViewById(R.id.txtview);
        edittxt = (EditText) findViewById(R.id.edittxt);
        btn10 = (Button) findViewById(R.id.btn10);


    }
    public void onClick(View v)throws IOException{
        switch (v.getId()){
            case R.id.btn1:
                FileOutputStream out1 =
                        openFileOutput("text.txt", Context.MODE_APPEND);
                String text1 = "text";
                out1.write(text1.getBytes());

                out1.close();
                break;

            case R.id.btn2:
                FileInputStream in1 = openFileInput("text.txt");
                byte[]txt1 = new byte[in1.available()];
                in1.read(txt1);
                txtview.setText(txt1.toString());
                in1.close();
                break;

            case R.id.btn3:

                break;

            case R.id.btn4:

                InputStream in2 = getResources().openRawResource(R.raw.raw_text);
                byte[] txt2 = new byte[in2.available()];
                in2.read(txt2);
                txtview.setText(new String(txt2));
                in2.close();

                break;

            case R.id.btn6:
                FileInputStream in3 = new FileInputStream("/sdcard/sample.txt");
                byte[]txt3 = new byte[in3.available()];
                in3.read(txt3);
                txtview.setText(txt3.toString());
                in3.close();
                break;

            case R.id.btn7:
                FileOutputStream out2 = new FileOutputStream("/sdcard/sample.txt");
                String text2 = "text";
                out2.write(text2.getBytes());
                out2.close();
                break;

            case R.id.btn8:
                String path1 = Environment.getExternalStorageDirectory().getAbsolutePath();

                File mydir1 = new File(path1 + "|testdir");
                if (!mydir1.isDirectory()) mydir1.mkdir();

                File[]file = mydir1.listFiles();
                mydir1.canRead();

                break;

            case R.id.btn9:
                String path2 = Environment.getExternalStorageDirectory().getAbsolutePath();

                File mydir2 = new File(path2 + "|mydir");
                if (!mydir2.isDirectory())mydir2.mkdir();
                break;
        }
        if ( v.getId() == R.id.btn10) {


            String path4 = Environment.getExternalStorageDirectory().getAbsolutePath();

            File mydir = new File(path4 +"/android");
            if (!mydir.isDirectory()) mydir.mkdir();

            File[] files = mydir.listFiles();

            String joined2 = "";
            for (File file1 : files) {
                if (file1.isDirectory() == true)
                    joined2 += "<폴더> ";
                else
                    joined2 += "<파일> ";

                joined2 += file1.getName() + "\r\n";
            }

            txtview.setText(joined2);
        }
    }
}
