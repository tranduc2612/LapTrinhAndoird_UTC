package com.example.studentcontact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InformationDetail extends AppCompatActivity {
    EditText etSBD, etHoTen, etDiemToan, etDiemLy, etDiemHoa;
    Button btnSua, btnQuayVe;
    int id;
    ContactDatabaseHelper helper = new ContactDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        InitWidget();
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        String name = bundle.getString("name");
        String studentID = bundle.getString("studentID");
        float mathScore = bundle.getFloat("toan");
        float physicsScore = bundle.getFloat("ly");
        float chemistryScore = bundle.getFloat("hoa");

        // sử dụng các giá trị lấy được từ bundle ở đây
        etHoTen.setText(name);
        etSBD.setText(studentID);
        etDiemToan.setText(String.valueOf(mathScore));
        etDiemLy.setText(String.valueOf(physicsScore));
        etDiemHoa.setText(String.valueOf(chemistryScore));


        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etHoTen.getText().toString();
                String studentID = etSBD.getText().toString();
                float mathScore = Float.parseFloat(etDiemToan.getText().toString());
                float physicsScore = Float.parseFloat(etDiemLy.getText().toString());
                float chemistryScore = Float.parseFloat(etDiemHoa.getText().toString());

                helper.updateContact(id, new Contact(studentID, name, mathScore, physicsScore, chemistryScore));
                // Đóng activity hiện tại và trở về activity trước đó
                Intent intent = new Intent(InformationDetail.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btnQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Đóng activity hiện tại và trở về activity trước đó
                Intent intent = new Intent(InformationDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    void InitWidget() {
        etSBD = findViewById(R.id.et_sbd);
        etHoTen = findViewById(R.id.et_hoten);
        etDiemToan = findViewById(R.id.et_diemtoan);
        etDiemLy = findViewById(R.id.et_diemly);
        etDiemHoa = findViewById(R.id.et_diemhoa);

        btnSua = findViewById(R.id.btn_sua);
        btnQuayVe = findViewById(R.id.btn_quayve);
    }
}