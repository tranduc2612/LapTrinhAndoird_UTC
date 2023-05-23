package com.example.de180501;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvSanPham;
    FloatingActionButton btnThem;
    List<Sanpham> lstSanPham;
    ListViewAdapter adapter;
    MySQLHelper db = new MySQLHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        db.insertData();
        lstSanPham = db.GetAll();
        adapter = new ListViewAdapter(this, lstSanPham);
        lvSanPham.setAdapter(adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addSpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitWidget(){
        lvSanPham = findViewById(R.id.lvSanPham);
        btnThem = findViewById(R.id.btnThem);
    }
}