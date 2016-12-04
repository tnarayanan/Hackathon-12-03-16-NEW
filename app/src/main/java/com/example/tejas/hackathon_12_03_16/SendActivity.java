package com.example.tejas.hackathon_12_03_16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SendActivity extends AppCompatActivity {
    EditText name;
    String blank;
    TimePicker alarm;
    Button send;
    CharSequence timeSet;
    public static ArrayList acceptedNames = new ArrayList<String>();
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    Boolean nameAccepted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        acceptedNames.add("Sripad");
        acceptedNames.add("Tejas");
        alarm = (TimePicker) findViewById(R.id.alarmSend);
        alarm.setIs24HourView(false);
        send = (Button) findViewById(R.id.send);
        name = (EditText) findViewById(R.id.toWho);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "NO NOMBRE AQUI", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    for(int i = 0; i < acceptedNames.size(); i++){
                        if(nameAccepted == false) {
                            nameAccepted = name.getText().toString().equals(acceptedNames.get(i).toString());
                        }
                    }
                    if(nameAccepted) {
                        if (alarm.getHour() >= 12) {
                            timeSet = "pm";
                        } else {
                            timeSet = "am";
                        }
                        Integer hour = alarm.getHour();
                        if (hour == 0) {
                            hour = 12;
                        } else if (hour > 12) {
                            hour -= 12;
                        }
                        Integer minute = alarm.getMinute();
                        String strMinute = minute.toString();
                        if (minute < 10) {
                            strMinute = "0" + strMinute;
                        }
                        //Toast toast = Toast.makeText(getApplicationContext(), name.getText().equals("") + "", Toast.LENGTH_SHORT);
                        Toast toast = Toast.makeText(getApplicationContext(), hour + ":" + strMinute + timeSet + " to " + name.getText(), Toast.LENGTH_SHORT);
                        toast.show();
                        nameAccepted = false;

                        mDatabase.child("Hour").setValue(alarm.getHour());
                        mDatabase.child("Minute").setValue(alarm.getMinute());

                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), name.getText().toString() + " isn't a registered user", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

    }
}
