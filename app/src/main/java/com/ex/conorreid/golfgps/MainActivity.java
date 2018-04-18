package com.ex.conorreid.golfgps;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

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

    boolean firstLocationResult = true; //set to false once the first onLocationChanged is called
    boolean metresSelected = false;

    int defaultCourse = 0;
    TextView tempTxt;
    ProgressDialog pd; //progress dialogue
    ImageView logo; //golf club logo
    ImageView arrow; //weather direction arrow
    ImageView leftGrey;
    ImageView rightGrey;
    TextView num1; //hole numbers
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

    TextView turnOnText; //'Turn On GPS' text shown when gps is turned off

    TextView latText;
    TextView lonText;

    TextView noInternet; //'No Internet for Weather' when app has no internet access

    TextView dText1; //centre yardage textViews
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

    TextView b1;//back and front yardage textViews
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

    boolean gpsPossible = false; //if device has gps. Checked with hasGPSDevice() method
    //    boolean gpsPermission = false;
    boolean gpsOn = false;

    double[] nwLat = new double[19];//initialise golf club green coordinate arrays
    double[] nwLong = new double[19];
    double[] nwBLat = new double[19];
    double[] nwBLong = new double[19];
    double[] nwFLat = new double[19];
    double[] nwFLong = new double[19];

    double[] bcLat = new double[19];
    double[] bcLong = new double[19];
    double[] bcBLat = new double[19];
    double[] bcBLong = new double[19];
    double[] bcFLat = new double[19];
    double[] bcFLong = new double[19];


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
    double[] balBLat = new double[19];
    double[] balBLon = new double[19];
    double[] balFLat = new double[19];
    double[] balFLon = new double[19];

    double[] narPLat = new double[19];
    double[] narPLon = new double[19];
    double[] narPBLat = new double[19];
    double[] narPBLon = new double[19];
    double[] narPFLat = new double[19];
    double[] narPFLon = new double[19];

    double[] donLat = new double[19];
    double[] donLon = new double[19];
    double[] donBLat = new double[19];
    double[] donBLon = new double[19];
    double[] donFLat = new double[19];
    double[] donFLon = new double[19];

    int selectedClub = 0; //track selected golf club from spinner

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String weatherLink = "http://api.openweathermap.org/data/2.5/weather?id=2654332&APPID=d2f1c7fd747498a9246f9467457b722e"; //link to get weather data is JSON format

    AdView adView;


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    } //method to round a double to a certain place

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        if(metresSelected){
            MenuItem item = menu.getItem(0);
            item.setTitle("Change To Yards");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);

        switch (item.getItemId()) {
            case R.id.changeToMetres:
                if (metresSelected) {
                    metresSelected = false;
                    item.setTitle("Change To Metres");
                } else {
                    metresSelected = true;
                    item.setTitle("Change To Yards");
                }
                editor = sp.edit();
                editor.putBoolean("metres_bool", metresSelected);
                editor.commit();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-7306277568792563~2937703196"); //app id from admob. ad is changed from test to live ad in the xml, not here.
        adView = findViewById(R.id.adView); //adView banner at bottom of screen

        sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        editor = sp.edit();

        leftGrey = findViewById(R.id.imageView2);
        rightGrey = findViewById(R.id.imageView4);
        dText1 = findViewById(R.id.distText1); //link views to pointers
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
        tempTxt = findViewById(R.id.tempText);
        populateCoordinateArrays(); //set up gps coordinates for greens

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

                setTextToZero(); //sets all yardage fields to '...' and '.' to look like they are loading. They will look like this until the first locationUpdate occurs
                selectedClub = position; //update selected club to the spinner item selected

                if (selectedClub == 0) { //north west

                    logo.setImageResource(R.drawable.north_west_logo); //set club logo

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                } else if (selectedClub == 1) { //buncrana 9-hole, therefore hide back 9 fields.

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
                } else if (selectedClub == 2) { //ballyliffin (old)

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

                } else if (selectedClub == 3) { //ballyliffin(glashedy), same logo as old links
                    logo.setImageResource(R.drawable.bl_logo);
                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);
                    }
                } else if (selectedClub == 4) { //portsalon

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

                } else if (selectedClub == 5) { //ballybofey & stranorlar

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

                } else if (selectedClub == 6) { //narin & portnoo

                    logo.setImageResource(R.drawable.narp_logo);

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                } else if (selectedClub == 7) { //Donegal (murvagh)

                    logo.setImageResource(R.drawable.murvagh_logo);

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

                editor.putInt("default_course_int", position);
                editor.commit();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        new JsonTask().execute(weatherLink); //download weather data using weatherLink string with JsonTask class

// Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.

                makeUseOfNewLocation(location);
                if (firstLocationResult) { //if this is the first time the location was received

                    AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).setLocation(location).build(); //build adRequest using location object
                    adView.loadAd(adRequest); //load ad into adView (banner at bottom)
                    firstLocationResult = false; //update boolean so adRequest isn't called again (only call once)
                }

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                System.out.println("Status changed : " + status);
            }

            public void onProviderEnabled(String provider) { //gps turned on
                gpsOn = true;
                System.out.println("Provider: " + provider + "Enabled");
                statusText.setText("GPS Active");
                statusText.setTextColor(Color.parseColor("#00C853"));
                hideFront9(false);
                turnOnText.setVisibility(View.INVISIBLE);
                leftGrey.setVisibility(View.VISIBLE);
                rightGrey.setVisibility(View.VISIBLE);
                if (selectedClub == 1) {
                    hideBack9(true);
                } else {
                    hideBack9(false);
                }
            }

            public void onProviderDisabled(String provider) { //gps turned off
                gpsOn = false;
                System.out.println("Provider: " + provider + "Disabled");
                statusText.setText("GPS Off");
                statusText.setTextColor(Color.RED);
                hideFront9(true);
                hideBack9(true);
                turnOnText.setVisibility(View.VISIBLE);
                leftGrey.setVisibility(View.INVISIBLE);
                rightGrey.setVisibility(View.INVISIBLE);
            }
        };

        setTextToZero(); //sets all yardage fields to '...' and '.' to look like they are loading. They will look like this until the first locationUpdate occurs

        if (!gpsPossible) { //top right text set according to if gps is possible
            statusText.setText("No GPS Hardware Detected");
            statusText.setTextColor(Color.RED);
        } else { //if gps possible, ask permission
            statusText.setText("GPS Hardware Detected");
            statusText.setTextColor(Color.parseColor("#FBC02D"));
//            checkGPSStatus();
            checkLocationPermission();

        }

        SharedPreferences sharedp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        defaultCourse = sharedp.getInt("default_course_int", 0);
        metresSelected = sharedp.getBoolean("metres_bool", false);

        spinner.setSelection(defaultCourse);


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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
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
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
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

        //new location of device received. Compare this location to the locations of the greens of the
        //course that is currently selected

        System.out.println("Received new location!");
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        System.out.println(String.valueOf("Lat: " + latitude));
        System.out.println(String.valueOf("Lon: " + longitude));
//        latText.setText(String.valueOf(latitude));
//        lonText.setText(String.valueOf(longitude));

        if (selectedClub == 0) {
            setUpYardage(nwLat, nwLong, location);
            setUpBackFrontYardage(nwBLat, nwBLong, nwFLat, nwFLong, location);
        } else if (selectedClub == 1) {
            hideBack9(true);
            setUpYardage(bcLat, bcLong, location);
            setUpBackFrontYardage(bcBLat, bcBLong, bcFLat, bcFLong, location);
        } else if (selectedClub == 2) {
            setUpYardage(bloLat, bloLon, location);
            setUpBackFrontYardage(bloBLat, bloBLon, bloFLat, bloFLon, location);
        } else if (selectedClub == 3) {
            setUpYardage(blgLat, blgLon, location);
        } else if (selectedClub == 4) {
            setUpYardage(psLat, psLon, location);
        } else if (selectedClub == 5) {
            setUpYardage(balLat, balLon, location);
            setUpBackFrontYardage(balBLat, balBLon, balFLat, balFLon, location);
        } else if (selectedClub == 6) {
            setUpYardage(narPLat, narPLon, location);
            setUpBackFrontYardage(narPBLat, narPBLon, narPFLat, narPFLon, location);
        } else if (selectedClub == 7) {
            setUpYardage(donLat, donLon, location);
            setUpBackFrontYardage(donBLat, donBLon, donFLat, donFLon, location);
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
        //used to set up centre yardage. Takes 2 arrays. Centre Lat & Lon. And users location.


        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        latText.setText(String.valueOf(latitude));
        lonText.setText(String.valueOf(longitude));

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

        System.out.println("Distance to first green is " + String.valueOf(distance1[0]));

        float yards1 = distance1[0];
        float yards2 = distance2[0];
        float yards3 = distance3[0];
        float yards4 = distance4[0];
        float yards5 = distance5[0];
        float yards6 = distance6[0];
        float yards7 = distance7[0];
        float yards8 = distance8[0];
        float yards9 = distance9[0];
        float yards10 = distance10[0];
        float yards11 = distance11[0];
        float yards12 = distance12[0];
        float yards13 = distance13[0];
        float yards14 = distance14[0];
        float yards15 = distance15[0];
        float yards16 = distance16[0];
        float yards17 = distance17[0];
        float yards18 = distance18[0];

        String distanceLetter = "";
        if (metresSelected) {
            //metres selected so do nothing
            distanceLetter = "m";
        } else {
            //change to yards
            yards1 = distance1[0] * 1.093f;
            yards2 = distance2[0] * 1.093f;
            yards3 = distance3[0] * 1.093f;
            yards4 = distance4[0] * 1.093f;
            yards5 = distance5[0] * 1.093f;
            yards6 = distance6[0] * 1.093f;
            yards7 = distance7[0] * 1.093f;
            yards8 = distance8[0] * 1.093f;
            yards9 = distance9[0] * 1.093f;
            yards10 = distance10[0] * 1.093f;
            yards11 = distance11[0] * 1.093f;
            yards12 = distance12[0] * 1.093f;
            yards13 = distance13[0] * 1.093f;
            yards14 = distance14[0] * 1.093f;
            yards15 = distance15[0] * 1.093f;
            yards16 = distance16[0] * 1.093f;
            yards17 = distance17[0] * 1.093f;
            yards18 = distance18[0] * 1.093f;
            distanceLetter = "y";
        }


        int[] roundedYardArray = new int[19];

        roundedYardArray[1] = (int) (yards1 + 0.5);
        roundedYardArray[2] = (int) (yards2 + 0.5);
        roundedYardArray[3] = (int) (yards3 + 0.5);
        roundedYardArray[4] = (int) (yards4 + 0.5);
        roundedYardArray[5] = (int) (yards5 + 0.5);
        roundedYardArray[6] = (int) (yards6 + 0.5);
        roundedYardArray[7] = (int) (yards7 + 0.5);
        roundedYardArray[8] = (int) (yards8 + 0.5);
        roundedYardArray[9] = (int) (yards9 + 0.5);
        roundedYardArray[10] = (int) (yards10 + 0.5);
        roundedYardArray[11] = (int) (yards11 + 0.5);
        roundedYardArray[12] = (int) (yards12 + 0.5);
        roundedYardArray[13] = (int) (yards13 + 0.5);
        roundedYardArray[14] = (int) (yards14 + 0.5);
        roundedYardArray[15] = (int) (yards15 + 0.5);
        roundedYardArray[16] = (int) (yards16 + 0.5);
        roundedYardArray[17] = (int) (yards17 + 0.5);
        roundedYardArray[18] = (int) (yards18 + 0.5);


        String[] roundedYardArrayString = new String[19];


        for (int i = 1; i < 19; i++) {
            if (roundedYardArray[i] > 5000) {
                roundedYardArrayString[i] = "5000+";
            } else if (roundedYardArray[i] < 25) {
                roundedYardArrayString[i] = "<25";
            } else {
                roundedYardArrayString[i] = String.valueOf(roundedYardArray[i]);
            }
        }

        dText1.setText(roundedYardArrayString[1] + distanceLetter);
        dText2.setText(roundedYardArrayString[2] + distanceLetter);
        dText3.setText(roundedYardArrayString[3] + distanceLetter);
        dText4.setText(roundedYardArrayString[4] + distanceLetter);
        dText5.setText(roundedYardArrayString[5] + distanceLetter);
        dText6.setText(roundedYardArrayString[6] + distanceLetter);
        dText7.setText(roundedYardArrayString[7] + distanceLetter);
        dText8.setText(roundedYardArrayString[8] + distanceLetter);
        dText9.setText(roundedYardArrayString[9] + distanceLetter);
        dText10.setText(roundedYardArrayString[10] + distanceLetter);
        dText11.setText(roundedYardArrayString[11] + distanceLetter);
        dText12.setText(roundedYardArrayString[12] + distanceLetter);
        dText13.setText(roundedYardArrayString[13] + distanceLetter);
        dText14.setText(roundedYardArrayString[14] + distanceLetter);
        dText15.setText(roundedYardArrayString[15] + distanceLetter);
        dText16.setText(roundedYardArrayString[16] + distanceLetter);
        dText17.setText(roundedYardArrayString[17] + distanceLetter);
        dText18.setText(roundedYardArrayString[18] + distanceLetter);

    }

    public void setUpBackFrontYardage(double[] backLat, double[] backLon, double[] frontLat, double[] frontLon, Location location) {
        //used to set up front & back yardage. Take 4 arrays. Back LAt and Lon, and Front Lat & Lon. And users location.
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

//        float[][] = new float[19];
        float[][] bDistance = new float[19][1];
        float[][] fDistance = new float[19][1];


        for (int i = 1; i < 19; i++) {
            Location.distanceBetween(backLat[i], backLon[i], latitude, longitude, bDistance[i]);
            Location.distanceBetween(frontLat[i], frontLon[i], latitude, longitude, fDistance[i]);
        }

        float[] bDistanceArray = new float[19];
        float[] fDistanceArray = new float[19];
        for (int i = 1; i < 19; i++) {
            bDistanceArray[i] = bDistance[i][0];
            fDistanceArray[i] = fDistance[i][0];
        }


        if (metresSelected) {
            //metres so do nothing
        } else if (!metresSelected) {
            for (int i = 1; i < 19; i++) {
                //change to yards
                bDistanceArray[i] = bDistanceArray[i] * 1.093f;
                fDistanceArray[i] = fDistanceArray[i] * 1.093f;
            }
        }


        int[] bRoundedArray = new int[19];
        String[] bStringsArray = new String[19];

        for (int i = 1; i < 19; i++) {
            bRoundedArray[i] = (int) (bDistanceArray[i] + 0.5);

        }
        for (int i = 1; i < 19; i++) {
            if (bRoundedArray[i] > 5000) {
                bStringsArray[i] = ".";
            } else if (bRoundedArray[i] < 25) {
                bStringsArray[i] = "<25";
            } else {
                bStringsArray[i] = String.valueOf(bRoundedArray[i]);
            }
        }

        b1.setText(bStringsArray[1]);
        b2.setText(bStringsArray[2]);
        b3.setText(bStringsArray[3]);
        b4.setText(bStringsArray[4]);
        b5.setText(bStringsArray[5]);
        b6.setText(bStringsArray[6]);
        b7.setText(bStringsArray[7]);
        b8.setText(bStringsArray[8]);
        b9.setText(bStringsArray[9]);
        b10.setText(bStringsArray[10]);
        b11.setText(bStringsArray[11]);
        b12.setText(bStringsArray[12]);
        b13.setText(bStringsArray[13]);
        b14.setText(bStringsArray[14]);
        b15.setText(bStringsArray[15]);
        b16.setText(bStringsArray[16]);
        b17.setText(bStringsArray[17]);
        b18.setText(bStringsArray[18]);


        int[] fRoundedArray = new int[19];
        String[] fStringArray = new String[19];

        for (int i = 1; i < 19; i++) {
            fRoundedArray[i] = (int) (fDistanceArray[i] + 0.5);
        }
        for (int i = 1; i < 19; i++) {
            if (fRoundedArray[i] > 5000) {
                fStringArray[i] = ".";
            } else if (fRoundedArray[i] < 25) {
                fStringArray[i] = "<25";
            } else {
                fStringArray[i] = String.valueOf(fRoundedArray[i]);
            }
        }
        f1.setText(fStringArray[1]);
        f2.setText(fStringArray[2]);
        f3.setText(fStringArray[3]);
        f4.setText(fStringArray[4]);
        f5.setText(fStringArray[5]);
        f6.setText(fStringArray[6]);
        f7.setText(fStringArray[7]);
        f8.setText(fStringArray[8]);
        f9.setText(fStringArray[9]);
        f10.setText(fStringArray[10]);
        f11.setText(fStringArray[11]);
        f12.setText(fStringArray[12]);
        f13.setText(fStringArray[13]);
        f14.setText(fStringArray[14]);
        f15.setText(fStringArray[15]);
        f16.setText(fStringArray[16]);
        f17.setText(fStringArray[17]);
        f18.setText(fStringArray[18]);

    }

    @Override
    protected void onDestroy() { //remove location updates to save battery
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
        System.out.println("locationUpdates removed");

    }

    @Override
    protected void onStop() { //remove location updates to save battery
        super.onStop();
        locationManager.removeUpdates(locationListener);
        System.out.println("locationUpdates removed");
    }

    //
    @Override
    protected void onRestart() { //restart previously removed location updates
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

    public void setTextToZero() { //make yardage fields look like they are loading
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

            pd = new ProgressDialog(MainActivity.this); //progress dialogue
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

                return buffer.toString(); //returning buffer.toString(), this should contain raw json data


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
            return ""; //return empty string on error
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()) { //hide progress dialogue
                pd.dismiss();
            }
//            tempTxt.setText(result);

            if (isNetworkAvailable() && (result != null || !result.equals(""))) { //if we have interned and the json task returned a proper string


                try { //tru to extract json data
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
                    tempTxt.setText(temp + " °C");
                    tempTxt.setVisibility(View.VISIBLE);


                    double convertWind = 3.6;
                    convertWind = Double.valueOf(wSpeed) * convertWind;
                    convertWind = round(convertWind, 1);
                    wSpeed = String.valueOf(convertWind);
                    System.out.println(wSpeed);
                    System.out.println(wDir);
                    float rotation = Float.valueOf(wDir);
                    arrow.setRotation(rotation - 270f); //rotate arrow image according to wind direction
                    arrow.setVisibility(View.VISIBLE);
                    windSpeedText.setText(wSpeed + " km/h");
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

    private boolean isNetworkAvailable() { //check if app has internet access
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void populateCoordinateArrays() {

        //method to set all green gps coordinates
        //north west coordinates (done accurately, each of centre, front, and back)

        nwLat[1] = 55.111010;
        nwLong[1] = -7.471013;
        nwLat[2] = 55.109380;
        nwLong[2] = -7.473575;
        nwLat[3] = 55.108692;
        nwLong[3] = -7.474221;
        nwLat[4] = 55.105960;
        nwLong[4] = -7.473651;
        nwLat[5] = 55.102958;
        nwLong[5] = -7.475744;
        nwLat[6] = 55.105544;
        nwLong[6] = -7.472815;
        nwLat[7] = 55.108733;
        nwLong[7] = -7.469882;
        nwLat[8] = 55.108174;
        nwLong[8] = -7.469602;
        nwLat[9] = 55.112493;
        nwLong[9] = -7.465870;
        nwLat[10] = 55.114432;
        nwLong[10] = -7.463045;
        nwLat[11] = 55.112941;
        nwLong[11] = -7.466530;
        nwLat[12] = 55.109029;
        nwLong[12] = -7.469428;
        nwLat[13] = 55.110212;
        nwLong[13] = -7.469640;
        nwLat[14] = 55.108407;
        nwLong[14] = -7.472316;
        nwLat[15] = 55.106320;
        nwLong[15] = -7.471977;
        nwLat[16] = 55.105450;
        nwLong[16] = -7.472292;
        nwLat[17] = 55.109042;
        nwLong[17] = -7.473564;
        nwLat[18] = 55.111500;
        nwLong[18] = -7.468708;

        //north west back
        nwBLat[1] = 55.110912;
        nwBLong[1] = -7.471190;
        nwBLat[2] = 55.109236;
        nwBLong[2] = -7.473578;

        nwBLat[3] = 55.108593;
        nwBLong[3] = -7.474201;
        nwBLat[4] = 55.105829;
        nwBLong[4] = -7.473664;
        nwBLat[5] = 55.102846;
        nwBLong[5] = -7.475853;
        nwBLat[6] = 55.105672;
        nwBLong[6] = -7.472735;
        nwBLat[7] = 55.108865;
        nwBLong[7] = -7.469840;
        nwBLat[8] = 55.108049;
        nwBLong[8] = -7.469685;
        nwBLat[9] = 55.112616;
        nwBLong[9] = -7.465720;
        nwBLat[10] = 55.114537;
        nwBLong[10] = -7.462917;
        nwBLat[11] = 55.112827;
        nwBLong[11] = -7.466777;
        nwBLat[12] = 55.108919;
        nwBLong[12] = -7.469460;
        nwBLat[13] = 55.110325;
        nwBLong[13] = -7.469554;
        nwBLat[14] = 55.108272;
        nwBLong[14] = -7.472420;
        nwBLat[15] = 55.106195;
        nwBLong[15] = -7.472002;
        nwBLat[16] = 55.105304;
        nwBLong[16] = -7.472364;
        nwBLat[17] = 55.109117;
        nwBLong[17] = -7.473580;
        nwBLat[18] = 55.111601;
        nwBLong[18] = -7.468512;

        //north west front
        nwFLat[1] = 55.111104;
        nwFLong[1] = -7.470860;
        nwFLat[2] = 55.109508;
        nwFLong[2] = -7.473531;

        nwFLat[3] = 55.108787;
        nwFLong[3] = -7.474249;
        nwFLat[4] = 55.106074;
        nwFLong[4] = -7.473641;
        nwFLat[5] = 55.103089;
        nwFLong[5] = -7.475633;
        nwFLat[6] = 55.105388;
        nwFLong[6] = -7.472893;
        nwFLat[7] = 55.108594;
        nwFLong[7] = -7.469970;
        nwFLat[8] = 55.108262;
        nwFLong[8] = -7.469532;
        nwFLat[9] = 55.112399;
        nwFLong[9] = -7.466003;
        nwFLat[10] = 55.114323;
        nwFLong[10] = -7.463197;
        nwFLat[11] = 55.113048;
        nwFLong[11] = -7.466322;
        nwFLat[12] = 55.109173;
        nwFLong[12] = -7.469402;
        nwFLat[13] = 55.110091;
        nwFLong[13] = -7.469754;
        nwFLat[14] = 55.108590;
        nwFLong[14] = -7.472179;
        nwFLat[15] = 55.106469;
        nwFLong[15] = -7.471974;
        nwFLat[16] = 55.105600;
        nwFLong[16] = -7.472265;
        nwFLat[17] = 55.108934;
        nwFLong[17] = -7.473556;
        nwFLat[18] = 55.111378;
        nwFLong[18] = -7.468884;

        //Buncrana co ordinates (done accurately, each of centre, front, and back)
        bcLat[1] = 55.124619;
        bcLong[1] = -7.457340;
        bcLat[2] = 55.123307;
        bcLong[2] = -7.456721;
        bcLat[3] = 55.124591;
        bcLong[3] = -7.457730;
        bcLat[4] = 55.122529;
        bcLong[4] = -7.457093;
        bcLat[5] = 55.127232;
        bcLong[5] = -7.460073;
        bcLat[6] = 55.127249;
        bcLong[6] = -7.459488;
        bcLat[7] = 55.124949;
        bcLong[7] = -7.457960;
        bcLat[8] = 55.127433;
        bcLong[8] = -7.458690;
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

        //buncrana back
        bcBLat[1] = 55.124522;
        bcBLong[1] = -7.457375;
        bcBLat[2] = 55.123208;
        bcBLong[2] = -7.456732;
        bcBLat[3] = 55.124691;
        bcBLong[3] = -7.457730;
        bcBLat[4] = 55.122450;
        bcBLong[4] = -7.457060;
        bcBLat[5] = 55.127294;
        bcBLong[5] = -7.460073;
        bcBLat[6] = 55.127162;
        bcBLong[6] = -7.459442;
        bcBLat[7] = 55.124878;
        bcBLong[7] = -7.457948;
        bcBLat[8] = 55.127514;
        bcBLong[8] = -7.458665;
        bcBLat[9] = 55.126883;
        bcBLong[9] = -7.457837;
        bcBLat[10] = 0;
        bcBLong[10] = 0;
        bcBLat[11] = 0;
        bcBLong[11] = 0;
        bcBLat[12] = 0;
        bcBLong[12] = 0;
        bcBLat[13] = 0;
        bcBLong[13] = 0;
        bcBLat[14] = 0;
        bcBLong[14] = 0;
        bcBLat[15] = 0;
        bcBLong[15] = 0;
        bcBLat[16] = 0;
        bcBLong[16] = 0;
        bcBLat[17] = 0;
        bcBLong[17] = 0;
        bcBLat[18] = 0;
        bcBLong[18] = 0;

        //buncrana front
        bcFLat[1] = 55.124722;
        bcFLong[1] = -7.457342;
        bcFLat[2] = 55.123397;
        bcFLong[2] = -7.456699;
        bcFLat[3] = 55.124491;
        bcFLong[3] = -7.457734;
        bcFLat[4] = 55.122612;
        bcFLong[4] = -7.457200;
        bcFLat[5] = 55.127172;
        bcFLong[5] = -7.460073;
        bcFLat[6] = 55.127331;
        bcFLong[6] = -7.459562;
        bcFLat[7] = 55.125043;
        bcFLong[7] = -7.457986;
        bcFLat[8] = 55.127348;
        bcFLong[8] = -7.458693;
        bcFLat[9] = 55.127268;
        bcFLong[9] = -7.457989;
        bcFLat[10] = 0;
        bcFLong[10] = 0;
        bcFLat[11] = 0;
        bcFLong[11] = 0;
        bcFLat[12] = 0;
        bcFLong[12] = 0;
        bcFLat[13] = 0;
        bcFLong[13] = 0;
        bcFLat[14] = 0;
        bcFLong[14] = 0;
        bcFLat[15] = 0;
        bcFLong[15] = 0;
        bcFLat[16] = 0;
        bcFLong[16] = 0;
        bcFLat[17] = 0;
        bcFLong[17] = 0;
        bcFLat[18] = 0;
        bcFLong[18] = 0;

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

        //Ballybofey golf club centre
        balLat[1] = 54.805555;
        balLon[1] = -7.754869;
        balLat[2] = 54.804985;
        balLon[2] = -7.757247;
        balLat[3] = 54.806832;
        balLon[3] = -7.760340;
        balLat[4] = 54.805400;
        balLon[4] = -7.756617;
        balLat[5] = 54.807965;
        balLon[5] = -7.758530;
        balLat[6] = 54.808714;
        balLon[6] = -7.761584;
        balLat[7] = 54.811030;
        balLon[7] = -7.755455;
        balLat[8] = 54.811465;
        balLon[8] = -7.753492;
        balLat[9] = 54.808771;
        balLon[9] = -7.757309;
        balLat[10] = 54.810270;
        balLon[10] = -7.753289;
        balLat[11] = 54.809815;
        balLon[11] = -7.748270;
        balLat[12] = 54.808888;
        balLon[12] = -7.751463;
        balLat[13] = 54.807020;
        balLon[13] = -7.749992;
        balLat[14] = 54.808142;
        balLon[14] = -7.750925;
        balLat[15] = 54.807718;
        balLon[15] = -7.755711;
        balLat[16] = 54.809530;
        balLon[16] = -7.750766;
        balLat[17] = 54.810069;
        balLon[17] = -7.752465;
        balLat[18] = 54.808078;
        balLon[18] = -7.756928;

        //ballybofey back
        balBLat[1] = 54.805455;
        balBLon[1] = -7.754867;
        balBLat[2] = 54.804965;
        balBLon[2] = -7.757400;
        balBLat[3] = 54.806882;
        balBLon[3] = -7.760476;
        balBLat[4] = 54.805310;
        balBLon[4] = -7.756517;
        balBLat[5] = 54.808055;
        balBLon[5] = -7.758616;
        balBLat[6] = 54.808714;
        balBLon[6] = -7.761773;
        balBLat[7] = 54.811155;
        balBLon[7] = -7.755405;
        balBLat[8] = 54.811490;
        balBLon[8] = -7.753302;
        balBLat[9] = 54.808723;
        balBLon[9] = -7.757488;
        balBLat[10] = 54.810355;
        balBLon[10] = -7.753120;
        balBLat[11] = 54.809800;
        balBLon[11] = -7.748100;
        balBLat[12] = 54.808842;
        balBLon[12] = -7.751683;
        balBLat[13] = 54.806920;
        balBLon[13] = -7.749992;
        balBLat[14] = 54.808259;
        balBLon[14] = -7.750915;
        balBLat[15] = 54.807668;
        balBLon[15] = -7.755900;
        balBLat[16] = 54.809585;
        balBLon[16] = -7.750582;
        balBLat[17] = 54.810099;
        balBLon[17] = -7.752665;
        balBLat[18] = 54.808028;
        balBLon[18] = -7.757075;

        //ballybofey front

        balFLat[1] = 54.805663;
        balFLon[1] = -7.754884;
        balFLat[2] = 54.805005;
        balFLon[2] = -7.757100;
        balFLat[3] = 54.806794;
        balFLon[3] = -7.760193;
        balFLat[4] = 54.805496;
        balFLon[4] = -7.756737;
        balFLat[5] = 54.807880;
        balFLon[5] = -7.758410;
        balFLat[6] = 54.808714;
        balFLon[6] = -7.761375;
        balFLat[7] = 54.810905;
        balFLon[7] = -7.755485;
        balFLat[8] = 54.811445;
        balFLon[8] = -7.753675;
        balFLat[9] = 54.808830;
        balFLon[9] = -7.757117;
        balFLat[10] = 54.810188;
        balFLon[10] = -7.753429;
        balFLat[11] = 54.809840;
        balFLon[11] = -7.748449;
        balFLat[12] = 54.808921;
        balFLon[12] = -7.751272;
        balFLat[13] = 54.807110;
        balFLon[13] = -7.749992;
        balFLat[14] = 54.808042;
        balFLon[14] = -7.750930;
        balFLat[15] = 54.807739;
        balFLon[15] = -7.755529;
        balFLat[16] = 54.809470;
        balFLon[16] = -7.750961;
        balFLat[17] = 54.810040;
        balFLon[17] = -7.752300;
        balFLat[18] = 54.808118;
        balFLon[18] = -7.756789;

        //Narin & port golf club centre
        narPLat[1] = 54.839156;
        narPLon[1] = -8.439048;
        narPLat[2] = 54.840706;
        narPLon[2] = -8.432423;
        narPLat[3] = 54.842055;
        narPLon[3] = -8.431723;
        narPLat[4] = 54.843529;
        narPLon[4] = -8.425403;
        narPLat[5] = 54.846368;
        narPLon[5] = -8.424545;
        narPLat[6] = 54.848458;
        narPLon[6] = -8.424116;
        narPLat[7] = 54.849687;
        narPLon[7] = -8.423067;
        narPLat[8] = 54.851323;
        narPLon[8] = -8.420347;
        narPLat[9] = 54.851110;
        narPLon[9] = -8.424963;
        narPLat[10] = 54.846641;
        narPLon[10] = -8.426398;
        narPLat[11] = 54.846090;
        narPLon[11] = -8.427262;
        narPLat[12] = 54.843534;
        narPLon[12] = -8.429617;
        narPLat[13] = 54.841699;
        narPLon[13] = -8.434520;
        narPLat[14] = 54.845047;
        narPLon[14] = -8.428077;
        narPLat[15] = 54.842749;
        narPLon[15] = -8.434128;
        narPLat[16] = 54.842094;
        narPLon[16] = -8.435829;
        narPLat[17] = 54.839739;
        narPLon[17] = -8.438136;
        narPLat[18] = 54.840194;
        narPLon[18] = -8.442105;

        //narin & port back
        narPBLat[1] = 54.839178;
        narPBLon[1] = -8.438804;
        narPBLat[2] = 54.840675;
        narPBLon[2] = -8.432213;
        narPBLat[3] = 54.842083;
        narPBLon[3] = -8.431503;
        narPBLat[4] = 54.843552;
        narPBLon[4] = -8.425178;
        narPBLat[5] = 54.846483;
        narPBLon[5] = -8.424569;
        narPBLat[6] = 54.848544;
        narPBLon[6] = -8.424298;
        narPBLat[7] = 54.849796;
        narPBLon[7] = -8.423028;
        narPBLat[8] = 54.851411;
        narPBLon[8] = -8.420253;
        narPBLat[9] = 54.851107;
        narPBLon[9] = -8.425197;
        narPBLat[10] = 54.846516;
        narPBLon[10] = -8.426460;
        narPBLat[11] = 54.845951;
        narPBLon[11] = -8.427289;
        narPBLat[12] = 54.843520;
        narPBLon[12] = -8.429850;
        narPBLat[13] = 54.841635;
        narPBLon[13] = -8.434725;
        narPBLat[14] = 54.845096;
        narPBLon[14] = -8.427911;
        narPBLat[15] = 54.842703;
        narPBLon[15] = -8.434292;
        narPBLat[16] = 54.842014;
        narPBLon[16] = -8.435985;
        narPBLat[17] = 54.839658;
        narPBLon[17] = -8.438321;
        narPBLat[18] = 54.840193;
        narPBLon[18] = -8.442363;

        //narin & port front

        narPFLat[1] = 54.839141;
        narPFLon[1] = -8.439308;
        narPFLat[2] = 54.840750;
        narPFLon[2] = -8.432672;
        narPFLat[3] = 54.842001;
        narPFLon[3] = -8.431948;
        narPFLat[4] = 54.843454;
        narPFLon[4] = -8.425623;
        narPFLat[5] = 54.846255;
        narPFLon[5] = -8.424574;
        narPFLat[6] = 54.848381;
        narPFLon[6] = -8.423966;
        narPFLat[7] = 54.849600;
        narPFLon[7] = -8.423097;
        narPFLat[8] = 54.851235;
        narPFLon[8] = -8.420468;
        narPFLat[9] = 54.851127;
        narPFLon[9] = -8.424700;
        narPFLat[10] = 54.846735;
        narPFLon[10] = -8.426326;
        narPFLat[11] = 54.846230;
        narPFLon[11] = -8.427281;
        narPFLat[12] = 54.843552;
        narPFLon[12] = -8.429397;
        narPFLat[13] = 54.841739;
        narPFLon[13] = -8.434315;
        narPFLat[14] = 54.844982;
        narPFLon[14] = -8.428289;
        narPFLat[15] = 54.842798;
        narPFLon[15] = -8.433992;
        narPFLat[16] = 54.842137;
        narPFLon[16] = -8.435684;
        narPFLat[17] = 54.839856;
        narPFLon[17] = -8.437977;
        narPFLat[18] = 54.840205;
        narPFLon[18] = -8.441899;

        //donagal murvagh golf club centre
        narPLat[1] = 54.617290;
        narPLon[1] = -8.157472;
        narPLat[2] = 54.617821;
        narPLon[2] = -8.163099;
        narPLat[3] = 54.619678;
        narPLon[3] = -8.161690;
        narPLat[4] = 54.623699;
        narPLon[4] = -8.159473;
        narPLat[5] = 54.625175;
        narPLon[5] = -8.158196;
        narPLat[6] = 54.622609;
        narPLon[6] = -8.164786;
        narPLat[7] = 54.618322;
        narPLon[7] = -8.165276;
        narPLat[8] = 54.613423;
        narPLon[8] = -8.164903;
        narPLat[9] = 54.612012;
        narPLon[9] = -8.160031;
        narPLat[10] = 54.613948;
        narPLon[10] = -8.164245;
        narPLat[11] = 54.617475;
        narPLon[11] = -8.164890;
        narPLat[12] = 54.622390;
        narPLon[12] = -8.162536;
        narPLat[13] = 54.622975;
        narPLon[13] = -8.161131;
        narPLat[14] = 54.618086;
        narPLon[14] = -8.163695;
        narPLat[15] = 54.614474;
        narPLon[15] = -8.161514;
        narPLat[16] = 54.617060;
        narPLon[16] = -8.163085;
        narPLat[17] = 54.616960;
        narPLon[17] = -8.159439;
        narPLat[18] = 54.613331;
        narPLon[18] = -8.160408;

        //donegal murvagh back
        narPBLat[1] = 54.617375;
        narPBLon[1] = -8.157440;
        narPBLat[2] = 54.617797;
        narPBLon[2] = -8.163386;
        narPBLat[3] = 54.619786;
        narPBLon[3] = -8.161656;
        narPBLat[4] = 54.623807;
        narPBLon[4] = -8.159336;
        narPBLat[5] = 54.625238;
        narPBLon[5] = -8.157980;
        narPBLat[6] = 54.622532;
        narPBLon[6] = -8.164867;
        narPBLat[7] = 54.618216;
        narPBLon[7] = -8.165272;
        narPBLat[8] = 54.613262;
        narPBLon[8] = -8.164880;
        narPBLat[9] = 54.611881;
        narPBLon[9] = -8.159922;
        narPBLat[10] = 54.613911;
        narPBLon[10] = -8.164487;
        narPBLat[11] = 54.617543;
        narPBLon[11] = -8.164891;
        narPBLat[12] = 54.622493;
        narPBLon[12] = -8.162511;
        narPBLat[13] = 54.622966;
        narPBLon[13] = -8.160907;
        narPBLat[14] = 54.617981;
        narPBLon[14] = -8.163774;
        narPBLat[15] = 54.614332;
        narPBLon[15] = -8.161467;
        narPBLat[16] = 54.617165;
        narPBLon[16] = -8.163196;
        narPBLat[17] = 54.616961;
        narPBLon[17] = -8.159276;
        narPBLat[18] = 54.613221;
        narPBLon[18] = -8.160408;

        //donegal murvagh front

        narPFLat[1] = 54.617222;
        narPFLon[1] = -8.157499;
        narPFLat[2] = 54.617845;
        narPFLon[2] = -8.162851;
        narPFLat[3] = 54.619562;
        narPFLon[3] = -8.161737;
        narPFLat[4] = 54.623585;
        narPFLon[4] = -8.159578;
        narPFLat[5] = 54.625092;
        narPFLon[5] = -8.158419;
        narPFLat[6] = 54.622683;
        narPFLon[6] = -8.164671;
        narPFLat[7] = 54.618421;
        narPFLon[7] = -8.165327;
        narPFLat[8] = 54.613553;
        narPFLon[8] = -8.164922;
        narPFLat[9] = 54.612113;
        narPFLon[9] = -8.160216;
        narPFLat[10] = 54.613970;
        narPFLon[10] = -8.164003;
        narPFLat[11] = 54.617401;
        narPFLon[11] = -8.164871;
        narPFLat[12] = 54.622265;
        narPFLon[12] = -8.162560;
        narPFLat[13] = 54.622957;
        narPFLon[13] = -8.161388;
        narPFLat[14] = 54.618188;
        narPFLon[14] = -8.163606;
        narPFLat[15] = 54.614594;
        narPFLon[15] = -8.161570;
        narPFLat[16] = 54.616961;
        narPFLon[16] = -8.162977;
        narPFLat[17] = 54.616957;
        narPFLon[17] = -8.159607;
        narPFLat[18] = 54.613449;
        narPFLon[18] = -8.160414;

    }

}
