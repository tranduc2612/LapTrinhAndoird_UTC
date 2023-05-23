package com.example.de02;

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
    public ListViewAdapter(Context context, List<ThuChi> lstThuChi)
    {
        super(context, 0, lstThuChi);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ThuChi thuChi = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent, false);
        }

        TextView tvThuChi = convertView.findViewById(R.id.tvThuChi);
        TextView tvNgayThang = convertView.findViewById(R.id.tvNgayThang);
        TextView tvNoiDung = convertView.findViewById(R.id.tvNoiDung);
        TextView tvSoTien = convertView.findViewById(R.id.tvSoTien);

        String loaiThuChi = thuChi.isKhoanThuChi() == true ? "Thu" : "Chi";
        tvThuChi.setText(loaiThuChi);
        tvNgayThang.setText(thuChi.getNgayThang());
        tvNoiDung.setText(thuChi.getNoiDung());
        tvSoTien.setText(String.valueOf(thuChi.getSoTien()));

        if(thuChi.isKhoanThuChi() == true)
        {
            convertView.findViewById(R.id.LL1).setBackgroundColor(Color.DKGRAY);
            convertView.findViewById(R.id.LL2).setBackgroundColor(Color.LTGRAY);
        }

        return convertView;
    }
}
