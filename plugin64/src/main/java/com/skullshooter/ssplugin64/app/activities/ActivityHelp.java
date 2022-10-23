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
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.DATABASE;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.RAIN_WEATHER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.SNOW_WEATHER;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.WEATHER32;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.WEATHER_TYPE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.skullshooter.ssplugin64.app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityHelp extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        statusBarColorController();

        final ImageView back = findViewById(R.id.back);
        back.setOnClickListener(this);

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
    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
