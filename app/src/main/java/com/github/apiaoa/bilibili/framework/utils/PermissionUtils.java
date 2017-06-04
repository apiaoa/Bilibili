package com.github.apiaoa.bilibili.framework.utils;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by Sunny on 5/4/2017.
 */

public class PermissionUtils {

    /**
     * Is the location service opened or not
     * @param context
     * @return
     */
    public static boolean isOpenLocationService(Context context) {
        boolean isGps = false;
        boolean isNetwork = false;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (null != locationManager) {
            isGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }

        return isGps || isNetwork;
    }
}
