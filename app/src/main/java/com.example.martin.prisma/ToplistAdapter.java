package com.example.martin.prisma;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Martin on 26.01.2016.
 */
public class ToplistAdapter extends BaseAdapter{
    private ArrayList<Meldung> listData;
    private LayoutInflater layoutInflater;

    public ToplistAdapter(Context aContext, ArrayList<Meldung> listData){
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Meldung getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.categoryView = (ImageView) convertView.findViewById(R.id.list_image);
            holder.listText = (TextView) convertView.findViewById(R.id.textViewListText);
            holder.date = (TextView) convertView.findViewById(R.id.textViewListDate);
            holder.votes = (TextView) convertView.findViewById(R.id.textViewListVotes);
            holder.distance = (TextView) convertView.findViewById(R.id.textViewDistance);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //TODO Daten, Timestamp, Distance (Location in die Main)
        holder.categoryView.setImageResource(R.drawable.festival2);
        holder.listText.setText(listData.get(position).getComment());
        //String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(listData.get(position).getDate());
        holder.date.setText("date");
        Integer votes = listData.get(position).getRating();
        holder.votes.setText(votes.toString());
        Location targetLocation = new Location("");
        targetLocation.setLatitude(listData.get(position).getLat());
        targetLocation.setLongitude(listData.get(position).getLng());
        //holder.distance.setText((new Float(Toplist.mLastLocation.distanceTo(targetLocation))).toString());

        return convertView;
    }
    static class ViewHolder{
        ImageView categoryView;
        TextView listText;
        TextView date;
        TextView votes;
        TextView distance;
        int position;
    }

}
