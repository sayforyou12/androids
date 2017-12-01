package com.android.serializable;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.serializable.model.ModelParcelable;
import com.android.serializable.model.Modelserializable;
import com.android.serializable.model.SubActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int intData = 0;
        final String strData = "";

        Button btn1, btn2, btn3, btn4, btn5;


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);

        public void onClick(View view) {
            Uri uri = null;
            Intent intent = new Intent(MainActivity.this, SubActivity.class);

            switch (view.getId()) {
                case R.id.btn1: // 기본 타입 데이터 전달
                    intent.putExtra("intData", intData  );
                    intent.putExtra("strData", strData );
                    break;
                case R.id.btn2:
                    break;
                case R.id.btn3:
                    Modelserializable model3 = new Modelserializable(intData, strData);
                    intent.putExtra("Serial_DATA", model3);
                    startActivity(intent);
                    break;
                case R.id.btn4:
                    ModelParcelable model4 = new ModelParcelable();
                    model4.setIntData(123456789);
                    model4.setStrData("Parcelabel Object");
                    intent.putExtra("SAMPLE_DATA", model4);
                    startActivity(intent);
                    break;
                case R.id.btn5:
                    Bundle model5 = new Bundle();
                    model5.putInt("INT_DATA", 123456789);
                    model5.putString("STR_DATA", "Bundle String");
                    intent.putExtra("SAMPLE_DATA", model5);

                    startActivity(intent);
                    break;
            }

            startActivity(intent);
        }
    }
}
