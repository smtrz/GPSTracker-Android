# GPSTracker-Android
This is an open source GPS Tracker developed using Google's Fused Location API - Compatible with Android O

This Library has been developed keeping in mind all the background limitation which are being added from Android O.

For Further information on Background Limitation you can follow the below link :
https://developer.android.com/about/versions/oreo/background.html

How to use this library :
------------------------
Step #1 :
--------

Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step #1 :
-------

Add the dependency
	dependencies {
	        compile 'com.github.smtrz:GPSTracker-Android:0.1.0'
	}

Follow the steps and then you would be able to get the benefit of using this library .

Step #3 :
--------
Add the following into your manifest :
Service tag :
<service android:name="com.highbryds.gpstracker.GPSService"></service>
Add Permissions:
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
 <uses-permission android:name="android.permission.INTERNET" />

Starting from Android M  - You have to take RunTime Permission from the user,make sure to take Runtime permissions before start GPSService from the library.

Step #4 :
--------
Create a class and extend it with Broadcast Reciever,this is where you can recieve the location updates then you can log the location based information into you local DB/ Shared Prefences or Remote DB.

public class MyReciver extends BroadcastReceiver {
@Override
    public void onReceive(Context context, Intent intent) {
        double latitude = Double.valueOf(intent.getStringExtra("latitude"));
        double longitude = Double.valueOf(intent.getStringExtra("longitude"));
        
        double speed = Double.valueOf(intent.getStringExtra("speed"));
        double altitude = Double.valueOf(intent.getStringExtra("altitude"));
        
    //Set it to some model class then maintain it in List saved in  sharedprefences - this will help you call the SendtoInternet Method
    //less frequently - i mean based on the number of list items you can take decision that once the list contains 5 items- send it to //the server - Its totally upto you.
   SendtoInternet(latitude,longitude,altitude,speed)
         
    
    }
    }
    
 

 



Step #5:
-------
Decalre the reciever in the manifest file :

<receiver android:name=".MyReciver">
            <intent-filter>
                <action android:name="com.highbryds.tracker" />
            </intent-filter>
        </receiver>
        
        


Now you are good to go...
Please let me know of any issues..
--
Tahir Raza
(smtrz@yahoo.com)
