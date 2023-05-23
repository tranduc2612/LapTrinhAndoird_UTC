package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactView extends AppCompatActivity {

    Button buttonAdd, buttonBack;
    EditText edtID, edtName, edtNumber;
    LeXuanQuynh_SQLite db = new LeXuanQuynh_SQLite(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_view);
        InitWidget();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtID.getText().toString());
                String name = edtName.getText().toString();
                String number = edtNumber.getText().toString();

                Contact_Quynh contact = new Contact_Quynh(id, name, number);
                db.addContact(contact);
                // Chuyen man hinh
                Intent intent = new Intent(AddContactView.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitWidget() {
        buttonAdd = findViewById(R.id.buttonEdit);
        buttonBack = findViewById(R.id.buttonBackEdit);
        edtID = findViewById(R.id.edtIDEdit);
        edtName = findViewById(R.id.edtNameEdit);
        edtNumber = findViewById(R.id.edtNumberEdit);
    }
}