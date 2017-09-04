package com.example.odisys.oms;

import android.app.Application;

import com.example.odisys.oms.Api.WebServicesHelpers;

/**
 * Created by eko on 7/23/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WebServicesHelpers.initialize();
    }
}
