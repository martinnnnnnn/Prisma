package com.example.martin.prisma;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 09.02.2016.
 */
public class MeldungenArray {

    public static ArrayList<Meldung> meldungen = new ArrayList<Meldung>();
    private static String url ="http://www.b19.rwth-aachen.de/download_folder/smart/eintrag";

    public static JsonObjectRequest refreshMeldungen(){
        meldungen.clear();

        final JSONObject params = new JSONObject();

        /*header parameters*/
        try {
            params.put("Accept","application/json");
            params.put("Content-Type","application/json");
        } catch (JSONException e) {
            e.printStackTrace();
        }
 /*url is static in class*/
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("responze", response.toString());
                            JSONArray jsonArray = response.getJSONArray("eintrag");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject meldung =     jsonArray.getJSONObject(i);
                                String comment = meldung.getString("comment");
                                Log.d("f", comment);
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

}
