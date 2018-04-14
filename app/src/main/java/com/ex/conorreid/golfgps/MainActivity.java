package com.ex.conorreid.golfgps;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    TextView txtJson;
    ProgressDialog pd;
    ImageView logo;
    ImageView arrow;
    TextView num1;
    TextView num2;
    TextView num3;
    TextView num4;
    TextView num5;
    TextView num6;
    TextView num7;
    TextView num8;
    TextView num9;
    TextView num10;
    TextView num11;
    TextView num12;
    TextView num13;
    TextView num14;
    TextView num15;
    TextView num16;
    TextView num17;
    TextView num18;

    TextView turnOnText;

    TextView latText;
    TextView lonText;

    TextView noInternet;

    TextView dText1;
    TextView dText2;
    TextView dText3;
    TextView dText4;
    TextView dText5;
    TextView dText6;
    TextView dText7;
    TextView dText8;
    TextView dText9;
    TextView dText10;
    TextView dText11;
    TextView dText12;
    TextView dText13;
    TextView dText14;
    TextView dText15;
    TextView dText16;
    TextView dText17;
    TextView dText18;

    TextView b1;
    TextView f1;
    TextView b2;
    TextView f2;
    TextView b3;
    TextView f3;
    TextView b4;
    TextView f4;
    TextView b5;
    TextView f5;
    TextView b6;
    TextView f6;
    TextView b7;
    TextView f7;
    TextView b8;
    TextView f8;
    TextView b9;
    TextView f9;
    TextView b10;
    TextView f10;
    TextView b11;
    TextView f11;
    TextView b12;
    TextView f12;
    TextView b13;
    TextView f13;
    TextView b14;
    TextView f14;
    TextView b15;
    TextView f15;
    TextView b16;
    TextView f16;
    TextView b17;
    TextView f17;
    TextView b18;
    TextView f18;
    TextView statusText;

    TextView northLetter;
    TextView windSpeedText;
    TextView windDirText;

    LocationManager locationManager;
    LocationListener locationListener;

    boolean gpsPossible = false;
    //    boolean gpsPermission = false;
    boolean gpsOn = false;

    double[] nwLat = new double[19];
    double[] nwLong = new double[19];

    double[] bcLat = new double[19];
    double[] bcLong = new double[19];

    double[] bloLat = new double[19];
    double[] bloLon = new double[19];
    double[] bloBLat = new double[19];
    double[] bloBLon = new double[19];
    double[] bloFLat = new double[19];
    double[] bloFLon = new double[19];


    double[] blgLat = new double[19];
    double[] blgLon = new double[19];

    double[] psLat = new double[19];
    double[] psLon = new double[19];

    double[] balLat = new double[19];
    double[] balLon = new double[19];

    int selectedClub = 0;

    String weatherLink = "http://api.openweathermap.org/data/2.5/weather?id=2654332&APPID=d2f1c7fd747498a9246f9467457b722e";

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dText1 = findViewById(R.id.distText1);
        dText2 = findViewById(R.id.distText2);
        dText3 = findViewById(R.id.distText3);
        dText4 = findViewById(R.id.distText4);
        dText5 = findViewById(R.id.distText5);
        dText6 = findViewById(R.id.distText6);
        dText7 = findViewById(R.id.distText7);
        dText8 = findViewById(R.id.distText8);
        dText9 = findViewById(R.id.distText9);
        dText10 = findViewById(R.id.distText10);
        dText11 = findViewById(R.id.distText11);
        dText12 = findViewById(R.id.distText12);
        dText13 = findViewById(R.id.distText13);
        dText14 = findViewById(R.id.distText14);
        dText15 = findViewById(R.id.distText15);
        dText16 = findViewById(R.id.distText16);
        dText17 = findViewById(R.id.distText17);
        dText18 = findViewById(R.id.distText18);

        b1 = findViewById(R.id.back1);
        f1 = findViewById(R.id.front1);
        b2 = findViewById(R.id.back2);
        f2 = findViewById(R.id.front2);
        b3 = findViewById(R.id.back3);
        f3 = findViewById(R.id.front3);
        b4 = findViewById(R.id.back4);
        f4 = findViewById(R.id.front4);
        b5 = findViewById(R.id.back5);
        f5 = findViewById(R.id.front5);
        b6 = findViewById(R.id.back6);
        f6 = findViewById(R.id.front6);
        b7 = findViewById(R.id.back7);
        f7 = findViewById(R.id.front7);
        b8 = findViewById(R.id.back8);
        f8 = findViewById(R.id.front8);
        b9 = findViewById(R.id.back9);
        f9 = findViewById(R.id.front9);
        b10 = findViewById(R.id.back10);
        f10 = findViewById(R.id.front10);
        b11 = findViewById(R.id.back11);
        f11 = findViewById(R.id.front11);
        b12 = findViewById(R.id.back12);
        f12 = findViewById(R.id.front12);
        b13 = findViewById(R.id.back13);
        f13 = findViewById(R.id.front13);
        b14 = findViewById(R.id.back14);
        f14 = findViewById(R.id.front14);
        b15 = findViewById(R.id.back15);
        f15 = findViewById(R.id.front15);
        b16 = findViewById(R.id.back16);
        f16 = findViewById(R.id.front16);
        b17 = findViewById(R.id.back17);
        f17 = findViewById(R.id.front17);
        b18 = findViewById(R.id.back18);
        f18 = findViewById(R.id.front18);

        statusText = findViewById(R.id.gpsStatusText);

        windSpeedText = findViewById(R.id.speedText);
        windDirText = findViewById(R.id.dirText);

        latText = findViewById(R.id.textView55);
        lonText = findViewById(R.id.textView56);

        turnOnText = findViewById(R.id.turnText);

        noInternet = findViewById(R.id.noInternetText);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        num10 = findViewById(R.id.num10);
        num11 = findViewById(R.id.num11);
        num12 = findViewById(R.id.num12);
        num13 = findViewById(R.id.num13);
        num14 = findViewById(R.id.num14);
        num15 = findViewById(R.id.num15);
        num16 = findViewById(R.id.num16);
        num17 = findViewById(R.id.num17);
        num18 = findViewById(R.id.num18);

        northLetter = findViewById(R.id.nText);
        logo = findViewById(R.id.logoView);
        arrow = findViewById(R.id.arrowImage);

        populateCoordinateArrays();
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        gpsPossible = hasGPSDevice(this);

        //Populate spinner
        Spinner spinner = findViewById(R.id.clubSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.golf_clubs, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                setTextToZero();
                selectedClub = position;
                System.out.println("Spinner selected at position: " + position);

                if (selectedClub == 0) {

                    logo.setImageResource(R.drawable.north_west_logo);

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                } else if (selectedClub == 1) {

                    logo.setImageResource(R.drawable.buncrana_gc_logo);

                    if (gpsOn) {
                        hideFront9(false);
                        hideBack9(true);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }
                } else if (selectedClub == 2) {

                    logo.setImageResource(R.drawable.bl_logo);

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                } else if (selectedClub == 3) {
                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);
                    }
                } else if (selectedClub == 4) {

                    logo.setImageResource(R.drawable.ps_logo);

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                } else if (selectedClub == 5) {

                    logo.setImageResource(R.drawable.bal_logo);

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                } else {
                    System.out.println("Error with spinner selection");
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        new JsonTask().execute(weatherLink);
//        btnHit = (Button) findViewById(R.id.btnHit);
        txtJson = findViewById(R.id.tempText);
//
//        btnHit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new JsonTask().execute("http://api.openweathermap.org/data/2.5/weather?id=2654332&APPID=d2f1c7fd747498a9246f9467457b722e");
//            }
//        });


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
                gpsOn = true;
                System.out.println("Provider: " + provider + "Enabled");
                statusText.setText("GPS Active");
                statusText.setTextColor(Color.parseColor("#00C853"));
                hideFront9(false);
                turnOnText.setVisibility(View.INVISIBLE);
                if (selectedClub == 1) {
                    hideBack9(true);
                } else {
                    hideBack9(false);
                }
            }

            public void onProviderDisabled(String provider) {
                gpsOn = false;
                System.out.println("Provider: " + provider + "Disabled");
                statusText.setText("GPS Off");
                statusText.setTextColor(Color.RED);
                hideFront9(true);
                hideBack9(true);
                turnOnText.setVisibility(View.VISIBLE);
            }
        };

        setTextToZero();

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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 1, locationListener);
            statusText.setText("GPS Working");
            gpsOn = true;
            hideFront9(false);
            hideBack9(false);
            turnOnText.setVisibility(View.INVISIBLE);
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
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 1, locationListener);
                        statusText.setText("GPS Working");
                        gpsOn = true;
                        statusText.setText("GPS Active");
                        statusText.setTextColor(Color.parseColor("#00C853"));
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);
                        if (selectedClub == 1) {
                            hideBack9(true);
                        } else {
                            hideBack9(false);
                        }
                    }

                } else {

                    System.out.println("Requested permissions, and were denied");
                    statusText.setText("Permission Denied after request!");
                    statusText.setTextColor(Color.RED);
                    hideFront9(true);
                    hideBack9(true);
                    turnOnText.setVisibility(View.VISIBLE);
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
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

    public void makeUseOfNewLocation(Location location) {

        System.out.println("Received new location!");
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        System.out.println(String.valueOf("Lat: " + latitude));
        System.out.println(String.valueOf("Lon: " + longitude));
//        latText.setText(String.valueOf(latitude));
//        lonText.setText(String.valueOf(longitude));

        if (selectedClub == 0) {
            setUpYardage(nwLat, nwLong, location);
        } else if (selectedClub == 1) {
            setUpYardage(bcLat, bcLong, location);
        } else if (selectedClub == 2) {
            setUpYardage(bloLat, bloLon, location);
            setUpBackFrontYardage(bloBLat, bloBLon, bloFLat, bloFLon, location);
        } else if (selectedClub == 3) {
            setUpYardage(blgLat, blgLon, location);
        } else if (selectedClub == 4) {
            setUpYardage(psLat, psLon, location);
        } else {
            System.out.println("Error with club selection and yardage calculation");
        }


    }

    public boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null) return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null) return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }

    public void hideBack9(boolean bool) {

        float choice;
        if (bool) {
            choice = 0.0f;
        } else {
            choice = 1.0f;
        }
        dText10.setAlpha(choice);
        dText11.setAlpha(choice);
        dText12.setAlpha(choice);
        dText13.setAlpha(choice);
        dText14.setAlpha(choice);
        dText15.setAlpha(choice);
        dText16.setAlpha(choice);
        dText17.setAlpha(choice);
        dText18.setAlpha(choice);

        b10.setAlpha(choice);
        f10.setAlpha(choice);
        b11.setAlpha(choice);
        f11.setAlpha(choice);
        b12.setAlpha(choice);
        f12.setAlpha(choice);
        b13.setAlpha(choice);
        f13.setAlpha(choice);
        b14.setAlpha(choice);
        f14.setAlpha(choice);
        b15.setAlpha(choice);
        f15.setAlpha(choice);
        b16.setAlpha(choice);
        f16.setAlpha(choice);
        b17.setAlpha(choice);
        f17.setAlpha(choice);
        b18.setAlpha(choice);
        f18.setAlpha(choice);

        num10.setAlpha(choice);
        num11.setAlpha(choice);
        num12.setAlpha(choice);
        num13.setAlpha(choice);
        num14.setAlpha(choice);
        num15.setAlpha(choice);
        num16.setAlpha(choice);
        num17.setAlpha(choice);
        num18.setAlpha(choice);
    }

    public void hideFront9(boolean bool) {

        float choice;
        if (bool) {
            choice = 0.0f;
        } else {
            choice = 1.0f;
        }
        dText1.setAlpha(choice);
        dText2.setAlpha(choice);
        dText3.setAlpha(choice);
        dText4.setAlpha(choice);
        dText5.setAlpha(choice);
        dText6.setAlpha(choice);
        dText7.setAlpha(choice);
        dText8.setAlpha(choice);
        dText9.setAlpha(choice);

        b1.setAlpha(choice);
        f1.setAlpha(choice);
        b2.setAlpha(choice);
        f2.setAlpha(choice);
        b3.setAlpha(choice);
        f3.setAlpha(choice);
        b4.setAlpha(choice);
        f4.setAlpha(choice);
        b5.setAlpha(choice);
        f5.setAlpha(choice);
        b6.setAlpha(choice);
        f6.setAlpha(choice);
        b7.setAlpha(choice);
        f7.setAlpha(choice);
        b8.setAlpha(choice);
        f8.setAlpha(choice);
        b9.setAlpha(choice);
        f9.setAlpha(choice);

        num1.setAlpha(choice);
        num2.setAlpha(choice);
        num3.setAlpha(choice);
        num4.setAlpha(choice);
        num5.setAlpha(choice);
        num6.setAlpha(choice);
        num7.setAlpha(choice);
        num8.setAlpha(choice);
        num9.setAlpha(choice);
    }

    public void setUpYardage(double[] arrayLat, double[] arrayLon, Location location) {


        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        float[] distance1 = new float[1];
        float[] distance2 = new float[1];
        float[] distance3 = new float[1];
        float[] distance4 = new float[1];
        float[] distance5 = new float[1];
        float[] distance6 = new float[1];
        float[] distance7 = new float[1];
        float[] distance8 = new float[1];
        float[] distance9 = new float[1];
        float[] distance10 = new float[1];
        float[] distance11 = new float[1];
        float[] distance12 = new float[1];
        float[] distance13 = new float[1];
        float[] distance14 = new float[1];
        float[] distance15 = new float[1];
        float[] distance16 = new float[1];
        float[] distance17 = new float[1];
        float[] distance18 = new float[1];

        Location.distanceBetween(arrayLat[1], arrayLon[1], latitude, longitude, distance1);
        Location.distanceBetween(arrayLat[2], arrayLon[2], latitude, longitude, distance2);
        Location.distanceBetween(arrayLat[3], arrayLon[3], latitude, longitude, distance3);
        Location.distanceBetween(arrayLat[4], arrayLon[4], latitude, longitude, distance4);
        Location.distanceBetween(arrayLat[5], arrayLon[5], latitude, longitude, distance5);
        Location.distanceBetween(arrayLat[6], arrayLon[6], latitude, longitude, distance6);
        Location.distanceBetween(arrayLat[7], arrayLon[7], latitude, longitude, distance7);
        Location.distanceBetween(arrayLat[8], arrayLon[8], latitude, longitude, distance8);
        Location.distanceBetween(arrayLat[9], arrayLon[9], latitude, longitude, distance9);
        Location.distanceBetween(arrayLat[10], arrayLon[10], latitude, longitude, distance10);
        Location.distanceBetween(arrayLat[11], arrayLon[11], latitude, longitude, distance11);
        Location.distanceBetween(arrayLat[12], arrayLon[12], latitude, longitude, distance12);
        Location.distanceBetween(arrayLat[13], arrayLon[13], latitude, longitude, distance13);
        Location.distanceBetween(arrayLat[14], arrayLon[14], latitude, longitude, distance14);
        Location.distanceBetween(arrayLat[15], arrayLon[15], latitude, longitude, distance15);
        Location.distanceBetween(arrayLat[16], arrayLon[16], latitude, longitude, distance16);
        Location.distanceBetween(arrayLat[17], arrayLon[17], latitude, longitude, distance17);
        Location.distanceBetween(arrayLat[18], arrayLon[18], latitude, longitude, distance18);

//        int roundedMetreFirst = (int) (distance1[0] + 0.5);
//        int roundedMetreSecond = (int) (distance2[0] + 0.5);
//        int roundedMetreThird = (int) (distance3[0] + 0.5);
//        int roundedMetreFourth = (int) (distance4[0] + 0.5);
//        int roundedMetreFifth = (int) (distance5[0] + 0.5);
//        int roundedMetreSixth = (int) (distance6[0] + 0.5);
//        int roundedMetre7 = (int) (distance7[0] + 0.5);
//        int roundedMetre8 = (int) (distance8[0] + 0.5);
//        int roundedMetre9 = (int) (distance9[0] + 0.5);
//        int roundedMetre10 = (int) (distance10[0] + 0.5);
//        int roundedMetre11 = (int) (distance11[0] + 0.5);
//        int roundedMetre12 = (int) (distance12[0] + 0.5);
//        int roundedMetre13 = (int) (distance13[0] + 0.5);
//        int roundedMetre14 = (int) (distance14[0] + 0.5);
//        int roundedMetre15 = (int) (distance15[0] + 0.5);
//        int roundedMetre16 = (int) (distance16[0] + 0.5);
//        int roundedMetre17 = (int) (distance17[0] + 0.5);
//        int roundedMetre18 = (int) (distance18[0] + 0.5);


        System.out.println("Distance to first green is " + String.valueOf(distance1[0]));

        float yards1 = distance1[0] * 1.093f;
        float yards2 = distance2[0] * 1.093f;
        float yards3 = distance3[0] * 1.093f;
        float yards4 = distance4[0] * 1.093f;
        float yards5 = distance5[0] * 1.093f;
        float yards6 = distance6[0] * 1.093f;
        float yards7 = distance7[0] * 1.093f;
        float yards8 = distance8[0] * 1.093f;
        float yards9 = distance9[0] * 1.093f;
        float yards10 = distance10[0] * 1.093f;
        float yards11 = distance11[0] * 1.093f;
        float yards12 = distance12[0] * 1.093f;
        float yards13 = distance13[0] * 1.093f;
        float yards14 = distance14[0] * 1.093f;
        float yards15 = distance15[0] * 1.093f;
        float yards16 = distance16[0] * 1.093f;
        float yards17 = distance17[0] * 1.093f;
        float yards18 = distance18[0] * 1.093f;

        int roundedYard1 = (int) (yards1 + 0.5);
        int roundedYard2 = (int) (yards2 + 0.5);
        int roundedYard3 = (int) (yards3 + 0.5);
        int roundedYard4 = (int) (yards4 + 0.5);
        int roundedYard5 = (int) (yards5 + 0.5);
        int roundedYard6 = (int) (yards6 + 0.5);
        int roundedYard7 = (int) (yards7 + 0.5);
        int roundedYard8 = (int) (yards8 + 0.5);
        int roundedYard9 = (int) (yards9 + 0.5);
        int roundedYard10 = (int) (yards10 + 0.5);
        int roundedYard11 = (int) (yards11 + 0.5);
        int roundedYard12 = (int) (yards12 + 0.5);
        int roundedYard13 = (int) (yards13 + 0.5);
        int roundedYard14 = (int) (yards14 + 0.5);
        int roundedYard15 = (int) (yards15 + 0.5);
        int roundedYard16 = (int) (yards16 + 0.5);
        int roundedYard17 = (int) (yards17 + 0.5);
        int roundedYard18 = (int) (yards18 + 0.5);

//        dText1.setText(String.valueOf(roundedMetreFirst) + "m   " + String.valueOf(roundedYard1) + "y");
//        dText2.setText(String.valueOf(roundedMetreSecond) + "m   " + String.valueOf(roundedYard2) + "y");
//        dText3.setText(String.valueOf(roundedMetreThird) + "m   " + String.valueOf(roundedYard3) + "y");
//        dText4.setText(String.valueOf(roundedMetreFourth) + "m   " + String.valueOf(roundedYard4) + "y");
//        dText5.setText(String.valueOf(roundedMetreFifth) + "m   " + String.valueOf(roundedYard5) + "y");
//        dText6.setText(String.valueOf(roundedMetreSixth) + "m   " + String.valueOf(roundedYard6) + "y");
//        dText7.setText(String.valueOf(roundedMetre7) + "m   " + String.valueOf(roundedYard7) + "y");
//        dText8.setText(String.valueOf(roundedMetre8) + "m   " + String.valueOf(roundedYard8) + "y");
//        dText9.setText(String.valueOf(roundedMetre9) + "m   " + String.valueOf(roundedYard9) + "y");
//        dText10.setText(String.valueOf(roundedMetre10) + "m   " + String.valueOf(roundedYard10) + "y");
//        dText11.setText(String.valueOf(roundedMetre11) + "m   " + String.valueOf(roundedYard11) + "y");
//        dText12.setText(String.valueOf(roundedMetre12) + "m   " + String.valueOf(roundedYard12) + "y");
//        dText13.setText(String.valueOf(roundedMetre13) + "m   " + String.valueOf(roundedYard13) + "y");
//        dText14.setText(String.valueOf(roundedMetre14) + "m   " + String.valueOf(roundedYard14) + "y");
//        dText15.setText(String.valueOf(roundedMetre15) + "m   " + String.valueOf(roundedYard15) + "y");
//        dText16.setText(String.valueOf(roundedMetre16) + "m   " + String.valueOf(roundedYard16) + "y");
//        dText17.setText(String.valueOf(roundedMetre17) + "m   " + String.valueOf(roundedYard17) + "y");
//        dText18.setText(String.valueOf(roundedMetre18) + "m   " + String.valueOf(roundedYard18) + "y");

        dText1.setText(String.valueOf(roundedYard1) + "y");
        dText2.setText(String.valueOf(roundedYard2) + "y");
        dText3.setText(String.valueOf(roundedYard3) + "y");
        dText4.setText(String.valueOf(roundedYard4) + "y");
        dText5.setText(String.valueOf(roundedYard5) + "y");
        dText6.setText(String.valueOf(roundedYard6) + "y");
        dText7.setText(String.valueOf(roundedYard7) + "y");
        dText8.setText(String.valueOf(roundedYard8) + "y");
        dText9.setText(String.valueOf(roundedYard9) + "y");
        dText10.setText(String.valueOf(roundedYard10) + "y");
        dText11.setText(String.valueOf(roundedYard11) + "y");
        dText12.setText(String.valueOf(roundedYard12) + "y");
        dText13.setText(String.valueOf(roundedYard13) + "y");
        dText14.setText(String.valueOf(roundedYard14) + "y");
        dText15.setText(String.valueOf(roundedYard15) + "y");
        dText16.setText(String.valueOf(roundedYard16) + "y");
        dText17.setText(String.valueOf(roundedYard17) + "y");
        dText18.setText(String.valueOf(roundedYard18) + "y");



    }

    public void setUpBackFrontYardage(double[] backLat, double[] backLon, double[] frontLat, double []frontLon, Location location){

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        float[] Bdistance1 = new float[1];
        float[] Bdistance2 = new float[1];
        float[] Bdistance3 = new float[1];
        float[] Bdistance4 = new float[1];
        float[] Bdistance5 = new float[1];
        float[] Bdistance6 = new float[1];
        float[] Bdistance7 = new float[1];
        float[] Bdistance8 = new float[1];
        float[] Bdistance9 = new float[1];
        float[] Bdistance10 = new float[1];
        float[] Bdistance11 = new float[1];
        float[] Bdistance12 = new float[1];
        float[] Bdistance13 = new float[1];
        float[] Bdistance14 = new float[1];
        float[] Bdistance15 = new float[1];
        float[] Bdistance16 = new float[1];
        float[] Bdistance17 = new float[1];
        float[] Bdistance18 = new float[1];

        float[] Fdistance1 = new float[1];
        float[] Fdistance2 = new float[1];
        float[] Fdistance3 = new float[1];
        float[] Fdistance4 = new float[1];
        float[] Fdistance5 = new float[1];
        float[] Fdistance6 = new float[1];
        float[] Fdistance7 = new float[1];
        float[] Fdistance8 = new float[1];
        float[] Fdistance9 = new float[1];
        float[] Fdistance10 = new float[1];
        float[] Fdistance11 = new float[1];
        float[] Fdistance12 = new float[1];
        float[] Fdistance13 = new float[1];
        float[] Fdistance14 = new float[1];
        float[] Fdistance15 = new float[1];
        float[] Fdistance16 = new float[1];
        float[] Fdistance17 = new float[1];
        float[] Fdistance18 = new float[1];

        Location.distanceBetween(backLat[1], backLon[1], latitude, longitude, Bdistance1);
        Location.distanceBetween(backLat[2], backLon[2], latitude, longitude, Bdistance2);
        Location.distanceBetween(backLat[3], backLon[3], latitude, longitude, Bdistance3);
        Location.distanceBetween(backLat[4], backLon[4], latitude, longitude, Bdistance4);
        Location.distanceBetween(backLat[5], backLon[5], latitude, longitude, Bdistance5);
        Location.distanceBetween(backLat[6], backLon[6], latitude, longitude, Bdistance6);
        Location.distanceBetween(backLat[7], backLon[7], latitude, longitude, Bdistance7);
        Location.distanceBetween(backLat[8], backLon[8], latitude, longitude, Bdistance8);
        Location.distanceBetween(backLat[9], backLon[9], latitude, longitude, Bdistance9);
        Location.distanceBetween(backLat[10], backLon[10], latitude, longitude, Bdistance10);
        Location.distanceBetween(backLat[11], backLon[11], latitude, longitude, Bdistance11);
        Location.distanceBetween(backLat[12], backLon[12], latitude, longitude, Bdistance12);
        Location.distanceBetween(backLat[13], backLon[13], latitude, longitude, Bdistance13);
        Location.distanceBetween(backLat[14], backLon[14], latitude, longitude, Bdistance14);
        Location.distanceBetween(backLat[15], backLon[15], latitude, longitude, Bdistance15);
        Location.distanceBetween(backLat[16], backLon[16], latitude, longitude, Bdistance16);
        Location.distanceBetween(backLat[17], backLon[17], latitude, longitude, Bdistance17);
        Location.distanceBetween(backLat[18], backLon[18], latitude, longitude, Bdistance18);

        Location.distanceBetween(frontLat[1], frontLon[1], latitude, longitude, Fdistance1);
        Location.distanceBetween(frontLat[2], frontLon[2], latitude, longitude, Fdistance2);
        Location.distanceBetween(frontLat[3], frontLon[3], latitude, longitude, Fdistance3);
        Location.distanceBetween(frontLat[4], frontLon[4], latitude, longitude, Fdistance4);
        Location.distanceBetween(frontLat[5], frontLon[5], latitude, longitude, Fdistance5);
        Location.distanceBetween(frontLat[6], frontLon[6], latitude, longitude, Fdistance6);
        Location.distanceBetween(frontLat[7], frontLon[7], latitude, longitude, Fdistance7);
        Location.distanceBetween(frontLat[8], frontLon[8], latitude, longitude, Fdistance8);
        Location.distanceBetween(frontLat[9], frontLon[9], latitude, longitude, Fdistance9);
        Location.distanceBetween(frontLat[10], frontLon[10], latitude, longitude, Fdistance10);
        Location.distanceBetween(frontLat[11], frontLon[11], latitude, longitude, Fdistance11);
        Location.distanceBetween(frontLat[12], frontLon[12], latitude, longitude, Fdistance12);
        Location.distanceBetween(frontLat[13], frontLon[13], latitude, longitude, Fdistance13);
        Location.distanceBetween(frontLat[14], frontLon[14], latitude, longitude, Fdistance14);
        Location.distanceBetween(frontLat[15], frontLon[15], latitude, longitude, Fdistance15);
        Location.distanceBetween(frontLat[16], frontLon[16], latitude, longitude, Fdistance16);
        Location.distanceBetween(frontLat[17], frontLon[17], latitude, longitude, Fdistance17);
        Location.distanceBetween(frontLat[18], frontLon[18], latitude, longitude, Fdistance18);


        float Byards1 = Bdistance1[0] * 1.093f;
        float Byards2 = Bdistance2[0] * 1.093f;
        float Byards3 = Bdistance3[0] * 1.093f;
        float Byards4 = Bdistance4[0] * 1.093f;
        float Byards5 = Bdistance5[0] * 1.093f;
        float Byards6 = Bdistance6[0] * 1.093f;
        float Byards7 = Bdistance7[0] * 1.093f;
        float Byards8 = Bdistance8[0] * 1.093f;
        float Byards9 = Bdistance9[0] * 1.093f;
        float Byards10 = Bdistance10[0] * 1.093f;
        float Byards11 = Bdistance11[0] * 1.093f;
        float Byards12 = Bdistance12[0] * 1.093f;
        float Byards13 = Bdistance13[0] * 1.093f;
        float Byards14 = Bdistance14[0] * 1.093f;
        float Byards15 = Bdistance15[0] * 1.093f;
        float Byards16 = Bdistance16[0] * 1.093f;
        float Byards17 = Bdistance17[0] * 1.093f;
        float Byards18 = Bdistance18[0] * 1.093f;

        float Fyards1 = Fdistance1[0] * 1.093f;
        float Fyards2 = Fdistance2[0] * 1.093f;
        float Fyards3 = Fdistance3[0] * 1.093f;
        float Fyards4 = Fdistance4[0] * 1.093f;
        float Fyards5 = Fdistance5[0] * 1.093f;
        float Fyards6 = Fdistance6[0] * 1.093f;
        float Fyards7 = Fdistance7[0] * 1.093f;
        float Fyards8 = Fdistance8[0] * 1.093f;
        float Fyards9 = Fdistance9[0] * 1.093f;
        float Fyards10 = Fdistance10[0] * 1.093f;
        float Fyards11 = Fdistance11[0] * 1.093f;
        float Fyards12 = Fdistance12[0] * 1.093f;
        float Fyards13 = Fdistance13[0] * 1.093f;
        float Fyards14 = Fdistance14[0] * 1.093f;
        float Fyards15 = Fdistance15[0] * 1.093f;
        float Fyards16 = Fdistance16[0] * 1.093f;
        float Fyards17 = Fdistance17[0] * 1.093f;
        float Fyards18 = Fdistance18[0] * 1.093f;

        int BroundedYard1 = (int) (Byards1 + 0.5);
        int BroundedYard2 = (int) (Byards2 + 0.5);
        int BroundedYard3 = (int) (Byards3 + 0.5);
        int BroundedYard4 = (int) (Byards4 + 0.5);
        int BroundedYard5 = (int) (Byards5 + 0.5);
        int BroundedYard6 = (int) (Byards6 + 0.5);
        int BroundedYard7 = (int) (Byards7 + 0.5);
        int BroundedYard8 = (int) (Byards8 + 0.5);
        int BroundedYard9 = (int) (Byards9 + 0.5);
        int BroundedYard10 = (int) (Byards10 + 0.5);
        int BroundedYard11 = (int) (Byards11 + 0.5);
        int BroundedYard12 = (int) (Byards12 + 0.5);
        int BroundedYard13 = (int) (Byards13 + 0.5);
        int BroundedYard14 = (int) (Byards14 + 0.5);
        int BroundedYard15 = (int) (Byards15 + 0.5);
        int BroundedYard16 = (int) (Byards16 + 0.5);
        int BroundedYard17 = (int) (Byards17 + 0.5);
        int BroundedYard18 = (int) (Byards18 + 0.5);

        int FroundedYard1 = (int) (Fyards1 + 0.5);
        int FroundedYard2 = (int) (Fyards2 + 0.5);
        int FroundedYard3 = (int) (Fyards3 + 0.5);
        int FroundedYard4 = (int) (Fyards4 + 0.5);
        int FroundedYard5 = (int) (Fyards5 + 0.5);
        int FroundedYard6 = (int) (Fyards6 + 0.5);
        int FroundedYard7 = (int) (Fyards7 + 0.5);
        int FroundedYard8 = (int) (Fyards8 + 0.5);
        int FroundedYard9 = (int) (Fyards9 + 0.5);
        int FroundedYard10 = (int) (Fyards10 + 0.5);
        int FroundedYard11 = (int) (Fyards11 + 0.5);
        int FroundedYard12 = (int) (Fyards12 + 0.5);
        int FroundedYard13 = (int) (Fyards13 + 0.5);
        int FroundedYard14 = (int) (Fyards14 + 0.5);
        int FroundedYard15 = (int) (Fyards15 + 0.5);
        int FroundedYard16 = (int) (Fyards16 + 0.5);
        int FroundedYard17 = (int) (Fyards17 + 0.5);
        int FroundedYard18 = (int) (Fyards18 + 0.5);

        b1.setText(String.valueOf(BroundedYard1));
        b2.setText(String.valueOf(BroundedYard2));
        b3.setText(String.valueOf(BroundedYard3));
        b4.setText(String.valueOf(BroundedYard4));
        b5.setText(String.valueOf(BroundedYard5));
        b6.setText(String.valueOf(BroundedYard6));
        b7.setText(String.valueOf(BroundedYard7));
        b8.setText(String.valueOf(BroundedYard8));
        b9.setText(String.valueOf(BroundedYard9));
        b10.setText(String.valueOf(BroundedYard10));
        b11.setText(String.valueOf(BroundedYard11));
        b12.setText(String.valueOf(BroundedYard12));
        b13.setText(String.valueOf(BroundedYard13));
        b14.setText(String.valueOf(BroundedYard14));
        b15.setText(String.valueOf(BroundedYard15));
        b16.setText(String.valueOf(BroundedYard16));
        b17.setText(String.valueOf(BroundedYard17));
        b18.setText(String.valueOf(BroundedYard18));

        f1.setText(String.valueOf(FroundedYard1));
        f2.setText(String.valueOf(FroundedYard2));
        f3.setText(String.valueOf(FroundedYard3));
        f4.setText(String.valueOf(FroundedYard4));
        f5.setText(String.valueOf(FroundedYard5));
        f6.setText(String.valueOf(FroundedYard6));
        f7.setText(String.valueOf(FroundedYard7));
        f8.setText(String.valueOf(FroundedYard8));
        f9.setText(String.valueOf(FroundedYard9));
        f10.setText(String.valueOf(FroundedYard10));
        f11.setText(String.valueOf(FroundedYard11));
        f12.setText(String.valueOf(FroundedYard12));
        f13.setText(String.valueOf(FroundedYard13));
        f14.setText(String.valueOf(FroundedYard14));
        f15.setText(String.valueOf(FroundedYard15));
        f16.setText(String.valueOf(FroundedYard16));
        f17.setText(String.valueOf(FroundedYard17));
        f18.setText(String.valueOf(FroundedYard18));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
        System.out.println("locationUpdates removed");

    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
        System.out.println("locationUpdates removed");
    }

    //
    @Override
    protected void onRestart() {
        super.onRestart();
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

    public void setTextToZero() {
        dText1.setText("...");
        dText2.setText("...");
        dText3.setText("...");
        dText4.setText("...");
        dText5.setText("...");
        dText6.setText("...");
        dText7.setText("...");
        dText8.setText("...");
        dText9.setText("...");
        dText10.setText("...");
        dText11.setText("...");
        dText12.setText("...");
        dText13.setText("...");
        dText14.setText("...");
        dText15.setText("...");
        dText16.setText("...");
        dText17.setText("...");
        dText18.setText("...");

        f1.setText(".");
        f2.setText(".");
        f3.setText(".");
        f4.setText(".");
        f5.setText(".");
        f6.setText(".");
        f7.setText(".");
        f8.setText(".");
        f9.setText(".");
        f10.setText(".");
        f11.setText(".");
        f12.setText(".");
        f13.setText(".");
        f14.setText(".");
        f15.setText(".");
        f16.setText(".");
        f17.setText(".");
        f18.setText(".");
        b1.setText(".");
        b2.setText(".");
        b3.setText(".");
        b4.setText(".");
        b5.setText(".");
        b6.setText(".");
        b7.setText(".");
        b8.setText(".");
        b9.setText(".");
        b10.setText(".");
        b11.setText(".");
        b12.setText(".");
        b13.setText(".");
        b14.setText(".");
        b15.setText(".");
        b16.setText(".");
        b17.setText(".");
        b18.setText(".");
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()) {
                pd.dismiss();
            }
//            txtJson.setText(result);

            if (isNetworkAvailable() && (result != null || !result.equals(""))) {


                try {
                    JSONObject jObject = new JSONObject(result);

                    JSONObject jMain = jObject.getJSONObject("main");
                    String temp = jMain.getString("temp");

                    JSONObject jWind = jObject.getJSONObject("wind");
                    String wSpeed = jWind.getString("speed");

                    //getting wind direction in degrees, if wind is too slow there will be no direction, so handle exception
                    String wDir = "0";
                    try {
                        wDir = jWind.getString("deg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        wDir = "0";
                    }


                    double convertTemp = 273.15;
                    convertTemp = Double.valueOf(temp) - convertTemp;
                    convertTemp = round(convertTemp, 1);
                    temp = String.valueOf(convertTemp);
                    System.out.println(temp);
                    txtJson.setText(temp + " °C");
                    txtJson.setVisibility(View.VISIBLE);


                    double convertWind = 3.6;
                    convertWind = Double.valueOf(wSpeed) * convertWind;
                    convertWind = round(convertWind, 1);
                    wSpeed = String.valueOf(convertWind);
                    System.out.println(wSpeed);
                    System.out.println(wDir);
                    float rotation = Float.valueOf(wDir);
                    arrow.setRotation(rotation - 270f);
                    arrow.setVisibility(View.VISIBLE);
                    windSpeedText.setText("Wind Speed: " + wSpeed + " km/h");
                    windSpeedText.setVisibility(View.VISIBLE);
//                    windDirText.setText("Wind Direction: " + wDir + "°");
//                    windDirText.setVisibility(View.VISIBLE);

                    northLetter.setVisibility(View.VISIBLE);

//                    btnHit.setClickable(false);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                noInternet.setVisibility(View.VISIBLE);

            }

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void populateCoordinateArrays() {
        //north west coordinates
        nwLat[1] = 55.111061;
        nwLong[1] = -7.470921;
        nwLat[2] = 55.109386;
        nwLong[2] = -7.473592;
        nwLat[3] = 55.108765;
        nwLong[3] = -7.474166;
        nwLat[4] = 55.105970;
        nwLong[4] = -7.473671;
        nwLat[5] = 55.102986;
        nwLong[5] = -7.475754;
        nwLat[6] = 55.105574;
        nwLong[6] = -7.472768;
        nwLat[7] = 55.108760;
        nwLong[7] = -7.469886;
        nwLat[8] = 55.108174;
        nwLong[8] = -7.469622;
        nwLat[9] = 55.112516;
        nwLong[9] = -7.465833;
        nwLat[10] = 55.114472;
        nwLong[10] = -7.463025;
        nwLat[11] = 55.112948;
        nwLong[11] = -7.466560;
        nwLat[12] = 55.109039;
        nwLong[12] = -7.469428;
        nwLat[13] = 55.110243;
        nwLong[13] = -7.469639;
        nwLat[14] = 55.108407;
        nwLong[14] = -7.472316;
        nwLat[15] = 55.106348;
        nwLong[15] = -7.471947;
        nwLat[16] = 55.105450;
        nwLong[16] = -7.472282;
        nwLat[17] = 55.109050;
        nwLong[17] = -7.473564;
        nwLat[18] = 55.111530;
        nwLong[18] = -7.468690;

        //Buncrana co ordinates
        bcLat[1] = 55.124619;
        bcLong[1] = -7.457340;
        bcLat[2] = 55.123307;
        bcLong[2] = -7.456721;
        bcLat[3] = 55.124618;
        bcLong[3] = -7.457744;
        bcLat[4] = 55.122529;
        bcLong[4] = -7.457093;
        bcLat[5] = 55.127252;
        bcLong[5] = -7.460156;
        bcLat[6] = 55.127258;
        bcLong[6] = -7.459483;
        bcLat[7] = 55.124962;
        bcLong[7] = -7.457947;
        bcLat[8] = 55.127454;
        bcLong[8] = -7.458692;
        bcLat[9] = 55.127079;
        bcLong[9] = -7.457915;
        bcLat[10] = 0;
        bcLong[10] = 0;
        bcLat[11] = 0;
        bcLong[11] = 0;
        bcLat[12] = 0;
        bcLong[12] = 0;
        bcLat[13] = 0;
        bcLong[13] = 0;
        bcLat[14] = 0;
        bcLong[14] = 0;
        bcLat[15] = 0;
        bcLong[15] = 0;
        bcLat[16] = 0;
        bcLong[16] = 0;
        bcLat[17] = 0;
        bcLong[17] = 0;
        bcLat[18] = 0;
        bcLong[18] = 0;

        //Ballyliffin Old Links
        bloLat[1] = 55.295708;
        bloLon[1] = -7.370343;
        bloLat[2] = 55.299623;
        bloLon[2] = -7.369054;
        bloLat[3] = 55.300574;
        bloLon[3] = -7.372707;
        bloLat[4] = 55.296418;
        bloLon[4] = -7.370266;
        bloLat[5] = 55.294629;
        bloLon[5] = -7.367512;
        bloLat[6] = 55.292088;
        bloLon[6] = -7.365688;
        bloLat[7] = 55.291302;
        bloLon[7] = -7.366574;
        bloLat[8] = 55.293761;
        bloLon[8] = -7.367334;
        bloLat[9] = 55.291485;
        bloLon[9] = -7.371499;
        bloLat[10] = 55.295563;
        bloLon[10] = -7.372883;
        bloLat[11] = 55.298276;
        bloLon[11] = -7.371554;
        bloLat[12] = 55.299922;
        bloLon[12] = -7.372818;
        bloLat[13] = 55.302784;
        bloLon[13] = -7.375221;
        bloLat[14] = 55.297555;
        bloLon[14] = -7.377919;
        bloLat[15] = 55.293921;
        bloLon[15] = -7.378471;
        bloLat[16] = 55.291755;
        bloLon[16] = -7.381436;
        bloLat[17] = 55.290570;
        bloLon[17] = -7.381314;
        bloLat[18] = 55.292597;
        bloLon[18] = -7.374077;

        //ballyliffin old back
        bloBLat[1] = 55.295816;
        bloBLon[1] = -7.370180;
        bloBLat[2] = 55.299737;
        bloBLon[2] = -7.368994;
        bloBLat[3] = 55.300581;
        bloBLon[3] = -7.372905;
        bloBLat[4] = 55.296312;
        bloBLon[4] = -7.370285;
        bloBLat[5] = 55.294556;
        bloBLon[5] = -7.367287;
        bloBLat[6] = 55.291982;
        bloBLon[6] = -7.365650;
        bloBLat[7] = 55.291246;
        bloBLon[7] = -7.366847;
        bloBLat[8] = 55.293928;
        bloBLon[8] = -7.367316;
        bloBLat[9] = 55.291399;
        bloBLon[9] = -7.371675;
        bloBLat[10] = 55.295706;
        bloBLon[10] = -7.372693;
        bloBLat[11] = 55.298407;
        bloBLon[11] = -7.371494;
        bloBLat[12] = 55.300044;
        bloBLon[12] = -7.372827;
        bloBLat[13] = 55.302937;
        bloBLon[13] = -7.375163;
        bloBLat[14] = 55.297490;
        bloBLon[14] = -7.377930;
        bloBLat[15] = 55.293834;
        bloBLon[15] = -7.378602;
        bloBLat[16] = 55.291704;
        bloBLon[16] = -7.381441;
        bloBLat[17] = 55.290484;
        bloBLon[17] = -7.381426;
        bloBLat[18] = 55.292664;
        bloBLon[18] = -7.373906;

        //ballyliffin old front

        bloFLat[1] = 55.295599;
        bloFLon[1] = -7.370518;
        bloFLat[2] = 55.299494;
        bloFLon[2] = -7.369132;
        bloFLat[3] = 55.300506;
        bloFLon[3] = -7.372539;
        bloFLat[4] = 55.296549;
        bloFLon[4] = -7.370262;
        bloFLat[5] = 55.294733;
        bloFLon[5] = -7.367708;
        bloFLat[6] = 55.292197;
        bloFLon[6] = -7.365711;
        bloFLat[7] = 55.291410;
        bloFLon[7] = -7.366260;
        bloFLat[8] = 55.293613;
        bloFLon[8] = -7.367378;
        bloFLat[9] = 55.291599;
        bloFLon[9] = -7.371346;
        bloFLat[10] = 55.295416;
        bloFLon[10] = -7.373059;
        bloFLat[11] = 55.298168;
        bloFLon[11] = -7.371578;
        bloFLat[12] = 55.299792;
        bloFLon[12] = -7.372816;
        bloFLat[13] = 55.302640;
        bloFLon[13] = -7.375202;
        bloFLat[14] = 55.297676;
        bloFLon[14] = -7.377874;
        bloFLat[15] = 55.294023;
        bloFLon[15] = -7.378325;
        bloFLat[16] = 55.291872;
        bloFLon[16] = -7.381413;
        bloFLat[17] = 55.290677;
        bloFLon[17] = -7.381199;
        bloFLat[18] = 55.292534;
        bloFLon[18] = -7.374208;


        //ballyliffin glashedy links
        blgLat[1] = 55.294274;
        blgLon[1] = -7.367827;
        blgLat[2] = 55.296994;
        blgLon[2] = -7.361751;
        blgLat[3] = 55.298439;
        blgLon[3] = -7.356450;
        blgLat[4] = 55.300008;
        blgLon[4] = -7.362289;
        blgLat[5] = 55.300781;
        blgLon[5] = -7.365157;
        blgLat[6] = 55.297968;
        blgLon[6] = -7.361918;
        blgLat[7] = 55.297401;
        blgLon[7] = -7.363201;
        blgLat[8] = 55.295791;
        blgLon[8] = -7.368021;
        blgLat[9] = 55.293188;
        blgLon[9] = -7.371848;
        blgLat[10] = 55.296428;
        blgLon[10] = -7.373940;
        blgLat[11] = 55.299322;
        blgLon[11] = -7.373898;
        blgLat[12] = 55.302256;
        blgLon[12] = -7.368775;
        blgLat[13] = 55.299013;
        blgLon[13] = -7.363243;
        blgLat[14] = 55.299683;
        blgLon[14] = -7.366701;
        blgLat[15] = 55.301076;
        blgLon[15] = -7.372793;
        blgLat[16] = 55.295932;
        blgLon[16] = -7.376213;
        blgLat[17] = 55.291818;
        blgLon[17] = -7.379100;
        blgLat[18] = 55.292898;
        blgLon[18] = -7.374770;

        //Portsalon
        psLat[1] = 55.205055;
        psLon[1] = -7.625933;
        psLat[2] = 55.201709;
        psLon[2] = -7.626437;
        psLat[3] = 55.199751;
        psLon[3] = -7.624622;
        psLat[4] = 55.196242;
        psLon[4] = -7.622001;
        psLat[5] = 55.198262;
        psLon[5] = -7.622216;
        psLat[6] = 55.195517;
        psLon[6] = -7.619590;
        psLat[7] = 55.192286;
        psLon[7] = -7.618245;
        psLat[8] = 55.195962;
        psLon[8] = -7.622138;
        psLat[9] = 55.199428;
        psLon[9] = -7.624689;
        psLat[10] = 55.197552;
        psLon[10] = -7.625068;
        psLat[11] = 55.193367;
        psLon[11] = -7.620589;
        psLat[12] = 55.194911;
        psLon[12] = -7.623548;
        psLat[13] = 55.197125;
        psLon[13] = -7.625942;
        psLat[14] = 55.200121;
        psLon[14] = -7.625439;
        psLat[15] = 55.199403;
        psLon[15] = -7.627016;
        psLat[16] = 55.201763;
        psLon[16] = -7.629144;
        psLat[17] = 55.205472;
        psLon[17] = -7.627161;
        psLat[18] = 55.207590;
        psLon[18] = -7.623168;

        //Ballybofey golf club
        balLat[1] = 55.205055;
        balLon[1] = -7.625933;
        balLat[2] = 55.201709;
        balLon[2] = -7.626437;
        balLat[3] = 55.199751;
        balLon[3] = -7.624622;
        balLat[4] = 55.196242;
        balLon[4] = -7.622001;
        balLat[5] = 55.198262;
        balLon[5] = -7.622216;
        balLat[6] = 55.195517;
        balLon[6] = -7.619590;
        balLat[7] = 55.192286;
        balLon[7] = -7.618245;
        balLat[8] = 55.195962;
        balLon[8] = -7.622138;
        balLat[9] = 55.199428;
        balLon[9] = -7.624689;
        balLat[10] = 55.197552;
        balLon[10] = -7.625068;
        balLat[11] = 55.193367;
        balLon[11] = -7.620589;
        balLat[12] = 55.194911;
        balLon[12] = -7.623548;
        balLat[13] = 55.197125;
        balLon[13] = -7.625942;
        balLat[14] = 55.200121;
        balLon[14] = -7.625439;
        balLat[15] = 55.199403;
        balLon[15] = -7.627016;
        balLat[16] = 55.201763;
        balLon[16] = -7.629144;
        balLat[17] = 55.205472;
        balLon[17] = -7.627161;
        balLat[18] = 55.207590;
        balLon[18] = -7.623168;

    }

}
