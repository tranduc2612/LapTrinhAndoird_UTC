package com.example.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


public class MyServices extends Service {
    private MediaPlayer player;
    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
    @Override
    public void onDestroy()
    {
        player.stop();
        notificationManager.cancel(NOTIFICATION_ID);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();

        //status bar notification
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String noti_id = "Noti_id";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(noti_id, "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, noti_id)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Now Playing")
                .setContentText("Music's Name")
                .setAutoCancel(false)
                .setOngoing(true);
        Notification notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);

        return START_STICKY;
    }
}
