package com.example.kinderfind.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.kinderfind.R;

public class InternetReceiver extends BroadcastReceiver {
    final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private ConnectivityManager cm;

    public InternetReceiver(ConnectivityManager cm){
        this.cm = cm;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String actionOfIntent = intent.getAction();
        boolean isConnected = checkForInternet();
        if(actionOfIntent.equals(CONNECTIVITY_ACTION)){
            if(isConnected){
                Log.d("Internet Receiver", "Internet detected");
            }else{
                Log.d("Internet Receiver", "No internet detected");
                Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean checkForInternet() {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
