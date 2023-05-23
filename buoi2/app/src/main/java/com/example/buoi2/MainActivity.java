package com.example.buoi2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText inputWeight;
    EditText inputHeight;
    TextView ans;
    Button btn;
    TextView textViewBMI;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputWeight = (EditText) findViewById(R.id.wInp);
        inputHeight = (EditText) findViewById(R.id.hInp);
        btn = (Button) findViewById(R.id.btnConvert);
        textViewBMI = (TextView) findViewById(R.id.answer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double weight = Double.parseDouble(inputWeight.getText().toString());
                double height = Double.parseDouble(inputHeight.getText().toString());
                double BMI = weight/(height*height);
                DecimalFormat df = new DecimalFormat("#.#");
                textViewBMI.setText(String.valueOf(df.format(BMI)));
                String kq;
                if(BMI<15){
                    kq="Suy dinh dưỡng";
                }else if(BMI<16){
                    kq="Rất gầy";
                }else if(BMI<18.5){
                    kq ="Gầy";
                }else if(BMI<25) {
                    kq = "Bình thường";
                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    imageView.setImageResource(R.drawable.normal);
                }else if(BMI<30){
                    kq = "Thừa cân";
                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    imageView.setImageResource(R.drawable.normal);
                }else if(BMI<35){
                    kq = "Thừa cân cấp 1";
                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    imageView.setImageResource(R.drawable.normal);
                }

            }
        });

    }

}