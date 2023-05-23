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

public class ShowContact extends AppCompatActivity {
    Button btnBack;
    ListView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        btnBack = (Button) findViewById(R.id.button4);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        ShowContact1();
    }

    private void ShowContact1() {
        Uri uri = Uri.parse("content://contacts/people");

        ArrayList<String> lst = new ArrayList<String>();
        Cursor c1 = getContentResolver().query(uri,null,null,null,null);
        c1.moveToFirst();

        while (!c1.isAfterLast()){
            String s = "";
            String idColumnName = ContactsContract.Contacts._ID;
            int idIndex = c1.getColumnIndex(idColumnName);
            s = c1.getString(idIndex) + "-";
            String nameColumnName = ContactsContract.Contacts.DISPLAY_NAME;
            int nameIndex = c1.getColumnIndex(nameColumnName);
            s = s + c1.getString(nameIndex);
            c1.moveToNext();
            lst.add(s);
        }
        c1.close();

        // add vao list view

        lstView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lst);
        lstView.setAdapter(adapter);
    }
}