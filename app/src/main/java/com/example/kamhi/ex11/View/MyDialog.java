package com.example.kamhi.ex11.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.kamhi.ex11.R;

import java.util.ArrayList;

/**
 * Created by Madaim on 07/02/2017.
 */

public class MyDialog extends DialogFragment {

    private ResultsListener listener;
    private int requestCode;
    public final static int ADD_DIALOG = 1;

    Spinner sp;

    public static MyDialog newInstance(int requestCode) {
        Bundle args = new Bundle();
        MyDialog fragment = new MyDialog();
        args.putInt("rc", requestCode);
        fragment.setArguments(args);
        return fragment;
    }

    private AlertDialog.Builder buildAddDialog() {
        final String[] selected = new String[1];
        View view = getActivity().getLayoutInflater().inflate(R.layout.country_selection, null);
        sp = (Spinner)view.findViewById(R.id.spinner);
        ItemsFragment iFrag = (ItemsFragment) getParentFragment();
        sp.setAdapter(
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        iFrag.getMissingCountries())
        );

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return new AlertDialog.Builder(getActivity())
                .setTitle("select country from list")
                .setView(view)
                .setPositiveButton("set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onFinishedDialog(requestCode, selected[0]);

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        this.requestCode = getArguments().getInt("rc");
        if(requestCode == ADD_DIALOG){
            return buildAddDialog().create();
        }
        else
            return buildAddDialog().create();
    }
    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            this.listener = (ResultsListener)activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException("Hosting activity must implement the interface ResultsListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }*/
    public interface ResultsListener{
        public void onFinishedDialog(int requestCode, Object results);
    }
}
