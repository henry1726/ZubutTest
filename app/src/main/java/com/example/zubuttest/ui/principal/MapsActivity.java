package com.example.zubuttest.ui.principal;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.zubuttest.R;
import com.example.zubuttest.sys.util.Constants;
import com.example.zubuttest.ui.adress.AdressActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.zubuttest.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final int REQUEST_CODE_GPS_ENABLE = 1;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient locationProviderClientUpdates;
    private LocationManager locationManager;
    private LocationCallback locationCallback;
    private Location location;
    private Geocoder geocoder;
    private MapsActivityViewModel viewModel;


    @SuppressLint("VisibleForTests")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        viewModel = new ViewModelProvider(this).get(MapsActivityViewModel.class);

        requestPermission();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null)
                    return;

                for (Location location : locationResult.getLocations()) {
                    MapsActivity.this.location = location;
                }
            }
        };

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationProviderClientUpdates = new FusedLocationProviderClient(this);

        geocoder = new Geocoder(this, Locale.getDefault());

        binding.fabNav.setOnClickListener(v -> addAdress());
        binding.fabList.setOnClickListener(v -> launchAdressList());
    }

    public void addAdress(){
        if(location == null){
            locationProviderClientUpdates.getLastLocation()
                    .addOnSuccessListener( this, location ->{
                        if(location != null){
                            try {
                                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                                List<Address> list = geocoder.getFromLocation(
                                        location.getLatitude(), location.getLongitude(), 1);
                                if (!list.isEmpty()) {
                                    Address DirCalle = list.get(0);
                                    viewModel.insertAdress("Dirección", DirCalle.getAddressLine(0) + ", " + DirCalle.getLocality(), String.format("%s,%s", location.getLatitude(), location.getLongitude()) );
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
        Toast.makeText(this, "Dirección guardada con éxito!", Toast.LENGTH_LONG).show();
    }

    public void launchAdressList(){
        Intent intent = new Intent(this, AdressActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            createDialogEnableGPS().show();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        viewModel.readAllCordinates().observe(this, list ->{
            if(list.size() > 0){
                locationProviderClientUpdates.getLastLocation()
                        .addOnSuccessListener( this, location ->{
                            // Add a marker in Sydney and move the camera
                            mMap.setMyLocationEnabled(true);
                            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(sydney).title("You're here!"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));

                            for(int i = 0; i<list.size(); i++){
                                LatLng mark = new LatLng(list.get(i).latitude, list.get(i).longitude);
                                mMap.addMarker(new MarkerOptions().position(mark).title("Adress: " + i+1));
                            }
                        });
            }else{
                if(location == null){
                    locationProviderClientUpdates.getLastLocation()
                            .addOnSuccessListener( this, location ->{
                                // Add a marker in Sydney and move the camera
                                mMap.setMyLocationEnabled(true);
                                LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(sydney).title("You're here!"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                            });
                }
            }
        });

    }

    private AlertDialog createDialogEnableGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        return builder.setTitle(R.string.aviso)
                .setMessage(getString(R.string.los_servicios_de_localizacion_estan_deshabilitados))
                .setCancelable(false)
                .setNegativeButton(R.string.cancelar, (a, b) -> {
                    a.dismiss();
                }).setPositiveButton(R.string.aceptar, (a, b) -> startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), Constants.Permissions.REQUEST_CODE_GPS_ENABLE)).create();
    }

    private void requestPermission() {
        ArrayList<String> pendingPermissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : Constants.Permissions.PERMISSIONS) {
                if (notHasPermission(permission))
                    pendingPermissions.add(permission);
            }

            ActivityCompat.requestPermissions(this, convertToVector(pendingPermissions), 0);
        }
    }

    private String[] convertToVector(ArrayList<String> pendingPermissions) {
        String[] strings = new String[pendingPermissions.size()];

        for (int i = 0; i < pendingPermissions.size(); i++) {
            strings[i] = pendingPermissions.get(i);
        }

        return strings;
    }

    private boolean notHasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }
}