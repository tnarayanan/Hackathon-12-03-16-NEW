package com.example.tejas.hackathon_12_03_16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class SendActivity extends AppCompatActivity {
    TimePicker alarm;
    Button send;
    CharSequence timeSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        alarm = (TimePicker) findViewById(R.id.alarmSend);
        alarm.setIs24HourView(false);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alarm.getHour() >= 12){
                    timeSet = "pm";
                } else {
                    timeSet = "am";
                }
                Integer hour = alarm.getHour();
                if(hour == 0){
                    hour = 12;
                } else if(hour > 12){
                    hour -= 12;
                }
                Integer minute = alarm.getMinute();
                String strMinute = minute.toString();
                if(minute < 10){
                    strMinute = "0" + strMinute;
                }
                Toast toast = Toast.makeText(getApplicationContext(), hour + ":" + strMinute + timeSet, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
