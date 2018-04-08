package com.ex.conorreid.golfgps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    TextView mText1;
    TextView yText1;
    TextView mText2;
    TextView yText2;
    TextView mText3;
    TextView yText3;
    TextView mText4;
    TextView yText4;
    TextView statusText;
    LocationManager locationManager;
    LocationListener locationListener;
    boolean gpsPossible = false;
    boolean gpsPermission = false;
    double[] nwLat = new double[18];
    double[] nwLong = new double[18];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText1 = findViewById(R.id.metresText1);
        yText1 = findViewById(R.id.yardsText1);
        mText2 = findViewById(R.id.metresText2);
        yText2 = findViewById(R.id.yardsText2);
        mText3 = findViewById(R.id.metresText3);
        yText3 = findViewById(R.id.yardsText3);
        mText4 = findViewById(R.id.metresText4);
        yText4 = findViewById(R.id.yardsText4);
        statusText = findViewById(R.id.gpsStatusText);
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        gpsPossible = hasGPSDevice(this);

// Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                System.out.println("Status changed : " + status);
            }

            public void onProviderEnabled(String provider) {
                System.out.println("Provider: " + provider + "Enabled");
                statusText.setText("GPS Working");
                statusText.setTextColor(Color.parseColor("#00C853"));
            }

            public void onProviderDisabled(String provider) {
                System.out.println("Provider: " + provider + "Disabled");
                statusText.setText("GPS Disabled");
                statusText.setTextColor(Color.RED);
            }
        };

        if (!gpsPossible) {
            statusText.setText("No GPS Hardware Detected");
            statusText.setTextColor(Color.RED);
        } else {
            statusText.setText("GPS Hardware Detected");
            statusText.setTextColor(Color.parseColor("#FBC02D"));
//            checkGPSStatus();
            checkLocationPermission();

        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Disabled")
                        .setMessage("Enable Location?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            System.out.println("returning false");
            return false;

        } else {

            System.out.println("You already have permission");
            System.out.println("returning true, and starting requestUpdates");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 1.5f, locationListener);
            statusText.setText("GPS Working");
            statusText.setTextColor(Color.parseColor("#00C853"));
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        System.out.println("Permission granted after requestResult");
                        statusText.setText("Permission Granted after request!, beginning locationUpdates");
                        statusText.setTextColor(Color.GREEN);
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 1.5f, locationListener);
                        statusText.setText("GPS Working");
                        statusText.setTextColor(Color.parseColor("#00C853"));
                    }

                } else {

                    System.out.println("Requested permissions, and were denied");
                    statusText.setText("Permission Denied after request!");
                    statusText.setTextColor(Color.RED);
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }


    public void makeUseOfNewLocation(Location location) {

        System.out.println("Received new location!");
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        float[] distance1 = new float[1];
        float[] distance2 = new float[1];
        float[] distance3 = new float[1];
        float[] distance4 = new float[1];

        nwLat[1] = 55.111061;
        nwLong[1] = -7.470921;
        nwLat[2] = 55.109386;
        nwLong[2] = -7.473592;
        nwLat[3] = 55.108765;
        nwLong[3] = -7.474166;
        double houseLat = 55.121861;
        double houseLon = -7.454870;


        Location.distanceBetween(nwLat[1], nwLong[1], latitude, longitude, distance1);
        Location.distanceBetween(nwLat[2], nwLong[2], latitude, longitude, distance2);
        Location.distanceBetween(nwLat[3], nwLong[3], latitude, longitude, distance3);
        Location.distanceBetween(houseLat, houseLon, latitude, longitude, distance4);


        int roundedMetreFirst = (int) (distance1[0] + 0.5);
        int roundedMetreSecond = (int) (distance2[0] + 0.5);
        int roundedMetreThird = (int) (distance3[0] + 0.5);
        int roundedMetreHouse = (int) (distance4[0] + 0.5);

        System.out.println("Distance to first green is " + String.valueOf(distance1[0]));

        mText1.setText(String.valueOf(roundedMetreFirst) + "m");
        mText2.setText(String.valueOf(roundedMetreSecond) + "m");
        mText3.setText(String.valueOf(roundedMetreThird) + "m");
        mText4.setText(String.valueOf(roundedMetreHouse) + "m");

        float yardsFirst = distance1[0] * 1.093f;
        float yardsSecond = distance2[0] * 1.093f;
        float yardsThird = distance3[0] * 1.093f;
        float yardsHouse = distance4[0] * 1.093f;
        int roundedYardFirst = (int) (yardsFirst + 0.5);
        int roundedYardSecond = (int) (yardsSecond + 0.5);
        int roundedYardThird = (int) (yardsThird + 0.5);
        int roundedYardHouse = (int) (yardsHouse + 0.5);
        yText1.setText(String.valueOf(roundedYardFirst) + "y");
        yText2.setText(String.valueOf(roundedYardSecond) + "y");
        yText3.setText(String.valueOf(roundedYardThird) + "y");
        yText4.setText(String.valueOf(roundedYardHouse) + "y");

    }

//    public void checkGPSStatus() {
//        boolean gpsLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        gpsPermission = checkLocationPermission();
//
//        if (gpsPermission && gpsLocationEnabled) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, locationListener);
//            statusText.setText("GPS Working");
//            statusText.setTextColor(Color.parseColor("#00C853"));
//        } else if (gpsPermission && !gpsLocationEnabled) {
//            statusText.setText("Please Turn GPS On");
//            statusText.setTextColor(Color.parseColor("#E53935"));
//        } else if (!gpsPermission) {
//            statusText.setText("No GPS Permission");
//            statusText.setTextColor(Color.parseColor("#E53935"));
//        }
//    }

    public boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null) return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null) return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }


}
