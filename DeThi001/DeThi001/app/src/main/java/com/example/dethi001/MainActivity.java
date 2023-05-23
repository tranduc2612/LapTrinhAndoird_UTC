package com.example.dethi001;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvGiaoDich;
    List<GiaoDich> lstGiaoDich;
    ListViewAdapter adapter;
    MySQLHelper db = new MySQLHelper(this);
    TextView tvSoDu;
    int pos;
    EditText edtSearch;
    FloatingActionButton btnAdd;

    CheckNetwork checkNetwork = new CheckNetwork();

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(checkNetwork, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Hủy đăng ký BroadcastReceiver
        unregisterReceiver(checkNetwork);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        db.insertData();
        lstGiaoDich = db.getAll();
        // sap xep tang dan theo thu tu ngay thang
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lstGiaoDich.sort((a, b) -> a.getNgayThang().compareTo(b.getNgayThang()));
        }
        adapter = new ListViewAdapter(this, lstGiaoDich);
        lvGiaoDich.setAdapter(adapter);

        //
            setSoDu();
        //
        lvGiaoDich.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    // Hiển thị context menu
                    registerForContextMenu(lvGiaoDich);
                    lvGiaoDich.showContextMenu();
                    pos = position;

                    // Hiện ra tổng số giao dịch có số tiền lớn hơn số tiền của item được chọn
                    GiaoDich giaoDichSelected = lstGiaoDich.get(position);
                    int cnt = 0;
                    for (GiaoDich giaoDich : lstGiaoDich)
                    {
                        if (giaoDich.getSoTien() > giaoDichSelected.getSoTien())
                        {
                            cnt++;
                        }
                    }
                    Toast.makeText(MainActivity.this, "Tỏng số giao dịch: " + cnt, Toast.LENGTH_SHORT).show();
                    return true;
                }
        });

        //
        btnAdd.setOnClickListener(x ->
        {
            Intent intent = new Intent(MainActivity.this, AddGiaoDich.class);
            startActivity(intent);
        });

        // Tìm kiếm các bản ghi có Nội dung bao gồm chữ cái mà người dùng nhập vào
        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    // Xử lí khi editText mất focus
                    if (edtSearch.getText().toString().isEmpty())
                    {
                        adapter = new ListViewAdapter(MainActivity.this, lstGiaoDich);
                        lvGiaoDich.setAdapter(adapter);
                        return;
                    }
                    String noiDung = edtSearch.getText().toString();
                    List<GiaoDich> allGiaoDich = db.getAll();
                    List<GiaoDich> newList = new ArrayList<>();
                    for (GiaoDich giaoDich : allGiaoDich)
                    {
                        if (giaoDich.getNoiDung().toLowerCase().contains(noiDung.toLowerCase()))
                        {
                             newList.add(giaoDich);
                        }
                    }
                    adapter = new ListViewAdapter(MainActivity.this, newList);
                    lvGiaoDich.setAdapter(adapter);
                }
            }
        });
    }

    private void InitWidget() {
        lvGiaoDich = findViewById(R.id.lvGiaoDich);
        tvSoDu = findViewById(R.id.tvSoDu);
        btnAdd = findViewById(R.id.btnAdd);
        edtSearch = findViewById(R.id.edtSearch);
    }

    Integer tinhSoDu()
    {
        int tongTienDen = 0, tongTienDi = 0;
        // tong tien den
        for (GiaoDich giaoDich : lstGiaoDich)
        {
            if (giaoDich.isLoaiGiaoDich() == true)
            {
                tongTienDen += giaoDich.getSoTien();
            }
            else
            {
                tongTienDi += giaoDich.getSoTien();
            }
        }

        return tongTienDen - tongTienDi;
    }

    void setSoDu()
    {
        NumberFormat numberFormat = new DecimalFormat("#,###");
        String formattedNumber = numberFormat.format(tinhSoDu());
        tvSoDu.setText(formattedNumber);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Lay thong tin giao dich
        GiaoDich giaoDich = lstGiaoDich.get(pos);
        switch (item.getItemId()) {
            case R.id.itemSua:
                //
                Intent intent = new Intent(MainActivity.this, EditGiaoDich.class);
                intent.putExtra("data", giaoDich);
                startActivity(intent);
                break;
            case R.id.itemXoa:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String msg = "Bạn có muốn xoá khoản Giao dịch?\n";
                msg += "Ngày tháng: " + giaoDich.getNgayThang() + "\n";
                msg += (giaoDich.isLoaiGiaoDich() == true?"Tiền đến từ: ":"Tiền đi tới: ") + giaoDich.getTenNguoi() + "\n";
                msg += "Số tiền: " + giaoDich.getSoTien() + "\n";
                builder.setTitle("Confirm")
                        .setMessage(msg)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý khi người dùng nhấn nút OK
                                db.delete(giaoDich.getMaGiaoDich());
                                lstGiaoDich.remove(pos);
                                adapter.notifyDataSetChanged();
                                setSoDu();
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