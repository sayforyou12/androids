package com.android.reservation;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    int selectyear, selectmonth, selectday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간예약");

        Button btnstart, btnend;
        RadioButton rdocal, rdotime;
        final Chronometer chrono1;
        final CalendarView calview1;
        final TimePicker picker1;
        final TextView tvyear, tvmonth, tvday, tvhour, tvminute;


        btnstart = (Button) findViewById(R.id.btnstart);
        btnend = (Button) findViewById(R.id.btnend);

        chrono1 = (Chronometer) findViewById(R.id.chrono1);

        rdocal = (RadioButton) findViewById(R.id.rdocal);
        rdotime = (RadioButton) findViewById(R.id.rdotime);

        picker1 = (TimePicker) findViewById(R.id.picker1);
        calview1 = (CalendarView) findViewById(R.id.calview1);

        tvyear = (TextView) findViewById(R.id.tvyear);
        tvmonth = (TextView) findViewById(R.id.tvmonth);
        tvday = (TextView) findViewById(R.id.tvday);
        tvhour = (TextView) findViewById(R.id.tvhour);
        tvminute = (TextView) findViewById(R.id.tvminute);

        picker1.setVisibility(View.INVISIBLE);
        calview1.setVisibility(View.INVISIBLE);


        rdocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker1.setVisibility(View.INVISIBLE);
                calview1.setVisibility(View.VISIBLE);
            }
        });
        rdotime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker1.setVisibility(View.VISIBLE);
                calview1.setVisibility(View.INVISIBLE);

            }
        });

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono1.setBase(SystemClock.elapsedRealtime());
                chrono1.start();
                chrono1.setTextColor(Color.RED);
            }
        });
        btnend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono1.stop();
                chrono1.setTextColor(Color.GRAY);
            }
        });
        calview1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectyear = year;
                selectmonth = month +1;
                selectday = dayOfMonth;

                tvyear.setText(Integer.toString(selectyear));
                tvmonth.setText(Integer.toString(selectmonth));
                tvday.setText(Integer.toString(selectday));

                tvhour.setText(Integer.toString(picker1.getCurrentHour()));
                tvminute.setText(Integer.toString(picker1.getCurrentMinute()));
            }
        });


    }
}
