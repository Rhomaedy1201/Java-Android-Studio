package com.example.projectlbs;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.projectlbs.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    final private int REQUEST_COURCE_ACCESS = 123;
    boolean permissionGranted = false;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    float zoomLevel = 16.0f;
    LocationManager lm;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COASE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_COURCE_ACCESS);
            return;
        }else{
            permissionGranted = true;
        }if (permissionGranted){
            lm.removeUpdates(locationListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, locationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, locationListener);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_COURCE_ACCESS);
            return;
        }else{
            permissionGranted = true;
        }

        if (permissionGranted){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, locationListener);
        }

        LatLng bws = new LatLng(-7.913110, 113.822870);
        mMap.addMarker(new MarkerOptions().position(bws).title("Bondowoso"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bws, zoomLevel));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case REQUEST_COURCE_ACCESS:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permissionGranted = true;
                }else {
                    permissionGranted = false;
                }break;
            default:
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private class MyLocationListener implements LocationListener{
        public void onLocationChanged(Location loc){
            if (loc != null){
                Toast.makeText(getBaseContext(), "Location Changed : Lat: \"" + "+ loc.getLatitude() + \n " + " \" lng: \n" + loc.getLongitude(), Toast.LENGTH_SHORT).show();
                LatLng p = new LatLng((int) (loc.getLatitude()), (int) (loc.getLongitude()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(p));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
            }
        }
        public void onProviderDisabled(String provider){
            Toast.makeText(getBaseContext(), provider + "Disabled", Toast.LENGTH_SHORT).show();
        }
        public void onProviderEnabled(String provider){
            Toast.makeText(getBaseContext(), provider + "Enabled", Toast.LENGTH_SHORT).show();
        }
        public void onStatusChanged(String provider, int status, Bundle extras){
            String statusString = "";
            switch (status){
                case LocationProvider.AVAILABLE:
                    statusString = "Available";
                case LocationProvider.OUT_OF_SERVICE:
                    statusString = "out of services";
                case LocationProvider.TEMPORRILY_UNAVAILABLE:
                    statusString = "Temporary Unavailable";
        }
        Toast.makeText(getBaseContext(), provider + " " + statusString, Toast.LENGTH_SHORT).show();

    }
}