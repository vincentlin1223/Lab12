package edu.illinois.cs.cs125.lab12;

import android.content.Intent;
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


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    /**dsfg*/
    private static String location;

    /**fadf*/
    public static String getLocation() {
        return MainActivity.location;
    }



    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);
        final Button update = findViewById(R.id.Update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "update button clicked");
                startAPICall();
            }
        });
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
        final Intent intent = new Intent(this, SecondActivity.class);
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://developers.zomato.com/api/v2.1/search?entity_id=130687&entity_type=subzone&apikey="
                            + BuildConfig.API_KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(2));
                                //TextView textview = findViewById(R.id.textView3);
                                //textview.setText(response.toString(2));
                                startActivity(intent);
                                Log.d(TAG, "here");
                                Log.d(TAG, "activity 2");
                            } catch (JSONException ignored) { }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
