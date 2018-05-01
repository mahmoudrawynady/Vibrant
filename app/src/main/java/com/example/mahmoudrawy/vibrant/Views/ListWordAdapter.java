package com.example.mahmoudrawy.vibrant.Views;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mahmoudrawy.vibrant.R;

import java.util.ArrayList;
import java.util.List;

import instance.Hospital;

/**
 * Created by Tarek on 4/23/2018.
 */
public class ListWordAdapter extends ArrayAdapter<Hospital> {

    private int vBackgroundColor;

    public ListWordAdapter(Context context, List<Hospital> listWord, int BackgroundColor) {
        super(context, 0, listWord);
        vBackgroundColor = BackgroundColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.hospital_item_list_view, parent, false);
        }
        Hospital hospitalInfo = getItem(position);
        TextView hospitalName = (TextView) listItemView.findViewById(R.id.hospital_name_ID);
        hospitalName.setText(hospitalInfo.getName());
        TextView hospitalLocation = (TextView) listItemView.findViewById(R.id.hospital_location_ID);
        hospitalLocation.setText(hospitalInfo.getAddress());
        TextView hospitalAvailableBeds = (TextView) listItemView.findViewById(R.id.hospital_availableBeds_ID);
        hospitalAvailableBeds.setText(hospitalInfo.getNumberOfAvailableBeds()+" "+"Available Beds");
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.hospital_image_ID);
        imageView.setImageResource(R.drawable.vibrant);
        View textContener = listItemView.findViewById(R.id.textConainer);
        int color = ContextCompat.getColor(getContext(), vBackgroundColor);
        textContener.setBackgroundColor(color);
        return listItemView;
    }
}
