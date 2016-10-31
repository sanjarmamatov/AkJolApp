package com.example.user.akjoll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double lat;
    Double lng;
    //Double lat1;
   // Double lng1;
    GeoMarker gg;
    //AkJollUser sts;
    List<GeoMarker> listgeo = new ArrayList();
    List<GeoMarker> listgeo1 = new ArrayList();
    GeoMarker gget;
    BackendlessUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        user = Backendless.UserService.CurrentUser();

        Intent intent5 = getIntent();

        //final String appVersion = "v1";
        //Backendless.initApp( this, "C22910BF-46B9-277F-FF89-C8F6085AEF00", "2DA3599F-9AC3-5E54-FFCF-6572485BDE00", appVersion );

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

        load();
      /*  Backendless.Persistence.of(GeoMarker.class).find(new AsyncCallback<BackendlessCollection<GeoMarker>>() {
            @Override
            public void handleResponse(BackendlessCollection<GeoMarker> response) {

                listgeo1 = response.getData();
                if (listgeo1.size() != 0) {
                    for (int i = 0; i < listgeo1.size(); i++) {
                        gget = listgeo1.get(i);
                        Double lat1 = gget.getLatitude();
                        Double lng1 = gget.getLongtitude();

                        LatLng place = new LatLng(lat1, lng1);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(place);
                        Marker locate = mMap.addMarker(new MarkerOptions().position(place));
                    }
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast toast = Toast.makeText(getApplicationContext(), "Ошибка при загрузке карты", Toast.LENGTH_SHORT);
                toast.show();
            }

        });*/


        LatLng bishkek = new LatLng(42.852178, 74.607600);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bishkek));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bishkek, 10));



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                if (Integer.parseInt(user.getProperty("status").toString()) == 1) {

                    LatLng position = new LatLng(latLng.latitude, latLng.longitude);
                    Marker marker = mMap.addMarker(new MarkerOptions().position(position));
                    lat = latLng.latitude;
                    lng = latLng.longitude;

                    Backendless.Persistence.save(new GeoMarker(lat, lng), new AsyncCallback<GeoMarker>() {
                        @Override
                        public void handleResponse(GeoMarker response) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Марка добавлена!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        @Override
                        public void handleFault(BackendlessFault fault) {
                        }
                    });
               }
            }
        });





        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {

                if (Integer.parseInt(user.getProperty("status").toString()) == 1) {
                    gg = new GeoMarker();
                    lat = marker.getPosition().latitude;
                    lng = marker.getPosition().longitude;


                    String whereClause = "latitude = " + lat + " AND longtitude = " + lng;
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause(whereClause);
                    Backendless.Persistence.of(GeoMarker.class).find(dataQuery, new AsyncCallback<BackendlessCollection<GeoMarker>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<GeoMarker> response) {
                            listgeo = response.getData();
                            gg = listgeo.get(0);
                            Backendless.Persistence.of(GeoMarker.class).remove(gg /*response.getData().get(0)*/, new AsyncCallback<Long>() {
                                @Override
                                public void handleResponse(Long response) {
                                    marker.remove();
                                    Toast toast = Toast.makeText(getApplicationContext(), "Метка удалена!", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Ошибка ретерна!", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });
                        }
                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Ошибка кери!", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });
                }
                return false;
            }
        });
    }


    private void load() {
        Backendless.Persistence.of(GeoMarker.class).find(new AsyncCallback<BackendlessCollection<GeoMarker>>() {
            @Override
            public void handleResponse(BackendlessCollection<GeoMarker> response) {

                listgeo1 = response.getData();
                if (listgeo1.size() != 0) {
                    for (int i = 0; i < listgeo1.size(); i++) {
                        gget = listgeo1.get(i);
                        Double lat1 = gget.getLatitude();
                        Double lng1 = gget.getLongtitude();

                        LatLng place = new LatLng(lat1, lng1);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(place);
                        Marker locate = mMap.addMarker(new MarkerOptions().position(place));
                    }
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast toast = Toast.makeText(getApplicationContext(), "Ошибка при загрузке карты", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }
        /*String whereClause = "latitude = "*//* + " AND longtitude = "*//*;
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);
        Backendless.Data.of(GeoMarker.class).find(dataQuery, new AsyncCallback<BackendlessCollection<GeoMarker>>() {
            @Override
            public void handleResponse(BackendlessCollection<GeoMarker> foundViolation) {

                listgeo = foundViolation.getData();
                if (listgeo.size() != 0) {

                    for (int i = 0; i < listgeo.size(); i++) {

                        gg = listgeo.get(i);
                        lat1 = gg.getLatitude();
                        lng1 = gg.getLongtitude();

                        LatLng position = new LatLng(lat1, lng1);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(position);

                        Marker location = mMap.addMarker(new MarkerOptions().position(position));

                    }

                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast toast = Toast.makeText(getApplicationContext(), "Ошибка при загрузке карты", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }*/
}