package com.example.alan.locationtest;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private TextView longitudeTextView,latitudeTextView;

    private LocationManager locationManager;

    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitudeTextView = (TextView) findViewById(R.id.latitudeTextView);
        longitudeTextView = (TextView)findViewById(R.id.longitudeTextView);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
            Log.d("location",provider.toString());
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            Log.d("location","location is wifi");
            provider = LocationManager.NETWORK_PROVIDER;
        } else {

            Toast.makeText(this, "NO location provider to use",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //LocationManager=>location
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            Log.d("location","location is not null");
            showLocation(location);
        } else {
            Location locationN = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            showLocation(locationN);
            Log.d("location","location is null");
        }

        locationManager.requestLocationUpdates(provider, 5000, 1, listener);
    }

    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            showLocation(location);
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


    private void showLocation(Location location){

        String currentLongtitude = "longitude is " + location.getLongitude();
        String currentLatitude = "Latitude is " + location.getLatitude();
        Log.d("location",currentLongtitude);
        Log.d("location",currentLatitude);

        longitudeTextView.setText(currentLongtitude);
        latitudeTextView.setText(currentLatitude);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {

            locationManager.removeUpdates(listener);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
