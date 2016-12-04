package com.example.tejas.hackathon_12_03_16;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;
import java.util.List;
import java.util.Map;

/**
 * Created by Tejas on 12/3/16.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer alarmSound;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        alarmSound = MediaPlayer.create(this, R.raw.alarm);
        alarmSound.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this, "DestroyingWorks", Toast.LENGTH_SHORT).show();
    }
}
