package com.example.buoi14;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvGiaoDich;
    List<GiaoDich> lstGiaoDich;
    ListViewAdapter adapter;
    TextView soDu;

    int pos;

    MySQLHelper db = new MySQLHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        db.insertData();
        lstGiaoDich = db.getAll();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lstGiaoDich.sort((a,b) -> a.getNgayThang().compareTo(b.getNgayThang()));
        }

        adapter = new ListViewAdapter(this,lstGiaoDich);
        lvGiaoDich.setAdapter(adapter);

        soDu.setText(tinhSoDu().toString());

        lvGiaoDich.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(lvGiaoDich);
                lvGiaoDich.showContextMenu();
                pos = position;
                return true;
            }
        });


    }

    private void InitWidget() {
        lvGiaoDich = findViewById(R.id.lvGiaoDich);
        soDu = findViewById(R.id.soDu);

    }

    Integer tinhSoDu(){
        int tongTienDen = 0;
        int tongTienDi = 0;

        for(GiaoDich giaoDich: lstGiaoDich){
            if(giaoDich.isLoaiGiaoDich()==true){
                tongTienDen += Integer.parseInt(giaoDich.getSoTien());
            }else{
                tongTienDi += Integer.parseInt(giaoDich.getSoTien());
            }
        }

        return tongTienDen - tongTienDi;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Contact_Quynh contact = listSinhVien.get(pos);
        switch (item.getItemId()) {
            case R.id.itemsua:
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