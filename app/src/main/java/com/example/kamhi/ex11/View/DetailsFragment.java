package com.example.kamhi.ex11.View;

import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kamhi.ex11.Model.Country;
import com.example.kamhi.ex11.R;

import java.io.File;

/**
 * Created by Kamhi on 3/1/2017.
 */

public class DetailsFragment extends Fragment {

    TextView tVDetails;
    CountryReporter listener;

    Context context;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Country country;
        this.context = getActivity();
        try {
            this.listener = (CountryReporter) getActivity();
            country = listener.getCountryData();
            if(country!=null)
                changeTo(country);
        }
        catch (ClassCastException e){
            throw new ClassCastException("The class " + getActivity().getClass().getName() + " must implements the interface 'CountryReporter");
        }
        int AnthemResorce = context.getResources().getIdentifier(country.getAnthem(), "raw", context.getPackageName());
        MediaPlayer mp = new MediaPlayer();
        try {
            mp.create(context, AnthemResorce);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.tVDetails = (TextView)view.findViewById(R.id.details);
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_frag, container, false);
    }

    public void changeTo(Country newCountry){
        this.tVDetails.setText(newCountry.getDetails());
    }

    public static interface CountryReporter{
        public Country getCountryData();
    }

}
