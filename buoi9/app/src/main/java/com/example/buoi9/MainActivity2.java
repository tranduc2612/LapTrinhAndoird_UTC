package com.example.buoi9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textViewNhan;
    Button btnTinh;
    Intent intent;
    int a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        InitWidgets();

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = a + b;
                intent.putExtra("tong",c);
                setResult(33,intent);
                finish();
            }
        });
    }

    private void InitWidgets() {
        textViewNhan = (TextView) findViewById(R.id.textView2);
        btnTinh = (Button) findViewById(R.id.button2);

        intent = getIntent();
        a = intent.getIntExtra("a",-1);
        b = intent.getIntExtra("b",-1);

        textViewNhan.setText("a = "+a+" b= "+b);
    }
}