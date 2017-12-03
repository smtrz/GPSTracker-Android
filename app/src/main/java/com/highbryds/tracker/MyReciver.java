package com.highbryds.tracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by tahir.raza on 12/2/2017.
 */

public class MyReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        double latitude = Double.valueOf(intent.getStringExtra("latitude"));
        //speedspeedspeed
        double speed = Double.valueOf(intent.getStringExtra("speed"));
        double altitude = Double.valueOf(intent.getStringExtra("altitude"));


        System.out.println("broadcast latitude:" + latitude);
        System.out.println("broadcast speed:" + speed);
        System.out.println("broadcast altitude:" + altitude);


        //  double longitude = Double.valueOf(intent.getStringExtra("longitude"));
        //  Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    }
}
