package com.example.btintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textViewHoTen, textViewNgaySinh, textViewGioiTinh, textViewQuocTich, textViewSoThich;
    String HoTen, NgaySinh, GioiTinh, QuocTich, SoThich;
    Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        InitWidgets();
        Intent intent = getIntent();
        ReceiveData(intent);
        SetTextAll();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void SetTextAll() {
        textViewHoTen.setText(HoTen);
        textViewNgaySinh.setText(NgaySinh);
        textViewGioiTinh.setText(GioiTinh);
        textViewQuocTich.setText(QuocTich);
        textViewSoThich.setText(SoThich);
    }

    private void ReceiveData(Intent intent) {
        HoTen = intent.getStringExtra("HoTen");
        NgaySinh =  intent.getStringExtra("NgaySinh");
        GioiTinh = intent.getStringExtra("GioiTinh");
        QuocTich = intent.getStringExtra("QuocTich");
        SoThich = intent.getStringExtra("SoThich");
    }

    private void InitWidgets() {
        textViewHoTen = (TextView) findViewById(R.id.textViewHoTen);
        textViewGioiTinh = (TextView) findViewById(R.id.textViewGioiTinh);
        textViewNgaySinh = (TextView) findViewById(R.id.textViewNgaySinh);
        textViewQuocTich = (TextView) findViewById(R.id.textViewQuocTich);
        textViewSoThich = (TextView) findViewById(R.id.textViewSoThich);
        buttonBack = (Button) findViewById(R.id.buttonBack);
    }
}