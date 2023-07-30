package com.niklas.test.ui.maps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.niklas.test.R;

import java.util.Random;


public class MapsActivityParkour extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker selectedMarker;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        random = new Random();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btnFakeLocation = findViewById(R.id.locationButton);
        btnFakeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFakeLocation();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                selectedMarker = marker;
                if (marker.getTag() != null && marker.getTag().equals("user")) {
                    Toast.makeText(MapsActivityParkour.this, "Marker löschen: " + marker.getTitle(), Toast.LENGTH_SHORT).show();
                    marker.remove();
                    return true; // Markierung wurde gelöscht, also true zurückgeben
                } else {

                    return false; // Markierung wurde nicht gelöscht, also false zurückgeben
                }
            }
        });


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                addMarker(latLng);
            }
        });

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng center = mMap.getCameraPosition().target;
                Toast.makeText(MapsActivityParkour.this, "Kameramittelpunkt: " + center.latitude + ", " + center.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        LatLngBounds germanyBounds = new LatLngBounds(
                new LatLng(47.270111, 5.866342),
                new LatLng(55.058939, 15.041931)
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(germanyBounds, 0));

        addRandomMarkersForGermany();

        LatLng koblenzLatLng = new LatLng(50.362416, 7.603523);
        addMarker(koblenzLatLng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(koblenzLatLng, 15f));
    }
    private void addRandomMarkersForGermany() {
        for (int i = 0; i < 3; i++) {
            double lat = getRandomNumberInRange(47.270111, 55.058939);
            double lng = getRandomNumberInRange(5.866342, 15.041931);
            LatLng randomLatLng = new LatLng(lat, lng);
            addRandomMarker(randomLatLng);
        }
    }

    private void addRandomMarker(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title("Zufälliger Standort");
        Marker marker = mMap.addMarker(markerOptions);
    }


    private double getRandomNumberInRange(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    private void addMarker(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title("Neuer Standort");
        Marker marker = mMap.addMarker(markerOptions);
        marker.setTag("user"); // Setze das Tag "user" für vom Benutzer gesetzte Marker
    }




    private void showFakeLocation() {
        LatLng koblenzLatLng = new LatLng(50.362416, 7.603523);
        addMarker(koblenzLatLng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(koblenzLatLng, 15f));
    }
}
