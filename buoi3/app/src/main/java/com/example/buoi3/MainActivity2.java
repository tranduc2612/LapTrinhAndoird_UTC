package com.example.buoi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    Button btnBack;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Khai báo intent từ bên main
//        Intent intent = getIntent();
        // Lấy dữ liệu vào biến
//        String a = intent.getStringExtra("s1");
//        String b = intent.getStringExtra("s2");
        Bundle bundle = getIntent().getBundleExtra("MyBundle");
        String a = bundle.getString("S1");
        String b = bundle.getString("S2");

        int s = Integer.parseInt(a) + Integer.parseInt(b);
        btnBack = (Button) findViewById(R.id.button2);
        text = (TextView) findViewById(R.id.textView);

        text.setText(String.valueOf(s));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);

                intent1.putExtra("Text1",a);
                intent1.putExtra("Text2",b);
                intent1.putExtra("Text3",String.valueOf(s));
                startActivity(intent1);
                finish();
            }
        });
    }
}