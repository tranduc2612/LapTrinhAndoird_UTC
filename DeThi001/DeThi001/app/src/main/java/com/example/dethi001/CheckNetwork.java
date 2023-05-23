package com.example.dethi001;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckNetwork extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Kiểm tra trạng thái kết nối mạng
        if (isNetworkConnected(context)) {
            // Khi có kết nối mạng
            Toast.makeText(context, "Đã kết nối mạng", Toast.LENGTH_SHORT).show();
        } else {
            // Khi mất kết nối mạng
            Toast.makeText(context, "Mất kết nối mạng", Toast.LENGTH_SHORT).show();
        }
    }

    // Phương thức kiểm tra trạng thái kết nối mạng
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}