package com.example.tuluyenbuoi1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    Spinner spinner;
    EditText inputAcc,inputPass;
    RadioGroup radioGroup;
    RadioButton radioButton;
    CheckBox checkBox;
    ArrayAdapter adapterQuocGia;
    String quocGiaValue,taiKhoanValue,matKhauValue,gioiTinhValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        inits();
        eventSpinner();
        eventAccount();
        eventPassWord();
        eventGender();


    }

    private void eventGender() {
        int id = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(id);
        gioiTinhValue = radioButton.getText().toString();
    }

    private void eventPassWord() {
        matKhauValue = inputPass.getText().toString().trim();
    }

    private void inits() {
        inputAcc = (EditText) findViewById(R.id.tk_dk);
        inputPass = (EditText) findViewById(R.id.mk_dk);
        spinner = (Spinner) findViewById(R.id.spinner);
        radioGroup = (RadioGroup) findViewById(R.id.rdg);

    }

    private void eventAccount() {
        taiKhoanValue = inputAcc.getText().toString().trim();
    }

    private void eventSpinner() {
        ArrayList<String> lstQuocGia = new ArrayList<String>();
        lstQuocGia.add("Quốc Gia");
        lstQuocGia.add("Việt Nam");
        lstQuocGia.add("Trung Quốc");
        lstQuocGia.add("Thái Lan");
        lstQuocGia.add("Mỹ");
        lstQuocGia.add("Campuchia");

        adapterQuocGia = new ArrayAdapter(MainActivity2.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,lstQuocGia);
        spinner.setAdapter(adapterQuocGia);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    Toast.makeText(MainActivity2.this,lstQuocGia.get(position),Toast.LENGTH_SHORT).show();
                    quocGiaValue = lstQuocGia.get(position);
                }else{
                    quocGiaValue = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}

