package com.highbryds.gpstracker;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
//import com.google.gson.reflect.TypeToken;

import com.highbryds.gpstracker.Model.TrackMe_Location;
import com.highbryds.gpstracker.Utils.NotificationClass;


import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by tahir.raza on 11/23/2017.
 */

public class GPSService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    // Binder given to clients
    // Registered callbacks
    private static final String LOGSERVICE = "#######";
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    List<TrackMe_Location> TrackLoc;
    Context c = this;
    NotificationClass nc = new NotificationClass();
    private static int PacketSize = 2;
    private static int LocationInterval = 20000;
    private static int LocationFastestInterval = 15000;
    /// private static final int drawableIcon = R.drawable.cast_ic_notification_small_icon;
    private static PendingIntent contentIntent;
    private static String NotificationTxt = "";
    private static String NotificationTitle = "";
    static int d = R.drawable.ic_check;
    IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        // building Google Api Client at the startup
        buildGoogleApiClient();

        //calling and building notification for ANDROID O within 5 seconds of the Service Startup.
        ForegroundServiceInitialize();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mGoogleApiClient.isConnected())
            mGoogleApiClient.connect();

        return START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        //Inntizlizing ArrayList
//        TrackLoc = new ArrayList < > ();
        // We already have taken the permission from the user so this is redundant here...
        @SuppressLint("MissingPermission") Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        startLocationUpdate();

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection Has been Suspended..", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(this, connectionResult.getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        try {

            Intent broadcastIntent = new Intent("com.yourcompany.testIntent");
            //  broadcastIntent.setAction(MainActivity.mBroadcastStringAction);
            broadcastIntent.putExtra("latitude", String.valueOf(location.getLatitude()));
            broadcastIntent.putExtra("longitude", String.valueOf(location.getLongitude()));
            broadcastIntent.putExtra("speed", String.valueOf(location.getSpeed()));
            broadcastIntent.putExtra("time", String.valueOf(location.getTime()));
            broadcastIntent.putExtra("altitude", String.valueOf(location.getAltitude()));

            sendBroadcast(broadcastIntent);
        } catch (NullPointerException e) {
            Toast.makeText(GPSService.this, "Location has been disabled...", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    private void initLocationRequest() {
        //these are the parameters defined.
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(LocationInterval);
        mLocationRequest.setFastestInterval(LocationFastestInterval);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdate() {
        //Getting all the paramters.
        initLocationRequest();
        //Requesting Location Updates..
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }


    private void stopLocationUpdate() {
        //TO Remove Location Udpates
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

    public void ForegroundServiceInitialize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Creating Channel
            nc.createMainNotificationChannel(this);
            //building Notification.
            Notification.Builder notifi = new Notification.Builder(getApplicationContext(), nc.getMainNotificationId());
            notifi.setSmallIcon(d);
            notifi.setContentTitle(NotificationTitle);
            notifi.setContentText(NotificationTxt);
            notifi.setContentIntent(contentIntent);
            //getting notification object from notification builder.
            Notification n = notifi.build();

            NotificationManager mNotificationManager =
                    (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);

            //  startting foreground
            startForeground(1, n);


        } else {
            //for devices less than API Level 26
            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle(NotificationTitle)
                    .setContentText(NotificationTxt)
                    .setSmallIcon(R.drawable.ic_check)
                    .setContentIntent(contentIntent)
                    .setOngoing(true).build();
            startForeground(1, notification);
        }


    }

    public Location calling_method(Location l) {
        //    System.out.println("##############");

        return l;
    }

    public class LocalBinder extends Binder {
        public GPSService getServerInstance() {
            return GPSService.this;
        }
    }
}