package com.example.buoi4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {

    public SinhVienAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView; //v la giao dien
        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_listview_item,null);
        }
        SinhVien sv = getItem(position);
        if(sv!=null){
            // anh xa
            TextView tt1 = (TextView) v.findViewById(R.id.textView3);
            TextView tt2 = (TextView) v.findViewById(R.id.textView);
            // gan gia tri
            tt1.setText(sv.Hoten);
            tt2.setText(String.valueOf(sv.NamSinh));
        }
        return v;
    }
}
