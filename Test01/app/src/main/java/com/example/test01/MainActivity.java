package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewSinhVien;
    FloatingActionButton buttonAdd;
    ArrayList<Contact_Quynh> listSinhVien;
    LeXuanQuynh_Adapter adapter;
    LeXuanQuynh_SQLite db = new LeXuanQuynh_SQLite(this);
    int pos;
    EditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        db.InsertData();
        listSinhVien = (ArrayList<Contact_Quynh>) db.getAllContacts();
        // sap xep list sinh vien theo ten
        listSinhVien.sort((s1,s2) -> s1.getTen().compareTo(s2.getTen()));
        adapter = new LeXuanQuynh_Adapter(this, listSinhVien);
        listViewSinhVien.setAdapter(adapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactView.class);
                startActivity(intent);
            }
        });

        listViewSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(listViewSinhVien);
                listViewSinhVien.showContextMenu();
                pos = position;
                return true;
            }
        });

        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Xử lý khi EditText nhận focus

                } else {
                    // Xử lý khi EditText mất focus
                    if (edtSearch.getText().toString().isEmpty())
                    {
                        listSinhVien = (ArrayList<Contact_Quynh>) db.getAllContacts();
                        // sap xep list sinh vien theo ten
                        listSinhVien.sort((s1,s2) -> s1.getTen().compareTo(s2.getTen()));
                        adapter = new LeXuanQuynh_Adapter(MainActivity.this, listSinhVien);
                        listViewSinhVien.setAdapter(adapter);
                        return;
                    }
                    int sdt = Integer.parseInt(edtSearch.getText().toString());
                    ArrayList<Contact_Quynh> newArrList = new ArrayList<>();
                    for (Contact_Quynh contact : listSinhVien)
                    {
                        if (Integer.parseInt(contact.getSdt()) > sdt)
                        {
                            newArrList.add(contact);
                        }
                    }
                    adapter = new LeXuanQuynh_Adapter(MainActivity.this, newArrList);
                    listViewSinhVien.setAdapter(adapter);
                }
            }
        });
    }

    private void InitWidget() {
        listViewSinhVien = findViewById(R.id.listViewSinhVien);
        buttonAdd = findViewById(R.id.buttonAdd);
        edtSearch = findViewById(R.id.editTextSeacrh);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Contact_Quynh contact = listSinhVien.get(pos);
        switch (item.getItemId()) {
            case R.id.itemEdit:
                Intent intent = new Intent(MainActivity.this, EditContactView.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
                break;
            case R.id.itemDelete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirm")
                        .setMessage("Le Xuan Quynh wants to delete?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý khi người dùng nhấn nút OK
                                db.deleteContact(contact.getId());
                                listSinhVien.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý khi người dùng nhấn nút Cancel
                            }
                        })
                        .show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}