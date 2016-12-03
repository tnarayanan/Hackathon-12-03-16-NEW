package com.example.tejas.hackathon_12_03_16;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReceiveActivity extends AppCompatActivity {

    AlarmManager alarmManager;

    final int hour = 2;
    final int minute = 0;
    Context context;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);

        this.context = this;

        final Calendar calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        String hourString = String.valueOf(hour);
        String minString = String.valueOf(minute);

        Intent intent = new Intent(this.context, Alarm_Receiver.class);

        pendingIntent = PendingIntent.getBroadcast(ReceiveActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
