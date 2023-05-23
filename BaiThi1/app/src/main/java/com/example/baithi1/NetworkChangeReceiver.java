package com.example.baithi1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action != null && action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                // Network is connected
                Toast.makeText(context, "Sẵn sàng đồng bộ dữ liệu lên cloud", Toast.LENGTH_SHORT).show();
            } else {
                // Network is disconnected
                Toast.makeText(context, "Mất kết nối nên không thể đồng bộ dữ liệu lên cloud", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
