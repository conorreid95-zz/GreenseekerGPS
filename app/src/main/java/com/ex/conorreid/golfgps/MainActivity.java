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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    Button btnHit;
    TextView txtJson;
    ProgressDialog pd;
    ImageView logo;
    ImageView arrow;
    TextView num10;
    TextView num11;
    TextView num12;
    TextView num13;
    TextView num14;
    TextView num15;
    TextView num16;
    TextView num17;
    TextView num18;

    TextView latText;
    TextView lonText;


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
    TextView statusText;

    TextView northLetter;
    TextView windSpeedText;
    TextView windDirText;

    LocationManager locationManager;
    LocationListener locationListener;

    boolean gpsPossible = false;
    boolean gpsPermission = false;

    double[] nwLat = new double[19];
    double[] nwLong = new double[19];
    double[] bcLat = new double[19];
    double[] bcLong = new double[19];
    double[] bloLat = new double[19];
    double[] bloLon = new double[19];
    int selectedClub = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        statusText = findViewById(R.id.gpsStatusText);

        windSpeedText = findViewById(R.id.textView53);
        windDirText = findViewById(R.id.textView54);

        latText = findViewById(R.id.textView55);
        lonText = findViewById(R.id.textView56);

        num10 = findViewById(R.id.num10);
        num11 = findViewById(R.id.num11);
        num12 = findViewById(R.id.num12);
        num13 = findViewById(R.id.num13);
        num14 = findViewById(R.id.num14);
        num15 = findViewById(R.id.num15);
        num16 = findViewById(R.id.num16);
        num17 = findViewById(R.id.num17);
        num18 = findViewById(R.id.num18);

        northLetter = findViewById(R.id.textView2);
        logo = findViewById(R.id.logoView);
        arrow = findViewById(R.id.arrowImage);
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
                selectedClub = position;
                System.out.println("Spinner selected at position: " + position);
                if (selectedClub == 0) {
                    hideBack9(false);
                    logo.setImageResource(R.drawable.north_west_logo);
                } else if (selectedClub == 1){
                    hideBack9(true);
                    logo.setImageResource(R.drawable.buncrana_gc_logo);
                }
                else if (selectedClub == 2){
                    hideBack9(false);
                    logo.setImageResource(R.drawable.bl_logo);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnHit = (Button) findViewById(R.id.btnHit);
        txtJson = (TextView) findViewById(R.id.tvJsonItem);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JsonTask().execute("http://api.openweathermap.org/data/2.5/weather?id=2654332&APPID=d2f1c7fd747498a9246f9467457b722e");
            }
        });


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
                statusText.setText("GPS Active");
                statusText.setTextColor(Color.parseColor("#00C853"));
            }

            public void onProviderDisabled(String provider) {
                System.out.println("Provider: " + provider + "Disabled");
                statusText.setText("GPS Off");
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
        bloLat[1] = 55.295716;
        bloLon[1] = -7.370336;
        bloLat[2] = 55.299634;
        bloLon[2] = -7.369055;
        bloLat[3] = 55.300580;
        bloLon[3] = -7.372728;
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
        bloLat[10] = 0;
        bloLon[10] = 0;
        bloLat[11] = 0;
        bloLon[11] = 0;
        bloLat[12] = 0;
        bloLon[12] = 0;
        bloLat[13] = 0;
        bloLon[13] = 0;
        bloLat[14] = 0;
        bloLon[14] = 0;
        bloLat[15] = 0;
        bloLon[15] = 0;
        bloLat[16] = 0;
        bloLon[16] = 0;
        bloLat[17] = 0;
        bloLon[17] = 0;
        bloLat[18] = 0;
        bloLon[18] = 0;

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

        latText.setText(String.valueOf(latitude));
        lonText.setText(String.valueOf(longitude));

        if(selectedClub == 0) {
            setUpYardage(nwLat, nwLong, location);
        }
        else if(selectedClub == 1){
            setUpYardage(bcLat, bcLong, location);
        }
        else if (selectedClub == 2){
            setUpYardage(bloLat, bloLon, location);
        }
        else {
            System.out.println("Error with club selection and yardage calculation");
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

    public boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null) return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null) return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
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
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()) {
                pd.dismiss();
            }
            txtJson.setText(result);

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
                } catch (JSONException e){
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
                arrow.setRotation(rotation-90f);
                arrow.setVisibility(View.VISIBLE);
                windSpeedText.setText("Wind Speed: " + wSpeed + " km/h");
                windSpeedText.setVisibility(View.VISIBLE);
                windDirText.setText("Wind Direction: " + wDir + "°");
                windDirText.setVisibility(View.VISIBLE);

                northLetter.setVisibility(View.VISIBLE);

                btnHit.setClickable(false);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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

    public void setUpYardage(double[] arrayLat, double[] arrayLon, Location location){


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

        float yardsFirst = distance1[0] * 1.093f;
        float yardsSecond = distance2[0] * 1.093f;
        float yardsThird = distance3[0] * 1.093f;
        float yardsFourth = distance4[0] * 1.093f;
        float yardsFifth = distance5[0] * 1.093f;
        float yardsSixth = distance6[0] * 1.093f;
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

        int roundedYardFirst = (int) (yardsFirst + 0.5);
        int roundedYardSecond = (int) (yardsSecond + 0.5);
        int roundedYardThird = (int) (yardsThird + 0.5);
        int roundedYardFourth = (int) (yardsFourth + 0.5);
        int roundedYardFifth = (int) (yardsFifth + 0.5);
        int roundedYardSixth = (int) (yardsSixth + 0.5);
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

//        dText1.setText(String.valueOf(roundedMetreFirst) + "m   " + String.valueOf(roundedYardFirst) + "y");
//        dText2.setText(String.valueOf(roundedMetreSecond) + "m   " + String.valueOf(roundedYardSecond) + "y");
//        dText3.setText(String.valueOf(roundedMetreThird) + "m   " + String.valueOf(roundedYardThird) + "y");
//        dText4.setText(String.valueOf(roundedMetreFourth) + "m   " + String.valueOf(roundedYardFourth) + "y");
//        dText5.setText(String.valueOf(roundedMetreFifth) + "m   " + String.valueOf(roundedYardFifth) + "y");
//        dText6.setText(String.valueOf(roundedMetreSixth) + "m   " + String.valueOf(roundedYardSixth) + "y");
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

        dText1.setText(String.valueOf(roundedYardFirst) + "y");
        dText2.setText(String.valueOf(roundedYardSecond) + "y");
        dText3.setText( String.valueOf(roundedYardThird) + "y");
        dText4.setText( String.valueOf(roundedYardFourth) + "y");
        dText5.setText(String.valueOf(roundedYardFifth) + "y");
        dText6.setText(String.valueOf(roundedYardSixth) + "y");
        dText7.setText( String.valueOf(roundedYard7) + "y");
        dText8.setText(String.valueOf(roundedYard8) + "y");
        dText9.setText( String.valueOf(roundedYard9) + "y");
        dText10.setText(String.valueOf(roundedYard10) + "y");
        dText11.setText(String.valueOf(roundedYard11) + "y");
        dText12.setText(String.valueOf(roundedYard12) + "y");
        dText13.setText( String.valueOf(roundedYard13) + "y");
        dText14.setText(String.valueOf(roundedYard14) + "y");
        dText15.setText( String.valueOf(roundedYard15) + "y");
        dText16.setText(String.valueOf(roundedYard16) + "y");
        dText17.setText( String.valueOf(roundedYard17) + "y");
        dText18.setText(String.valueOf(roundedYard18) + "y");

    }


}
