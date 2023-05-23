package com.example.buoi14;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SongsAdapter extends ArrayAdapter<Song> {


    public SongsAdapter(Context context, List<Song> lstSong) {
        super(context, 0, lstSong);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // lấy đối tượng Song tại vị trí position trong danh sách
        Song song = getItem(position);

        // kiểm tra view đã được khởi tạo trước đó hay chưa
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_listview, parent, false);
        }

        // lấy ra các TextView từ view hiện tại để hiển thị thông tin Song
        TextView tvTenBaiHat = convertView.findViewById(R.id.name);
        TextView tvThoiGian = convertView.findViewById(R.id.time);
        TextView tvCaSi = convertView.findViewById(R.id.author);

        // hiển thị thông tin của Song lên các TextView
        tvTenBaiHat.setText(song.getName());
        tvCaSi.setText(song.getSinger());
        tvThoiGian.setText(String.valueOf(song.getTime()));

        return convertView;
    }
}
