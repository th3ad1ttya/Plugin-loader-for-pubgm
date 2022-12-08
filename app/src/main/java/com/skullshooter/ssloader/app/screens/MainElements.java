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

import static com.skullshooter.ssloader.app.api.API.ERROR_TEXT;
import static com.skullshooter.ssloader.app.api.API.IS_MAINTENANCE;
import static com.skullshooter.ssloader.app.api.API.LOADER_VERSION;
import static com.skullshooter.ssloader.app.api.API.PG64_VERSION;
import static com.skullshooter.ssloader.app.api.API.PG_32BIT_NAME;
import static com.skullshooter.ssloader.app.api.API.PG_64BIT_NAME;
import static com.skullshooter.ssloader.app.api.API.PG_VERSION;
import static com.skullshooter.ssloader.app.api.API.PLUG64_DIR;
import static com.skullshooter.ssloader.app.api.API.PLUG64_NAME;
import static com.skullshooter.ssloader.app.api.API.PLUGIN;
import static com.skullshooter.ssloader.app.api.API.PLUGIN64;
import static com.skullshooter.ssloader.app.api.API.PLUG_DIR;
import static com.skullshooter.ssloader.app.api.API.PLUG_NAME;
import static com.skullshooter.ssloader.app.api.API.SAFE;
import static com.skullshooter.ssloader.app.api.API.dataWasUpdatedAt;
import static com.skullshooter.ssloader.app.api.API.is32isSafe;
import static com.skullshooter.ssloader.app.api.API.is64isSafe;
import static com.skullshooter.ssloader.app.api.FetchAPI.APIplugin64Version;
import static com.skullshooter.ssloader.app.api.FetchAPI.downloadPlugin32WithDialogVersionFirstTime;
import static com.skullshooter.ssloader.app.api.FetchAPI.isInternetAvailable;
import static com.skullshooter.ssloader.app.loader.MachineLearning.getFieldValue;
import static com.skullshooter.ssloader.app.loader.MachineLearning.invokeMethod;
import static com.skullshooter.ssloader.app.loader.MachineLearning.setFieldValue;

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
import com.skullshooter.ssloader.app.api.API;
import com.skullshooter.ssloader.app.api.FetchAPI;
import com.skullshooter.ssloader.app.loader.MachineLearning;
import com.skullshooter.ssloader.app.loader.RCHPatcher;
import com.skullshooter.ssloader.app.loader.Utilities;
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
        updates_info = findViewById(R.id.updates_info); //layout
        updates_info.setOnClickListener(this);
        hackSelections = findViewById(R.id.hackSelections); //layout
        error_layout = findViewById(R.id.error_function_layout); //Layout
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
        prefs.write(dataWasUpdatedAt, "Data was updated at");

        download_launch_pg_32bit.setOnClickListener(this);
        download_launch_pg_64bit.setOnClickListener(this);
        String loaderVersion = prefs.read(LOADER_VERSION);
        titleLoader.setText("CheatAssassin Loader v"+loaderVersion);

        //SafetyStatus
        prefs.write(is32isSafe, SAFE);
        prefs.write(is64isSafe, SAFE);
        prefs.write("IS_MAINTENANCE", IS_MAINTENANCE);

        //MainImplements
        loaderUpdateDetector();
        existingAndVersionChecker();
        dataWasUpdated();

        // Removing the custom url from the shared preferences.
        customLinkReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.remove("custom_url");
                customeUpdate_layout.setVisibility(View.GONE);
                loaderUpdateDetector();
            }
        });

        // Setting a long click listener on the updates_info TextView.
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

    /**
     * It changes the status bar and navigation bar color.
     */
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

    private void dataWasUpdated() {
        data_was_updated_on.setText(": " + DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_12HOUR));
    }

    /**
     * It fetches the data from the API and displays it on the screen
     */
    private void loaderUpdateDetector() {
        refresh_layout.setVisibility(View.VISIBLE);
        updateLoaderInfo_text.setText("Refreshing loader info...");
        updates_info.setVisibility(View.GONE);
        hackSelections.setVisibility(View.GONE);
        error_layout.setVisibility(View.GONE);
        FetchAPI.fetchData(this, connectionErrorTxt, refresh_layout, updates_info, hackSelections,
                error_layout, announcements, safety32bit, safety64bit,
                download_launch_pg_32bit, installation_status_32bit, installation_status_64bit,
                updateLoaderInfo_text, download_launch_pg_64bit);
    }

    /**
     * If the plugin exists, then set the onClickListener to launch the plugin
     */
    private void existingAndVersionChecker()
    {

        File pluginPath = new File(prefs.read(PLUG_DIR), PLUG_NAME);
        File plugin64Path = new File(prefs.read(PLUG64_DIR), PLUG64_NAME);

        if (pluginPath.exists())
        {
            runOnUiThread(()->{
                download_launch_pg_32bit.setOnClickListener(v -> {
                    launchPlugin();
                });
            });
        }//plugin

        if (plugin64Path.exists())
        {
            download_launch_pg_64bit.setOnClickListener(v -> {
                launchPlugin64();
            });
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
                    launchPlugin();
                }
                break;

            case R.id.selection_64bit:
                /** TODO implement plugin64Download here*/
                File plugin64 = new File(prefs.read(PLUG64_DIR, PLUG64_NAME));
                if (plugin64.exists()) {
                    launchPlugin64();
                }
                break;

            case R.id.error_function_layout:
                loaderUpdateDetector();
                break;
            case R.id.updates_info:
                loaderUpdateDetector();
                break;
        }

    }


    /**
     * > We get the current activity thread, get the mPackages field, get the loadedApk reference, and
     * set the classloader to our new classloader
     *
     * @param apkPath The path of the apk file to be loaded
     */
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

    /**
     * It changes the top application to the one specified by the appClassName parameter
     *
     * @param appClassName The name of the application class to be loaded.
     * @return The application object.
     */
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

    /**
     * It sets the classloader of the loadedApk to the newClassLoader.
     *
     * @param loadedApkClass The LoadedApk class.
     * @param loadedApkRef The reference to the LoadedApk object.
     * @param newClassLoader The new classloader to be set.
     */
    private void setAppClassLoader(Class<?> loadedApkClass, WeakReference<?> loadedApkRef, ClassLoader newClassLoader) {
        try {
            Field mClassLoaderField = loadedApkClass.getDeclaredField("mClassLoader");
            mClassLoaderField.setAccessible(true);
            mClassLoaderField.set(loadedApkRef.get(), newClassLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It creates a new DexClassLoader with the given appPath, odexDir, libPath and parent
     *
     * @param appPath The path of the apk file
     * @param parent The parent classloader of the new classloader.
     * @return A ClassLoader object.
     */
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


    /**
     * patches it, and launches it
     */
    public void launchPlugin() {
        // Fix for Quirky internet problem when launching plugin in Virtual Apps
        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(API._API).openConnection();
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
        File loaderPath = new File(prefs.read(API.PLUG_DIR), API.PLUG_NAME);
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
                HttpURLConnection connection = (HttpURLConnection) new URL(API._API).openConnection();
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

    /**
     * It creates a dialog box with an edit text field, and when the user clicks OK, it saves the text
     * in the edit text field to a variable called "abcd", and then saves that variable to the shared
     * preferences
     */
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
