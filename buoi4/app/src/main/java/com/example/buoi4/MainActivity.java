package com.example.buoi4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lstViewSinhVien;
    ArrayList<SinhVien> arrSinhVien;
    Button btn;
    EditText inputName;
    EditText inputDate;
    Button btnDelete;
    SinhVienAdapter adapter;
    Button btnLogin;
    TextView textViewTest;
    int val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        arrSinhVien = new ArrayList<SinhVien>();
        arrSinhVien.add(new SinhVien("Nguyen Van A",21));
        arrSinhVien.add(new SinhVien("Nguyen Van B",19));
        arrSinhVien.add(new SinhVien("Nguyen Van C",22));


        btn = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.buttonDelete);
        textViewTest = (TextView) findViewById(R.id.textViewTest);
        adapter = new SinhVienAdapter(MainActivity.this,R.layout.activity_listview_item,arrSinhVien);
        lstViewSinhVien.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputName = (EditText) findViewById(R.id.inputName);
                inputDate = (EditText) findViewById(R.id.inputDate);
                String strName = inputName.getText().toString();
                String strDate = inputDate.getText().toString();
                if(strName.equals("") && strDate.equals("")){
                    arrSinhVien.add(new SinhVien(strName,Integer.parseInt(strDate)));
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // tao adapter
        // add vao adapter
        lstViewSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                val = i;
                Toast.makeText(MainActivity.this,arrSinhVien.get(i).Hoten + arrSinhVien.get(i).NamSinh.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xacnhanxoa();
            }
        });

        lstViewSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xacnhanxoa();
                return false;
            }
        });
        btnLogin = (Button) findViewById(R.id.Login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydialog1();
            }
        });

    }

    private void xacnhanxoa(){
        AlertDialog.Builder aler = new AlertDialog.Builder(this);
        aler.setTitle("Thong bao");
        aler.setIcon(R.mipmap.ic_launcher);
        aler.setMessage("Co xoa khong ?");
        aler.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrSinhVien.remove(val);
                adapter.notifyDataSetChanged();
            }
        });
        aler.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    public void mydialog1(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        EditText editTextName = (EditText) dialog.findViewById(R.id.editTextTextPersonName);
        EditText editTextPass = (EditText) dialog.findViewById(R.id.editTextTextPassword);
        Button btnLogin = (Button) dialog.findViewById(R.id.buttonOk);
        Button btnCancel = (Button) dialog.findViewById(R.id.button2);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextName.toString() == "Duc" && editTextPass.toString() == "123"){
                    dialog.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuGreed){
            textViewTest.setBackgroundColor(Color.GREEN);
        }else if(item.getItemId()==R.id.mnuRed){
            textViewTest.setBackgroundColor(Color.RED);
        }else{
            textViewTest.setBackgroundColor(Color.YELLOW);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initWidgets(){
        lstViewSinhVien = (ListView) findViewById(R.id.lstViewSinhVien);

    }
}