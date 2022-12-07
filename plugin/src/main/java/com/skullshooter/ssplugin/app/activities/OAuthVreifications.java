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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.github.matteobattilana.weather.WeatherView;
import com.skullshooter.ssplugin.app.BuildConfig;
import com.skullshooter.ssplugin.app.R;
import com.skullshooter.ssplugin.app.api.API;
import com.skullshooter.ssplugin.app.preferences.Preferences;
import com.skullshooter.ssplugin.app.secureENC.Authantications;
import com.skullshooter.ssplugin.app.secureENC.RSA;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import static com.skullshooter.ssplugin.app.configuaration.AppConfig.DAEMON_VERSION;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.EXECUTION_CONTAINER;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.EXECUTION_SUPPERUSER;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.LOADER_VERSION;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.PLUGIN;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.PLUGIN_VERSION;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.bit32native;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.pgARMV;

public class OAuthVreifications extends Activity implements View.OnClickListener {
    EditText userName;
    EditText passWord;
    String sUsername, sPassword;
    ImageButton paste, clear;
    TextView changeExec;
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
    private final String USER = "USER";
    private final String PASS = "PASS";
    public static FrameLayout logFrag;
    public static FrameLayout layoutInfo;
    public static Button login_button;
    public static TextView aboutLogin, versionInformation, getKey;
    private Preferences prefs;

    private ProgressDialog mPDialog;
    private Context mContext;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oauthnatications);
        statusandNavBarColor();
        prefs = Preferences.with(this);
        logFrag = findViewById(R.id.logFrag);

        layoutInfo = findViewById(R.id.layoutInfo);
        login_button = findViewById(R.id.login_button);
        aboutLogin = findViewById(R.id.aboutLogin);
        getKey = findViewById(R.id.getKey);
        getKey.setOnClickListener(this);

        changeExec = findViewById(R.id.changeExec);
        changeExec.setOnClickListener(this);

        String loaderVersion = prefs.read(LOADER_VERSION);
        String pluginVersion = BuildConfig.VERSION_NAME;
        String daemonVersion = prefs.read(DAEMON_VERSION);
        versionInformation = (TextView) findViewById(R.id.version);
        versionInformation.setText(String.format("SS. Loader version: %s.\n"+"Plugin version: %s | Daemon: %s(arm-V7a)", loaderVersion, pluginVersion, daemonVersion));

        WeatherView weatherView = findViewById(R.id.weather_view);
        userName = findViewById(R.id.edit_ss_username);
        passWord = findViewById(R.id.edit_ss_password);
        paste = findViewById(R.id.paste);
        clear = findViewById(R.id.clear);
        String uName = prefs.read(USER);
        String pWord = prefs.read(PASS);
        userName.setText(uName);
        passWord.setText(pWord);

        paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasteKey();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName.setText(null);
                passWord.setText(null);
                paste.setVisibility(View.VISIBLE);
                clear.setVisibility(View.GONE);
            }
        });
        Button btnSignIn = findViewById(R.id.login_button);
        btnSignIn.setOnClickListener(this);

        String rand = prefs.read(bit32native);
        if (rand.equals("")){
            prefs.write(bit32native, RSA.getRandomText(11));
        }

        prefs.write(PLUGIN_VERSION, BuildConfig.VERSION_NAME);

        String uNameEdited = userName.getText().toString();
        String pWordEdited = passWord.getText().toString();

        if (uNameEdited.equals("")) {
            paste.setVisibility(View.VISIBLE);
            clear.setVisibility(View.GONE);
        } else {
            paste.setVisibility(View.GONE);
            clear.setVisibility(View.VISIBLE);
        }

        if (pWordEdited.equals("")) {
            paste.setVisibility(View.VISIBLE);
            clear.setVisibility(View.GONE);
        } else {
            paste.setVisibility(View.GONE);
            clear.setVisibility(View.VISIBLE);
        }
    } //onCreate

    @Override
    protected void onResume() {
        super.onResume();
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (userName.getText().toString().equals("")) {
                  paste.setVisibility(View.VISIBLE);
                  clear.setVisibility(View.GONE);
                } else {
                    paste.setVisibility(View.GONE);
                    clear.setVisibility(View.VISIBLE);
                }
            }
        });

        passWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (passWord.getText().toString().equals("")) {
                    paste.setVisibility(View.VISIBLE);
                    clear.setVisibility(View.GONE);
                } else {
                    paste.setVisibility(View.GONE);
                    clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    private void pasteKey() {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        String[] splitUNamendPWrord = clipboardManager.getPrimaryClip().getItemAt(0).getText().toString().split(":");
        for (int i = 0; i < splitUNamendPWrord.length; i++) {
            if (i == 0) {
                userName.setText(splitUNamendPWrord[0]);
            } else if (i == 1) {
                passWord.setText(splitUNamendPWrord[1]);
            }
            paste.setVisibility(View.GONE);
            clear.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getKey:
                LinearLayout layout = new LinearLayout(this);
                layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                WebView webView = new WebView(this);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                webView.loadUrl("https://key_gen_if_you_have");
                webView.setWebChromeClient(new WebChromeClient(){
                    ProgressDialog progressDialog = new ProgressDialog(OAuthVreifications.this);
                    public void onProgressChanged(WebView view1, int progress) {
                        progressDialog.setMessage("Please wait...");
                        progressDialog.show();
                        if (progress == 100) {
                            progressDialog.dismiss();
                        }
                    }
                });
                webView.setScrollContainer(true);
                layout.addView(webView);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(layout);
                builder.show();
                break;

            case R.id.changeExec:
                String changeExecutionSU = prefs.read(EXECUTION_SUPPERUSER);
                String changeExecutionContainer = prefs.read(EXECUTION_CONTAINER);
                if (changeExecutionSU.equals(EXECUTION_SUPPERUSER))
                {
                    prefs.remove(EXECUTION_SUPPERUSER);
                    getSharedPreferences(getPackageName() + "_execution", MODE_PRIVATE).edit().clear().commit();
                }
                else if (changeExecutionContainer.equals(EXECUTION_CONTAINER))
                {
                    prefs.remove(EXECUTION_CONTAINER);
                    getSharedPreferences(getPackageName() + "_execution", MODE_PRIVATE).edit().clear().commit();
                }
                Intent intentExecutionsFallBack = new Intent(this, a.b.class);
                startActivity(intentExecutionsFallBack);
                finish();
                break;
            case R.id.login_button:
                File randomNativeFile = new File(prefs.read(pgARMV) + "/" + prefs.read(bit32native));
                if (randomNativeFile.exists()) {
                    prefs.write("oAuthVerificationOpenedIsConfirmed?", "confirmedVerifyOauth");
                    logFrag.setVisibility(View.VISIBLE);
                    aboutLogin.setText(R.string.starttingDaemon);
                    layoutInfo.setVisibility(View.GONE);
                    login_button.setVisibility(View.GONE);
                    aboutLogin.setText(R.string.starttingDaemon);
                    finalThreades();
                } else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(OAuthVreifications.this, android.R.style.Theme_Material_Dialog_Alert))
                            .setCancelable(false)
                            .setTitle("Additional data needs to be download")
                            .setMessage("Additional data must be download to use this program. If you are using a mobile network, this may incur additional charges.");
                    dialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                            moveTaskToBack(false);
                            System.exit(0);
                        }
                    });
                    dialog.setPositiveButton("Download now", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            downloadFinalNative32bit();

                        }
                    });

                    final AlertDialog alert = dialog.create();
                    alert.show();
                }
                break;
        }
    }

    public void finalThreades() {
        new Thread(()->{
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(API._API).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Connection", "Colse");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    throw new IOException("Request Code Is Not 200OK");
                }

                JSONObject databaseConnection;

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
                        databaseConnection = new JSONObject(byteArrayOutputStream.toString("UTF-8"));
                        break;
                    }
                }
                runOnUiThread(()->{
                    prefs.write("oAuthVerificationOpenedIsConfirmed?", "confirmedVerifyOauth");
                    logFrag.setVisibility(View.VISIBLE);
                    aboutLogin.setText(R.string.starttingDaemon);
                    layoutInfo.setVisibility(View.GONE);
                    login_button.setVisibility(View.GONE);
                    aboutLogin.setText(R.string.starttingDaemon);
                    TextView textUsername = findViewById(R.id.edit_ss_username);
                    TextView textPassword = findViewById(R.id.edit_ss_password);
                    prefs.write(USER, textUsername.getText().toString());
                    prefs.write(PASS, textPassword.getText().toString());
                    new Authantications(OAuthVreifications.this).execute(textUsername.getText().toString(), textPassword.getText().toString());
                });
            } catch (IOException | JSONException e) {
                runOnUiThread(()->{
                    aboutLogin.setText("Sending device status to server");
                    Toast.makeText(this, "Auth error: Code 8: cannot find trust anchor certificate path exception", Toast.LENGTH_LONG).show();
                    logFrag.setVisibility(View.GONE);
                    aboutLogin.setText(R.string.starttingDaemon);
                    layoutInfo.setVisibility(View.VISIBLE);
                    login_button.setVisibility(View.VISIBLE);
                });
            }
        }).start();
    }


    //DownloadingDataStartFormHere
    public void downloadFinalNative32bit(){
        if (Build.VERSION.SDK_INT < 23) {
            downloadNative32v7();
        } else {
            downloadNative32v8();
        }

    } //finalDownloadByDeviceArchitecture

    private void downloadNative32v8() {
        downloadNativev8 downloadNativev8 = new downloadNativev8();
        downloadNativev8.setContext(OAuthVreifications.this);
        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(API._LIB_DATA).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                int responseCode = connection.getResponseCode();
                StringBuffer response = new StringBuffer();
                if (responseCode != 200) {
                    throw new IOException("Server communication failed, Request not send into server. Please try again.");
                }

                BufferedReader inUrl = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()
                ));

                String inputLine;
                while ((inputLine = inUrl.readLine()) != null) {
                    response.append(inputLine);
                }

                inUrl.close();
                String appData = response.toString();

                try {
                    JSONObject appDObject = new JSONObject(appData);
                    JSONArray appDArray = appDObject.getJSONArray("data");

                    for (int i = 0; i < appDObject.length(); i++) {
                        JSONObject object = appDArray.getJSONObject(i);
                        Log.d("APP_DATA=>>>> ", object.toString());

                        String download32V8 = object.getString("libARMV8aUrl");
                        String versionLib = object.getString("libARMV8aVersion");
                        API._APP_LIB_V7a_VERSION = Objects.requireNonNull(versionLib.toString());
                        runOnUiThread(() -> {
                            downloadNativev8.execute(API._ROOT_URL + "uploads/libs/pg32/v8/archiver/" + download32V8);
                        });
                    }
                } catch (Exception e) {
                    runOnUiThread(()->{
                        new AlertDialog.Builder(this)
                                .setTitle("Error")
                                .setMessage(e.getMessage())
                                .show();
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    //mPDialog.dismiss();
                    new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
                            .setCancelable(false)
                            .setTitle("Failed!")
                            .setMessage(e.getMessage())
                            .setNegativeButton("OK", (d, w) -> finish())
                            .show();
                });
            }
        }).start();
    } //RetRive32bitv8

    private void downloadNative32v7() {
        downloadNativev7 downloadNativev8 = new downloadNativev7();
        downloadNativev8.setContext(OAuthVreifications.this);
        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(API._LIB_DATA).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                int responseCode = connection.getResponseCode();
                StringBuffer response = new StringBuffer();
                if (responseCode != 200) {
                    throw new IOException("Server communication failed, Request not send into server. Please try again.");
                }

                BufferedReader inUrl = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()
                ));

                String inputLine;
                while ((inputLine = inUrl.readLine()) != null) {
                    response.append(inputLine);
                }

                inUrl.close();
                String appData = response.toString();

                try {
                    JSONObject appDObject = new JSONObject(appData);
                    JSONArray appDArray = appDObject.getJSONArray("data");

                    for (int i = 0; i < appDObject.length(); i++) {
                        JSONObject object = appDArray.getJSONObject(i);
                        Log.d("APP_DATA=>>>> ", object.toString());

                        String download32V7 = object.getString("libARMV7aUrl");
                        String versionLib = object.getString("libARMV7aVersion");
                        API._APP_LIB_V7a_VERSION = Objects.requireNonNull(versionLib.toString());
                        runOnUiThread(() -> {
                            downloadNativev8.execute(API._ROOT_URL + "uploads/libs/pg32/v7/archiver/" + download32V7);
                        });
                    }
                } catch (JSONException e) {

                }
            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    mPDialog.dismiss();
                    new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
                            .setCancelable(false)
                            .setTitle("Failed!")
                            .setMessage(""+e)
                            .setNegativeButton("OK", (d, w) -> finish())
                            .show();
                });
            }
        }).start();
    } //RetraiveData

    public class downloadNativev7 extends AsyncTask<String, Integer, String> {
        private ProgressDialog mPDialog;
        private Context mContext;

        void setContext(Activity context) {
            mContext = context;
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPDialog = new ProgressDialog(new ContextThemeWrapper(OAuthVreifications.this, R.style.Theme_Material3_Dark_Dialog));
                    mPDialog.setMessage("Downloading...");
                    mPDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mPDialog.setCancelable(false);
                    mPDialog.show();
                }
            });
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {

                File ifAvailablev7File = new File(prefs.read(pgARMV) + "/" + prefs.read(bit32native));
                if (ifAvailablev7File.exists()) {
                    ifAvailablev7File.delete();
                }

                URL url = new URL(arg0[0]);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();
                int lenghtOfFile = c.getContentLength();

                String native32v7name = null;
                prefs.write(bit32native, RSA.getRandomText(11));
                native32v7name = prefs.read(bit32native);
                File system32v7 = new File(getFilesDir(), PLUGIN);

                if (!prefs.contains(pgARMV)) {
                    prefs.write(pgARMV, system32v7.getAbsolutePath());
                }

                File loaderPath = new File(system32v7, native32v7name);

                FileOutputStream fos = new FileOutputStream(loaderPath);
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
            if (mPDialog != null) {
                mPDialog.dismiss();
            }
            if (result != null)
                Toast.makeText(mContext, "Download error: Code 440: " + result, Toast.LENGTH_LONG).show();
        }//onPostExecute

    }//Downloadv8Native

    public class downloadNativev8 extends AsyncTask<String, Integer, String> {
        private ProgressDialog mPDialog;
        private Context mContext;

        void setContext(Activity context) {
            mContext = context;
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPDialog = new ProgressDialog(new ContextThemeWrapper(OAuthVreifications.this, R.style.Theme_Material3_Dark_Dialog));
                    mPDialog.setMessage("Downloading...");
                    mPDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mPDialog.setCancelable(false);
                    mPDialog.show();
                }
            });
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {

                File ifAvailablev7File = new File(prefs.read(pgARMV) + "/" + prefs.read(bit32native));
                if (ifAvailablev7File.exists()) {
                    ifAvailablev7File.delete();
                }

                URL url = new URL(arg0[0]);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();
                int lenghtOfFile = c.getContentLength();

                String native32v7name = null;
                prefs.write(bit32native, RSA.getRandomText(11));
                native32v7name = prefs.read(bit32native);
                File system32v7 = new File(getFilesDir(), PLUGIN);

                if (!prefs.contains(pgARMV)) {
                    prefs.write(pgARMV, system32v7.getAbsolutePath());
                }

                File loaderPath = new File(system32v7, native32v7name);

                FileOutputStream fos = new FileOutputStream(loaderPath);
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
            if (result != null)
                Toast.makeText(mContext, "Download error: Code 440: " + result, Toast.LENGTH_LONG).show();
        }//onPostExecute

    }//Downloadv8Native

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

