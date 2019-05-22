package com.marketplace.view.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.marketplace.R;
import com.marketplace.util.AppConstants;
import com.marketplace.util.GpsUtils;
import com.marketplace.util.PermissionUtils;
import com.marketplace.view.base.BaseFragment;
import com.marketplace.view.home.HomeViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.marketplace.util.AppConstants.LOCATION_REQUEST;
import static com.marketplace.util.AppConstants.PRODUCT_ID;

/**
 * Created by shima.zeinali on 5/19/2019.
 * shima.zeinalii@gmail.com
 */
public class MapFragment extends BaseFragment<HomeViewModel> {
    SupportMapFragment mapFragment;
    private HomeViewModel viewModel;
    private GoogleMap map;
    private Geocoder geocoder;
    private Marker marker;
    private String addressText;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private double wayLatitude = 0.0, wayLongitude = 0.0;

    public static MapFragment newInstance(Bundle args) {
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareGps();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstants.GPS_REQUEST) {
                prepareMap();
            }
        }else {
            prepareGps();
        }
    }

    private void prepareMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new MapReady());
        geocoder = new Geocoder(getBaseActivity(), Locale.getDefault());
    }

    private void prepareGps() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getBaseActivity());
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds

        new GpsUtils(getBaseActivity()).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                prepareMap();
            }
        });
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            setLatLon();
        } else {
            PermissionUtils.requestPermission(this, LOCATION_REQUEST,
                    Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    @SuppressLint("MissingPermission")
    private void setLatLon() {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(getBaseActivity(), location -> {
            if (location != null) {
                wayLatitude = location.getLatitude();
                wayLongitude = location.getLongitude();
                setupMap(true);
            } else {
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setLatLon();
                } else {
                    PermissionUtils.requestPermission(this, LOCATION_REQUEST,
                            Manifest.permission.ACCESS_FINE_LOCATION);
                }
                break;
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void setupMap(boolean isLocationEnable) {
        map.setMyLocationEnabled(isLocationEnable);
        map.getUiSettings().setMyLocationButtonEnabled(isLocationEnable);
        if (wayLatitude != 0) {
            LatLng latLng = new LatLng(wayLatitude, wayLongitude);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
            marker = map.addMarker(getMarkerOption(latLng));
        }
        map.setOnMapClickListener(new OnMapClick());
    }

    private MarkerOptions getMarkerOption(LatLng latLng) {
        return new MarkerOptions().position(latLng).title("Marker")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.submit:
                viewModel.registerOrder(getArguments().getInt(PRODUCT_ID), addressText);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public int getActionMenu() {
        return R.menu.action_map;
    }

    @Override
    public HomeViewModel getViewModel() {
        viewModel = ViewModelProviders.of(getBaseActivity()).
                get(HomeViewModel.class);

        return viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    protected int getTitleId() {
        return R.string.title_map;
    }

    private class MapReady implements OnMapReadyCallback {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            checkPermission();
        }
    }

    private class OnMapClick implements GoogleMap.OnMapClickListener {
        @Override
        public void onMapClick(LatLng latLng) {
            List<Address> addresses = new ArrayList<>();
            try {
                addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses.size() == 0)
                return;
            Address address = addresses.get(0);
            if (address != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                }
                addressText = sb.toString();
                Toast.makeText(getBaseActivity(), sb.toString(), Toast.LENGTH_LONG).show();
            }
            if (marker != null) {
                marker.remove();
            }
            marker = map.addMarker(getMarkerOption(latLng));
        }
    }
}
