package com.example.kamhi.ex11.View;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.location.GpsStatus;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kamhi.ex11.Controller.CountryAdapter;
import com.example.kamhi.ex11.Model.Country;
import com.example.kamhi.ex11.Model.DataLoader;
import com.example.kamhi.ex11.R;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Kamhi on 3/1/2017.
 */

public class ItemsFragment extends ListFragment implements MyDialog.ResultsListener{

    CountryAdapter adapter;
    Context context;
    CountryselectList listener;
    ArrayList<Country> shownCountries = new ArrayList<>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        this.context = getActivity();
        try {
            this.listener = (CountryselectList) getActivity();
            this.adapter = new CountryAdapter(context);
            this.adapter.sort( new Comparator<Country>() {
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
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.items_frag, container, false);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        getListView().setSelector(android.R.color.holo_blue_dark);
        this.listener.onCountryChange(position, adapter.getItem(position));
    }

    @Override
    public void onFinishedDialog(int requestCode, Object results) {

    }


    public interface CountryselectList{
        public void onCountryChange(int position, Country country);
        public int getCurrentSelection();
        public void setInitCountry(Country country);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.context, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                MyDialog.newInstance(MyDialog.ADD_DIALOG).show(getChildFragmentManager(), null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ArrayList<String> getMissingCountries(){
        return adapter.getMissingCountries(shownCountries);
    }
}
