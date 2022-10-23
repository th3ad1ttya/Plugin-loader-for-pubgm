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

import static com.skullshooter.ssplugin.app.configuaration.AppConfig.CLEAR_WEATHER;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.DAEMON;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.DAEMON_DIR;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.DAEMON_VERSION;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.DATABASE;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.EXECUTION_CONTAINER;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.EXECUTION_SUPPERUSER;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.GLOBAL;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.ID_GLOBAL;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.ID_INDIA;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.ID_KOREAN;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.ID_TAIWAN;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.ID_VIETNAM;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.INDIA;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.KOREAN;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.LOADER_VERSION;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.PLUGIN;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.PLUGIN_VERSION;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.RAIN_WEATHER;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.SERVER_URL;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.SNOW_WEATHER;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.TAIWAN;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.UPDATE_LINK;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.VERSION;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.VIETNAM;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.WEATHER32;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.WEATHER_TYPE;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.bit32native;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.pgARMV;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.pgARMV7ANative;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.pgARMV7ANative_version;
import static com.skullshooter.ssplugin.app.configuaration.AppConfig.pgARMVSO;
import static com.skullshooter.ssplugin.app.manager.SuperSU.isRootGiven;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.skullshooter.ssplugin.app.BuildConfig;
import com.skullshooter.ssplugin.app.R;
import com.skullshooter.ssplugin.app.preferences.ColorizedPrefs;
import com.skullshooter.ssplugin.app.preferences.Preferences;
import com.skullshooter.ssplugin.app.secureENC.RSA;
import com.topjohnwu.superuser.Shell;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Daemon extends Activity implements View.OnClickListener {
    @SuppressLint("MissingPermission")
    public void onBackPressed() {
        try {
            ActivityManager actvityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
            actvityManager.killBackgroundProcesses(this.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finishAffinity();
        moveTaskToBack(false);
        super.onBackPressed();
        System.exit(0);
    }
    private final String EXPIRED = "EXPIRED";
    private final String SUBS_LEFT = "Subscription time left: ";
    private final String DAYS = " days ";
    private final String MINUTES = " minutes ";
    private final String HOURS = " hours ";
    private String pkg;
    private int version;
    private TextView expiredKeyViewText;
    private static boolean isRunning = false;
    public String gameName = "com.tencent.ig";
    public static int gameType = 1;
    static int bit = 1;
    private boolean isDisplay = false;
    private boolean isMenuDis = false;
    Context ctx;
    View menu;
    public static String socket;
    Process process;
    public String daemonPath;
    public boolean onESP = false;
    public boolean isDaemon = false;
    Preferences prefs;
    ColorizedPrefs cPrefs;
    LinearLayout btnStart, btnStop;
    RelativeLayout logOutLayout, importSettings, exportSettings, settings, help, startLayout;
    public TextView pluginVersionInfo, linklink, webAddr;
    static String loginSuccess = "loginSuccess";
    static String isSuccess = "statusSuccess";
    public static ProgressDialog mPDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daemon);
        statusandNavBarColor();
        setTheme(R.style.Theme_Plugin);


        WeatherView weatherView = findViewById(R.id.weather_view);
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

        ctx = this;
        prefs = Preferences.with(this);
        cPrefs = ColorizedPrefs.with(this);
        String verifications = prefs.read("oAuthVerificationOpenedIsConfirmed?");
        if (!verifications.equals("confirmedVerifyOauth")) {
            System.exit(0);
            Toast.makeText(this, "Something wents wrong with your loader\n Re-download the official verison", Toast.LENGTH_LONG).show();
        }
        String arcitectureInfo = prefs.read("architecture");
        String executionContainer = prefs.read(EXECUTION_CONTAINER);
        if (arcitectureInfo.equals("ARMv7a(32-bit)") && executionContainer.equals(EXECUTION_CONTAINER)) {

            File system32v7 = new File(Preferences.getInstance().read(pgARMV) + "/" + Preferences.getInstance().read(bit32native));
            if (system32v7.exists()) {
              //  downloadNative32v7();
            }
        }

        CheckFloatViewPermission();
        if (!isConfigExist()) {
            Init();
        }
        btnStart = (LinearLayout) findViewById(R.id.startCheats);
        btnStop = (LinearLayout) findViewById(R.id.kill);
        logOutLayout = findViewById(R.id.logOutLayout);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        logOutLayout.setOnClickListener(this);

        importSettings = findViewById(R.id.importSettings);
        exportSettings = findViewById(R.id.exportSettings);
        settings = findViewById(R.id.settings_layout);
        help = findViewById(R.id.help_layout);
        importSettings.setOnClickListener(this);
        exportSettings.setOnClickListener(this);
        settings.setOnClickListener(this);
        help.setOnClickListener(this);

        linklink = findViewById(R.id.linklink);
        linklink.setOnClickListener(this);
        Typeface typeFaceVersion = ResourcesCompat.getFont(this, R.font.space_mono_regular);

        pluginVersionInfo = findViewById(R.id.versionLoader);
        pluginVersionInfo.setTypeface(typeFaceVersion);
        String loaderVersion = prefs.read(LOADER_VERSION);
        String pluginVersion = BuildConfig.VERSION_NAME;
        String daemonVersion = prefs.read(DAEMON_VERSION);

        webAddr = findViewById(R.id.webAddr);
        webAddr.setOnClickListener(this);

        pluginVersionInfo.setText(String.format("SS. Loader version: %s.\n"+"Plugin version: %s | Daemon: %s(armv7a)", loaderVersion, pluginVersion, daemonVersion));

        startLayout = findViewById(R.id.startLayout);

        ExecuteElf("su -c");
        randomDaemon();
        socket = daemonPath;
        getVersions();
        countDownKeySubscriptionTimer();
    }
    Handler handler = new Handler();
    private void countDownKeySubscriptionTimer() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    long duration = Long.parseLong(prefs.read(EXPIRED, ""));
                    long now = Calendar.getInstance().getTimeInMillis();
                    long distance = duration - now;
                    long days = distance / (24 * 60 * 60 * 1000);
                    long hours = distance / (60 * 60 * 1000) % 24;
                    long minutes = distance / (60 * 1000) % 60;
                    long seconds = distance / 1000 % 60;
                    if (distance < 0) {
                    } else{
                        expiredKeyViewText = findViewById(R.id.subsLeft);
                        if (days == 0 && hours == 0 && minutes == 0) {
                            expiredKeyViewText.setText(String.format(SUBS_LEFT + "%02d"));
                        }else if (days == 0 && hours == 0) {
                            expiredKeyViewText.setText(String.format(SUBS_LEFT + "%02d", minutes) + MINUTES);
                        }else if (days == 0) {
                            expiredKeyViewText.setText(String.format(SUBS_LEFT + "%02d", hours) + HOURS + String.format("%02d", minutes) + MINUTES);
                        }else {
                            expiredKeyViewText.setText(String.format(SUBS_LEFT + "%02d", days) + DAYS + String.format("%02d", hours) + HOURS);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }
    private void getVersions() {
        final String loader_version = prefs.read(LOADER_VERSION);
        final String plugin_version = prefs.read(PLUGIN_VERSION);
        String daemon_type = "arm-v7a";

        //versionLoader.setText(String.format("XNCOS. Loader version: %s\nPlugin version: %s | Daemon: "+daemon_type, loader_version, plugin_version));
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

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startCheats:
                daemon();
                break;
            case R.id.kill:
                if (isDisplay) {
                    isDisplay = false;
                    isMenuDis = false;
                    isDaemon = false;

                    stopService(new Intent(this, a.f.class));
                    stopService(new Intent(this, a.g.class));
                }
                break;
            case R.id.importSettings:
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                boolean importSettingsClipboard = Boolean.parseBoolean(item.getText().toString());
                SharedPreferences fp = getSharedPreferences("activeValues", MODE_PRIVATE);
                String vName = null;
                fp.edit().putBoolean(vName, importSettingsClipboard).apply();
                break;
            case R.id.exportSettings:
                ClipboardManager clipboardManager1 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                SharedPreferences pf = getSharedPreferences("activeValues", MODE_PRIVATE);
                String ssssss = String.valueOf(pf.getAll());
                String bbbbbb = String.valueOf(cPrefs.getAll());
                ClipData clipData = ClipData.newPlainText("Settings", ssssss);
                ClipData clipData1 = ClipData.newPlainText("Settings", bbbbbb);
                clipboardManager1.setPrimaryClip(clipData);
                clipboardManager1.setPrimaryClip(clipData1);

                break;
            case R.id.settings_layout:
                Intent settingsInt = new Intent(this, a.d.class);
                startActivity(settingsInt);
                break;
            case R.id.logOutLayout:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(Daemon.this, android.R.style.Theme_Material_Dialog_Alert))
                        .setCancelable(false)
                        .setMessage("Do you want logout now?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Daemon.this, a.l.class);
                        startActivity(intent);
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                final AlertDialog alert = dialog.create();
                alert.show();
                break;
            case R.id.help_layout:
                Intent newHelpActivityIntent = new Intent(this, a.e.class);
                startActivity(newHelpActivityIntent);
                break;

            case R.id.linklink:
                String urlWeb = "https://xncos.xyz/";

                Intent intentOpenUri = new Intent(Intent.ACTION_VIEW);
                intentOpenUri.setData(Uri.parse(urlWeb));
                startActivity(intentOpenUri);
                break;

            case R.id.webAddr:
                if (webAddr != null) {
                    webAddr.setMovementMethod(LinkMovementMethod.getInstance());
                }
                break;
        }
    }

    @SuppressLint("NewApi")
    public void CheckFloatViewPermission() {
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "Enable Floating Permission ", Toast.LENGTH_LONG).show();
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        }
    }

    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (a.f.class.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    void startPatcher() {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 123);
        } else {
            startFloater();
        }
    }

    private void startFloater() {
        if (!isServiceRunning()) {
            startService(new Intent(this, a.f.class));
        } else {
            Toast.makeText(this, "Cheats already executed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void ExecuteElf(String shell) {
        String s = shell;
        try {
            Runtime.getRuntime().exec(s, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Shell(String str) {
        DataOutputStream dataOutputStream = null;
        try {
            process = Runtime.getRuntime().exec(str);
        } catch (IOException e) {
            e.printStackTrace();
            process = null;
        }
        if (process != null) {
            dataOutputStream = new DataOutputStream(process.getOutputStream());
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
    }

    public void startDaemon() {
        new Thread() {

            public void run() {
                Shell(socket);
            }

        }.start();
    }

    public void loadDaemon() {
        daemonPath = prefs.read(DAEMON_DIR) + "/" + prefs.read(DAEMON);
        try {
            Runtime.getRuntime().exec("chmod 777 " + daemonPath);
        } catch (RuntimeException | IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void Init() {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("fps", 60);
        ed.apply();
    }

    boolean isConfigExist() {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        return sp.contains("fps");
    }

    void daemon() {
        String version = prefs.read("version");
        String executionSu = prefs.read(EXECUTION_SUPPERUSER);
        String executionContainer = prefs.read(EXECUTION_CONTAINER);
        if (executionSu.equals(EXECUTION_SUPPERUSER))
        {
            if (!isDisplay && !isMenuDis) {
                if (version.equals(GLOBAL)) {
                    if (getProcessID(ID_GLOBAL) < 0) {
                        gameNotRunningWarn();
                    } else {
                        socket = "su -c " + daemonPath;
                        ExecuteElf("su -c");
                        gameType = 1;
                        gameName = GLOBAL;
                        startPatcher();
                        startService(new Intent(this, a.g.class));
                        isDisplay = true;
                        isDaemon = true;
                    }
                } else if (version.equals(KOREAN)) {
                    if (getProcessID(ID_KOREAN) < 0) {
                        gameNotRunningWarn();
                    } else {
                        socket = "su -c " + daemonPath;
                        ExecuteElf("su -c");
                        gameType = 2;
                        gameName = KOREAN;
                        startPatcher();
                        startService(new Intent(this, a.g.class));
                        isDisplay = true;
                        isDaemon = true;
                    }
                } else if (version.equals(VIETNAM)) {
                    if (getProcessID(ID_VIETNAM) < 0) {
                        gameNotRunningWarn();
                    } else {
                        socket = "su -c " + daemonPath;
                        ExecuteElf("su -c");
                        gameType = 3;
                        gameName = VIETNAM;
                        startPatcher();
                        startService(new Intent(this, a.g.class));
                        isDisplay = true;
                        isDaemon = true;
                    }
                } else if (version.equals(TAIWAN)) {
                    if (getProcessID(ID_TAIWAN) < 0) {
                        gameNotRunningWarn();
                    } else {
                        socket = "su -c " + daemonPath;
                        ExecuteElf("su -c");
                        gameType = 4;
                        gameName = TAIWAN;
                        startPatcher();
                        startService(new Intent(this, a.g.class));
                        isDisplay = true;
                        isDaemon = true;
                    }
                } else if (version.equals(INDIA)) {
                    if (getProcessID(ID_INDIA) < 0) {
                        gameNotRunningWarn();
                    } else {
                        socket = "su -c " + daemonPath;
                        ExecuteElf("su -c");
                        gameType = 5;
                        gameName = INDIA;
                        startPatcher();
                        startService(new Intent(this, a.g.class));
                        isDisplay = true;
                        isDaemon = true;
                    }
                } else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(Daemon.this, android.R.style.Theme_Material_Dialog_Alert))
                            .setCancelable(false)
                            .setMessage("Please select your pubg mobile version first for execute!");
                    dialog.setPositiveButton("Let's go", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Daemon.this, a.d.class);
                            startActivity(intent);
                        }
                    });
                    final AlertDialog alert = dialog.create();
                    alert.show();
                }
            }
        }

        if (executionContainer.equals(EXECUTION_CONTAINER))
        {
            if (!isDisplay && !isMenuDis) {
                if (version.equals(GLOBAL)) {
                    socket = daemonPath;
                    gameType = 1;
                    gameName = GLOBAL;
                    startPatcher();
                    startService(new Intent(this, a.g.class));
                    isDisplay = true;
                    isDaemon = true;
                } else if (version.equals(KOREAN)) {
                    socket = daemonPath;
                    gameType = 2;
                    gameName = KOREAN;
                    startPatcher();
                    startService(new Intent(this, a.g.class));
                    isDisplay = true;
                    isDaemon = true;
                } else if (version.equals(VIETNAM)) {
                    socket = daemonPath;
                    gameType = 3;
                    gameName = VIETNAM;
                    startPatcher();
                    startService(new Intent(this, a.g.class));
                    isDisplay = true;
                    isDaemon = true;
                } else if (version.equals(TAIWAN)) {
                    socket = daemonPath;
                    gameType = 4;
                    gameName = TAIWAN;
                    startPatcher();
                    startService(new Intent(this, a.g.class));
                    isDisplay = true;
                    isDaemon = true;
                } else if (version.equals(INDIA)) {
                    socket = daemonPath;
                    gameType = 5;
                    gameName = INDIA;
                    startPatcher();
                    startService(new Intent(this, a.g.class));
                    isDisplay = true;
                    isDaemon = true;
                } else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(Daemon.this, android.R.style.Theme_Material_Dialog_Alert))
                            .setCancelable(false)
                            .setMessage("Please select your pubg mobile version first for execute!");
                    dialog.setPositiveButton("Let's go", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Daemon.this, a.d.class);
                            startActivity(intent);
                        }
                    });
                    final AlertDialog alert = dialog.create();
                    alert.show();
                }
            }
        }
    }

    private void gameNotRunningWarn() {
        startService(new Intent(Daemon.this, a.f.class));
        startService(new Intent(Daemon.this, a.g.class));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Daemon.this, "Game is not running!", Toast.LENGTH_LONG).show();
                stopService(new Intent(Daemon.this, a.f.class));
                stopService(new Intent(Daemon.this, a.g.class));
                isDisplay = false;
                isDaemon = false;
            }
        }, 400);
    }

    private int getProcessID(String pkgName) {
        int pid = -1;
        if (Shell.rootAccess()) {
            String cmd = "for p in /proc/[0-9]*; do [[ $(<$p/cmdline) = " + pkgName + " ]] && echo ${p##*/}; done";
            List<String> outs = new ArrayList<>();
            Shell.su(cmd).to(outs).exec();
            if (outs.size() > 0) {
                pid = Integer.parseInt(outs.get(0));
            }
        } else {
            Shell.Result out = Shell.sh("/system/bin/ps -A | grep \"" + pkgName + "\"").exec();
            List<String> output = out.getOut();
            if (output.isEmpty() || output.get(0).contains("bad pid")) {
                out = Shell.sh("/system/bin/ps | grep \"" + pkgName + "\"").exec();
                output = out.getOut();
                if (!output.isEmpty() && !output.get(0).contains("bad pid")) {
                    for (int i = 0; i < output.size(); i++) {
                        String[] results = output.get(i).trim().replaceAll("( )+", ",").replaceAll("(\n)+", ",").split(",");
                        if (results[8].equals(pkg)) {
                            pid = Integer.parseInt(results[1]);
                        }
                    }
                }
            } else {
                for (int i = 0; i < output.size(); i++) {
                    String[] results = output.get(i).trim().replaceAll("( )+", ",").replaceAll("(\n)+", ",").split(",");
                    for (int j = 0; j < results.length; j++) {
                        if (results[j].equals(pkg)) {
                            pid = Integer.parseInt(results[j - 7]);
                        }
                    }
                }
            }
        }
        return pid;
    }

    public void randomDaemon() {
        File daemon = new File(prefs.read(DAEMON_DIR) + "/" + prefs.read(DAEMON));
        if (daemon.exists()) {
            daemon.delete();
        }
        String fileName = null;
        prefs.write(DAEMON, RSA.getRandomText(8));
        fileName = prefs.read(DAEMON);

        File daemonDir = new File(getFilesDir(), PLUGIN);
        if(!prefs.contains(DAEMON_DIR)){
            prefs.write(DAEMON_DIR, daemonDir.getAbsolutePath());
        }
        File loaderPath = new File(daemonDir, fileName);
        try {
            OutputStream outputStream = new FileOutputStream(loaderPath);
            byte[] buffer = new byte[1024];
            int length;
            InputStream inputStream = getResources().openRawResource(R.raw.dp);
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_LONG).show();
        }
        loadDaemon();
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
