package com.antonio.practica_ut05_googlemaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private Button miLocalizacion;
    private Button iraCip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        miLocalizacion =  findViewById(R.id.button_milocalizacion);
        iraCip = findViewById(R.id.button_ircip);

        miLocalizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMyLocation();
            }
        });

        iraCip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCipLocation();
            }
        });




    }




    private void getMyLocation() {
        LatLng silliconValley = new LatLng(37.4030185, -122.321292);
        mMap.addMarker(new MarkerOptions().position(silliconValley).title("Aquí vivo yo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(silliconValley));
    }

    private void getCipLocation() {
        LatLng cip = new LatLng(28.4840067, -16.3118097);
        mMap.addMarker(new MarkerOptions().position(cip).title("Centro Internacional Politécnico"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cip));
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
        mMap.setOnMapLongClickListener(this);


    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Toast.makeText(MapsActivity.this, "Tu marcador", Toast.LENGTH_SHORT).show();

        final MarkerOptions markerOptions =
                new MarkerOptions().position(latLng).title(latLng.toString());
        mMap.addMarker(markerOptions);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();

                Toast.makeText(MapsActivity.this, "Marcador eliminado", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
