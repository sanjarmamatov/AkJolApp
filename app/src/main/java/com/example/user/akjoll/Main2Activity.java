package com.example.user.akjoll;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.backendless.Backendless;
import com.backendless.exceptions.BackendlessFault;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
/*

        Img img = new Img();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.ImageWindow, img, img.getTag()).commit();
*/

        ImageView imgView = (ImageView)findViewById(R.id.imageView2);
        //https://pp.vk.me/c837637/v837637097/1971/zbTKefuN0Pg.jpg

        Picasso.with(getApplicationContext()).load("https://pp.vk.me/c638126/v638126097/7513/Wn6dpQGhUjQ.jpg").into(imgView);
        //Picasso.with(getApplicationContext()).load("https://api.backendless.com/C22910BF-46B9-277F-FF89-C8F6085AEF00/v1/files/pictures/AkJol.jpg").into(imgView);
       // https://api.backendless.com/C22910BF-46B9-277F-FF89-C8F6085AEF00/v1/files/pictures/10398211.jpg


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent5 = new Intent(Main2Activity.this, MapsActivity.class);
            startActivity(intent5);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }/* else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        }*/ else if (id == R.id.nav_send) {

            Backendless.UserService.logout(new DefaultCallback<Void>( this )
            {
                @Override
                public void handleResponse( Void response )
                {
                    super.handleResponse( response );
                    startActivity( new Intent( Main2Activity.this, LoginActivity.class ) );
                    finish();
                }

                @Override
                public void handleFault( BackendlessFault fault )
                {
                    if( fault.getCode().equals( "3023" ) ) // Unable to logout: not logged in (session expired, etc.)
                        handleResponse( null );
                    else
                        super.handleFault( fault );
                }
            } );
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
