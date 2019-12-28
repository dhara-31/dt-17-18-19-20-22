package com.example.MyMapDemp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    ArrayList<ModelApi> contact;
    GoogleMap gmap;
    double s1, s2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contact = new ArrayList<>();

        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpHandler handler = new HttpHandler();
            String url = "https://maps.googleapis.com/maps/api/place/search/json?key=AIzaSyBvVkvSSftJdfmzPfIFIcY4JNx2UW7hj4g&location=21.170240,72.831062&radius=1000";
            String jsonStr = handler.makeServiceCall(url);
            if (jsonStr != null) {

                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray results = jsonObj.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {

                        JSONObject jsonObject = results.getJSONObject(i);
                        JSONObject g = jsonObject.getJSONObject("geometry");
                        JSONObject location = g.getJSONObject("location");

                        s1 = location.getDouble("lat");
                        //Log.e("TAG", "lattttt::::::::======>>> " + s1);
                        s2 = location.getDouble("lng");
                        //Log.e("TAG", "lnggggg:======>>>>>>>> " + s2);
                        ModelApi latLong = new ModelApi();
                        latLong.setLat(s1);
                        latLong.setLon(s2);
                        contact.add(latLong);

                    }

                } catch (final JSONException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Json Parsing Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Could not get json From Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
            mapFragment.getMapAsync(MainActivity.this);
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gmap = googleMap;
        for (int i = 0; i < contact.size(); i++) {
            LatLng latlng = new LatLng(contact.get(i).getLat(), contact.get(i).getLon());
            googleMap.addMarker(new MarkerOptions().position(latlng));
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

        }
    }
}
