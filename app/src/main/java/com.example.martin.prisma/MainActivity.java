package com.example.martin.prisma;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<Meldung> meldungen = new ArrayList<Meldung>();
    private static String url ="http://www.b19.rwth-aachen.de/download_folder/smarter/meldung";
    //TODO Methode zum updaten des meldungen arrays?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /*check if user is online*/
        if (!isOnline()){
            KeineInternetverbindung d = new KeineInternetverbindung();
            d.show(getFragmentManager(), "dialog");
        }
        initializeButtons();

        //get liste of Meldungen via JSON using volley
        Context context = getApplicationContext();
        Log.d("beginne","dasGanzeJSONZeug");
        final JSONObject params = new JSONObject();

        /*header parameters*/
        try {
            params.put("Accept","application/json");
            params.put("Content-Type","application/json");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = refreshMeldungen(params);
        queue.add(jsonObjectRequest);

    }
    protected void onResume(Bundle savedInstanceState){
        final JSONObject params = new JSONObject();

        /*header parameters*/
        try {
            params.put("Accept","application/json");
            params.put("Content-Type","application/json");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Context context = getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = refreshMeldungen(params);
        queue.add(jsonObjectRequest);

    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public JsonObjectRequest refreshMeldungen(final JSONObject params){
 /*url is static in class*/
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("eintrag");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject meldung =     jsonArray.getJSONObject(i);
                                String comment = meldung.getString("comment");
                                Log.d("ausgabe", comment);
                                Date datum = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(meldung.getString("date"));

                                meldungen.add(new Meldung(meldung.getInt("rating"), meldung.getInt("status"), meldung.getDouble("lat"), meldung.getDouble("lng"),
                                        meldung.getInt("id"), meldung.getString("comment"), meldung.getInt("category"), meldung.getInt("user_id"), datum));
                                Log.d("Comment", meldungen.get(0).getComment());

                            }
                            Log.d("howmany", "soviele geholt: "+meldungen.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        Log.d("leider fail!","failez");
                    }
                }){
            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {

                try {
                    Log.i("json", params.toString());
                    return params.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        return jsonObjectRequest;
    }
    public void initializeButtons(){

        final Button b1p1 = (Button) this.findViewById(R.id.button1p1);
        final Button mapButton = (Button) this.findViewById(R.id.button1p2);
        final Button toplistButton = (Button) this.findViewById(R.id.button1p3);
        final Button b1p4 = (Button) this.findViewById(R.id.button1p4);

        final Button b2p1 = (Button) this.findViewById(R.id.button2p1);
        final Button b2p2 = (Button) this.findViewById(R.id.button2p2);
        final Button b2p3 = (Button) this.findViewById(R.id.button2p3);
        final Button b2p4 = (Button) this.findViewById(R.id.button2p4);

        final Button b3p1 = (Button) this.findViewById(R.id.button3p1);
        final Button b3p2 = (Button) this.findViewById(R.id.button3p2);
        final Button b3p3 = (Button) this.findViewById(R.id.button3p3);
        final Button b3p4 = (Button) this.findViewById(R.id.button3p4);

        final Button b4p1 = (Button) this.findViewById(R.id.button4p1);
        final Button b4p2 = (Button) this.findViewById(R.id.button4p2);
        final Button b4p3 = (Button) this.findViewById(R.id.button4p3);
        final Button b4p4 = (Button) this.findViewById(R.id.button4p4);

        final Button b5p1 = (Button) this.findViewById(R.id.button5p1);
        final Button b5p2 = (Button) this.findViewById(R.id.button5p2);
        final Button b5p3 = (Button) this.findViewById(R.id.button5p3);
        final Button b5p4 = (Button) this.findViewById(R.id.button5p4);

        final ImageButton b6p1 = (ImageButton) this.findViewById(R.id.button6p1);
        final Button b6p2 = (Button) this.findViewById(R.id.button6p2);
        final Button b6p3 = (Button) this.findViewById(R.id.button6p3);
        final Button b6p4 = (Button) this.findViewById(R.id.button6p4);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

        toplistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Toplist.class));
            }
        });
        b1p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Eventsmaske.class));
            }
        });
        b6p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profil.class));
            }




        });

        b1p1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                b1p1.setVisibility(View.GONE);
                b2p1.setVisibility(View.VISIBLE);
                b3p1.setVisibility(View.VISIBLE);
                b4p1.setVisibility(View.VISIBLE);
                b5p1.setVisibility(View.VISIBLE);
                b6p1.setVisibility(View.VISIBLE);
            }
        });

        b2p1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                b1p1.setVisibility(View.VISIBLE);
                b2p1.setVisibility(View.GONE);
                b3p1.setVisibility(View.VISIBLE);
                b4p1.setVisibility(View.VISIBLE);
                b5p1.setVisibility(View.VISIBLE);
                b6p1.setVisibility(View.VISIBLE);            }
        });

        b3p1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                b1p1.setVisibility(View.VISIBLE);
                b2p1.setVisibility(View.VISIBLE);
                b3p1.setVisibility(View.GONE);
                b4p1.setVisibility(View.VISIBLE);
                b5p1.setVisibility(View.VISIBLE);
                b6p1.setVisibility(View.VISIBLE);            }
        });

        b4p1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                b1p1.setVisibility(View.VISIBLE);
                b2p1.setVisibility(View.VISIBLE);
                b3p1.setVisibility(View.VISIBLE);
                b4p1.setVisibility(View.GONE);
                b5p1.setVisibility(View.VISIBLE);
                b6p1.setVisibility(View.VISIBLE);            }
        });

        b5p1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                b1p1.setVisibility(View.VISIBLE);
                b2p1.setVisibility(View.VISIBLE);
                b3p1.setVisibility(View.VISIBLE);
                b4p1.setVisibility(View.VISIBLE);
                b5p1.setVisibility(View.GONE);
                b6p1.setVisibility(View.VISIBLE);            }
        });

        b6p1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                b1p1.setVisibility(View.VISIBLE);
                b2p1.setVisibility(View.VISIBLE);
                b3p1.setVisibility(View.VISIBLE);
                b4p1.setVisibility(View.VISIBLE);
                b5p1.setVisibility(View.VISIBLE);
                b6p1.setVisibility(View.GONE);
            }
        });

    }
}
