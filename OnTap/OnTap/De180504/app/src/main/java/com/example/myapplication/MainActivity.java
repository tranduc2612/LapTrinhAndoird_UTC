package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvSanPham;
    List<Sanpham> lstSanPham;
    ListViewAdapter adapter;
    Integer tient1, tient2;
    TextView edtSearch;
    MySQLHelper db = new MySQLHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        db.insertData();
        lstSanPham = db.GetAll();
        Collections.sort(lstSanPham, new Comparator<Sanpham>() {
            @Override
            public int compare(Sanpham t1, Sanpham t2) {
                tient1 = t1.getSoTien();
                tient2 = t2.getSoTien();
                if(t2.isGiamGia() == true){
                    tient2 = t2.getSoTien() * 9 / 10;
                }
                if(t1.isGiamGia() == true){
                    tient1 = t1.getSoTien() * 9 / 10;
                }
                return tient2 - tient1;
            }
        });

        adapter = new ListViewAdapter(this, lstSanPham);
        lvSanPham.setAdapter(adapter);

        lvSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showAvgDialog();
                return true;
            }
        });
    }

    private void InitWidget(){
        lvSanPham = findViewById(R.id.lvSanPham);
        edtSearch = findViewById(R.id.edtSearch);

        //Filter with search
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterData(editable.toString());
            }
        });
    }

    private void filterData(String searchText) {
        List<Sanpham> filteredList = new ArrayList<>();

        for (Sanpham sanPham : lstSanPham) {
            int giaTien = sanPham.getSoTien();
            if(sanPham.isGiamGia() == true){
                giaTien = giaTien * 9  /10;
            }
            if (giaTien < Integer.parseInt(searchText)) {
                filteredList.add(sanPham);
            }
        }

        adapter = new ListViewAdapter(this, filteredList);
        lvSanPham.setAdapter(adapter);
    }

    private void showAvgDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Giá Trung Bình");
        int giaTrungBinh = 0;
        int i = 0;
        List<Sanpham> filteredList = new ArrayList<>();
        for (Sanpham sanPham : lstSanPham) {
            int giaTien = sanPham.getSoTien();
            if(sanPham.isGiamGia() == true){
                giaTien = giaTien * 9  /10;
            }
            giaTrungBinh = giaTrungBinh + giaTien;
            i++;
        }
        giaTrungBinh = giaTrungBinh / i;
        String message = String.valueOf(giaTrungBinh);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }
}