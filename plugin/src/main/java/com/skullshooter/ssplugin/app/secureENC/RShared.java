package com.skullshooter.ssplugin.app.secureENC;

import android.content.Context;
import android.content.SharedPreferences;

public class RShared {
    private Context context;
    private SecurePreferences securePreferences;
    private SharedPreferences shared;

    private SharedPreferences bindShared() {
        return this.securePreferences;
    }

    public RShared (Context context){
        this.context = context;
        this.securePreferences = new SecurePreferences(context,  "com.skullshooter.loader.app_preferences", Context.MODE_PRIVATE);
        this.shared = bindShared();
    }

    public static void InitShared(Context context){
        SecurePreferences shared = new SecurePreferences(context,  "com.skullshooter.loader.app_preferences", Context.MODE_PRIVATE);
        if (shared.getBoolean("InitShared", true)){
            shared.edit().putBoolean("RADOMDAEMON", false).apply();
            shared.edit().putString("RANDOMDAEMONNAME", RSA.getRandomText(8)).apply();
        }
    }
    public String getString(String name){
        return this.shared.getString(name,  null);
    }
    public boolean getBoolean(String name){
        return this.shared.getBoolean(name, false);
    }
    public int getInt(String name){
        return this.shared.getInt(name, 0);
    }
    public void setString(String name, String value){
        this.shared.edit().putString(name, value).apply();
    }
    public void setBoolean(String name, boolean value){
        this.shared.edit().putBoolean(name, value).apply();
    }
    public void setInt(String name, int value){
        this.shared.edit().putInt(name, value).apply();
    }
    public void remove(String name){
        this.shared.edit().remove(name).apply();
    }
}
