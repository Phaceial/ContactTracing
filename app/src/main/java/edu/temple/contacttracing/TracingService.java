package edu.temple.contacttracing;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;


import static android.content.ContentValues.TAG;


public class TracingService extends Service {
    public static final String channelID = "Tracing Service";

    private LocationManager lm;
    private LocationListener ll;
    private double lon, lat;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public TracingService() {

    }

    private boolean checkPermission() {
        return (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        lm = getSystemService(LocationManager.class);
        Intent notificationIntent = new Intent(TracingService.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(TracingService.this, 0, notificationIntent, 0);

        NotificationManager nm = getSystemService(NotificationManager.class);
        NotificationChannel channel = new NotificationChannel(channelID, "Tracing Notifications", NotificationManager.IMPORTANCE_HIGH);
        nm.createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Contact Tracing")
                .setContentText("Currently Tracing location")
                .setContentIntent(pendingIntent)
                .build();

        startForeground(2679, notification);

        ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "lat updated");
                Log.d(TAG, "long updated");
            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if(checkPermission())
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lm.removeUpdates(ll);
    }

    public void stopThisService() {
        stopForeground(true);
    }
}
