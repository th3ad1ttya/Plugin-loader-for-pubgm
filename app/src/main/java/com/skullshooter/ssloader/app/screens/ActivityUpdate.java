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
 | LastModified: Jan 5, 2022                               |
 |                                                          |
 \**********************************************************/
package com.skullshooter.ssloader.app.screens;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.res.ResourcesCompat;

import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.skullshooter.ssloader.app.BuildConfig;
import com.skullshooter.ssloader.app.R;
import com.skullshooter.ssloader.app.preferences.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;


import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ActivityUpdate extends Activity implements View.OnClickListener {
    public Preferences prefs;
    private static int PERMISSION_REQUEST_CODE = 1080;
    public TextView updateLog, appVersion, ttl, current_version, upcomingVersion, refresing, error_connection_txt, update_loader_error_info;
    public RelativeLayout updateNowBtn, continueWithCurrBtn, mainUpdateLayout, refresh_layout, error_function_layout;
    public LinearLayout refresh_bar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loader_update_activity);
        prefs = Preferences.with(this);

        updateLog = findViewById(R.id.update_info_txt);
        updateNowBtn = findViewById(R.id.download_loader);
        continueWithCurrBtn = findViewById(R.id.continue_with_current_version);
        appVersion = (TextView) findViewById(R.id.version);
        ttl = findViewById(R.id.updateLog);
        current_version = findViewById(R.id.current_version);
        upcomingVersion = findViewById(R.id.upcomingVersion);

        refresh_bar = findViewById(R.id.refresh_bar);

        mainUpdateLayout = findViewById(R.id.mainUpdateLayout);
        refresh_layout = findViewById(R.id.refresh_layout);
        refresing = findViewById(R.id.progress_tint_txt);
        error_function_layout = findViewById(R.id.error_function_layout);
        error_connection_txt = findViewById(R.id.error_connection_txt);
        update_loader_error_info = findViewById(R.id.update_loader_error_info);
        refresh_layout.setOnClickListener(this);
        updateNowBtn.setOnClickListener(this);
        continueWithCurrBtn.setOnClickListener(this);
        error_function_layout.setOnClickListener(this);

        Typeface spaceMonoRegular = ResourcesCompat.getFont(this, R.font.space_mono_regular);
        Typeface arabotoBold400 = ResourcesCompat.getFont(this, R.font.araboto_bold400);
        appVersion.setTypeface(spaceMonoRegular);
        ttl.setTypeface(arabotoBold400);

        update_loader_error_info.setText("Common issues while update:\n\nIf you are facing 'Apk not installed', Goto /sdcard/android/data/" + getPackageName() + "/files/(You'll get a apk named: Updated_loader.apk)\n\n* Get a backup of the latest loader(Updated_loader.apk) in your internal storage or as your wish.\n* After backup done, Uninstall old loader\n* After uninstall compleate, install the latest loader(Updated_loader.apk).\n\n\n********** \n\nAndroid version error like: 'There was a problem parsing the package', kindly contact with any of our admin. \n\n**********");



        //ExtraFunctions
        statusandNavBarColor();
        loaderUpdateDetector();
    }

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download_loader:
                if (checkPermission()) {
                    //UpdateApp atualizaApp = new UpdateApp();
                    //atualizaApp.setContext(ActivityUpdate.this);
                    //atualizaApp.execute("https://download.xncos.xyz/");
                    Uri uri = Uri.parse("https://download.skullshooter.com/"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                } else {
                    requestPermission();
                }
                break;

            case R.id.continue_with_current_version:
                finish();
                break;

            case R.id.error_function_layout:
                loaderUpdateDetector();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (locationAccepted && cameraAccepted) {
                    UpdateApp updateApp = new UpdateApp();
                    updateApp.setContext(ActivityUpdate.this);
                    updateApp.execute("https://loaderlink");
                }
            }
        }
    }//onRequestPermissionsResult

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }//checkPermission

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }//requestPermission


    public class UpdateApp extends AsyncTask<String, Integer, String> {
        private ProgressDialog mPDialog;
        private Context mContext;

        void setContext(Activity context) {
            mContext = context;
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPDialog = new ProgressDialog(new ContextThemeWrapper(ActivityUpdate.this, R.style.Theme_Material3_Dark_Dialog));
                    mPDialog.setMessage("Downloading...");
                    mPDialog.setIndeterminate(true);

                    mPDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mPDialog.setCancelable(false);
                    mPDialog.show();
                }
            });
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {

                URL url = new URL(arg0[0]);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();
                int lenghtOfFile = c.getContentLength();

                String PATH = Objects.requireNonNull(mContext.getExternalFilesDir(null)).getAbsolutePath();
                File file = new File(PATH);
                boolean isCreate = file.mkdirs();
                File outputFile = new File(file, "Updated_loader.apk");
                if (outputFile.exists()) {
                    boolean isDelete = outputFile.delete();
                }
                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = c.getInputStream();

                byte[] buffer = new byte[1024];
                int len1;
                long total = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    total += len1;
                    fos.write(buffer, 0, len1);
                    publishProgress((int) ((total * 100) / lenghtOfFile));
                }
                fos.close();
                is.close();
                if (mPDialog != null) {
                    Toast.makeText(ActivityUpdate.this, "Starting install", Toast.LENGTH_LONG).show();
                    installApk();
                    mPDialog.dismiss();
                }
            } catch (Exception e) {
                Log.e("UpdateAPP", "Update error! " + e.getMessage());
            }
            return null;
        }//doInBackground

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mPDialog != null)
                mPDialog.show();
        }//onPreExecute


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (mPDialog != null) {
                mPDialog.setIndeterminate(false);
                mPDialog.setMax(100);
                mPDialog.setProgress(values[0]);
            }
        }//onProgressUpdate

        @Override
        protected void onPostExecute(String result) {
            if (mPDialog != null)
                mPDialog.dismiss();
        }//onPostExecute

        private void installApk() {
            try {
                String PATH = Objects.requireNonNull(mContext.getExternalFilesDir(null)).getAbsolutePath();
                File file = new File(PATH + "/Updated_loader.apk");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (Build.VERSION.SDK_INT >= 24) {
                    Uri downloaded_apk = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", file);
                    intent.setDataAndType(downloaded_apk, "application/vnd.android.package-archive");
                    List<ResolveInfo> resInfoList = mContext.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        mContext.grantUriPermission(mContext.getApplicationContext().getPackageName() + ".provider", downloaded_apk, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);
                } else {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//installApk

    }//UpdateApp



    private void loaderUpdateDetector() {
        refresh_layout.setVisibility(View.VISIBLE);
        refresh_bar.setVisibility(View.VISIBLE);
        mainUpdateLayout.setVisibility(View.GONE);
        error_function_layout.setVisibility(View.GONE);
        refresing.setText("Downloading update...");
        //theredes();

    }

    @SuppressLint("MissingPermission")
    private boolean isInternetAvailable() {
        @SuppressLint({"NewApi", "LocalSuppress"}) ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }//InternetConnectio

    /*private void theredes() {
        new Thread(() -> {
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

                final String updateLogs = update.getJSONObject(UPDATE_TTL).getString(UPDATE_INFO);
                final String currAppVersion = prefs.read(LOADER_VERSION, BuildConfig.VERSION_NAME);

                final String newLoaderVersion = update.getJSONObject(LOADER).getString(VERSION);
                final String currentLoaderVersion = prefs.read(LOADER_VERSION, BuildConfig.VERSION_NAME);

                runOnUiThread(()->updateLog.setText(String.format("current version: %s | New version: %s\n\nWhat's new:\n%s",currAppVersion, newLoaderVersion, updateLogs, UPDATE_LINK)));

                runOnUiThread(() -> appVersion.setText(String.format("Loaded version: %s" + " | New version: %s", currentLoaderVersion, newLoaderVersion, UPDATE_LINK)));

                runOnUiThread(()->current_version.setText(String.format("%s", currAppVersion)));
                runOnUiThread(()->upcomingVersion.setText(String.format("%s", newLoaderVersion)));

                runOnUiThread(() -> {
                    refresh_layout.setVisibility(View.GONE);
                    refresh_bar.setVisibility(View.GONE);
                    mainUpdateLayout.setVisibility(View.VISIBLE);
                    error_function_layout.setVisibility(View.GONE);

                });


            } catch (IOException | JSONException e) {
                runOnUiThread(()->{
                    refresing.setVisibility(View.GONE);
                    refresh_bar.setVisibility(View.GONE);
                    mainUpdateLayout.setVisibility(View.GONE);
                    error_connection_txt.setText(""+e);
                    error_function_layout.setVisibility(View.VISIBLE);
                });
            }
        }).start();
    }*/

}
