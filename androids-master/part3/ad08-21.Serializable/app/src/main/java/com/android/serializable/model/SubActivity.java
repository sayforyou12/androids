package com.android.serializable.model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.serializable.R;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

            TextView txt = (TextView) findViewById(R.id.textView);
            TextView txt2 = (TextView) findViewById(R.id.textView2);
            TextView txt3 = (TextView) findViewById(R.id.textView3);

            Intent intent=getIntent();
            Modelserializable model3 = (Modelserializable) intent.getSerializableExtra("Serial_DATA");
            if(model3!=null) {txt.setText("Serializable "+model3.toString());}

            ModelParcelable model4 = (ModelParcelable) intent.getParcelableExtra("SAMPLE_DATA");
            if(model4 !=null) {txt2.setText("Parcelable "+model4.toString());}

            Bundle model5 = (Bundle) intent.getBundleExtra("BUNDLE_DATA");
            if(model5 !=null) {txt3.setText(model5.getInt("INT_DATA")+"/"+model5.getString("STR_DATA"));}
        }
    }
    }
}
