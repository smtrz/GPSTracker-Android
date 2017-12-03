package com.highbryds.tracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.highbryds.gpstracker.GPSService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MyReciver m = new MyReciver();
        //  IntentFilter filter = new IntentFilter("com.yourcompany.testIntent");
        //registerReceiver(m, filter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(MainActivity.this, GPSService.class));
        } else {

            startService(new Intent(MainActivity.this, GPSService.class));
            Toast.makeText(MainActivity.this, "Tracking Started..", Toast.LENGTH_SHORT).show();
        }
    }

   /* public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            double latitude = Double.valueOf(intent.getStringExtra("latitude"));
            //  double longitude = Double.valueOf(intent.getStringExtra("longitude"));

            System.out.println("this is Lati: " + latitude);
            //  List<Address> addresses = null;

            *//*try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                String cityName = addresses.get(0).getAddressLine(0);
                String stateName = addresses.get(0).getAddressLine(1);
                String countryName = addresses.get(0).getAddressLine(2);

                tv_area.setText(addresses.get(0).getAdminArea());
                tv_locality.setText(stateName);
                tv_address.setText(countryName);



            } catch (IOException e1) {
                e1.printStackTrace();
            }
*//*

         *//*   tv_latitude.setText(latitude + "");
            tv_longitude.setText(longitude + "");
            tv_address.getText();*//*


        }
    };

*/
}
