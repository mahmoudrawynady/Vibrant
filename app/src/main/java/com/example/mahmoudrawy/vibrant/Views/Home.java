package com.example.mahmoudrawy.vibrant.Views;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mahmoudrawy.vibrant.R;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean location;
    private boolean disease;
    private boolean children;
    private boolean adults;

    DrawerLayout drawer;

    private Fragment homeFragment;
    private Fragment publicFragment;
    private Fragment specialFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.home);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, new HomeFragment()).commit();
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.location) {

            if (item.isChecked()) {
                item.setChecked(false);
                location = false;
            } else {
                item.setChecked(true);
                location = true;
            }
            optionResult();
        }
        if (id == R.id.disease) {
            if (item.isChecked()) {
                item.setChecked(false);
                disease = false;
            } else {
                item.setChecked(true);
                disease = true;
            }
            optionResult();
        }

        if (id == R.id.children) {
            Toast.makeText(this, "Children Only", Toast.LENGTH_LONG).show();
            children = true;
            adults = false;
            optionResult();
        } else if (id == R.id.adults) {
            adults = true;
            children = false;
            optionResult();
        }

        return super.onOptionsItemSelected(item);
    }

    public void optionResult() {
        if (location && disease && !children && adults) {
            Toast.makeText(this, "Location , Disease and Children", Toast.LENGTH_LONG).show();
        } else if (location && disease && children && !adults) {
            Toast.makeText(this, "Location , Disease and Adults", Toast.LENGTH_LONG).show();
        } else if (!location && disease && children && !adults) {
            Toast.makeText(this, "Disease and Children", Toast.LENGTH_LONG).show();
        } else if (!location && disease && !children && adults) {
            Toast.makeText(this, "Disease and Adults", Toast.LENGTH_LONG).show();
        } else if (location && !disease && children && !adults) {
            Toast.makeText(this, "Location and Children", Toast.LENGTH_LONG).show();
        } else if (location && !disease && !children && adults) {
            Toast.makeText(this, "Location and Adults", Toast.LENGTH_LONG).show();
        } else if (location && disease && !children && !adults) {
            Toast.makeText(this, "Location and Disease", Toast.LENGTH_LONG).show();
        } else if (location && !disease && !children && !adults) {
            Toast.makeText(this, "Location Only", Toast.LENGTH_LONG).show();
        } else if (!location && !disease && !children && adults) {
            Toast.makeText(this, "Adults Only", Toast.LENGTH_LONG).show();
        } else if (!location && !disease && children && !adults) {
            Toast.makeText(this, "Children Only", Toast.LENGTH_LONG).show();
        } else if (!location && disease && !children && !adults) {
            Toast.makeText(this, "Disease Only", Toast.LENGTH_LONG).show();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.special) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment,new SpecialFragment()).addToBackStack(null).commit();
        } else if (id == R.id.public_hospital) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, new PublicFragment()).addToBackStack(null).commit();

        } else if (id == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, new HomeFragment()).addToBackStack(null).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
