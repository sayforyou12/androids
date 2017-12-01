package seveno.android.miniseconds.colorbrick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import seveno.android.miniseconds.R;
import seveno.android.miniseconds.etc.BackPressCloseSystem;

public class CBOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_overcb);

        backPressCloseSystem = new BackPressCloseSystem(this);

        Button mainBtn = (Button) findViewById(R.id.mainBtn);
        Button reBtn = (Button) findViewById(R.id.reBtn);

        reBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CBStartActivity.class);
                startActivity(intent);
            }
        });
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainintent = new Intent(getApplicationContext(), CBStartActivity.class);
                startActivity(mainintent);
            }
        });
    }

    private BackPressCloseSystem backPressCloseSystem;

    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }
}
