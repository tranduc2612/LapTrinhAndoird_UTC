package com.example.buoi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText input1,input2;
    TextView v1,v2,vkq;
    Button btn,btnBundel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();


        Intent intent1 = getIntent();
        String a = intent1.getStringExtra("Text1");
        String b = intent1.getStringExtra("Text2");
        String c = intent1.getStringExtra("Text3");

        v1.setText(a);
        v2.setText(b);
        vkq.setText(c);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                String So1 = input1.getText().toString();
                String So2 = input2.getText().toString();
                // Đưa dữ liệu vào intent
                intent.putExtra("s1",So1);
                intent.putExtra("s2",So2);
                startActivity(intent);
            }
        });

        btnBundel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                String So1 = input1.getText().toString();
                String So2 = input2.getText().toString();
                // dua vao bundle
                bundle.putString("S1",So1);
                bundle.putString("S2",So2);
                intent.putExtra("MyBundle",bundle);
                startActivity(intent);
            }
        });
    }

    private void InitWidgets() {
        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
        btn = (Button) findViewById(R.id.button);

        v1 = (TextView) findViewById(R.id.textviewso1);
        v2 = (TextView) findViewById(R.id.textviewso2);
        vkq = (TextView) findViewById(R.id.textviewtong2);
        btnBundel = (Button) findViewById(R.id.bundel);

    }
}