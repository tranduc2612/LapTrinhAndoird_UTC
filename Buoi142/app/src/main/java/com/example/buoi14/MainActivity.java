package com.example.buoi14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton addSong;
    ArrayList<Song> lstSong;
    SongsAdapter adapter;
    SongHelper helper = new SongHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        InsertData();
        lstSong = (ArrayList<Song>) helper.getAllSongs();
        adapter = new SongsAdapter(this, lstSong);
        listView.setAdapter(adapter);

        addSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InsertData() {
        helper.addSong(new Song("Chiec la mua dong", "Thuy chi", 1.25));
        helper.addSong(new Song("Phut cuoi", "Tung Duong", 1.25));
        helper.addSong(new Song("Ba toi", "Thuy chi", 1.25));
        helper.addSong(new Song("Got Hong", "Thuy chi", 1.25));
        helper.addSong(new Song("Dem dong", "Thuy chi", 1.25));

    }

    private void InitWidget() {
        listView = findViewById(R.id.listview);
        addSong = findViewById(R.id.floatingActionButton2);
    }
}