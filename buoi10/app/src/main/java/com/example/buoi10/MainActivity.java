package com.example.buoi10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    EditText id,name,date,find;
    Button btnInsert,btnDelete,btnUpdate,btnLoad,btnFind;
    TextView textView;
    MyDBHelper dbHelper = new MyDBHelper(this);
    @Override
    protected void onStart(){
        super.onStart();
        dbHelper.openDB();
    }

    protected void onStop() {
        super.onStop();
        dbHelper.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();

        dbHelper = new MyDBHelper(MainActivity.this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long ktInsert = dbHelper.Insert(name.getText().toString(),Integer.parseInt(date.getText().toString()));
                if(ktInsert == -1){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long ktUpdate = dbHelper.Update(Integer.parseInt(id.getText().toString()),name.getText().toString(),Integer.parseInt(date.getText().toString()));
                if(ktUpdate == -1){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor cursor = dbHelper.DisplayAll();
                for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){

                    buffer.append(cursor.getString(cursor.getColumnIndex(dbHelper.getID())));
                    buffer.append("-");
                    buffer.append(cursor.getString(cursor.getColumnIndex(dbHelper.getNAME())));
                    buffer.append("-");
                    buffer.append(cursor.getString(cursor.getColumnIndex(dbHelper.getYEAROB())));
                    buffer.append("\n");
                }
                textView.setText(buffer);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long ktDelete = dbHelper.Delete(Integer.parseInt(
                        id.getText().toString()
                ));

                if(ktDelete == -1){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor cursor = dbHelper.findById(Integer.parseInt(find.getText().toString()));
                for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                    buffer.append(cursor.getString(cursor.getColumnIndex(dbHelper.getID())));
                    buffer.append("-");
                    buffer.append(cursor.getString(cursor.getColumnIndex(dbHelper.getNAME())));
                    buffer.append("-");
                    buffer.append(cursor.getString(cursor.getColumnIndex(dbHelper.getYEAROB())));
                    buffer.append("\n");
                }
                textView.setText(buffer);
            }
        });
    }

    private void initWidgets() {
        id = (EditText) findViewById(R.id.input1);
        name = (EditText) findViewById(R.id.input2);
        date = (EditText) findViewById(R.id.input3);
        find = (EditText) findViewById(R.id.input4);
        btnInsert = (Button) findViewById(R.id.insert);
        btnUpdate = (Button) findViewById(R.id.update);
        btnDelete = (Button) findViewById(R.id.delete);
        btnFind = (Button) findViewById(R.id.find);
        btnLoad = (Button) findViewById(R.id.read);
        textView = (TextView) findViewById(R.id.textView);
    }
}