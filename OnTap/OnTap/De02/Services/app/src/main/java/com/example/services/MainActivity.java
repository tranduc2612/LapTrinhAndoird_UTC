package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;

import java.security.Provider;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    Button btnPlay, btnStop, btnStartService, btnStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(" Play the sound ");
        InitWidget();
        btnPlay.setOnClickListener(view -> {
            player = MediaPlayer.create(MainActivity.this, Settings.System.DEFAULT_RINGTONE_URI);
            player.setLooping(true);
            player.start();
        });

        btnStop.setOnClickListener(view -> player.stop());
        btnStartService.setOnClickListener(view ->
                 startService(
                        new Intent(MainActivity.this, MyServices.class)
                ));

        btnStopService.setOnClickListener(view ->
                stopService(
                        new Intent(MainActivity.this, MyServices.class)
                ));
    }

    public void InitWidget(){
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStopService = (Button) findViewById(R.id.btnStopService);
    }
}