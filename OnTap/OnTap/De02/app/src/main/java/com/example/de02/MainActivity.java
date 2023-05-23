package com.example.de02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView lvThuChi;
    TextView tvSoDu, edtSearch;
    List<ThuChi> lstThuChi;
    ListViewAdapter adapter;
    MySQLHelper db =new MySQLHelper(this);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        db.insertData();
        lstThuChi=db.GetAll();
        Collections.sort(lstThuChi, new Comparator<ThuChi>() {
            @Override
            public int compare(ThuChi t1, ThuChi t2) {
                return t2.getSoTien() - t1.getSoTien();
            }
        });
        adapter = new ListViewAdapter(this, lstThuChi);
        lvThuChi.setAdapter(adapter);
        tvSoDu.setText(tinhSoDu().toString());

        // Register the BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    private void InitWidget(){
        lvThuChi = findViewById(R.id.lvThuChi);
        tvSoDu = findViewById(R.id.tvSoDu);
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

    Integer tinhSoDu()
    {
        int tongThu = 0, tongChi = 0;
        // tong tien den
        for(ThuChi giaoDich : lstThuChi)
        {
            if(giaoDich.isKhoanThuChi() == true)
            {
                tongThu += giaoDich.getSoTien();
            }
            else{
                tongChi += giaoDich.getSoTien();
            }
        }
        return tongThu - tongChi;
    }

    private void filterData(String searchText) {
        List<ThuChi> filteredList = new ArrayList<>();

        for (ThuChi thuChi : lstThuChi) {
            // Check if the search text is contained in the "getNoiDung" field
            if (thuChi.getNoiDung().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(thuChi);
            }
        }

        adapter = new ListViewAdapter(this, filteredList);
        lvThuChi.setAdapter(adapter);
    }
}