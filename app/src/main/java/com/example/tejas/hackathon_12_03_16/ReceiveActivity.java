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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;

public class ReceiveActivity extends AppCompatActivity {
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
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



        int hour;
        int minute;

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int hour_inside = 0;
                int minute_inside = 0;
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    if(postSnapshot.getKey().equals("Hour")){
                        hour_inside = Integer.parseInt(postSnapshot.getValue().toString());

                    }else if(postSnapshot.getKey().equals("Minute")){
                        minute_inside = Integer.parseInt(postSnapshot.getValue().toString());
                    }
                }
                setAlarm(hour_inside, minute_inside);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void setAlarm(int hour, int minute){
        final int hour_this = hour;
        final int minute_this = minute;
        final Calendar calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(this.context, Alarm_Receiver.class);

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY, hour_this);
                calendar.set(Calendar.MINUTE, minute_this);
                pendingIntent = PendingIntent.getBroadcast(ReceiveActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                int currHour, currMin;
                do {
                    currHour = new Time(System.currentTimeMillis()).getHours();
                    currMin = new Time(System.currentTimeMillis()).getMinutes();
                } while (currHour * 60 + currMin < hour_this * 60 + minute_this + (5/60));
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            }
        });
    }
}
