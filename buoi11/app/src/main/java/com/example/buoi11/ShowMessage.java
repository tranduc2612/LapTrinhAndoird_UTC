package com.example.buoi11;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowMessage extends AppCompatActivity {

    Button btnBack;
    ListView lstView;
    ArrayList<String> messlist;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);
        
        initWidGet();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        messlist = new ArrayList<String>();
        adapter = new ArrayAdapter<>(ShowMessage.this, android.R.layout.simple_list_item_1,messlist);
        lstView.setAdapter(adapter);
        readMessage();
    }

    private void readMessage() {
        Uri uri = Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        messlist.clear();
        while (cursor.moveToNext()){
            int index_phone = cursor.getColumnIndex("address");
            int index_date = cursor.getColumnIndex("date");
            int index_body = cursor.getColumnIndex("body");

            String phonenumber = cursor.getString(index_phone);
            String date_ = cursor.getString(index_date);
            String body_ = cursor.getString(index_body);

            messlist.add(phonenumber + "\n" + date_ + "\n" + body_);
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private void initWidGet() {
        btnBack = (Button) findViewById(R.id.button5);
        lstView = (ListView) findViewById(R.id.listview2);
    }
}