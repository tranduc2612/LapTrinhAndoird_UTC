package com.example.tranminhduc_201210096;

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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvBaiHat;
    List<BaiHat> lstBaiHat;
    ListViewAdapter adapter;
    MySQLHelper db = new MySQLHelper(this);
    TextView tvTrungBinh;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        db.insertData();
        lstBaiHat = db.getAll();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lstBaiHat.sort((a, b) -> a.getTenBai().compareTo(b.getTenBai()));
        }
        adapter = new ListViewAdapter(this, lstBaiHat);
        lvBaiHat.setAdapter(adapter);

        lvBaiHat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                registerForContextMenu(lvBaiHat);
                lvBaiHat.showContextMenu();
                return true;
            }
        });
    }

    private void initWidget() {
        lvBaiHat = findViewById(R.id.listview);
        tvTrungBinh = findViewById(R.id.tvTrungBinh);
        tvTrungBinh.setText("206.5");
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
            showConfirmationDialog(pos);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void showConfirmationDialog(int selectedIndex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        String selectedBaiHat = lstBaiHat.get(selectedIndex).getTenBai();
        String selectedCaSy = lstBaiHat.get(selectedIndex).getCaSy();
        builder.setMessage(
                "Bạn muốn xóa bài hát?" +
                        selectedBaiHat +
                        "\nCa sỹ: " + selectedCaSy);
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
        BaiHat selectedGiaoDich = lstBaiHat.get(selectedIndex);

        db.delete(selectedGiaoDich.getId());

        lstBaiHat.remove(selectedIndex);

        // Update the adapter to reflect the changes
        adapter.notifyDataSetChanged();

    }
}