package steel.marnix.eindwerkandroidstreats.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import steel.marnix.eindwerkandroidstreats.R;
import steel.marnix.eindwerkandroidstreats.fragments.AboutFragment;
import steel.marnix.eindwerkandroidstreats.fragments.DetailFragment;
import steel.marnix.eindwerkandroidstreats.fragments.RecyclerFragment;
import steel.marnix.eindwerkandroidstreats.model.FoodTruck;
import steel.marnix.eindwerkandroidstreats.model.StreatDatabase;
import steel.marnix.eindwerkandroidstreats.model.StreetArt;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, CompoundButton.OnCheckedChangeListener {

    private GoogleMap mMap;
    Switch artSwitch;
    Switch foodSwitch;

    //broadcastReceiver, will get updates from db
    private BroadcastReceiver mMessageBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            drawFoodMarkers();
            drawArtMarkers();
        }
    };

    @Override
    public void onInfoWindowClick(Marker marker) {

        getFragmentManager().beginTransaction().replace(R.id.main_container, DetailFragment.newInstance()).addToBackStack("Back").commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem foodMenuItem = navigationView.getMenu().findItem(R.id.switch_food);
        foodSwitch = foodMenuItem.getActionView().findViewById(R.id.item_switch);
        foodSwitch.setChecked(true);
        foodSwitch.setOnCheckedChangeListener(this);

        MenuItem artMenuItem = navigationView.getMenu().findItem(R.id.switch_streetArt);
        artSwitch = artMenuItem.getActionView().findViewById(R.id.item_switch);
        artSwitch.setChecked(true);
        artSwitch.setOnCheckedChangeListener(this);

        MapFragment mf = MapFragment.newInstance();
        mf.getMapAsync(this);
        getFragmentManager().beginTransaction().replace(R.id.main_container, mf).commit();

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mMessageBroadcastReceiver, new IntentFilter("klaar"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.switch_streetArt) {
            //DetailFragment df = DetailFragment.newInstance();
            //getFragmentManager().beginTransaction().replace(R.id.main_container, df).addToBackStack("Back").commit();
        } else if (id == R.id.switch_food) {
            //DetailFragment df = DetailFragment.newInstance();
            //getFragmentManager().beginTransaction().replace(R.id.main_container, df).addToBackStack("Back").commit();

        } else if (id == R.id.nav_maps) {
            MapFragment mf = MapFragment.newInstance();
            mf.getMapAsync(this);

            getFragmentManager().beginTransaction().replace(R.id.main_container, mf).addToBackStack("Back").commit();

        } else if (id == R.id.nav_list) {
            RecyclerFragment rf = RecyclerFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.main_container, rf).addToBackStack("Back").commit();

        } else if (id == R.id.nav_about) {
            AboutFragment af = AboutFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.main_container, af).addToBackStack("Back").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        updatecamera();
        drawArtMarkers();
        drawFoodMarkers();
    }

    private void updatecamera() {
        LatLng bxlCoord = new LatLng(50.8503369, 4.3517103);
        CameraUpdate mCameraUpdate = CameraUpdateFactory.newLatLngZoom(bxlCoord, 13);

        mMap.animateCamera(mCameraUpdate);
    }

    private void drawArtMarkers() {
        List<StreetArt> streetArts = StreatDatabase.getInstance(this).getArtDAO().getAllStreetArt();

        for (StreetArt art : streetArts) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(art.getLatitude(), art.getLongitude()))
                    .title(art.getArtistName())
                    .snippet(art.getDescription())
                    .icon(BitmapDescriptorFactory.defaultMarker(200))
            );
        }
    }

    private void drawFoodMarkers() {

        List<FoodTruck> foodTrucks = StreatDatabase.getInstance(this).getFoodDAO().getAllFoodTrucks();

        for (FoodTruck truck : foodTrucks) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(truck.getLatitude(), truck.getLongitude()))
                    .title(truck.getName())
                    .snippet(truck.getLocation())
                    .icon(BitmapDescriptorFactory.defaultMarker(320))
            );
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mMessageBroadcastReceiver);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        mMap.clear();

        if (artSwitch.isChecked()) {
            drawArtMarkers();
        }

        if (foodSwitch.isChecked()) {
            drawFoodMarkers();
        }
    }
}
