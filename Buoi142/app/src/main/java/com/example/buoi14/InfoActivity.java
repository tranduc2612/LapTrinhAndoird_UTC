package com.example.buoi14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InfoActivity extends AppCompatActivity {

    EditText nameinput,authorinput,timeadd;
    Button btnadd,btnback;
    SongHelper helper = new SongHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        nameinput = (EditText) findViewById(R.id.nameadd);
        authorinput = (EditText) findViewById(R.id.authoradd);
        timeadd = (EditText) findViewById(R.id.timeadd);
        btnadd = (Button) findViewById(R.id.buttonadd);
        btnback = (Button) findViewById(R.id.buttonback);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameinput.getText().toString().trim();
                String author = authorinput.getText().toString().trim();
                String date = timeadd.getText().toString().trim();

                Song newSong = new Song(name,author,Double.parseDouble(date));
                helper.addSong(newSong);
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}