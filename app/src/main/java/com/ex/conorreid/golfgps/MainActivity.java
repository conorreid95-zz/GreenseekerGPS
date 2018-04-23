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
import android.widget.ProgressBar;
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
    double[] psBLat = new double[19];
    double[] psBLon = new double[19];
    double[] psFLat = new double[19];
    double[] psFLon = new double[19];

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

    double[] letLat = new double[19];
    double[] letLon = new double[19];
    double[] letBLat = new double[19];
    double[] letBLon = new double[19];
    double[] letFLat = new double[19];
    double[] letFLon = new double[19];

    double[] crLat = new double[19];
    double[] crLon = new double[19];
    double[] crBLat = new double[19];
    double[] crBLon = new double[19];
    double[] crFLat = new double[19];
    double[] crFLon = new double[19];


    double[] greLat = new double[19];
    double[] greLon = new double[19];
    double[] greBLat = new double[19];
    double[] greBLon = new double[19];
    double[] greFLat = new double[19];
    double[] greFLon = new double[19];

    double[] dunfLat = new double[19];
    double[] dunfLon = new double[19];
    double[] dunfBLat = new double[19];
    double[] dunfBLon = new double[19];
    double[] dunfFLat = new double[19];
    double[] dunfFLon = new double[19];


    private ProgressBar pSpinner;


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

        MobileAds.initialize(this, "ca-app-pub-7306277568792563~7353995311"); //app id from admob. ad is changed from test to live ad in the xml, not here.
        adView = findViewById(R.id.adView); //adView banner at bottom of screen

        sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        editor = sp.edit();


        pSpinner = (ProgressBar)findViewById(R.id.progressBar1);


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
                    weatherLink = "http://api.openweathermap.org/data/2.5/weather?id=2964751&APPID=d2f1c7fd747498a9246f9467457b722e";

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                }else if (selectedClub == 8) { //Letterkenny

                    logo.setImageResource(R.drawable.letterkenny_logo);

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                } else if (selectedClub == 9) { //Cruit island

                    logo.setImageResource(R.drawable.cruit_logo);

                    if (gpsOn) {
                        hideBack9(true);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                }else if (selectedClub == 10) { //Greencastle

                    logo.setImageResource(R.drawable.greencastle_logo);

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                } else if (selectedClub == 11) { //Dunfanaghy

                    logo.setImageResource(R.drawable.dunfanaghy_logo);

                    if (gpsOn) {
                        hideBack9(false);
                        hideFront9(false);
                        turnOnText.setVisibility(View.INVISIBLE);

                    } else {
                        hideBack9(true);
                        hideFront9(true);
                        turnOnText.setVisibility(View.VISIBLE);
                    }

                }
                else {
                    System.out.println("Error with spinner selection");
                }

                editor.putInt("default_course_int", position);
                editor.commit();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


// Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.

                pSpinner.setVisibility(View.GONE);

                makeUseOfNewLocation(location);
                if (firstLocationResult) { //if this is the first time the location was received

                    AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).setLocation(location).build(); //build adRequest using location object
//                    AdRequest adRequest = new AdRequest.Builder().setLocation(location).build();
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
                if (selectedClub == 1 || selectedClub == 9) {
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

        //change link based on previously saved default course, only called once
        if(defaultCourse == 0 ){ //buncrana area
           weatherLink = "http://api.openweathermap.org/data/2.5/weather?id=2654332&APPID=d2f1c7fd747498a9246f9467457b722e";
        }
        else if(defaultCourse == 4){ //portsalon
            weatherLink = "http://api.openweathermap.org/data/2.5/weather?lat=55.20&lon=-7.62&APPID=d2f1c7fd747498a9246f9467457b722e";
        }
        else if(defaultCourse == 6 || defaultCourse == 9){ //narin portnoo and cruit area
            weatherLink = "http://api.openweathermap.org/data/2.5/weather?lat=54.83&lon=-8.43&APPID=d2f1c7fd747498a9246f9467457b722e";
        }
        else if(defaultCourse == 7){ //donegal (murvagh)
            weatherLink = "http://api.openweathermap.org/data/2.5/weather?lat=54.61&lon=-8.15&APPID=d2f1c7fd747498a9246f9467457b722e";
        }
        else if(defaultCourse == 11){ //dunfanaghy
            weatherLink = "http://api.openweathermap.org/data/2.5/weather?lat=55.18&lon=-7.96&APPID=d2f1c7fd747498a9246f9467457b722e";
        }

        new JsonTask().execute(weatherLink); //download weather data using weatherLink string with JsonTask class
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
                        if (selectedClub == 1 || selectedClub == 9) {
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
            setUpBackFrontYardage(psBLat, psBLon, psFLat, psFLon, location);
        } else if (selectedClub == 5) {
            setUpYardage(balLat, balLon, location);
            setUpBackFrontYardage(balBLat, balBLon, balFLat, balFLon, location);
        } else if (selectedClub == 6) {
            setUpYardage(narPLat, narPLon, location);
            setUpBackFrontYardage(narPBLat, narPBLon, narPFLat, narPFLon, location);
        } else if (selectedClub == 7) {
            setUpYardage(donLat, donLon, location);
            setUpBackFrontYardage(donBLat, donBLon, donFLat, donFLon, location);
        } else if (selectedClub == 8) {
            setUpYardage(letLat, letLon, location);
            setUpBackFrontYardage(letBLat, letBLon, letFLat, letFLon, location);
        } else if (selectedClub == 9) {
            hideBack9(true);
            setUpYardage(crLat, crLon, location);
            setUpBackFrontYardage(crBLat, crBLon, crFLat, crFLon, location);
        }else if (selectedClub == 10) {
            setUpYardage(greLat, greLon, location);
            setUpBackFrontYardage(greBLat, greBLon, greFLat, greFLon, location);
        }else if (selectedClub == 11) {
            setUpYardage(dunfLat, dunfLon, location);
            setUpBackFrontYardage(dunfBLat, dunfBLon, dunfFLat, dunfFLon, location);
        }else {
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
            rightGrey.setVisibility(View.INVISIBLE);
        } else {
            choice = 1.0f;

            rightGrey.setVisibility(View.VISIBLE);
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

            leftGrey.setVisibility(View.INVISIBLE);
        } else {
            choice = 1.0f;

            leftGrey.setVisibility(View.VISIBLE);
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
            if (roundedYardArray[i] > 99999) {
                roundedYardArrayString[i] = ">99999";
            } else if (roundedYardArray[i] < 25) {
                roundedYardArrayString[i] = "<25";
            } else {
                roundedYardArrayString[i] = String.valueOf(roundedYardArray[i]);
            }
        }

//        for (int i = 1; i < 19; i++) { //for testing
//                roundedYardArrayString[i] = String.valueOf(roundedYardArray[i]);
//        }

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
            if (bRoundedArray[i] > 99999) {
                bStringsArray[i] = ".";
            } else if (bRoundedArray[i] < 25) {
                bStringsArray[i] = "<25";
            } else {
                bStringsArray[i] = String.valueOf(bRoundedArray[i]);
            }
        }
//        for (int i = 1; i < 19; i++) { //for testing
//                bStringsArray[i] = String.valueOf(bRoundedArray[i]);
//        }


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
            if (fRoundedArray[i] > 99999) {
                fStringArray[i] = ".";
            } else if (fRoundedArray[i] < 25) {
                fStringArray[i] = "<25";
            } else {
                fStringArray[i] = String.valueOf(fRoundedArray[i]);
            }
        }

//        for (int i = 1; i < 19; i++) { //for testing
//                fStringArray[i] = String.valueOf(fRoundedArray[i]);
//        }

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
        pSpinner.setVisibility(View.VISIBLE);
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

        nwLat[1] = 55.111015;
        nwLong[1] = -7.471009;
        nwLat[2] = 55.109358;
        nwLong[2] = -7.473637;
        nwLat[3] = 55.108698;
        nwLong[3] = -7.474221;
        nwLat[4] = 55.105963;
        nwLong[4] = -7.473642;
        nwLat[5] = 55.102943;
        nwLong[5] = -7.475699;
        nwLat[6] = 55.105514;
        nwLong[6] = -7.472897;
        nwLat[7] = 55.108705;
        nwLong[7] = -7.469917;
        nwLat[8] = 55.108116;
        nwLong[8] = -7.469673;
        nwLat[9] = 55.112465;
        nwLong[9] = -7.465933;
        nwLat[10] = 55.114393;
        nwLong[10] = -7.463127;
        nwLat[11] = 55.112861;
        nwLong[11] = -7.46660;
        nwLat[12] = 55.109008;
        nwLong[12] = -7.469507;
        nwLat[13] = 55.110180;
        nwLong[13] = -7.469705;
        nwLat[14] = 55.108449;
        nwLong[14] = -7.472329;
        nwLat[15] = 55.106288;
        nwLong[15] = -7.472039;
        nwLat[16] = 55.105430;
        nwLong[16] = -7.472345;
        nwLat[17] = 55.109005;
        nwLong[17] = -7.473647;
        nwLat[18] = 55.111462;
        nwLong[18] = -7.468776;

        //north west back
        nwBLat[1] = 55.110932;
        nwBLong[1] = -7.471214;
        nwBLat[2] = 55.109226;
        nwBLong[2] = -7.473694;
        nwBLat[3] = 55.108595;
        nwBLong[3] = -7.474213;
        nwBLat[4] = 55.105822;
        nwBLong[4] = -7.473649;
        nwBLat[5] = 55.102835;
        nwBLong[5] = -7.475773;
        nwBLat[6] = 55.105655;
        nwBLong[6] = -7.472803;
        nwBLat[7] = 55.108856;
        nwBLong[7] = -7.469936;
        nwBLat[8] = 55.108038;
        nwBLong[8] = -7.469685;
        nwBLat[9] = 55.112567;
        nwBLong[9] = -7.465788;
        nwBLat[10] = 55.114485;
        nwBLong[10] = -7.463024;
        nwBLat[11] = 55.112766;
        nwBLong[11] = -7.466826;
        nwBLat[12] = 55.108903;
        nwBLong[12] = -7.469530;
        nwBLat[13] = 55.110279;
        nwBLong[13] = -7.469618;
        nwBLat[14] = 55.108355;
        nwBLong[14] = -7.472380;
        nwBLat[15] = 55.106176;
        nwBLong[15] = -7.472062;
        nwBLat[16] = 55.105323;
        nwBLong[16] = -7.472396;
        nwBLat[17] = 55.109097;
        nwBLong[17] = -7.473654;
        nwBLat[18] = 55.111532;
        nwBLong[18] = -7.468595;

        //north west front
        nwFLat[1] = 55.111086;
        nwFLong[1] = -7.470844;
        nwFLat[2] = 55.109471;
        nwFLong[2] = -7.473578;
        nwFLat[3] = 55.108790;
        nwFLong[3] = -7.474237;
        nwFLat[4] = 55.106083;
        nwFLong[4] = -7.473642;
        nwFLat[5] = 55.103077;
        nwFLong[5] = -7.475595;
        nwFLat[6] = 55.105379;
        nwFLong[6] = -7.472959;
        nwFLat[7] = 55.108553;
        nwFLong[7] = -7.469992;
        nwFLat[8] = 55.108220;
        nwFLong[8] = -7.469609;
        nwFLat[9] = 55.112369;
        nwFLong[9] = -7.466082;
        nwFLat[10] = 55.114294;
        nwFLong[10] = -7.463272;
        nwFLat[11] = 55.112983;
        nwFLong[11] = -7.466385;
        nwFLat[12] = 55.109105;
        nwFLong[12] = -7.469504;
        nwFLat[13] = 55.110062;
        nwFLong[13] = -7.469829;
        nwFLat[14] = 55.108570;
        nwFLong[14] = -7.472248;
        nwFLat[15] = 55.106415;
        nwFLong[15] = -7.472016;
        nwFLat[16] = 55.105538;
        nwFLong[16] = -7.472299;
        nwFLat[17] = 55.108898;
        nwFLong[17] = -7.473627;
        nwFLat[18] = 55.111374;
        nwFLong[18] = -7.468942;

        //Buncrana co ordinates (done accurately, each of centre, front, and back)
        bcLat[1] = 55.124638;
        bcLong[1] = -7.457352;
        bcLat[2] = 55.123258;
        bcLong[2] = -7.456777;
        bcLat[3] = 55.124591;
        bcLong[3] = -7.457743;
        bcLat[4] = 55.122516;
        bcLong[4] = -7.457251;
        bcLat[5] = 55.127245;
        bcLong[5] = -7.460087;
        bcLat[6] = 55.127269;
        bcLong[6] = -7.459454;
        bcLat[7] = 55.124978;
        bcLong[7] = -7.457967;
        bcLat[8] = 55.127444;
        bcLong[8] = -7.458677;
        bcLat[9] = 55.127125;
        bcLong[9] = -7.457920;
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
        bcBLat[1] = 55.124523;
        bcBLong[1] = -7.457399;
        bcBLat[2] = 55.123174;
        bcBLong[2] = -7.456792;
        bcBLat[3] = 55.124692;
        bcBLong[3] = -7.457731;
        bcBLat[4] = 55.122425;
        bcBLong[4] = -7.457196;
        bcBLat[5] = 55.127313;
        bcBLong[5] = -7.460071;
        bcBLat[6] = 55.127181;
        bcBLong[6] = -7.459420;
        bcBLat[7] = 55.124891;
        bcBLong[7] = -7.457944;
        bcBLat[8] = 55.127520;
        bcBLong[8] = -7.458656;
        bcBLat[9] = 55.127002;
        bcBLong[9] = -7.457888;
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
        bcFLat[1] = 55.124734;
        bcFLong[1] = -7.457344;
        bcFLat[2] = 55.123342;
        bcFLong[2] = -7.456744;
        bcFLat[3] = 55.124468;
        bcFLong[3] = -7.457759;
        bcFLat[4] = 55.122621;
        bcFLong[4] = -7.457320;
        bcFLat[5] = 55.127175;
        bcFLong[5] = -7.460104;
        bcFLat[6] = 55.127336;
        bcFLong[6] = -7.459504;
        bcFLat[7] = 55.125047;
        bcFLong[7] = -7.457995;
        bcFLat[8] = 55.127369;
        bcFLong[8] = -7.458703;
        bcFLat[9] = 55.127256;
        bcFLong[9] = -7.457972;
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
        psLat[1] = 55.205044;
        psLon[1] = -7.625929;
        psLat[2] = 55.201678;
        psLon[2] = -7.626443;
        psLat[3] = 55.199743;
        psLon[3] = -7.624631;
        psLat[4] = 55.196229;
        psLon[4] = -7.621958;
        psLat[5] = 55.198249;
        psLon[5] = -7.622228;
        psLat[6] = 55.195505;
        psLon[6] = -7.619606;
        psLat[7] = 55.192268;
        psLon[7] = -7.618259;
        psLat[8] = 55.195943;
        psLon[8] = -7.622118;
        psLat[9] = 55.199396;
        psLon[9] = -7.624713;
        psLat[10] = 55.197555;
        psLon[10] = -7.625048;
        psLat[11] = 55.193354;
        psLon[11] = -7.620586;
        psLat[12] = 55.194899;
        psLon[12] = -7.623527;
        psLat[13] = 55.197128;
        psLon[13] = -7.625876;
        psLat[14] = 55.200106;
        psLon[14] = -7.625432;
        psLat[15] = 55.199397;
        psLon[15] = -7.626995;
        psLat[16] = 55.201720;
        psLon[16] = -7.629114;
        psLat[17] = 55.205446;
        psLon[17] = -7.627148;
        psLat[18] = 55.207573;
        psLon[18] = -7.623183;

        //Portsalon back
        psBLat[1] = 55.204956;
        psBLon[1] = -7.626086;
        psBLat[2] = 55.201526;
        psBLon[2] = -7.626429;
        psBLat[3] = 55.199566;
        psBLon[3] = -7.624614;
        psBLat[4] = 55.196100;
        psBLon[4] = -7.622027;
        psBLat[5] = 55.198377;
        psBLon[5] = -7.622225;
        psBLat[6] = 55.195360;
        psBLon[6] = -7.619487;
        psBLat[7] = 55.192191;
        psBLon[7] = -7.618286;
        psBLat[8] = 55.196033;
        psBLon[8] = -7.622208;
        psBLat[9] = 55.199543;
        psBLon[9] = -7.624624;
        psBLat[10] = 55.197432;
        psBLon[10] = -7.625020;
        psBLat[11] = 55.193271;
        psBLon[11] = -7.620476;
        psBLat[12] = 55.194982;
        psBLon[12] = -7.623704;
        psBLat[13] = 55.197203;
        psBLon[13] = -7.625940;
        psBLat[14] = 55.200214;
        psBLon[14] = -7.625375;
        psBLat[15] = 55.199320;
        psBLon[15] = -7.627060;
        psBLat[16] = 55.201853;
        psBLon[16] = -7.629135;
        psBLat[17] = 55.205547;
        psBLon[17] = -7.627144;
        psBLat[18] = 55.207613;
        psBLon[18] = -7.623004;

        //Portsalon front
        psFLat[1] = 55.205118;
        psFLon[1] = -7.625869;
        psFLat[2] = 55.201820;
        psFLon[2] = -7.626501;
        psFLat[3] = 55.199897;
        psFLon[3] = -7.624709;
        psFLat[4] = 55.196335;
        psFLon[4] = -7.622017;
        psFLat[5] = 55.198120;
        psFLon[5] = -7.622196;
        psFLat[6] = 55.195627;
        psFLon[6] = -7.619711;
        psFLat[7] = 55.192349;
        psFLon[7] = -7.618200;
        psFLat[8] = 55.195846;
        psFLon[8] = -7.622053;
        psFLat[9] = 55.199266;
        psFLon[9] = -7.624737;
        psFLat[10] = 55.197663;
        psFLon[10] = -7.625082;
        psFLat[11] = 55.193441;
        psFLon[11] = -7.620714;
        psFLat[12] = 55.194830;
        psFLon[12] = -7.623361;
        psFLat[13] = 55.197058;
        psFLon[13] = -7.625795;
        psFLat[14] = 55.199999;
        psFLon[14] = -7.625480;
        psFLat[15] = 55.199453;
        psFLon[15] = -7.626900;
        psFLat[16] = 55.201604;
        psFLon[16] = -7.629075;
        psFLat[17] = 55.205362;
        psFLon[17] = -7.627143;
        psFLat[18] = 55.207545;
        psFLon[18] = -7.623340;

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
        donLat[1] = 54.617290;
        donLon[1] = -8.157472;
        donLat[2] = 54.617821;
        donLon[2] = -8.163099;
        donLat[3] = 54.619678;
        donLon[3] = -8.161690;
        donLat[4] = 54.623699;
        donLon[4] = -8.159473;
        donLat[5] = 54.625175;
        donLon[5] = -8.158196;
        donLat[6] = 54.622609;
        donLon[6] = -8.164786;
        donLat[7] = 54.618322;
        donLon[7] = -8.165276;
        donLat[8] = 54.613423;
        donLon[8] = -8.164903;
        donLat[9] = 54.612012;
        donLon[9] = -8.160031;
        donLat[10] = 54.613948;
        donLon[10] = -8.164245;
        donLat[11] = 54.617475;
        donLon[11] = -8.164890;
        donLat[12] = 54.622390;
        donLon[12] = -8.162536;
        donLat[13] = 54.622975;
        donLon[13] = -8.161131;
        donLat[14] = 54.618086;
        donLon[14] = -8.163695;
        donLat[15] = 54.614474;
        donLon[15] = -8.161514;
        donLat[16] = 54.617060;
        donLon[16] = -8.163085;
        donLat[17] = 54.616960;
        donLon[17] = -8.159439;
        donLat[18] = 54.613331;
        donLon[18] = -8.160408;

        //donegal murvagh back
        donBLat[1] = 54.617375;
        donBLon[1] = -8.157440;
        donBLat[2] = 54.617797;
        donBLon[2] = -8.163386;
        donBLat[3] = 54.619786;
        donBLon[3] = -8.161656;
        donBLat[4] = 54.623807;
        donBLon[4] = -8.159336;
        donBLat[5] = 54.625238;
        donBLon[5] = -8.157980;
        donBLat[6] = 54.622532;
        donBLon[6] = -8.164867;
        donBLat[7] = 54.618216;
        donBLon[7] = -8.165272;
        donBLat[8] = 54.613262;
        donBLon[8] = -8.164880;
        donBLat[9] = 54.611881;
        donBLon[9] = -8.159922;
        donBLat[10] = 54.613911;
        donBLon[10] = -8.164487;
        donBLat[11] = 54.617543;
        donBLon[11] = -8.164891;
        donBLat[12] = 54.622493;
        donBLon[12] = -8.162511;
        donBLat[13] = 54.622966;
        donBLon[13] = -8.160907;
        donBLat[14] = 54.617981;
        donBLon[14] = -8.163774;
        donBLat[15] = 54.614332;
        donBLon[15] = -8.161467;
        donBLat[16] = 54.617165;
        donBLon[16] = -8.163196;
        donBLat[17] = 54.616961;
        donBLon[17] = -8.159276;
        donBLat[18] = 54.613221;
        donBLon[18] = -8.160408;

        //donegal murvagh front

        donFLat[1] = 54.617222;
        donFLon[1] = -8.157499;
        donFLat[2] = 54.617845;
        donFLon[2] = -8.162851;
        donFLat[3] = 54.619561;
        donFLon[3] = -8.161735;
        donFLat[4] = 54.623585;
        donFLon[4] = -8.159578;
        donFLat[5] = 54.625092;
        donFLon[5] = -8.158419;
        donFLat[6] = 54.622683;
        donFLon[6] = -8.164671;
        donFLat[7] = 54.618421;
        donFLon[7] = -8.165327;
        donFLat[8] = 54.613553;
        donFLon[8] = -8.164922;
        donFLat[9] = 54.612113;
        donFLon[9] = -8.160216;
        donFLat[10] = 54.613970;
        donFLon[10] = -8.164003;
        donFLat[11] = 54.617401;
        donFLon[11] = -8.164871;
        donFLat[12] = 54.622265;
        donFLon[12] = -8.162560;
        donFLat[13] = 54.622957;
        donFLon[13] = -8.161388;
        donFLat[14] = 54.618188;
        donFLon[14] = -8.163606;
        donFLat[15] = 54.614594;
        donFLon[15] = -8.161570;
        donFLat[16] = 54.616961;
        donFLon[16] = -8.162977;
        donFLat[17] = 54.616957;
        donFLon[17] = -8.159607;
        donFLat[18] = 54.613449;
        donFLon[18] = -8.160414;

        //letterkenny golf club centre
        letLat[1] = 54.961716;
        letLon[1] = -7.681945;
        letLat[2] = 54.962275;
        letLon[2] = -7.675120;
        letLat[3] = 54.963172;
        letLon[3] = -7.670014;
        letLat[4] = 54.964699;
        letLon[4] = -7.666250;
        letLat[5] = 54.965926;
        letLon[5] = -7.664026;
        letLat[6] = 54.964293;
        letLon[6] = -7.669554;
        letLat[7] = 54.962774;
        letLon[7] = -7.674951;
        letLat[8] = 54.963834;
        letLon[8] = -7.673911;
        letLat[9] = 54.963144;
        letLon[9] = -7.679800;
        letLat[10] = 54.964466;
        letLon[10] = -7.675641;
        letLat[11] = 54.965497;
        letLon[11] = -7.681004;
        letLat[12] = 54.966633;
        letLon[12] = -7.685375;
        letLat[13] = 54.964974;
        letLon[13] = -7.682355;
        letLat[14] = 54.964534;
        letLon[14] = -7.688709;
        letLat[15] = 54.965467;
        letLon[15] = -7.684875;
        letLat[16] = 54.966247;
        letLon[16] = -7.686040;
        letLat[17] = 54.964864;
        letLon[17] = -7.690912;
        letLat[18] = 54.962851;
        letLon[18] = -7.685225;

        //letterkenny back
        letBLat[1] = 54.961628;
        letBLon[1] = -7.681970;
        letBLat[2] = 54.962268;
        letBLon[2] = -7.674880;
        letBLat[3] = 54.963194;
        letBLon[3] = -7.669848;
        letBLat[4] = 54.964772;
        letBLon[4] = -7.666118;
        letBLat[5] = 54.965972;
        letBLon[5] = -7.663899;
        letBLat[6] = 54.964244;
        letBLon[6] = -7.669690;
        letBLat[7] = 54.962757;
        letBLon[7] = -7.675155;
        letBLat[8] = 54.963918;
        letBLon[8] = -7.673736;
        letBLat[9] = 54.963108;
        letBLon[9] = -7.679992;
        letBLat[10] = 54.964504;
        letBLon[10] = -7.675502;
        letBLat[11] = 54.965553;
        letBLon[11] = -7.681198;
        letBLat[12] = 54.966683;
        letBLon[12] = -7.685509;
        letBLat[13] = 54.964856;
        letBLon[13] = -7.682320;
        letBLat[14] = 54.964535;
        letBLon[14] = -7.688839;
        letBLat[15] = 54.965483;
        letBLon[15] = -7.684674;
        letBLat[16] = 54.966314;
        letBLon[16] = -7.686157;
        letBLat[17] = 54.964766;
        letBLon[17] = -7.691100;
        letBLat[18] = 54.962781;
        letBLon[18] = -7.685085;

        //letterkenny front

        letFLat[1] = 54.961800;
        letFLon[1] = -7.681903;
        letFLat[2] = 54.962313;
        letFLon[2] = -7.675388;
        letFLat[3] = 54.963125;
        letFLon[3] = -7.670196;
        letFLat[4] = 54.964625;
        letFLon[4] = -7.666386;
        letFLat[5] = 54.965891;
        letFLon[5] = -7.664143;
        letFLat[6] = 54.964363;
        letFLon[6] = -7.669417;
        letFLat[7] = 54.962743;
        letFLon[7] = -7.674707;
        letFLat[8] = 54.963771;
        letFLon[8] = -7.674119;
        letFLat[9] = 54.963182;
        letFLon[9] = -7.679627;
        letFLat[10] = 54.964438;
        letFLon[10] = -7.675813;
        letFLat[11] = 54.965457;
        letFLon[11] = -7.680790;
        letFLat[12] = 54.966584;
        letFLon[12] = -7.685233;
        letFLat[13] = 54.965083;
        letFLon[13] = -7.682502;
        letFLat[14] = 54.964527;
        letFLon[14] = -7.688599;
        letFLat[15] = 54.965447;
        letFLon[15] = -7.685069;
        letFLat[16] = 54.966200;
        letFLon[16] = -7.685938;
        letFLat[17] = 54.964949;
        letFLon[17] = -7.690745;
        letFLat[18] = 54.962917;
        letFLon[18] = -7.685390;

        //cruit co ordinates centre
        crLat[1] = 55.046755;
        crLon[1] = -8.425536;
        crLat[2] = 55.049255;
        crLon[2] = -8.427241;
        crLat[3] = 55.049826;
        crLon[3] = -8.423943;
        crLat[4] = 55.050224;
        crLon[4] = -8.428178;
        crLat[5] = 55.051323;
        crLon[5] = -8.427431;
        crLat[6] = 55.052169;
        crLon[6] = -8.428036;
        crLat[7] = 55.049467;
        crLon[7] = -8.429578;
        crLat[8] = 55.048084;
        crLon[8] = -8.427344;
        crLat[9] = 55.047790;
        crLon[9] = -8.430896;
        crLat[10] = 0;
        crLon[10] = 0;
        crLat[11] = 0;
        crLon[11] = 0;
        crLat[12] = 0;
        crLon[12] = 0;
        crLat[13] = 0;
        crLon[13] = 0;
        crLat[14] = 0;
        crLon[14] = 0;
        crLat[15] = 0;
        crLon[15] = 0;
        crLat[16] = 0;
        crLon[16] = 0;
        crLat[17] = 0;
        crLon[17] = 0;
        crLat[18] = 0;
        crLon[18] = 0;

        //cruit back
        crBLat[1] = 55.046689;
        crBLon[1] = -8.425359;
        crBLat[2] = 55.049342;
        crBLon[2] = -8.427367;
        crBLat[3] = 55.049842;
        crBLon[3] = -8.423764;
        crBLat[4] = 55.050194;
        crBLon[4] = -8.428464;
        crBLat[5] = 55.051384;
        crBLon[5] = -8.427292;
        crBLat[6] = 55.052219;
        crBLon[6] = -8.428138;
        crBLat[7] = 55.049375;
        crBLon[7] = -8.429594;
        crBLat[8] = 55.047992;
        crBLon[8] = -8.427239;
        crBLat[9] = 55.047788;
        crBLon[9] = -8.430892;
        crBLat[10] = 0;
        crBLon[10] = 0;
        crBLat[11] = 0;
        crBLon[11] = 0;
        crBLat[12] = 0;
        crBLon[12] = 0;
        crBLat[13] = 0;
        crBLon[13] = 0;
        crBLat[14] = 0;
        crBLon[14] = 0;
        crBLat[15] = 0;
        crBLon[15] = 0;
        crBLat[16] = 0;
        crBLon[16] = 0;
        crBLat[17] = 0;
        crBLon[17] = 0;
        crBLat[18] = 0;
        crBLon[18] = 0;

        //cruit front
        crFLat[1] = 55.046834;
        crFLon[1] = -8.425722;
        crFLat[2] = 55.049140;
        crFLon[2] = -8.427131;
        crFLat[3] = 55.049816;
        crFLon[3] = -8.424096;
        crFLat[4] = 55.050248;
        crFLon[4] = -8.427918;
        crFLat[5] = 55.051281;
        crFLon[5] = -8.427591;
        crFLat[6] = 55.052124;
        crFLon[6] = -8.427926;
        crFLat[7] = 55.049560;
        crFLon[7] = -8.429565;
        crFLat[8] = 55.048155;
        crFLon[8] = -8.427458;
        crFLat[9] = 55.048005;
        crFLon[9] = -8.430676;
        crFLat[10] = 0;
        crFLon[10] = 0;
        crFLat[11] = 0;
        crFLon[11] = 0;
        crFLat[12] = 0;
        crFLon[12] = 0;
        crFLat[13] = 0;
        crFLon[13] = 0;
        crFLat[14] = 0;
        crFLon[14] = 0;
        crFLat[15] = 0;
        crFLon[15] = 0;
        crFLat[16] = 0;
        crFLon[16] = 0;
        crFLat[17] = 0;
        crFLon[17] = 0;
        crFLat[18] = 0;
        crFLon[18] = 0;

        //greencastle co ordinates centre
        greLat[1] = 55.212043;
        greLon[1] = -6.957816;
        greLat[2] = 55.210050;
        greLon[2] = -6.963720;
        greLat[3] = 55.209613;
        greLon[3] = -6.966247;
        greLat[4] = 55.210684;
        greLon[4] = -6.959379;
        greLat[5] = 55.209074;
        greLon[5] = -6.962325;
        greLat[6] = 55.207567;
        greLon[6] = -6.963944;
        greLat[7] = 55.208696;
        greLon[7] = -6.962140;
        greLat[8] = 55.208455;
        greLon[8] = -6.967856;
        greLat[9] = 55.209204;
        greLon[9] = -6.966436;
        greLat[10] = 55.211915;
        greLon[10] = -6.958861;
        greLat[11] = 55.210034;
        greLon[11] = -6.958168;
        greLat[12] = 55.209817;
        greLon[12] = -6.952496;
        greLat[13] = 55.212796;
        greLon[13] = -6.955761;
        greLat[14] = 55.210462;
        greLon[14] = -6.952382;
        greLat[15] = 55.211201;
        greLon[15] = -6.952215;
        greLat[16] = 55.213516;
        greLon[16] = -6.954786;
        greLat[17] = 55.212242;
        greLon[17] = -6.958285;
        greLat[18] = 55.210597;
        greLon[18] = -6.955928;

        //greencastle back
        greBLat[1] = 55.212007;
        greBLon[1] = -6.957952;
        greBLat[2] = 55.209963;
        greBLon[2] = -6.963894;
        greBLat[3] = 55.209567;
        greBLon[3] = -6.966418;
        greBLat[4] = 55.210754;
        greBLon[4] = -6.959141;
        greBLat[5] = 55.209003;
        greBLon[5] = -6.962420;
        greBLat[6] = 55.207509;
        greBLon[6] = -6.964077;
        greBLat[7] = 55.208784;
        greBLon[7] = -6.961979;
        greBLat[8] = 55.208419;
        greBLon[8] = -6.968029;
        greBLat[9] = 55.209240;
        greBLon[9] = -6.966251;
        greBLat[10] = 55.211966;
        greBLon[10] = -6.958661;
        greBLat[11] = 55.209954;
        greBLon[11] = -6.958161;
        greBLat[12] = 55.209794;
        greBLon[12] = -6.952315;
        greBLat[13] = 55.212892;
        greBLon[13] = -6.955810;
        greBLat[14] = 55.210350;
        greBLon[14] = -6.952255;
        greBLat[15] = 55.211270;
        greBLon[15] = -6.952270;
        greBLat[16] = 55.213605;
        greBLon[16] = -6.954875;
        greBLat[17] = 55.212189;
        greBLon[17] = -6.958425;
        greBLat[18] = 55.210501;
        greBLon[18] = -6.955681;

        //greencastle front
        greFLat[1] = 55.212078;
        greFLon[1] = -6.957676;
        greFLat[2] = 55.210146;
        greFLon[2] = -6.963562;
        greFLat[3] = 55.209645;
        greFLon[3] = -6.966072;
        greFLat[4] = 55.210602;
        greFLon[4] = -6.959618;
        greFLat[5] = 55.209162;
        greFLon[5] = -6.962199;
        greFLat[6] = 55.207620;
        greFLon[6] = -6.963861;
        greFLat[7] = 55.208607;
        greFLon[7] = -6.962332;
        greFLat[8] = 55.208481;
        greFLon[8] = -6.967695;
        greFLat[9] = 55.209166;
        greFLon[9] = -6.966609;
        greFLat[10] = 55.211858;
        greFLon[10] = -6.959064;
        greFLat[11] = 55.210112;
        greFLon[11] = -6.958173;
        greFLat[12] = 55.209814;
        greFLon[12] = -6.952700;
        greFLat[13] = 55.212704;
        greFLon[13] = -6.955730;
        greFLat[14] = 55.210598;
        greFLon[14] = -6.952531;
        greFLat[15] = 55.211137;
        greFLon[15] = -6.952156;
        greFLat[16] = 55.213442;
        greFLon[16] = -6.954708;
        greFLat[17] = 55.212289;
        greFLon[17] = -6.958165;
        greFLat[18] = 55.210712;
        greFLon[18] = -6.956131;

        //dunfanaghy co ordinates centre
        dunfLat[1] = 55.182576;
        dunfLon[1] = -7.955691;
        dunfLat[2] = 55.181712;
        dunfLon[2] = -7.955181;
        dunfLat[3] = 55.179695;
        dunfLon[3] = -7.950537;
        dunfLat[4] = 55.179574;
        dunfLon[4] = -7.945781;
        dunfLat[5] = 55.179581;
        dunfLon[5] = -7.942490;
        dunfLat[6] = 55.180781;
        dunfLon[6] = -7.938326;
        dunfLat[7] = 55.179841;
        dunfLon[7] = -7.941847;
        dunfLat[8] = 55.181878;
        dunfLon[8] = -7.940550;
        dunfLat[9] = 55.181265;
        dunfLon[9] = -7.941955;
        dunfLat[10] = 55.179501;
        dunfLon[10] = -7.945252;
        dunfLat[11] = 55.179807;
        dunfLon[11] = -7.949440;
        dunfLat[12] = 55.181858;
        dunfLon[12] = -7.954324;
        dunfLat[13] = 55.182952;
        dunfLon[13] = -7.954532;
        dunfLat[14] = 55.183259;
        dunfLon[14] = -7.958474;
        dunfLat[15] = 55.183690;
        dunfLon[15] = -7.954230;
        dunfLat[16] = 55.184893;
        dunfLon[16] = -7.961606;
        dunfLat[17] = 55.184468;
        dunfLon[17] = -7.964102;
        dunfLat[18] = 55.183373;
        dunfLon[18] = -7.959603;

        //dunfanaghy back
        dunfBLat[1] = 55.182543;
        dunfBLon[1] = -7.955514;
        dunfBLat[2] = 55.181615;
        dunfBLon[2] = -7.955091;
        dunfBLat[3] = 55.179615;
        dunfBLon[3] = -7.950298;
        dunfBLat[4] = 55.179610;
        dunfBLon[4] = -7.945590;
        dunfBLat[5] = 55.179659;
        dunfBLon[5] = -7.942348;
        dunfBLat[6] = 55.180923;
        dunfBLon[6] = -7.938137;
        dunfBLat[7] = 55.179766;
        dunfBLon[7] = -7.941991;
        dunfBLat[8] = 55.181975;
        dunfBLon[8] = -7.940442;
        dunfBLat[9] = 55.181163;
        dunfBLon[9] = -7.942056;
        dunfBLat[10] = 55.179448;
        dunfBLon[10] = -7.945426;
        dunfBLat[11] = 55.179807;
        dunfBLon[11] = -7.949719;
        dunfBLat[12] = 55.181929;
        dunfBLon[12] = -7.954462;
        dunfBLat[13] = 55.183035;
        dunfBLon[13] = -7.954621;
        dunfBLat[14] = 55.183222;
        dunfBLon[14] = -7.958629;
        dunfBLat[15] = 55.183640;
        dunfBLon[15] = -7.954053;
        dunfBLat[16] = 55.184987;
        dunfBLon[16] = -7.961676;
        dunfBLat[17] = 55.184427;
        dunfBLon[17] = -7.964314;
        dunfBLat[18] = 55.183294;
        dunfBLon[18] = -7.959443;

        //dunfanaghy front
        dunfFLat[1] = 55.182605;
        dunfFLon[1] = -7.955872;
        dunfFLat[2] = 55.181764;
        dunfFLon[2] = -7.955313;
        dunfFLat[3] = 55.179776;
        dunfFLon[3] = -7.950705;
        dunfFLat[4] = 55.179545;
        dunfFLon[4] = -7.945965;
        dunfFLat[5] = 55.179508;
        dunfFLon[5] = -7.942622;
        dunfFLat[6] = 55.180670;
        dunfFLon[6] = -7.938495;
        dunfFLat[7] = 55.179901;
        dunfFLon[7] = -7.941716;
        dunfFLat[8] = 55.181779;
        dunfFLon[8] = -7.940672;
        dunfFLat[9] = 55.181351;
        dunfFLon[9] = -7.941774;
        dunfFLat[10] = 55.179554;
        dunfFLon[10] = -7.945089;
        dunfFLat[11] = 55.179816;
        dunfFLon[11] = -7.949196;
        dunfFLat[12] = 55.181787;
        dunfFLon[12] = -7.954185;
        dunfFLat[13] = 55.182881;
        dunfFLon[13] = -7.954437;
        dunfFLat[14] = 55.183293;
        dunfFLon[14] = -7.958288;
        dunfFLat[15] = 55.183719;
        dunfFLon[15] = -7.954425;
        dunfFLat[16] = 55.184815;
        dunfFLon[16] = -7.961529;
        dunfFLat[17] = 55.184502;
        dunfFLon[17] = -7.963916;
        dunfFLat[18] = 55.183435;
        dunfFLon[18] = -7.959789;

    }

}
