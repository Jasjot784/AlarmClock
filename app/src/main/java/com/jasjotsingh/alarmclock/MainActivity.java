package com.jasjotsingh.alarmclock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1;
    private Button button,button2;
    private int SET_TIMER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlarm("Wake Up", Calendar.HOUR_OF_DAY,Calendar.MINUTE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer("Timer",5);
            }
        });
    if (getIntent()!=null){
        Intent intent = getIntent();
        if (getIntent().getExtras()!=null){
            SPLASH_TIME_OUT =  intent.getExtras().getInt(AlarmClock.EXTRA_LENGTH);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Timer Ends", Toast.LENGTH_SHORT).show();
                }
            },SPLASH_TIME_OUT*1000);
        }

    }

    }

    public void createAlarm(String message,int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void startTimer(String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            Toast.makeText(this, "Timer will set for 5s as soon as you pick choice", Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == SET_TIMER && resultCode == RESULT_OK){
//            if (data != null){
//                int time = data.getExtras().getInt(AlarmClock.EXTRA_LENGTH);
//                SPLASH_TIME_OUT = time;
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, "Timer Ends", Toast.LENGTH_SHORT).show();
//                    }
//                },SPLASH_TIME_OUT);
//
//            }
//        }
//    }
}