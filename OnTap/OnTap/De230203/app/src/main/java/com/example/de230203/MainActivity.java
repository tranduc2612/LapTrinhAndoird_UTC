package com.example.de230203;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    TextView tvSoDu;
    List<GiaoDich> lstGiaoDich;
    ListViewAdapter adapter;
    MySQLHelper db = new MySQLHelper(this);
    int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        db.insertData();
        lstGiaoDich = db.GetAll();
        // sap xep tang dan theo thu tu ngay thang
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lstGiaoDich.sort((a, b) -> a.getNgayThang().compareTo(b.getNgayThang()));
        }
        adapter = new ListViewAdapter(this, lstGiaoDich);
        lvGiaoDich.setAdapter(adapter);

        //
        tvSoDu.setText(tinhSoDu().toString());
        //
        lvGiaoDich.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPosition = i;
                registerForContextMenu(lvGiaoDich);
                lvGiaoDich.showContextMenu();
                return true;
            }
        });
    }

    private void InitWidget(){
        lvGiaoDich = findViewById(R.id.lvGiaoDich);
        tvSoDu = findViewById(R.id.tvSoDu);
    }


    Integer tinhSoDu()
    {
        int tongTienDen = 0, tongTienDi = 0;
        // tong tien den
        for(GiaoDich giaoDich : lstGiaoDich)
        {
            if(giaoDich.isLoaiGiaoDich() == true)
            {
                tongTienDen += giaoDich.getSoTien();
            }
            else{
                tongTienDi += giaoDich.getSoTien();
            }
        }
        return tongTienDen - tongTienDi;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnXoa) {
            showConfirmationDialog(selectedPosition);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void showConfirmationDialog(int selectedIndex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        String selectedNgayThang = lstGiaoDich.get(selectedIndex).getNgayThang();
        String selectedTenNguoi = lstGiaoDich.get(selectedIndex).getTenNguoi();
        Integer selectedSoTien = lstGiaoDich.get(selectedIndex).getSoTien();
        String loaiGiaoDich = lstGiaoDich.get(selectedIndex).isLoaiGiaoDich() == true ? "Tiền đến từ" : "Tiền đi tới";
        builder.setMessage(
                "Bạn muốn xóa khoản giao dịch?" +
                "\nNgày tháng: " + selectedNgayThang +
                "\n" + loaiGiaoDich + ": " + selectedTenNguoi +
                "\nSố tiền: " + selectedSoTien);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteSelectedItem(selectedIndex);
            }
        });
        builder.create().show();
    }

    private void deleteSelectedItem(int selectedIndex) {
        GiaoDich selectedGiaoDich = lstGiaoDich.get(selectedIndex);

        db.delete(selectedGiaoDich.getMaGiaoDich());

        lstGiaoDich.remove(selectedIndex);

        // Update the adapter to reflect the changes
        adapter.notifyDataSetChanged();

        // Recalculate and update the total balance
        tvSoDu.setText(tinhSoDu().toString());
    }
}