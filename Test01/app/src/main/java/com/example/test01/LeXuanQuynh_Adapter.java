package com.example.test01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LeXuanQuynh_Adapter extends ArrayAdapter<Contact_Quynh> {
    public LeXuanQuynh_Adapter(Context context, List<Contact_Quynh> contacts)
    {
        super(context, 0, contacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact_Quynh contact_quynh = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent, false);
        }

        TextView tvID = convertView.findViewById(R.id.tvID);
        TextView tvHoTen = convertView.findViewById(R.id.tvHoTen);
        TextView tvSDT = convertView.findViewById(R.id.tvSDT);

        tvID.setText(String.valueOf(contact_quynh.getId()));
        tvHoTen.setText(contact_quynh.getTen());
        tvSDT.setText(contact_quynh.getSdt());

        return convertView;
    }
}
