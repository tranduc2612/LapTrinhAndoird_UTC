package com.example.myapplication;

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

public class ListViewAdapter extends ArrayAdapter<Sanpham> {
    public ListViewAdapter(Context context, List<Sanpham> lstSanPham)
    {
        super(context, 0, lstSanPham);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Sanpham sanPham = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent, false);
        }

        TextView tvNoiDung = convertView.findViewById(R.id.tvNoiDung);
        TextView tvSoTien = convertView.findViewById(R.id.tvSoTien);
        TextView tvGiamGia = convertView.findViewById(R.id.tvGiamGia);

        Integer tienGiamGia = sanPham.getSoTien() * 9 / 10;
        String giamGia = sanPham.isGiamGia() == true ? "Giảm giá còn: " + String.valueOf(tienGiamGia) : "";
        tvNoiDung.setText(sanPham.getNoiDung());
        tvSoTien.setText(String.valueOf(sanPham.getSoTien()));
        tvGiamGia.setText(giamGia);

        return convertView;
    }
}
