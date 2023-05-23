package com.example.tranminhduc_201210096;

        import android.content.Context;
        import android.graphics.Color;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;

        import java.text.DecimalFormat;
        import java.text.NumberFormat;
        import java.util.List;

public class ListViewAdapter extends ArrayAdapter<BaiHat> {
    public ListViewAdapter(Context context, List<BaiHat> lstBaiHat)
    {
        super(context, 0, lstBaiHat);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BaiHat baihat = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, parent, false);
        }

        TextView tvBaiHat = convertView.findViewById(R.id.tvTenBaiHat);
        TextView tvCaSy = convertView.findViewById(R.id.tvCaSy);
        TextView tvLike = convertView.findViewById(R.id.tvLike);
        TextView tvShare = convertView.findViewById(R.id.tvShare);
        TextView tvDiem = convertView.findViewById(R.id.tvDiem);


        tvBaiHat.setText(baihat.getTenBai());
        tvCaSy.setText(baihat.getCaSy());
        tvLike.setText(String.valueOf(baihat.getLike()));
        tvShare.setText(String.valueOf(baihat.getShare()));

        int point = Integer.parseInt(String.valueOf(baihat.getLike())) + Integer.parseInt(String.valueOf(baihat.getShare()))*5;
        tvDiem.setText(String.valueOf(point));
        if (point > 160)
        {
            convertView.findViewById(R.id.layout1).setBackgroundColor(Color.GRAY);
            convertView.findViewById(R.id.layout2).setBackgroundColor(Color.LTGRAY);
        }
        return convertView;
    }
}
