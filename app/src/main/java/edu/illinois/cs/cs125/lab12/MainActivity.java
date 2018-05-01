package edu.illinois.cs.cs125.lab12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonParser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;
    //Arraylist for searching cuisine type
    private static List cuisineArray = new ArrayList();
    //not sure if need or not
    private static List selectedArray = new ArrayList();
    //index array
    private static List indexArray = new ArrayList();

    private int randomNum;

    //manages click and access
    private static boolean isMexican = false;
    private static boolean isIndian = false;
    private static boolean isThai = false;
    private static boolean isChinese = false;
    private static boolean isKorean = false;
    private static boolean isAmerican = false;
    //manages price range
    private static int priceRange = 0;
    //getter for name
    private static String restaurantName;
    public static String getRestaurantName() {
        return restaurantName;
    }
    //getter for ratings
    private static String ratings;
    public static String getRating() {
         return ratings;
    }
    //getter for website
    private static String webs;
    public static String getWebs() {
        return webs;
    }

    private static double lats;
    public static double getLats() {
        return lats;
    }

    private static double longs;
    public static double getLongs() {
        return longs;
    }

    private static String addr;
    public static String getAddr() {
        return addr;
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

        final Button mexican = findViewById(R.id.Mexican);
        mexican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Mexican button clicked");
                if (!isMexican) {
                    cuisineArray.add("73");
                    Log.d(TAG, "added");
                    Log.d(TAG, cuisineArray.toString());
                    isMexican = true;
                } else {
                    Log.d(TAG, "removed");
                    cuisineArray.remove("73");
                    Log.d(TAG, cuisineArray.toString());
                    isMexican = false;
                }
            }
        });
        final Button indian = findViewById(R.id.Indian);
        indian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Indian button clicked");
                if (!isIndian) {
                    cuisineArray.add("148");
                    Log.d(TAG, "added");
                    Log.d(TAG, cuisineArray.toString());
                    isIndian = true;
                } else {
                    Log.d(TAG, "removed");
                    cuisineArray.remove("148");
                    Log.d(TAG, cuisineArray.toString());
                    isIndian = false;
                }
            }
        });
        final Button thai = findViewById(R.id.Thai);
        thai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Thai button clicked");
                if (!isThai) {
                    cuisineArray.add("95");
                    Log.d(TAG, "added");
                    Log.d(TAG, cuisineArray.toString());
                    isThai = true;
                } else {
                    Log.d(TAG, "removed");
                    cuisineArray.remove("95");
                    Log.d(TAG, cuisineArray.toString());
                    isThai = false;
                }
            }
        });
        final Button chinese = findViewById(R.id.Chinese);
        chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Chinese button clicked");
                if (!isChinese) {
                    cuisineArray.add("25");
                    Log.d(TAG, "added");
                    Log.d(TAG, cuisineArray.toString());
                    isChinese = true;
                } else {
                    Log.d(TAG, "removed");
                    cuisineArray.remove("25");
                    Log.d(TAG, cuisineArray.toString());
                    isChinese = false;
                }
            }
        });
        final Button korean = findViewById(R.id.Korean);
        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Korean button clicked");
                if (!isKorean) {
                    cuisineArray.add("67");
                    Log.d(TAG, "added");
                    Log.d(TAG, cuisineArray.toString());
                    isKorean = true;
                } else {
                    Log.d(TAG, "removed");
                    cuisineArray.remove("67");
                    Log.d(TAG, cuisineArray.toString());
                    isKorean = false;
                }
            }
        });
        final Button american = findViewById(R.id.American);
        american.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Others button clicked");
                if (!isAmerican) {
                    cuisineArray.add("1");
                    Log.d(TAG, "added");
                    Log.d(TAG, cuisineArray.toString());
                    isAmerican = true;
                } else {
                    Log.d(TAG, "removed");
                    cuisineArray.remove("1");
                    Log.d(TAG, cuisineArray.toString());
                    isAmerican = false;
                }
            }
        });

        final Button normal = findViewById(R.id.cheap);
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "cheap button clicked");
                priceRange = 1;
            }
        });
        final Button medium = findViewById(R.id.medium);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "medium button clicked");
                priceRange = 2;
            }
        });
        final Button expensive = findViewById(R.id.expensive);
        expensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "expensive button clicked");
                priceRange = 3;
            }
        });
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
        String cuisines = "";
        for (int i = 0; i < cuisineArray.size(); i++) {
            cuisines = cuisines.concat((String)cuisineArray.get(i) + "%2C");
        }
        Log.d(TAG,"selected cuisines:" + cuisines);
            try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://developers.zomato.com/api/v2.1/search?entity_id=130687&entity_type=subzone&cuisines=" + cuisines +
                            "&apikey=" + BuildConfig.API_KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(2));
                                JSONArray restaurantsArr = response.getJSONArray("restaurants");
                                for (int i = 0; i < restaurantsArr.length(); i++) {
                                    JSONObject restaurant = restaurantsArr.getJSONObject(i);
                                    JSONObject localRes = restaurant.getJSONObject("restaurant");
                                    JSONObject localUser = localRes.getJSONObject("user_rating");
                                    JSONObject localLoc = localRes.getJSONObject("location");
                                    Log.d(TAG,"heh:" + localRes);
                                    //name of restaurant
                                    String name = localRes.getString("name");
                                    Log.d(TAG, "name:" + name);

                                    //price range
                                    int price = localRes.getInt("price_range");
                                    Log.d(TAG, "price range:" + price);


                                    if (priceRange == 0 || priceRange == price) {
                                        selectedArray.add(name);
                                        indexArray.add(i);
                                    }
                                }
                                Random random = new Random();
                                if (indexArray.size() <= 1) {
                                    randomNum = 0;
                                } else {
                                    randomNum = random.nextInt(indexArray.size() - 1);
                                }
                                restaurantName = selectedArray.get(randomNum).toString();
                                JSONObject restaurant = restaurantsArr.getJSONObject((int)indexArray.get(randomNum));
                                JSONObject localRes = restaurant.getJSONObject("restaurant");
                                JSONObject localUser = localRes.getJSONObject("user_rating");
                                JSONObject localLoc = localRes.getJSONObject("location");
                                //name of restaurant
                                String name = localRes.getString("name");
                                Log.d(TAG, "name:" + name);

                                //type of cuisine
                                String cuisine = localRes.getString("cuisines");
                                Log.d(TAG, "Cuisine:" + cuisine);

                                //price range
                                int price = localRes.getInt("price_range");
                                Log.d(TAG, "price range:" + price);

                                //rating
                                double rating = localUser.getDouble("aggregate_rating");
                                Log.d(TAG, "rating:" + rating);

                                //web page
                                String web = localRes.getString("url");
                                Log.d(TAG, "url:" + web);

                                double latitude = localLoc.getDouble("latitude");
                                double longitude = localLoc.getDouble("longitude");

                                String address = localLoc.getString("address");
                                Log.d(TAG, "-----");
                                ratings = String.format("%.1f",rating);
                                webs = web;
                                lats = latitude;
                                longs = longitude;
                                addr = address;
                                startActivity(intent);
                                Log.d(TAG, selectedArray.toString());
                            } catch (JSONException ignored) { }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
            System.out.println("json response:" + jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
