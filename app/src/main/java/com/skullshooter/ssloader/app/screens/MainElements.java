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
 | LastModified: Oct 5, 2022                               |
 |                                                          |
 \**********************************************************/
package com.skullshooter.ssloader.app.screens;

import static com.skullshooter.ssloader.app.loader.MachineLearning.getFieldValue;
import static com.skullshooter.ssloader.app.loader.MachineLearning.invokeMethod;
import static com.skullshooter.ssloader.app.loader.MachineLearning.setFieldValue;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.ANNOUNCEMENT;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.AN_TTL;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.CLEAR_WEATHER;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.ERROR_TEXT;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.ISMAINTENANCE;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.LOADER;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.LOADER_VERSION;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PG64_VERSION;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PG_32BIT_NAME;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PG_64BIT_NAME;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PG_LTE_NAME;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PG_VERSION;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PLUG64_DIR;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PLUG64_NAME;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PLUGIN;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PLUGIN64;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PLUG_DIR;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.PLUG_NAME;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.RAIN_WEATHER;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.SAFE;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.SERVER_URL;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.SNOW_WEATHER;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.UNSAFE;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.UPDATE_LINK;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.VALUE32;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.VALUE64;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.VERSION;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.WEATHER32;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.WEATHER_TYPE;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.ann_ttl;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.dataWasUpdatedAt;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.infi_on_ref;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.is32Bit;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.is32isSafe;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.is64Bit;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.is64isSafe;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.isMaintenance;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.server_status;
import static com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig.status_server;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.ArrayMap;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.skullshooter.ssloader.app.BuildConfig;
import com.skullshooter.ssloader.app.R;
import com.skullshooter.ssloader.app.loader.MachineLearning;
import com.skullshooter.ssloader.app.loader.RCHPatcher;
import com.skullshooter.ssloader.app.loader.Utilities;
import com.skullshooter.ssloader.app.loaderConfiguarations.LoaderConfig;
import com.skullshooter.ssloader.app.preferences.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import dalvik.system.DexClassLoader;

public class MainElements extends Activity implements View.OnClickListener {
    public Preferences prefs;
    public static String pluginVersion, plugin64Version;
    public RelativeLayout download_launch_pg_32bit, download_launch_pg_64bit, refresh_layout /*announcement_layout*/, error_layout, download_launch_pg_lte, customeUpdate_layout;
    public ProgressBar refresh_or_download_progressBar;
    public LinearLayout updates_info, hackSelections;
    public TextView text32bit, text64bit, CustomURL;
    public TextView updateLoaderInfo_text, installation_status_32bit, installation_status_64bit,data_was_updated_on, titleLoader, connectionErrorTxt;
    public TextView safety32bit, safety64bit, announcements, announcement_title, updateInfoT, textLite, txtDataUpdated;
    public Button customLinkReset;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_elements);
        //SahredPrefs
        prefs = Preferences.with(this);
        if (!prefs.contains(LOADER_VERSION))
        {
            prefs.write(LOADER_VERSION, BuildConfig.VERSION_NAME);
        }

        statusandNavBarColor();

        //FindViewById
        download_launch_pg_32bit = findViewById(R.id.selection_32bit); //layout
        download_launch_pg_64bit = findViewById(R.id.selection_64bit); //layout
        refresh_layout = findViewById(R.id.refresh_layout); //layout
        //announcement_layout = findViewById(R.id.announcement_layout); //layout
        //announcement_layout.setOnClickListener(this);
        updates_info = findViewById(R.id.updates_info); //layout
        updates_info.setOnClickListener(this);
        hackSelections = findViewById(R.id.hackSelections); //layout
        error_layout = findViewById(R.id.error_function_layout); //Layout
        //download_launch_pg_lte = findViewById(R.id.selection_lite);
        //download_launch_pg_lte.setOnClickListener(this);
        error_layout.setOnClickListener(this);
        refresh_or_download_progressBar = findViewById(R.id.refreshProgress); //ProgressBar
        updateLoaderInfo_text = findViewById(R.id.progress_tint_txt); //TextView
        installation_status_32bit = findViewById(R.id.installation_status_32bit); //TextView
        installation_status_64bit = findViewById(R.id.installation_status_64bit); //TextView
        connectionErrorTxt = findViewById(R.id.error_connection_txt); //TextView
        safety32bit = findViewById(R.id.safety32bit); //TextVIew
        safety64bit = findViewById(R.id.safety64bit); //TextView
        announcements = findViewById(R.id.announcements);
        data_was_updated_on = findViewById(R.id.data_was_updated);
        titleLoader = findViewById(R.id.ttl_skull);

        text32bit = findViewById(R.id.text32bit);
        text64bit = findViewById(R.id.text64bit);
        announcement_title = findViewById(R.id.announcement_title);
        updateInfoT = findViewById(R.id.updateInfoT);
        //textLite = findViewById(R.id.textLite);
        txtDataUpdated = findViewById(R.id.txtDataUpdated);
        customeUpdate_layout = findViewById(R.id.customeUpdate_layout);
        CustomURL = findViewById(R.id.CustomURL);
        customLinkReset = findViewById(R.id.customLinkReset);

        prefs.write(PG_32BIT_NAME, "PUBG Mobile [32-bit]");
        prefs.write(PG_64BIT_NAME, "PUBG Mobile [64-bit]");
        prefs.write(PG_LTE_NAME, "PUBG Mobile Lite");
        prefs.write(ann_ttl, "Announcement");
        prefs.write(infi_on_ref, "Please select the hack you want to start, or tap and hold an item for more options.");
        prefs.write(dataWasUpdatedAt, "Data was updated at");

        download_launch_pg_32bit.setOnClickListener(this);
        download_launch_pg_64bit.setOnClickListener(this);
        String loaderVersion = prefs.read(LOADER_VERSION);
        titleLoader.setText("CheatAssassin Loader v"+loaderVersion);

        //SafetyStatus
        prefs.write(is32isSafe, SAFE);
        prefs.write(is64isSafe, SAFE);
        prefs.write(isMaintenance, status_server);

        //MainImplements
        loaderUpdateDetector();
        existingAndVersionChecker();
        dataWasUpdated();
        onLongPressedForDownload();

        Intent intent = new Intent("com.skullshooter.brmobicall");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        Bundle bundle = new Bundle();
        bundle.putString("msg_from_browser", "Launched from Browser");
        intent.putExtras(bundle);

        customLinkReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.remove("custom_url");
                customeUpdate_layout.setVisibility(View.GONE);
                loaderUpdateDetector();
            }
        });

        updates_info.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(15);
                customUpdateURL();
                return false;
            }
        });
        String cust = prefs.read("custom_url");
        if (!cust.equals("")) {
            customeUpdate_layout.setVisibility(View.VISIBLE);
            CustomURL.setText(prefs.read("custom_url"));
        } else {
            customeUpdate_layout.setVisibility(View.GONE);
        }
    } //onCreate

    @SuppressLint("NewApi")
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

    private void theredes() {
        new Thread(() -> {
            try {
                String urlCustomized = prefs.read("custom_url");
                String defaultURL = null;
                if (!urlCustomized.equals("")) {
                    defaultURL = urlCustomized;
                } else {
                    defaultURL = UPDATE_LINK;
                }
                HttpURLConnection connection = (HttpURLConnection) new URL(defaultURL).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    throw new IOException("Request Code Not 200");
                }

                JSONObject update;

                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[8192];
                while (true) {
                    long read = inputStream.read(bArr, 0, 8192);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, (int) read);
                    } else {
                        inputStream.close();
                        connection.disconnect();
                        update = new JSONObject(byteArrayOutputStream.toString("UTF-8"));
                        break;
                    }
                }

                WeatherView weatherView = findViewById(R.id.weather_view);
                String getWeatherData = update.getJSONObject(WEATHER32).getString(WEATHER_TYPE);
                runOnUiThread(()->{
                    if (getWeatherData.equals(CLEAR_WEATHER)) {
                        weatherView.setWeatherData(PrecipType.CLEAR);
                    }
                    if (getWeatherData.equals(SNOW_WEATHER)) {
                        weatherView.setWeatherData(PrecipType.SNOW);
                    }
                    if (getWeatherData.equals(RAIN_WEATHER)) {
                        weatherView.setWeatherData(PrecipType.RAIN);
                    }
                });

                final String announcementFromServer = update.getJSONObject(AN_TTL).getString(ANNOUNCEMENT);
                runOnUiThread(()->announcements.setText(String.format("%s", announcementFromServer, UPDATE_LINK)));

                final String for32bitSafety = update.getJSONObject(is32Bit).getString(VALUE32);
                final String for32bitValueSharedPrefs = prefs.read(is32isSafe);

                final String for64bitSafety = update.getJSONObject(is64Bit).getString(VALUE64);
                final String for64bitValueSharedPrefs = prefs.read(is64isSafe);

                if (for32bitValueSharedPrefs.equals(for32bitSafety)) {
                    runOnUiThread(()->{
                        safety32bit.setText(SAFE);
                        safety32bit.setTextColor(Color.parseColor("#FF00FF00"));
                        safety32bit.setTypeface(null, Typeface.BOLD);
                    });
                } else {
                    runOnUiThread(()->{
                        safety32bit.setText(UNSAFE);
                        safety32bit.setTextColor(Color.parseColor("#FFFF0000"));
                        safety32bit.setTypeface(null, Typeface.BOLD);
                    });
                }

                if (for64bitValueSharedPrefs.equals(for64bitSafety)) {
                    runOnUiThread(()->{
                        safety64bit.setText(SAFE);
                        safety64bit.setTextColor(Color.parseColor("#FF00FF00"));
                        safety64bit.setTypeface(null, Typeface.BOLD);
                    });
                } else {
                    runOnUiThread(()->{
                        safety64bit.setText(UNSAFE);
                        safety64bit.setTextColor(Color.parseColor("#FFFF0000"));
                        safety64bit.setTypeface(null, Typeface.BOLD);
                    });
                }

                pluginVersion = update.getJSONObject(PLUGIN).getString(VERSION);
                final String currentPluginVersion = prefs.read(PG_VERSION);

                plugin64Version = update.getJSONObject(PLUGIN64).getString(VERSION);
                final String current64PluginVersion = prefs.read(PG64_VERSION);

                File pg = new File(prefs.read(PLUG_DIR), PLUG_NAME);
                File pg64 = new File(prefs.read(PLUG64_DIR), PLUG64_NAME);

                //LoaderUpdate
                final String newSver = update.getJSONObject(LOADER).getString(VERSION);
                final String currSver = prefs.read(LOADER_VERSION, BuildConfig.VERSION_NAME);

                if (!currSver.equals(newSver)) {
                    runOnUiThread(()->{
                        Intent openUpdateLogActivity = new Intent(this, a.h.class);
                        startActivity(openUpdateLogActivity);
                    });
                }

                if (pg.exists()) {
                    if (currentPluginVersion.equals(pluginVersion)) {
                        download_launch_pg_32bit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                runOnUiThread(()->{
                                    launchPlugin();
                                });
                            }
                        });
                    } else {
                        installation_status_32bit.setText("New Update");
                        installation_status_32bit.setTextSize(24);
                        installation_status_32bit.setTypeface(null, Typeface.NORMAL);
                        safety32bit.setVisibility(View.GONE);
                        installation_status_32bit.setTextColor(Color.parseColor("#00FFE6"));
                        download_launch_pg_32bit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                forRedownloadPlugin32OnHold();
                            }
                        });
                    }
                }
                if (pg64.exists()) {
                    if (current64PluginVersion.equals(plugin64Version)) {
                        download_launch_pg_64bit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                runOnUiThread(()->{
                                    launchPlugin64();
                                });
                            }
                        });
                    } else {
                        installation_status_64bit.setText("New Update");
                        installation_status_64bit.setTextSize(24);
                        installation_status_64bit.setTypeface(null, Typeface.NORMAL);
                        safety64bit.setVisibility(View.GONE);
                        installation_status_64bit.setTextColor(Color.parseColor("#00FFE6"));
                        download_launch_pg_64bit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                forRedownloadPlugin64OnHold();
                            }
                        });
                    }

                }//64Bit

                //ServerStatusChecker
                final String status_in_server = update.getJSONObject(ISMAINTENANCE).getString(server_status);
                final String status_in_prefs = prefs.read(isMaintenance, status_server);

                final String descriptionServer = update.getJSONObject(ISMAINTENANCE).getString(VERSION);

                if (!status_in_prefs.equals(status_in_server)) {
                    runOnUiThread(()->{
                        refresh_layout.setVisibility(View.GONE);
                        //announcement_layout.setVisibility(View.GONE);
                        updates_info.setVisibility(View.GONE);
                        hackSelections.setVisibility(View.GONE);
                        error_layout.setVisibility(View.VISIBLE);
                        connectionErrorTxt.setText(String.format("%s", descriptionServer));
                    });
                } else {
                    //Loader Update
                    final String newSVer = update.getJSONObject(LOADER).getString(VERSION);
                    final String currSVer = prefs.read(LOADER_VERSION, BuildConfig.VERSION_NAME);
                    runOnUiThread(() -> {
                        refresh_layout.setVisibility(View.GONE);
                        //announcement_layout.setVisibility(View.VISIBLE);
                        updates_info.setVisibility(View.VISIBLE);
                        hackSelections.setVisibility(View.VISIBLE);
                        error_layout.setVisibility(View.GONE);
                        dataWasUpdated();
                        prefs.write(LOADER_VERSION, currSVer);

                        String pluginLoaderName = prefs.read(PG_32BIT_NAME);
                        String plugin64LoaderName = prefs.read(PG_64BIT_NAME);

                        text32bit.setText(R.string.pg_nm_32_bit);
                        text64bit.setText(R.string.pg_nm_64_bit);
                        announcement_title.setText(R.string.announcement);
                        updateInfoT.setText(R.string.hack_info);
                        txtDataUpdated.setText(R.string.datawas);

                        if (!text32bit.getText().equals(pluginLoaderName)) {
                            showEditError();
                        }
                        if (!text64bit.getText().equals(plugin64LoaderName)) {
                            showEditError();
                        }
                        if (!announcement_title.getText().equals(prefs.read(ann_ttl))) {
                            showEditError();
                        }
                        if (!updateInfoT.getText().equals(prefs.read(infi_on_ref))) {
                            showEditError();
                        }
                        if (!txtDataUpdated.getText().equals(prefs.read(dataWasUpdatedAt))) {
                            showEditError();
                        }

                    });
                }
            } catch (IOException | JSONException e) {
                //HelloWorld
                runOnUiThread(()->{
                    e.printStackTrace();
                    connectionErrorTxt.setText(""+e);
                    refresh_layout.setVisibility(View.GONE);
                    //announcement_layout.setVisibility(View.GONE);
                    updates_info.setVisibility(View.GONE);
                    hackSelections.setVisibility(View.GONE);
                    error_layout.setVisibility(View.VISIBLE);

                    refresh_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loaderUpdateDetector();
                        }
                    });
                });
            }
        }).start();
    }

    private void onLongPressedForDownload() {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        download_launch_pg_32bit.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public boolean onLongClick(View v) {
                File plugin = new File(prefs.read(PLUG_DIR, PLUG_NAME));
                vibe.vibrate(15);
                if (plugin.exists()) {
                    forRedownloadPlugin32OnHold();
                } else {
                    downloadPlugin32WithDialogVersionFirstTime();
                }
                return false;
            }
        });

        download_launch_pg_64bit.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public boolean onLongClick(View v) {
                vibe.vibrate(15);
                File plugin64 = new File(prefs.read(PLUG64_DIR, PLUG64_NAME));
                if (plugin64.exists()) {
                    forRedownloadPlugin64OnHold();
                } else {
                    downloadPlugin64WithDialogVersionFirstTime();
                }
                return false;
            }
        });
    }

    private void dataWasUpdated() {
        data_was_updated_on.setText(": " + DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_12HOUR));
    }

    private void loaderUpdateDetector() {
        refresh_layout.setVisibility(View.VISIBLE);
        updateLoaderInfo_text.setText("Refreshing loader info...");
       //announcement_layout.setVisibility(View.GONE);
        updates_info.setVisibility(View.GONE);
        hackSelections.setVisibility(View.GONE);
        error_layout.setVisibility(View.GONE);
        theredes();

    }

    private void existingAndVersionChecker()
    {
        String pgVersion = prefs.read(PG_VERSION);
        String pg64Version = prefs.read(PG64_VERSION);

        File pluginPath = new File(prefs.read(PLUG_DIR), PLUG_NAME);
        File plugin64Path = new File(prefs.read(PLUG64_DIR), PLUG64_NAME);

        if (pluginPath.exists())
        {
            runOnUiThread(()->{
                installation_status_32bit.setText(pgVersion);
            });
        }
        else
        {
            runOnUiThread(()->{
                installation_status_32bit.setText("Not installed");
            });
        }//plugin

        if (plugin64Path.exists())
        {
            runOnUiThread(()->{
                installation_status_64bit.setText(pg64Version);
            });
        }
        else
        {
            installation_status_64bit.setText("Not installed");
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.selection_32bit:
                File plugin = new File(prefs.read(PLUG_DIR, PLUG_NAME));
                if (plugin.exists()) {
                    forRedownloadPlugin32OnHold();
                } else {
                    downloadPlugin32WithDialogVersionFirstTime();
                }
                break;

            case R.id.selection_64bit:
                /** TODO implement plugin64Download here*/
                File plugin64 = new File(prefs.read(PLUG64_DIR, PLUG64_NAME));
                if (plugin64.exists()) {
                    forRedownloadPlugin64OnHold();
                } else {
                    downloadPlugin64WithDialogVersionFirstTime();
                }
                break;

            /*case R.id.selection_lite:
                break;*/

            case R.id.error_function_layout:
                loaderUpdateDetector();
                break;
            case R.id.updates_info:
                loaderUpdateDetector();
                break;

            /*case R.id.announcement_layout:
                String urlTG = "https://t.me/CheatAssassin";
                Intent openURIInt = new Intent(Intent.ACTION_VIEW);
                openURIInt.setData(Uri.parse(urlTG));
                startActivity(openURIInt);
                break;*/


        }

    }

    private void downloadPlugin32WithDialogVersionFirstTime() {
        runOnUiThread(()->{
            new AlertDialog.Builder(new ContextThemeWrapper(MainElements.this, android.R.style.Theme_Material_Dialog_Alert))
                    .setTitle("PUBG Mobile [32-bit]")
                    .setMessage(String.format("Version: %s", pluginVersion))
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Download and launch", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (isInternetAvailable())
                            {
                                /** TODO implement pluginDownload here*/
                                downloadPlugin();
                            } else {
                                //announcement_layout.setVisibility(View.GONE);
                                updates_info.setVisibility(View.GONE);
                                hackSelections.setVisibility(View.GONE);
                                error_layout.setVisibility(View.VISIBLE);
                                connectionErrorTxt.setText("Unable to download plugin: No address associated with hostname.");
                            }
                        }
                    }).show();
        });
    }

    private void downloadPlugin64WithDialogVersionFirstTime() {
                runOnUiThread(()->{
                    new AlertDialog.Builder(new ContextThemeWrapper(MainElements.this, android.R.style.Theme_Material_Dialog_Alert))
                            .setTitle("PUBG Mobile [64-bit]")
                            .setMessage(String.format("Version: %s", plugin64Version))
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            })
                            .setPositiveButton("Download and launch", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (isInternetAvailable())
                                    {
                                        /** TODO implement pluginDownload here*/
                                        downloadPlugin64();
                                    } else {
                                        //announcement_layout.setVisibility(View.GONE);
                                        updates_info.setVisibility(View.GONE);
                                        hackSelections.setVisibility(View.GONE);
                                        error_layout.setVisibility(View.VISIBLE);
                                        connectionErrorTxt.setText("Unable to download plugin: No address associated with hostname.");
                                    }
                                }
                            }).show();
                });
    }

    private void forRedownloadPlugin32OnHold() {
                final String installedVersion = prefs.read(PG_VERSION);

                if (!installedVersion.equals(pluginVersion)) {
                    //
                    runOnUiThread(()->{
                        new AlertDialog.Builder(new ContextThemeWrapper(MainElements.this, android.R.style.Theme_Material_Dialog_Alert))
                                .setTitle("PUBG Mobile [32-bit]")
                                //.setMessage(String.format("Installed version: %s\n" + "New version: %s", Html.FROM_HTML_MODE_COMPACT, installedVersion, pluginVersion))
                                .setMessage(Html.fromHtml("<p> <font color='#FFFFFF'>Installed version:"+ installedVersion + "<font/></p>" +"<b> <font color='#00FFE6'>New version:"+pluginVersion+ "</font></b>"))
                                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                })
                                .setPositiveButton(Html.fromHtml("<b> <font color='#00FFE6'>Update and launch</font></b>"), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (isInternetAvailable())
                                        {
                                            /** TODO implement pluginDownload here*/
                                            downloadPlugin();
                                        } else {
                                            //announcement_layout.setVisibility(View.GONE);
                                            updates_info.setVisibility(View.GONE);
                                            hackSelections.setVisibility(View.GONE);
                                            error_layout.setVisibility(View.VISIBLE);
                                            connectionErrorTxt.setText("Unable to download plugin: No address associated with hostname.");
                                        }
                                    }
                                })
                                .setNegativeButton("Launch current version", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        launchPlugin();
                                    }
                                }).show();
                    });
                } else {
                    runOnUiThread(()->{
                        new AlertDialog.Builder(new ContextThemeWrapper(MainElements.this, android.R.style.Theme_Material_Dialog_Alert))
                                .setTitle("PUBG Mobile [32-bit]")
                                .setMessage(String.format("Installed version: %s", installedVersion))
                                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                })
                                .setPositiveButton("Re-download and launch", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (isInternetAvailable())
                                        {
                                            /** TODO implement pluginDownload here*/
                                            downloadPlugin();
                                        } else {
                                            //announcement_layout.setVisibility(View.GONE);
                                            updates_info.setVisibility(View.GONE);
                                            hackSelections.setVisibility(View.GONE);
                                            error_layout.setVisibility(View.VISIBLE);
                                            connectionErrorTxt.setText("Unable to download plugin: No address associated with hostname.");
                                        }
                                    }
                                })
                                .setNegativeButton("Launch current version", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        launchPlugin();
                                    }
                                }).show();
                    });
                }

    }

    private void forRedownloadPlugin64OnHold() {
                final String installedVersion = prefs.read(PG64_VERSION);

                if (!installedVersion.equals(plugin64Version)) {
                    runOnUiThread(()->{
                        new AlertDialog.Builder(new ContextThemeWrapper(MainElements.this, android.R.style.Theme_Material_Dialog_Alert))
                                .setTitle("PUBG Mobile [64-bit]")
                                .setMessage(Html.fromHtml("<p> <font color='#FFFFFF'>Installed version:"+ installedVersion + "<font/></p>" +"<b> <font color='#00FFE6'>New version:"+plugin64Version+ "</font></b>"))
                                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                })
                                .setPositiveButton(Html.fromHtml("<b> <font color='#00FFE6'>Update and launch</font></b>"), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (isInternetAvailable())
                                        {
                                            /** TODO implement pluginDownload here*/
                                            downloadPlugin64();
                                        } else {
                                            //announcement_layout.setVisibility(View.GONE);
                                            updates_info.setVisibility(View.GONE);
                                            hackSelections.setVisibility(View.GONE);
                                            error_layout.setVisibility(View.VISIBLE);
                                            connectionErrorTxt.setText("Unable to download plugin: No address associated with hostname.");
                                        }
                                    }
                                })
                                .setNegativeButton("Launch current version", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        launchPlugin64();
                                    }
                                }).show();
                    });
                } else {
                    runOnUiThread(()->{
                        new AlertDialog.Builder(new ContextThemeWrapper(MainElements.this, android.R.style.Theme_Material_Dialog_Alert))
                                .setTitle("PUBG Mobile [64-bit]")
                                .setMessage(String.format("Installed version: %s", installedVersion))
                                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                })
                                .setPositiveButton("Re-download and launch", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (isInternetAvailable())
                                        {
                                            /** TODO implement pluginDownload here*/
                                            downloadPlugin64();
                                        } else {
                                            //announcement_layout.setVisibility(View.GONE);
                                            updates_info.setVisibility(View.GONE);
                                            hackSelections.setVisibility(View.GONE);
                                            error_layout.setVisibility(View.VISIBLE);
                                            connectionErrorTxt.setText("Unable to download plugin: No address associated with hostname.");
                                        }
                                    }
                                })
                                .setNegativeButton("Launch current version", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        launchPlugin64();
                                    }
                                }).show();
                    });
                }
    }

    //PluginDownloader
    private void downloadPlugin()
    {
        runOnUiThread(()->{
            refresh_layout.setVisibility(View.VISIBLE);
            //announcement_layout.setVisibility(View.GONE);
            updates_info.setVisibility(View.GONE);
            hackSelections.setVisibility(View.GONE);

            updateLoaderInfo_text.setText("Downloading PUBG Mobile [32-bit]");
        });
        new Thread(()->{
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(UPDATE_LINK).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    throw new IOException("Request Code Not 200");
                }

                JSONObject update;

                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[8192];
                while (true) {
                    long read = inputStream.read(bArr, 0, 8192);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, (int) read);
                    } else {
                        inputStream.close();
                        connection.disconnect();
                        update = new JSONObject(byteArrayOutputStream.toString("UTF-8"));
                        break;
                    }
                }

                pluginVersion = update.getJSONObject(PLUGIN).getString(VERSION);
                runOnUiThread(()->{
                    refresh_layout.setVisibility(View.VISIBLE);
                    //announcement_layout.setVisibility(View.GONE);
                    updates_info.setVisibility(View.GONE);
                    hackSelections.setVisibility(View.GONE);

                    updateLoaderInfo_text.setText("Downloading PUBG Mobile [32-bit]");
                });
                String version = update.getJSONObject(PLUGIN).getString(VERSION);
                String dlLink = update.getJSONObject(PLUGIN).getString(SERVER_URL);
                File loaderDir = new File(getFilesDir(), PLUGIN);
                if(loaderDir.exists()){
                    Utilities.deleteRecursive(loaderDir);
                }
                loaderDir.mkdir();

                if(!prefs.contains(PLUG_DIR)){
                    prefs.write(PLUG_DIR, loaderDir.getAbsolutePath());
                }

                File loaderPath = new File(loaderDir, PLUG_NAME);
                connection = (HttpURLConnection) new URL(dlLink).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    throw new IOException("Request Code Not 200");
                }

                InputStream bStream = connection.getInputStream();
                FileOutputStream fileOut = new FileOutputStream(loaderPath);
                int fileLength = connection.getContentLength();

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = bStream.read(data)) != -1) {
                    total += count;
                    if (fileLength > 0) {
                        long finalTotal1 = total;
                        runOnUiThread(() -> {
                            String txt = ("Downloading PUBG Mobile [32-bit]" + " ("+ (int) (finalTotal1 * 100 / fileLength)+"%"+")");
                            runOnUiThread(() -> {
                                refresh_layout.setVisibility(View.VISIBLE);
                                //announcement_layout.setVisibility(View.GONE);
                                updates_info.setVisibility(View.GONE);
                                hackSelections.setVisibility(View.GONE);
                                updateLoaderInfo_text.setText(txt);
                            });
                        });
                    }
                    fileOut.write(data, 0, count);
                }

                bStream.close();
                fileOut.flush();
                fileOut.close();
                connection.disconnect();
                runOnUiThread(()->{
                    error_layout.setVisibility(View.GONE);
                    launchPlugin();
                    updateLoaderInfo_text.setText("Starting PUBGM [32-bit]");
                    prefs.write(PG_VERSION, version);
                });
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    refresh_layout.setVisibility(View.GONE);
                    //announcement_layout.setVisibility(View.GONE);
                    updates_info.setVisibility(View.GONE);
                    hackSelections.setVisibility(View.GONE);
                    error_layout.setVisibility(View.VISIBLE);
                    connectionErrorTxt.setText(""+e);
                });
            }
        }).start();
    }//PluginDownloader

    //Plugin64Download
    private void downloadPlugin64() {
        runOnUiThread(()->{
            refresh_layout.setVisibility(View.VISIBLE);
            //announcement_layout.setVisibility(View.GONE);
            updates_info.setVisibility(View.GONE);
            hackSelections.setVisibility(View.GONE);

            updateLoaderInfo_text.setText("Downloading PUBG Mobile [64-bit]");
        });
        new Thread(()->{
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(UPDATE_LINK).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    throw new IOException("Request Code Not 200");
                }

                JSONObject update;

                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[8192];
                while (true) {
                    long read = inputStream.read(bArr, 0, 8192);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, (int) read);
                    } else {
                        inputStream.close();
                        connection.disconnect();
                        update = new JSONObject(byteArrayOutputStream.toString("UTF-8"));
                        break;
                    }
                }

                pluginVersion = update.getJSONObject(PLUGIN64).getString(VERSION);
                runOnUiThread(()->{
                    refresh_layout.setVisibility(View.VISIBLE);
                    //announcement_layout.setVisibility(View.GONE);
                    updates_info.setVisibility(View.GONE);
                    hackSelections.setVisibility(View.GONE);

                    updateLoaderInfo_text.setText("Downloading PUBG Mobile [64-bit]");
                });
                String version = update.getJSONObject(PLUGIN64).getString(VERSION);
                String dlLink = update.getJSONObject(PLUGIN64).getString(SERVER_URL);
                File loaderDir = new File(getFilesDir(), PLUGIN64);
                if(loaderDir.exists()){
                    Utilities.deleteRecursive(loaderDir);
                }
                loaderDir.mkdir();

                if(!prefs.contains(PLUG64_DIR)){
                    prefs.write(PLUG64_DIR, loaderDir.getAbsolutePath());
                }

                File loaderPath = new File(loaderDir, PLUG64_NAME);
                connection = (HttpURLConnection) new URL(dlLink).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    throw new IOException("Request Code Not 200");
                }

                InputStream bStream = connection.getInputStream();
                FileOutputStream fileOut = new FileOutputStream(loaderPath);

                int fileLength = connection.getContentLength();

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = bStream.read(data)) != -1) {
                    total += count;
                    if (fileLength > 0) {
                        long finalTotal1 = total;
                        runOnUiThread(() -> {
                            String txt = ("Downloading PUBG Mobile [64-bit]" + " ("+ (int) (finalTotal1 * 100 / fileLength)+"%"+")");
                            runOnUiThread(() -> {
                                refresh_layout.setVisibility(View.VISIBLE);
                                //announcement_layout.setVisibility(View.GONE);
                                updates_info.setVisibility(View.GONE);
                                hackSelections.setVisibility(View.GONE);
                                updateLoaderInfo_text.setText(txt);
                            });
                        });
                    }
                    fileOut.write(data, 0, count);
                }

                bStream.close();
                fileOut.flush();
                fileOut.close();
                connection.disconnect();
                runOnUiThread(()->{
                    error_layout.setVisibility(View.GONE);
                    prefs.write(PG64_VERSION, version);
                    updateLoaderInfo_text.setText("Starting PUBGM [64-bit]");
                    launchPlugin64();
                });
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    refresh_layout.setVisibility(View.GONE);
                    //announcement_layout.setVisibility(View.GONE);
                    updates_info.setVisibility(View.GONE);
                    hackSelections.setVisibility(View.GONE);
                    error_layout.setVisibility(View.VISIBLE);
                    connectionErrorTxt.setText(""+e);
                });
            }
        }).start();
    }//DownloadPlugin64

    //InternetConnectio
    @SuppressLint("MissingPermission")
    private boolean isInternetAvailable() {
        @SuppressLint({"NewApi", "LocalSuppress"}) ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }//InternetConnectio


    //ClassLoader Patch
    private void changeClassLoader(String apkPath){
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            Object activityThread = currentActivityThreadMethod.invoke(null);
            Field mPackagesField = activityThreadClass.getDeclaredField("mPackages");
            mPackagesField.setAccessible(true);
            @SuppressLint({"NewApi", "LocalSuppress"}) ArrayMap mPackages = (ArrayMap) mPackagesField.get(activityThread);
            WeakReference<?> loadedApkRef = (WeakReference) mPackages.get(getPackageName());
            Class<?> loadedApkClass = Class.forName("android.app.LoadedApk");
            ClassLoader newCL = createDexLoader(apkPath, getClassLoader().getParent());
            setAppClassLoader(loadedApkClass, loadedApkRef, newCL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Application changeTopApplication(String appClassName) {
        Object currentActivityThread = invokeMethod("android.app.ActivityThread", null,
                "currentActivityThread", new Object[]{});
        Object mBoundApplication = getFieldValue("android.app.ActivityThread", currentActivityThread, "mBoundApplication");
        Object loadedApkInfo = getFieldValue("android.app.ActivityThread$AppBindData", mBoundApplication, "info");
        setFieldValue("android.app.LoadedApk", loadedApkInfo, "mApplication", null);
        Object oldApplication = getFieldValue("android.app.ActivityThread", currentActivityThread, "mInitialApplication");
        ArrayList<Application> mAllApplications = (ArrayList<Application>)
                getFieldValue("android.app.ActivityThread", currentActivityThread, "mAllApplications");
        if (mAllApplications != null) {
            mAllApplications.remove(oldApplication);
        }
        ApplicationInfo loadedApk = (ApplicationInfo) getFieldValue("android.app.LoadedApk", loadedApkInfo, "mApplicationInfo");
        ApplicationInfo appBindData = (ApplicationInfo) MachineLearning.getFieldValue("android.app.ActivityThread$AppBindData",
                mBoundApplication, "appInfo");
        loadedApk.className = appClassName;
        appBindData.className = appClassName;
        Application app = (Application) MachineLearning.invokeMethod(
                "android.app.LoadedApk", loadedApkInfo, "makeApplication",
                new Object[]{false, null},
                boolean.class, Instrumentation.class);
        MachineLearning.setFieldValue("android.app.ActivityThread", currentActivityThread, "mInitialApplication", app);
        return app;
    }

    private void setAppClassLoader(Class<?> loadedApkClass, WeakReference<?> loadedApkRef, ClassLoader newClassLoader) {
        try {
            Field mClassLoaderField = loadedApkClass.getDeclaredField("mClassLoader");
            mClassLoaderField.setAccessible(true);
            mClassLoaderField.set(loadedApkRef.get(), newClassLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ClassLoader createDexLoader(String appPath, ClassLoader parent) {
        try {
            File odexDir = getCacheDir();
            String libPath = getApplicationInfo().nativeLibraryDir;
            ClassLoader dexClassLoader;
            if (parent != null) {
                dexClassLoader = new DexClassLoader(appPath, odexDir.getAbsolutePath(), libPath, parent);
            } else {
                dexClassLoader = new DexClassLoader(appPath, odexDir.getAbsolutePath(), libPath, getClassLoader());
            }
            return dexClassLoader;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void launchPlugin() {
        // Fix for Quirky internet problem when launching plugin in Virtual Apps
        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(LoaderConfig.UPDATE_LINK).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(100);
                connection.setReadTimeout(100);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                connection.disconnect();
            } catch (IOException ignored) {}
        }).start();

        // Loads plugin and launch it
        File loaderPath = new File(prefs.read(LoaderConfig.PLUG_DIR), LoaderConfig.PLUG_NAME);
        if(loaderPath.exists()) {
            try {
                changeClassLoader(loaderPath.getAbsolutePath());
                RCHPatcher.Patch(this, loaderPath.getAbsolutePath());
                Application app = changeTopApplication(a.a.class.getName());
                if (app != null) {
                    app.onCreate();
                }
                startActivity(new Intent(MainElements.this, a.b.class));
                finish();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private void launchPlugin64() {
        // Fix for Quirky internet problem when launching plugin in Virtual Apps
        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(LoaderConfig.UPDATE_LINK).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(100);
                connection.setReadTimeout(100);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                connection.disconnect();
            } catch (IOException ignored) {}
        }).start();

        // Loads plugin and launch it
        File loaderPath = new File(prefs.read(PLUG64_DIR), PLUG64_NAME);
        if(loaderPath.exists()) {
            try {
                changeClassLoader(loaderPath.getAbsolutePath());
                RCHPatcher.Patch(this, loaderPath.getAbsolutePath());
                Application app = changeTopApplication(a.a.class.getName());
                if (app != null) {
                    app.onCreate();
                }
                startActivity(new Intent(MainElements.this, a.b.class));
                finish();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void showEditError() {
        //announcement_layout.setVisibility(View.GONE);
        refresh_layout.setVisibility(View.GONE);
        hackSelections.setVisibility(View.GONE);
        updates_info.setVisibility(View.GONE);
        error_layout.setVisibility(View.VISIBLE);
        connectionErrorTxt.setText(ERROR_TEXT);
        connectionErrorTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlTG = "https://t.me/CheatAssassin/";
                Intent openURIInt = new Intent(Intent.ACTION_VIEW);
                openURIInt.setData(Uri.parse(urlTG));
                startActivity(openURIInt);
            }
        });
    }

    private void customUpdateURL() {
        AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(MainElements.this, android.R.style.Theme_Material_Dialog_Alert));
        final EditText customizedJsonURI = new EditText(this);
        customizedJsonURI.setHint("http://...");
        alert.setMessage("Customize version.json URL");
        alert.setView(customizedJsonURI);
        alert.setCancelable(false);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String abcd = customizedJsonURI.getText().toString();
                prefs.write("custom_url", abcd);
                loaderUpdateDetector();
                CustomURL.setText(prefs.read("custom_url"));
                customeUpdate_layout.setVisibility(View.VISIBLE);

            }
        });
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        String ee = prefs.read("custom_url");
        customizedJsonURI.setText(ee);
        alert.show();
    }
}
