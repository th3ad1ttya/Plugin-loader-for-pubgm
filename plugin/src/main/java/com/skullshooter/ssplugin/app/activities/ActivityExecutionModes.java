/**********************************************************\
 |                                                          |
 | Made by: Adittya Hasan (Frankenstein404)                 |
 |                                                          |
 | Android plugin loader for create online update based     |
 | loader                                                   |
 |                                                          |
 | Credits:                                                 |
 |      Frankenstein(Adittya)                               |
 |      Original X Ninja Cheats                             |
 |                                                          |
 | Code Authors: Adittya <theadittyaadi@icloud.com>         |
 | LastModified: Jan 10, 2022                               |
 |                                                          |
 \**********************************************************/

package com.skullshooter.ssplugin.app.activities;

import static com.skullshooter.ssplugin.app.configuaration.AppConfig.EXECUTION_CONTAINER;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.EXECUTION_SUPPERUSER;
import static com.skullshooter.ssplugin.app.manager.SuperSU.isRootGiven;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.skullshooter.ssplugin.app.R;
import com.skullshooter.ssplugin.app.preferences.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ActivityExecutionModes extends Activity {
    @SuppressLint("MissingPermission")
    public void onBackPressed() {
        try {
            ActivityManager actvityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
            actvityManager.killBackgroundProcesses(this.toString());// pkgn is a process id /////
        } catch (Exception e) {
            e.printStackTrace();
        }
        finishAffinity();
        moveTaskToBack(false);
        super.onBackPressed();
        System.exit(0);
    }
    public static final boolean executionMode = true;
    Preferences prefs;
    RelativeLayout root, container;
    TextView rootModeTtl, versionText;
    Boolean containerBool, suBoolean;
    SharedPreferences executionShared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executionmodes);
        setTheme(R.style.Theme_Plugin);
        statusandNavBarColor();
        root = findViewById(R.id.rootmode);
        container = findViewById(R.id.containermode);
        rootModeTtl = findViewById(R.id.rootModeTtl);
        prefs = Preferences.with(this);
        containerSharedPreffs();
        suSharedPreffs();
    }



    private void containerSharedPreffs()
    {
        executionShared = getSharedPreferences(getPackageName() + "_execution", MODE_PRIVATE);
        containerBool = executionShared.getBoolean(EXECUTION_CONTAINER, true);
        mainCallsContainer();
    }//containerSharedPreffs

    private void suSharedPreffs()
    {
        executionShared = getSharedPreferences(getPackageName() + "_execution", MODE_PRIVATE);
        suBoolean = executionShared.getBoolean(EXECUTION_SUPPERUSER, true);
        mainCallsSuperuser();
    }//sharedpreferencesTag


    private void mainCallsSuperuser()
    {
        if (suBoolean)
        {
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = executionShared.edit();
                    suBoolean = false;
                    editor.putBoolean(EXECUTION_SUPPERUSER, suBoolean);
                    editor.apply();
                    prefs.write(EXECUTION_SUPPERUSER, EXECUTION_SUPPERUSER);
                    prefs.remove(EXECUTION_CONTAINER);
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(ActivityExecutionModes.this, android.R.style.Theme_Material_Dialog_Alert))
                            .setTitle("Acquiring root permission")
                            .setMessage("If you see a prompt dialog, please click allow");
                    dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String charngeExecutionSU = prefs.read(EXECUTION_SUPPERUSER);
                            if (charngeExecutionSU.equals(EXECUTION_SUPPERUSER))
                            {
                                prefs.remove(EXECUTION_SUPPERUSER);
                                getSharedPreferences(getPackageName() + "_execution", MODE_PRIVATE).edit().clear().commit();
                            }
                            dialog.dismiss();
                        }
                    });

                    final AlertDialog alert = dialog.create();
                    alert.show();

                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (alert.isShowing())
                            {
                                alert.dismiss();
                                if (isRootGiven()) {
                                    Intent intentDaemon = new Intent(ActivityExecutionModes.this, a.l.class);
                                    startActivity(intentDaemon);
                                } else {
                                    isRootGiven();
                                    String charngeExecutionSU = prefs.read(EXECUTION_SUPPERUSER);
                                    if (charngeExecutionSU.equals(EXECUTION_SUPPERUSER))
                                    {
                                        prefs.remove(EXECUTION_SUPPERUSER);
                                        getSharedPreferences(getPackageName() + "_execution", MODE_PRIVATE).edit().clear().commit();
                                    }
                                    getSharedPreferences(getPackageName() + "_execution", MODE_PRIVATE).edit().clear().commit();
                                    final AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(ActivityExecutionModes.this, android.R.style.Theme_Material_Dialog_Alert))
                                            .setTitle("Permission_denied")
                                            .setMessage("Unable to get root privileges, you may have denied the request or your device does not have root privileges unlocked.");
                                    dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    final AlertDialog alert = dialog.create();
                                    alert.show();
                                }
                            }
                        }
                    };

                    alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            handler.removeCallbacks(runnable);
                        }
                    });
                    handler.postDelayed(runnable, 1500);
                }
            });
        } else {
            if (isRootGiven())
            {
                Intent intentDaemon = new Intent(ActivityExecutionModes.this, a.l.class);
                startActivity(intentDaemon);
            } else {
                String charngeExecutionSU = prefs.read(EXECUTION_SUPPERUSER);
                if (charngeExecutionSU.equals(EXECUTION_SUPPERUSER))
                {
                    prefs.remove(EXECUTION_SUPPERUSER);
                    getSharedPreferences(getPackageName() + "_execution", MODE_PRIVATE).edit().clear().commit();
                }
                final AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(ActivityExecutionModes.this, android.R.style.Theme_Material_Dialog_Alert))
                        .setTitle("Permission_denied")
                        .setMessage("Unable to get root privileges, you may have denied the request or your device does not have root privileges unlocked.");
                dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog alert = dialog.create();
                alert.show();
            }
        }
    }//MainCallSupperuser


    private void mainCallsContainer()
    {
        if (containerBool)
        {
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = executionShared.edit();
                    containerBool = false;
                    editor.putBoolean(EXECUTION_CONTAINER, containerBool);
                    editor.apply();
                    prefs.write(EXECUTION_CONTAINER, EXECUTION_CONTAINER);
                    prefs.remove(EXECUTION_SUPPERUSER);
                    Intent intentDaemon = new Intent(ActivityExecutionModes.this, a.l.class);
                    startActivity(intentDaemon);
                }
            });
        } else {
            Intent intentDaemon = new Intent(ActivityExecutionModes.this, a.l.class);
            startActivity(intentDaemon);
        }
    }//mainCallContainer





    private void statusandNavBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.ss_Color_primary));
        }
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.ss_Color_primary));
        window.getDecorView().setSystemUiVisibility(Color.TRANSPARENT);
    }

    public boolean isRunningApp(String packageName) {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (String activeProcess : processInfo.pkgList) {
                    if (activeProcess.equals(packageName)) { //Other app, which is running on background.

                    } else {
                        Toast.makeText(this, "Machine learning error exception: can't find trusted engine to run this plugin! self destruct within 5 seconds", Toast.LENGTH_LONG).show();
                        final ConstraintLayout mainExecution = findViewById(R.id.mView);
                        mainExecution.setVisibility(View.GONE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finishAffinity();
                                System.exit(0);
                            }
                        }, 5000);
                    }
                }
            }
        }
        return true;
    }
}
