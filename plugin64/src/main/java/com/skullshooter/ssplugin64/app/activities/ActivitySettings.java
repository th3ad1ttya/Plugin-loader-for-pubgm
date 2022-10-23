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

import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.CLEAR_WEATHER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.RAIN_WEATHER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.SNOW_WEATHER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.DATABASE;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.WEATHER32;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.WEATHER_TYPE;
import static com.skullshooter.ssplugin64.app.configuaration.Protections.dirP1;
import static com.skullshooter.ssplugin64.app.configuaration.Protections.dirP2;
import static com.skullshooter.ssplugin64.app.configuaration.Protections.dirP3;
import static com.skullshooter.ssplugin64.app.configuaration.Protections.dirP4;
import static com.skullshooter.ssplugin64.app.configuaration.Protections.p1;
import static com.skullshooter.ssplugin64.app.configuaration.Protections.p2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.google.android.material.snackbar.Snackbar;
import com.skullshooter.ssplugin64.app.R;
import com.skullshooter.ssplugin64.app.preferences.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivitySettings extends Activity implements View.OnClickListener {
    Preferences prefs;
    RelativeLayout selectVersion, deviceDetails, protectionSheild;
    TextView cn_versionInfo;
    ImageView back;
    RadioButton global, korean, taiwan, vietnam, india;
    TextView save;
    public static int item;
    public static int index;
    private static final int REQ_CODE = 1111;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        statusBarColorController();
        prefs = Preferences.with(this);
        selectVersion = findViewById(R.id.selectVersion);
        selectVersion.setOnClickListener(this);
        protectionSheild = findViewById(R.id.protectionSheild);
        protectionSheild.setOnClickListener(this);
        cn_versionInfo = findViewById(R.id.cn_versionInfo);
        deviceDetails = findViewById(R.id.deviceDetails);
        deviceDetails.setOnClickListener(this);

        WeatherView weatherView = findViewById(R.id.weather_view);
        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(DATABASE).openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.setRequestProperty("Connection", "close");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    throw new IOException("Server communication failed, Request not send into server. Please try again.");
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
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        String version = prefs.read("version");


        if (version.equals("Global")) {
            cn_versionInfo.setText(prefs.read("version"));
        } else if (version.equals("Korean")) {
            cn_versionInfo.setText(prefs.read("version"));
        } else if (version.equals("Vietnam")) {
            cn_versionInfo.setText(prefs.read("version"));
        } else if (version.equals("Taiwan")) {
            cn_versionInfo.setText(prefs.read("version"));
        } else if (version.equals("India")) {
            cn_versionInfo.setText(prefs.read("version"));
        } else {
            cn_versionInfo.setText("[Not-selected]");
        }
    } //onCreate

    @SuppressLint("NewApi")
    public void statusBarColorController() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.ss_Color_primary));
        }
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.ss_Color_primary));
        window.getDecorView().setSystemUiVisibility(Color.TRANSPARENT);
    }//statusBarColorController

    private void saveValues(String key, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + "_GameVersion", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    boolean getConfig(String key) {
        SharedPreferences sp = this.getSharedPreferences(getPackageName() + "_GameVersion", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.selectVersion:
                dialogItems();
                break;
            case R.id.back:
                finish();
                break;

            case R.id.deviceDetails:

                final AlertDialog.Builder dialogDeviceInfo = new AlertDialog.Builder(new ContextThemeWrapper(ActivitySettings.this, android.R.style.Theme_Material_Dialog_Alert))
                        .setMessage("Brand: "+ getDeviceName() + "\nModel: " + getDeviceModel() + "\nSerial No: " + getDeviceSerialNumber() + "\nUUID: " + getDeviceImei(this))

                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alertDetails = dialogDeviceInfo.create();
                alertDetails.show();
                break;

            case R.id.protectionSheild:
                final Switch protectionSheildSwitch = findViewById(R.id.protectionSheildSwitch);
                if (protectionSheildSwitch.isChecked()) {
                    protectionSheildSwitch.setChecked(false);
                } else {
                    protectionSheildSwitch.setChecked(true);
                }
                break;
        }
    }
    public String getDeviceSerialNumber() {
        return Build.SERIAL;
    }
    public String getDeviceModel() {
        return Build.MODEL;
    }
    public String getDeviceName() {
        return Build.BRAND;
    }

    public static String getDeviceImei(Context context) {
        String deviceID;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            deviceID = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.ANDROID_ID
            );
        } else {
            final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
            if (mTelephony.getDeviceId() != null) {
                deviceID = mTelephony.getDeviceId();
            } else {
                deviceID = Settings.Secure.getString(
                        context.getContentResolver(),
                        Settings.Secure.ANDROID_ID
                );
            }
        }
        return deviceID;
    }


    private void dialogItems() {
        Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.version_selection);
        customDialog.show();
        customDialog.setCancelable(true);
        global = customDialog.findViewById(R.id.vGlobal);
        korean = customDialog.findViewById(R.id.vKorean);
        vietnam = customDialog.findViewById(R.id.vVietnam);
        taiwan = customDialog.findViewById(R.id.vTaiwan);
        india = customDialog.findViewById(R.id.vIndia);

        save = customDialog.findViewById(R.id.saveVersion);


        global.setChecked(getConfig((String) global.getText()));
        korean.setChecked(getConfig((String) korean.getText()));
        vietnam.setChecked(getConfig((String) vietnam.getText()));
        taiwan.setChecked(getConfig((String) taiwan.getText()));
        india.setChecked(getConfig((String) india.getText()));


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (global.isChecked()) {
                    prefs.write("version", "Global");
                    saveValues(String.valueOf(global.getText()), global.isChecked());
                    saveValues(String.valueOf(korean.getText()), false);
                    saveValues(String.valueOf(vietnam.getText()), false);
                    saveValues(String.valueOf(taiwan.getText()), false);
                    saveValues(String.valueOf(india.getText()), false);
                }
                if (korean.isChecked()) {
                    prefs.write("version", "Korean");
                    saveValues(String.valueOf(korean.getText()), korean.isChecked());
                    saveValues(String.valueOf(global.getText()), false);
                    saveValues(String.valueOf(vietnam.getText()), false);
                    saveValues(String.valueOf(taiwan.getText()), false);
                    saveValues(String.valueOf(india.getText()), false);
                }
                if (vietnam.isChecked()) {
                    prefs.write("version", "Vietnam");
                    saveValues(String.valueOf(vietnam.getText()), vietnam.isChecked());
                    saveValues(String.valueOf(korean.getText()), false);
                    saveValues(String.valueOf(global.getText()), false);
                    saveValues(String.valueOf(taiwan.getText()), false);
                    saveValues(String.valueOf(india.getText()), false);
                }
                if (taiwan.isChecked()) {
                    prefs.write("version", "Taiwan");
                    saveValues(String.valueOf(taiwan.getText()), taiwan.isChecked());
                    saveValues(String.valueOf(korean.getText()), false);
                    saveValues(String.valueOf(vietnam.getText()), false);
                    saveValues(String.valueOf(global.getText()), false);
                    saveValues(String.valueOf(india.getText()), false);
                }
                if (india.isChecked()) {
                    prefs.write("version", "India");
                    saveValues(String.valueOf(india.getText()), india.isChecked());
                    saveValues(String.valueOf(korean.getText()), false);
                    saveValues(String.valueOf(vietnam.getText()), false);
                    saveValues(String.valueOf(taiwan.getText()), false);
                    saveValues(String.valueOf(global.getText()), false);
                }
                customDialog.dismiss();
                cn_versionInfo.setText(prefs.read("version"));
            }
        });
    }
}
