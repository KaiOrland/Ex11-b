package com.example.kamhi.ex11.View;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.location.GpsStatus;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kamhi.ex11.Model.Country;
import com.example.kamhi.ex11.Model.DataLoader;
import com.example.kamhi.ex11.R;

import java.util.Comparator;

/**
 * Created by Kamhi on 3/1/2017.
 */

public class ItemsFragment extends ListFragment{

    ArrayAdapter<Country> adapter;
    Context context;
    CountryselectList listener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        this.context = getActivity();
        try {
            this.listener = (CountryselectList) getActivity();
            this.adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_list_item_1, DataLoader.getCountries());
            this.adapter.sort(new Comparator<Country>() {
                @Override
                public int compare(Country c1, Country c2) {
                    return c1.compare(c2);
                }
            });
            setListAdapter(adapter);
            int position = listener.getCurrentSelection();
            if(position !=-1){
                listener.setInitCountry(adapter.getItem(position));
            }
        }
        catch (ClassCastException e){
            throw new ClassCastException("The class " + getActivity().getClass().getName() + " must implements the interfase 'CountryselectList");
        }


        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.items_frag, container, false);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        getListView().setSelector(android.R.color.holo_blue_dark);
        this.listener.onCountryChange(position, adapter.getItem(position));
    }



    public interface CountryselectList{
        public void onCountryChange(int position, Country country);
        public int getCurrentSelection();
        public void setInitCountry(Country country);
    }
}
