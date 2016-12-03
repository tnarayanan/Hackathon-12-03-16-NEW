package com.example.tejas.hackathon_12_03_16;

import android.app.AlarmManager;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReceiveActivity extends AppCompatActivity {

    AlarmManager alarmManager;

    final int hour = 1;
    final int minute = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);

        final Calendar calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        String hourString = String.valueOf(hour);
        String minString = String.valueOf(minute);

    }
}
