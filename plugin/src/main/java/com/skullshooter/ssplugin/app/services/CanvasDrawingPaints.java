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
 | LastModified: Feb 13, 2022                               |
 |                                                          |
 \**********************************************************/
package com.skullshooter.ssplugin.app.services;

import static android.animation.ObjectAnimator.ofArgb;
import static android.animation.ValueAnimator.INFINITE;
import static com.skullshooter.ssplugin.app.services.WindowOverlay.ctx;
import static com.skullshooter.ssplugin.app.services.WindowOverlay.getConfig;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.ColorUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.skullshooter.ssplugin.app.R;

public class CanvasDrawingPaints extends View implements Runnable
{
    private Path paths[];
    private float mAnimPercentage = 0.0f;
    private static final int clip(int value) {
        return Math.max(0, Math.min(255, value));
    }

    Paint mStrokePaint;
    Paint mFilledPaint;
    Paint mItemsFilledPaints;
    Paint mTextPaint, mTeamIDPaint, mNamePaint, p, mCountPaint, mItemPaint, mTextPaint1, mWarnPaint;
    public Paint mRectPaint;
    public Paint mCirclePaint;
    public Animation animation;
    Thread mThread;
    private Rect eRect;
    private GradientDrawable eGradientDrawable;
    private int eColor[] = {Color.TRANSPARENT,Color.GREEN,Color.TRANSPARENT};
    Bitmap bitmap, out, bitmap2, out2, OTHER;
    private int FPS = 60;
    static long sleepTime;
    Date time;
    Paint bitMapPaint = new Paint();
    SimpleDateFormat formatter;
    private static int colorItem;
    boolean stop = false;
    public static Typeface styleBold, typeface, fontStyleT;

    private static final int FADE_MILLISECONDS = 7000;
    private static final int FADE_STEP = 80;
    private static final int ALPHA_STEP = 255 / (FADE_MILLISECONDS / FADE_STEP);

    private static int ScallingSize;

    //TeamIdColor
    private Random random = new Random();
    int[] playersIDcolors = getResources().getIntArray(R.array.playersIDcolor);
    int randomPlayerIDcolor = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor1 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor2 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor3 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor4 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor5 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor6 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor7 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor8 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor9 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor10 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor11 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor12 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor13 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor14 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor15 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor16 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor17 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor18 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor19 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor20 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor21 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor22 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor23 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor24 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor25 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor26 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor27 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor28 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});
    int randomPlayerIDcolor29 = ColorUtils.HSLToColor(new float[]{random.nextInt(360), random.nextFloat(), random.nextFloat()* 0.8f});



    public static void ChangeFps(int fps)
    {
        sleepTime = 1000 / (20 + fps);
    }

    public CanvasDrawingPaints(Context context)
    {
        super(context, null, 0);
        InitializePaints();
        setFocusableInTouchMode(false);
        setBackgroundColor(Color.TRANSPARENT);
        time = new Date();
        formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        sleepTime = 1000 / FPS;
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (canvas != null && getVisibility() == VISIBLE)
        {
            ClearCanvas(canvas);
            WindowOverlay.DrawOn(CanvasDrawingPaints.this, canvas);
        }
    }

    public static void espScallingSize(int scallingSize) {
        ScallingSize = scallingSize+3;
    }

    @Override
    public void run()
    {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        while (mThread.isAlive() && !mThread.isInterrupted())
        {
            try
            {
                long t1 = System.currentTimeMillis();
                postInvalidate();
                long td = System.currentTimeMillis() - t1;
                Thread.sleep(Math.max(Math.min(0, sleepTime - td), sleepTime));
            }
            catch (Exception e)
            {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    @SuppressLint("WrongConstant")
    public void InitializePaints()
    {
        eRect = new Rect(-85-ScallingSize,60,85+ScallingSize,90+ScallingSize);
        eGradientDrawable = new GradientDrawable();
        eGradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        eGradientDrawable.setColors(eColor);
        eGradientDrawable.setShape(GradientDrawable.RECTANGLE);
        eGradientDrawable.setBounds(eRect);

        mStrokePaint = new Paint();
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setColor(Color.rgb(0, 0, 0));

        mFilledPaint = new Paint();
        mFilledPaint.setStyle(Paint.Style.FILL);
        mFilledPaint.setAntiAlias(true);
        mFilledPaint.setColor(Color.rgb(0, 0, 0));       
        
        mItemsFilledPaints = new Paint();
        mItemsFilledPaints.setStyle(Paint.Style.FILL);
        mItemsFilledPaints.setAntiAlias(true);
        mItemsFilledPaints.setColor(Color.parseColor("#FF00FF7B"));


        typeface = ResourcesCompat.getFont(ctx, R.font.neue);
        fontStyleT = ResourcesCompat.getFont(ctx, R.font.araboto_light400);

        styleBold = Typeface.DEFAULT_BOLD;

        mCirclePaint = new Paint();
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);
     //   mCirclePaint.setAlpha(255);

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.rgb(0, 0, 0));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        // mTextPaint.setShadowLayer(6, 0, 0, Color.BLACK);
        mTextPaint.setTypeface(typeface);

        mWarnPaint = new Paint();
        mWarnPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWarnPaint.setStrokeWidth(2);
        mWarnPaint.setTextAlign(Paint.Align.CENTER);
        mWarnPaint.setTypeface(typeface);
        mWarnPaint.setAlpha(255);





        mTextPaint1 = new Paint();
        mTextPaint1.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint1.setAntiAlias(true);
        mTextPaint1.setColor(Color.rgb(0, 0, 0));
        mTextPaint1.setTextAlign(Paint.Align.CENTER);
        mTextPaint1.setShadowLayer(6, 0, 0, Color.BLACK);
        mTextPaint1.setTypeface(typeface);

        mItemPaint = new Paint();
        mItemPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mItemPaint.setAntiAlias(true);
        mItemPaint.setTextAlign(Paint.Align.CENTER);
        mItemPaint.setShadowLayer(1, 0, 0, Color.parseColor("#000000"));


        mCountPaint = new Paint();
        mCountPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCountPaint.setAntiAlias(true);
        mCountPaint.setColor(Color.rgb(0, 0, 0));
        mCountPaint.setTextAlign(Paint.Align.CENTER);

        mRectPaint = new Paint();
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setAntiAlias(true);

        mTeamIDPaint = new Paint();
        mTeamIDPaint.setStyle(Paint.Style.FILL);
        mTeamIDPaint.setAntiAlias(true);
        mTeamIDPaint.setColor(Color.rgb(0, 0, 0));
        mTeamIDPaint.setTextAlign(Paint.Align.LEFT);
        mTeamIDPaint.setTypeface(typeface);
        //mTeamIDPaint.setStrokeWidth(3.5f);
        mTeamIDPaint.setShadowLayer(6, 0, 0, Color.BLACK);

        p = new Paint();

        mNamePaint = new Paint();
        mNamePaint.setStyle(Paint.Style.FILL);
        mNamePaint.setAntiAlias(true);
        mNamePaint.setColor(Color.rgb(0, 0, 0));
        mNamePaint.setTextAlign(Paint.Align.CENTER);
        mNamePaint.setShadowLayer(6, 0, 0, Color.BLACK);
        mNamePaint.setTypeface(typeface);

        bitMapPaint.setAlpha(175);


    }

    private void initAnimation() {
        animation = new AlphaAnimation(1.0f, 0.2f);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        animation.setDuration(7500);
        startAnimation(animation);
    }

    Runnable mAnimationTask;
    int foregrndalpha = 255;
    int picCount;
    public void fadeAnimation() {
        mAnimationTask = new Runnable() {
            public void run() {
                if (mWarnPaint.getAlpha() == 255) {
                    mWarnPaint.setAlpha(foregrndalpha);
                    handler.sendEmptyMessage(0);
                    handler.postDelayed(mAnimationTask, 1000);
                }
            }
        };
        handler.postDelayed(mAnimationTask, 1000);
    }

    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (true)
                {
                    invalidate();
                }
            } catch (Exception e){
            }
            handler.postDelayed(this, FPS);
        }
    };

    public void setFPS(int fps){
        FPS = 60 - fps;
    }

    public void ClearCanvas(Canvas cvs)
    {
        cvs.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    //射线
    public void DrawLine(Canvas cvs, int a, int r, int g, int b, float lineWidth, float fromX, float fromY, float toX, float toY)
    {
        mStrokePaint.setColor(Color.rgb(r, g, b));
        mStrokePaint.setAlpha(a);
        mStrokePaint.setStrokeWidth(lineWidth);
        cvs.drawLine(fromX, fromY, toX, toY, mStrokePaint);
    }

    public void DebugText(String s)
    {
        System.out.println(s);
    }

    public void DrawText(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size)
    {
        mTextPaint.setARGB(a, r, g, b);
        mTextPaint.setTextSize(size);
        cvs.drawText(txt, posX, posY, mTextPaint);
    }

    //DrawActorsCount
    public void DrawActorsCount(Canvas cvs, int colors, float dx, float dy)
    {
        if (colors == 1)
        {
            eColor[1] = Color.parseColor("#FF18C33D");
        }
        if (colors == 2)
        {
            eColor[1] = Color.parseColor("#FFF3AA00");
        }
        cvs.save();
        cvs.translate(dx, dy);
        eGradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        eGradientDrawable.draw(cvs);
        cvs.restore();
    }

    public void DrawName(Canvas cvs, int a, int r, int g, int b, String nametxt, float posX, float posY, float size) {
        String[] namesp = nametxt.split(":");
        char[] nameint = new char[namesp.length];
        for (int i = 0; i < namesp.length; i++)
            nameint[i] = (char) Integer.parseInt(namesp[i]);
        String realname = new String(nameint);
        mNamePaint.setARGB(a, r, g, b);
        mNamePaint.setTextSize(size);
        mNamePaint.setShadowLayer(10.0f,1.0f,1.0f,Color.BLACK);
        cvs.drawText(realname, posX, posY, mNamePaint);
    }

    public void drawRectAngle(Canvas canvas, int a, int r, int g, int b, float strokeSize, float startX, float startY, float endX, float endY)
    {
        mRectPaint.setColor(Color.rgb(r, g, b));
        mTextPaint.setAlpha(a);
        mRectPaint.setStrokeWidth(strokeSize);
        mRectPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(startX, startY, endX, endY, mRectPaint);
    }

    public void DrawFilledCircle(Canvas cvs, int a, int r, int g, int b, float posX, float posY, float radius) {
        mFilledPaint.setColor(Color.rgb(r, g, b));
        mFilledPaint.setAlpha(a);
        cvs.drawCircle(posX, posY, radius, mFilledPaint);
    }

    public void DrawRect(Canvas cvs, int a, int r, int g, int b, float stroke, float x, float y, float width, float height) {
        mStrokePaint.setStrokeWidth(stroke);
        mStrokePaint.setColor(Color.rgb(r, g, b));
        mStrokePaint.setAlpha(a);
        cvs.drawRect(x, y, width, height, mStrokePaint);
    }

    public void DrawFilledRect(Canvas cvs, int a, int r, int g, int b, float x, float y, float width, float height) {
        mFilledPaint.setColor(Color.rgb(r, g, b));
        mFilledPaint.setAlpha(a);
        cvs.drawRect(x, y, width, height, mFilledPaint);
    }

    public void DrawTeamID(Canvas cvs, int a, int r, int g, int b, int teamid, float posX, float posY, float size) {
        mTeamIDPaint.setTextSize(size);
        mTeamIDPaint.setShadowLayer(10.0f,1.0f,1.0f,Color.BLACK);
        if (teamid <= 5) {
            mTeamIDPaint.setColor(randomPlayerIDcolor);
        } else if (teamid <= 10) {
            mTeamIDPaint.setColor(randomPlayerIDcolor1);
        } else if (teamid <= 15) {
            mTeamIDPaint.setColor(randomPlayerIDcolor2);
        } else if (teamid <= 20) {
            mTeamIDPaint.setColor(randomPlayerIDcolor3);
        } else if (teamid <= 25) {
            mTeamIDPaint.setColor(randomPlayerIDcolor4);
        } else if (teamid <= 30) {
            mTeamIDPaint.setColor(randomPlayerIDcolor5);
        } else if (teamid <= 35) {
            mTeamIDPaint.setColor(randomPlayerIDcolor6);
        } else if (teamid <= 40) {
            mTeamIDPaint.setColor(randomPlayerIDcolor7);
        } else if (teamid <= 50) {
            mTeamIDPaint.setColor(randomPlayerIDcolor8);
        } else if (teamid <= 55) {
            mTeamIDPaint.setColor(randomPlayerIDcolor9);
        } else if (teamid <= 60) {
            mTeamIDPaint.setColor(randomPlayerIDcolor10);
        } else if (teamid <= 70) {
            mTeamIDPaint.setColor(randomPlayerIDcolor11);
        } else if (teamid <= 80) {
            mTeamIDPaint.setColor(randomPlayerIDcolor12);
        } else if (teamid <= 90) {
            mTeamIDPaint.setColor(randomPlayerIDcolor13);
        } else if (teamid <= 100) {
            mTeamIDPaint.setColor(randomPlayerIDcolor14);
        } else if (teamid <= 110) {
            mTeamIDPaint.setColor(randomPlayerIDcolor15);
        } else if (teamid <= 120) {
            mTeamIDPaint.setColor(randomPlayerIDcolor16);
        } else if (teamid <= 130) {
            mTeamIDPaint.setColor(randomPlayerIDcolor17);
        } else if (teamid <= 160) {
            mTeamIDPaint.setColor(randomPlayerIDcolor18);
        } else if (teamid <= 180) {
            mTeamIDPaint.setColor(randomPlayerIDcolor19);
        } else if (teamid <= 200) {
            mTeamIDPaint.setColor(randomPlayerIDcolor20);
        } else if (teamid <= 250) {
            mTeamIDPaint.setColor(randomPlayerIDcolor21);
        } else if (teamid <= 270) {
            mTeamIDPaint.setColor(randomPlayerIDcolor22);
        } else if (teamid <= 300) {
            mTeamIDPaint.setColor(randomPlayerIDcolor23);
        } else if (teamid <= 310) {
            mTeamIDPaint.setColor(randomPlayerIDcolor24);
        } else if (teamid <= 360) {
            mTeamIDPaint.setColor(randomPlayerIDcolor25);
        } else if (teamid <= 400) {
            mTeamIDPaint.setColor(randomPlayerIDcolor26);
        } else if (teamid <= 420) {
            mTeamIDPaint.setColor(randomPlayerIDcolor27);
        } else if (teamid <= 450) {
            mTeamIDPaint.setColor(randomPlayerIDcolor28);
        } else if (teamid <= 500) {
            mTeamIDPaint.setColor(randomPlayerIDcolor29);
        } else {
            mTeamIDPaint.setARGB(255, 36, 255, 3);
        }
        cvs.drawText(teamid + " ", posX, posY, mTeamIDPaint);


    }

    public void DrawOTH(Canvas cvs, int W, int H, float X, float Y) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bot);
        OTHER = Bitmap.createScaledBitmap(bitmap, W, H, false);
        cvs.drawBitmap(OTHER, X, Y, p);
        p.setAlpha(160);
    }

    public void DrawTextName(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size) {
        mTextPaint.setARGB(a, r, g, b);
        mTextPaint.setTextSize(size);
        String formar = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        cvs.drawText(formar + txt, posX, posY, mTextPaint);
    }

    public void DrawText1(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size) {
        mNamePaint.setARGB(a, r, g, b);
        mNamePaint.setTextSize(size);
        cvs.drawText(txt,posX, posY, mNamePaint);
    }

    public void DrawLineRadar(Canvas cvs, int a, int r, int g, int b, float lineWidth, float fromX, float fromY, float toX, float toY) {
        mStrokePaint.setColor(Color.rgb(r, g, b));
        mStrokePaint.setAlpha(a);
        mStrokePaint.setStrokeWidth(lineWidth);
        cvs.drawLine(fromX, fromY, toX, toY, mStrokePaint);
    }

    public void DrawWeapon(Canvas cvs, int a, int r, int g, int b, int id, int ammo, float posX, float posY, float size) {
        mNamePaint.setARGB(a, r, g, b);
        mNamePaint.setTextSize(size);
        String wname = getWeapon(id);
        if (wname != null)
            cvs.drawText(wname, posX, posY, mNamePaint);
    }

    public void DrawWeaponAmmo(Canvas cvs, int a, int r, int g, int b, int id, int ammo, float posX, float posY, float size) {
        mNamePaint.setARGB(a, r, g, b);
        mNamePaint.setTextSize(size);
        String wName = getWeapon(id);
        if (wName != null) {
            cvs.drawText("(" + ammo + ")", posX, posY, mNamePaint);
        }
    }


    public void DrawCircle(Canvas cvs, int a, int r, int g, int b, float cx, float cy, float radius){
        mCirclePaint.setColor(Color.rgb(r, g, b));
        mCirclePaint.setAlpha(a);
        cvs.drawCircle(cx, cy, radius, mCirclePaint);
    }

    public void DrawTextWarning(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size)
    {
        mWarnPaint.setARGB(a, r, g, b);
        mWarnPaint.setTextSize(size);
        if (animation == null) {
            initAnimation();
            cvs.drawText(txt, posX, posY, mWarnPaint);
        }

    }


    public void DrawFOV(Canvas cvs, int a, int r, int g, int b, float posX, float posY, float radius, float stroke)
    {
        mStrokePaint.setARGB(a, r, g, b);
        mStrokePaint.setStrokeWidth(stroke);
        cvs.drawCircle(posX, posY, radius, mStrokePaint);
    }

    public void DrawItems(Canvas cvs, String itemName, float distance, float posX, float posY, float size) {
        String realItemName = getItemName(itemName);
        mItemPaint.setTextSize(size);
        //AmmoColorSTart
        if (getConfig("display_item_more_clearly")) {
            mItemPaint.setShadowLayer(3, 0, 0,  Color.parseColor("#000000"));
        } else {
            mItemPaint.setShadowLayer(1, 0, 0,  Color.parseColor("#000000"));
        }
        if (realItemName != null && realItemName.equals("7.62")) {
            if (getConfig("cRam762")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPam762")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGam762")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBam762")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYam762")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWam762")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("45ACP")) {
            if (getConfig("cRam45")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPam45")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGam45")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBam45")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYam45")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWam45")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("5.56")) {
            if (getConfig("cRam556")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPam556")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGam556")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBam556")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYam556")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWam556")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("9mm")) {
            if (getConfig("cRam9mm")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPam9mm")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGam9mm")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBam9mm")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYam9mm")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWam9mm")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("300Magnum")) {
            if (getConfig("cR300mm")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cP300mm")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cG300mm")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cB300mm")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cY300mm")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cW300mm")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("12 Guage")) {
            if (getConfig("cRam12g")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPam12g")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGam12g")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBam12g")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYam12g")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWam12g")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } //Ammu finish

        //ArmorsStartColoring
        else if (realItemName != null && realItemName.equals("Armor2")) {
            if (getConfig("cRar2")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPar2")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGar2")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBar2")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYar2")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWar2")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Armor3")) {
            if (getConfig("cRar3")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPar3")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGar3")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBar3")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYar3")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWar3")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Helmet2")) {
            if (getConfig("cRhel2")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPhel2")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGhel2")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBhel2")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYhel2")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWhel2")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Helmet3")) {
            if (getConfig("cRhel3")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPhel3")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGhel3")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBhel3")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYhel3")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWhel3")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        }//ArmorsFinish here
        //HealthItemsColoring
        else if (realItemName != null && realItemName.equals("Bandage")) {
            if (getConfig("cRban")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPban")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGban")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBban")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYban")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWban")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Drink")) {
            if (getConfig("cRdrink")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPdrink")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGdrink")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBdrink")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYdrink")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWdrink")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("FirstAid")) {
            if (getConfig("cRfa")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPfa")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGfa")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBfa")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYfa")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWfa")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Adrenaline")) {
            if (getConfig("cRin")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPin")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGin")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBin")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYin")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWin")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Painkiller")) {
            if (getConfig("cRpills")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPpills")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGpills")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBpills")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYpills")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWpills")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } //HealthCustomized finished here

        //miskItemsColoringStartHere
        else if (realItemName != null && realItemName.equals("Loot")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.loot);
            out = Bitmap.createScaledBitmap(bitmap, 40, 40, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, null
            );
            if (getConfig("cRloot")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPloot")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGloot")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBloot")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYloot")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWloot")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }

        } else if (realItemName != null && realItemName.equals("Aircraft")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.airdrop);
            out = Bitmap.createScaledBitmap(bitmap, 40, 40, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, null
            );
            if (getConfig("cRac")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPac")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGac")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBac")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYac")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWac")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Airdrop")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.airdrop);
            out = Bitmap.createScaledBitmap(bitmap, 40, 40, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, null
            );
            if (getConfig("cRairD")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPairD")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGairD")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBairD")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYairD")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWairD")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Bag2")) {
            if (getConfig("cRb2")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPb2")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGb2")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBb2")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYb2")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWb2")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Bag3")) {
            if (getConfig("cRb3")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPb3")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGb3")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBb3")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYb3")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWb3")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Compensator")) {
            if (getConfig("cRcomp")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPcomp")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGcomp")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBcomp")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYcomp")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWcomp")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Supressor")) {
            if (getConfig("cRsupp")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPsupp")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGsupp")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBsupp")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYsupp")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWsupp")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } //MiskItemsFinishHere

        //allWeapons
        else if (realItemName != null && realItemName.equals("8X")) {
            if (getConfig("cRx8")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPx8")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGx8")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBx8")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYx8")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWx8")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && getConfig("2X")) {
            if (getConfig("cRx2")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPx2")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGx2")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBx2")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYx2")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWx2")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("3X")) {
            if (getConfig("cRx3")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPx3")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGx3")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBx3")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYx3")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWx3")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("4X")) {
            if (getConfig("cRx4")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPx4")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGx4")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBx4")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYx4")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWx4")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("6X")) {
            if (getConfig("cRx6")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPx6")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGx6")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBx6")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYx6")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWx6")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("98k")) {
            if (getConfig("cRk98")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPk98")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGk98")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBk98")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYk98")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWk98")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("AKM")) {
            if (getConfig("cRakm")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPakm")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGakm")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBakm")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYakm")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWakm")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("AUG")) {
            if (getConfig("cRaug")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPaug")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGaug")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBaug")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYaug")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWaug")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("AWM")) {
            if (getConfig("cRawm")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPawm")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGawm")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBawm")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYawm")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWawm")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Bow")) {
            if (getConfig("cRbow")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPbow")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGbow")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBbow")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYbow")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWbow")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("DBS")) {
            if (getConfig("cRdp12")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPdp12")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGdp12")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBdp12")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYdp12")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWdp12")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("dp28")) {
            if (getConfig("cRdp28")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPdp28")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGdp28")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBdp28")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYdp28")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWdp28")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("dEagle")) {
            if (getConfig("cRdeg")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPdeg")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGdeg")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBdeg")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYdeg")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWdeg")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("RedDot")) {
            if (getConfig("cRredDot")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPredDot")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGredDot")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBredDot")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYredDot")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWredDot")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Flare")) {
            if (getConfig("cRflare")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPflare")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGflare")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBflare")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYflare")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWflare")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("G36")) {
            if (getConfig("cRg36")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPg36")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGg36")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBg36")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYg36")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWg36")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Grenade")) {
            if (getConfig("cRgrenade")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPgrenade")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGgrenade")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBgrenade")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYgrenade")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWgrenade")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Groza")) {
            if (getConfig("cRgroza")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPgroza")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGgroza")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBgroza")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYgroza")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWgroza")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Hollow")) {
            if (getConfig("cRhollo")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPhollo")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGhollo")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBhollo")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYhollo")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWhollo")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("M16")) {
            if (getConfig("cRm16")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPm16")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGm16")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBm16")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYm16")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWm16")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("m24")) {
            if (getConfig("cRm24")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPm24")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGm24")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBm24")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYm24")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWm24")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("m249")) {
            if (getConfig("cRm249")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPm249")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGm249")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBm249")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYm249")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWm249")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("M4")) {
            if (getConfig("cRm4")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPm4")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGm4")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBm4")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYm4")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWm4")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("M762")) {
            if (getConfig("cRm762")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPm762")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGm762")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBm762")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYm762")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWm762")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("mk14")) {
            if (getConfig("cRmk14")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPmk14")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGmk14")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBmk14")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYmk14")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWmk14")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("mk47")) {
            if (getConfig("cRmk47")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPmk47")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGmk47")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBmk47")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYmk47")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWmk47")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("MP5K")) {
            if (getConfig("cRmp5k")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPmp5k")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGmp5k")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBmp5k")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYmp5k")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWmp5k")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("mini14")) {
            if (getConfig("cRmini14")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPmini14")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGmini14")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBmini14")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYmini14")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWmini14")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Molotov")) {
            if (getConfig("cRburn")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPburn")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGburn")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBburn")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYburn")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWburn")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("pp19")) {
            if (getConfig("cRpp19")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPpp19")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGpp19")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBpp19")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYpp19")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWpp19")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Pan")) {
            if (getConfig("cRpan")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPpan")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGpan")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBpan")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYpan")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWpan")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("QBU")) {
            if (getConfig("cRqbu")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPqbu")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGqbu")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBqbu")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYqbu")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWqbu")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("QBZ")) {
            if (getConfig("cRqbz")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPqbz")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGqbz")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBqbz")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYqbz")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWqbz")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("S12K")) {
            if (getConfig("cRs12k")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPs12k")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGs12k")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBs12k")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYs12k")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWs12k")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("s1897")) {
            if (getConfig("cRs1897")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPs1897")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGs1897")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBs1897")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYs1897")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWs1897")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("s686")) {
            if (getConfig("cRs686")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPs686")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGs686")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBs686")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYs686")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWs686")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("scarL")) {
            if (getConfig("cRscar")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPscar")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGscar")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBscar")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cWscar")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cYscar")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("SKS")) {
            if (getConfig("cRsks")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPsks")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGsks")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBsks")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYsks")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWsks")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("SLR")) {
            if (getConfig("cRslr")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPslr")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGslr")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBslr")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYslr")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWslr")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Stung")) {
            if (getConfig("cRflash")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPflash")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGflash")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBflash")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYflash")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWflash")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("TommyGun")) {
            if (getConfig("cRthomp")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPthomp")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGthomp")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBthomp")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYthomp")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWthomp")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("UMP45")) {
            if (getConfig("cRump45")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPump45")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGump45")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBump45")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYump45")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWump45")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("UZI")) {
            if (getConfig("cRuzi")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPuzi")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGuzi")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBuzi")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYuzi")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWuzi")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("VSS")) {
            if (getConfig("cRvss")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPvss")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGvss")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBvss")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYvss")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWvss")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Vector")) {
            if (getConfig("cRvect")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPvect")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGvect")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBvect")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYvect")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWvect")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        } else if (realItemName != null && realItemName.equals("Win94")) {
            if (getConfig("cRwinchester")) {
                mItemPaint.setColor(Color.parseColor("#FF0000")); //Red
            } else if (getConfig("cPwinchester")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Pink
            } else if (getConfig("cGwinchester")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00")); //Green
            } else if (getConfig("cBwinchester")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff")); //Blue
            } else if (getConfig("cYwinchester")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00")); //Yellow
            } else if (getConfig("cWwinchester")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0")); //White
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC")); //Default
            }
        }
        if (realItemName != null && !realItemName.equals(""))
            cvs.drawText(realItemName + " (" + Math.round(distance) + "m)", posX, posY, mItemPaint);
    }


    //EnemyCount

    public void EnemyCount(Canvas cvs, int a, int r, int g, int b, String txt, float posX, float posY, float size)
    {
        mCountPaint.setARGB(a, r, g, b);
        mCountPaint.setTextSize(size);
        cvs.drawText(txt, posX, posY, mCountPaint);
    }

    //Vechicles
    public void DrawVehicles(Canvas cvs, String itemName, float distance, float posX, float posY, float size) {
        String realVehicleName = getVehicleName(itemName);
        //mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
        mItemPaint.setTextSize(size);
        if (getConfig("display_item_more_clearly")) {
            mItemPaint.setShadowLayer(3, 0, 0,  Color.parseColor("#000000"));
        } else {
            mItemPaint.setShadowLayer(1, 0, 0,  Color.parseColor("#000000"));
        }
        if (realVehicleName.equals("Buggy")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedBuggy")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkBuggy")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenBuggy")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueBuggy")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteBuggy")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowBuggy")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }

            //setItemStyleBold
            if (getConfig("sBbuggy")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTbuggy")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("UAZ")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedUAZ")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkUAZ")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenUAZ")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueUAZ")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteUAZ")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowUAZ")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBuaz")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTuaz")){
                mItemPaint.setTypeface(fontStyleT);
            }else {
                mItemPaint.setTypeface(typeface);
            }

        } else if (realVehicleName.equals("Trike")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
        } else if (realVehicleName.equals("Bike")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedBike")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkBike")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenBike")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueBike")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteBike")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowBike")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBBike")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTBike")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Dacia")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedDacia")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkDacia")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenDacia")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueDacia")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteDacia")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowDacia")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBDacia")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTDacia")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("AquaRail")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boat);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedAquaRail")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkAquaRail")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenAquaRail")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueAquaRail")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteAquaRail")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowAquaRail")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBAquaRail")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTAquaRail")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Boat")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boat);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedBoat")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkBoat")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenBoat")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueBoat")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteBoat")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowBoat")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBboat")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTboat")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Bus")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedBus")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkBus")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenBus")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueBus")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteBus")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowBus")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBbus")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTBus")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Mirado")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedMirado")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkMirado")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenMirado")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueMirado")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteMirado")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowMirado")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBmirado")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTmirado")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Scooter")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedScooter")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkScooter")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenScooter")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueScooter")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteScooter")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowScooter")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBscooter")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTscooter")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Rony")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedRony")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkRony")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenRony")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueRony")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteRony")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowRony")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBrony")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTrony")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Snowbike")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedSnowbike")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkSnowbike")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenSnowbike")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueSnowbike")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteSnowbike")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowSnowbike")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBsnowB")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTsnowB")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Snowmobile")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedSnowmobile")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkSnowmobile")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenSnowmobile")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueSnowmobile")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteSnowmobile")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowSnowmobile")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBsnowM")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTsnowM")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Tuk")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedTuk")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkTuk")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenTuk")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueTuk")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteTuk")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowTuk")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBtuk")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTtuk")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Truck")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedTruck")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkTruck")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenTruck")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueTruck")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteTruck")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowTruck")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBtruck")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTtruck")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("BRDM")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedBRDM")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkBRDM")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenBRDM")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueBRDM")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteBRDM")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowBRDM")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBbrdm")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTbrdm")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("LadaNiva")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedLadaNiva")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkLadaNiva")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenLadaNiva")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueLadaNiva")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteLadaNiva")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowLadaNiva")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBlada")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTlada")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Monster Truck")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedMonster")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkMonster")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenMonster")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueMonster")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteMonster")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowMonster")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBmoster")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTmoster")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("Coupe RB")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedCoupeRB")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkCoupeRB")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenCoupeRB")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueCoupeRB")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteCoupeRB")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowCoupeRB")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sBcouper")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTcouper")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        } else if (realVehicleName.equals("UTV")) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vehicle);
            out = Bitmap.createScaledBitmap(bitmap, 35, 35, false);
            cvs.drawBitmap(
                    out, posX - 20, posY - 50, bitMapPaint
            );
        cvs.drawCircle(posX, posY + 10, 5, mItemsFilledPaints);
            if (getConfig("clrRedUTV")) {
                mItemPaint.setColor(Color.parseColor("#FF0000"));
            } else if (getConfig("clrPinkUTV")) {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            } else if (getConfig("clrGreenUTV")) {
                mItemPaint.setColor(Color.parseColor("#ff00ff00"));
            } else if (getConfig("clrBlueUTV")) {
                mItemPaint.setColor(Color.parseColor("#ff00ffff"));
            } else if (getConfig("clrWhiteUTV")) {
                mItemPaint.setColor(Color.parseColor("#ffc0c0c0"));
            } else if (getConfig("clrYellowUTV")) {
                mItemPaint.setColor(Color.parseColor("#ffffff00"));
            } else {
                mItemPaint.setColor(Color.parseColor("#FFFFBEDC"));
            }
            if (getConfig("sButv")) {
                mItemPaint.setTypeface(styleBold);
            } else if (getConfig("sTutv")) {
                mItemPaint.setTypeface(fontStyleT);
            } else {
                mItemPaint.setTypeface(typeface);
            }
        }
        if (realVehicleName != null && !realVehicleName.equals(""))
            cvs.drawText(realVehicleName + "(" + Math.round(distance) + ")", posX, posY, mItemPaint);
    }


    //Vehicles
    private String getVehicleName(String s) {
        if (s.contains("Buggy") && getConfig("Buggy"))
            return "Buggy";
        if (s.contains("UAZ") && getConfig("UAZ"))
            return "UAZ";
        if (s.contains("MotorcycleC") && getConfig("Trike"))
            return "Trike";
        if (s.contains("Motorcycle") && getConfig("Bike"))
            return "Bike";
        if (s.contains("Dacia") && getConfig("Dacia"))
            return "Dacia";
        if (s.contains("AquaRail") && getConfig("Aquarail"))
            return "AquaRail";
        if (s.contains("PG117") && getConfig("Boat"))
            return "Boat";
        if (s.contains("MiniBus") && getConfig("Bus"))
            return "Bus";
        if (s.contains("Mirado") && getConfig("Mirado"))
            return "Mirado";
        if (s.contains("Scooter") && getConfig("Scooter"))
            return "Scooter";
        if (s.contains("Rony") && getConfig("Rony"))
            return "Rony";
        if (s.contains("Snowbike") && getConfig("Snowbike"))
            return "Snowbike";
        if (s.contains("Snowmobile") && getConfig("Snowmobile"))
            return "Snowmobile";
        if (s.contains("Tuk") && getConfig("Tuk"))
            return "Tuk";
        if (s.contains("PickUp") && getConfig("Truck"))
            return "Truck";
        if (s.contains("BRDM") && getConfig("BRDM"))
            return "BRDM";
        if (s.contains("LadaNiva") && getConfig("LadaNiva"))
            return "LadaNiva";
        if (s.contains("Bigfoot") && getConfig("Monster truck"))
            return "Monster Truck";
        if (s.contains("CoupeRB") && getConfig("Coupe RB"))
            return "Coupe RB";
        if (s.contains("UTV") && getConfig("UTV"))
            return "UTV";

        return "";
    }

    private String getWeapon(int id) {
        //AR and SMG
        if (id == 101006)
            return "AUG";
        if (id == 101008)
            return "M762";
        if (id == 101003)
            return "SCAR-L";
        if (id == 101004)
            return "M416";
        if (id == 101002)
            return "M16A-4";
        if (id == 101009)
            return "Mk47 Mutant";
        if (id == 101010)
            return "G36C";
        if (id == 101007)
            return "QBZ";
        if (id == 101001)
            return "AKM";
        if (id == 101005)
            return "Groza";
        if (id == 102005)
            return "Bizon";
        if (id == 102004)
            return "TommyGun";
        if (id == 102007)
            return "MP5K";
        if (id == 102002)
            return "UMP";
        if (id == 102003)
            return "Vector";
        if (id == 102001)
            return "Uzi";
        if (id == 105002)
            return "DP28";
        if (id == 105001)
            return "M249";

        //Snipers
        if (id == 103003)
            return "AWM";
        if (id == 103010)
            return "QBU";
        if (id == 103009)
            return "SLR";
        if (id == 103004)
            return "SKS";
        if (id == 103006)
            return "Mini14";
        if (id == 103002)
            return "M24";
        if (id == 103001)
            return "Kar98k";
        if (id == 103005)
            return "VSS";
        if (id == 103008)
            return "Win94";
        if (id == 103007)
            return "Mk14";

        //Shotguns and Hand weapons
        if (id == 104003)
            return "S12K";
        if (id == 104004)
            return "DBS";
        if (id == 104001)
            return "S686";
        if (id == 104002)
            return "S1897";
        if (id == 108003)
            return "Sickle";
        if (id == 108001)
            return "Machete";
        if (id == 108002)
            return "Crowbar";
        if (id == 107001)
            return "CrossBow";
        if (id == 108004)
            return "Pan";

        //Pistols
        if (id == 106006)
            return "SawedOff";
        if (id == 106003)
            return "R1895";
        if (id == 106008)
            return "Vz61";
        if (id == 106001)
            return "P92";
        if (id == 106004)
            return "P18C";
        if (id == 106005)
            return "R45";
        if (id == 106002)
            return "P1911";
        if (id == 106010)
            return "DesertEagle";

        return null;
    }

    private String getItemName(String s) {
        //Scopes
        if (s.contains("MZJ_8X") && getConfig("8X")) {
            mNamePaint.setARGB(255, 247, 99, 245);
            return "8X";
        }
        if (s.contains("MZJ_2X") && getConfig("2X")) {
            mNamePaint.setARGB(255, 230, 172, 226);
            return "2X";
        }
        if (s.contains("MZJ_HD") && getConfig("RedDot")) {
            mNamePaint.setARGB(255, 230, 172, 226);
            return "RedDot";
        }
        if (s.contains("MZJ_3X") && getConfig("3X")) {
            mNamePaint.setARGB(255, 247, 99, 245);
            return "3X";
        }
        if (s.contains("MZJ_QX") && getConfig("Hollow")) {
            mNamePaint.setARGB(255, 153, 75, 152);
            return "Hollow";
        }
        if (s.contains("MZJ_6X") && getConfig("6X")) {
            mNamePaint.setARGB(255, 247, 99, 245);
            return "6x";
        }
        if (s.contains("MZJ_4X") && getConfig("4X")) {
            mNamePaint.setARGB(255, 247, 99, 245);
            return "4x";
        }
        //AR and SMG
        if (s.contains("AUG") && getConfig("AUG")) {
            mNamePaint.setARGB(255, 52, 224, 63);
            return "AUG";
        }
        if (s.contains("M762") && getConfig("m762")) {
            mNamePaint.setARGB(255, 43, 26, 28);
            return "M762";
        }
        if (s.contains("SCAR") && getConfig("scarL")) {
            mNamePaint.setARGB(255, 52, 224, 63);
            return "scarL";
        }
        if (s.contains("M416") && getConfig("M4")) {
            mNamePaint.setARGB(255, 115, 235, 223);
            return "M4";
        }
        if (s.contains("M16A4") && getConfig("M16")) {
            mNamePaint.setARGB(255, 116, 227, 123);
            return "M16";
        }
        if (s.contains("Mk47") && getConfig("mk47")) {
            mNamePaint.setARGB(255, 247, 99, 245);
            return "mk47";
        }
        if (s.contains("G36") && getConfig("G36")) {
            mNamePaint.setARGB(255, 116, 227, 123);
            return "G36";
        }
        if (s.contains("QBZ") && getConfig("QBZ")) {
            mNamePaint.setARGB(255, 52, 224, 63);
            return "QBZ";
        }
        if (s.contains("AKM") && getConfig("AKM")) {
            mNamePaint.setARGB(255, 214, 99, 99);
            return "AKM";
        }
        if (s.contains("Groza") && getConfig("Groza")) {
            mNamePaint.setARGB(255, 214, 99, 99);
            return "Groza";
        }
        if (s.contains("PP19") && getConfig("pp19")) {
            mNamePaint.setARGB(255, 255, 246, 0);
            return "pp19";
        }
        if (s.contains("TommyGun") && getConfig("Tommy")) {
            mNamePaint.setARGB(255, 207, 207, 207);
            return "TommyGun";
        }
        if (s.contains("MP5K") && getConfig("mp5k")) {
            mNamePaint.setARGB(255, 207, 207, 207);
            return "MP5K";
        }
        if (s.contains("UMP9") && getConfig("ump45")) {
            mNamePaint.setARGB(255, 207, 207, 207);
            return "UMP45";
        }
        if (s.contains("Vector") && getConfig("Vector")) {
            mNamePaint.setARGB(255, 255, 246, 0);
            return "Vector";
        }
        if (s.contains("MachineGun_Uzi") && getConfig("UZI")) {
            mNamePaint.setARGB(255, 255, 246, 0);
            return "UZI";
        }
        if (s.contains("DP28") && getConfig("dp28")) {
            mNamePaint.setARGB(255, 43, 26, 28);
            return "dp28";
        }
        if (s.contains("M249") && getConfig("m249")) {
            mNamePaint.setARGB(255, 247, 99, 245);
            return "m249";
        }

        //Snipers
        if (s.contains("AWM") && getConfig("AWM")) {
            mNamePaint.setColor(Color.BLACK);
            return "AWM";
        }
        if (s.contains("QBU") && getConfig("QBU")) {
            mNamePaint.setARGB(255, 207, 207, 207);
            return "QBU";
        }
        if (s.contains("SLR") && getConfig("SLR")) {
            mNamePaint.setARGB(255, 43, 26, 28);
            return "SLR";
        }
        if (s.contains("SKS") && getConfig("SKS")) {
            mNamePaint.setARGB(255, 43, 26, 28);
            return "SKS";
        }
        if (s.contains("Mini14") && getConfig("mini14")) {
            mNamePaint.setARGB(255, 247, 99, 245);
            return "mini14";
        }
        if (s.contains("Sniper_M24") && getConfig("m24")) {
            mNamePaint.setARGB(255, 247, 99, 245);
            return "m24";
        }
        if (s.contains("Kar98k") && getConfig("98k")) {
            mNamePaint.setARGB(255, 247, 99, 245);
            return "98k";
        }
        if (s.contains("VSS") && getConfig("VSS")) {
            mNamePaint.setARGB(255, 255, 246, 0);
            return "VSS";
        }
        if (s.contains("Win94") && getConfig("Win94")) {
            mNamePaint.setARGB(255, 207, 207, 207);
            return "Win94";
        }
        if (s.contains("Mk14") && getConfig("mk14")) {
            mNamePaint.setColor(Color.BLACK);
            return "mk14";
        }

        //Shotguns and Hand weapons
        if (s.contains("S12K") && getConfig("s12k")) {
            mNamePaint.setARGB(255, 153, 109, 109);
            return "S12K";
        }
        if (s.contains("ShotGun_DP12") && getConfig("DP12")) {
            mNamePaint.setARGB(255, 153, 109, 109);
            return "DBS";
        }
        if (s.contains("S686") && getConfig("s686")) {
            mNamePaint.setARGB(255, 153, 109, 109);
            return "s686";
        }
        if (s.contains("S1897") && getConfig("s1897")) {
            mNamePaint.setARGB(255, 153, 109, 109);
            return "s1897";
        }
        if (s.contains("Sickle") && getConfig("Sickle")) {
            mNamePaint.setARGB(255, 102, 74, 74);
            return "Sickle";
        }
        if (s.contains("Machete") && getConfig("Machete")) {
            mNamePaint.setARGB(255, 102, 74, 74);
            return "Machete";
        }
        if (s.contains("Cowbar") && getConfig("Crowbar")) {
            mNamePaint.setARGB(255, 102, 74, 74);
            return "Crowbar";
        }
        if (s.contains("CrossBow") && getConfig("Bow")) {
            mNamePaint.setARGB(255, 102, 74, 74);
            return "Bow";
        }
        if (s.contains("Pan") && getConfig("Pan")) {
            mNamePaint.setARGB(255, 102, 74, 74);
            return "Pan";
        }

        //Pistols
        if (s.contains("SawedOff") && getConfig("SawedOff")) {
            mNamePaint.setARGB(255, 156, 113, 81);
            return "SawedOff";
        }
        if (s.contains("R1895") && getConfig("R1895")) {
            mNamePaint.setARGB(255, 156, 113, 81);
            return "R1895";
        }
        if (s.contains("Vz61") && getConfig("Vz61")) {
            mNamePaint.setARGB(255, 156, 113, 81);
            return "Vz61";
        }
        if (s.contains("P92") && getConfig("P92")) {
            mNamePaint.setARGB(255, 156, 113, 81);
            return "P92";
        }
        if (s.contains("P18C") && getConfig("P18C")) {
            mNamePaint.setARGB(255, 156, 113, 81);
            return "P18C";
        }
        if (s.contains("R45") && getConfig("R45")) {
            mNamePaint.setARGB(255, 156, 113, 81);
            return "R45";
        }
        if (s.contains("P1911") && getConfig("P1911")) {
            mNamePaint.setARGB(255, 156, 113, 81);
            return "P1911";
        }
        if (s.contains("DesertEagle") && getConfig("dEagle")) {
            mNamePaint.setARGB(255, 156, 113, 81);
            return "dEagle";
        }

        //Ammo
        if (s.contains("Ammo_762mm") && getConfig("7.62")) {
            mNamePaint.setARGB(255, 92, 36, 28);
            return "7.62";
        }
        if (s.contains("Ammo_45AC") && getConfig(".45")) {
            mNamePaint.setColor(Color.LTGRAY);
            return "45ACP";
        }
        if (s.contains("Ammo_556mm") && getConfig("5.56")) {
            mNamePaint.setColor(Color.GREEN);
            return "5.56";
        }
        if (s.contains("Ammo_9mm") && getConfig("9mm")) {
            mNamePaint.setColor(Color.YELLOW);
            return "9mm";
        }
        if (s.contains("Ammo_300Magnum") && getConfig(".300")) {
            mNamePaint.setColor(Color.BLACK);
            return "300Magnum";
        }
        if (s.contains("Ammo_12Guage") && getConfig("12G")) {
            mNamePaint.setARGB(255, 156, 91, 81);
            return "12 Guage";
        }
        if (s.contains("Ammo_Bolt") && getConfig("Arrow")) {
            mNamePaint.setARGB(255, 156, 113, 81);
            return "Arrow";
        }

        //Bag, Helmet, Vest
        if (s.contains("Bag_Lv3") && getConfig("Bag3")) {
            mNamePaint.setARGB(255, 36, 83, 255);
            return "Bag3";
        }
        if (s.contains("Bag_Lv2") && getConfig("Bag2")) {
            mNamePaint.setARGB(255, 77, 115, 255);
            return "Bag2";
        }


        if (s.contains("Armor_Lv2") && getConfig("Armor2")) {
            mNamePaint.setARGB(255, 77, 115, 255);
            return "Armor2";
        }
        if (s.contains("Armor_Lv3") && getConfig("Armor3")) {
            return "Armor3";
        }
        if (s.contains("Helmet_Lv2") && getConfig("Helmet2")) {
            return "Helmet2";
        }
        if (s.contains("Helmet_Lv3") && getConfig("Helmet3")) {
            return "Helmet3";
        }

        //Health kits
        if (s.contains("Pills") && getConfig("Painkiller")) {
            return "Painkiller";
        }
        if (s.contains("Injection") && getConfig("Adrenaline")) {
            return "Adrenaline";
        }
        if (s.contains("Drink") && getConfig("Drink")) {
            return "Drink";
        }
        if (s.contains("Firstaid") && getConfig("FirstAid")) {
            return "FirstAid";
        }
        if (s.contains("Bandage") && getConfig("Bandage")) {
            return "Bandage";
        }
        if (s.contains("FirstAidbox") && getConfig("Medkit")) {
            return "Medkit";
        }

        //Throwables
        if (s.contains("Grenade_Stun") && getConfig("Stung")) {
            return "Stung";
        }
        if (s.contains("Grenade_Shoulei") && getConfig("Grenade")) {
            return "Grenade";
        }
        if (s.contains("Grenade_Smoke") && getConfig("Smoke")) {
            return "Smoke";
        }
        if (s.contains("Grenade_Burn") && getConfig("Molotov")) {
            return "Molotov";
        }

        //Others
        if (s.contains("QK_Large_C") && getConfig("Compensator")) {
            return "Compensator";
        }
        if (s.contains("Large_Suppressor") && getConfig("Supressor")) {
            return "Supressor";
        }

        //Special
        if (s.contains("Flare") && getConfig("Flare")) {
            return "Flare";
        }
        if (s.contains("Ghillie") && getConfig("Ghillie Suit")) {
            return "Ghillie Suit";
        }
        if (s.contains("QT_Sniper") && getConfig("CheekPad")) {
            return "CheekPad";
        }



        if (s.contains("PickUpListWrapperActor") && getConfig("Loot")) {
            return "Loot";
        }
        if ((s.contains("AirDropPlane")) && getConfig("Aircraft")) {
            return "Aircraft";
        }
        if ((s.contains("AirDrop")) && getConfig("Airdrop")) {
            return "Airdrop";
        }
        //return s;
        return null;
    }



    public static Bitmap scale(Bitmap bitmap, int maxWidth, int maxHeight) {
        // Determine the constrained dimension, which determines both dimensions.
        int width;
        int height;
        float widthRatio = (float)bitmap.getWidth() / maxWidth;
        float heightRatio = (float)bitmap.getHeight() / maxHeight;
        // Width constrained.
        if (widthRatio >= heightRatio) {
            width = maxWidth;
            height = (int)(((float)width / bitmap.getWidth()) * bitmap.getHeight());
        }
        // Height constrained.
        else {
            height = maxHeight;
            width = (int)(((float)height / bitmap.getHeight()) * bitmap.getWidth());
        }
        Bitmap scaledBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        float ratioX = (float)width / bitmap.getWidth();
        float ratioY = (float)height / bitmap.getHeight();
        float middleX = width / 2.0f;
        float middleY = height / 2.0f;
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        return scaledBitmap;
    }

    private void blinking() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlinking = 1000;
                try {
                    Thread.sleep(timeToBlinking);
                } catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCirclePaint.getAlpha() == 255) {
                            mCirclePaint.setAlpha(0);
                        } else {
                            mCirclePaint.setAlpha(255);
                        }
                        blinking();
                    }
                });
            }
        }).start();
    }

}