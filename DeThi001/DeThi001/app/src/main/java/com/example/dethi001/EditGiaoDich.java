package com.example.dethi001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class EditGiaoDich extends AppCompatActivity {
    TextView tvMaGiaoDich;
    EditText edtNoiDung, edtNgayThang, edtTenNguoi, edtSoTien;
    RadioButton rdbTienDi, rdbTienDen;
    Button buttonEdit, buttonBack;
    GiaoDich giaoDich;
    MySQLHelper db = new MySQLHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_giao_dich);
        InitWidget();
        GetData();
        buttonBack.setOnClickListener(x -> finish());
        buttonEdit.setOnClickListener(x -> {
            giaoDich.setNoiDung(edtNoiDung.getText().toString());
            giaoDich.setNgayThang(edtNgayThang.getText().toString());
            giaoDich.setLoaiGiaoDich(rdbTienDen.isChecked());
            giaoDich.setTenNguoi(edtTenNguoi.getText().toString());
            giaoDich.setSoTien(Integer.parseInt(edtSoTien.getText().toString()));

            db.update(giaoDich);

            // chuyen man hinh
            Intent intent = new Intent(EditGiaoDich.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void InitWidget() {
        tvMaGiaoDich = findViewById(R.id.tvMaGiaoDich);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        edtNgayThang = findViewById(R.id.edtNgayThang);
        edtTenNguoi = findViewById(R.id.edtTenNguoi);
        edtSoTien = findViewById(R.id.edtSoTien);
        rdbTienDen = findViewById(R.id.radioBtnTienDen);
        rdbTienDi = findViewById(R.id.radioBtnTienDi);
        buttonEdit = findViewById(R.id.buttonSua);
        buttonBack = findViewById(R.id.buttonBack);
    }

    private void GetData() {
        giaoDich = (GiaoDich) getIntent().getSerializableExtra("data");
        tvMaGiaoDich.setText(String.valueOf(giaoDich.getMaGiaoDich()));
        edtNoiDung.setText(giaoDich.getNoiDung());
        edtNgayThang.setText(giaoDich.getNgayThang());
        edtTenNguoi.setText(giaoDich.getTenNguoi());
        edtSoTien.setText(String.valueOf(giaoDich.getSoTien()));
        if (giaoDich.isLoaiGiaoDich() == true)
        {
            rdbTienDen.setChecked(true);
        }
        else
        {
            rdbTienDi.setChecked(true);
        }
    }
}