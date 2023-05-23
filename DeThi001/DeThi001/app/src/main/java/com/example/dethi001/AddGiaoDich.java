package com.example.dethi001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class AddGiaoDich extends AppCompatActivity {

    TextView tvMaGiaoDich;
    EditText edtNoiDung, edtNgayThang, edtTenNguoi, edtSoTien;
    RadioButton rdbTienDi, rdbTienDen;
    Button buttonAdd, buttonBack;
    GiaoDich giaoDich;
    MySQLHelper db = new MySQLHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_giao_dich);
        InitWidget();
        buttonBack.setOnClickListener(x -> finish());
        buttonAdd.setOnClickListener(x -> {
            giaoDich = new GiaoDich();
            giaoDich.setMaGiaoDich(0);
            giaoDich.setNoiDung(edtNoiDung.getText().toString());
            giaoDich.setNgayThang(edtNgayThang.getText().toString());
            giaoDich.setLoaiGiaoDich(rdbTienDen.isChecked());
            giaoDich.setTenNguoi(edtTenNguoi.getText().toString());
            giaoDich.setSoTien(Integer.parseInt(edtSoTien.getText().toString()));

            db.add(giaoDich);

            // chuyen man hinh
            Intent intent = new Intent(AddGiaoDich.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void InitWidget() {
        tvMaGiaoDich = findViewById(R.id.tvMaGiaoDichAdd);
        edtNoiDung = findViewById(R.id.edtNoiDungAdd);
        edtNgayThang = findViewById(R.id.edtNgayThangAdd);
        edtTenNguoi = findViewById(R.id.edtTenNguoiAdd);
        edtSoTien = findViewById(R.id.edtSoTienAdd);
        rdbTienDen = findViewById(R.id.radioBtnTienDenAdd);
        rdbTienDi = findViewById(R.id.radioBtnTienDiAdd);
        buttonAdd = findViewById(R.id.buttonThem);
        buttonBack = findViewById(R.id.buttonBackAdd);
    }
}