package com.github.rappi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.github.rappi.RappiApplication;


/**
 * Created by Roger Patiño on 15/06/2016.
 */
public class Networks {

    public static boolean isOnline(Context context) {
        Context ctx = context == null ? RappiApplication.getContext() : context;
        ConnectivityManager connMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}