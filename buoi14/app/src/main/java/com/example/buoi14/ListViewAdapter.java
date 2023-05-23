package com.example.buoi14;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<GiaoDich> {
    public ListViewAdapter(Context context, List<GiaoDich> lstGiaoDich) {
        super(context, 0, lstGiaoDich);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent) {
        GiaoDich giaoDich = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent, false);
        }

        TextView tvTenNguoi = convertView.findViewById(R.id.tvTenNguoi);
        TextView tvNgayThang = convertView.findViewById(R.id.tvNgayThang);
        TextView tvNoiDung = convertView.findViewById(R.id.tvNoiDung);
        TextView tvSoTien = convertView.findViewById(R.id.tvSoTien);

        String loaiGiaoDich = giaoDich.isLoaiGiaoDich() == true ? "Tiền đến từ" : "Tiền đi tới";
        tvTenNguoi.setText(loaiGiaoDich + ": " + giaoDich.getTenNguoi());
        tvNgayThang.setText(giaoDich.getNgayThang());
        tvNoiDung.setText(giaoDich.getNoiDung());
        tvSoTien.setText(giaoDich.getSoTien());

        if(giaoDich.isLoaiGiaoDich() == true){
            convertView.findViewById(R.id.layout1).setBackgroundColor(Color.GRAY);
            convertView.findViewById(R.id.layout2).setBackgroundColor(Color.LTGRAY);
        }

        return convertView;

    }
}
