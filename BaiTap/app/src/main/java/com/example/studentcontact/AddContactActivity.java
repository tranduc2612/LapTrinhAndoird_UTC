package com.example.studentcontact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private EditText edtSoBaoDanh;
    private EditText edtHoTen;
    private EditText edtDiemToan;
    private EditText edtDiemLy;
    private EditText edtDiemHoa;
    Button btnAddContact;
    ContactDatabaseHelper helper = new ContactDatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtSoBaoDanh = findViewById(R.id.etSoBaoDanh);
        edtHoTen = findViewById(R.id.etHoTen);
        edtDiemToan = findViewById(R.id.etDiemToan);
        edtDiemLy = findViewById(R.id.etDiemLy);
        edtDiemHoa = findViewById(R.id.etDiemHoa);
        btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
    }

    public void addContact() {
        String soBaoDanh = edtSoBaoDanh.getText().toString().trim();
        String hoTen = edtHoTen.getText().toString().trim();
        String diemToan = edtDiemToan.getText().toString().trim();
        String diemLy = edtDiemLy.getText().toString().trim();
        String diemHoa = edtDiemHoa.getText().toString().trim();

        if (TextUtils.isEmpty(soBaoDanh) || TextUtils.isEmpty(hoTen)
                || TextUtils.isEmpty(diemToan) || TextUtils.isEmpty(diemLy)
                || TextUtils.isEmpty(diemHoa)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            float fDiemToan = Float.parseFloat(diemToan);
            float fDiemLy = Float.parseFloat(diemLy);
            float fDiemHoa = Float.parseFloat(diemHoa);

            Contact newContact = new Contact(soBaoDanh, hoTen, fDiemToan, fDiemLy, fDiemHoa);
            helper.addContact(newContact);
            // Quay lại main activity
            Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
