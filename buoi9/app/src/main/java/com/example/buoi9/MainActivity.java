package com.example.buoi9;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText soa,sob;
    Button btnSend;
    TextView ketqua;
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()==33){
                Intent intent = result.getData();
                int i = intent.getIntExtra("tong", -1);
                ketqua.setText("tong cach(2) = " + i);

            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLy();
            }
        });
    }

    private void XuLy() {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra("a",Integer.parseInt(soa.getText().toString()));
        intent.putExtra("b",Integer.parseInt(sob.getText().toString()));

        // send va lay du lieu tra ve
        // khong dung startactivities
//        startActivityForResult(intent,99);

        mActivityResultLauncher.launch(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == 33){
            int i = data.getIntExtra("tong",1);
            ketqua.setText("Tong = " + i);
        }
    }

    private void initWidgets() {
        soa = (EditText) findViewById(R.id.input1);
        sob = (EditText) findViewById(R.id.input2);

        btnSend = (Button) findViewById(R.id.button);
        ketqua = (TextView) findViewById(R.id.textView);
    }
}