package edu.illinois.cs.cs125.lab12;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


/**
 * Main class for our UI design lab.
 */
public final class SecondActivity extends FragmentActivity implements OnMapReadyCallback {
    /**
     * dmdm
     */
    private static final String TAG = "Lab12:Main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;
    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_second);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        String name = MainActivity.getRestaurantName();
        TextView textView = findViewById(R.id.restaurant);
        textView.setText(name);
        TextView addressView = findViewById(R.id.address);
        addressView.setText(MainActivity.getAddr());
        startAPICall();
    }

    private GoogleMap mMap;

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(MainActivity.getLats(), MainActivity.getLongs());
        mMap.addMarker(new MarkerOptions().position(location).title(MainActivity.getRestaurantName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16.0f));
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Make a call to the weather API.
     */
    void startAPICall() {
        final Intent intent = new Intent(this, MainActivity.class);
        TextView textView = findViewById(R.id.rating);
       textView.setText("Rating: " + MainActivity.getRating());

        final Button website = findViewById(R.id.Website);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String url = MainActivity.getWebs();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        final Button goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(intent);
            }
        });
    }
}


