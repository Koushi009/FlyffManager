package com.klinger2709.klingerju.flyffmanager;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ManagerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        selectItem(0);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            selectItem(0);
        } else if (id == R.id.nav_upgrade) {
            selectItem(1);
        } else if (id == R.id.nav_asal) {
            selectItem(2);
        } else if (id == R.id.nav_upgradeScrolls) {
            selectItem(5);
        } else if (id == R.id.nav_time_easy) {
            selectItem(3);
        } else if(id == R.id.nav_wcoin) {
            selectItem(4);
        } else if (id == R.id.nav_share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Flyff manager");
                String sAux = getString(R.string.link_to_app);
                sAux = sAux + getString(R.string.link);
                sAux = sAux + getString(R.string.have_fun_testing);
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, getString(R.string.pls_choose)));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }

    public void selectItem(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = new UpgradeFragment();
                break;
            case 2:
                fragment = new AsalFragment();
                break;
            case 3:
                fragment = new LevelEasyFragment();
                break;
            case 4:
                fragment = new WCoinsFragment();
                break;
            case 5:
                fragment = new MoreUpgradesFragment();
                break;
            default:
                fragment = new HomeFragment();
                break;
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

}
