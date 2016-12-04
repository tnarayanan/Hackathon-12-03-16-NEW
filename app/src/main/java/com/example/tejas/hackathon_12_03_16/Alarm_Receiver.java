package com.example.tejas.hackathon_12_03_16;

/**
 * Created by Tejas on 12/3/16.
 */

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class Alarm_Receiver extends IntentService {
    public Alarm_Receiver(String name) {
        super(name);
    }
    /* public void onHandleIntent (Intent intent) {
        Log.e("We are the receiver", "Yay");

        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        context.startService(service_intent);
    } */

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("We are the receiver", "Yay");
        Intent serviceIntent = new Intent(this, RingtonePlayingService.class);
        startService(serviceIntent);
    }
}
