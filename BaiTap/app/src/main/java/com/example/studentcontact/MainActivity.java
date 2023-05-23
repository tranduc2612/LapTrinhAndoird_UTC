package com.example.studentcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button btnAddContact;
    ArrayList<Contact> contacts;
    ContactListAdapter adapter;
    ContactDatabaseHelper helper = new ContactDatabaseHelper(this);
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        contacts = (ArrayList<Contact>) helper.getAllContacts();
        adapter = new ContactListAdapter(MainActivity.this, contacts);

        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                registerForContextMenu(listView);
                listView.showContextMenu();
                position = pos;
                return true;
            }
        });

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // lấy thông tin Sinh viên được chọn
        Contact selectedContact = contacts.get(position);
        switch (item.getItemId()) {
            case R.id.edit_item:
                // chuyển sang màn hình EditContactActivity
                Intent intent = new Intent(MainActivity.this, InformationDetail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", selectedContact.getId());
                bundle.putString("name", selectedContact.getName());
                bundle.putString("studentID", selectedContact.getStudentID());
                bundle.putFloat("toan", selectedContact.getMathScore());
                bundle.putFloat("ly", selectedContact.getPhysicsScore());
                bundle.putFloat("hoa", selectedContact.getChemistryScore());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.delete_item:
                // xóa Sinh viên
                contacts.remove(position);
                helper.deleteContact(selectedContact);

                // cập nhật lại ListView
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }


    void InitWidget() {
        listView = findViewById(R.id.listView);
        btnAddContact = findViewById(R.id.btn_add_contact);
    }


}