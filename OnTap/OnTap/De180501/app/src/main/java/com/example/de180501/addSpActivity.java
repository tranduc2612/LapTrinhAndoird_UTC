package com.example.de180501;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class addSpActivity extends AppCompatActivity {
    TextView edtTenSP, edtGia;
    Switch swGiamGia;
    Button btnDongY, btnQuayVe;
    MySQLHelper helper = new MySQLHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sp);
        InitWidget();
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensp = edtTenSP.getText().toString().trim();
                String gia = edtGia.getText().toString().trim();
                Boolean giamgia = swGiamGia.isChecked();

                Sanpham spmoi = new Sanpham(0, tensp, giamgia, Integer.parseInt(gia));
                helper.add(spmoi);
                Intent intent = new Intent(addSpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void InitWidget() {
        edtTenSP = findViewById(R.id.edtTenSP);
        edtGia = findViewById(R.id.edtGia);
        swGiamGia = findViewById(R.id.swGiamGia);
        btnDongY = findViewById(R.id.btnDongY);
        btnQuayVe = findViewById(R.id.btnQuayVe);
    }
}
