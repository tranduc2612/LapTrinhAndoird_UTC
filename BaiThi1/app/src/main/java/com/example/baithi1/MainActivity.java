package com.example.baithi1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstView;
    FloatingActionButton btnAdd;
    TextView soDu;

    EditText inputSearch;
    List<ThuChi> lstThuChi;
    ListViewAdapter adapter;

    int pos;
    MySQLHelper db = new MySQLHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        db.insertData();
        lstThuChi = db.getAll();
        adapter = new ListViewAdapter(this,lstThuChi);
        lstView.setAdapter(adapter);

        soDu.setText(String.valueOf(TinhSoDu()));

        inputSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(inputSearch.getText().toString().isEmpty()){
                            adapter =new ListViewAdapter(MainActivity.this,lstThuChi);
                            lstView.setAdapter(adapter);
                            return;
                        }

                        String noiDung = inputSearch.getText().toString();
                        List<ThuChi> allGiaoDich = db.getAll();
                        List<ThuChi> newList = new ArrayList<>();
                        for (ThuChi thuChi : allGiaoDich)
                        {
                            if (thuChi.getTenKhoan().toLowerCase().contains(noiDung.toLowerCase()))
                        {
                            newList.add(thuChi);
                        }
                    }
                    adapter = new ListViewAdapter(MainActivity.this, newList);
                    lstView.setAdapter(adapter);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_add.class);
                startActivity(intent);
            }
        });
    }

    public int TinhSoDu(){
        int tongSoChi = 0;
        int tongSoThu = 0;
        for (ThuChi item : lstThuChi){
            if(item.isLoaiThuChi() == true){
                tongSoThu += Integer.parseInt(item.getSoTien());
            }else{
                tongSoChi += Integer.parseInt(item.getSoTien());
            }
        }

        return tongSoThu - tongSoChi;
    }

    private void initWidget() {
        lstView = (ListView) findViewById(R.id.lstView);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        inputSearch = (EditText) findViewById(R.id.inputsearch);
        soDu = (TextView) findViewById(R.id.tvSoDu);
    }

    NetworkChangeReceiver checkNetwork = new NetworkChangeReceiver();

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


}