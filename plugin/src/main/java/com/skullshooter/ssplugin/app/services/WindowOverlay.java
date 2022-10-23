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
 | LastModified: Jan 11, 2022                               |
 |                                                          |
 \**********************************************************/
package com.skullshooter.ssplugin.app.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Toast;

import com.skullshooter.ssplugin.app.activities.Daemon;
import com.skullshooter.ssplugin.app.manager.RecorderFakeUtils;
import com.skullshooter.ssplugin.app.preferences.Preferences;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.skullshooter.ssplugin.app.configuaration.AppConfig.hideEsp;

public class WindowOverlay extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    WindowManager windowManager;
    Process process;
    View mFloatingView;
    CanvasDrawingPaints overlayView;
    public static Preferences prefs;
    @SuppressLint("StaticFieldLeak")
    private static WindowOverlay Instance;

    static Context ctx;

    @SuppressLint("InflateParams")
    @Override
    public void onCreate() {
        prefs = Preferences.with(this);
        super.onCreate();
        ctx = this;
        if (Daemon.gameType == 1) {
            Start(ctx, 1, 1);
        }
        else if (Daemon.gameType == 2) {
            Start(ctx, 2, 1);
        }
        else if (Daemon.gameType == 3) {
            Start(ctx, 3, 1);
        }
        else if (Daemon.gameType == 4) {
            Start(ctx, 4, 1);
        }
        else if (Daemon.gameType == 5) {
            Start(ctx, 5, 1);
        }
        windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        overlayView = new CanvasDrawingPaints(ctx);
        //  mFloatingView = LayoutInflater.from(ctx).inflate(R.layout.float_view, null);
        DrawCanvas();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Close();
        if (overlayView != null) {
            ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE)).removeView(overlayView);
            overlayView = null;
        }
        process.destroy();
    }

    public void Start(final Context context, final int gametype, final int bit) {
        if (Instance == null) {
            // Intent intent = new Intent(context, Overlay.class);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    //if())
                    getReady(gametype);
                }
            });
            t.start();

            //   context.startService(intent);
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StartDaemon(context, bit);
                }
            });
            t2.start();
            //Toast.makeText(context,"Already Started !!",Toast.LENGTH_LONG).show();
        }
    }

    static native boolean getReady(int nameofgame);

    public void StartDaemon(final Context context, int bit) {
        Shell(Daemon.socket);
    }


    public static void Stop(Context context) {
        Intent intent = new Intent(context, WindowOverlay.class);
        context.stopService(intent);

        Intent floatLogo = new Intent(context, SsFlawMain.class);
        context.stopService(floatLogo);
    }

    private native void Close();

    static boolean getConfig(String key) {
        SharedPreferences sp = ctx.getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
        // return !key.equals("");
    }

    @SuppressLint("NewApi")
    private void DrawCanvas() {
        // mFloatingView = LayoutInflater.from(this).inflate(R.layout.float_view, null);
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        }
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, 0, getNavigationBarHeight(),
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN,
                PixelFormat.RGBA_8888);

        if (Preferences.getInstance().read(hideEsp).equals("true")) {
            RecorderFakeUtils.setFakeRecorderWindowLayoutParams(params);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        params.gravity = Gravity.TOP | Gravity.START;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 0;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(overlayView, params);
    }

    public static native void DrawOn(CanvasDrawingPaints espView, Canvas canvas);

    private int getNavigationBarHeight() {
        boolean hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0 && !hasMenuKey) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
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
}
