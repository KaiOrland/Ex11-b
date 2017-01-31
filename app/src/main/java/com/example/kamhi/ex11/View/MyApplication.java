package com.example.kamhi.ex11.View;

import android.app.Application;
import android.content.Context;

/**
 * Created by Madaim on 24/01/2017.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
    }
    public static Context getAppContext(){
        return MyApplication.context;
    }
}
