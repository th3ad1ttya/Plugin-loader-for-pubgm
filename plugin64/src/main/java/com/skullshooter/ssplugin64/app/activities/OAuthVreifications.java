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
package com.skullshooter.ssplugin64.app.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.skullshooter.ssplugin64.app.BuildConfig;
import com.skullshooter.ssplugin64.app.R;
import com.skullshooter.ssplugin64.app.api.API;
import com.skullshooter.ssplugin64.app.preferences.Preferences;
import com.skullshooter.ssplugin64.app.secureENC.Authantications;
import com.skullshooter.ssplugin64.app.secureENC.RSA;

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

import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.CLEAR_WEATHER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.DAEMON64_VERSION;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.DATABASE;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.EXECUTION_CONTAINER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.EXECUTION_SUPPERUSER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.LOADER_VERSION;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.PLUGIN64;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.PLUGIN64_VERSION;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.RAIN_WEATHER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.SERVER_URL;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.SNOW_WEATHER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.VERSION;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.WEATHER32;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.WEATHER_TYPE;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.bit64native;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.pg64ARMV;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.pg64ARMV8ANative;

public class OAuthVreifications extends Activity implements View.OnClickListener {
    EditText userName, passWord;
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
        String daemonVersion = prefs.read(DAEMON64_VERSION);
        versionInformation = (TextView) findViewById(R.id.version);
        versionInformation.setText(String.format("SS. Loader version: %s.\n"+"Plugin version: %s | Daemon: %s(armv64)", loaderVersion, pluginVersion, daemonVersion));

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

        String rand = prefs.read(bit64native);
        if (rand.equals("")){
            prefs.write(bit64native, RSA.getRandomText(11));
        }

        prefs.write(PLUGIN64_VERSION, BuildConfig.VERSION_NAME);

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
                webView.loadUrl("https://freekey.xncos.xyz/");
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
                File randomNativeFile = new File(prefs.read(pg64ARMV) + "/" + prefs.read(bit64native));
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
        if (Build.VERSION.SDK_INT > 23) {
            downloadNative32v8();
        } else {
            new AlertDialog.Builder(mContext)
                    .setTitle("Error")
                    .setMessage("Device not compatible for this version. Required Android 9(Latest API-LEVEL 29)")
                    .show();
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

                        String download64V8 = object.getString("lib64ARMV8aUrl");
                        String versionLib = object.getString( "lib64ARMV8aVersion");
                        API._APP_LIB_V7a_VERSION = Objects.requireNonNull(versionLib.toString());
                        runOnUiThread(() -> {
                            downloadNativev8.execute(API._ROOT_URL + "uploads/libs/pg64/v8/archiver/" + download64V8);
                        });
                    }
                } catch (JSONException e) {
                    runOnUiThread(()->{
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
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
    } //RetRive32bitv8

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

                File ifAvailablev7File = new File(prefs.read(pg64ARMV) + "/" + prefs.read(bit64native));
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
                prefs.write(bit64native, RSA.getRandomText(11));
                native32v7name = prefs.read(bit64native);
                File system32v7 = new File(getFilesDir(), PLUGIN64);

                if (!prefs.contains(pg64ARMV)) {
                    prefs.write(pg64ARMV, system32v7.getAbsolutePath());
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
}



