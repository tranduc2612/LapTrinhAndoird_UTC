package com.example.baithi1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<ThuChi> {
    public ListViewAdapter(Context context, List<ThuChi> lstThuChi) {
        super(context, 0, lstThuChi);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ThuChi thuChi = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent, false);
        }

        TextView tvTenKhoan = convertView.findViewById(R.id.tvTenKhoan);
        TextView tvNgayThang = convertView.findViewById(R.id.tvNgayThang);
        TextView tvSoTien = convertView.findViewById(R.id.tvSoTien);
        TextView tvLoaiThuChi = convertView.findViewById(R.id.tvLoaiThuChi);

        String loaiThuChi = thuChi.isLoaiThuChi() == true ? "Thu" : "Chi";

        tvTenKhoan.setText(thuChi.getTenKhoan());
        tvNgayThang.setText(thuChi.getNgayThang());
        tvSoTien.setText(thuChi.getSoTien());
        tvLoaiThuChi.setText(loaiThuChi);

        if(thuChi.isLoaiThuChi() == false){
            convertView.findViewById(R.id.layout1).setBackgroundColor(Color.GRAY);
            convertView.findViewById(R.id.layout2).setBackgroundColor(Color.LTGRAY);
        }

        return convertView;

    }

}
