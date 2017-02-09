package com.example.kamhi.ex11.View;

import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
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
    private MediaController mc;
    private static MediaPlayer mp;
    Context context;
    MediaController mController;
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

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.tVDetails = (TextView)view.findViewById(R.id.details);
        tVDetails.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mController.show();
                return false;
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_frag, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mp!=null) {
            mp.stop();
            mp.release();
        }
    }
    @Override
    public void onPause() {
        if(mp!=null){
            mp.stop();
            mp.reset();
        }
        super.onPause();
    }

    public void changeTo(Country newCountry){

        this.tVDetails.setText(newCountry.getDetails());
        if(mp!=null)
            mp.stop();
        int AnthemResorce = context.getResources().getIdentifier(newCountry.getAnthem(), "raw", context.getPackageName());
        mp = MediaPlayer.create(context, AnthemResorce);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mController = new MediaController(context);
        mController.setMediaPlayer(new MediaControll());
        mController.setAnchorView(tVDetails);
        mController.show();
        mp.start();

    }

    public static interface CountryReporter{
        public Country getCountryData();
    }

    public class MediaControll implements MediaController.MediaPlayerControl{

        @Override
        public void start() {
            mp.start();
        }

        @Override
        public void pause() {

            if(mp.isPlaying())
                mp.pause();
        }

        @Override
        public int getDuration() {
            return mp.getDuration();
        }

        @Override
        public int getCurrentPosition() {
            return mp.getCurrentPosition();
        }

        @Override
        public void seekTo(int pos) {
            mp.seekTo(pos);
        }

        @Override
        public boolean isPlaying() {
            return mp.isPlaying();
        }

        @Override
        public int getBufferPercentage() {
            int percentage = (mp.getCurrentPosition() * 100) / mp.getDuration();
            return percentage;
        }

        @Override
        public boolean canPause() {
            return true;
        }

        @Override
        public boolean canSeekBackward() {
            return false;
        }

        @Override
        public boolean canSeekForward() {
            return false;
        }

        @Override
        public int getAudioSessionId() {
            return 0;
        }
    }

}
