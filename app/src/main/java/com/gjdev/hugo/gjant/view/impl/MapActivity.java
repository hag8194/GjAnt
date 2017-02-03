package com.gjdev.hugo.gjant.view.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.gjdev.hugo.gjant.R;
import com.gjdev.hugo.gjant.data.api.model.Address;
import com.gjdev.hugo.gjant.data.api.model.ClientWallet;
import com.gjdev.hugo.gjant.data.api.model.Zone;
import com.gjdev.hugo.gjant.view.MapView;
import com.gjdev.hugo.gjant.presenter.loader.PresenterFactory;
import com.gjdev.hugo.gjant.presenter.MapPresenter;
import com.gjdev.hugo.gjant.injection.AppComponent;
import com.gjdev.hugo.gjant.injection.MapViewModule;
import com.gjdev.hugo.gjant.injection.DaggerMapViewComponent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MapActivity extends BaseActivity<MapPresenter, MapView> implements MapView, OnMapReadyCallback {
    @Inject
    PresenterFactory<MapPresenter> mPresenterFactory;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    // Your presenter is available using the mPresenter variable
    private GoogleMap mMap;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ButterKnife.bind(this);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMapViewComponent.builder()
                .appComponent(parentComponent)
                .mapViewModule(new MapViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<MapPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mPresenter.onMapReady();

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    @Override
    public void setupActivity() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setupMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void setupCamera(Zone zone) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(zone.getLat(), zone.getLng())));
    }

    @Override
    public void addMarkers(List<ClientWallet> clientWalletList) {
        for (ClientWallet clientWallet : clientWalletList) {
            Address clientAddress = clientWallet.getClient().getAddress();
            LatLng position = new LatLng(clientAddress.getLat(), clientAddress.getLng());

            mMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(clientWallet.getClient().getFullname()));
        }
    }
}
