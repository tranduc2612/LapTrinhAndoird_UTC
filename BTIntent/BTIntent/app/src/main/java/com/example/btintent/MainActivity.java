package com.example.btintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String HoTen, NgaySinh, GioiTinh, QuocTich, SoThich;

    Button buttonSend;
    CheckBox checkBoxTheThao, checkBoxDuLich;
    RadioButton radioButton1, radioButton2;
    Spinner spinnerQuocTich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        InitSpinner();
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                GetValue();
                SendData(intent);
                startActivity(intent);
            }
        });
    }

    private void SendData(Intent intent) {
        intent.putExtra("HoTen", HoTen);
        intent.putExtra("NgaySinh", NgaySinh);
        intent.putExtra("GioiTinh", GioiTinh);
        intent.putExtra("QuocTich", QuocTich);
        intent.putExtra("SoThich", SoThich);
    }

    private void InitSpinner() {
        ArrayList<String> lstQuocTich = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, lstQuocTich);
        lstQuocTich.add("Viet Nam");
        lstQuocTich.add("USA");
        spinnerQuocTich.setAdapter(adapter);
    }

    private void GetValue() {
        HoTen = ((EditText) findViewById(R.id.editTextHoTen)).getText().toString();
        NgaySinh = ((EditText) findViewById(R.id.editTextNgaySinh)).getText().toString();
        if (radioButton1.isChecked())
        {
            GioiTinh = radioButton1.getText().toString();
        }
        if (radioButton2.isChecked())
        {
            GioiTinh = radioButton2.getText().toString();
        }
        QuocTich = spinnerQuocTich.getSelectedItem().toString();
        SoThich = "";
        if (checkBoxTheThao.isChecked())
        {
            SoThich += checkBoxTheThao.getText().toString() + ", ";
        }
        if (checkBoxDuLich.isChecked())
        {
            SoThich += checkBoxDuLich.getText().toString() + ", ";
        }

    }

    private void InitWidget() {
        buttonSend = (Button) findViewById(R.id.buttonSend);
        checkBoxDuLich = (CheckBox) findViewById(R.id.checkBoxDuLich);
        checkBoxTheThao = (CheckBox) findViewById(R.id.checkBoxTheThao);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton);
        radioButton2  = (RadioButton) findViewById(R.id.radioButton2);
        spinnerQuocTich = (Spinner) findViewById(R.id.spinnerQuocTich);
    }
}