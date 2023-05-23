package com.example.buoi7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
//        String st = "";
//        st = intent.getStringExtra("HT");
//        st += intent.getStringExtra("NS");
//        st += intent.getStringExtra("QT");
//        st += intent.getStringExtra("GT");
//        st += intent.getStringExtra("ST");

//        Bundle bundle = intent.getBundleExtra("MyBundle");
//        st = bundle.getString("HT");
//        st += bundle.getString("NS");
//        st += bundle.getString("QT");
//        st += bundle.getString("GT");
//        st += bundle.getString("ST");

//        Intent intent = getIntent();
//        String ht = intent.getStringExtra("HT");
//        int ns = intent.getIntExtra("NS",0);
//
//        SinhVien sv = (SinhVien) intent.getSerializableExtra("SinhVien");
//
//        String st = "Ho ten: " + ht + "Ngay Sinh: " + ns + sv;

        // Gửi obj,kiểu dữ liệu khác nhau bằng bundle
        Bundle bundle = intent.getBundleExtra("MyBundle");
        String ht = bundle.getString("HT");
        int ns = bundle.getInt("NS",0);
        SinhVien sv = (SinhVien) bundle.getSerializable("SinhVien");

        String st = "Ho ten: " + ht + "Ngay Sinh: " + ns + sv;

        Toast.makeText(MainActivity2.this,st,Toast.LENGTH_SHORT).show();
    }
}