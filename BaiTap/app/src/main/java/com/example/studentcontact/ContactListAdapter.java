package com.example.studentcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Contact> {

    public ContactListAdapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // lấy đối tượng Contact tại vị trí position trong danh sách
        Contact contact = getItem(position);

        // kiểm tra view đã được khởi tạo trước đó hay chưa
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_contact, parent, false);
        }

        // lấy ra các TextView từ view hiện tại để hiển thị thông tin Contact
        TextView tvSoBaoDanh = convertView.findViewById(R.id.tvSoBaoDanh);
        TextView tvHoTen = convertView.findViewById(R.id.tvHoTen);
        TextView tvDiemTong = convertView.findViewById(R.id.tvDiemTong);

        // hiển thị thông tin của Contact lên các TextView
        tvSoBaoDanh.setText(contact.getStudentID());
        tvHoTen.setText(contact.getName());
        tvDiemTong.setText(String.format("%.2f", contact.getMathScore() + contact.getPhysicsScore() + contact.getChemistryScore()));

        return convertView;
    }
}
