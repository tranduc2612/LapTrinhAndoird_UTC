package com.example.buoi21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btnPlay,btnStop,btnStartService,btnStopService;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidGet();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player = MediaPlayer.create(MainActivity.this, Settings.System.DEFAULT_RINGTONE_URI);
                player.setLooping(true);
                player.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
            }
        });

        btnStartService.setOnClickListener(view ->  {
           Intent intent = new Intent(MainActivity.this,MyService.class);
           startService(intent);
        });

        btnStopService.setOnClickListener(view ->  startService(new Intent(MainActivity.this,MyService.class)));
    }


    private void initWidGet() {
        btnPlay = (Button) findViewById(R.id.button);
        btnStop = (Button) findViewById(R.id.button2);
        btnStartService = (Button) findViewById(R.id.button3);
        btnStopService = (Button) findViewById(R.id.button4);
    }
}