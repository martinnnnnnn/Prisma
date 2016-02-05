package com.example.martin.prisma;

import android.os.Bundle;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Martin on 24.01.2016.
 */
public class Meldung {

    //TODO Datenanbindung, Initalisierung entfernen, Timestamp!
    private int rating = 0;
    private int status = 0;
    private double lat = 0.0;
    private double lng = 0.0;
    private int id = 0;
    private String comment = "comment";
    private int category = 0;
    private int user_id = 0;
    private Date date;

    @Override
    public String toString(){
        return "ID: " + id + ": " + comment;
    }

    //TODO remove constructor
    public Meldung(){

    }
    public Meldung(int rating,int status, double lat, double lng, int id, String comment, int category, int user_id, Date date){
        this.rating = rating;
        this.status = status;
        this.lat = lat;
        this.lng = lng;
        this.id = id;
        this.comment = comment;
        this.category = category;
        this.user_id = user_id;
        this.date = date;
    }
    public Bundle getBundle(){
        Bundle meldungBundle = new Bundle();
        meldungBundle.putInt("rating", rating);
        meldungBundle.putInt("status", status);
        meldungBundle.putDouble("lat", lat);
        meldungBundle.putDouble("lng", lng);
        meldungBundle.putInt("id", id);
        meldungBundle.putString("comment", comment);
        meldungBundle.putInt("category", category);
        meldungBundle.putInt("user_id", user_id);
        meldungBundle.putString("date", date.toString());
        return meldungBundle;
    }

    public int getRating (){
     return rating;
    }
    public int getStatus (){
        return status;
    }
    public double getLat (){
        return lat;
    }
    public double getLng (){
        return lng;
    }
    public int getId (){
        return id;
    }
    public String getComment(){
        return comment;
    }
    public int getCategory(){
        return category;
    }
    public int getUser_id(){
        return user_id;
    }
    public Date getDate(){
        return date;
    }

}
