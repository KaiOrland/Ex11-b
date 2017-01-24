package com.example.kamhi.ex11.Model;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.kamhi.ex11.View.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Madaim on 24/01/2017.
 */

public class DataLoader {

    public static ArrayList<Country> getCountries(){
        ArrayList<Country> data = null;
        Context context = MyApplication.getAppContext();
        InputStream in = openAssetDataStream(context);
        data = XMLParser.parseFromStream(in);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static InputStream openAssetDataStream(Context context){
        AssetManager assetManager = context.getAssets();
        InputStream in = null;
        try {
            in = assetManager.open("countries.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}
