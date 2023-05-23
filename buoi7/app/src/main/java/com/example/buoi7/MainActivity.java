package com.example.buoi7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText name,date;
    RadioGroup radioGroup;
    CheckBox checkBox1,checkBox2;
    Spinner spinner;
    RadioButton radioNam,radioNu;
    Button btn,btnBundle,btnObj,btnObjBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        ArrayList<String> qt = new ArrayList<String>();
        qt.add("VN");
        qt.add("Lao");
        qt.add("Campuchia");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,qt);
        spinner.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("HT",name.getText().toString());
                intent.putExtra("NS",date.getText().toString());
                intent.putExtra("QT",spinner.getSelectedItem().toString());
                if(radioNam.isChecked()){
                    intent.putExtra("GT",radioNam.getText().toString());

                }
                if(radioNu.isChecked()){
                    intent.putExtra("GT",radioNu.getText().toString());
                }
                String st ="";
                if(checkBox1.isChecked()){
                    st += checkBox1.getText().toString();
                }
                if (checkBox2.isChecked()){
                    st += checkBox2.getText().toString();
                }
                intent.putExtra("SoThich",st);
                startActivity(intent);
            }
        });

        btnBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("HT",name.getText().toString());
                bundle.putString("NS",date.getText().toString());
                bundle.putString("QT",spinner.getSelectedItem().toString());
                if(radioNam.isChecked()){
                    bundle.putString("GT",radioNam.getText().toString());
                }
                if(radioNu.isChecked()){
                    bundle.putString("GT",radioNu.getText().toString());
                }
                String st ="";
                if(checkBox1.isChecked()){
                    st += checkBox1.getText().toString();
                }
                if (checkBox2.isChecked()){
                    st += checkBox2.getText().toString();
                }
                bundle.putString("ST",st);
                intent.putExtra("MyBundle",bundle);
                startActivity(intent);
            }
        });

        btnObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                String ht = name.getText().toString();
                Integer ns = Integer.parseInt(date.getText().toString());

                intent.putExtra("HT",ht);
                intent.putExtra("NS",ns);

                // chen doi tuong
                SinhVien sv = new SinhVien(201210096,"Quang Ninh");
                intent.putExtra("SinhVien",sv);
                startActivity(intent);
            }
        });

        btnObjBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("HT",name.getText().toString());
                Integer ns = Integer.parseInt(date.getText().toString());
                bundle.putInt("NS",ns);
                SinhVien sv = new SinhVien(201210096,"Quang Ninh");
                bundle.putSerializable("SinhVien",sv);
                intent.putExtra("MyBundle",bundle);
                startActivity(intent);
            }
        });

    }

    protected void initial(){
        name = (EditText) findViewById(R.id.name);
        date = (EditText) findViewById(R.id.date);
        radioNam = (RadioButton) findViewById(R.id.radioButton);
        radioNu = (RadioButton) findViewById(R.id.radioButton2);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        btn = (Button) findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnBundle = (Button) findViewById(R.id.bundle);
        btnObj = (Button) findViewById(R.id.button3);
        btnObjBundle = (Button) findViewById(R.id.button4);
    }
    
    
}