package com.project.machinetest.app;

import android.app.Application;
import android.content.SharedPreferences;

public class MachineTestApp extends Application {

    public static MachineTestApp mInstance;
    public static final String SESSION="MyAuth";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static void setUserId(String userId){
        SharedPreferences.Editor editor = mInstance.getSharedPreferences(SESSION, MODE_PRIVATE).edit();
        editor.putString("userId", userId);
        editor.apply();
    }

    public static String getUserId(){
        SharedPreferences prefs = mInstance.getSharedPreferences(SESSION, MODE_PRIVATE);
        return prefs.getString("userId", null);
    }

}
