package com.example.kamhi.ex11.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kamhi.ex11.Model.Country;
import com.example.kamhi.ex11.Model.DataLoader;
import com.example.kamhi.ex11.R;

/**
 * Created by Madaim on 31/01/2017.
 */


public class CountryAdapter extends ArrayAdapter<Country> {

    private Context context;
    public CountryAdapter(Context context) {
        super(context, R.layout.world_explorer);
        this.context = context;
        this.addAll(DataLoader.getCountries());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.world_explorer, parent, false);
        }
            TextView tvName = (TextView)convertView.findViewById(R.id.name);
            TextView tvShort = (TextView)convertView.findViewById(R.id.shorty);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.flag);
            Country country = getItem(position);
            tvName.setText(country.getName());
            tvShort.setText(country.getShortDetails());
            int imageResorce = parent.getContext().getResources().getIdentifier(country.getFlag(), "drawable", parent.getContext().getPackageName());
            imageView.setImageResource(imageResorce);

        return convertView;
    }



}
