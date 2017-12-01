package com.android.fileio;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editText = null;
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);
    }

    public void clicked(View view) throws IOException {

        if ( view.getId() == R.id.btnEmbeddedMemoryWrite ) {
            FileOutputStream out = openFileOutput("text.txt", Context.MODE_APPEND);
            String text = editText.getText().toString();
            out.write( text.getBytes() );
            if(out != null) out.close();
        }

        if ( view.getId() == R.id.btnEmbeddedMemoryRead ) {
            InputStream in = openFileInput("text.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-16"));
            String s = "";
            while( (s=br.readLine()) != null ){
                textView.append(s + "\n");
            }
            if(in != null) in.close();
        }

        if ( view.getId() == R.id.btnEmbeddedMemoryRead ) {
            try {
                FileInputStream in = openFileInput("text.txt");
                byte[] txt = new byte[in.available()];
                in.read(txt);
                if(in != null) in.close();
                textView.setText( new String(txt) );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if ( view.getId() == R.id.btnRawFolderRead) {
            InputStream in = getResources().openRawResource(R.raw.raw_test);
            byte []  txt = new byte [in.available()];
            in.read( txt );
            if(in != null) in.close();

            textView.setText( new String(txt) );
        }

        if ( view.getId() == R.id.btnRawFolderWrite) {
            StringBuffer sb = new StringBuffer();

            Resources res = getResources();
            BufferedReader br = new BufferedReader(new InputStreamReader( res.openRawResource(R.raw.raw_test), "UTF-8" ) );

            String readline = "";

            while ( (readline = br.readLine()) != null ) {
                sb.append(readline + "\n");
            }

            br.close();

            String txt = sb.toString();
            textView.setText( new String(txt) );
        }

        if ( view.getId() == R.id.btnCacheFileWrite ) {

            String txt = editText.getText().toString();;
            File cacheFile = new File(getCacheDir(), "cache_data");
            FileOutputStream out = new FileOutputStream(cacheFile);
            out.write(txt.getBytes());
            if(out != null) out.close();
            textView.setText( txt );
        }

        if ( view.getId() == R.id.btnSDCardRead ) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(path, "sample.txt");
            FileInputStream in = new FileInputStream(file.getAbsoluteFile());
            byte[] txt = new byte[in.available()];
            in.read(txt);
            if(in != null) in.close();

            textView.setText( new String(txt) );
        }

        if ( view.getId() == R.id.btnSDCardWrite ) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(path, "sample.txt");
            FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
            String text = editText.getText().toString();;
            out.write( text.getBytes() );
            if(out != null) out.close();
        }

        if ( view.getId() == R.id.btnSDCardFolderMake ) {

            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File mydir = new File ( path + "/mydir" );
            if ( !mydir.isDirectory () ) mydir.mkdir ();
        }

        if ( view.getId() == R.id.btnSDCardFolderDelete ) {

            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File mydir = new File ( path + "/mydir" );
            if (mydir.isDirectory()) {
                File [] children = mydir.listFiles();
                for (int i = 0; i < children.length; i++) {
                    children[i].delete();
                }
            }
            if ( mydir.isDirectory () )
                mydir.delete ();
        }

        if ( view.getId() == R.id.btnSDCardFileList) {

            String path = Environment.getExternalStorageDirectory().getAbsolutePath();

            File mydir = new File ( path + "/testdir" );
            if ( !mydir.isDirectory () ) mydir.mkdir ();

            File [] files = mydir.listFiles ();

            String joined2= "" ;
            for( File file : files ) {
                if (file.isDirectory() == true)
                    joined2 += "<폴더> " ;
                else
                    joined2 += "<파일> " ;

                joined2 += file.getName() + "\r\n";
            }

            textView.setText( joined2 );
        }
    }

    void deleteRecursive(File dir) {
        Log.d("DeleteRecursive", "DELETEPREVIOUS TOP" + dir.getPath());
        if (dir.isDirectory()) {

            String[] children = dir.list();

            for (int i = 0; i < children.length; i++) {

                File temp = new File(dir, children[i]);
                if (temp.isDirectory()) {
                    Log.d("DeleteRecursive", "Recursive Call" + temp.getPath());
                    deleteRecursive(temp);
                }
                else {
                    Log.d("DeleteRecursive", "Delete File" + temp.getPath());
                    boolean b = temp.delete();
                    if (b == false) {
                        Log.d("DeleteRecursive", "DELETE FAIL");
                    }
                }
            }

        }
        dir.delete();
    }
}
