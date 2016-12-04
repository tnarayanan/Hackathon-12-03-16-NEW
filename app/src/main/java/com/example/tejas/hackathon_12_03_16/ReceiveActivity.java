package com.example.tejas.hackathon_12_03_16;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;

public class ReceiveActivity extends AppCompatActivity {

    AlarmManager alarmManager;

    Button alarmButton;


    Context context;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);

        alarmButton = (Button) findViewById(R.id.alarmButton);

        this.context = this;

        final Calendar calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(this.context, Alarm_Receiver.class);

        final int hour = 16;
        final int minute = 11;

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                pendingIntent = PendingIntent.getBroadcast(ReceiveActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                int currHour, currMin;
                do {
                    currHour = new Time(System.currentTimeMillis()).getHours();
                    currMin = new Time(System.currentTimeMillis()).getMinutes();
                } while (currHour * 60 + currMin < hour * 60 + minute + (5/60));
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            }
        });

    }
}
