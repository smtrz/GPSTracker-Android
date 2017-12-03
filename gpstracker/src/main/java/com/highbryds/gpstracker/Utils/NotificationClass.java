package com.highbryds.gpstracker.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by tahir.raza on 11/30/2017.
 */

public class NotificationClass {
    private String CHANNEL_ID = "123";
    private String CHANNEL_NAME = "Your human readable notification channel name";
    private String CHANNEL_DESCRIPTION = "description";

    @RequiresApi(Build.VERSION_CODES.O)
    public void createMainNotificationChannel(Context c) {
        String id = CHANNEL_ID;
        String name = CHANNEL_NAME;
        String description = CHANNEL_DESCRIPTION;
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
        mChannel.setDescription (description);
        mChannel.enableLights(true);
        mChannel.setLightColor( Color.RED);
        mChannel.enableVibration(true);
        NotificationManager mNotificationManager =
                (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
       // NotificationManager mNotificationManager = c.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        mNotificationManager.createNotificationChannel(mChannel);
    }

    @RequiresApi(Build.VERSION_CODES.O)
   public String getMainNotificationId() {
        return CHANNEL_ID;
    }

}
