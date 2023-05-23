package com.example.tuluyenbuoi2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstView;
    ArrayList<String> lstData;
    ArrayAdapter<String> adapter;
    Button btnAdd, btnRemove, btnUpdate;
    EditText input;
    int pos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.button);
        btnUpdate = (Button) findViewById(R.id.button2);
        input = (EditText) findViewById(R.id.input);
        lstView = (ListView) findViewById(R.id.listview);

        lstData = new ArrayList<>();

        lstData.add("Nội dung 1 !");
        lstData.add("Nội dung 2 !");
        lstData.add("Nội dung 3 !");
        lstData.add("Nội dung 4 !");

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,lstData);

        lstView.setAdapter(adapter);

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,lstData.get(i),Toast.LENGTH_SHORT).show();
                input.setText(lstData.get(i));
                pos = i;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = input.getText().toString().trim();
                if(value == ""){
                    Toast.makeText(MainActivity.this,"Bạn cần nhập trường này thì mới thêm được",Toast.LENGTH_SHORT).show();
                }else{
                    lstData.add(value);
                    adapter.notifyDataSetChanged();
                    input.setText("");
                }}
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newValue = input.getText().toString();
                lstData.set(pos,newValue);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Dữ liệu đã được cập nhật !",Toast.LENGTH_SHORT).show();
            }
        });

        lstView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int posDel = i;
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Xác nhận");
                dialog.setMessage("Bạn có đồng ý xóa không ?");
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        lstData.remove(posDel);
                        adapter.notifyDataSetChanged();
                    }
                });

                dialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
                return false;
            }
        });

    }
}