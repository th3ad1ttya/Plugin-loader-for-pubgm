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
package com.skullshooter.ssplugin64.app.services;

import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.bit64native;
import static com.skullshooter.ssplugin64.app.configuaration.AppConfig.pg64ARMV;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.BLUE;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.GREEN;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.PINK;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.RED;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.STYLEB;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.STYLET;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.WHITE;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.YELLOW;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.aquarailStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.bikeStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.boatStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.brdmStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.buggyStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.busStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.coupeStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.daciaStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.ladanivaStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.miradoStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.monsterStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.ronyStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.scooterStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.snowMobileStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.snowbikeStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.truckStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.tukStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.uazStyle;
import static com.skullshooter.ssplugin64.app.configuaration.Colors.utvStyle;
import static java.lang.System.exit;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.core.content.res.ResourcesCompat;

import com.skullshooter.ssplugin64.app.R;
import com.skullshooter.ssplugin64.app.preferences.ColorizedPrefs;
import com.skullshooter.ssplugin64.app.preferences.Preferences;

import java.io.File;

public class SsFlawMain extends Service implements View.OnClickListener {

    private WindowManager mWindowManager;
    private View mView;
    public ColorizedPrefs cPrefs;
    public static CheckBox Buggy, hideOverlay;
    Animation textBlinkAnimation;

    @SuppressLint("StaticFieldLeak")
    private static SsFlawMain Instance;

    public SsFlawMain() {/*Keep empty*/}

    static {
        // File pathNative = new File(Preferences.getInstance().read(pgARMV, pgARMVSO));
        System.load(Preferences.getInstance().read(pg64ARMV) + "/" + Preferences.getInstance().read(bit64native));
        //System.loadLibrary("libpg-lib");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    View espView, logoView;

    public void onCreate() {
        super.onCreate();
        Instance = this;
        createOver();
        cPrefs = ColorizedPrefs.with(this);

        logoView = mView.findViewById(R.id.relativeLayoutParent);
        espView = mView.findViewById(R.id.floatingMain);
        hideOverlay = mView.findViewById(R.id.idHideCheckBox);
        Init();
    }

    void createOver() {
        mView = LayoutInflater.from(this).inflate(R.layout.ss_flaw_main, null);
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }

        //Setting the layout parameters
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.RGBA_8888);

        // WindowManager.LayoutParams lm = new WindowManager.LayoutParams();
        params.gravity = Gravity.TOP | Gravity.START;

        //Getting windows services and adding the floating view to it
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mView, params);

        final GestureDetector gestureDetector = new GestureDetector(this, new SingleTapConfirm());

        //Window function
        final ToggleButton editToggle = mView.findViewById(R.id.mod);
        ImageView closeBtn = mView.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                editToggle.setChecked(false);
                if (hideOverlay.isChecked()) {
                    espView.setVisibility(View.GONE);
                    logoView.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            logoView.setAlpha(0.0f);
                        }
                    }, 1000);
                } else {
                    logoView.setAlpha(1.0f);
                    espView.setVisibility(View.GONE);
                    logoView.setVisibility(View.VISIBLE);
                }
            }
        });


        final RelativeLayout visionLayout = mView.findViewById(R.id.vision_layout); //layoutBtn
        final RelativeLayout itemsLayout = mView.findViewById(R.id.weapon_Layout); //layoutBtn
        final RelativeLayout controlsLayout = mView.findViewById(R.id.aimbot_Layout); //layoutBtn


        final ImageView visionBtn = mView.findViewById(R.id.vision_Btn); //ImageBtn
        final ImageView weaponBtn = mView.findViewById(R.id.weapon_Btn); //ImageBtn
        final ImageView aimbotBtn = mView.findViewById(R.id.aimbot_Btn); //ImageBtn

        final LinearLayout playerMenu = mView.findViewById(R.id.playermenu); //HackMenuLayout
        final LinearLayout itemsMenu = mView.findViewById(R.id.menuitem); //ItemsLayout

        final TextView infoLayout = mView.findViewById(R.id.infoMenu); //InfoText

        final TextView vehiclesBtn = mView.findViewById(R.id.vehicle); //TextView
        final TextView ammoBtn = mView.findViewById(R.id.ammo); //TextView
        final TextView weaponsBtn = mView.findViewById(R.id.weapon); //TextView
        final TextView armorsBtn = mView.findViewById(R.id.armor); //TextView
        final TextView healthBtn = mView.findViewById(R.id.health); //TextView
        final TextView miskBtn = mView.findViewById(R.id.misc); //TextView

        final LinearLayout vehicleMenu = mView.findViewById(R.id.vehiclemenu);
        final LinearLayout ammoMenu = mView.findViewById(R.id.ammomenu);
        final LinearLayout weaponMenu = mView.findViewById(R.id.weapommenu);
        final LinearLayout armorMenu = mView.findViewById(R.id.armorsmenu);
        final LinearLayout healthMenu = mView.findViewById(R.id.healthmenu);
        final LinearLayout miskMenu = mView.findViewById(R.id.miscmenu);

        final LinearLayout menuColor = mView.findViewById(R.id.menucolor);

        final LinearLayout vec1 = mView.findViewById(R.id.vec1);
        final LinearLayout vec1_edit = mView.findViewById(R.id.vec1_edit);

        editToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    menuColor.setVisibility(View.VISIBLE);
                } else {
                    menuColor.setVisibility(View.GONE);

                }
            }
        });

        visionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visionBtn.setImageResource(R.drawable.ic_vision_active);
                weaponBtn.setImageResource(R.drawable.ic_weapon_nonactive);
                aimbotBtn.setImageResource(R.drawable.ic_aimbot_nonactive);

                visionLayout.setBackgroundColor(Color.parseColor("#3C8C92AC"));
                itemsLayout.setBackgroundColor(Color.parseColor("#FF182231"));
                controlsLayout.setBackgroundColor(Color.parseColor("#FF182231"));

                playerMenu.setVisibility(View.VISIBLE);
                itemsMenu.setVisibility(View.GONE);
                editToggle.setVisibility(View.GONE);
                editToggle.setChecked(false);

                infoLayout.setText("Display");
            }
        });


        itemsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visionBtn.setImageResource(R.drawable.ic_vision_nonactive);
                weaponBtn.setImageResource(R.drawable.ic_weapon_active);
                aimbotBtn.setImageResource(R.drawable.ic_aimbot_nonactive);

                visionLayout.setBackgroundColor(Color.parseColor("#FF182231"));
                itemsLayout.setBackgroundColor(Color.parseColor("#3C8C92AC"));
                controlsLayout.setBackgroundColor(Color.parseColor("#FF182231"));

                playerMenu.setVisibility(View.GONE);
                itemsMenu.setVisibility(View.VISIBLE);
                editToggle.setVisibility(View.VISIBLE);

                infoLayout.setText("Items");
            }
        });

        controlsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visionBtn.setImageResource(R.drawable.ic_vision_nonactive);
                weaponBtn.setImageResource(R.drawable.ic_weapon_nonactive);
                aimbotBtn.setImageResource(R.drawable.ic_aimbot_active);

                visionLayout.setBackgroundColor(Color.parseColor("#FF182231"));
                itemsLayout.setBackgroundColor(Color.parseColor("#FF182231"));
                controlsLayout.setBackgroundColor(Color.parseColor("#3C8C92AC"));

                playerMenu.setVisibility(View.GONE);
                itemsMenu.setVisibility(View.GONE);

                infoLayout.setText("Controls");
            }
        });

        vehiclesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehiclesBtn.setBackgroundColor(Color.parseColor("#3C8C92AC"));
                ammoBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                weaponsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                armorsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                healthBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                miskBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));

                vehicleMenu.setVisibility(View.VISIBLE);
                ammoMenu.setVisibility(View.GONE);
                weaponMenu.setVisibility(View.GONE);
                armorMenu.setVisibility(View.GONE);
                healthMenu.setVisibility(View.GONE);
                miskMenu.setVisibility(View.GONE);
            }
        });

        ammoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ammoBtn.setBackgroundColor(Color.parseColor("#3C8C92AC"));
                vehiclesBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                weaponsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                armorsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                healthBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                miskBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));

                ammoMenu.setVisibility(View.VISIBLE);
                vehicleMenu.setVisibility(View.GONE);
                weaponMenu.setVisibility(View.GONE);
                armorMenu.setVisibility(View.GONE);
                healthMenu.setVisibility(View.GONE);
                miskMenu.setVisibility(View.GONE);
            }
        });

        weaponsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weaponsBtn.setBackgroundColor(Color.parseColor("#3C8C92AC"));
                ammoBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                vehiclesBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                armorsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                healthBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                miskBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));

                weaponMenu.setVisibility(View.VISIBLE);
                ammoMenu.setVisibility(View.GONE);
                vehicleMenu.setVisibility(View.GONE);
                armorMenu.setVisibility(View.GONE);
                healthMenu.setVisibility(View.GONE);
                miskMenu.setVisibility(View.GONE);
            }
        });

        armorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                armorsBtn.setBackgroundColor(Color.parseColor("#3C8C92AC"));
                weaponsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                ammoBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                vehiclesBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                healthBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                miskBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));

                armorMenu.setVisibility(View.VISIBLE);
                weaponMenu.setVisibility(View.GONE);
                ammoMenu.setVisibility(View.GONE);
                vehicleMenu.setVisibility(View.GONE);
                healthMenu.setVisibility(View.GONE);
                miskMenu.setVisibility(View.GONE);
            }
        });

        healthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                healthBtn.setBackgroundColor(Color.parseColor("#3C8C92AC"));
                armorsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                weaponsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                ammoBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                vehiclesBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                miskBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));

                healthMenu.setVisibility(View.VISIBLE);
                armorMenu.setVisibility(View.GONE);
                weaponMenu.setVisibility(View.GONE);
                ammoMenu.setVisibility(View.GONE);
                vehicleMenu.setVisibility(View.GONE);
                miskMenu.setVisibility(View.GONE);
            }
        });

        miskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miskBtn.setBackgroundColor(Color.parseColor("#3C8C92AC"));
                healthBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                armorsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                weaponsBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                ammoBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));
                vehiclesBtn.setBackgroundColor(Color.parseColor("#FF1F2B3F"));

                miskMenu.setVisibility(View.VISIBLE);
                healthMenu.setVisibility(View.GONE);
                armorMenu.setVisibility(View.GONE);
                weaponMenu.setVisibility(View.GONE);
                ammoMenu.setVisibility(View.GONE);
                vehicleMenu.setVisibility(View.GONE);

            }
        });

        mView.findViewById(R.id.relativeLayoutParent).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @SuppressLint({"ClickableViewAccessibility", "MissingPermission", "NewApi"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                logoView.setSelected(event.getAction() == MotionEvent.ACTION_MOVE);
                hideOverlay.setChecked(false);
                //flotLogoAnimation();
                logoView.setAlpha(1.0f);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        logoView.setAlpha(0.5f);
                    }
                }, 2000);
                Handler handler = new Handler();
                if (gestureDetector.onTouchEvent(event)) {
                    espView.setVisibility(View.VISIBLE);
                    logoView.setVisibility(View.GONE);
                    return true;
                } else {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            logoView.setAlpha(1.0f);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    logoView.setAlpha(0.5f);
                                }
                            }, 2000);
                             Vibrator vvv = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                             vvv.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    handler.postDelayed(this, 100);
                                      vvv.cancel();
                                }
                            };
                            initialX = params.x;
                            initialY = params.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            return true;

                        case MotionEvent.ACTION_MOVE:
                            logoView.setAlpha(1.0f);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    logoView.setAlpha(0.5f);
                                }
                            }, 2000);
                            params.x = initialX + (int) (event.getRawX() - initialTouchX);
                            params.y = initialY + (int) (event.getRawY() - initialTouchY);
                            logoView.setSelected(event.getAction() == MotionEvent.ACTION_MOVE);
                            mWindowManager.updateViewLayout(mView, params);
                            return true;
                    }
                    return false;

                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mView != null) mWindowManager.removeView(mView);
    }

    @Override
    public void onClick(View v) {
    }

    private String getType() {
        SharedPreferences sp = this.getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        return sp.getString("type", "1");
    }

    private void setValue(String key, boolean b) {
        SharedPreferences sp = this.getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(key, b);
        ed.apply();
    }

    boolean getConfig(String key) {
        SharedPreferences sp = this.getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
        // return !key.equals("");
    }


    void setFrameRate(int frameRate) {
        SharedPreferences sp = this.getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("frameRate", frameRate);
        ed.apply();

    }

    int getFrameRate() {
        SharedPreferences sp = this.getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        return sp.getInt("frameRate", 45);
    }

    int getFps() {
        SharedPreferences sp = this.getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        return sp.getInt("fps", 1);
    }

    void setColorItems(int ColorItems) {
        SharedPreferences sp = this.getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("colorItems", ColorItems);
        ed.apply();
    }

    int getItemsColor() {
        SharedPreferences sp = this.getSharedPreferences("activeValues", Context.MODE_PRIVATE);
        return sp.getInt("ColorItems", 1);
    }


    public static void HideFloat() {
        if (Instance != null) {
            Instance.Hide();
        }
    }

    public void Hide() {
        stopSelf();
        exit(-1);
    }


    private void Init() {
        hideOverlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    SettingValue(16, hideOverlay.isChecked());
                    Toast.makeText(SsFlawMain.this, "Remember the last position of icon to reopen.", Toast.LENGTH_LONG).show();
                } else {
                    SettingValue(16, false);
                    Toast.makeText(SsFlawMain.this, "Overlay visible", Toast.LENGTH_LONG).show();
                }
            }
        });

        //final CheckBox actorBox = mView.findViewById(R.id.playerBox);
        final CheckBox actorsTopLine = mView.findViewById(R.id.playerLine);
        //final CheckBox thrrowables = mView.findViewById(R.id.grenadeWarning);
        final CheckBox edgeWarns = mView.findViewById(R.id.edgeWarning);
        final CheckBox actorsCount = mView.findViewById(R.id.enenmyCount);

        final CheckBox actorsHealth = mView.findViewById(R.id.actorHealth);
        final CheckBox actorsName = mView.findViewById(R.id.actorsName);
        final CheckBox actorsTeamID = mView.findViewById(R.id.actorsID);
        //final CheckBox redDots = mView.findViewById(R.id.playerDots);
        final CheckBox actorsDistance = mView.findViewById(R.id.playerDistance);
        final CheckBox actorSkeleton = mView.findViewById(R.id.playerSkeleton);
        final CheckBox actorsWeapons = mView.findViewById(R.id.playerWeapon);
        actorSkeleton.setChecked(false);


        //Vehicles
        Buggy = mView.findViewById(R.id.veh_buggy);
        Buggy.setChecked(getConfig((String) Buggy.getText()));
        Buggy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                setValue(String.valueOf(Buggy.getText()), Buggy.isChecked());
            }
        });

        final CheckBox UAZ = mView.findViewById(R.id.veh_uaz);
        UAZ.setChecked(getConfig((String) UAZ.getText()));
        UAZ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                setValue(String.valueOf(UAZ.getText()), UAZ.isChecked());
            }
        });

        final CheckBox Trike = mView.findViewById(R.id.veh_trick);
        Trike.setChecked(getConfig((String) Trike.getText()));
        Trike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Trike.getText()), Trike.isChecked());

            }
        });

        final CheckBox Bike = mView.findViewById(R.id.veh_honda);
        Bike.setChecked(getConfig((String) Bike.getText()));
        Bike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Bike.getText()), Bike.isChecked());
            }
        });

        final CheckBox Dacia = mView.findViewById(R.id.veh_dacia);
        Dacia.setChecked(getConfig((String) Dacia.getText()));
        Dacia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Dacia.getText()), Dacia.isChecked());
            }
        });

        final CheckBox Jet = mView.findViewById(R.id.veh_aquarail);
        Jet.setChecked(getConfig((String) Jet.getText()));
        Jet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Jet.getText()), Jet.isChecked());
            }
        });

        final CheckBox Boat = mView.findViewById(R.id.veh_boat);
        Boat.setChecked(getConfig((String) Boat.getText()));
        Boat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Boat.getText()), Boat.isChecked());
            }
        });

        final CheckBox Scooter = mView.findViewById(R.id.veh_scooter);
        Scooter.setChecked(getConfig((String) Scooter.getText()));
        Scooter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Scooter.getText()), Scooter.isChecked());
            }
        });

        final CheckBox Bus = mView.findViewById(R.id.veh_bus);
        Bus.setChecked(getConfig((String) Bus.getText()));
        Bus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Bus.getText()), Bus.isChecked());
            }
        });

        final CheckBox Mirado = mView.findViewById(R.id.veh_mirado);
        Mirado.setChecked(getConfig((String) Mirado.getText()));
        Mirado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Mirado.getText()), Mirado.isChecked());
            }
        });

        final CheckBox Rony = mView.findViewById(R.id.veh_rony);
        Rony.setChecked(getConfig((String) Rony.getText()));
        Rony.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Rony.getText()), Rony.isChecked());
            }
        });

        final CheckBox Snowbike = mView.findViewById(R.id.veh_snowbike);
        Snowbike.setChecked(getConfig((String) Snowbike.getText()));
        Snowbike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Snowbike.getText()), Snowbike.isChecked());
            }
        });

        final CheckBox Snowmobile = mView.findViewById(R.id.veh_snowmobile);
        Snowmobile.setChecked(getConfig((String) Snowmobile.getText()));
        Snowmobile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Snowmobile.getText()), Snowmobile.isChecked());
            }
        });

        final CheckBox Tempo = mView.findViewById(R.id.veh_tuk);
        Tempo.setChecked(getConfig((String) Tempo.getText()));
        Tempo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Tempo.getText()), Tempo.isChecked());
            }
        });

        final CheckBox Truck = mView.findViewById(R.id.veh_truck);
        Truck.setChecked(getConfig((String) Truck.getText()));
        Truck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(Truck.getText()), Truck.isChecked());
            }
        });

        final CheckBox MonsterTruck = mView.findViewById(R.id.veh_monster_truck);
        MonsterTruck.setChecked(getConfig((String) MonsterTruck.getText()));
        MonsterTruck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(MonsterTruck.getText()), MonsterTruck.isChecked());
            }
        });

        final CheckBox BRDM = mView.findViewById(R.id.veh_brdm);
        BRDM.setChecked(getConfig((String) BRDM.getText()));
        BRDM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(BRDM.getText()), BRDM.isChecked());
            }
        });

        final CheckBox LadaNiva = mView.findViewById(R.id.veh_ladaniva);
        LadaNiva.setChecked(getConfig((String) LadaNiva.getText()));
        LadaNiva.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(LadaNiva.getText()), LadaNiva.isChecked());
            }
        });

        final CheckBox CoupeRB = mView.findViewById(R.id.veh_coupe);
        CoupeRB.setChecked(getConfig((String) CoupeRB.getText()));
        CoupeRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(CoupeRB.getText()), CoupeRB.isChecked());
            }
        });


        final CheckBox UTV = mView.findViewById(R.id.veh_utv);
        UTV.setChecked(getConfig((String) UTV.getText()));
        UTV.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(UTV.getText()), UTV.isChecked());
            }
        });

        final Switch switchClearItems = mView.findViewById(R.id.checkboxitemstyle);
        switchClearItems.setChecked(getConfig("display_item_more_clearly"));
        switchClearItems.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue("display_item_more_clearly", switchClearItems.isChecked());
            }
        });

        actorsName.setChecked(getConfig((String) actorsName.getText()));
        SettingValue(1, getConfig((String) actorsName.getText()));
        actorsName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(actorsName.getText()), actorsName.isChecked());
                SettingValue(1, actorsName.isChecked());
            }
        });

        actorsCount.setChecked(getConfig((String) actorsCount.getText()));
        SettingValue(2, getConfig((String) actorsCount.getText()));
        actorsCount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(actorsCount.getText()), actorsCount.isChecked());
                SettingValue(2, actorsCount.isChecked());
            }
        });

        actorSkeleton.setChecked(getConfig((String) actorSkeleton.getText()));
        SettingValue(3, getConfig((String) actorSkeleton.getText()));
        actorSkeleton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(actorSkeleton.getText()), actorSkeleton.isChecked());
                SettingValue(3, actorSkeleton.isChecked());
            }
        });

        /*actorBox.setChecked(getConfig((String) actorBox.getText()));
        SettingValue(4, getConfig((String) actorBox.getText()));
        actorBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(actorBox.getText()), actorBox.isChecked());
                SettingValue(4, actorBox.isChecked());
            }
        });*/

        final RadioButton disabledBox = mView.findViewById(R.id.disableBox);
        final RadioButton legacymodeBox = mView.findViewById(R.id.legacymode);
        final RadioButton precisemodeBox = mView.findViewById(R.id.precisemode);


        disabledBox.setChecked(getConfig((String) disabledBox.getText()));
        disabledBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    precisemodeBox.setChecked(false);
                    legacymodeBox.setChecked(false);
                }
                setValue(String.valueOf(disabledBox.getText()), disabledBox.isChecked());
            }
        });

        precisemodeBox.setChecked(getConfig((String) precisemodeBox.getText()));
        SettingValue(4, getConfig((String) precisemodeBox.getText()));
        precisemodeBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    disabledBox.setChecked(false);
                    legacymodeBox.setChecked(false);
                }
                setValue(String.valueOf(precisemodeBox.getText()), precisemodeBox.isChecked());
                SettingValue(4, precisemodeBox.isChecked());
            }
        });

        legacymodeBox.setChecked(getConfig((String) legacymodeBox.getText()));
        SettingValue(5, getConfig((String) legacymodeBox.getText()));
        legacymodeBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    disabledBox.setChecked(false);
                    precisemodeBox.setChecked(false);
                }
                setValue(String.valueOf(legacymodeBox.getText()), legacymodeBox.isChecked());
                SettingValue(5, legacymodeBox.isChecked());
            }
        });

        edgeWarns.setChecked(getConfig((String) edgeWarns.getText()));
        SettingValue(6, getConfig((String) edgeWarns.getText()));
        edgeWarns.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(edgeWarns.getText()), edgeWarns.isChecked());
                SettingValue(6, edgeWarns.isChecked());
            }
        });


        actorsTeamID.setChecked(getConfig((String) actorsTeamID.getText()));
        SettingValue(7, getConfig((String) actorsTeamID.getText()));
        actorsTeamID.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(actorsTeamID.getText()), actorsTeamID.isChecked());
                SettingValue(7, actorsTeamID.isChecked());
            }
        });

        actorsDistance.setChecked(getConfig((String) actorsDistance.getText()));
        SettingValue(8, getConfig((String) actorsDistance.getText()));
        actorsDistance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(actorsDistance.getText()), actorsDistance.isChecked());
                SettingValue(8, actorsDistance.isChecked());
            }
        });

        actorsTopLine.setChecked(getConfig((String) actorsTopLine.getText()));
        SettingValue(9, getConfig((String) actorsTopLine.getText()));
        actorsTopLine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(actorsTopLine.getText()), actorsTopLine.isChecked());
                SettingValue(9, actorsTopLine.isChecked());
            }
        });

        actorsHealth.setChecked(getConfig((String) actorsHealth.getText()));
        SettingValue(11, getConfig((String) actorsHealth.getText()));
        actorsHealth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(actorsHealth.getText()), actorsHealth.isChecked());
                SettingValue(11, actorsHealth.isChecked());
            }
        });

        actorsWeapons.setChecked(getConfig((String) actorsWeapons.getText()));
        SettingValue(12, getConfig((String) actorsWeapons.getText()));
        actorsWeapons.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(actorsWeapons.getText()), actorsWeapons.isChecked());
                SettingValue(12, actorsWeapons.isChecked());
            }
        });

        final CheckBox weaponAmmo = mView.findViewById(R.id.weaponammo);
        weaponAmmo.setChecked(getConfig((String) weaponAmmo.getText()));
        SettingValue(13, getConfig((String) weaponAmmo.getText()));
        weaponAmmo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(weaponAmmo.getText()), weaponAmmo.isChecked());
                SettingValue(13, weaponAmmo.isChecked());
            }
        });

        final CheckBox throwables = mView.findViewById(R.id.grenadeWarning);
        throwables.setChecked(getConfig((String) throwables.getText()));
        SettingValue(14, getConfig((String) throwables.getText()));
        throwables.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(throwables.getText()), throwables.isChecked());
                SettingValue(14, throwables.isChecked());
            }
        });

        //Items<Ammos>
        final CheckBox am300 = mView.findViewById(R.id.am300);
        final CheckBox am45 = mView.findViewById(R.id.am45);
        final CheckBox am12g = mView.findViewById(R.id.amG12);
        final CheckBox am556 = mView.findViewById(R.id.am556);
        final CheckBox am762 = mView.findViewById(R.id.am762);
        final CheckBox am9mm = mView.findViewById(R.id.am9);

        am300.setChecked(getConfig((String) am300.getText()));
        am300.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(am300.getText()), am300.isChecked());
            }
        });

        am45.setChecked(getConfig((String) am45.getText()));
        am45.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(am45.getText()), am45.isChecked());
            }
        });

        am12g.setChecked(getConfig((String) am12g.getText()));
        am12g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(am12g.getText()), am12g.isChecked());
            }
        });

        am556.setChecked(getConfig((String) am556.getText()));
        am556.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(am556.getText()), am556.isChecked());
            }
        });

        am762.setChecked(getConfig((String) am762.getText()));
        am762.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(am762.getText()), am762.isChecked());
            }
        });

        am9mm.setChecked(getConfig((String) am9mm.getText()));
        am9mm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(am9mm.getText()), am9mm.isChecked());
            }
        });

        //Armors
        final CheckBox armorLvl2 = mView.findViewById(R.id.armorv2);
        final CheckBox armorLvl3 = mView.findViewById(R.id.armorv3);
        final CheckBox helmetLvl2 = mView.findViewById(R.id.helmetv2);
        final CheckBox helmetLvl3 = mView.findViewById(R.id.helmetv3);

        armorLvl2.setChecked(getConfig((String) armorLvl2.getText()));
        armorLvl2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(armorLvl2.getText()), armorLvl2.isChecked());
            }
        });

        armorLvl3.setChecked(getConfig((String) armorLvl3.getText()));
        armorLvl3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(armorLvl3.getText()), armorLvl3.isChecked());
            }
        });

        helmetLvl2.setChecked(getConfig((String) helmetLvl2.getText()));
        helmetLvl2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(helmetLvl2.getText()), helmetLvl2.isChecked());
            }
        });

        helmetLvl3.setChecked(getConfig((String) helmetLvl3.getText()));
        helmetLvl3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(helmetLvl3.getText()), helmetLvl3.isChecked());
            }
        });


        //HealthItems
        final CheckBox bandage = mView.findViewById(R.id.bandag);
        final CheckBox drink = mView.findViewById(R.id.drink);
        final CheckBox firstAir = mView.findViewById(R.id.firstaid);
        final CheckBox injection = mView.findViewById(R.id.injection);
        final CheckBox pills = mView.findViewById(R.id.pills);

        bandage.setChecked(getConfig((String) bandage.getText()));
        bandage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(bandage.getText()), bandage.isChecked());
            }
        });

        drink.setChecked(getConfig((String) drink.getText()));
        drink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(drink.getText()), drink.isChecked());
            }
        });

        firstAir.setChecked(getConfig((String) firstAir.getText()));
        firstAir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(firstAir.getText()), firstAir.isChecked());
            }
        });

        injection.setChecked(getConfig((String) injection.getText()));
        injection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(injection.getText()), injection.isChecked());
            }
        });

        pills.setChecked(getConfig((String) pills.getText()));
        pills.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(pills.getText()), pills.isChecked());
            }
        });

        //MiskItems
        final CheckBox airCraft = mView.findViewById(R.id.aircraft);
        final CheckBox airDrop = mView.findViewById(R.id.airdrop);
        final CheckBox bag2 = mView.findViewById(R.id.bagv2);
        final CheckBox bag3 = mView.findViewById(R.id.bagv3);
        final CheckBox compensator = mView.findViewById(R.id.compe);
        final CheckBox loot = mView.findViewById(R.id.loot);
        final CheckBox supressor = mView.findViewById(R.id.supressor);


        airCraft.setChecked(getConfig((String) airCraft.getText()));
        airCraft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(airCraft.getText()), airCraft.isChecked());
            }
        });

        airDrop.setChecked(getConfig((String) airDrop.getText()));
        airDrop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(airDrop.getText()), airDrop.isChecked());
            }
        });

        bag2.setChecked(getConfig((String) bag2.getText()));
        bag2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(bag2.getText()), bag2.isChecked());
            }
        });

        bag3.setChecked(getConfig((String) bag3.getText()));
        bag3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(bag3.getText()), bag3.isChecked());
            }
        });

        compensator.setChecked(getConfig((String) compensator.getText()));
        compensator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(compensator.getText()), compensator.isChecked());
            }
        });


        loot.setChecked(getConfig((String) loot.getText()));
        loot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(loot.getText()), loot.isChecked());
            }
        });

        supressor.setChecked(getConfig((String) supressor.getText()));
        supressor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(supressor.getText()), supressor.isChecked());
            }
        });

        //WeaponsItems
        final CheckBox x2 = mView.findViewById(R.id.x2);
        final CheckBox x3 = mView.findViewById(R.id.x3);
        final CheckBox x4 = mView.findViewById(R.id.x4);
        final CheckBox x6 = mView.findViewById(R.id.x6);
        final CheckBox x8 = mView.findViewById(R.id.x8);
        final CheckBox k98 = mView.findViewById(R.id.kar98);
        final CheckBox akm = mView.findViewById(R.id.akm);
        final CheckBox aug = mView.findViewById(R.id.aug);
        final CheckBox awm = mView.findViewById(R.id.awm);
        final CheckBox bow = mView.findViewById(R.id.crossbo);
        final CheckBox dp12 = mView.findViewById(R.id.dp12);
        final CheckBox dp28 = mView.findViewById(R.id.dp28);
        final CheckBox deagle = mView.findViewById(R.id.deagle);
        final CheckBox redDot = mView.findViewById(R.id.dot);
        final CheckBox flare = mView.findViewById(R.id.flare);
        final CheckBox g36 = mView.findViewById(R.id.g36);
        final CheckBox grenade = mView.findViewById(R.id.grenade);
        final CheckBox groza = mView.findViewById(R.id.groza);
        final CheckBox hollo = mView.findViewById(R.id.hollo);
        final CheckBox m1014 = mView.findViewById(R.id.m1014);
        final CheckBox m16 = mView.findViewById(R.id.m16);
        final CheckBox m24 = mView.findViewById(R.id.m24);
        final CheckBox m249 = mView.findViewById(R.id.m249);
        final CheckBox m4 = mView.findViewById(R.id.m4);
        final CheckBox m762g = mView.findViewById(R.id.m762);
        final CheckBox mk14 = mView.findViewById(R.id.mk14);
        final CheckBox mk47 = mView.findViewById(R.id.mk47);
        final CheckBox mp5k = mView.findViewById(R.id.mp5k);
        final CheckBox mini14 = mView.findViewById(R.id.mini14);
        final CheckBox molotov = mView.findViewById(R.id.molotov);
        final CheckBox pp19 = mView.findViewById(R.id.pp19);
        final CheckBox pan = mView.findViewById(R.id.pan);
        final CheckBox qbu = mView.findViewById(R.id.qbu);
        final CheckBox qbz = mView.findViewById(R.id.qbz);
        final CheckBox s12k = mView.findViewById(R.id.s12k);
        final CheckBox s1897 = mView.findViewById(R.id.s1897);
        final CheckBox s686 = mView.findViewById(R.id.s686);
        final CheckBox scarl = mView.findViewById(R.id.scar);
        final CheckBox sks = mView.findViewById(R.id.sks);
        final CheckBox slr = mView.findViewById(R.id.slr);
        final CheckBox stunt = mView.findViewById(R.id.stun);
        final CheckBox tommy = mView.findViewById(R.id.tomm);
        final CheckBox ump45 = mView.findViewById(R.id.ump);
        final CheckBox uzi = mView.findViewById(R.id.uzi);
        final CheckBox vss = mView.findViewById(R.id.vss);
        final CheckBox vector = mView.findViewById(R.id.vectorGun);
        final CheckBox win94 = mView.findViewById(R.id.win94);


        x2.setChecked(getConfig((String) x2.getText()));
        x2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(x2.getText()), x2.isChecked());
            }
        });

        x3.setChecked(getConfig((String) x3.getText()));
        x3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(x3.getText()), x3.isChecked());
            }
        });

        x4.setChecked(getConfig((String) x4.getText()));
        x4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(x4.getText()), x4.isChecked());
            }
        });

        x6.setChecked(getConfig((String) x6.getText()));
        x6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(x6.getText()), x6.isChecked());
            }
        });

        x8.setChecked(getConfig((String) x8.getText()));
        x8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(x8.getText()), x8.isChecked());
            }
        });

        k98.setChecked(getConfig((String) k98.getText()));
        k98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(k98.getText()), k98.isChecked());
            }
        });

        akm.setChecked(getConfig((String) akm.getText()));
        akm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(akm.getText()), akm.isChecked());
            }
        });

        aug.setChecked(getConfig((String) aug.getText()));
        aug.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(aug.getText()), aug.isChecked());
            }
        });

        awm.setChecked(getConfig((String) awm.getText()));
        awm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(awm.getText()), awm.isChecked());
            }
        });

        bow.setChecked(getConfig((String) bow.getText()));
        bow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(bow.getText()), bow.isChecked());
            }
        });

        dp12.setChecked(getConfig((String) dp12.getText()));
        dp12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(dp12.getText()), dp12.isChecked());
            }
        });

        dp28.setChecked(getConfig((String) dp28.getText()));
        dp28.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(dp28.getText()), dp28.isChecked());
            }
        });

        deagle.setChecked(getConfig((String) deagle.getText()));
        deagle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(deagle.getText()), deagle.isChecked());
            }
        });

        redDot.setChecked(getConfig((String) redDot.getText()));
        redDot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(redDot.getText()), redDot.isChecked());
            }
        });

        flare.setChecked(getConfig((String) flare.getText()));
        flare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(flare.getText()), flare.isChecked());
            }
        });

        g36.setChecked(getConfig((String) g36.getText()));
        g36.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(g36.getText()), g36.isChecked());
            }
        });

        grenade.setChecked(getConfig((String) grenade.getText()));
        grenade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(grenade.getText()), grenade.isChecked());
            }
        });

        groza.setChecked(getConfig((String) groza.getText()));
        groza.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(groza.getText()), groza.isChecked());
            }
        });

        hollo.setChecked(getConfig((String) hollo.getText()));
        hollo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(hollo.getText()), hollo.isChecked());
            }
        });

        m1014.setChecked(getConfig((String) m1014.getText()));
        m1014.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(m1014.getText()), m1014.isChecked());
            }
        });

        m16.setChecked(getConfig((String) m16.getText()));
        m16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(m16.getText()), m16.isChecked());
            }
        });

        m24.setChecked(getConfig((String) m24.getText()));
        m24.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(m24.getText()), m24.isChecked());
            }
        });

        m249.setChecked(getConfig((String) m249.getText()));
        m249.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(m249.getText()), m249.isChecked());
            }
        });

        m4.setChecked(getConfig((String) m4.getText()));
        m4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(m4.getText()), m4.isChecked());
            }
        });

        m762g.setChecked(getConfig((String) m762g.getText()));
        m762g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(m762g.getText()), m762g.isChecked());
            }
        });

        mk14.setChecked(getConfig((String) mk14.getText()));
        mk14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(mk14.getText()), mk14.isChecked());
            }
        });

        mk47.setChecked(getConfig((String) mk47.getText()));
        mk47.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(mk47.getText()), mk47.isChecked());
            }
        });

        mp5k.setChecked(getConfig((String) mp5k.getText()));
        mp5k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(mp5k.getText()), mp5k.isChecked());
            }
        });

        mini14.setChecked(getConfig((String) mini14.getText()));
        mini14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(mini14.getText()), mini14.isChecked());
            }
        });

        molotov.setChecked(getConfig((String) molotov.getText()));
        molotov.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(molotov.getText()), molotov.isChecked());
            }
        });

        pp19.setChecked(getConfig((String) pp19.getText()));
        pp19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(pp19.getText()), pp19.isChecked());
            }
        });

        pan.setChecked(getConfig((String) pan.getText()));
        pan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(pan.getText()), pan.isChecked());
            }
        });

        qbu.setChecked(getConfig((String) qbu.getText()));
        qbu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(qbu.getText()), qbu.isChecked());
            }
        });

        qbz.setChecked(getConfig((String) qbz.getText()));
        qbz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(qbz.getText()), qbz.isChecked());
            }
        });

        s12k.setChecked(getConfig((String) s12k.getText()));
        s12k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(s12k.getText()), s12k.isChecked());
            }
        });

        s1897.setChecked(getConfig((String) s1897.getText()));
        s1897.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(s1897.getText()), s1897.isChecked());
            }
        });

        s686.setChecked(getConfig((String) s686.getText()));
        s686.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(s686.getText()), s686.isChecked());
            }
        });

        scarl.setChecked(getConfig((String) scarl.getText()));
        scarl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(scarl.getText()), scarl.isChecked());
            }
        });

        sks.setChecked(getConfig((String) sks.getText()));
        sks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(sks.getText()), sks.isChecked());
            }
        });

        slr.setChecked(getConfig((String) slr.getText()));
        slr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(slr.getText()), slr.isChecked());
            }
        });

        stunt.setChecked(getConfig((String) stunt.getText()));
        stunt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(stunt.getText()), stunt.isChecked());
            }
        });

        tommy.setChecked(getConfig((String) tommy.getText()));
        tommy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(tommy.getText()), tommy.isChecked());
            }
        });

        ump45.setChecked(getConfig((String) ump45.getText()));
        ump45.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(ump45.getText()), ump45.isChecked());
            }
        });

        uzi.setChecked(getConfig((String) uzi.getText()));
        uzi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(uzi.getText()), uzi.isChecked());
            }
        });

        vss.setChecked(getConfig((String) vss.getText()));
        vss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(vss.getText()), vss.isChecked());
            }
        });

        vector.setChecked(getConfig((String) vector.getText()));
        vector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(vector.getText()), vector.isChecked());
            }
        });

        win94.setChecked(getConfig((String) win94.getText()));
        win94.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setValue(String.valueOf(win94.getText()), win94.isChecked());
            }
        });


        final Button clrRed = mView.findViewById(R.id.red);
        final Button clrPink = mView.findViewById(R.id.pink);
        final Button clrGreen = mView.findViewById(R.id.green);
        final Button clrYellow = mView.findViewById(R.id.yellow);
        final Button clrBlue = mView.findViewById(R.id.blue);
        final Button clrWhite = mView.findViewById(R.id.white);

        clrRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dacia.isChecked()) {
                    setValue("clrRedDacia", true);
                    setValue("clrPinkDacia", false);
                    setValue("clrGreenDacia", false);
                    setValue("clrBlueDacia", false);
                    setValue("clrWhiteDacia", false);
                    setValue("clrYellowDacia", false);
                    cPrefs.write(String.valueOf(Dacia.getText()), RED);
                    Dacia.setTextColor(Color.parseColor("#FF0000"));
                    Dacia.setChecked(false);
                    if (!Dacia.isChecked()) {
                        setValue(String.valueOf(Dacia.getText()), true);
                    }
                }
                if (UAZ.isChecked()) {
                    setValue("clrRedUAZ", true);
                    setValue("clrPinkUAZ", false);
                    setValue("clrGreenUAZ", false);
                    setValue("clrBlueUAZ", false);
                    setValue("clrWhiteUAZ", false);
                    setValue("clrYellowUAZ", false);
                    cPrefs.write(String.valueOf(UAZ.getText()), RED);
                    UAZ.setTextColor(Color.parseColor("#FF0000"));
                    setValue("UAZ", UAZ.isChecked());
                    UAZ.setChecked(false);
                    if (!UAZ.isChecked()) {
                        setValue(String.valueOf(UAZ.getText()), true);
                    }
                }
                if (Buggy.isChecked()) {
                    setValue("clrRedBuggy", true);
                    setValue("clrPinkBuggy", false);
                    setValue("clrGreenBuggy", false);
                    setValue("clrBlueBuggy", false);
                    setValue("clrWhiteBuggy", false);
                    setValue("clrYellowBuggy", false);
                    cPrefs.write(String.valueOf(Buggy.getText()), RED);
                    Buggy.setTextColor(Color.parseColor("#FF0000"));
                    Buggy.setChecked(false);
                    if (!Buggy.isChecked()) {
                        setValue(String.valueOf(Buggy.getText()), true);
                    }
                }
                if (Bike.isChecked()) {
                    setValue("clrRedBike", true);
                    setValue("clrPinkBike", false);
                    setValue("clrGreenBike", false);
                    setValue("clrBlueBike", false);
                    setValue("clrWhiteBike", false);
                    setValue("clrYellowBike", false);
                    cPrefs.write(String.valueOf(Bike.getText()), RED);
                    Bike.setTextColor(Color.parseColor("#FF0000"));
                    Bike.setChecked(false);
                    if (!Bike.isChecked()) {
                        setValue(String.valueOf(Bike.getText()), true);
                    }
                }
                if (Jet.isChecked()) {
                    setValue("clrRedAquaRail", true);
                    setValue("clrPinkAquaRail", false);
                    setValue("clrGreenAquaRail", false);
                    setValue("clrBlueAquaRail", false);
                    setValue("clrWhiteAquaRail", false);
                    setValue("clrYellowAquaRail", false);
                    cPrefs.write(String.valueOf(Jet.getText()), RED);
                    Jet.setTextColor(Color.parseColor("#FF0000"));
                    Jet.setChecked(false);
                    if (!Jet.isChecked()) {
                        setValue(String.valueOf(Jet.getText()), true);
                    }
                }
                if (Boat.isChecked()) {
                    setValue("clrRedBoat", true);
                    setValue("clrPinkBoat", false);
                    setValue("clrGreenBoat", false);
                    setValue("clrBlueBoat", false);
                    setValue("clrWhiteBoat", false);
                    setValue("clrYellowBoat", false);
                    cPrefs.write(String.valueOf(Boat.getText()), RED);
                    Boat.setTextColor(Color.parseColor("#FF0000"));
                    Boat.setChecked(false);
                    if (!Boat.isChecked()) {
                        setValue(String.valueOf(Boat.getText()), true);
                    }
                }
                if (Bus.isChecked()) {
                    setValue("clrRedBus", true);
                    setValue("clrPinkBus", false);
                    setValue("clrGreenBus", false);
                    setValue("clrBlueBus", false);
                    setValue("clrWhiteBus", false);
                    setValue("clrYellowBus", false);
                    cPrefs.write(String.valueOf(Bus.getText()), RED);
                    Bus.setTextColor(Color.parseColor("#FF0000"));
                    Bus.setChecked(false);
                    if (!Bus.isChecked()) {
                        setValue(String.valueOf(Bus.getText()), true);
                    }
                }
                if (Mirado.isChecked()) {
                    setValue("clrRedMirado", true);
                    setValue("clrPinkMirado", false);
                    setValue("clrGreenMirado", false);
                    setValue("clrBlueMirado", false);
                    setValue("clrWhiteMirado", false);
                    setValue("clrYellowMirado", false);
                    cPrefs.write(String.valueOf(Mirado.getText()), RED);
                    Mirado.setTextColor(Color.parseColor("#FF0000"));
                    Mirado.setChecked(false);
                    if (!Mirado.isChecked()) {
                        setValue(String.valueOf(Mirado.getText()), true);
                    }
                }
                if (Scooter.isChecked()) {
                    setValue("clrRedScooter", true);
                    setValue("clrPinkScooter", false);
                    setValue("clrGreenScooter", false);
                    setValue("clrBlueScooter", false);
                    setValue("clrWhiteScooter", false);
                    setValue("clrYellowScooter", false);
                    cPrefs.write(String.valueOf(Scooter.getText()), RED);
                    Scooter.setTextColor(Color.parseColor("#FF0000"));
                    Scooter.setChecked(false);
                    if (!Scooter.isChecked()) {
                        setValue(String.valueOf(Scooter.getText()), true);
                    }
                }
                if (Rony.isChecked()) {
                    setValue("clrRedRony", true);
                    setValue("clrPinkRony", false);
                    setValue("clrGreenRony", false);
                    setValue("clrBlueRony", false);
                    setValue("clrWhiteRony", false);
                    setValue("clrYellowRony", false);
                    cPrefs.write(String.valueOf(Rony.getText()), RED);
                    Rony.setTextColor(Color.parseColor("#FF0000"));
                    Rony.setChecked(false);
                    if (!Rony.isChecked()) {
                        setValue(String.valueOf(Rony.getText()), true);
                    }
                }
                if (Snowbike.isChecked()) {
                    setValue("clrRedSnowbike", true);
                    setValue("clrPinkSnowbike", false);
                    setValue("clrGreenSnowbike", false);
                    setValue("clrBlueSnowbike", false);
                    setValue("clrWhiteSnowbike", false);
                    setValue("clrYellowSnowbike", false);
                    cPrefs.write(String.valueOf(Snowbike.getText()), RED);
                    Snowbike.setTextColor(Color.parseColor("#FF0000"));
                    Snowbike.setChecked(false);
                    if (!Snowbike.isChecked()) {
                        setValue(String.valueOf(Snowbike.getText()), true);
                    }
                }
                if (Snowmobile.isChecked()) {
                    setValue("clrRedSnowmobile", true);
                    setValue("clrPinkSnowmobile", false);
                    setValue("clrGreenSnowmobile", false);
                    setValue("clrBlueSnowmobile", false);
                    setValue("clrWhiteSnowmobile", false);
                    setValue("clrYellowSnowmobile", false);
                    cPrefs.write(String.valueOf(Snowmobile.getText()), RED);
                    Snowmobile.setTextColor(Color.parseColor("#FF0000"));
                    Snowmobile.setChecked(false);
                    if (!Snowmobile.isChecked()) {
                        setValue(String.valueOf(Snowmobile.getText()), true);
                    }
                }
                if (Tempo.isChecked()) {
                    setValue("clrRedTuk", true);
                    setValue("clrPinkTuk", false);
                    setValue("clrGreenTuk", false);
                    setValue("clrBlueTuk", false);
                    setValue("clrWhiteTuk", false);
                    setValue("clrYellowTuk", false);
                    cPrefs.write(String.valueOf(Tempo.getText()), RED);
                    Tempo.setTextColor(Color.parseColor("#FF0000"));
                    Tempo.setChecked(false);
                    if (!Tempo.isChecked()) {
                        setValue(String.valueOf(Tempo.getText()), true);
                    }
                }
                if (Truck.isChecked()) {
                    setValue("clrRedTruck", true);
                    setValue("clrPinkTruck", false);
                    setValue("clrGreenTruck", false);
                    setValue("clrBlueTruck", false);
                    setValue("clrWhiteTruck", false);
                    setValue("clrYellowTruck", false);
                    cPrefs.write(String.valueOf(Truck.getText()), RED);
                    Truck.setTextColor(Color.parseColor("#FF0000"));
                    Truck.setChecked(false);
                    if (!Truck.isChecked()) {
                        setValue(String.valueOf(Truck.getText()), true);
                    }
                }
                if (BRDM.isChecked()) {
                    setValue("clrRedBRDM", true);
                    setValue("clrPinkBRDM", false);
                    setValue("clrGreenBRDM", false);
                    setValue("clrBlueBRDM", false);
                    setValue("clrWhiteBRDM", false);
                    setValue("clrYellowBRDM", false);
                    cPrefs.write(String.valueOf(BRDM.getText()), RED);
                    BRDM.setTextColor(Color.parseColor("#FF0000"));
                    BRDM.setChecked(false);
                    if (!BRDM.isChecked()) {
                        setValue(String.valueOf(BRDM.getText()), true);
                    }
                }
                if (LadaNiva.isChecked()) {
                    setValue("clrRedLadaNiva", true);
                    setValue("clrPinkLadaNiva", false);
                    setValue("clrGreenLadaNiva", false);
                    setValue("clrBlueLadaNiva", false);
                    setValue("clrWhiteLadaNiva", false);
                    setValue("clrYellowLadaNiva", false);
                    cPrefs.write(String.valueOf(LadaNiva.getText()), RED);
                    LadaNiva.setTextColor(Color.parseColor("#FF0000"));
                    LadaNiva.setChecked(false);
                    if (!LadaNiva.isChecked()) {
                        setValue(String.valueOf(LadaNiva.getText()), true);
                    }
                }
                if (MonsterTruck.isChecked()) {
                    setValue("clrRedMonster", true);
                    setValue("clrPinkMonster", false);
                    setValue("clrGreenMonster", false);
                    setValue("clrBlueMonster", false);
                    setValue("clrWhiteMonster", false);
                    setValue("clrYellowMonster", false);
                    cPrefs.write(String.valueOf(MonsterTruck.getText()), RED);
                    MonsterTruck.setTextColor(Color.parseColor("#FF0000"));
                    MonsterTruck.setChecked(false);
                    if (!MonsterTruck.isChecked()) {
                        setValue(String.valueOf(MonsterTruck.getText()), true);
                    }
                }
                if (CoupeRB.isChecked()) {
                    setValue("clrRedCoupeRB", true);
                    setValue("clrPinkCoupeRB", false);
                    setValue("clrGreenCoupeRB", false);
                    setValue("clrBlueCoupeRB", false);
                    setValue("clrWhiteCoupeRB", false);
                    setValue("clrYellowCoupeRB", false);
                    cPrefs.write(String.valueOf(CoupeRB.getText()), RED);
                    CoupeRB.setTextColor(Color.parseColor("#FF0000"));
                    CoupeRB.setChecked(false);
                    if (!CoupeRB.isChecked()) {
                        setValue(String.valueOf(CoupeRB.getText()), true);
                    }
                }
                if (UTV.isChecked()) {
                    setValue("clrRedUTV", true);
                    setValue("clrPinkUTV", false);
                    setValue("clrGreenUTV", false);
                    setValue("clrBlueUTV", false);
                    setValue("clrWhiteUTV", false);
                    setValue("clrYellowUTV", false);
                    cPrefs.write(String.valueOf(UTV.getText()), RED);
                    UTV.setTextColor(Color.parseColor("#FF0000"));
                    UTV.setChecked(false);
                    if (!UTV.isChecked()) {
                        setValue(String.valueOf(UTV.getText()), true);
                    }
                }

                //Ammos
                if (am300.isChecked()) {
                    setValue("cR300mm", true);
                    setValue("cP300mm", false);
                    setValue("cG300mm", false);
                    setValue("cB300mm", false);
                    setValue("cW300mm", false);
                    setValue("cY300mm", false);
                    cPrefs.write(String.valueOf(am300.getText()), RED);
                    am300.setTextColor(Color.parseColor("#FF0000"));
                    am300.setChecked(false);
                    if (!am300.isChecked()) {
                        setValue(String.valueOf(am300.getText()), true);
                    }
                }

                if (am45.isChecked()) {
                    setValue("cRam45", true);
                    setValue("cPam45", false);
                    setValue("cGam45", false);
                    setValue("cBam45", false);
                    setValue("cWam45", false);
                    setValue("cYam45", false);
                    cPrefs.write(String.valueOf(am45.getText()), RED);
                    am45.setTextColor(Color.parseColor("#FF0000"));
                    am45.setChecked(false);
                    if (!am45.isChecked()) {
                        setValue(String.valueOf(am45.getText()), true);
                    }
                }

                if (am12g.isChecked()) {
                    setValue("cRam12g", true);
                    setValue("cPam12g", false);
                    setValue("cGam12g", false);
                    setValue("cBam12g", false);
                    setValue("cWam12g", false);
                    setValue("cYam12g", false);
                    cPrefs.write(String.valueOf(am12g.getText()), RED);
                    am12g.setTextColor(Color.parseColor("#FF0000"));
                    am12g.setChecked(false);
                    if (!am12g.isChecked()) {
                        setValue(String.valueOf(am12g.getText()), true);
                    }
                }

                if (am556.isChecked()) {
                    setValue("cRam556", true);
                    setValue("cPam556", false);
                    setValue("cGam556", false);
                    setValue("cBam556", false);
                    setValue("cWam556", false);
                    setValue("cYam556", false);
                    cPrefs.write(String.valueOf(am556.getText()), RED);
                    am556.setTextColor(Color.parseColor("#FF0000"));
                    am556.setChecked(false);
                    if (!am556.isChecked()) {
                        setValue(String.valueOf(am556.getText()), true);
                    }
                }

                if (am762.isChecked()) {
                    setValue("cRam762", true);
                    setValue("cPam762", false);
                    setValue("cGam762", false);
                    setValue("cBam762", false);
                    setValue("cWam762", false);
                    setValue("cYam762", false);
                    cPrefs.write(String.valueOf(am762.getText()), RED);
                    am762.setTextColor(Color.parseColor("#FF0000"));
                    am762.setChecked(false);
                    if (!am762.isChecked()) {
                        setValue(String.valueOf(am762.getText()), true);
                    }
                }

                if (am9mm.isChecked()) {
                    setValue("cRam9mm", true);
                    setValue("cPam9mm", false);
                    setValue("cGam9mm", false);
                    setValue("cBam9mm", false);
                    setValue("cWam9mm", false);
                    setValue("cYam9mm", false);
                    cPrefs.write(String.valueOf(am9mm.getText()), RED);
                    am9mm.setTextColor(Color.parseColor("#FF0000"));
                    am9mm.setChecked(false);
                    if (!am9mm.isChecked()) {
                        setValue(String.valueOf(am9mm.getText()), true);
                    }
                }

                if (armorLvl2.isChecked()) {
                    setValue("cRar2", true);
                    setValue("cPar2", false);
                    setValue("cGar2", false);
                    setValue("cBar2", false);
                    setValue("cWar2", false);
                    setValue("cYar2", false);
                    cPrefs.write(String.valueOf(armorLvl2.getText()), RED);
                    armorLvl2.setTextColor(Color.parseColor("#FF0000"));
                    armorLvl2.setChecked(false);
                    if (!armorLvl2.isChecked()) {
                        setValue(String.valueOf(armorLvl2.getText()), true);
                    }
                }

                if (armorLvl3.isChecked()) {
                    setValue("cRar3", true);
                    setValue("cPar3", false);
                    setValue("cGar3", false);
                    setValue("cBar3", false);
                    setValue("cWar3", false);
                    setValue("cYar3", false);
                    cPrefs.write(String.valueOf(armorLvl3.getText()), RED);
                    armorLvl3.setTextColor(Color.parseColor("#FF0000"));
                    armorLvl3.setChecked(false);
                    if (!armorLvl3.isChecked()) {
                        setValue(String.valueOf(armorLvl3.getText()), true);
                    }
                }

                if (helmetLvl2.isChecked()) {
                    setValue("cRhel2", true);
                    setValue("cPhel2", false);
                    setValue("cGhel2", false);
                    setValue("cBhel2", false);
                    setValue("cWhel2", false);
                    setValue("cYhel2", false);
                    cPrefs.write(String.valueOf(helmetLvl2.getText()), RED);
                    helmetLvl2.setTextColor(Color.parseColor("#FF0000"));
                    helmetLvl2.setChecked(false);
                    if (!helmetLvl2.isChecked()) {
                        setValue(String.valueOf(helmetLvl2.getText()), true);
                    }
                }

                if (helmetLvl3.isChecked()) {
                    setValue("cRhel3", true);
                    setValue("cPhel3", false);
                    setValue("cGhel3", false);
                    setValue("cBhel3", false);
                    setValue("cWhel3", false);
                    setValue("cYhel3", false);
                    cPrefs.write(String.valueOf(helmetLvl3.getText()), RED);
                    helmetLvl3.setTextColor(Color.parseColor("#FF0000"));
                    helmetLvl3.setChecked(false);
                    if (!helmetLvl3.isChecked()) {
                        setValue(String.valueOf(helmetLvl3.getText()), true);
                    }
                }

                if (bandage.isChecked()) {
                    setValue("cRban", true);
                    setValue("cPban", false);
                    setValue("cGban", false);
                    setValue("cBban", false);
                    setValue("cWban", false);
                    setValue("cYban", false);
                    cPrefs.write(String.valueOf(bandage.getText()), RED);
                    bandage.setTextColor(Color.parseColor("#FF0000"));
                    bandage.setChecked(false);
                    if (!bandage.isChecked()) {
                        setValue(String.valueOf(bandage.getText()), true);
                    }
                }

                if (drink.isChecked()) {
                    setValue("cRdrink", true);
                    setValue("cPdrink", false);
                    setValue("cGdrink", false);
                    setValue("cBdrink", false);
                    setValue("cWdrink", false);
                    setValue("cYdrink", false);
                    cPrefs.write(String.valueOf(drink.getText()), RED);
                    drink.setTextColor(Color.parseColor("#FF0000"));
                    drink.setChecked(false);
                    if (!drink.isChecked()) {
                        setValue(String.valueOf(drink.getText()), true);
                    }
                }

                if (firstAir.isChecked()) {
                    setValue("cRfa", true);
                    setValue("cPfa", false);
                    setValue("cGfa", false);
                    setValue("cBfa", false);
                    setValue("cWfa", false);
                    setValue("cYfa", false);
                    cPrefs.write(String.valueOf(firstAir.getText()), RED);
                    firstAir.setTextColor(Color.parseColor("#FF0000"));
                    firstAir.setChecked(false);
                    if (!firstAir.isChecked()) {
                        setValue(String.valueOf(firstAir.getText()), true);
                    }
                }

                if (injection.isChecked()) {
                    setValue("cRin", true);
                    setValue("cPin", false);
                    setValue("cGin", false);
                    setValue("cBin", false);
                    setValue("cWin", false);
                    setValue("cYin", false);
                    cPrefs.write(String.valueOf(injection.getText()), RED);
                    injection.setTextColor(Color.parseColor("#FF0000"));
                    injection.setChecked(false);
                    if (!injection.isChecked()) {
                        setValue(String.valueOf(injection.getText()), true);
                    }
                }

                if (pills.isChecked()) {
                    setValue("cRpills", true);
                    setValue("cPpills", false);
                    setValue("cGpills", false);
                    setValue("cBpills", false);
                    setValue("cWpills", false);
                    setValue("cYpills", false);
                    cPrefs.write(String.valueOf(pills.getText()), RED);
                    pills.setTextColor(Color.parseColor("#FF0000"));
                    pills.setChecked(false);
                    if (!pills.isChecked()) {
                        setValue(String.valueOf(pills.getText()), true);
                    }
                }

                //Misk
                if (airCraft.isChecked()) {
                    setValue("cRac", true);
                    setValue("cPac", false);
                    setValue("cGac", false);
                    setValue("cBac", false);
                    setValue("cWac", false);
                    setValue("cYac", false);
                    cPrefs.write(String.valueOf(airCraft.getText()), RED);
                    airCraft.setTextColor(Color.parseColor("#FF0000"));
                    airCraft.setChecked(false);
                    if (!airCraft.isChecked()) {
                        setValue(String.valueOf(airCraft.getText()), true);
                    }
                }

                if (airDrop.isChecked()) {
                    setValue("cRairD", true);
                    setValue("cPairD", false);
                    setValue("cGairD", false);
                    setValue("cBairD", false);
                    setValue("cWairD", false);
                    setValue("cYairD", false);
                    cPrefs.write(String.valueOf(airDrop.getText()), RED);
                    airDrop.setTextColor(Color.parseColor("#FF0000"));
                    airDrop.setChecked(false);
                    if (!airDrop.isChecked()) {
                        setValue(String.valueOf(airDrop.getText()), true);
                    }
                }

                if (bag2.isChecked()) {
                    setValue("cRb2", true);
                    setValue("cPb2", false);
                    setValue("cGb2", false);
                    setValue("cBb2", false);
                    setValue("cWb2", false);
                    setValue("cYb2", false);
                    cPrefs.write(String.valueOf(bag2.getText()), RED);
                    bag2.setTextColor(Color.parseColor("#FF0000"));
                    bag2.setChecked(false);
                    if (!bag2.isChecked()) {
                        setValue(String.valueOf(bag2.getText()), true);
                    }
                }

                if (bag3.isChecked()) {
                    setValue("cRb3", true);
                    setValue("cPb3", false);
                    setValue("cGb3", false);
                    setValue("cBb3", false);
                    setValue("cWb3", false);
                    setValue("cYb3", false);
                    cPrefs.write(String.valueOf(bag3.getText()), RED);
                    bag3.setTextColor(Color.parseColor("#FF0000"));
                    bag3.setChecked(false);
                    if (!bag3.isChecked()) {
                        setValue(String.valueOf(bag3.getText()), true);
                    }
                }

                if (compensator.isChecked()) {
                    setValue("cRcomp", true);
                    setValue("cPcomp", false);
                    setValue("cGcomp", false);
                    setValue("cBcomp", false);
                    setValue("cWcomp", false);
                    setValue("cYcomp", false);
                    cPrefs.write(String.valueOf(compensator.getText()), RED);
                    compensator.setTextColor(Color.parseColor("#FF0000"));
                    compensator.setChecked(false);
                    if (!compensator.isChecked()) {
                        setValue(String.valueOf(compensator.getText()), true);
                    }
                }

                if (loot.isChecked()) {
                    setValue("cRloot", true);
                    setValue("cPloot", false);
                    setValue("cGloot", false);
                    setValue("cBloot", false);
                    setValue("cWloot", false);
                    setValue("cYloot", false);
                    cPrefs.write(String.valueOf(loot.getText()), RED);
                    loot.setTextColor(Color.parseColor("#FF0000"));
                    loot.setChecked(false);
                    if (!loot.isChecked()) {
                        setValue(String.valueOf(loot.getText()), true);
                    }
                }

                if (supressor.isChecked()) {
                    setValue("cRsupp", true);
                    setValue("cPsupp", false);
                    setValue("cGsupp", false);
                    setValue("cBsupp", false);
                    setValue("cWsupp", false);
                    setValue("cYsupp", false);
                    cPrefs.write(String.valueOf(supressor.getText()), RED);
                    supressor.setTextColor(Color.parseColor("#FF0000"));
                    supressor.setChecked(false);
                    if (!supressor.isChecked()) {
                        setValue(String.valueOf(supressor.getText()), true);
                    }
                }

                //Weapons
                if (x2.isChecked()) {
                    setValue("cRx2", true);
                    setValue("cPx2", false);
                    setValue("cGx2", false);
                    setValue("cBx2", false);
                    setValue("cWx2", false);
                    setValue("cYx2", false);
                    cPrefs.write(String.valueOf(x2.getText()), RED);
                    x2.setTextColor(Color.parseColor("#FF0000"));
                    x2.setChecked(false);
                    if (!x2.isChecked()) {
                        setValue(String.valueOf(x2.getText()), true);
                    }
                }

                if (x3.isChecked()) {
                    setValue("cRx3", true);
                    setValue("cPx3", false);
                    setValue("cGx3", false);
                    setValue("cBx3", false);
                    setValue("cWx3", false);
                    setValue("cYx3", false);
                    cPrefs.write(String.valueOf(x3.getText()), RED);
                    x3.setTextColor(Color.parseColor("#FF0000"));
                    x3.setChecked(false);
                    if (!x3.isChecked()) {
                        setValue(String.valueOf(x3.getText()), true);
                    }
                }

                if (x4.isChecked()) {
                    setValue("cRx4", true);
                    setValue("cPx4", false);
                    setValue("cGx4", false);
                    setValue("cBx4", false);
                    setValue("cWx4", false);
                    setValue("cYx4", false);
                    cPrefs.write(String.valueOf(x4.getText()), RED);
                    x4.setTextColor(Color.parseColor("#FF0000"));
                    x4.setChecked(false);
                    if (!x4.isChecked()) {
                        setValue(String.valueOf(x4.getText()), true);
                    }
                }

                if (x6.isChecked()) {
                    setValue("cRx6", true);
                    setValue("cPx6", false);
                    setValue("cGx6", false);
                    setValue("cBx6", false);
                    setValue("cWx6", false);
                    setValue("cYx6", false);
                    cPrefs.write(String.valueOf(x6.getText()), RED);
                    x6.setTextColor(Color.parseColor("#FF0000"));
                    x6.setChecked(false);
                    if (!x6.isChecked()) {
                        setValue(String.valueOf(x6.getText()), true);
                    }
                }

                if (x8.isChecked()) {
                    setValue("cRx8", true);
                    setValue("cPx8", false);
                    setValue("cGx8", false);
                    setValue("cBx8", false);
                    setValue("cWx8", false);
                    setValue("cYx8", false);
                    cPrefs.write(String.valueOf(x8.getText()), RED);
                    x8.setTextColor(Color.parseColor("#FF0000"));
                    x8.setChecked(false);
                    if (!x8.isChecked()) {
                        setValue(String.valueOf(x8.getText()), true);
                    }
                }

                //real
                if (k98.isChecked()) {
                    setValue("cRk98", true);
                    setValue("cPk98", false);
                    setValue("cGk98", false);
                    setValue("cBk98", false);
                    setValue("cWk98", false);
                    setValue("cYk98", false);
                    cPrefs.write(String.valueOf(k98.getText()), RED);
                    k98.setTextColor(Color.parseColor("#FF0000"));
                    k98.setChecked(false);
                    if (!k98.isChecked()) {
                        setValue(String.valueOf(k98.getText()), true);
                    }
                }

                if (akm.isChecked()) {
                    setValue("cRakm", true);
                    setValue("cPakm", false);
                    setValue("cGakm", false);
                    setValue("cBakm", false);
                    setValue("cWakm", false);
                    setValue("cYakm", false);
                    cPrefs.write(String.valueOf(akm.getText()), RED);
                    akm.setTextColor(Color.parseColor("#FF0000"));
                    akm.setChecked(false);
                    if (!akm.isChecked()) {
                        setValue(String.valueOf(akm.getText()), true);
                    }
                }

                if (aug.isChecked()) {
                    setValue("cRaug", true);
                    setValue("cPaug", false);
                    setValue("cGaug", false);
                    setValue("cBaug", false);
                    setValue("cWaug", false);
                    setValue("cYaug", false);
                    cPrefs.write(String.valueOf(aug.getText()), RED);
                    aug.setTextColor(Color.parseColor("#FF0000"));
                    aug.setChecked(false);
                    if (!aug.isChecked()) {
                        setValue(String.valueOf(aug.getText()), true);
                    }
                }

                if (awm.isChecked()) {
                    setValue("cRawm", true);
                    setValue("cPawm", false);
                    setValue("cGawm", false);
                    setValue("cBawm", false);
                    setValue("cWawm", false);
                    setValue("cYawm", false);
                    cPrefs.write(String.valueOf(awm.getText()), RED);
                    awm.setTextColor(Color.parseColor("#FF0000"));
                    awm.setChecked(false);
                    if (!awm.isChecked()) {
                        setValue(String.valueOf(awm.getText()), true);
                    }
                }

                if (bow.isChecked()) {
                    setValue("cRbow", true);
                    setValue("cPbow", false);
                    setValue("cGbow", false);
                    setValue("cBbow", false);
                    setValue("cWbow", false);
                    setValue("cYbow", false);
                    cPrefs.write(String.valueOf(bow.getText()), RED);
                    bow.setTextColor(Color.parseColor("#FF0000"));
                    bow.setChecked(false);
                    if (!bow.isChecked()) {
                        setValue(String.valueOf(bow.getText()), true);
                    }
                }

                if (dp12.isChecked()) {
                    setValue("cRdp12", true);
                    setValue("cPdp12", false);
                    setValue("cGdp12", false);
                    setValue("cBdp12", false);
                    setValue("cWdp12", false);
                    setValue("cYdp12", false);
                    cPrefs.write(String.valueOf(dp12.getText()), RED);
                    dp12.setTextColor(Color.parseColor("#FF0000"));
                    dp12.setChecked(false);
                    if (!dp12.isChecked()) {
                        setValue(String.valueOf(dp12.getText()), true);
                    }
                }

                if (dp28.isChecked()) {
                    setValue("cRdp28", true);
                    setValue("cPdp28", false);
                    setValue("cGdp28", false);
                    setValue("cBdp28", false);
                    setValue("cWdp28", false);
                    setValue("cYdp28", false);
                    cPrefs.write(String.valueOf(dp28.getText()), RED);
                    dp28.setTextColor(Color.parseColor("#FF0000"));
                    dp28.setChecked(false);
                    if (!dp28.isChecked()) {
                        setValue(String.valueOf(dp28.getText()), true);
                    }
                }

                if (deagle.isChecked()) {
                    setValue("cRdeg", true);
                    setValue("cPdeg", false);
                    setValue("cGdeg", false);
                    setValue("cBdeg", false);
                    setValue("cWdeg", false);
                    setValue("cYdeg", false);
                    cPrefs.write(String.valueOf(deagle.getText()), RED);
                    deagle.setTextColor(Color.parseColor("#FF0000"));
                    deagle.setChecked(false);
                    if (!deagle.isChecked()) {
                        setValue(String.valueOf(deagle.getText()), true);
                    }
                }

                if (redDot.isChecked()) {
                    setValue("cRredDot", true);
                    setValue("cPredDot", false);
                    setValue("cGredDot", false);
                    setValue("cBredDot", false);
                    setValue("cWredDot", false);
                    setValue("cYredDot", false);
                    cPrefs.write(String.valueOf(redDot.getText()), RED);
                    redDot.setTextColor(Color.parseColor("#FF0000"));
                    redDot.setChecked(false);
                    if (!redDot.isChecked()) {
                        setValue(String.valueOf(redDot.getText()), true);
                    }
                }

                if (flare.isChecked()) {
                    setValue("cRflare", true);
                    setValue("cPflare", false);
                    setValue("cGflare", false);
                    setValue("cBflare", false);
                    setValue("cWflare", false);
                    setValue("cYflare", false);
                    cPrefs.write(String.valueOf(flare.getText()), RED);
                    flare.setTextColor(Color.parseColor("#FF0000"));
                    flare.setChecked(false);
                    if (!flare.isChecked()) {
                        setValue(String.valueOf(flare.getText()), true);
                    }
                }

                if (g36.isChecked()) {
                    setValue("cRg36", true);
                    setValue("cPg36", false);
                    setValue("cGg36", false);
                    setValue("cBg36", false);
                    setValue("cWg36", false);
                    setValue("cYg36", false);
                    cPrefs.write(String.valueOf(g36.getText()), RED);
                    g36.setTextColor(Color.parseColor("#FF0000"));
                    g36.setChecked(false);
                    if (!g36.isChecked()) {
                        setValue(String.valueOf(g36.getText()), true);
                    }
                }

                if (grenade.isChecked()) {
                    setValue("cRgrenade", true);
                    setValue("cPgrenade", false);
                    setValue("cGgrenade", false);
                    setValue("cBgrenade", false);
                    setValue("cWgrenade", false);
                    setValue("cYgrenade", false);
                    cPrefs.write(String.valueOf(grenade.getText()), RED);
                    grenade.setTextColor(Color.parseColor("#FF0000"));
                    grenade.setChecked(false);
                    if (!grenade.isChecked()) {
                        setValue(String.valueOf(grenade.getText()), true);
                    }
                }

                if (groza.isChecked()) {
                    setValue("cRgroza", true);
                    setValue("cPgroza", false);
                    setValue("cGgroza", false);
                    setValue("cBgroza", false);
                    setValue("cWgroza", false);
                    setValue("cYgroza", false);
                    cPrefs.write(String.valueOf(groza.getText()), RED);
                    groza.setTextColor(Color.parseColor("#FF0000"));
                    groza.setChecked(false);
                    if (!groza.isChecked()) {
                        setValue(String.valueOf(groza.getText()), true);
                    }
                }

                if (hollo.isChecked()) {
                    setValue("cRhollo", true);
                    setValue("cPhollo", false);
                    setValue("cGhollo", false);
                    setValue("cBhollo", false);
                    setValue("cWhollo", false);
                    setValue("cYhollo", false);
                    cPrefs.write(String.valueOf(hollo.getText()), RED);
                    hollo.setTextColor(Color.parseColor("#FF0000"));
                    hollo.setChecked(false);
                    if (!hollo.isChecked()) {
                        setValue(String.valueOf(hollo.getText()), true);
                    }
                }

                if (m1014.isChecked()) {
                    setValue("cRm1014", true);
                    setValue("cPm1014", false);
                    setValue("cGm1014", false);
                    setValue("cBm1014", false);
                    setValue("cWm1014", false);
                    setValue("cYm1014", false);
                    cPrefs.write(String.valueOf(m1014.getText()), RED);
                    m1014.setTextColor(Color.parseColor("#FF0000"));
                    m1014.setChecked(false);
                    if (!m1014.isChecked()) {
                        setValue(String.valueOf(m1014.getText()), true);
                    }
                }

                if (m16.isChecked()) {
                    setValue("cRm16", true);
                    setValue("cPm16", false);
                    setValue("cGm16", false);
                    setValue("cBm16", false);
                    setValue("cWm16", false);
                    setValue("cYm16", false);
                    cPrefs.write(String.valueOf(m16.getText()), RED);
                    m16.setTextColor(Color.parseColor("#FF0000"));
                    m16.setChecked(false);
                    if (!m16.isChecked()) {
                        setValue(String.valueOf(m16.getText()), true);
                    }
                }

                if (m24.isChecked()) {
                    setValue("cRm24", true);
                    setValue("cPm24", false);
                    setValue("cGm24", false);
                    setValue("cBm24", false);
                    setValue("cWm24", false);
                    setValue("cYm24", false);
                    cPrefs.write(String.valueOf(m24.getText()), RED);
                    m24.setTextColor(Color.parseColor("#FF0000"));
                    m24.setChecked(false);
                    if (!m24.isChecked()) {
                        setValue(String.valueOf(m24.getText()), true);
                    }
                }

                if (m249.isChecked()) {
                    setValue("cRm249", true);
                    setValue("cPm249", false);
                    setValue("cGm249", false);
                    setValue("cBm249", false);
                    setValue("cWm249", false);
                    setValue("cYm249", false);
                    cPrefs.write(String.valueOf(m249.getText()), RED);
                    m249.setTextColor(Color.parseColor("#FF0000"));
                    m249.setChecked(false);
                    if (!m249.isChecked()) {
                        setValue(String.valueOf(m249.getText()), true);
                    }
                }

                if (m4.isChecked()) {
                    setValue("cRm4", true);
                    setValue("cPm4", false);
                    setValue("cGm4", false);
                    setValue("cBm4", false);
                    setValue("cWm4", false);
                    setValue("cYm4", false);
                    cPrefs.write(String.valueOf(m4.getText()), RED);
                    m4.setTextColor(Color.parseColor("#FF0000"));
                    m4.setChecked(false);
                    if (!m4.isChecked()) {
                        setValue(String.valueOf(m4.getText()), true);
                    }
                }

                if (m762g.isChecked()) {
                    setValue("cRm762", true);
                    setValue("cPm762", false);
                    setValue("cGm762", false);
                    setValue("cBm762", false);
                    setValue("cWm762", false);
                    setValue("cYm762", false);
                    cPrefs.write(String.valueOf(m762g.getText()), RED);
                    m762g.setTextColor(Color.parseColor("#FF0000"));
                    m762g.setChecked(false);
                    if (!m762g.isChecked()) {
                        setValue(String.valueOf(m762g.getText()), true);
                    }
                }

                if (mk14.isChecked()) {
                    setValue("cRmk14", true);
                    setValue("cPmk14", false);
                    setValue("cGmk14", false);
                    setValue("cBmk14", false);
                    setValue("cWmk14", false);
                    setValue("cYmk14", false);
                    cPrefs.write(String.valueOf(mk14.getText()), RED);
                    mk14.setTextColor(Color.parseColor("#FF0000"));
                    mk14.setChecked(false);
                    if (!mk14.isChecked()) {
                        setValue(String.valueOf(mk14.getText()), true);
                    }
                }

                if (mk47.isChecked()) {
                    setValue("cRmk47", true);
                    setValue("cPmk47", false);
                    setValue("cGmk47", false);
                    setValue("cBmk47", false);
                    setValue("cWmk47", false);
                    setValue("cYmk47", false);
                    cPrefs.write(String.valueOf(mk47.getText()), RED);
                    mk47.setTextColor(Color.parseColor("#FF0000"));
                    mk47.setChecked(false);
                    if (!mk47.isChecked()) {
                        setValue(String.valueOf(mk47.getText()), true);
                    }
                }

                if (mp5k.isChecked()) {
                    setValue("cRmp5k", true);
                    setValue("cPmp5k", false);
                    setValue("cGmp5k", false);
                    setValue("cBmp5k", false);
                    setValue("cWmp5k", false);
                    setValue("cYmp5k", false);
                    cPrefs.write(String.valueOf(mp5k.getText()), RED);
                    mp5k.setTextColor(Color.parseColor("#FF0000"));
                    mp5k.setChecked(false);
                    if (!mp5k.isChecked()) {
                        setValue(String.valueOf(mp5k.getText()), true);
                    }
                }

                if (mini14.isChecked()) {
                    setValue("cRmini14", true);
                    setValue("cPmini14", false);
                    setValue("cGmini14", false);
                    setValue("cBmini14", false);
                    setValue("cWmini14", false);
                    setValue("cYmini14", false);
                    cPrefs.write(String.valueOf(mini14.getText()), RED);
                    mini14.setTextColor(Color.parseColor("#FF0000"));
                    mini14.setChecked(false);
                    if (!mini14.isChecked()) {
                        setValue(String.valueOf(mini14.getText()), true);
                    }
                }

                if (molotov.isChecked()) {
                    setValue("cRburn", true);
                    setValue("cPburn", false);
                    setValue("cGburn", false);
                    setValue("cBburn", false);
                    setValue("cWburn", false);
                    setValue("cYburn", false);
                    cPrefs.write(String.valueOf(molotov.getText()), RED);
                    molotov.setTextColor(Color.parseColor("#FF0000"));
                    molotov.setChecked(false);
                    if (!molotov.isChecked()) {
                        setValue(String.valueOf(molotov.getText()), true);
                    }
                }

                if (pp19.isChecked()) {
                    setValue("cRpp19", true);
                    setValue("cPpp19", false);
                    setValue("cGpp19", false);
                    setValue("cBpp19", false);
                    setValue("cWpp19", false);
                    setValue("cYpp19", false);
                    cPrefs.write(String.valueOf(pp19.getText()), RED);
                    pp19.setTextColor(Color.parseColor("#FF0000"));
                    pp19.setChecked(false);
                    if (!pp19.isChecked()) {
                        setValue(String.valueOf(pp19.getText()), true);
                    }
                }

                if (pan.isChecked()) {
                    setValue("cRpan", true);
                    setValue("cPpan", false);
                    setValue("cGpan", false);
                    setValue("cBpan", false);
                    setValue("cWpan", false);
                    setValue("cYpan", false);
                    cPrefs.write(String.valueOf(pan.getText()), RED);
                    pan.setTextColor(Color.parseColor("#FF0000"));
                    pan.setChecked(false);
                    if (!pan.isChecked()) {
                        setValue(String.valueOf(pan.getText()), true);
                    }
                }

                if (qbu.isChecked()) {
                    setValue("cRqbu", true);
                    setValue("cPqbu", false);
                    setValue("cGqbu", false);
                    setValue("cBqbu", false);
                    setValue("cWqbu", false);
                    setValue("cYqbu", false);
                    cPrefs.write(String.valueOf(qbu.getText()), RED);
                    qbu.setTextColor(Color.parseColor("#FF0000"));
                    qbu.setChecked(false);
                    if (!qbu.isChecked()) {
                        setValue(String.valueOf(qbu.getText()), true);
                    }
                }

                if (qbz.isChecked()) {
                    setValue("cRqbz", true);
                    setValue("cPqbz", false);
                    setValue("cGqbz", false);
                    setValue("cBqbz", false);
                    setValue("cWqbz", false);
                    setValue("cYqbz", false);
                    cPrefs.write(String.valueOf(qbz.getText()), RED);
                    qbz.setTextColor(Color.parseColor("#FF0000"));
                    qbz.setChecked(false);
                    if (!qbz.isChecked()) {
                        setValue(String.valueOf(qbz.getText()), true);
                    }
                }

                if (s12k.isChecked()) {
                    setValue("cRs12k", true);
                    setValue("cPs12k", false);
                    setValue("cGs12k", false);
                    setValue("cBs12k", false);
                    setValue("cWs12k", false);
                    setValue("cYs12k", false);
                    cPrefs.write(String.valueOf(s12k.getText()), RED);
                    s12k.setTextColor(Color.parseColor("#FF0000"));
                    s12k.setChecked(false);
                    if (!s12k.isChecked()) {
                        setValue(String.valueOf(s12k.getText()), true);
                    }
                }

                if (s1897.isChecked()) {
                    setValue("cRs1897", true);
                    setValue("cPs1897", false);
                    setValue("cGs1897", false);
                    setValue("cBs1897", false);
                    setValue("cWs1897", false);
                    setValue("cYs1897", false);
                    cPrefs.write(String.valueOf(s1897.getText()), RED);
                    s1897.setTextColor(Color.parseColor("#FF0000"));
                    s1897.setChecked(false);
                    if (!s1897.isChecked()) {
                        setValue(String.valueOf(s1897.getText()), true);
                    }
                }

                if (s686.isChecked()) {
                    setValue("cRs686", true);
                    setValue("cPs686", false);
                    setValue("cGs686", false);
                    setValue("cBs686", false);
                    setValue("cWs686", false);
                    setValue("cYs686", false);
                    cPrefs.write(String.valueOf(s686.getText()), RED);
                    s686.setTextColor(Color.parseColor("#FF0000"));
                    s686.setChecked(false);
                    if (!s686.isChecked()) {
                        setValue(String.valueOf(s686.getText()), true);
                    }
                }

                if (scarl.isChecked()) {
                    setValue("cRscar", true);
                    setValue("cPscar", false);
                    setValue("cGscar", false);
                    setValue("cBscar", false);
                    setValue("cWscar", false);
                    setValue("cYscar", false);
                    cPrefs.write(String.valueOf(scarl.getText()), RED);
                    scarl.setTextColor(Color.parseColor("#FF0000"));
                    scarl.setChecked(false);
                    if (!scarl.isChecked()) {
                        setValue(String.valueOf(scarl.getText()), true);
                    }
                }

                if (sks.isChecked()) {
                    setValue("cRsks", true);
                    setValue("cPsks", false);
                    setValue("cGsks", false);
                    setValue("cBsks", false);
                    setValue("cWsks", false);
                    setValue("cYsks", false);
                    cPrefs.write(String.valueOf(sks.getText()), RED);
                    sks.setTextColor(Color.parseColor("#FF0000"));
                    sks.setChecked(false);
                    if (!sks.isChecked()) {
                        setValue(String.valueOf(sks.getText()), true);
                    }
                }

                if (slr.isChecked()) {
                    setValue("cRslr", true);
                    setValue("cPslr", false);
                    setValue("cGslr", false);
                    setValue("cBslr", false);
                    setValue("cWslr", false);
                    setValue("cYslr", false);
                    cPrefs.write(String.valueOf(slr.getText()), RED);
                    slr.setTextColor(Color.parseColor("#FF0000"));
                    slr.setChecked(false);
                    if (!slr.isChecked()) {
                        setValue(String.valueOf(slr.getText()), true);
                    }
                }

                if (stunt.isChecked()) {
                    setValue("cRflash", true);
                    setValue("cPflash", false);
                    setValue("cGflash", false);
                    setValue("cBflash", false);
                    setValue("cWflash", false);
                    setValue("cYflash", false);
                    cPrefs.write(String.valueOf(stunt.getText()), RED);
                    stunt.setTextColor(Color.parseColor("#FF0000"));
                    stunt.setChecked(false);
                    if (!stunt.isChecked()) {
                        setValue(String.valueOf(stunt.getText()), true);
                    }
                }

                if (tommy.isChecked()) {
                    setValue("cRthomp", true);
                    setValue("cPthomp", false);
                    setValue("cGthomp", false);
                    setValue("cBthomp", false);
                    setValue("cWthomp", false);
                    setValue("cYthomp", false);
                    cPrefs.write(String.valueOf(tommy.getText()), RED);
                    tommy.setTextColor(Color.parseColor("#FF0000"));
                    tommy.setChecked(false);
                    if (!tommy.isChecked()) {
                        setValue(String.valueOf(tommy.getText()), true);
                    }
                }

                if (ump45.isChecked()) {
                    setValue("cRump45", true);
                    setValue("cPump45", false);
                    setValue("cGump45", false);
                    setValue("cBump45", false);
                    setValue("cWump45", false);
                    setValue("cYump45", false);
                    cPrefs.write(String.valueOf(ump45.getText()), RED);
                    ump45.setTextColor(Color.parseColor("#FF0000"));
                    ump45.setChecked(false);
                    if (!ump45.isChecked()) {
                        setValue(String.valueOf(ump45.getText()), true);
                    }
                }

                if (uzi.isChecked()) {
                    setValue("cRuzi", true);
                    setValue("cPuzi", false);
                    setValue("cGuzi", false);
                    setValue("cBuzi", false);
                    setValue("cWuzi", false);
                    setValue("cYuzi", false);
                    cPrefs.write(String.valueOf(uzi.getText()), RED);
                    uzi.setTextColor(Color.parseColor("#FF0000"));
                    uzi.setChecked(false);
                    if (!uzi.isChecked()) {
                        setValue(String.valueOf(uzi.getText()), true);
                    }
                }

                if (vss.isChecked()) {
                    setValue("cRvss", true);
                    setValue("cPvss", false);
                    setValue("cGvss", false);
                    setValue("cBvss", false);
                    setValue("cWvss", false);
                    setValue("cYvss", false);
                    cPrefs.write(String.valueOf(vss.getText()), RED);
                    vss.setTextColor(Color.parseColor("#FF0000"));
                    vss.setChecked(false);
                    if (!vss.isChecked()) {
                        setValue(String.valueOf(vss.getText()), true);
                    }
                }

                if (vector.isChecked()) {
                    setValue("cRvect", true);
                    setValue("cPvect", false);
                    setValue("cGvect", false);
                    setValue("cBvect", false);
                    setValue("cWvect", false);
                    setValue("cYvect", false);
                    cPrefs.write(String.valueOf(vector.getText()), RED);
                    vector.setTextColor(Color.parseColor("#FF0000"));
                    vector.setChecked(false);
                    if (!vector.isChecked()) {
                        setValue(String.valueOf(vector.getText()), true);
                    }
                }

                if (win94.isChecked()) {
                    setValue("cRwinchester", true);
                    setValue("cPwinchester", false);
                    setValue("cGwinchester", false);
                    setValue("cBwinchester", false);
                    setValue("cWwinchester", false);
                    setValue("cYwinchester", false);
                    cPrefs.write(String.valueOf(win94.getText()), RED);
                    win94.setTextColor(Color.parseColor("#FF0000"));
                    win94.setChecked(false);
                    if (!win94.isChecked()) {
                        setValue(String.valueOf(win94.getText()), true);
                    }
                }
            }
        }); //ItemColorRed

        clrPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dacia.isChecked()) {
                    setValue("clrRedDacia", false);
                    setValue("clrPinkDacia", true);
                    setValue("clrGreenDacia", false);
                    setValue("clrBlueDacia", false);
                    setValue("clrWhiteDacia", false);
                    setValue("clrYellowDacia", false);
                    cPrefs.write(String.valueOf(Dacia.getText()), PINK);
                    Dacia.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Dacia.setChecked(false);
                    if (!Dacia.isChecked()) {
                        setValue(String.valueOf(Dacia.getText()), true);
                    }
                }
                if (UAZ.isChecked()) {
                    setValue("clrRedUAZ", false);
                    setValue("clrPinkUAZ", true);
                    setValue("clrGreenUAZ", false);
                    setValue("clrBlueUAZ", false);
                    setValue("clrWhiteUAZ", false);
                    setValue("clrYellowUAZ", false);
                    cPrefs.write(String.valueOf(UAZ.getText()), PINK);
                    UAZ.setTextColor(Color.parseColor("#FFFFBEDC"));
                    UAZ.setChecked(false);
                    if (!UAZ.isChecked()) {
                        setValue(String.valueOf(UAZ.getText()), true);
                    }
                }
                if (Buggy.isChecked()) {
                    setValue("clrRedBuggy", false);
                    setValue("clrPinkBuggy", true);
                    setValue("clrGreenBuggy", false);
                    setValue("clrBlueBuggy", false);
                    setValue("clrWhiteBuggy", false);
                    setValue("clrYellowBuggy", false);
                    cPrefs.write(String.valueOf(Buggy.getText()), PINK);
                    Buggy.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Buggy.setChecked(false);
                    if (!Buggy.isChecked()) {
                        setValue(String.valueOf(Buggy.getText()), true);
                    }
                }
                if (Bike.isChecked()) {
                    setValue("clrRedBike", false);
                    setValue("clrPinkBike", true);
                    setValue("clrGreenBike", false);
                    setValue("clrBlueBike", false);
                    setValue("clrWhiteBike", false);
                    setValue("clrYellowBike", false);
                    cPrefs.write(String.valueOf(Bike.getText()), PINK);
                    Bike.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Bike.setChecked(false);
                    if (!Bike.isChecked()) {
                        setValue(String.valueOf(Bike.getText()), true);
                    }
                }
                if (Jet.isChecked()) {
                    setValue("clrRedAquaRail", false);
                    setValue("clrPinkAquaRail", true);
                    setValue("clrGreenAquaRail", false);
                    setValue("clrBlueAquaRail", false);
                    setValue("clrWhiteAquaRail", false);
                    setValue("clrYellowAquaRail", false);
                    cPrefs.write(String.valueOf(Jet.getText()), PINK);
                    Jet.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Jet.setChecked(false);
                    if (!Jet.isChecked()) {
                        setValue(String.valueOf(Jet.getText()), true);
                    }
                }
                if (Boat.isChecked()) {
                    setValue("clrRedBoat", false);
                    setValue("clrPinkBoat", true);
                    setValue("clrGreenBoat", false);
                    setValue("clrBlueBoat", false);
                    setValue("clrWhiteBoat", false);
                    setValue("clrYellowBoat", false);
                    cPrefs.write(String.valueOf(Boat.getText()), PINK);
                    Boat.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Boat.setChecked(false);
                    if (!Boat.isChecked()) {
                        setValue(String.valueOf(Boat.getText()), true);
                    }
                }
                if (Bus.isChecked()) {
                    setValue("clrRedBus", false);
                    setValue("clrPinkBus", true);
                    setValue("clrGreenBus", false);
                    setValue("clrBlueBus", false);
                    setValue("clrWhiteBus", false);
                    setValue("clrYellowBus", false);
                    cPrefs.write(String.valueOf(Bus.getText()), PINK);
                    Bus.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Bus.setChecked(false);
                    if (!Bus.isChecked()) {
                        setValue(String.valueOf(Bus.getText()), true);
                    }
                }
                if (Mirado.isChecked()) {
                    setValue("clrRedMirado", false);
                    setValue("clrPinkMirado", true);
                    setValue("clrGreenMirado", false);
                    setValue("clrBlueMirado", false);
                    setValue("clrWhiteMirado", false);
                    setValue("clrYellowMirado", false);
                    cPrefs.write(String.valueOf(Mirado.getText()), PINK);
                    Mirado.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Mirado.setChecked(false);
                    if (!Mirado.isChecked()) {
                        setValue(String.valueOf(Mirado.getText()), true);
                    }
                }
                if (Scooter.isChecked()) {
                    setValue("clrRedScooter", false);
                    setValue("clrPinkScooter", true);
                    setValue("clrGreenScooter", false);
                    setValue("clrBlueScooter", false);
                    setValue("clrWhiteScooter", false);
                    setValue("clrYellowScooter", false);
                    cPrefs.write(String.valueOf(Scooter.getText()), PINK);
                    Scooter.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Scooter.setChecked(false);
                    if (!Scooter.isChecked()) {
                        setValue(String.valueOf(Scooter.getText()), true);
                    }
                }
                if (Rony.isChecked()) {
                    setValue("clrRedRony", false);
                    setValue("clrPinkRony", true);
                    setValue("clrGreenRony", false);
                    setValue("clrBlueRony", false);
                    setValue("clrWhiteRony", false);
                    setValue("clrYellowRony", false);
                    cPrefs.write(String.valueOf(Rony.getText()), PINK);
                    Rony.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Rony.setChecked(false);
                    if (!Rony.isChecked()) {
                        setValue(String.valueOf(Rony.getText()), true);
                    }
                }
                if (Snowbike.isChecked()) {
                    setValue("clrRedSnowbike", false);
                    setValue("clrPinkSnowbike", true);
                    setValue("clrGreenSnowbike", false);
                    setValue("clrBlueSnowbike", false);
                    setValue("clrWhiteSnowbike", false);
                    setValue("clrYellowSnowbike", false);
                    cPrefs.write(String.valueOf(Snowbike.getText()), PINK);
                    Snowbike.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Snowbike.setChecked(false);
                    if (!Snowbike.isChecked()) {
                        setValue(String.valueOf(Snowbike.getText()), true);
                    }
                }
                if (Snowmobile.isChecked()) {
                    setValue("clrRedSnowmobile", false);
                    setValue("clrPinkSnowmobile", true);
                    setValue("clrGreenSnowmobile", false);
                    setValue("clrBlueSnowmobile", false);
                    setValue("clrWhiteSnowmobile", false);
                    setValue("clrYellowSnowmobile", false);
                    cPrefs.write(String.valueOf(Snowmobile.getText()), PINK);
                    Snowmobile.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Snowmobile.setChecked(false);
                    if (!Snowmobile.isChecked()) {
                        setValue(String.valueOf(Snowmobile.getText()), true);
                    }
                }
                if (Tempo.isChecked()) {
                    setValue("clrRedTuk", false);
                    setValue("clrPinkTuk", true);
                    setValue("clrGreenTuk", false);
                    setValue("clrBlueTuk", false);
                    setValue("clrWhiteTuk", false);
                    setValue("clrYellowTuk", false);
                    cPrefs.write(String.valueOf(Tempo.getText()), PINK);
                    Tempo.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Tempo.setChecked(false);
                    if (!Tempo.isChecked()) {
                        setValue(String.valueOf(Tempo.getText()), true);
                    }
                }
                if (Truck.isChecked()) {
                    setValue("clrRedTruck", false);
                    setValue("clrPinkTruck", true);
                    setValue("clrGreenTruck", false);
                    setValue("clrBlueTruck", false);
                    setValue("clrWhiteTruck", false);
                    setValue("clrYellowTruck", false);
                    cPrefs.write(String.valueOf(Truck.getText()), PINK);
                    Truck.setTextColor(Color.parseColor("#FFFFBEDC"));
                    Truck.setChecked(false);
                    if (!Truck.isChecked()) {
                        setValue(String.valueOf(Truck.getText()), true);
                    }
                }
                if (BRDM.isChecked()) {
                    setValue("clrRedBRDM", false);
                    setValue("clrPinkBRDM", true);
                    setValue("clrGreenBRDM", false);
                    setValue("clrBlueBRDM", false);
                    setValue("clrWhiteBRDM", false);
                    setValue("clrYellowBRDM", false);
                    cPrefs.write(String.valueOf(BRDM.getText()), PINK);
                    BRDM.setTextColor(Color.parseColor("#FFFFBEDC"));
                    BRDM.setChecked(false);
                    if (!BRDM.isChecked()) {
                        setValue(String.valueOf(BRDM.getText()), true);
                    }
                }
                if (LadaNiva.isChecked()) {
                    setValue("clrRedLadaNiva", false);
                    setValue("clrPinkLadaNiva", true);
                    setValue("clrGreenLadaNiva", false);
                    setValue("clrBlueLadaNiva", false);
                    setValue("clrWhiteLadaNiva", false);
                    setValue("clrYellowLadaNiva", false);
                    cPrefs.write(String.valueOf(LadaNiva.getText()), PINK);
                    LadaNiva.setTextColor(Color.parseColor("#FFFFBEDC"));
                    LadaNiva.setChecked(false);
                    if (!LadaNiva.isChecked()) {
                        setValue(String.valueOf(LadaNiva.getText()), true);
                    }
                }
                if (MonsterTruck.isChecked()) {
                    setValue("clrRedMonster", false);
                    setValue("clrPinkMonster", true);
                    setValue("clrGreenMonster", false);
                    setValue("clrBlueMonster", false);
                    setValue("clrWhiteMonster", false);
                    setValue("clrYellowMonster", false);
                    cPrefs.write(String.valueOf(MonsterTruck.getText()), PINK);
                    MonsterTruck.setTextColor(Color.parseColor("#FFFFBEDC"));
                    MonsterTruck.setChecked(false);
                    if (!MonsterTruck.isChecked()) {
                        setValue(String.valueOf(MonsterTruck.getText()), true);
                    }
                }
                if (CoupeRB.isChecked()) {
                    setValue("clrRedCoupeRB", false);
                    setValue("clrPinkCoupeRB", true);
                    setValue("clrGreenCoupeRB", false);
                    setValue("clrBlueCoupeRB", false);
                    setValue("clrWhiteCoupeRB", false);
                    setValue("clrYellowCoupeRB", false);
                    cPrefs.write(String.valueOf(CoupeRB.getText()), PINK);
                    CoupeRB.setTextColor(Color.parseColor("#FFFFBEDC"));
                    CoupeRB.setChecked(false);
                    if (!CoupeRB.isChecked()) {
                        setValue(String.valueOf(CoupeRB.getText()), true);
                    }
                }
                if (UTV.isChecked()) {
                    setValue("clrRedUTV", false);
                    setValue("clrPinkUTV", true);
                    setValue("clrGreenUTV", false);
                    setValue("clrBlueUTV", false);
                    setValue("clrWhiteUTV", false);
                    setValue("clrYellowUTV", false);
                    cPrefs.write(String.valueOf(UTV.getText()), PINK);
                    UTV.setTextColor(Color.parseColor("#FFFFBEDC"));
                    UTV.setChecked(false);
                    if (!UTV.isChecked()) {
                        setValue(String.valueOf(UTV.getText()), true);
                    }
                }
                if (am300.isChecked()) {
                    setValue("cR300mm", false);
                    setValue("cP300mm", true);
                    setValue("cG300mm", false);
                    setValue("cB300mm", false);
                    setValue("cW300mm", false);
                    setValue("cY300mm", false);
                    cPrefs.write(String.valueOf(am300.getText()), PINK);
                    am300.setTextColor(Color.parseColor("#FFFFBEDC"));
                    am300.setChecked(false);
                    if (!am300.isChecked()) {
                        setValue(String.valueOf(am300.getText()), true);
                    }
                }

                if (am45.isChecked()) {
                    setValue("cRam45", false);
                    setValue("cPam45", true);
                    setValue("cGam45", false);
                    setValue("cBam45", false);
                    setValue("cWam45", false);
                    setValue("cYam45", false);
                    cPrefs.write(String.valueOf(am45.getText()), PINK);
                    am45.setTextColor(Color.parseColor("#FFFFBEDC"));
                    am45.setChecked(false);
                    if (!am45.isChecked()) {
                        setValue(String.valueOf(am45.getText()), true);
                    }
                }

                if (am12g.isChecked()) {
                    setValue("cRam12g", false);
                    setValue("cPam12g", true);
                    setValue("cGam12g", false);
                    setValue("cBam12g", false);
                    setValue("cWam12g", false);
                    setValue("cYam12g", false);
                    cPrefs.write(String.valueOf(am12g.getText()), PINK);
                    am12g.setTextColor(Color.parseColor("#FFFFBEDC"));
                    am12g.setChecked(false);
                    if (!am12g.isChecked()) {
                        setValue(String.valueOf(am12g.getText()), true);
                    }
                }

                if (am556.isChecked()) {
                    setValue("cRam556", false);
                    setValue("cPam556", true);
                    setValue("cGam556", false);
                    setValue("cBam556", false);
                    setValue("cWam556", false);
                    setValue("cYam556", false);
                    cPrefs.write(String.valueOf(am556.getText()), PINK);
                    am556.setTextColor(Color.parseColor("#FFFFBEDC"));
                    am556.setChecked(false);
                    if (!am556.isChecked()) {
                        setValue(String.valueOf(am556.getText()), true);
                    }
                }

                if (am762.isChecked()) {
                    setValue("cRam762", false);
                    setValue("cPam762", true);
                    setValue("cGam762", false);
                    setValue("cBam762", false);
                    setValue("cWam762", false);
                    setValue("cYam762", false);
                    cPrefs.write(String.valueOf(am762.getText()), PINK);
                    am762.setTextColor(Color.parseColor("#FFFFBEDC"));
                    am762.setChecked(false);
                    if (!am762.isChecked()) {
                        setValue(String.valueOf(am762.getText()), true);
                    }
                }

                if (am9mm.isChecked()) {
                    setValue("cRam9mm", false);
                    setValue("cPam9mm", true);
                    setValue("cGam9mm", false);
                    setValue("cBam9mm", false);
                    setValue("cWam9mm", false);
                    setValue("cYam9mm", false);
                    cPrefs.write(String.valueOf(am9mm.getText()), PINK);
                    am9mm.setTextColor(Color.parseColor("#FFFFBEDC"));
                    am9mm.setChecked(false);
                    if (!am9mm.isChecked()) {
                        setValue(String.valueOf(am9mm.getText()), true);
                    }
                }

                if (armorLvl2.isChecked()) {
                    setValue("cRar2", false);
                    setValue("cPar2", true);
                    setValue("cGar2", false);
                    setValue("cBar2", false);
                    setValue("cWar2", false);
                    setValue("cYar2", false);
                    cPrefs.write(String.valueOf(armorLvl2.getText()), PINK);
                    armorLvl2.setTextColor(Color.parseColor("#FFFFBEDC"));
                    armorLvl2.setChecked(false);
                    if (!armorLvl2.isChecked()) {
                        setValue(String.valueOf(armorLvl2.getText()), true);
                    }
                }

                if (armorLvl3.isChecked()) {
                    setValue("cRar3", false);
                    setValue("cPar3", true);
                    setValue("cGar3", false);
                    setValue("cBar3", false);
                    setValue("cWar3", false);
                    setValue("cYar3", false);
                    cPrefs.write(String.valueOf(armorLvl3.getText()), PINK);
                    armorLvl3.setTextColor(Color.parseColor("#FFFFBEDC"));
                    armorLvl3.setChecked(false);
                    if (!armorLvl3.isChecked()) {
                        setValue(String.valueOf(armorLvl3.getText()), true);
                    }
                }

                if (helmetLvl2.isChecked()) {
                    setValue("cRhel2", false);
                    setValue("cPhel2", true);
                    setValue("cGhel2", false);
                    setValue("cBhel2", false);
                    setValue("cWhel2", false);
                    setValue("cYhel2", false);
                    cPrefs.write(String.valueOf(helmetLvl2.getText()), PINK);
                    helmetLvl2.setTextColor(Color.parseColor("#FFFFBEDC"));
                    helmetLvl2.setChecked(false);
                    if (!helmetLvl2.isChecked()) {
                        setValue(String.valueOf(helmetLvl2.getText()), true);
                    }
                }

                if (helmetLvl3.isChecked()) {
                    setValue("cRhel3", false);
                    setValue("cPhel3", true);
                    setValue("cGhel3", false);
                    setValue("cBhel3", false);
                    setValue("cWhel3", false);
                    setValue("cYhel3", false);
                    cPrefs.write(String.valueOf(helmetLvl3.getText()), PINK);
                    helmetLvl3.setTextColor(Color.parseColor("#FFFFBEDC"));
                    helmetLvl3.setChecked(false);
                    if (!helmetLvl3.isChecked()) {
                        setValue(String.valueOf(helmetLvl3.getText()), true);
                    }
                }

                if (bandage.isChecked()) {
                    setValue("cRban", false);
                    setValue("cPban", true);
                    setValue("cGban", false);
                    setValue("cBban", false);
                    setValue("cWban", false);
                    setValue("cYban", false);
                    cPrefs.write(String.valueOf(bandage.getText()), PINK);
                    bandage.setTextColor(Color.parseColor("#FFFFBEDC"));
                    bandage.setChecked(false);
                    if (!bandage.isChecked()) {
                        setValue(String.valueOf(bandage.getText()), true);
                    }
                }

                if (drink.isChecked()) {
                    setValue("cRdrink", false);
                    setValue("cPdrink", true);
                    setValue("cGdrink", false);
                    setValue("cBdrink", false);
                    setValue("cWdrink", false);
                    setValue("cYdrink", false);
                    cPrefs.write(String.valueOf(drink.getText()), PINK);
                    drink.setTextColor(Color.parseColor("#FFFFBEDC"));
                    drink.setChecked(false);
                    if (!drink.isChecked()) {
                        setValue(String.valueOf(drink.getText()), true);
                    }
                }

                if (firstAir.isChecked()) {
                    setValue("cRfa", false);
                    setValue("cPfa", true);
                    setValue("cGfa", false);
                    setValue("cBfa", false);
                    setValue("cWfa", false);
                    setValue("cYfa", false);
                    cPrefs.write(String.valueOf(firstAir.getText()), PINK);
                    firstAir.setTextColor(Color.parseColor("#FFFFBEDC"));
                    firstAir.setChecked(false);
                    if (!firstAir.isChecked()) {
                        setValue(String.valueOf(firstAir.getText()), true);
                    }
                }

                if (injection.isChecked()) {
                    setValue("cRin", false);
                    setValue("cPin", true);
                    setValue("cGin", false);
                    setValue("cBin", false);
                    setValue("cWin", false);
                    setValue("cYin", false);
                    cPrefs.write(String.valueOf(injection.getText()), PINK);
                    injection.setTextColor(Color.parseColor("#FFFFBEDC"));
                    injection.setChecked(false);
                    if (!injection.isChecked()) {
                        setValue(String.valueOf(injection.getText()), true);
                    }
                }

                if (pills.isChecked()) {
                    setValue("cRpills", false);
                    setValue("cPpills", true);
                    setValue("cGpills", false);
                    setValue("cBpills", false);
                    setValue("cWpills", false);
                    setValue("cYpills", false);
                    cPrefs.write(String.valueOf(pills.getText()), PINK);
                    pills.setTextColor(Color.parseColor("#FFFFBEDC"));
                    pills.setChecked(false);
                    if (!pills.isChecked()) {
                        setValue(String.valueOf(pills.getText()), true);
                    }
                }

                //Misk
                if (airCraft.isChecked()) {
                    setValue("cRac", false);
                    setValue("cPac", true);
                    setValue("cGac", false);
                    setValue("cBac", false);
                    setValue("cWac", false);
                    setValue("cYac", false);
                    cPrefs.write(String.valueOf(airCraft.getText()), PINK);
                    airCraft.setTextColor(Color.parseColor("#FFFFBEDC"));
                    airCraft.setChecked(false);
                    if (!airCraft.isChecked()) {
                        setValue(String.valueOf(airCraft.getText()), true);
                    }
                }

                if (airDrop.isChecked()) {
                    setValue("cRairD", false);
                    setValue("cPairD", true);
                    setValue("cGairD", false);
                    setValue("cBairD", false);
                    setValue("cWairD", false);
                    setValue("cYairD", false);
                    cPrefs.write(String.valueOf(airDrop.getText()), PINK);
                    airDrop.setTextColor(Color.parseColor("#FFFFBEDC"));
                    airDrop.setChecked(false);
                    if (!airDrop.isChecked()) {
                        setValue(String.valueOf(airDrop.getText()), true);
                    }
                }

                if (bag2.isChecked()) {
                    setValue("cRb2", false);
                    setValue("cPb2", true);
                    setValue("cGb2", false);
                    setValue("cBb2", false);
                    setValue("cWb2", false);
                    setValue("cYb2", false);
                    cPrefs.write(String.valueOf(bag2.getText()), PINK);
                    bag2.setTextColor(Color.parseColor("#FFFFBEDC"));
                    bag2.setChecked(false);
                    if (!bag2.isChecked()) {
                        setValue(String.valueOf(bag2.getText()), true);
                    }
                }

                if (bag3.isChecked()) {
                    setValue("cRb3", false);
                    setValue("cPb3", true);
                    setValue("cGb3", false);
                    setValue("cBb3", false);
                    setValue("cWb3", false);
                    setValue("cYb3", false);
                    cPrefs.write(String.valueOf(bag3.getText()), PINK);
                    bag3.setTextColor(Color.parseColor("#FFFFBEDC"));
                    bag3.setChecked(false);
                    if (!bag3.isChecked()) {
                        setValue(String.valueOf(bag3.getText()), true);
                    }
                }

                if (compensator.isChecked()) {
                    setValue("cRcomp", false);
                    setValue("cPcomp", true);
                    setValue("cGcomp", false);
                    setValue("cBcomp", false);
                    setValue("cWcomp", false);
                    setValue("cYcomp", false);
                    cPrefs.write(String.valueOf(compensator.getText()), PINK);
                    compensator.setTextColor(Color.parseColor("#FFFFBEDC"));
                    compensator.setChecked(false);
                    if (!compensator.isChecked()) {
                        setValue(String.valueOf(compensator.getText()), true);
                    }
                }

                if (loot.isChecked()) {
                    setValue("cRloot", false);
                    setValue("cPloot", true);
                    setValue("cGloot", false);
                    setValue("cBloot", false);
                    setValue("cWloot", false);
                    setValue("cYloot", false);
                    cPrefs.write(String.valueOf(loot.getText()), PINK);
                    loot.setTextColor(Color.parseColor("#FFFFBEDC"));
                    loot.setChecked(false);
                    if (!loot.isChecked()) {
                        setValue(String.valueOf(loot.getText()), true);
                    }
                }

                if (supressor.isChecked()) {
                    setValue("cRsupp", false);
                    setValue("cPsupp", true);
                    setValue("cGsupp", false);
                    setValue("cBsupp", false);
                    setValue("cWsupp", false);
                    setValue("cYsupp", false);
                    cPrefs.write(String.valueOf(supressor.getText()), PINK);
                    supressor.setTextColor(Color.parseColor("#FFFFBEDC"));
                    supressor.setChecked(false);
                    if (!supressor.isChecked()) {
                        setValue(String.valueOf(supressor.getText()), true);
                    }
                }

                //Weapons
                if (x2.isChecked()) {
                    setValue("cRx2", false);
                    setValue("cPx2", true);
                    setValue("cGx2", false);
                    setValue("cBx2", false);
                    setValue("cWx2", false);
                    setValue("cYx2", false);
                    cPrefs.write(String.valueOf(x2.getText()), PINK);
                    x2.setTextColor(Color.parseColor("#FFFFBEDC"));
                    x2.setChecked(false);
                    if (!x2.isChecked()) {
                        setValue(String.valueOf(x2.getText()), true);
                    }
                }

                if (x3.isChecked()) {
                    setValue("cRx3", false);
                    setValue("cPx3", true);
                    setValue("cGx3", false);
                    setValue("cBx3", false);
                    setValue("cWx3", false);
                    setValue("cYx3", false);
                    cPrefs.write(String.valueOf(x3.getText()), PINK);
                    x3.setTextColor(Color.parseColor("#FFFFBEDC"));
                    x3.setChecked(false);
                    if (!x3.isChecked()) {
                        setValue(String.valueOf(x3.getText()), true);
                    }
                }

                if (x4.isChecked()) {
                    setValue("cRx4", false);
                    setValue("cPx4", true);
                    setValue("cGx4", false);
                    setValue("cBx4", false);
                    setValue("cWx4", false);
                    setValue("cYx4", false);
                    cPrefs.write(String.valueOf(x4.getText()), PINK);
                    x4.setTextColor(Color.parseColor("#FFFFBEDC"));
                    x4.setChecked(false);
                    if (!x4.isChecked()) {
                        setValue(String.valueOf(x4.getText()), true);
                    }
                }

                if (x6.isChecked()) {
                    setValue("cRx6", false);
                    setValue("cPx6", true);
                    setValue("cGx6", false);
                    setValue("cBx6", false);
                    setValue("cWx6", false);
                    setValue("cYx6", false);
                    cPrefs.write(String.valueOf(x6.getText()), PINK);
                    x6.setTextColor(Color.parseColor("#FFFFBEDC"));
                    x6.setChecked(false);
                    if (!x6.isChecked()) {
                        setValue(String.valueOf(x6.getText()), true);
                    }
                }

                if (x8.isChecked()) {
                    setValue("cRx8", false);
                    setValue("cPx8", true);
                    setValue("cGx8", false);
                    setValue("cBx8", false);
                    setValue("cWx8", false);
                    setValue("cYx8", false);
                    cPrefs.write(String.valueOf(x8.getText()), PINK);
                    x8.setTextColor(Color.parseColor("#FFFFBEDC"));
                    x8.setChecked(false);
                    if (!x8.isChecked()) {
                        setValue(String.valueOf(x8.getText()), true);
                    }
                }

                //real
                if (k98.isChecked()) {
                    setValue("cRk98", false);
                    setValue("cPk98", true);
                    setValue("cGk98", false);
                    setValue("cBk98", false);
                    setValue("cWk98", false);
                    setValue("cYk98", false);
                    cPrefs.write(String.valueOf(k98.getText()), PINK);
                    k98.setTextColor(Color.parseColor("#FFFFBEDC"));
                    k98.setChecked(false);
                    if (!k98.isChecked()) {
                        setValue(String.valueOf(k98.getText()), true);
                    }
                }

                if (akm.isChecked()) {
                    setValue("cRakm", false);
                    setValue("cPakm", true);
                    setValue("cGakm", false);
                    setValue("cBakm", false);
                    setValue("cWakm", false);
                    setValue("cYakm", false);
                    cPrefs.write(String.valueOf(akm.getText()), PINK);
                    akm.setTextColor(Color.parseColor("#FFFFBEDC"));
                    akm.setChecked(false);
                    if (!akm.isChecked()) {
                        setValue(String.valueOf(akm.getText()), true);
                    }
                }

                if (aug.isChecked()) {
                    setValue("cRaug", false);
                    setValue("cPaug", true);
                    setValue("cGaug", false);
                    setValue("cBaug", false);
                    setValue("cWaug", false);
                    setValue("cYaug", false);
                    cPrefs.write(String.valueOf(aug.getText()), PINK);
                    aug.setTextColor(Color.parseColor("#FFFFBEDC"));
                    aug.setChecked(false);
                    if (!aug.isChecked()) {
                        setValue(String.valueOf(aug.getText()), true);
                    }
                }

                if (awm.isChecked()) {
                    setValue("cRawm", false);
                    setValue("cPawm", true);
                    setValue("cGawm", false);
                    setValue("cBawm", false);
                    setValue("cWawm", false);
                    setValue("cYawm", false);
                    cPrefs.write(String.valueOf(awm.getText()), PINK);
                    awm.setTextColor(Color.parseColor("#FFFFBEDC"));
                    awm.setChecked(false);
                    if (!awm.isChecked()) {
                        setValue(String.valueOf(awm.getText()), true);
                    }
                }

                if (bow.isChecked()) {
                    setValue("cRbow", false);
                    setValue("cPbow", true);
                    setValue("cGbow", false);
                    setValue("cBbow", false);
                    setValue("cWbow", false);
                    setValue("cYbow", false);
                    cPrefs.write(String.valueOf(bow.getText()), PINK);
                    bow.setTextColor(Color.parseColor("#FFFFBEDC"));
                    bow.setChecked(false);
                    if (!bow.isChecked()) {
                        setValue(String.valueOf(bow.getText()), true);
                    }
                }

                if (dp12.isChecked()) {
                    setValue("cRdp12", false);
                    setValue("cPdp12", true);
                    setValue("cGdp12", false);
                    setValue("cBdp12", false);
                    setValue("cWdp12", false);
                    setValue("cYdp12", false);
                    cPrefs.write(String.valueOf(dp12.getText()), PINK);
                    dp12.setTextColor(Color.parseColor("#FFFFBEDC"));
                    dp12.setChecked(false);
                    if (!dp12.isChecked()) {
                        setValue(String.valueOf(dp12.getText()), true);
                    }
                }

                if (dp28.isChecked()) {
                    setValue("cRdp28", false);
                    setValue("cPdp28", true);
                    setValue("cGdp28", false);
                    setValue("cBdp28", false);
                    setValue("cWdp28", false);
                    setValue("cYdp28", false);
                    cPrefs.write(String.valueOf(dp28.getText()), PINK);
                    dp28.setTextColor(Color.parseColor("#FFFFBEDC"));
                    dp28.setChecked(false);
                    if (!dp28.isChecked()) {
                        setValue(String.valueOf(dp28.getText()), true);
                    }
                }

                if (deagle.isChecked()) {
                    setValue("cRdeg", false);
                    setValue("cPdeg", true);
                    setValue("cGdeg", false);
                    setValue("cBdeg", false);
                    setValue("cWdeg", false);
                    setValue("cYdeg", false);
                    cPrefs.write(String.valueOf(deagle.getText()), PINK);
                    deagle.setTextColor(Color.parseColor("#FFFFBEDC"));
                    deagle.setChecked(false);
                    if (!deagle.isChecked()) {
                        setValue(String.valueOf(deagle.getText()), true);
                    }
                }

                if (redDot.isChecked()) {
                    setValue("cRredDot", false);
                    setValue("cPredDot", true);
                    setValue("cGredDot", false);
                    setValue("cBredDot", false);
                    setValue("cWredDot", false);
                    setValue("cYredDot", false);
                    cPrefs.write(String.valueOf(redDot.getText()), PINK);
                    redDot.setTextColor(Color.parseColor("#FFFFBEDC"));
                    redDot.setChecked(false);
                    if (!redDot.isChecked()) {
                        setValue(String.valueOf(redDot.getText()), true);
                    }
                }

                if (flare.isChecked()) {
                    setValue("cRflare", false);
                    setValue("cPflare", true);
                    setValue("cGflare", false);
                    setValue("cBflare", false);
                    setValue("cWflare", false);
                    setValue("cYflare", false);
                    cPrefs.write(String.valueOf(flare.getText()), PINK);
                    flare.setTextColor(Color.parseColor("#FFFFBEDC"));
                    flare.setChecked(false);
                    if (!flare.isChecked()) {
                        setValue(String.valueOf(flare.getText()), true);
                    }
                }

                if (g36.isChecked()) {
                    setValue("cRg36", false);
                    setValue("cPg36", true);
                    setValue("cGg36", false);
                    setValue("cBg36", false);
                    setValue("cWg36", false);
                    setValue("cYg36", false);
                    cPrefs.write(String.valueOf(g36.getText()), PINK);
                    g36.setTextColor(Color.parseColor("#FFFFBEDC"));
                    g36.setChecked(false);
                    if (!g36.isChecked()) {
                        setValue(String.valueOf(g36.getText()), true);
                    }
                }

                if (grenade.isChecked()) {
                    setValue("cRgrenade", false);
                    setValue("cPgrenade", true);
                    setValue("cGgrenade", false);
                    setValue("cBgrenade", false);
                    setValue("cWgrenade", false);
                    setValue("cYgrenade", false);
                    cPrefs.write(String.valueOf(grenade.getText()), PINK);
                    grenade.setTextColor(Color.parseColor("#FFFFBEDC"));
                    grenade.setChecked(false);
                    if (!grenade.isChecked()) {
                        setValue(String.valueOf(grenade.getText()), true);
                    }
                }

                if (groza.isChecked()) {
                    setValue("cRgroza", false);
                    setValue("cPgroza", true);
                    setValue("cGgroza", false);
                    setValue("cBgroza", false);
                    setValue("cWgroza", false);
                    setValue("cYgroza", false);
                    cPrefs.write(String.valueOf(groza.getText()), PINK);
                    groza.setTextColor(Color.parseColor("#FFFFBEDC"));
                    groza.setChecked(false);
                    if (!groza.isChecked()) {
                        setValue(String.valueOf(groza.getText()), true);
                    }
                }

                if (hollo.isChecked()) {
                    setValue("cRhollo", false);
                    setValue("cPhollo", true);
                    setValue("cGhollo", false);
                    setValue("cBhollo", false);
                    setValue("cWhollo", false);
                    setValue("cYhollo", false);
                    cPrefs.write(String.valueOf(hollo.getText()), PINK);
                    hollo.setTextColor(Color.parseColor("#FFFFBEDC"));
                    hollo.setChecked(false);
                    if (!hollo.isChecked()) {
                        setValue(String.valueOf(hollo.getText()), true);
                    }
                }

                if (m1014.isChecked()) {
                    setValue("cRm1014", false);
                    setValue("cPm1014", true);
                    setValue("cGm1014", false);
                    setValue("cBm1014", false);
                    setValue("cWm1014", false);
                    setValue("cYm1014", false);
                    cPrefs.write(String.valueOf(m1014.getText()), PINK);
                    m1014.setTextColor(Color.parseColor("#FFFFBEDC"));
                    m1014.setChecked(false);
                    if (!m1014.isChecked()) {
                        setValue(String.valueOf(m1014.getText()), true);
                    }
                }

                if (m16.isChecked()) {
                    setValue("cRm16", false);
                    setValue("cPm16", true);
                    setValue("cGm16", false);
                    setValue("cBm16", false);
                    setValue("cWm16", false);
                    setValue("cYm16", false);
                    cPrefs.write(String.valueOf(m16.getText()), PINK);
                    m16.setTextColor(Color.parseColor("#FFFFBEDC"));
                    m16.setChecked(false);
                    if (!m16.isChecked()) {
                        setValue(String.valueOf(m16.getText()), true);
                    }
                }

                if (m24.isChecked()) {
                    setValue("cRm24", false);
                    setValue("cPm24", true);
                    setValue("cGm24", false);
                    setValue("cBm24", false);
                    setValue("cWm24", false);
                    setValue("cYm24", false);
                    cPrefs.write(String.valueOf(m24.getText()), PINK);
                    m24.setTextColor(Color.parseColor("#FFFFBEDC"));
                    m24.setChecked(false);
                    if (!m24.isChecked()) {
                        setValue(String.valueOf(m24.getText()), true);
                    }
                }

                if (m249.isChecked()) {
                    setValue("cRm249", false);
                    setValue("cPm249", true);
                    setValue("cGm249", false);
                    setValue("cBm249", false);
                    setValue("cWm249", false);
                    setValue("cYm249", false);
                    cPrefs.write(String.valueOf(m249.getText()), PINK);
                    m249.setTextColor(Color.parseColor("#FFFFBEDC"));
                    m249.setChecked(false);
                    if (!m249.isChecked()) {
                        setValue(String.valueOf(m249.getText()), true);
                    }
                }

                if (m4.isChecked()) {
                    setValue("cRm4", false);
                    setValue("cPm4", true);
                    setValue("cGm4", false);
                    setValue("cBm4", false);
                    setValue("cWm4", false);
                    setValue("cYm4", false);
                    cPrefs.write(String.valueOf(m4.getText()), PINK);
                    m4.setTextColor(Color.parseColor("#FFFFBEDC"));
                    m4.setChecked(false);
                    if (!m4.isChecked()) {
                        setValue(String.valueOf(m4.getText()), true);
                    }
                }

                if (m762g.isChecked()) {
                    setValue("cRm762", false);
                    setValue("cPm762", true);
                    setValue("cGm762", false);
                    setValue("cBm762", false);
                    setValue("cWm762", false);
                    setValue("cYm762", false);
                    cPrefs.write(String.valueOf(m762g.getText()), PINK);
                    m762g.setTextColor(Color.parseColor("#FFFFBEDC"));
                    m762g.setChecked(false);
                    if (!m762g.isChecked()) {
                        setValue(String.valueOf(m762g.getText()), true);
                    }
                }

                if (mk14.isChecked()) {
                    setValue("cRmk14", false);
                    setValue("cPmk14", true);
                    setValue("cGmk14", false);
                    setValue("cBmk14", false);
                    setValue("cWmk14", false);
                    setValue("cYmk14", false);
                    cPrefs.write(String.valueOf(mk14.getText()), PINK);
                    mk14.setTextColor(Color.parseColor("#FFFFBEDC"));
                    mk14.setChecked(false);
                    if (!mk14.isChecked()) {
                        setValue(String.valueOf(mk14.getText()), true);
                    }
                }

                if (mk47.isChecked()) {
                    setValue("cRmk47", false);
                    setValue("cPmk47", true);
                    setValue("cGmk47", false);
                    setValue("cBmk47", false);
                    setValue("cWmk47", false);
                    setValue("cYmk47", false);
                    cPrefs.write(String.valueOf(mk47.getText()), PINK);
                    mk47.setTextColor(Color.parseColor("#FFFFBEDC"));
                    mk47.setChecked(false);
                    if (!mk47.isChecked()) {
                        setValue(String.valueOf(mk47.getText()), true);
                    }
                }

                if (mp5k.isChecked()) {
                    setValue("cRmp5k", false);
                    setValue("cPmp5k", true);
                    setValue("cGmp5k", false);
                    setValue("cBmp5k", false);
                    setValue("cWmp5k", false);
                    setValue("cYmp5k", false);
                    cPrefs.write(String.valueOf(mp5k.getText()), PINK);
                    mp5k.setTextColor(Color.parseColor("#FFFFBEDC"));
                    mp5k.setChecked(false);
                    if (!mp5k.isChecked()) {
                        setValue(String.valueOf(mp5k.getText()), true);
                    }
                }

                if (mini14.isChecked()) {
                    setValue("cRmini14", false);
                    setValue("cPmini14", true);
                    setValue("cGmini14", false);
                    setValue("cBmini14", false);
                    setValue("cWmini14", false);
                    setValue("cYmini14", false);
                    cPrefs.write(String.valueOf(mini14.getText()), PINK);
                    mini14.setTextColor(Color.parseColor("#FFFFBEDC"));
                    mini14.setChecked(false);
                    if (!mini14.isChecked()) {
                        setValue(String.valueOf(mini14.getText()), true);
                    }
                }

                if (molotov.isChecked()) {
                    setValue("cRburn", false);
                    setValue("cPburn", true);
                    setValue("cGburn", false);
                    setValue("cBburn", false);
                    setValue("cWburn", false);
                    setValue("cYburn", false);
                    cPrefs.write(String.valueOf(molotov.getText()), PINK);
                    molotov.setTextColor(Color.parseColor("#FFFFBEDC"));
                    molotov.setChecked(false);
                    if (!molotov.isChecked()) {
                        setValue(String.valueOf(molotov.getText()), true);
                    }
                }

                if (pp19.isChecked()) {
                    setValue("cRpp19", false);
                    setValue("cPpp19", true);
                    setValue("cGpp19", false);
                    setValue("cBpp19", false);
                    setValue("cWpp19", false);
                    setValue("cYpp19", false);
                    cPrefs.write(String.valueOf(pp19.getText()), PINK);
                    pp19.setTextColor(Color.parseColor("#FFFFBEDC"));
                    pp19.setChecked(false);
                    if (!pp19.isChecked()) {
                        setValue(String.valueOf(pp19.getText()), true);
                    }
                }

                if (pan.isChecked()) {
                    setValue("cRpan", false);
                    setValue("cPpan", true);
                    setValue("cGpan", false);
                    setValue("cBpan", false);
                    setValue("cWpan", false);
                    setValue("cYpan", false);
                    cPrefs.write(String.valueOf(pan.getText()), PINK);
                    pan.setTextColor(Color.parseColor("#FFFFBEDC"));
                    pan.setChecked(false);
                    if (!pan.isChecked()) {
                        setValue(String.valueOf(pan.getText()), true);
                    }
                }

                if (qbu.isChecked()) {
                    setValue("cRqbu", false);
                    setValue("cPqbu", true);
                    setValue("cGqbu", false);
                    setValue("cBqbu", false);
                    setValue("cWqbu", false);
                    setValue("cYqbu", false);
                    cPrefs.write(String.valueOf(qbu.getText()), PINK);
                    qbu.setTextColor(Color.parseColor("#FFFFBEDC"));
                    qbu.setChecked(false);
                    if (!qbu.isChecked()) {
                        setValue(String.valueOf(qbu.getText()), true);
                    }
                }

                if (qbz.isChecked()) {
                    setValue("cRqbz", false);
                    setValue("cPqbz", true);
                    setValue("cGqbz", false);
                    setValue("cBqbz", false);
                    setValue("cWqbz", false);
                    setValue("cYqbz", false);
                    cPrefs.write(String.valueOf(qbz.getText()), PINK);
                    qbz.setTextColor(Color.parseColor("#FFFFBEDC"));
                    qbz.setChecked(false);
                    if (!qbz.isChecked()) {
                        setValue(String.valueOf(qbz.getText()), true);
                    }
                }

                if (s12k.isChecked()) {
                    setValue("cRs12k", false);
                    setValue("cPs12k", true);
                    setValue("cGs12k", false);
                    setValue("cBs12k", false);
                    setValue("cWs12k", false);
                    setValue("cYs12k", false);
                    cPrefs.write(String.valueOf(s12k.getText()), PINK);
                    s12k.setTextColor(Color.parseColor("#FFFFBEDC"));
                    s12k.setChecked(false);
                    if (!s12k.isChecked()) {
                        setValue(String.valueOf(s12k.getText()), true);
                    }
                }

                if (s1897.isChecked()) {
                    setValue("cRs1897", false);
                    setValue("cPs1897", true);
                    setValue("cGs1897", false);
                    setValue("cBs1897", false);
                    setValue("cWs1897", false);
                    setValue("cYs1897", false);
                    cPrefs.write(String.valueOf(s1897.getText()), PINK);
                    s1897.setTextColor(Color.parseColor("#FFFFBEDC"));
                    s1897.setChecked(false);
                    if (!s1897.isChecked()) {
                        setValue(String.valueOf(s1897.getText()), true);
                    }
                }

                if (s686.isChecked()) {
                    setValue("cRs686", false);
                    setValue("cPs686", true);
                    setValue("cGs686", false);
                    setValue("cBs686", false);
                    setValue("cWs686", false);
                    setValue("cYs686", false);
                    cPrefs.write(String.valueOf(s686.getText()), PINK);
                    s686.setTextColor(Color.parseColor("#FFFFBEDC"));
                    s686.setChecked(false);
                    if (!s686.isChecked()) {
                        setValue(String.valueOf(s686.getText()), true);
                    }
                }

                if (scarl.isChecked()) {
                    setValue("cRscar", false);
                    setValue("cPscar", true);
                    setValue("cGscar", false);
                    setValue("cBscar", false);
                    setValue("cWscar", false);
                    setValue("cYscar", false);
                    cPrefs.write(String.valueOf(scarl.getText()), PINK);
                    scarl.setTextColor(Color.parseColor("#FFFFBEDC"));
                    scarl.setChecked(false);
                    if (!scarl.isChecked()) {
                        setValue(String.valueOf(scarl.getText()), true);
                    }
                }

                if (sks.isChecked()) {
                    setValue("cRsks", false);
                    setValue("cPsks", true);
                    setValue("cGsks", false);
                    setValue("cBsks", false);
                    setValue("cWsks", false);
                    setValue("cYsks", false);
                    cPrefs.write(String.valueOf(sks.getText()), PINK);
                    sks.setTextColor(Color.parseColor("#FFFFBEDC"));
                    sks.setChecked(false);
                    if (!sks.isChecked()) {
                        setValue(String.valueOf(sks.getText()), true);
                    }
                }

                if (slr.isChecked()) {
                    setValue("cRslr", false);
                    setValue("cPslr", true);
                    setValue("cGslr", false);
                    setValue("cBslr", false);
                    setValue("cWslr", false);
                    setValue("cYslr", false);
                    cPrefs.write(String.valueOf(slr.getText()), PINK);
                    slr.setTextColor(Color.parseColor("#FFFFBEDC"));
                    slr.setChecked(false);
                    if (!slr.isChecked()) {
                        setValue(String.valueOf(slr.getText()), true);
                    }
                }

                if (stunt.isChecked()) {
                    setValue("cRflash", false);
                    setValue("cPflash", true);
                    setValue("cGflash", false);
                    setValue("cBflash", false);
                    setValue("cWflash", false);
                    setValue("cYflash", false);
                    cPrefs.write(String.valueOf(stunt.getText()), PINK);
                    stunt.setTextColor(Color.parseColor("#FFFFBEDC"));
                    stunt.setChecked(false);
                    if (!stunt.isChecked()) {
                        setValue(String.valueOf(stunt.getText()), true);
                    }
                }

                if (tommy.isChecked()) {
                    setValue("cRthomp", false);
                    setValue("cPthomp", true);
                    setValue("cGthomp", false);
                    setValue("cBthomp", false);
                    setValue("cWthomp", false);
                    setValue("cYthomp", false);
                    cPrefs.write(String.valueOf(tommy.getText()), PINK);
                    tommy.setTextColor(Color.parseColor("#FFFFBEDC"));
                    tommy.setChecked(false);
                    if (!tommy.isChecked()) {
                        setValue(String.valueOf(tommy.getText()), true);
                    }
                }

                if (ump45.isChecked()) {
                    setValue("cRump45", false);
                    setValue("cPump45", true);
                    setValue("cGump45", false);
                    setValue("cBump45", false);
                    setValue("cWump45", false);
                    setValue("cYump45", false);
                    cPrefs.write(String.valueOf(ump45.getText()), PINK);
                    ump45.setTextColor(Color.parseColor("#FFFFBEDC"));
                    ump45.setChecked(false);
                    if (!ump45.isChecked()) {
                        setValue(String.valueOf(ump45.getText()), true);
                    }
                }

                if (uzi.isChecked()) {
                    setValue("cRuzi", false);
                    setValue("cPuzi", true);
                    setValue("cGuzi", false);
                    setValue("cBuzi", false);
                    setValue("cWuzi", false);
                    setValue("cYuzi", false);
                    cPrefs.write(String.valueOf(uzi.getText()), PINK);
                    uzi.setTextColor(Color.parseColor("#FFFFBEDC"));
                    uzi.setChecked(false);
                    if (!uzi.isChecked()) {
                        setValue(String.valueOf(uzi.getText()), true);
                    }
                }

                if (vss.isChecked()) {
                    setValue("cRvss", false);
                    setValue("cPvss", true);
                    setValue("cGvss", false);
                    setValue("cBvss", false);
                    setValue("cWvss", false);
                    setValue("cYvss", false);
                    cPrefs.write(String.valueOf(vss.getText()), PINK);
                    vss.setTextColor(Color.parseColor("#FFFFBEDC"));
                    vss.setChecked(false);
                    if (!vss.isChecked()) {
                        setValue(String.valueOf(vss.getText()), true);
                    }
                }

                if (vector.isChecked()) {
                    setValue("cRvect", false);
                    setValue("cPvect", true);
                    setValue("cGvect", false);
                    setValue("cBvect", false);
                    setValue("cWvect", false);
                    setValue("cYvect", false);
                    cPrefs.write(String.valueOf(vector.getText()), PINK);
                    vector.setTextColor(Color.parseColor("#FFFFBEDC"));
                    vector.setChecked(false);
                    if (!vector.isChecked()) {
                        setValue(String.valueOf(vector.getText()), true);
                    }
                }

                if (win94.isChecked()) {
                    setValue("cRwinchester", false);
                    setValue("cPwinchester", true);
                    setValue("cGwinchester", false);
                    setValue("cBwinchester", false);
                    setValue("cWwinchester", false);
                    setValue("cYwinchester", false);
                    cPrefs.write(String.valueOf(win94.getText()), PINK);
                    win94.setTextColor(Color.parseColor("#FFFFBEDC"));
                    win94.setChecked(false);
                    if (!win94.isChecked()) {
                        setValue(String.valueOf(win94.getText()), true);
                    }
                }
            }
        }); //ItemColorPink

        clrGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dacia.isChecked()) {
                    setValue("clrRedDacia", false);
                    setValue("clrPinkDacia", false);
                    setValue("clrGreenDacia", true);
                    setValue("clrBlueDacia", false);
                    setValue("clrWhiteDacia", false);
                    setValue("clrYellowDacia", false);
                    cPrefs.write(String.valueOf(Dacia.getText()), GREEN);
                    Dacia.setTextColor(Color.parseColor("#ff00ff00"));
                    Dacia.setChecked(false);
                    if (!Dacia.isChecked()) {
                        setValue(String.valueOf(Dacia.getText()), true);
                    }
                }
                if (UAZ.isChecked()) {
                    setValue("clrRedUAZ", false);
                    setValue("clrPinkUAZ", false);
                    setValue("clrGreenUAZ", true);
                    setValue("clrBlueUAZ", false);
                    setValue("clrWhiteUAZ", false);
                    setValue("clrYellowUAZ", false);
                    cPrefs.write(String.valueOf(UAZ.getText()), GREEN);
                    UAZ.setTextColor(Color.parseColor("#ff00ff00"));
                    UAZ.setChecked(false);
                    if (!UAZ.isChecked()) {
                        setValue(String.valueOf(UAZ.getText()), true);
                    }
                }
                if (Buggy.isChecked()) {
                    setValue("clrRedBuggy", false);
                    setValue("clrPinkBuggy", false);
                    setValue("clrGreenBuggy", true);
                    setValue("clrBlueBuggy", false);
                    setValue("clrWhiteBuggy", false);
                    setValue("clrYellowBuggy", false);
                    cPrefs.write(String.valueOf(Buggy.getText()), GREEN);
                    Buggy.setTextColor(Color.parseColor("#ff00ff00"));
                    Buggy.setChecked(false);
                    if (!Buggy.isChecked()) {
                        setValue(String.valueOf(Buggy.getText()), true);
                    }
                }
                if (Bike.isChecked()) {
                    setValue("clrRedBike", false);
                    setValue("clrPinkBike", false);
                    setValue("clrGreenBike", true);
                    setValue("clrBlueBike", false);
                    setValue("clrWhiteBike", false);
                    setValue("clrYellowBike", false);
                    cPrefs.write(String.valueOf(Bike.getText()), GREEN);
                    Bike.setTextColor(Color.parseColor("#ff00ff00"));
                    Bike.setChecked(false);
                    if (!Bike.isChecked()) {
                        setValue(String.valueOf(Bike.getText()), true);
                    }
                }
                if (Jet.isChecked()) {
                    setValue("clrRedAquaRail", false);
                    setValue("clrPinkAquaRail", false);
                    setValue("clrGreenAquaRail", true);
                    setValue("clrBlueAquaRail", false);
                    setValue("clrWhiteAquaRail", false);
                    setValue("clrYellowAquaRail", false);
                    cPrefs.write(String.valueOf(Jet.getText()), GREEN);
                    Jet.setTextColor(Color.parseColor("#ff00ff00"));
                    Jet.setChecked(false);
                    if (!Jet.isChecked()) {
                        setValue(String.valueOf(Jet.getText()), true);
                    }
                }
                if (Boat.isChecked()) {
                    setValue("clrRedBoat", false);
                    setValue("clrPinkBoat", false);
                    setValue("clrGreenBoat", true);
                    setValue("clrBlueBoat", false);
                    setValue("clrWhiteBoat", false);
                    setValue("clrYellowBoat", false);
                    cPrefs.write(String.valueOf(Boat.getText()), GREEN);
                    Boat.setTextColor(Color.parseColor("#ff00ff00"));
                    Boat.setChecked(false);
                    if (!Boat.isChecked()) {
                        setValue(String.valueOf(Boat.getText()), true);
                    }
                }
                if (Bus.isChecked()) {
                    setValue("clrRedBus", false);
                    setValue("clrPinkBus", false);
                    setValue("clrGreenBus", true);
                    setValue("clrBlueBus", false);
                    setValue("clrWhiteBus", false);
                    setValue("clrYellowBus", false);
                    cPrefs.write(String.valueOf(Bus.getText()), GREEN);
                    Bus.setTextColor(Color.parseColor("#ff00ff00"));
                    Bus.setChecked(false);
                    if (!Bus.isChecked()) {
                        setValue(String.valueOf(Bus.getText()), true);
                    }
                }
                if (Mirado.isChecked()) {
                    setValue("clrRedMirado", false);
                    setValue("clrPinkMirado", false);
                    setValue("clrGreenMirado", true);
                    setValue("clrBlueMirado", false);
                    setValue("clrWhiteMirado", false);
                    setValue("clrYellowMirado", false);
                    cPrefs.write(String.valueOf(Mirado.getText()), GREEN);
                    Mirado.setTextColor(Color.parseColor("#ff00ff00"));
                    Mirado.setChecked(false);
                    if (!Mirado.isChecked()) {
                        setValue(String.valueOf(Mirado.getText()), true);
                    }
                }
                if (Scooter.isChecked()) {
                    setValue("clrRedScooter", false);
                    setValue("clrPinkScooter", false);
                    setValue("clrGreenScooter", true);
                    setValue("clrBlueScooter", false);
                    setValue("clrWhiteScooter", false);
                    setValue("clrYellowScooter", false);
                    cPrefs.write(String.valueOf(Scooter.getText()), GREEN);
                    Scooter.setTextColor(Color.parseColor("#ff00ff00"));
                    Scooter.setChecked(false);
                    if (!Scooter.isChecked()) {
                        setValue(String.valueOf(Scooter.getText()), true);
                    }
                }
                if (Rony.isChecked()) {
                    setValue("clrRedRony", false);
                    setValue("clrPinkRony", false);
                    setValue("clrGreenRony", true);
                    setValue("clrBlueRony", false);
                    setValue("clrWhiteRony", false);
                    setValue("clrYellowRony", false);
                    cPrefs.write(String.valueOf(Rony.getText()), GREEN);
                    Rony.setTextColor(Color.parseColor("#ff00ff00"));
                    Rony.setChecked(false);
                    if (!Rony.isChecked()) {
                        setValue(String.valueOf(Rony.getText()), true);
                    }
                }
                if (Snowbike.isChecked()) {
                    setValue("clrRedSnowbike", false);
                    setValue("clrPinkSnowbike", false);
                    setValue("clrGreenSnowbike", true);
                    setValue("clrBlueSnowbike", false);
                    setValue("clrWhiteSnowbike", false);
                    setValue("clrYellowSnowbike", false);
                    cPrefs.write(String.valueOf(Snowbike.getText()), GREEN);
                    Snowbike.setTextColor(Color.parseColor("#ff00ff00"));
                    Snowbike.setChecked(false);
                    if (!Snowbike.isChecked()) {
                        setValue(String.valueOf(Snowbike.getText()), true);
                    }
                }
                if (Snowmobile.isChecked()) {
                    setValue("clrRedSnowmobile", false);
                    setValue("clrPinkSnowmobile", false);
                    setValue("clrGreenSnowmobile", true);
                    setValue("clrBlueSnowmobile", false);
                    setValue("clrWhiteSnowmobile", false);
                    setValue("clrYellowSnowmobile", false);
                    cPrefs.write(String.valueOf(Snowmobile.getText()), GREEN);
                    Snowmobile.setTextColor(Color.parseColor("#ff00ff00"));
                    Snowmobile.setChecked(false);
                    if (!Snowmobile.isChecked()) {
                        setValue(String.valueOf(Snowmobile.getText()), true);
                    }
                }
                if (Tempo.isChecked()) {
                    setValue("clrRedTuk", false);
                    setValue("clrPinkTuk", false);
                    setValue("clrGreenTuk", true);
                    setValue("clrBlueTuk", false);
                    setValue("clrWhiteTuk", false);
                    setValue("clrYellowTuk", false);
                    cPrefs.write(String.valueOf(Tempo.getText()), GREEN);
                    Tempo.setTextColor(Color.parseColor("#ff00ff00"));
                    Tempo.setChecked(false);
                    if (!Tempo.isChecked()) {
                        setValue(String.valueOf(Tempo.getText()), true);
                    }
                }
                if (Truck.isChecked()) {
                    setValue("clrRedTruck", false);
                    setValue("clrPinkTruck", false);
                    setValue("clrGreenTruck", true);
                    setValue("clrBlueTruck", false);
                    setValue("clrWhiteTruck", false);
                    setValue("clrYellowTruck", false);
                    cPrefs.write(String.valueOf(Truck.getText()), GREEN);
                    Truck.setTextColor(Color.parseColor("#ff00ff00"));
                    Truck.setChecked(false);
                    if (!Truck.isChecked()) {
                        setValue(String.valueOf(Truck.getText()), true);
                    }
                }
                if (BRDM.isChecked()) {
                    setValue("clrRedBRDM", false);
                    setValue("clrPinkBRDM", false);
                    setValue("clrGreenBRDM", true);
                    setValue("clrBlueBRDM", false);
                    setValue("clrWhiteBRDM", false);
                    setValue("clrYellowBRDM", false);
                    cPrefs.write(String.valueOf(BRDM.getText()), GREEN);
                    BRDM.setTextColor(Color.parseColor("#ff00ff00"));
                    BRDM.setChecked(false);
                    if (!BRDM.isChecked()) {
                        setValue(String.valueOf(BRDM.getText()), true);
                    }
                }
                if (LadaNiva.isChecked()) {
                    setValue("clrRedLadaNiva", false);
                    setValue("clrPinkLadaNiva", false);
                    setValue("clrGreenLadaNiva", true);
                    setValue("clrBlueLadaNiva", false);
                    setValue("clrWhiteLadaNiva", false);
                    setValue("clrYellowLadaNiva", false);
                    cPrefs.write(String.valueOf(LadaNiva.getText()), GREEN);
                    LadaNiva.setTextColor(Color.parseColor("#ff00ff00"));
                    LadaNiva.setChecked(false);
                    if (!LadaNiva.isChecked()) {
                        setValue(String.valueOf(LadaNiva.getText()), true);
                    }
                }
                if (MonsterTruck.isChecked()) {
                    setValue("clrRedMonster", false);
                    setValue("clrPinkMonster", false);
                    setValue("clrGreenMonster", true);
                    setValue("clrBlueMonster", false);
                    setValue("clrWhiteMonster", false);
                    setValue("clrYellowMonster", false);
                    cPrefs.write(String.valueOf(MonsterTruck.getText()), GREEN);
                    MonsterTruck.setTextColor(Color.parseColor("#ff00ff00"));
                    MonsterTruck.setChecked(false);
                    if (!MonsterTruck.isChecked()) {
                        setValue(String.valueOf(MonsterTruck.getText()), true);
                    }
                }
                if (CoupeRB.isChecked()) {
                    setValue("clrRedCoupeRB", false);
                    setValue("clrPinkCoupeRB", false);
                    setValue("clrGreenCoupeRB", true);
                    setValue("clrBlueCoupeRB", false);
                    setValue("clrWhiteCoupeRB", false);
                    setValue("clrYellowCoupeRB", false);
                    cPrefs.write(String.valueOf(CoupeRB.getText()), GREEN);
                    CoupeRB.setTextColor(Color.parseColor("#ff00ff00"));
                    CoupeRB.setChecked(false);
                    if (!CoupeRB.isChecked()) {
                        setValue(String.valueOf(CoupeRB.getText()), true);
                    }
                }
                if (UTV.isChecked()) {
                    setValue("clrRedUTV", false);
                    setValue("clrPinkUTV", false);
                    setValue("clrGreenUTV", true);
                    setValue("clrBlueUTV", false);
                    setValue("clrWhiteUTV", false);
                    setValue("clrYellowUTV", false);
                    cPrefs.write(String.valueOf(UTV.getText()), GREEN);
                    UTV.setTextColor(Color.parseColor("#ff00ff00"));
                    UTV.setChecked(false);
                    if (!UTV.isChecked()) {
                        setValue(String.valueOf(UTV.getText()), true);
                    }
                }

                if (am300.isChecked()) {
                    setValue("cR300mm", false);
                    setValue("cP300mm", false);
                    setValue("cG300mm", true);
                    setValue("cB300mm", false);
                    setValue("cW300mm", false);
                    setValue("cY300mm", false);
                    cPrefs.write(String.valueOf(am300.getText()), GREEN);
                    am300.setTextColor(Color.parseColor("#ff00ff00"));
                    am300.setChecked(false);
                    if (!am300.isChecked()) {
                        setValue(String.valueOf(am300.getText()), true);
                    }
                }

                if (am45.isChecked()) {
                    setValue("cRam45", false);
                    setValue("cPam45", false);
                    setValue("cGam45", true);
                    setValue("cBam45", false);
                    setValue("cWam45", false);
                    setValue("cYam45", false);
                    cPrefs.write(String.valueOf(am45.getText()), GREEN);
                    am45.setTextColor(Color.parseColor("#ff00ff00"));
                    am45.setChecked(false);
                    if (!am45.isChecked()) {
                        setValue(String.valueOf(am45.getText()), true);
                    }
                }

                if (am12g.isChecked()) {
                    setValue("cRam12g", false);
                    setValue("cPam12g", false);
                    setValue("cGam12g", true);
                    setValue("cBam12g", false);
                    setValue("cWam12g", false);
                    setValue("cYam12g", false);
                    cPrefs.write(String.valueOf(am12g.getText()), GREEN);
                    am12g.setTextColor(Color.parseColor("#ff00ff00"));
                    am12g.setChecked(false);
                    if (!am12g.isChecked()) {
                        setValue(String.valueOf(am12g.getText()), true);
                    }
                }

                if (am556.isChecked()) {
                    setValue("cRam556", false);
                    setValue("cPam556", false);
                    setValue("cGam556", true);
                    setValue("cBam556", false);
                    setValue("cWam556", false);
                    setValue("cYam556", false);
                    cPrefs.write(String.valueOf(am556.getText()), GREEN);
                    am556.setTextColor(Color.parseColor("#ff00ff00"));
                    am556.setChecked(false);
                    if (!am556.isChecked()) {
                        setValue(String.valueOf(am556.getText()), true);
                    }
                }

                if (am762.isChecked()) {
                    setValue("cRam762", false);
                    setValue("cPam762", false);
                    setValue("cGam762", true);
                    setValue("cBam762", false);
                    setValue("cWam762", false);
                    setValue("cYam762", false);
                    cPrefs.write(String.valueOf(am762.getText()), GREEN);
                    am762.setTextColor(Color.parseColor("#ff00ff00"));
                    am762.setChecked(false);
                    if (!am762.isChecked()) {
                        setValue(String.valueOf(am762.getText()), true);
                    }
                }

                if (am9mm.isChecked()) {
                    setValue("cRam9mm", false);
                    setValue("cPam9mm", false);
                    setValue("cGam9mm", true);
                    setValue("cBam9mm", false);
                    setValue("cWam9mm", false);
                    setValue("cYam9mm", false);
                    cPrefs.write(String.valueOf(am9mm.getText()), GREEN);
                    am9mm.setTextColor(Color.parseColor("#ff00ff00"));
                    am9mm.setChecked(false);
                    if (!am9mm.isChecked()) {
                        setValue(String.valueOf(am9mm.getText()), true);
                    }
                }

                if (armorLvl2.isChecked()) {
                    setValue("cRar2", false);
                    setValue("cPar2", false);
                    setValue("cGar2", true);
                    setValue("cBar2", false);
                    setValue("cWar2", false);
                    setValue("cYar2", false);
                    cPrefs.write(String.valueOf(armorLvl2.getText()), GREEN);
                    armorLvl2.setTextColor(Color.parseColor("#ff00ff00"));
                    armorLvl2.setChecked(false);
                    if (!armorLvl2.isChecked()) {
                        setValue(String.valueOf(armorLvl2.getText()), true);
                    }
                }

                if (armorLvl3.isChecked()) {
                    setValue("cRar3", false);
                    setValue("cPar3", false);
                    setValue("cGar3", true);
                    setValue("cBar3", false);
                    setValue("cWar3", false);
                    setValue("cYar3", false);
                    cPrefs.write(String.valueOf(armorLvl3.getText()), GREEN);
                    armorLvl3.setTextColor(Color.parseColor("#ff00ff00"));
                    armorLvl3.setChecked(false);
                    if (!armorLvl3.isChecked()) {
                        setValue(String.valueOf(armorLvl3.getText()), true);
                    }
                }

                if (helmetLvl2.isChecked()) {
                    setValue("cRhel2", false);
                    setValue("cPhel2", false);
                    setValue("cGhel2", true);
                    setValue("cBhel2", false);
                    setValue("cWhel2", false);
                    setValue("cYhel2", false);
                    cPrefs.write(String.valueOf(helmetLvl2.getText()), GREEN);
                    helmetLvl2.setTextColor(Color.parseColor("#ff00ff00"));
                    helmetLvl2.setChecked(false);
                    if (!helmetLvl2.isChecked()) {
                        setValue(String.valueOf(helmetLvl2.getText()), true);
                    }
                }

                if (helmetLvl3.isChecked()) {
                    setValue("cRhel3", false);
                    setValue("cPhel3", false);
                    setValue("cGhel3", true);
                    setValue("cBhel3", false);
                    setValue("cWhel3", false);
                    setValue("cYhel3", false);
                    cPrefs.write(String.valueOf(helmetLvl3.getText()), GREEN);
                    helmetLvl3.setTextColor(Color.parseColor("#ff00ff00"));
                    helmetLvl3.setChecked(false);
                    if (!helmetLvl3.isChecked()) {
                        setValue(String.valueOf(helmetLvl3.getText()), true);
                    }
                }

                if (bandage.isChecked()) {
                    setValue("cRban", false);
                    setValue("cPban", false);
                    setValue("cGban", true);
                    setValue("cBban", false);
                    setValue("cWban", false);
                    setValue("cYban", false);
                    cPrefs.write(String.valueOf(bandage.getText()), GREEN);
                    bandage.setTextColor(Color.parseColor("#ff00ff00"));
                    bandage.setChecked(false);
                    if (!bandage.isChecked()) {
                        setValue(String.valueOf(bandage.getText()), true);
                    }
                }

                if (drink.isChecked()) {
                    setValue("cRdrink", false);
                    setValue("cPdrink", false);
                    setValue("cGdrink", true);
                    setValue("cBdrink", false);
                    setValue("cWdrink", false);
                    setValue("cYdrink", false);
                    cPrefs.write(String.valueOf(drink.getText()), GREEN);
                    drink.setTextColor(Color.parseColor("#ff00ff00"));
                    drink.setChecked(false);
                    if (!drink.isChecked()) {
                        setValue(String.valueOf(drink.getText()), true);
                    }
                }

                if (firstAir.isChecked()) {
                    setValue("cRfa", false);
                    setValue("cPfa", false);
                    setValue("cGfa", true);
                    setValue("cBfa", false);
                    setValue("cWfa", false);
                    setValue("cYfa", false);
                    cPrefs.write(String.valueOf(firstAir.getText()), GREEN);
                    firstAir.setTextColor(Color.parseColor("#ff00ff00"));
                    firstAir.setChecked(false);
                    if (!firstAir.isChecked()) {
                        setValue(String.valueOf(firstAir.getText()), true);
                    }
                }

                if (injection.isChecked()) {
                    setValue("cRin", false);
                    setValue("cPin", false);
                    setValue("cGin", true);
                    setValue("cBin", false);
                    setValue("cWin", false);
                    setValue("cYin", false);
                    cPrefs.write(String.valueOf(injection.getText()), GREEN);
                    injection.setTextColor(Color.parseColor("#ff00ff00"));
                    injection.setChecked(false);
                    if (!injection.isChecked()) {
                        setValue(String.valueOf(injection.getText()), true);
                    }
                }

                if (pills.isChecked()) {
                    setValue("cRpills", false);
                    setValue("cPpills", false);
                    setValue("cGpills", true);
                    setValue("cBpills", false);
                    setValue("cWpills", false);
                    setValue("cYpills", false);
                    cPrefs.write(String.valueOf(pills.getText()), GREEN);
                    pills.setTextColor(Color.parseColor("#ff00ff00"));
                    pills.setChecked(false);
                    if (!pills.isChecked()) {
                        setValue(String.valueOf(pills.getText()), true);
                    }
                }

                //Misk
                if (airCraft.isChecked()) {
                    setValue("cRac", false);
                    setValue("cPac", false);
                    setValue("cGac", true);
                    setValue("cBac", false);
                    setValue("cWac", false);
                    setValue("cYac", false);
                    cPrefs.write(String.valueOf(airCraft.getText()), GREEN);
                    airCraft.setTextColor(Color.parseColor("#ff00ff00"));
                    airCraft.setChecked(false);
                    if (!airCraft.isChecked()) {
                        setValue(String.valueOf(airCraft.getText()), true);
                    }
                }

                if (airDrop.isChecked()) {
                    setValue("cRairD", false);
                    setValue("cPairD", false);
                    setValue("cGairD", true);
                    setValue("cBairD", false);
                    setValue("cWairD", false);
                    setValue("cYairD", false);
                    cPrefs.write(String.valueOf(airDrop.getText()), GREEN);
                    airDrop.setTextColor(Color.parseColor("#ff00ff00"));
                    airDrop.setChecked(false);
                    if (!airDrop.isChecked()) {
                        setValue(String.valueOf(airDrop.getText()), true);
                    }
                }

                if (bag2.isChecked()) {
                    setValue("cRb2", false);
                    setValue("cPb2", false);
                    setValue("cGb2", true);
                    setValue("cBb2", false);
                    setValue("cWb2", false);
                    setValue("cYb2", false);
                    cPrefs.write(String.valueOf(bag2.getText()), GREEN);
                    bag2.setTextColor(Color.parseColor("#ff00ff00"));
                    bag2.setChecked(false);
                    if (!bag2.isChecked()) {
                        setValue(String.valueOf(bag2.getText()), true);
                    }
                }

                if (bag3.isChecked()) {
                    setValue("cRb3", false);
                    setValue("cPb3", false);
                    setValue("cGb3", true);
                    setValue("cBb3", false);
                    setValue("cWb3", false);
                    setValue("cYb3", false);
                    cPrefs.write(String.valueOf(bag3.getText()), GREEN);
                    bag3.setTextColor(Color.parseColor("#ff00ff00"));
                    bag3.setChecked(false);
                    if (!bag3.isChecked()) {
                        setValue(String.valueOf(bag3.getText()), true);
                    }
                }

                if (compensator.isChecked()) {
                    setValue("cRcomp", false);
                    setValue("cPcomp", false);
                    setValue("cGcomp", true);
                    setValue("cBcomp", false);
                    setValue("cWcomp", false);
                    setValue("cYcomp", false);
                    cPrefs.write(String.valueOf(compensator.getText()), GREEN);
                    compensator.setTextColor(Color.parseColor("#ff00ff00"));
                    compensator.setChecked(false);
                    if (!compensator.isChecked()) {
                        setValue(String.valueOf(compensator.getText()), true);
                    }
                }

                if (loot.isChecked()) {
                    setValue("cRloot", false);
                    setValue("cPloot", false);
                    setValue("cGloot", true);
                    setValue("cBloot", false);
                    setValue("cWloot", false);
                    setValue("cYloot", false);
                    cPrefs.write(String.valueOf(loot.getText()), GREEN);
                    loot.setTextColor(Color.parseColor("#ff00ff00"));
                    loot.setChecked(false);
                    if (!loot.isChecked()) {
                        setValue(String.valueOf(loot.getText()), true);
                    }
                }

                if (supressor.isChecked()) {
                    setValue("cRsupp", false);
                    setValue("cPsupp", false);
                    setValue("cGsupp", true);
                    setValue("cBsupp", false);
                    setValue("cWsupp", false);
                    setValue("cYsupp", false);
                    cPrefs.write(String.valueOf(supressor.getText()), GREEN);
                    supressor.setTextColor(Color.parseColor("#ff00ff00"));
                    supressor.setChecked(false);
                    if (!supressor.isChecked()) {
                        setValue(String.valueOf(supressor.getText()), true);
                    }
                }

                //Weapons
                if (x2.isChecked()) {
                    setValue("cRx2", false);
                    setValue("cPx2", false);
                    setValue("cGx2", true);
                    setValue("cBx2", false);
                    setValue("cWx2", false);
                    setValue("cYx2", false);
                    cPrefs.write(String.valueOf(x2.getText()), GREEN);
                    x2.setTextColor(Color.parseColor("#ff00ff00"));
                    x2.setChecked(false);
                    if (!x2.isChecked()) {
                        setValue(String.valueOf(x2.getText()), true);
                    }
                }

                if (x3.isChecked()) {
                    setValue("cRx3", false);
                    setValue("cPx3", false);
                    setValue("cGx3", true);
                    setValue("cBx3", false);
                    setValue("cWx3", false);
                    setValue("cYx3", false);
                    cPrefs.write(String.valueOf(x3.getText()), GREEN);
                    x3.setTextColor(Color.parseColor("#ff00ff00"));
                    x3.setChecked(false);
                    if (!x3.isChecked()) {
                        setValue(String.valueOf(x3.getText()), true);
                    }
                }

                if (x4.isChecked()) {
                    setValue("cRx4", false);
                    setValue("cPx4", false);
                    setValue("cGx4", true);
                    setValue("cBx4", false);
                    setValue("cWx4", false);
                    setValue("cYx4", false);
                    cPrefs.write(String.valueOf(x4.getText()), GREEN);
                    x4.setTextColor(Color.parseColor("#ff00ff00"));
                    x4.setChecked(false);
                    if (!x4.isChecked()) {
                        setValue(String.valueOf(x4.getText()), true);
                    }
                }

                if (x6.isChecked()) {
                    setValue("cRx6", false);
                    setValue("cPx6", false);
                    setValue("cGx6", true);
                    setValue("cBx6", false);
                    setValue("cWx6", false);
                    setValue("cYx6", false);
                    cPrefs.write(String.valueOf(x6.getText()), GREEN);
                    x6.setTextColor(Color.parseColor("#ff00ff00"));
                    x6.setChecked(false);
                    if (!x6.isChecked()) {
                        setValue(String.valueOf(x6.getText()), true);
                    }
                }

                if (x8.isChecked()) {
                    setValue("cRx8", false);
                    setValue("cPx8", false);
                    setValue("cGx8", true);
                    setValue("cBx8", false);
                    setValue("cWx8", false);
                    setValue("cYx8", false);
                    cPrefs.write(String.valueOf(x8.getText()), GREEN);
                    x8.setTextColor(Color.parseColor("#ff00ff00"));
                    x8.setChecked(false);
                    if (!x8.isChecked()) {
                        setValue(String.valueOf(x8.getText()), true);
                    }
                }

                //real
                if (k98.isChecked()) {
                    setValue("cRk98", false);
                    setValue("cPk98", false);
                    setValue("cGk98", true);
                    setValue("cBk98", false);
                    setValue("cWk98", false);
                    setValue("cYk98", false);
                    cPrefs.write(String.valueOf(k98.getText()), GREEN);
                    k98.setTextColor(Color.parseColor("#ff00ff00"));
                    k98.setChecked(false);
                    if (!k98.isChecked()) {
                        setValue(String.valueOf(k98.getText()), true);
                    }
                }

                if (akm.isChecked()) {
                    setValue("cRakm", false);
                    setValue("cPakm", false);
                    setValue("cGakm", true);
                    setValue("cBakm", false);
                    setValue("cWakm", false);
                    setValue("cYakm", false);
                    cPrefs.write(String.valueOf(akm.getText()), GREEN);
                    akm.setTextColor(Color.parseColor("#ff00ff00"));
                    akm.setChecked(false);
                    if (!akm.isChecked()) {
                        setValue(String.valueOf(akm.getText()), true);
                    }
                }

                if (aug.isChecked()) {
                    setValue("cRaug", false);
                    setValue("cPaug", false);
                    setValue("cGaug", true);
                    setValue("cBaug", false);
                    setValue("cWaug", false);
                    setValue("cYaug", false);
                    cPrefs.write(String.valueOf(aug.getText()), GREEN);
                    aug.setTextColor(Color.parseColor("#ff00ff00"));
                    aug.setChecked(false);
                    if (!aug.isChecked()) {
                        setValue(String.valueOf(aug.getText()), true);
                    }
                }

                if (awm.isChecked()) {
                    setValue("cRawm", false);
                    setValue("cPawm", false);
                    setValue("cGawm", true);
                    setValue("cBawm", false);
                    setValue("cWawm", false);
                    setValue("cYawm", false);
                    cPrefs.write(String.valueOf(awm.getText()), GREEN);
                    awm.setTextColor(Color.parseColor("#ff00ff00"));
                    awm.setChecked(false);
                    if (!awm.isChecked()) {
                        setValue(String.valueOf(awm.getText()), true);
                    }
                }

                if (bow.isChecked()) {
                    setValue("cRbow", false);
                    setValue("cPbow", false);
                    setValue("cGbow", true);
                    setValue("cBbow", false);
                    setValue("cWbow", false);
                    setValue("cYbow", false);
                    cPrefs.write(String.valueOf(bow.getText()), GREEN);
                    bow.setTextColor(Color.parseColor("#ff00ff00"));
                    bow.setChecked(false);
                    if (!bow.isChecked()) {
                        setValue(String.valueOf(bow.getText()), true);
                    }
                }

                if (dp12.isChecked()) {
                    setValue("cRdp12", false);
                    setValue("cPdp12", false);
                    setValue("cGdp12", true);
                    setValue("cBdp12", false);
                    setValue("cWdp12", false);
                    setValue("cYdp12", false);
                    cPrefs.write(String.valueOf(dp12.getText()), GREEN);
                    dp12.setTextColor(Color.parseColor("#ff00ff00"));
                    dp12.setChecked(false);
                    if (!dp12.isChecked()) {
                        setValue(String.valueOf(dp12.getText()), true);
                    }
                }

                if (dp28.isChecked()) {
                    setValue("cRdp28", false);
                    setValue("cPdp28", false);
                    setValue("cGdp28", true);
                    setValue("cBdp28", false);
                    setValue("cWdp28", false);
                    setValue("cYdp28", false);
                    cPrefs.write(String.valueOf(dp28.getText()), GREEN);
                    dp28.setTextColor(Color.parseColor("#ff00ff00"));
                    dp28.setChecked(false);
                    if (!dp28.isChecked()) {
                        setValue(String.valueOf(dp28.getText()), true);
                    }
                }

                if (deagle.isChecked()) {
                    setValue("cRdeg", false);
                    setValue("cPdeg", false);
                    setValue("cGdeg", true);
                    setValue("cBdeg", false);
                    setValue("cWdeg", false);
                    setValue("cYdeg", false);
                    cPrefs.write(String.valueOf(deagle.getText()), GREEN);
                    deagle.setTextColor(Color.parseColor("#ff00ff00"));
                    deagle.setChecked(false);
                    if (!deagle.isChecked()) {
                        setValue(String.valueOf(deagle.getText()), true);
                    }
                }

                if (redDot.isChecked()) {
                    setValue("cRredDot", false);
                    setValue("cPredDot", false);
                    setValue("cGredDot", true);
                    setValue("cBredDot", false);
                    setValue("cWredDot", false);
                    setValue("cYredDot", false);
                    cPrefs.write(String.valueOf(redDot.getText()), GREEN);
                    redDot.setTextColor(Color.parseColor("#ff00ff00"));
                    redDot.setChecked(false);
                    if (!redDot.isChecked()) {
                        setValue(String.valueOf(redDot.getText()), true);
                    }
                }

                if (flare.isChecked()) {
                    setValue("cRflare", false);
                    setValue("cPflare", false);
                    setValue("cGflare", true);
                    setValue("cBflare", false);
                    setValue("cWflare", false);
                    setValue("cYflare", false);
                    cPrefs.write(String.valueOf(flare.getText()), GREEN);
                    flare.setTextColor(Color.parseColor("#ff00ff00"));
                    flare.setChecked(false);
                    if (!flare.isChecked()) {
                        setValue(String.valueOf(flare.getText()), true);
                    }
                }

                if (g36.isChecked()) {
                    setValue("cRg36", false);
                    setValue("cPg36", false);
                    setValue("cGg36", true);
                    setValue("cBg36", false);
                    setValue("cWg36", false);
                    setValue("cYg36", false);
                    cPrefs.write(String.valueOf(g36.getText()), GREEN);
                    g36.setTextColor(Color.parseColor("#ff00ff00"));
                    g36.setChecked(false);
                    if (!g36.isChecked()) {
                        setValue(String.valueOf(g36.getText()), true);
                    }
                }

                if (grenade.isChecked()) {
                    setValue("cRgrenade", false);
                    setValue("cPgrenade", false);
                    setValue("cGgrenade", true);
                    setValue("cBgrenade", false);
                    setValue("cWgrenade", false);
                    setValue("cYgrenade", false);
                    cPrefs.write(String.valueOf(grenade.getText()), GREEN);
                    grenade.setTextColor(Color.parseColor("#ff00ff00"));
                    grenade.setChecked(false);
                    if (!grenade.isChecked()) {
                        setValue(String.valueOf(grenade.getText()), true);
                    }
                }

                if (groza.isChecked()) {
                    setValue("cRgroza", false);
                    setValue("cPgroza", false);
                    setValue("cGgroza", true);
                    setValue("cBgroza", false);
                    setValue("cWgroza", false);
                    setValue("cYgroza", false);
                    cPrefs.write(String.valueOf(groza.getText()), GREEN);
                    groza.setTextColor(Color.parseColor("#ff00ff00"));
                    groza.setChecked(false);
                    if (!groza.isChecked()) {
                        setValue(String.valueOf(groza.getText()), true);
                    }
                }

                if (hollo.isChecked()) {
                    setValue("cRhollo", false);
                    setValue("cPhollo", false);
                    setValue("cGhollo", true);
                    setValue("cBhollo", false);
                    setValue("cWhollo", false);
                    setValue("cYhollo", false);
                    cPrefs.write(String.valueOf(hollo.getText()), GREEN);
                    hollo.setTextColor(Color.parseColor("#ff00ff00"));
                    hollo.setChecked(false);
                    if (!hollo.isChecked()) {
                        setValue(String.valueOf(hollo.getText()), true);
                    }
                }

                if (m1014.isChecked()) {
                    setValue("cRm1014", false);
                    setValue("cPm1014", false);
                    setValue("cGm1014", true);
                    setValue("cBm1014", false);
                    setValue("cWm1014", false);
                    setValue("cYm1014", false);
                    cPrefs.write(String.valueOf(m1014.getText()), GREEN);
                    m1014.setTextColor(Color.parseColor("#ff00ff00"));
                    m1014.setChecked(false);
                    if (!m1014.isChecked()) {
                        setValue(String.valueOf(m1014.getText()), true);
                    }
                }

                if (m16.isChecked()) {
                    setValue("cRm16", false);
                    setValue("cPm16", false);
                    setValue("cGm16", true);
                    setValue("cBm16", false);
                    setValue("cWm16", false);
                    setValue("cYm16", false);
                    cPrefs.write(String.valueOf(m16.getText()), GREEN);
                    m16.setTextColor(Color.parseColor("#ff00ff00"));
                    m16.setChecked(false);
                    if (!m16.isChecked()) {
                        setValue(String.valueOf(m16.getText()), true);
                    }
                }

                if (m24.isChecked()) {
                    setValue("cRm24", false);
                    setValue("cPm24", false);
                    setValue("cGm24", true);
                    setValue("cBm24", false);
                    setValue("cWm24", false);
                    setValue("cYm24", false);
                    cPrefs.write(String.valueOf(m24.getText()), GREEN);
                    m24.setTextColor(Color.parseColor("#ff00ff00"));
                    m24.setChecked(false);
                    if (!m24.isChecked()) {
                        setValue(String.valueOf(m24.getText()), true);
                    }
                }

                if (m249.isChecked()) {
                    setValue("cRm249", false);
                    setValue("cPm249", false);
                    setValue("cGm249", true);
                    setValue("cBm249", false);
                    setValue("cWm249", false);
                    setValue("cYm249", false);
                    cPrefs.write(String.valueOf(m249.getText()), GREEN);
                    m249.setTextColor(Color.parseColor("#ff00ff00"));
                    m249.setChecked(false);
                    if (!m249.isChecked()) {
                        setValue(String.valueOf(m249.getText()), true);
                    }
                }

                if (m4.isChecked()) {
                    setValue("cRm4", false);
                    setValue("cPm4", false);
                    setValue("cGm4", true);
                    setValue("cBm4", false);
                    setValue("cWm4", false);
                    setValue("cYm4", false);
                    cPrefs.write(String.valueOf(m4.getText()), GREEN);
                    m4.setTextColor(Color.parseColor("#ff00ff00"));
                    m4.setChecked(false);
                    if (!m4.isChecked()) {
                        setValue(String.valueOf(m4.getText()), true);
                    }
                }

                if (m762g.isChecked()) {
                    setValue("cRm762", false);
                    setValue("cPm762", false);
                    setValue("cGm762", true);
                    setValue("cBm762", false);
                    setValue("cWm762", false);
                    setValue("cYm762", false);
                    cPrefs.write(String.valueOf(m762g.getText()), GREEN);
                    m762g.setTextColor(Color.parseColor("#ff00ff00"));
                    m762g.setChecked(false);
                    if (!m762g.isChecked()) {
                        setValue(String.valueOf(m762g.getText()), true);
                    }
                }

                if (mk14.isChecked()) {
                    setValue("cRmk14", false);
                    setValue("cPmk14", false);
                    setValue("cGmk14", true);
                    setValue("cBmk14", false);
                    setValue("cWmk14", false);
                    setValue("cYmk14", false);
                    cPrefs.write(String.valueOf(mk14.getText()), GREEN);
                    mk14.setTextColor(Color.parseColor("#ff00ff00"));
                    mk14.setChecked(false);
                    if (!mk14.isChecked()) {
                        setValue(String.valueOf(mk14.getText()), true);
                    }
                }

                if (mk47.isChecked()) {
                    setValue("cRmk47", false);
                    setValue("cPmk47", false);
                    setValue("cGmk47", true);
                    setValue("cBmk47", false);
                    setValue("cWmk47", false);
                    setValue("cYmk47", false);
                    cPrefs.write(String.valueOf(mk47.getText()), GREEN);
                    mk47.setTextColor(Color.parseColor("#ff00ff00"));
                    mk47.setChecked(false);
                    if (!mk47.isChecked()) {
                        setValue(String.valueOf(mk47.getText()), true);
                    }
                }

                if (mp5k.isChecked()) {
                    setValue("cRmp5k", false);
                    setValue("cPmp5k", false);
                    setValue("cGmp5k", true);
                    setValue("cBmp5k", false);
                    setValue("cWmp5k", false);
                    setValue("cYmp5k", false);
                    cPrefs.write(String.valueOf(mp5k.getText()), GREEN);
                    mp5k.setTextColor(Color.parseColor("#ff00ff00"));
                    mp5k.setChecked(false);
                    if (!mp5k.isChecked()) {
                        setValue(String.valueOf(mp5k.getText()), true);
                    }
                }

                if (mini14.isChecked()) {
                    setValue("cRmini14", false);
                    setValue("cPmini14", false);
                    setValue("cGmini14", true);
                    setValue("cBmini14", false);
                    setValue("cWmini14", false);
                    setValue("cYmini14", false);
                    cPrefs.write(String.valueOf(mini14.getText()), GREEN);
                    mini14.setTextColor(Color.parseColor("#ff00ff00"));
                    mini14.setChecked(false);
                    if (!mini14.isChecked()) {
                        setValue(String.valueOf(mini14.getText()), true);
                    }
                }

                if (molotov.isChecked()) {
                    setValue("cRburn", false);
                    setValue("cPburn", false);
                    setValue("cGburn", true);
                    setValue("cBburn", false);
                    setValue("cWburn", false);
                    setValue("cYburn", false);
                    cPrefs.write(String.valueOf(molotov.getText()), GREEN);
                    molotov.setTextColor(Color.parseColor("#ff00ff00"));
                    molotov.setChecked(false);
                    if (!molotov.isChecked()) {
                        setValue(String.valueOf(molotov.getText()), true);
                    }
                }

                if (pp19.isChecked()) {
                    setValue("cRpp19", false);
                    setValue("cPpp19", false);
                    setValue("cGpp19", true);
                    setValue("cBpp19", false);
                    setValue("cWpp19", false);
                    setValue("cYpp19", false);
                    cPrefs.write(String.valueOf(pp19.getText()), GREEN);
                    pp19.setTextColor(Color.parseColor("#ff00ff00"));
                    pp19.setChecked(false);
                    if (!pp19.isChecked()) {
                        setValue(String.valueOf(pp19.getText()), true);
                    }
                }

                if (pan.isChecked()) {
                    setValue("cRpan", false);
                    setValue("cPpan", false);
                    setValue("cGpan", true);
                    setValue("cBpan", false);
                    setValue("cWpan", false);
                    setValue("cYpan", false);
                    cPrefs.write(String.valueOf(pan.getText()), GREEN);
                    pan.setTextColor(Color.parseColor("#ff00ff00"));
                    pan.setChecked(false);
                    if (!pan.isChecked()) {
                        setValue(String.valueOf(pan.getText()), true);
                    }
                }

                if (qbu.isChecked()) {
                    setValue("cRqbu", false);
                    setValue("cPqbu", false);
                    setValue("cGqbu", true);
                    setValue("cBqbu", false);
                    setValue("cWqbu", false);
                    setValue("cYqbu", false);
                    cPrefs.write(String.valueOf(qbu.getText()), GREEN);
                    qbu.setTextColor(Color.parseColor("#ff00ff00"));
                    qbu.setChecked(false);
                    if (!qbu.isChecked()) {
                        setValue(String.valueOf(qbu.getText()), true);
                    }
                }

                if (qbz.isChecked()) {
                    setValue("cRqbz", false);
                    setValue("cPqbz", false);
                    setValue("cGqbz", true);
                    setValue("cBqbz", false);
                    setValue("cWqbz", false);
                    setValue("cYqbz", false);
                    cPrefs.write(String.valueOf(qbz.getText()), GREEN);
                    qbz.setTextColor(Color.parseColor("#ff00ff00"));
                    qbz.setChecked(false);
                    if (!qbz.isChecked()) {
                        setValue(String.valueOf(qbz.getText()), true);
                    }
                }

                if (s12k.isChecked()) {
                    setValue("cRs12k", false);
                    setValue("cPs12k", false);
                    setValue("cGs12k", true);
                    setValue("cBs12k", false);
                    setValue("cWs12k", false);
                    setValue("cYs12k", false);
                    cPrefs.write(String.valueOf(s12k.getText()), GREEN);
                    s12k.setTextColor(Color.parseColor("#ff00ff00"));
                    s12k.setChecked(false);
                    if (!s12k.isChecked()) {
                        setValue(String.valueOf(s12k.getText()), true);
                    }
                }

                if (s1897.isChecked()) {
                    setValue("cRs1897", false);
                    setValue("cPs1897", false);
                    setValue("cGs1897", true);
                    setValue("cBs1897", false);
                    setValue("cWs1897", false);
                    setValue("cYs1897", false);
                    cPrefs.write(String.valueOf(s1897.getText()), GREEN);
                    s1897.setTextColor(Color.parseColor("#ff00ff00"));
                    s1897.setChecked(false);
                    if (!s1897.isChecked()) {
                        setValue(String.valueOf(s1897.getText()), true);
                    }
                }

                if (s686.isChecked()) {
                    setValue("cRs686", false);
                    setValue("cPs686", false);
                    setValue("cGs686", true);
                    setValue("cBs686", false);
                    setValue("cWs686", false);
                    setValue("cYs686", false);
                    cPrefs.write(String.valueOf(s686.getText()), GREEN);
                    s686.setTextColor(Color.parseColor("#ff00ff00"));
                    s686.setChecked(false);
                    if (!s686.isChecked()) {
                        setValue(String.valueOf(s686.getText()), true);
                    }
                }

                if (scarl.isChecked()) {
                    setValue("cRscar", false);
                    setValue("cPscar", false);
                    setValue("cGscar", true);
                    setValue("cBscar", false);
                    setValue("cWscar", false);
                    setValue("cYscar", false);
                    cPrefs.write(String.valueOf(scarl.getText()), GREEN);
                    scarl.setTextColor(Color.parseColor("#ff00ff00"));
                    scarl.setChecked(false);
                    if (!scarl.isChecked()) {
                        setValue(String.valueOf(scarl.getText()), true);
                    }
                }

                if (sks.isChecked()) {
                    setValue("cRsks", false);
                    setValue("cPsks", false);
                    setValue("cGsks", true);
                    setValue("cBsks", false);
                    setValue("cWsks", false);
                    setValue("cYsks", false);
                    cPrefs.write(String.valueOf(sks.getText()), GREEN);
                    sks.setTextColor(Color.parseColor("#ff00ff00"));
                    sks.setChecked(false);
                    if (!sks.isChecked()) {
                        setValue(String.valueOf(sks.getText()), true);
                    }
                }

                if (slr.isChecked()) {
                    setValue("cRslr", false);
                    setValue("cPslr", false);
                    setValue("cGslr", true);
                    setValue("cBslr", false);
                    setValue("cWslr", false);
                    setValue("cYslr", false);
                    cPrefs.write(String.valueOf(slr.getText()), GREEN);
                    slr.setTextColor(Color.parseColor("#ff00ff00"));
                    slr.setChecked(false);
                    if (!slr.isChecked()) {
                        setValue(String.valueOf(slr.getText()), true);
                    }
                }

                if (stunt.isChecked()) {
                    setValue("cRflash", false);
                    setValue("cPflash", false);
                    setValue("cGflash", true);
                    setValue("cBflash", false);
                    setValue("cWflash", false);
                    setValue("cYflash", false);
                    cPrefs.write(String.valueOf(stunt.getText()), GREEN);
                    stunt.setTextColor(Color.parseColor("#ff00ff00"));
                    stunt.setChecked(false);
                    if (!stunt.isChecked()) {
                        setValue(String.valueOf(stunt.getText()), true);
                    }
                }

                if (tommy.isChecked()) {
                    setValue("cRthomp", false);
                    setValue("cPthomp", false);
                    setValue("cGthomp", true);
                    setValue("cBthomp", false);
                    setValue("cWthomp", false);
                    setValue("cYthomp", false);
                    cPrefs.write(String.valueOf(tommy.getText()), GREEN);
                    tommy.setTextColor(Color.parseColor("#ff00ff00"));
                    tommy.setChecked(false);
                    if (!tommy.isChecked()) {
                        setValue(String.valueOf(tommy.getText()), true);
                    }
                }

                if (ump45.isChecked()) {
                    setValue("cRump45", false);
                    setValue("cPump45", false);
                    setValue("cGump45", true);
                    setValue("cBump45", false);
                    setValue("cWump45", false);
                    setValue("cYump45", false);
                    cPrefs.write(String.valueOf(ump45.getText()), GREEN);
                    ump45.setTextColor(Color.parseColor("#ff00ff00"));
                    ump45.setChecked(false);
                    if (!ump45.isChecked()) {
                        setValue(String.valueOf(ump45.getText()), true);
                    }
                }

                if (uzi.isChecked()) {
                    setValue("cRuzi", false);
                    setValue("cPuzi", false);
                    setValue("cGuzi", true);
                    setValue("cBuzi", false);
                    setValue("cWuzi", false);
                    setValue("cYuzi", false);
                    cPrefs.write(String.valueOf(uzi.getText()), GREEN);
                    uzi.setTextColor(Color.parseColor("#ff00ff00"));
                    uzi.setChecked(false);
                    if (!uzi.isChecked()) {
                        setValue(String.valueOf(uzi.getText()), true);
                    }
                }

                if (vss.isChecked()) {
                    setValue("cRvss", false);
                    setValue("cPvss", false);
                    setValue("cGvss", true);
                    setValue("cBvss", false);
                    setValue("cWvss", false);
                    setValue("cYvss", false);
                    cPrefs.write(String.valueOf(vss.getText()), GREEN);
                    vss.setTextColor(Color.parseColor("#ff00ff00"));
                    vss.setChecked(false);
                    if (!vss.isChecked()) {
                        setValue(String.valueOf(vss.getText()), true);
                    }
                }

                if (vector.isChecked()) {
                    setValue("cRvect", false);
                    setValue("cPvect", false);
                    setValue("cGvect", true);
                    setValue("cBvect", false);
                    setValue("cWvect", false);
                    setValue("cYvect", false);
                    cPrefs.write(String.valueOf(vector.getText()), GREEN);
                    vector.setTextColor(Color.parseColor("#ff00ff00"));
                    vector.setChecked(false);
                    if (!vector.isChecked()) {
                        setValue(String.valueOf(vector.getText()), true);
                    }
                }

                if (win94.isChecked()) {
                    setValue("cRwinchester", false);
                    setValue("cPwinchester", false);
                    setValue("cGwinchester", true);
                    setValue("cBwinchester", false);
                    setValue("cWwinchester", false);
                    setValue("cYwinchester", false);
                    cPrefs.write(String.valueOf(win94.getText()), GREEN);
                    win94.setTextColor(Color.parseColor("#ff00ff00"));
                    win94.setChecked(false);
                    if (!win94.isChecked()) {
                        setValue(String.valueOf(win94.getText()), true);
                    }
                }
            }
        }); //ItemColorGreen

        clrBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dacia.isChecked()) {
                    setValue("clrRedDacia", false);
                    setValue("clrPinkDacia", false);
                    setValue("clrGreenDacia", false);
                    setValue("clrBlueDacia", true);
                    setValue("clrWhiteDacia", false);
                    setValue("clrYellowDacia", false);
                    cPrefs.write(String.valueOf(Dacia.getText()), BLUE);
                    Dacia.setTextColor(Color.parseColor("#ff00ffff"));
                    Dacia.setChecked(false);
                    if (!Dacia.isChecked()) {
                        setValue(String.valueOf(Dacia.getText()), true);
                    }
                }
                if (UAZ.isChecked()) {
                    setValue("clrRedUAZ", false);
                    setValue("clrPinkUAZ", false);
                    setValue("clrGreenUAZ", false);
                    setValue("clrBlueUAZ", true);
                    setValue("clrWhiteUAZ", false);
                    setValue("clrYellowUAZ", false);
                    cPrefs.write(String.valueOf(UAZ.getText()), BLUE);
                    UAZ.setTextColor(Color.parseColor("#ff00ffff"));
                    UAZ.setChecked(false);
                    if (!UAZ.isChecked()) {
                        setValue(String.valueOf(UAZ.getText()), true);
                    }
                }
                if (Buggy.isChecked()) {
                    setValue("clrRedBuggy", false);
                    setValue("clrPinkBuggy", false);
                    setValue("clrGreenBuggy", false);
                    setValue("clrBlueBuggy", true);
                    setValue("clrWhiteBuggy", false);
                    setValue("clrYellowBuggy", false);
                    cPrefs.write(String.valueOf(Buggy.getText()), BLUE);
                    Buggy.setTextColor(Color.parseColor("#ff00ffff"));
                    Buggy.setChecked(false);
                    if (!Buggy.isChecked()) {
                        setValue(String.valueOf(Buggy.getText()), true);
                    }
                }
                if (Bike.isChecked()) {
                    setValue("clrRedBike", false);
                    setValue("clrPinkBike", false);
                    setValue("clrGreenBike", false);
                    setValue("clrBlueBike", true);
                    setValue("clrWhiteBike", false);
                    setValue("clrYellowBike", false);
                    cPrefs.write(String.valueOf(Bike.getText()), BLUE);
                    Bike.setTextColor(Color.parseColor("#ff00ffff"));
                    Bike.setChecked(false);
                    if (!Bike.isChecked()) {
                        setValue(String.valueOf(Bike.getText()), true);
                    }
                }
                if (Jet.isChecked()) {
                    setValue("clrRedAquaRail", false);
                    setValue("clrPinkAquaRail", false);
                    setValue("clrGreenAquaRail", false);
                    setValue("clrBlueAquaRail", true);
                    setValue("clrWhiteAquaRail", false);
                    setValue("clrYellowAquaRail", false);
                    cPrefs.write(String.valueOf(Jet.getText()), BLUE);
                    Jet.setTextColor(Color.parseColor("#ff00ffff"));
                    Jet.setChecked(false);
                    if (!Jet.isChecked()) {
                        setValue(String.valueOf(Jet.getText()), true);
                    }
                }
                if (Boat.isChecked()) {
                    setValue("clrRedBoat", false);
                    setValue("clrPinkBoat", false);
                    setValue("clrGreenBoat", false);
                    setValue("clrBlueBoat", true);
                    setValue("clrWhiteBoat", false);
                    setValue("clrYellowBoat", false);
                    cPrefs.write(String.valueOf(Boat.getText()), BLUE);
                    Boat.setTextColor(Color.parseColor("#ff00ffff"));
                    Boat.setChecked(false);
                    if (!Boat.isChecked()) {
                        setValue(String.valueOf(Boat.getText()), true);
                    }
                }
                if (Bus.isChecked()) {
                    setValue("clrRedBus", false);
                    setValue("clrPinkBus", false);
                    setValue("clrGreenBus", false);
                    setValue("clrBlueBus", true);
                    setValue("clrWhiteBus", false);
                    setValue("clrYellowBus", false);
                    cPrefs.write(String.valueOf(Bus.getText()), BLUE);
                    Bus.setTextColor(Color.parseColor("#ff00ffff"));
                    Bus.setChecked(false);
                    if (!Bus.isChecked()) {
                        setValue(String.valueOf(Bus.getText()), true);
                    }
                }
                if (Mirado.isChecked()) {
                    setValue("clrRedMirado", false);
                    setValue("clrPinkMirado", false);
                    setValue("clrGreenMirado", false);
                    setValue("clrBlueMirado", true);
                    setValue("clrWhiteMirado", false);
                    setValue("clrYellowMirado", false);
                    cPrefs.write(String.valueOf(Mirado.getText()), BLUE);
                    Mirado.setTextColor(Color.parseColor("#ff00ffff"));
                    Mirado.setChecked(false);
                    if (!Mirado.isChecked()) {
                        setValue(String.valueOf(Mirado.getText()), true);
                    }
                }
                if (Scooter.isChecked()) {
                    setValue("clrRedScooter", false);
                    setValue("clrPinkScooter", false);
                    setValue("clrGreenScooter", false);
                    setValue("clrBlueScooter", true);
                    setValue("clrWhiteScooter", false);
                    setValue("clrYellowScooter", false);
                    cPrefs.write(String.valueOf(Scooter.getText()), BLUE);
                    Scooter.setTextColor(Color.parseColor("#ff00ffff"));
                    Scooter.setChecked(false);
                    if (!Scooter.isChecked()) {
                        setValue(String.valueOf(Scooter.getText()), true);
                    }
                }
                if (Rony.isChecked()) {
                    setValue("clrRedRony", false);
                    setValue("clrPinkRony", false);
                    setValue("clrGreenRony", false);
                    setValue("clrBlueRony", true);
                    setValue("clrWhiteRony", false);
                    setValue("clrYellowRony", false);
                    cPrefs.write(String.valueOf(Rony.getText()), BLUE);
                    Rony.setTextColor(Color.parseColor("#ff00ffff"));
                    Rony.setChecked(false);
                    if (!Rony.isChecked()) {
                        setValue(String.valueOf(Rony.getText()), true);
                    }
                }
                if (Snowbike.isChecked()) {
                    setValue("clrRedSnowbike", false);
                    setValue("clrPinkSnowbike", false);
                    setValue("clrGreenSnowbike", false);
                    setValue("clrBlueSnowbike", true);
                    setValue("clrWhiteSnowbike", false);
                    setValue("clrYellowSnowbike", false);
                    cPrefs.write(String.valueOf(Snowbike.getText()), BLUE);
                    Snowbike.setTextColor(Color.parseColor("#ff00ffff"));
                    Snowbike.setChecked(false);
                    if (!Snowbike.isChecked()) {
                        setValue(String.valueOf(Snowbike.getText()), true);
                    }
                }
                if (Snowmobile.isChecked()) {
                    setValue("clrRedSnowmobile", false);
                    setValue("clrPinkSnowmobile", false);
                    setValue("clrGreenSnowmobile", false);
                    setValue("clrBlueSnowmobile", true);
                    setValue("clrWhiteSnowmobile", false);
                    cPrefs.write(String.valueOf(Snowmobile.getText()), BLUE);
                    setValue("clrYellowSnowmobile", false);
                    Snowmobile.setTextColor(Color.parseColor("#ff00ffff"));
                    Snowmobile.setChecked(false);
                    if (!Snowmobile.isChecked()) {
                        setValue(String.valueOf(Snowmobile.getText()), true);
                    }
                }
                if (Tempo.isChecked()) {
                    setValue("clrRedTuk", false);
                    setValue("clrPinkTuk", false);
                    setValue("clrGreenTuk", false);
                    setValue("clrBlueTuk", true);
                    setValue("clrWhiteTuk", false);
                    setValue("clrYellowTuk", false);
                    cPrefs.write(String.valueOf(Tempo.getText()), BLUE);
                    Tempo.setTextColor(Color.parseColor("#ff00ffff"));
                    Tempo.setChecked(false);
                    if (!Tempo.isChecked()) {
                        setValue(String.valueOf(Tempo.getText()), true);
                    }
                }
                if (Truck.isChecked()) {
                    setValue("clrRedTruck", false);
                    setValue("clrPinkTruck", false);
                    setValue("clrGreenTruck", false);
                    setValue("clrBlueTruck", true);
                    setValue("clrWhiteTruck", false);
                    setValue("clrYellowTruck", false);
                    cPrefs.write(String.valueOf(Truck.getText()), BLUE);
                    Truck.setTextColor(Color.parseColor("#ff00ffff"));
                    Truck.setChecked(false);
                    if (!Truck.isChecked()) {
                        setValue(String.valueOf(Truck.getText()), true);
                    }
                }
                if (BRDM.isChecked()) {
                    setValue("clrRedBRDM", false);
                    setValue("clrPinkBRDM", false);
                    setValue("clrGreenBRDM", false);
                    setValue("clrBlueBRDM", true);
                    setValue("clrWhiteBRDM", false);
                    setValue("clrYellowBRDM", false);
                    cPrefs.write(String.valueOf(BRDM.getText()), BLUE);
                    BRDM.setTextColor(Color.parseColor("#ff00ffff"));
                    BRDM.setChecked(false);
                    if (!BRDM.isChecked()) {
                        setValue(String.valueOf(BRDM.getText()), true);
                    }
                }
                if (LadaNiva.isChecked()) {
                    setValue("clrRedLadaNiva", false);
                    setValue("clrPinkLadaNiva", false);
                    setValue("clrGreenLadaNiva", false);
                    setValue("clrBlueLadaNiva", true);
                    setValue("clrWhiteLadaNiva", false);
                    setValue("clrYellowLadaNiva", false);
                    cPrefs.write(String.valueOf(LadaNiva.getText()), BLUE);
                    LadaNiva.setTextColor(Color.parseColor("#ff00ffff"));
                    LadaNiva.setChecked(false);
                    if (!LadaNiva.isChecked()) {
                        setValue(String.valueOf(LadaNiva.getText()), true);
                    }
                }
                if (MonsterTruck.isChecked()) {
                    setValue("clrRedMonster", false);
                    setValue("clrPinkMonster", false);
                    setValue("clrGreenMonster", false);
                    setValue("clrBlueMonster", true);
                    setValue("clrWhiteMonster", false);
                    setValue("clrYellowMonster", false);
                    cPrefs.write(String.valueOf(MonsterTruck.getText()), BLUE);
                    MonsterTruck.setTextColor(Color.parseColor("#ff00ffff"));
                    MonsterTruck.setChecked(false);
                    if (!MonsterTruck.isChecked()) {
                        setValue(String.valueOf(MonsterTruck.getText()), true);
                    }
                }
                if (CoupeRB.isChecked()) {
                    setValue("clrRedCoupeRB", false);
                    setValue("clrPinkCoupeRB", false);
                    setValue("clrGreenCoupeRB", false);
                    setValue("clrBlueCoupeRB", true);
                    setValue("clrWhiteCoupeRB", false);
                    setValue("clrYellowCoupeRB", false);
                    cPrefs.write(String.valueOf(CoupeRB.getText()), BLUE);
                    CoupeRB.setTextColor(Color.parseColor("#ff00ffff"));
                    CoupeRB.setChecked(false);
                    if (!CoupeRB.isChecked()) {
                        setValue(String.valueOf(CoupeRB.getText()), true);
                    }
                }
                if (UTV.isChecked()) {
                    setValue("clrRedUTV", false);
                    setValue("clrPinkUTV", false);
                    setValue("clrGreenUTV", false);
                    setValue("clrBlueUTV", true);
                    setValue("clrWhiteUTV", false);
                    setValue("clrYellowUTV", false);
                    cPrefs.write(String.valueOf(UTV.getText()), BLUE);
                    UTV.setTextColor(Color.parseColor("#ff00ffff"));
                    UTV.setChecked(false);
                    if (!UTV.isChecked()) {
                        setValue(String.valueOf(UTV.getText()), true);
                    }
                }

                if (am300.isChecked()) {
                    setValue("cR300mm", false);
                    setValue("cP300mm", false);
                    setValue("cG300mm", false);
                    setValue("cB300mm", true);
                    setValue("cW300mm", false);
                    setValue("cY300mm", false);
                    cPrefs.write(String.valueOf(am300.getText()), BLUE);
                    am300.setTextColor(Color.parseColor("#ff00ffff"));
                    am300.setChecked(false);
                    if (!am300.isChecked()) {
                        setValue(String.valueOf(am300.getText()), true);
                    }
                }

                if (am45.isChecked()) {
                    setValue("cRam45", false);
                    setValue("cPam45", false);
                    setValue("cGam45", false);
                    setValue("cBam45", true);
                    setValue("cWam45", false);
                    setValue("cYam45", false);
                    cPrefs.write(String.valueOf(am45.getText()), BLUE);
                    am45.setTextColor(Color.parseColor("#ff00ffff"));
                    am45.setChecked(false);
                    if (!am45.isChecked()) {
                        setValue(String.valueOf(am45.getText()), true);
                    }
                }

                if (am12g.isChecked()) {
                    setValue("cRam12g", false);
                    setValue("cPam12g", false);
                    setValue("cGam12g", false);
                    setValue("cBam12g", true);
                    setValue("cWam12g", false);
                    setValue("cYam12g", false);
                    cPrefs.write(String.valueOf(am12g.getText()), BLUE);
                    am12g.setTextColor(Color.parseColor("#ff00ffff"));
                    am12g.setChecked(false);
                    if (!am12g.isChecked()) {
                        setValue(String.valueOf(am12g.getText()), true);
                    }
                }

                if (am556.isChecked()) {
                    setValue("cRam556", false);
                    setValue("cPam556", false);
                    setValue("cGam556", false);
                    setValue("cBam556", true);
                    setValue("cWam556", false);
                    setValue("cYam556", false);
                    cPrefs.write(String.valueOf(am556.getText()), BLUE);
                    am556.setTextColor(Color.parseColor("#ff00ffff"));
                    am556.setChecked(false);
                    if (!am556.isChecked()) {
                        setValue(String.valueOf(am556.getText()), true);
                    }
                }

                if (am762.isChecked()) {
                    setValue("cRam762", false);
                    setValue("cPam762", false);
                    setValue("cGam762", false);
                    setValue("cBam762", true);
                    setValue("cWam762", false);
                    setValue("cYam762", false);
                    cPrefs.write(String.valueOf(am762.getText()), BLUE);
                    am762.setTextColor(Color.parseColor("#ff00ffff"));
                    am762.setChecked(false);
                    if (!am762.isChecked()) {
                        setValue(String.valueOf(am762.getText()), true);
                    }
                }

                if (am9mm.isChecked()) {
                    setValue("cRam9mm", false);
                    setValue("cPam9mm", false);
                    setValue("cGam9mm", false);
                    setValue("cBam9mm", true);
                    setValue("cWam9mm", false);
                    setValue("cYam9mm", false);
                    cPrefs.write(String.valueOf(am9mm.getText()), BLUE);
                    am9mm.setTextColor(Color.parseColor("#ff00ffff"));
                    am9mm.setChecked(false);
                    if (!am9mm.isChecked()) {
                        setValue(String.valueOf(am9mm.getText()), true);
                    }
                }

                if (armorLvl2.isChecked()) {
                    setValue("cRar2", false);
                    setValue("cPar2", false);
                    setValue("cGar2", false);
                    setValue("cBar2", true);
                    setValue("cWar2", false);
                    setValue("cYar2", false);
                    cPrefs.write(String.valueOf(armorLvl2.getText()), BLUE);
                    armorLvl2.setTextColor(Color.parseColor("#ff00ffff"));
                    armorLvl2.setChecked(false);
                    if (!armorLvl2.isChecked()) {
                        setValue(String.valueOf(armorLvl2.getText()), true);
                    }
                }

                if (armorLvl3.isChecked()) {
                    setValue("cRar3", false);
                    setValue("cPar3", false);
                    setValue("cGar3", false);
                    setValue("cBar3", true);
                    setValue("cWar3", false);
                    setValue("cYar3", false);
                    cPrefs.write(String.valueOf(armorLvl3.getText()), BLUE);
                    armorLvl3.setTextColor(Color.parseColor("#ff00ffff"));
                    armorLvl3.setChecked(false);
                    if (!armorLvl3.isChecked()) {
                        setValue(String.valueOf(armorLvl3.getText()), true);
                    }
                }

                if (helmetLvl2.isChecked()) {
                    setValue("cRhel2", false);
                    setValue("cPhel2", false);
                    setValue("cGhel2", false);
                    setValue("cBhel2", true);
                    setValue("cWhel2", false);
                    setValue("cYhel2", false);
                    cPrefs.write(String.valueOf(helmetLvl2.getText()), BLUE);
                    helmetLvl2.setTextColor(Color.parseColor("#ff00ffff"));
                    helmetLvl2.setChecked(false);
                    if (!helmetLvl2.isChecked()) {
                        setValue(String.valueOf(helmetLvl2.getText()), true);
                    }
                }

                if (helmetLvl3.isChecked()) {
                    setValue("cRhel3", false);
                    setValue("cPhel3", false);
                    setValue("cGhel3", false);
                    setValue("cBhel3", true);
                    setValue("cWhel3", false);
                    setValue("cYhel3", false);
                    cPrefs.write(String.valueOf(helmetLvl3.getText()), BLUE);
                    helmetLvl3.setTextColor(Color.parseColor("#ff00ffff"));
                    helmetLvl3.setChecked(false);
                    if (!helmetLvl3.isChecked()) {
                        setValue(String.valueOf(helmetLvl3.getText()), true);
                    }
                }

                if (bandage.isChecked()) {
                    setValue("cRban", false);
                    setValue("cPban", false);
                    setValue("cGban", false);
                    setValue("cBban", true);
                    setValue("cWban", false);
                    setValue("cYban", false);
                    cPrefs.write(String.valueOf(bandage.getText()), BLUE);
                    bandage.setTextColor(Color.parseColor("#ff00ffff"));
                    bandage.setChecked(false);
                    if (!bandage.isChecked()) {
                        setValue(String.valueOf(bandage.getText()), true);
                    }
                }

                if (drink.isChecked()) {
                    setValue("cRdrink", false);
                    setValue("cPdrink", false);
                    setValue("cGdrink", false);
                    setValue("cBdrink", true);
                    setValue("cWdrink", false);
                    setValue("cYdrink", false);
                    cPrefs.write(String.valueOf(drink.getText()), BLUE);
                    drink.setTextColor(Color.parseColor("#ff00ffff"));
                    drink.setChecked(false);
                    if (!drink.isChecked()) {
                        setValue(String.valueOf(drink.getText()), true);
                    }
                }

                if (firstAir.isChecked()) {
                    setValue("cRfa", false);
                    setValue("cPfa", false);
                    setValue("cGfa", false);
                    setValue("cBfa", true);
                    setValue("cWfa", false);
                    setValue("cYfa", false);
                    cPrefs.write(String.valueOf(firstAir.getText()), BLUE);
                    firstAir.setTextColor(Color.parseColor("#ff00ffff"));
                    firstAir.setChecked(false);
                    if (!firstAir.isChecked()) {
                        setValue(String.valueOf(firstAir.getText()), true);
                    }
                }

                if (injection.isChecked()) {
                    setValue("cRin", false);
                    setValue("cPin", false);
                    setValue("cGin", false);
                    setValue("cBin", true);
                    setValue("cWin", false);
                    setValue("cYin", false);
                    cPrefs.write(String.valueOf(injection.getText()), BLUE);
                    injection.setTextColor(Color.parseColor("#ff00ffff"));
                    injection.setChecked(false);
                    if (!injection.isChecked()) {
                        setValue(String.valueOf(injection.getText()), true);
                    }
                }

                if (pills.isChecked()) {
                    setValue("cRpills", false);
                    setValue("cPpills", false);
                    setValue("cGpills", false);
                    setValue("cBpills", true);
                    setValue("cWpills", false);
                    setValue("cYpills", false);
                    cPrefs.write(String.valueOf(pills.getText()), BLUE);
                    pills.setTextColor(Color.parseColor("#ff00ffff"));
                    pills.setChecked(false);
                    if (!pills.isChecked()) {
                        setValue(String.valueOf(pills.getText()), true);
                    }
                }

                //Misk
                if (airCraft.isChecked()) {
                    setValue("cRac", false);
                    setValue("cPac", false);
                    setValue("cGac", false);
                    setValue("cBac", true);
                    setValue("cWac", false);
                    setValue("cYac", false);
                    cPrefs.write(String.valueOf(airCraft.getText()), BLUE);
                    airCraft.setTextColor(Color.parseColor("#ff00ffff"));
                    airCraft.setChecked(false);
                    if (!airCraft.isChecked()) {
                        setValue(String.valueOf(airCraft.getText()), true);
                    }
                }

                if (airDrop.isChecked()) {
                    setValue("cRairD", false);
                    setValue("cPairD", false);
                    setValue("cGairD", false);
                    setValue("cBairD", true);
                    setValue("cWairD", false);
                    setValue("cYairD", false);
                    cPrefs.write(String.valueOf(airDrop.getText()), BLUE);
                    airDrop.setTextColor(Color.parseColor("#ff00ffff"));
                    airDrop.setChecked(false);
                    if (!airDrop.isChecked()) {
                        setValue(String.valueOf(airDrop.getText()), true);
                    }
                }

                if (bag2.isChecked()) {
                    setValue("cRb2", false);
                    setValue("cPb2", false);
                    setValue("cGb2", false);
                    setValue("cBb2", true);
                    setValue("cWb2", false);
                    setValue("cYb2", false);
                    cPrefs.write(String.valueOf(bag2.getText()), BLUE);
                    bag2.setTextColor(Color.parseColor("#ff00ffff"));
                    bag2.setChecked(false);
                    if (!bag2.isChecked()) {
                        setValue(String.valueOf(bag2.getText()), true);
                    }
                }

                if (bag3.isChecked()) {
                    setValue("cRb3", false);
                    setValue("cPb3", false);
                    setValue("cGb3", false);
                    setValue("cBb3", true);
                    setValue("cWb3", false);
                    setValue("cYb3", false);
                    cPrefs.write(String.valueOf(bag3.getText()), BLUE);
                    bag3.setTextColor(Color.parseColor("#ff00ffff"));
                    bag3.setChecked(false);
                    if (!bag3.isChecked()) {
                        setValue(String.valueOf(bag3.getText()), true);
                    }
                }

                if (compensator.isChecked()) {
                    setValue("cRcomp", false);
                    setValue("cPcomp", false);
                    setValue("cGcomp", false);
                    setValue("cBcomp", true);
                    setValue("cWcomp", false);
                    setValue("cYcomp", false);
                    cPrefs.write(String.valueOf(compensator.getText()), BLUE);
                    compensator.setTextColor(Color.parseColor("#ff00ffff"));
                    compensator.setChecked(false);
                    if (!compensator.isChecked()) {
                        setValue(String.valueOf(compensator.getText()), true);
                    }
                }

                if (loot.isChecked()) {
                    setValue("cRloot", false);
                    setValue("cPloot", false);
                    setValue("cGloot", false);
                    setValue("cBloot", true);
                    setValue("cWloot", false);
                    setValue("cYloot", false);
                    cPrefs.write(String.valueOf(loot.getText()), BLUE);
                    loot.setTextColor(Color.parseColor("#ff00ffff"));
                    loot.setChecked(false);
                    if (!loot.isChecked()) {
                        setValue(String.valueOf(loot.getText()), true);
                    }
                }

                if (supressor.isChecked()) {
                    setValue("cRsupp", false);
                    setValue("cPsupp", false);
                    setValue("cGsupp", false);
                    setValue("cBsupp", true);
                    setValue("cWsupp", false);
                    setValue("cYsupp", false);
                    cPrefs.write(String.valueOf(supressor.getText()), BLUE);
                    supressor.setTextColor(Color.parseColor("#ff00ffff"));
                    supressor.setChecked(false);
                    if (!supressor.isChecked()) {
                        setValue(String.valueOf(supressor.getText()), true);
                    }
                }

                //Weapons
                if (x2.isChecked()) {
                    setValue("cRx2", false);
                    setValue("cPx2", false);
                    setValue("cGx2", false);
                    setValue("cBx2", true);
                    setValue("cWx2", false);
                    setValue("cYx2", false);
                    cPrefs.write(String.valueOf(x2.getText()), BLUE);
                    x2.setTextColor(Color.parseColor("#ff00ffff"));
                    x2.setChecked(false);
                    if (!x2.isChecked()) {
                        setValue(String.valueOf(x2.getText()), true);
                    }
                }

                if (x3.isChecked()) {
                    setValue("cRx3", false);
                    setValue("cPx3", false);
                    setValue("cGx3", false);
                    setValue("cBx3", true);
                    setValue("cWx3", false);
                    setValue("cYx3", false);
                    cPrefs.write(String.valueOf(x3.getText()), BLUE);
                    x3.setTextColor(Color.parseColor("#ff00ffff"));
                    x3.setChecked(false);
                    if (!x3.isChecked()) {
                        setValue(String.valueOf(x3.getText()), true);
                    }
                }

                if (x4.isChecked()) {
                    setValue("cRx4", false);
                    setValue("cPx4", false);
                    setValue("cGx4", false);
                    setValue("cBx4", true);
                    setValue("cWx4", false);
                    setValue("cYx4", false);
                    cPrefs.write(String.valueOf(x4.getText()), BLUE);
                    x4.setTextColor(Color.parseColor("#ff00ffff"));
                    x4.setChecked(false);
                    if (!x4.isChecked()) {
                        setValue(String.valueOf(x4.getText()), true);
                    }
                }

                if (x6.isChecked()) {
                    setValue("cRx6", false);
                    setValue("cPx6", false);
                    setValue("cGx6", false);
                    setValue("cBx6", true);
                    setValue("cWx6", false);
                    setValue("cYx6", false);
                    cPrefs.write(String.valueOf(x6.getText()), BLUE);
                    x6.setTextColor(Color.parseColor("#ff00ffff"));
                    x6.setChecked(false);
                    if (!x6.isChecked()) {
                        setValue(String.valueOf(x6.getText()), true);
                    }
                }

                if (x8.isChecked()) {
                    setValue("cRx8", false);
                    setValue("cPx8", false);
                    setValue("cGx8", false);
                    setValue("cBx8", true);
                    setValue("cWx8", false);
                    setValue("cYx8", false);
                    cPrefs.write(String.valueOf(x8.getText()), BLUE);
                    x8.setTextColor(Color.parseColor("#ff00ffff"));
                    x8.setChecked(false);
                    if (!x8.isChecked()) {
                        setValue(String.valueOf(x8.getText()), true);
                    }
                }

                //real
                if (k98.isChecked()) {
                    setValue("cRk98", false);
                    setValue("cPk98", false);
                    setValue("cGk98", false);
                    setValue("cBk98", true);
                    setValue("cWk98", false);
                    setValue("cYk98", false);
                    cPrefs.write(String.valueOf(k98.getText()), BLUE);
                    k98.setTextColor(Color.parseColor("#ff00ffff"));
                    k98.setChecked(false);
                    if (!k98.isChecked()) {
                        setValue(String.valueOf(k98.getText()), true);
                    }
                }

                if (akm.isChecked()) {
                    setValue("cRakm", false);
                    setValue("cPakm", false);
                    setValue("cGakm", false);
                    setValue("cBakm", true);
                    setValue("cWakm", false);
                    setValue("cYakm", false);
                    cPrefs.write(String.valueOf(akm.getText()), BLUE);
                    akm.setTextColor(Color.parseColor("#ff00ffff"));
                    akm.setChecked(false);
                    if (!akm.isChecked()) {
                        setValue(String.valueOf(akm.getText()), true);
                    }
                }

                if (aug.isChecked()) {
                    setValue("cRaug", false);
                    setValue("cPaug", false);
                    setValue("cGaug", false);
                    setValue("cBaug", true);
                    setValue("cWaug", false);
                    setValue("cYaug", false);
                    cPrefs.write(String.valueOf(aug.getText()), BLUE);
                    aug.setTextColor(Color.parseColor("#ff00ffff"));
                    aug.setChecked(false);
                    if (!aug.isChecked()) {
                        setValue(String.valueOf(aug.getText()), true);
                    }
                }

                if (awm.isChecked()) {
                    setValue("cRawm", false);
                    setValue("cPawm", false);
                    setValue("cGawm", false);
                    setValue("cBawm", true);
                    setValue("cWawm", false);
                    setValue("cYawm", false);
                    cPrefs.write(String.valueOf(awm.getText()), BLUE);
                    awm.setTextColor(Color.parseColor("#ff00ffff"));
                    awm.setChecked(false);
                    if (!awm.isChecked()) {
                        setValue(String.valueOf(awm.getText()), true);
                    }
                }

                if (bow.isChecked()) {
                    setValue("cRbow", false);
                    setValue("cPbow", false);
                    setValue("cGbow", false);
                    setValue("cBbow", true);
                    setValue("cWbow", false);
                    setValue("cYbow", false);
                    cPrefs.write(String.valueOf(bow.getText()), BLUE);
                    bow.setTextColor(Color.parseColor("#ff00ffff"));
                    bow.setChecked(false);
                    if (!bow.isChecked()) {
                        setValue(String.valueOf(bow.getText()), true);
                    }
                }

                if (dp12.isChecked()) {
                    setValue("cRdp12", false);
                    setValue("cPdp12", false);
                    setValue("cGdp12", false);
                    setValue("cBdp12", true);
                    setValue("cWdp12", false);
                    setValue("cYdp12", false);
                    cPrefs.write(String.valueOf(dp12.getText()), BLUE);
                    dp12.setTextColor(Color.parseColor("#ff00ffff"));
                    dp12.setChecked(false);
                    if (!dp12.isChecked()) {
                        setValue(String.valueOf(dp12.getText()), true);
                    }
                }

                if (dp28.isChecked()) {
                    setValue("cRdp28", false);
                    setValue("cPdp28", false);
                    setValue("cGdp28", false);
                    setValue("cBdp28", true);
                    setValue("cWdp28", false);
                    setValue("cYdp28", false);
                    cPrefs.write(String.valueOf(dp28.getText()), BLUE);
                    dp28.setTextColor(Color.parseColor("#ff00ffff"));
                    dp28.setChecked(false);
                    if (!dp28.isChecked()) {
                        setValue(String.valueOf(dp28.getText()), true);
                    }
                }

                if (deagle.isChecked()) {
                    setValue("cRdeg", false);
                    setValue("cPdeg", false);
                    setValue("cGdeg", false);
                    setValue("cBdeg", true);
                    setValue("cWdeg", false);
                    setValue("cYdeg", false);
                    cPrefs.write(String.valueOf(deagle.getText()), BLUE);
                    deagle.setTextColor(Color.parseColor("#ff00ffff"));
                    deagle.setChecked(false);
                    if (!deagle.isChecked()) {
                        setValue(String.valueOf(deagle.getText()), true);
                    }
                }

                if (redDot.isChecked()) {
                    setValue("cRredDot", false);
                    setValue("cPredDot", false);
                    setValue("cGredDot", false);
                    setValue("cBredDot", true);
                    setValue("cWredDot", false);
                    setValue("cYredDot", false);
                    cPrefs.write(String.valueOf(redDot.getText()), BLUE);
                    redDot.setTextColor(Color.parseColor("#ff00ffff"));
                    redDot.setChecked(false);
                    if (!redDot.isChecked()) {
                        setValue(String.valueOf(redDot.getText()), true);
                    }
                }

                if (flare.isChecked()) {
                    setValue("cRflare", false);
                    setValue("cPflare", false);
                    setValue("cGflare", false);
                    setValue("cBflare", true);
                    setValue("cWflare", false);
                    setValue("cYflare", false);
                    cPrefs.write(String.valueOf(flare.getText()), BLUE);
                    flare.setTextColor(Color.parseColor("#ff00ffff"));
                    flare.setChecked(false);
                    if (!flare.isChecked()) {
                        setValue(String.valueOf(flare.getText()), true);
                    }
                }

                if (g36.isChecked()) {
                    setValue("cRg36", false);
                    setValue("cPg36", false);
                    setValue("cGg36", false);
                    setValue("cBg36", true);
                    setValue("cWg36", false);
                    setValue("cYg36", false);
                    cPrefs.write(String.valueOf(g36.getText()), BLUE);
                    g36.setTextColor(Color.parseColor("#ff00ffff"));
                    g36.setChecked(false);
                    if (!g36.isChecked()) {
                        setValue(String.valueOf(g36.getText()), true);
                    }
                }

                if (grenade.isChecked()) {
                    setValue("cRgrenade", false);
                    setValue("cPgrenade", false);
                    setValue("cGgrenade", false);
                    setValue("cBgrenade", true);
                    setValue("cWgrenade", false);
                    setValue("cYgrenade", false);
                    cPrefs.write(String.valueOf(grenade.getText()), BLUE);
                    grenade.setTextColor(Color.parseColor("#ff00ffff"));
                    grenade.setChecked(false);
                    if (!grenade.isChecked()) {
                        setValue(String.valueOf(grenade.getText()), true);
                    }
                }

                if (groza.isChecked()) {
                    setValue("cRgroza", false);
                    setValue("cPgroza", false);
                    setValue("cGgroza", false);
                    setValue("cBgroza", true);
                    setValue("cWgroza", false);
                    setValue("cYgroza", false);
                    cPrefs.write(String.valueOf(groza.getText()), BLUE);
                    groza.setTextColor(Color.parseColor("#ff00ffff"));
                    groza.setChecked(false);
                    if (!groza.isChecked()) {
                        setValue(String.valueOf(groza.getText()), true);
                    }
                }

                if (hollo.isChecked()) {
                    setValue("cRhollo", false);
                    setValue("cPhollo", false);
                    setValue("cGhollo", false);
                    setValue("cBhollo", true);
                    setValue("cWhollo", false);
                    setValue("cYhollo", false);
                    cPrefs.write(String.valueOf(hollo.getText()), BLUE);
                    hollo.setTextColor(Color.parseColor("#ff00ffff"));
                    hollo.setChecked(false);
                    if (!hollo.isChecked()) {
                        setValue(String.valueOf(hollo.getText()), true);
                    }
                }

                if (m1014.isChecked()) {
                    setValue("cRm1014", false);
                    setValue("cPm1014", false);
                    setValue("cGm1014", false);
                    setValue("cBm1014", true);
                    setValue("cWm1014", false);
                    setValue("cYm1014", false);
                    cPrefs.write(String.valueOf(m1014.getText()), BLUE);
                    m1014.setTextColor(Color.parseColor("#ff00ffff"));
                    m1014.setChecked(false);
                    if (!m1014.isChecked()) {
                        setValue(String.valueOf(m1014.getText()), true);
                    }
                }

                if (m16.isChecked()) {
                    setValue("cRm16", false);
                    setValue("cPm16", false);
                    setValue("cGm16", false);
                    setValue("cBm16", true);
                    setValue("cWm16", false);
                    setValue("cYm16", false);
                    cPrefs.write(String.valueOf(m16.getText()), BLUE);
                    m16.setTextColor(Color.parseColor("#ff00ffff"));
                    m16.setChecked(false);
                    if (!m16.isChecked()) {
                        setValue(String.valueOf(m16.getText()), true);
                    }
                }

                if (m24.isChecked()) {
                    setValue("cRm24", false);
                    setValue("cPm24", false);
                    setValue("cGm24", false);
                    setValue("cBm24", true);
                    setValue("cWm24", false);
                    setValue("cYm24", false);
                    cPrefs.write(String.valueOf(m24.getText()), BLUE);
                    m24.setTextColor(Color.parseColor("#ff00ffff"));
                    m24.setChecked(false);
                    if (!m24.isChecked()) {
                        setValue(String.valueOf(m24.getText()), true);
                    }
                }

                if (m249.isChecked()) {
                    setValue("cRm249", false);
                    setValue("cPm249", false);
                    setValue("cGm249", false);
                    setValue("cBm249", true);
                    setValue("cWm249", false);
                    setValue("cYm249", false);
                    cPrefs.write(String.valueOf(m249.getText()), BLUE);
                    m249.setTextColor(Color.parseColor("#ff00ffff"));
                    m249.setChecked(false);
                    if (!m249.isChecked()) {
                        setValue(String.valueOf(m249.getText()), true);
                    }
                }

                if (m4.isChecked()) {
                    setValue("cRm4", false);
                    setValue("cPm4", false);
                    setValue("cGm4", false);
                    setValue("cBm4", true);
                    setValue("cWm4", false);
                    setValue("cYm4", false);
                    cPrefs.write(String.valueOf(m4.getText()), BLUE);
                    m4.setTextColor(Color.parseColor("#ff00ffff"));
                    m4.setChecked(false);
                    if (!m4.isChecked()) {
                        setValue(String.valueOf(m4.getText()), true);
                    }
                }

                if (m762g.isChecked()) {
                    setValue("cRm762", false);
                    setValue("cPm762", false);
                    setValue("cGm762", false);
                    setValue("cBm762", true);
                    setValue("cWm762", false);
                    setValue("cYm762", false);
                    cPrefs.write(String.valueOf(m762g.getText()), BLUE);
                    m762g.setTextColor(Color.parseColor("#ff00ffff"));
                    m762g.setChecked(false);
                    if (!m762g.isChecked()) {
                        setValue(String.valueOf(m762g.getText()), true);
                    }
                }

                if (mk14.isChecked()) {
                    setValue("cRmk14", false);
                    setValue("cPmk14", false);
                    setValue("cGmk14", false);
                    setValue("cBmk14", true);
                    setValue("cWmk14", false);
                    setValue("cYmk14", false);
                    cPrefs.write(String.valueOf(mk14.getText()), BLUE);
                    mk14.setTextColor(Color.parseColor("#ff00ffff"));
                    mk14.setChecked(false);
                    if (!mk14.isChecked()) {
                        setValue(String.valueOf(mk14.getText()), true);
                    }
                }

                if (mk47.isChecked()) {
                    setValue("cRmk47", false);
                    setValue("cPmk47", false);
                    setValue("cGmk47", false);
                    setValue("cBmk47", true);
                    setValue("cWmk47", false);
                    setValue("cYmk47", false);
                    cPrefs.write(String.valueOf(mk47.getText()), BLUE);
                    mk47.setTextColor(Color.parseColor("#ff00ffff"));
                    mk47.setChecked(false);
                    if (!mk47.isChecked()) {
                        setValue(String.valueOf(mk47.getText()), true);
                    }
                }

                if (mp5k.isChecked()) {
                    setValue("cRmp5k", false);
                    setValue("cPmp5k", false);
                    setValue("cGmp5k", false);
                    setValue("cBmp5k", true);
                    setValue("cWmp5k", false);
                    setValue("cYmp5k", false);
                    cPrefs.write(String.valueOf(mp5k.getText()), BLUE);
                    mp5k.setTextColor(Color.parseColor("#ff00ffff"));
                    mp5k.setChecked(false);
                    if (!mp5k.isChecked()) {
                        setValue(String.valueOf(mp5k.getText()), true);
                    }
                }

                if (mini14.isChecked()) {
                    setValue("cRmini14", false);
                    setValue("cPmini14", false);
                    setValue("cGmini14", false);
                    setValue("cBmini14", true);
                    setValue("cWmini14", false);
                    setValue("cYmini14", false);
                    cPrefs.write(String.valueOf(mini14.getText()), BLUE);
                    mini14.setTextColor(Color.parseColor("#ff00ffff"));
                    mini14.setChecked(false);
                    if (!mini14.isChecked()) {
                        setValue(String.valueOf(mini14.getText()), true);
                    }
                }

                if (molotov.isChecked()) {
                    setValue("cRburn", false);
                    setValue("cPburn", false);
                    setValue("cGburn", false);
                    setValue("cBburn", true);
                    setValue("cWburn", false);
                    setValue("cYburn", false);
                    cPrefs.write(String.valueOf(molotov.getText()), BLUE);
                    molotov.setTextColor(Color.parseColor("#ff00ffff"));
                    molotov.setChecked(false);
                    if (!molotov.isChecked()) {
                        setValue(String.valueOf(molotov.getText()), true);
                    }
                }

                if (pp19.isChecked()) {
                    setValue("cRpp19", false);
                    setValue("cPpp19", false);
                    setValue("cGpp19", false);
                    setValue("cBpp19", true);
                    setValue("cWpp19", false);
                    setValue("cYpp19", false);
                    cPrefs.write(String.valueOf(pp19.getText()), BLUE);
                    pp19.setTextColor(Color.parseColor("#ff00ffff"));
                    pp19.setChecked(false);
                    if (!pp19.isChecked()) {
                        setValue(String.valueOf(pp19.getText()), true);
                    }
                }

                if (pan.isChecked()) {
                    setValue("cRpan", false);
                    setValue("cPpan", false);
                    setValue("cGpan", false);
                    setValue("cBpan", true);
                    setValue("cWpan", false);
                    setValue("cYpan", false);
                    cPrefs.write(String.valueOf(pan.getText()), BLUE);
                    pan.setTextColor(Color.parseColor("#ff00ffff"));
                    pan.setChecked(false);
                    if (!pan.isChecked()) {
                        setValue(String.valueOf(pan.getText()), true);
                    }
                }

                if (qbu.isChecked()) {
                    setValue("cRqbu", false);
                    setValue("cPqbu", false);
                    setValue("cGqbu", false);
                    setValue("cBqbu", true);
                    setValue("cWqbu", false);
                    setValue("cYqbu", false);
                    cPrefs.write(String.valueOf(qbu.getText()), BLUE);
                    qbu.setTextColor(Color.parseColor("#ff00ffff"));
                    qbu.setChecked(false);
                    if (!qbu.isChecked()) {
                        setValue(String.valueOf(qbu.getText()), true);
                    }
                }

                if (qbz.isChecked()) {
                    setValue("cRqbz", false);
                    setValue("cPqbz", false);
                    setValue("cGqbz", false);
                    setValue("cBqbz", true);
                    setValue("cWqbz", false);
                    setValue("cYqbz", false);
                    cPrefs.write(String.valueOf(qbz.getText()), BLUE);
                    qbz.setTextColor(Color.parseColor("#ff00ffff"));
                    qbz.setChecked(false);
                    if (!qbz.isChecked()) {
                        setValue(String.valueOf(qbz.getText()), true);
                    }
                }

                if (s12k.isChecked()) {
                    setValue("cRs12k", false);
                    setValue("cPs12k", false);
                    setValue("cGs12k", false);
                    setValue("cBs12k", true);
                    setValue("cWs12k", false);
                    setValue("cYs12k", false);
                    cPrefs.write(String.valueOf(s12k.getText()), BLUE);
                    s12k.setTextColor(Color.parseColor("#ff00ffff"));
                    s12k.setChecked(false);
                    if (!s12k.isChecked()) {
                        setValue(String.valueOf(s12k.getText()), true);
                    }
                }

                if (s1897.isChecked()) {
                    setValue("cRs1897", false);
                    setValue("cPs1897", false);
                    setValue("cGs1897", false);
                    setValue("cBs1897", true);
                    setValue("cWs1897", false);
                    setValue("cYs1897", false);
                    cPrefs.write(String.valueOf(s1897.getText()), BLUE);
                    s1897.setTextColor(Color.parseColor("#ff00ffff"));
                    s1897.setChecked(false);
                    if (!s1897.isChecked()) {
                        setValue(String.valueOf(s1897.getText()), true);
                    }
                }

                if (s686.isChecked()) {
                    setValue("cRs686", false);
                    setValue("cPs686", false);
                    setValue("cGs686", false);
                    setValue("cBs686", true);
                    setValue("cWs686", false);
                    setValue("cYs686", false);
                    cPrefs.write(String.valueOf(s686.getText()), BLUE);
                    s686.setTextColor(Color.parseColor("#ff00ffff"));
                    s686.setChecked(false);
                    if (!s686.isChecked()) {
                        setValue(String.valueOf(s686.getText()), true);
                    }
                }

                if (scarl.isChecked()) {
                    setValue("cRscar", false);
                    setValue("cPscar", false);
                    setValue("cGscar", false);
                    setValue("cBscar", true);
                    setValue("cWscar", false);
                    setValue("cYscar", false);
                    cPrefs.write(String.valueOf(scarl.getText()), BLUE);
                    scarl.setTextColor(Color.parseColor("#ff00ffff"));
                    scarl.setChecked(false);
                    if (!scarl.isChecked()) {
                        setValue(String.valueOf(scarl.getText()), true);
                    }
                }

                if (sks.isChecked()) {
                    setValue("cRsks", false);
                    setValue("cPsks", false);
                    setValue("cGsks", false);
                    setValue("cBsks", true);
                    setValue("cWsks", false);
                    setValue("cYsks", false);
                    cPrefs.write(String.valueOf(sks.getText()), BLUE);
                    sks.setTextColor(Color.parseColor("#ff00ffff"));
                    sks.setChecked(false);
                    if (!sks.isChecked()) {
                        setValue(String.valueOf(sks.getText()), true);
                    }
                }

                if (slr.isChecked()) {
                    setValue("cRslr", false);
                    setValue("cPslr", false);
                    setValue("cGslr", false);
                    setValue("cBslr", true);
                    setValue("cWslr", false);
                    setValue("cYslr", false);
                    cPrefs.write(String.valueOf(slr.getText()), BLUE);
                    slr.setTextColor(Color.parseColor("#ff00ffff"));
                    slr.setChecked(false);
                    if (!slr.isChecked()) {
                        setValue(String.valueOf(slr.getText()), true);
                    }
                }

                if (stunt.isChecked()) {
                    setValue("cRflash", false);
                    setValue("cPflash", false);
                    setValue("cGflash", false);
                    setValue("cBflash", true);
                    setValue("cWflash", false);
                    setValue("cYflash", false);
                    cPrefs.write(String.valueOf(stunt.getText()), BLUE);
                    stunt.setTextColor(Color.parseColor("#ff00ffff"));
                    stunt.setChecked(false);
                    if (!stunt.isChecked()) {
                        setValue(String.valueOf(stunt.getText()), true);
                    }
                }

                if (tommy.isChecked()) {
                    setValue("cRthomp", false);
                    setValue("cPthomp", false);
                    setValue("cGthomp", false);
                    setValue("cBthomp", true);
                    setValue("cWthomp", false);
                    setValue("cYthomp", false);
                    cPrefs.write(String.valueOf(tommy.getText()), BLUE);
                    tommy.setTextColor(Color.parseColor("#ff00ffff"));
                    tommy.setChecked(false);
                    if (!tommy.isChecked()) {
                        setValue(String.valueOf(tommy.getText()), true);
                    }
                }

                if (ump45.isChecked()) {
                    setValue("cRump45", false);
                    setValue("cPump45", false);
                    setValue("cGump45", false);
                    setValue("cBump45", true);
                    setValue("cWump45", false);
                    setValue("cYump45", false);
                    cPrefs.write(String.valueOf(ump45.getText()), BLUE);
                    ump45.setTextColor(Color.parseColor("#ff00ffff"));
                    ump45.setChecked(false);
                    if (!ump45.isChecked()) {
                        setValue(String.valueOf(ump45.getText()), true);
                    }
                }

                if (uzi.isChecked()) {
                    setValue("cRuzi", false);
                    setValue("cPuzi", false);
                    setValue("cGuzi", false);
                    setValue("cBuzi", true);
                    setValue("cWuzi", false);
                    setValue("cYuzi", false);
                    cPrefs.write(String.valueOf(uzi.getText()), BLUE);
                    uzi.setTextColor(Color.parseColor("#ff00ffff"));
                    uzi.setChecked(false);
                    if (!uzi.isChecked()) {
                        setValue(String.valueOf(uzi.getText()), true);
                    }
                }

                if (vss.isChecked()) {
                    setValue("cRvss", false);
                    setValue("cPvss", false);
                    setValue("cGvss", false);
                    setValue("cBvss", true);
                    setValue("cWvss", false);
                    setValue("cYvss", false);
                    cPrefs.write(String.valueOf(vss.getText()), BLUE);
                    vss.setTextColor(Color.parseColor("#ff00ffff"));
                    vss.setChecked(false);
                    if (!vss.isChecked()) {
                        setValue(String.valueOf(vss.getText()), true);
                    }
                }

                if (vector.isChecked()) {
                    setValue("cRvect", false);
                    setValue("cPvect", false);
                    setValue("cGvect", false);
                    setValue("cBvect", true);
                    setValue("cWvect", false);
                    setValue("cYvect", false);
                    cPrefs.write(String.valueOf(vector.getText()), BLUE);
                    vector.setTextColor(Color.parseColor("#ff00ffff"));
                    vector.setChecked(false);
                    if (!vector.isChecked()) {
                        setValue(String.valueOf(vector.getText()), true);
                    }
                }

                if (win94.isChecked()) {
                    setValue("cRwinchester", false);
                    setValue("cPwinchester", false);
                    setValue("cGwinchester", false);
                    setValue("cBwinchester", true);
                    setValue("cWwinchester", false);
                    setValue("cYwinchester", false);
                    cPrefs.write(String.valueOf(win94.getText()), BLUE);
                    win94.setTextColor(Color.parseColor("#ff00ffff"));
                    win94.setChecked(false);
                    if (!win94.isChecked()) {
                        setValue(String.valueOf(win94.getText()), true);
                    }
                }
            }
        }); //ItemColorBlue

        clrYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dacia.isChecked()) {
                    setValue("clrRedDacia", false);
                    setValue("clrPinkDacia", false);
                    setValue("clrGreenDacia", false);
                    setValue("clrBlueDacia", false);
                    setValue("clrWhiteDacia", false);
                    setValue("clrYellowDacia", true);
                    cPrefs.write(String.valueOf(Dacia.getText()), YELLOW);
                    Dacia.setTextColor(Color.parseColor("#ffffff00"));
                    Dacia.setChecked(false);
                    if (!Dacia.isChecked()) {
                        setValue(String.valueOf(Dacia.getText()), true);
                    }
                }
                if (UAZ.isChecked()) {
                    setValue("clrRedUAZ", false);
                    setValue("clrPinkUAZ", false);
                    setValue("clrGreenUAZ", false);
                    setValue("clrBlueUAZ", false);
                    setValue("clrWhiteUAZ", false);
                    setValue("clrYellowUAZ", true);
                    cPrefs.write(String.valueOf(UAZ.getText()), YELLOW);
                    UAZ.setTextColor(Color.parseColor("#ffffff00"));
                    UAZ.setChecked(false);
                    if (!UAZ.isChecked()) {
                        setValue(String.valueOf(UAZ.getText()), true);
                    }
                }
                if (Buggy.isChecked()) {
                    setValue("clrRedBuggy", false);
                    setValue("clrPinkBuggy", false);
                    setValue("clrGreenBuggy", false);
                    setValue("clrBlueBuggy", false);
                    setValue("clrWhiteBuggy", false);
                    setValue("clrYellowBuggy", true);
                    cPrefs.write(String.valueOf(Buggy.getText()), YELLOW);
                    Buggy.setTextColor(Color.parseColor("#ffffff00"));
                    Buggy.setChecked(false);
                    if (!Buggy.isChecked()) {
                        setValue(String.valueOf(Buggy.getText()), true);
                    }
                }
                if (Bike.isChecked()) {
                    setValue("clrRedBike", false);
                    setValue("clrPinkBike", false);
                    setValue("clrGreenBike", false);
                    setValue("clrBlueBike", false);
                    setValue("clrWhiteBike", false);
                    setValue("clrYellowBike", true);
                    cPrefs.write(String.valueOf(Bike.getText()), YELLOW);
                    Bike.setTextColor(Color.parseColor("#ffffff00"));
                    Bike.setChecked(false);
                    if (!Bike.isChecked()) {
                        setValue(String.valueOf(Bike.getText()), true);
                    }
                }
                if (Jet.isChecked()) {
                    setValue("clrRedAquaRail", false);
                    setValue("clrPinkAquaRail", false);
                    setValue("clrGreenAquaRail", false);
                    setValue("clrBlueAquaRail", false);
                    setValue("clrWhiteAquaRail", false);
                    setValue("clrYellowAquaRail", true);
                    cPrefs.write(String.valueOf(Jet.getText()), YELLOW);
                    Jet.setTextColor(Color.parseColor("#ffffff00"));
                    Jet.setChecked(false);
                    if (!Jet.isChecked()) {
                        setValue(String.valueOf(Jet.getText()), true);
                    }
                }
                if (Boat.isChecked()) {
                    setValue("clrRedBoat", false);
                    setValue("clrPinkBoat", false);
                    setValue("clrGreenBoat", false);
                    setValue("clrBlueBoat", false);
                    setValue("clrWhiteBoat", false);
                    setValue("clrYellowBoat", true);
                    cPrefs.write(String.valueOf(Boat.getText()), YELLOW);
                    Boat.setTextColor(Color.parseColor("#ffffff00"));
                    Boat.setChecked(false);
                    if (!Boat.isChecked()) {
                        setValue(String.valueOf(Boat.getText()), true);
                    }
                }
                if (Bus.isChecked()) {
                    setValue("clrRedBus", false);
                    setValue("clrPinkBus", false);
                    setValue("clrGreenBus", false);
                    setValue("clrBlueBus", false);
                    setValue("clrWhiteBus", false);
                    cPrefs.write(String.valueOf(Bus.getText()), YELLOW);
                    setValue("clrYellowBus", true);
                    Bus.setTextColor(Color.parseColor("#ffffff00"));
                    Bus.setChecked(false);
                    if (!Bus.isChecked()) {
                        setValue(String.valueOf(Bus.getText()), true);
                    }
                }
                if (Mirado.isChecked()) {
                    setValue("clrRedMirado", false);
                    setValue("clrPinkMirado", false);
                    setValue("clrGreenMirado", false);
                    setValue("clrBlueMirado", false);
                    setValue("clrWhiteMirado", false);
                    setValue("clrYellowMirado", true);
                    cPrefs.write(String.valueOf(Mirado.getText()), YELLOW);
                    Mirado.setTextColor(Color.parseColor("#ffffff00"));
                    Mirado.setChecked(false);
                    if (!Mirado.isChecked()) {
                        setValue(String.valueOf(Mirado.getText()), true);
                    }
                }
                if (Scooter.isChecked()) {
                    setValue("clrRedScooter", false);
                    setValue("clrPinkScooter", false);
                    setValue("clrGreenScooter", false);
                    setValue("clrBlueScooter", false);
                    setValue("clrWhiteScooter", false);
                    setValue("clrYellowScooter", true);
                    cPrefs.write(String.valueOf(Scooter.getText()), YELLOW);
                    Scooter.setTextColor(Color.parseColor("#ffffff00"));
                    Scooter.setChecked(false);
                    if (!Scooter.isChecked()) {
                        setValue(String.valueOf(Scooter.getText()), true);
                    }
                }
                if (Rony.isChecked()) {
                    setValue("clrRedRony", false);
                    setValue("clrPinkRony", false);
                    setValue("clrGreenRony", false);
                    setValue("clrBlueRony", false);
                    setValue("clrWhiteRony", false);
                    setValue("clrYellowRony", true);
                    cPrefs.write(String.valueOf(Rony.getText()), YELLOW);
                    Rony.setTextColor(Color.parseColor("#ffffff00"));
                    Rony.setChecked(false);
                    if (!Rony.isChecked()) {
                        setValue(String.valueOf(Rony.getText()), true);
                    }
                }
                if (Snowbike.isChecked()) {
                    setValue("clrRedSnowbike", false);
                    setValue("clrPinkSnowbike", false);
                    setValue("clrGreenSnowbike", false);
                    setValue("clrBlueSnowbike", false);
                    setValue("clrWhiteSnowbike", false);
                    setValue("clrYellowSnowbike", true);
                    cPrefs.write(String.valueOf(Snowbike.getText()), YELLOW);
                    Snowbike.setTextColor(Color.parseColor("#ffffff00"));
                    Snowbike.setChecked(false);
                    if (!Snowbike.isChecked()) {
                        setValue(String.valueOf(Snowbike.getText()), true);
                    }
                }
                if (Snowmobile.isChecked()) {
                    setValue("clrRedSnowmobile", false);
                    setValue("clrPinkSnowmobile", false);
                    setValue("clrGreenSnowmobile", false);
                    setValue("clrBlueSnowmobile", false);
                    setValue("clrWhiteSnowmobile", false);
                    setValue("clrYellowSnowmobile", true);
                    cPrefs.write(String.valueOf(Snowmobile.getText()), YELLOW);
                    Snowmobile.setTextColor(Color.parseColor("#ffffff00"));
                    Snowmobile.setChecked(false);
                    if (!Snowmobile.isChecked()) {
                        setValue(String.valueOf(Snowmobile.getText()), true);
                    }
                }
                if (Tempo.isChecked()) {
                    setValue("clrRedTuk", false);
                    setValue("clrPinkTuk", false);
                    setValue("clrGreenTuk", false);
                    setValue("clrBlueTuk", false);
                    setValue("clrWhiteTuk", false);
                    setValue("clrYellowTuk", true);
                    cPrefs.write(String.valueOf(Tempo.getText()), YELLOW);
                    Tempo.setTextColor(Color.parseColor("#ffffff00"));
                    Tempo.setChecked(false);
                    if (!Tempo.isChecked()) {
                        setValue(String.valueOf(Tempo.getText()), true);
                    }
                }
                if (Truck.isChecked()) {
                    setValue("clrRedTruck", false);
                    setValue("clrPinkTruck", false);
                    setValue("clrGreenTruck", false);
                    setValue("clrBlueTruck", false);
                    setValue("clrWhiteTruck", false);
                    setValue("clrYellowTruck", true);
                    cPrefs.write(String.valueOf(Truck.getText()), YELLOW);
                    Truck.setTextColor(Color.parseColor("#ffffff00"));
                    Truck.setChecked(false);
                    if (!Truck.isChecked()) {
                        setValue(String.valueOf(Truck.getText()), true);
                    }
                }
                if (BRDM.isChecked()) {
                    setValue("clrRedBRDM", false);
                    setValue("clrPinkBRDM", false);
                    setValue("clrGreenBRDM", false);
                    setValue("clrBlueBRDM", false);
                    setValue("clrWhiteBRDM", false);
                    setValue("clrYellowBRDM", true);
                    cPrefs.write(String.valueOf(BRDM.getText()), YELLOW);
                    BRDM.setTextColor(Color.parseColor("#ffffff00"));
                    BRDM.setChecked(false);
                    if (!BRDM.isChecked()) {
                        setValue(String.valueOf(BRDM.getText()), true);
                    }
                }
                if (LadaNiva.isChecked()) {
                    setValue("clrRedLadaNiva", false);
                    setValue("clrPinkLadaNiva", false);
                    setValue("clrGreenLadaNiva", false);
                    setValue("clrBlueLadaNiva", false);
                    setValue("clrWhiteLadaNiva", false);
                    setValue("clrYellowLadaNiva", true);
                    cPrefs.write(String.valueOf(LadaNiva.getText()), YELLOW);
                    LadaNiva.setTextColor(Color.parseColor("#ffffff00"));
                    LadaNiva.setChecked(false);
                    if (!LadaNiva.isChecked()) {
                        setValue(String.valueOf(LadaNiva.getText()), true);
                    }
                }
                if (MonsterTruck.isChecked()) {
                    setValue("clrRedMonster", false);
                    setValue("clrPinkMonster", false);
                    setValue("clrGreenMonster", false);
                    setValue("clrBlueMonster", false);
                    setValue("clrWhiteMonster", false);
                    setValue("clrYellowMonster", true);
                    cPrefs.write(String.valueOf(MonsterTruck.getText()), YELLOW);
                    MonsterTruck.setTextColor(Color.parseColor("#ffffff00"));
                    MonsterTruck.setChecked(false);
                    if (!MonsterTruck.isChecked()) {
                        setValue(String.valueOf(MonsterTruck.getText()), true);
                    }
                }
                if (CoupeRB.isChecked()) {
                    setValue("clrRedCoupeRB", false);
                    setValue("clrPinkCoupeRB", false);
                    setValue("clrGreenCoupeRB", false);
                    setValue("clrBlueCoupeRB", false);
                    setValue("clrWhiteCoupeRB", false);
                    setValue("clrYellowCoupeRB", true);
                    cPrefs.write(String.valueOf(CoupeRB.getText()), YELLOW);
                    CoupeRB.setTextColor(Color.parseColor("#ffffff00"));
                    CoupeRB.setChecked(false);
                    if (!CoupeRB.isChecked()) {
                        setValue(String.valueOf(CoupeRB.getText()), true);
                    }
                }
                if (UTV.isChecked()) {
                    setValue("clrRedUTV", false);
                    setValue("clrPinkUTV", false);
                    setValue("clrGreenUTV", false);
                    setValue("clrBlueUTV", false);
                    setValue("clrWhiteUTV", false);
                    setValue("clrYellowUTV", true);
                    cPrefs.write(String.valueOf(UTV.getText()), YELLOW);
                    UTV.setTextColor(Color.parseColor("#ffffff00"));
                    UTV.setChecked(false);
                    if (!UTV.isChecked()) {
                        setValue(String.valueOf(UTV.getText()), true);
                    }
                }

                if (am300.isChecked()) {
                    setValue("cR300mm", false);
                    setValue("cP300mm", false);
                    setValue("cG300mm", false);
                    setValue("cB300mm", false);
                    setValue("cW300mm", false);
                    setValue("cY300mm", true);
                    cPrefs.write(String.valueOf(am300.getText()), YELLOW);
                    am300.setTextColor(Color.parseColor("#ffffff00"));
                    am300.setChecked(false);
                    if (!am300.isChecked()) {
                        setValue(String.valueOf(am300.getText()), true);
                    }
                }

                if (am45.isChecked()) {
                    setValue("cRam45", false);
                    setValue("cPam45", false);
                    setValue("cGam45", false);
                    setValue("cBam45", false);
                    setValue("cWam45", false);
                    setValue("cYam45", true);
                    cPrefs.write(String.valueOf(am45.getText()), YELLOW);
                    am45.setTextColor(Color.parseColor("#ffffff00"));
                    am45.setChecked(false);
                    if (!am45.isChecked()) {
                        setValue(String.valueOf(am45.getText()), true);
                    }
                }

                if (am12g.isChecked()) {
                    setValue("cRam12g", false);
                    setValue("cPam12g", false);
                    setValue("cGam12g", false);
                    setValue("cBam12g", false);
                    setValue("cWam12g", false);
                    setValue("cYam12g", true);
                    cPrefs.write(String.valueOf(am12g.getText()), YELLOW);
                    am12g.setTextColor(Color.parseColor("#ffffff00"));
                    am12g.setChecked(false);
                    if (!am12g.isChecked()) {
                        setValue(String.valueOf(am12g.getText()), true);
                    }
                }

                if (am556.isChecked()) {
                    setValue("cRam556", false);
                    setValue("cPam556", false);
                    setValue("cGam556", false);
                    setValue("cBam556", false);
                    setValue("cWam556", false);
                    setValue("cYam556", true);
                    cPrefs.write(String.valueOf(am556.getText()), YELLOW);
                    am556.setTextColor(Color.parseColor("#ffffff00"));
                    am556.setChecked(false);
                    if (!am556.isChecked()) {
                        setValue(String.valueOf(am556.getText()), true);
                    }
                }

                if (am762.isChecked()) {
                    setValue("cRam762", false);
                    setValue("cPam762", false);
                    setValue("cGam762", false);
                    setValue("cBam762", false);
                    setValue("cWam762", false);
                    setValue("cYam762", true);
                    cPrefs.write(String.valueOf(am762.getText()), YELLOW);
                    am762.setTextColor(Color.parseColor("#ffffff00"));
                    am762.setChecked(false);
                    if (!am762.isChecked()) {
                        setValue(String.valueOf(am762.getText()), true);
                    }
                }

                if (am9mm.isChecked()) {
                    setValue("cRam9mm", false);
                    setValue("cPam9mm", false);
                    setValue("cGam9mm", false);
                    setValue("cBam9mm", false);
                    setValue("cWam9mm", false);
                    setValue("cYam9mm", true);
                    cPrefs.write(String.valueOf(am9mm.getText()), YELLOW);
                    am9mm.setTextColor(Color.parseColor("#ffffff00"));
                    am9mm.setChecked(false);
                    if (!am9mm.isChecked()) {
                        setValue(String.valueOf(am9mm.getText()), true);
                    }
                }

                if (armorLvl2.isChecked()) {
                    setValue("cRar2", false);
                    setValue("cPar2", false);
                    setValue("cGar2", false);
                    setValue("cBar2", false);
                    setValue("cWar2", false);
                    setValue("cYar2", true);
                    cPrefs.write(String.valueOf(armorLvl2.getText()), YELLOW);
                    armorLvl2.setTextColor(Color.parseColor("#ffffff00"));
                    armorLvl2.setChecked(false);
                    if (!armorLvl2.isChecked()) {
                        setValue(String.valueOf(armorLvl2.getText()), true);
                    }
                }

                if (armorLvl3.isChecked()) {
                    setValue("cRar3", false);
                    setValue("cPar3", false);
                    setValue("cGar3", false);
                    setValue("cBar3", false);
                    setValue("cWar3", false);
                    setValue("cYar3", true);
                    cPrefs.write(String.valueOf(armorLvl3.getText()), YELLOW);
                    armorLvl3.setTextColor(Color.parseColor("#ffffff00"));
                    armorLvl3.setChecked(false);
                    if (!armorLvl3.isChecked()) {
                        setValue(String.valueOf(armorLvl3.getText()), true);
                    }
                }

                if (helmetLvl2.isChecked()) {
                    setValue("cRhel2", false);
                    setValue("cPhel2", false);
                    setValue("cGhel2", false);
                    setValue("cBhel2", false);
                    setValue("cWhel2", false);
                    setValue("cYhel2", true);
                    cPrefs.write(String.valueOf(helmetLvl2.getText()), YELLOW);
                    helmetLvl2.setTextColor(Color.parseColor("#ffffff00"));
                    helmetLvl2.setChecked(false);
                    if (!helmetLvl2.isChecked()) {
                        setValue(String.valueOf(helmetLvl2.getText()), true);
                    }
                }

                if (helmetLvl3.isChecked()) {
                    setValue("cRhel3", false);
                    setValue("cPhel3", false);
                    setValue("cGhel3", false);
                    setValue("cBhel3", false);
                    setValue("cWhel3", false);
                    setValue("cYhel3", true);
                    cPrefs.write(String.valueOf(helmetLvl3.getText()), YELLOW);
                    helmetLvl3.setTextColor(Color.parseColor("#ffffff00"));
                    helmetLvl3.setChecked(false);
                    if (!helmetLvl3.isChecked()) {
                        setValue(String.valueOf(helmetLvl3.getText()), true);
                    }
                }

                if (bandage.isChecked()) {
                    setValue("cRban", false);
                    setValue("cPban", false);
                    setValue("cGban", false);
                    setValue("cBban", false);
                    setValue("cWban", false);
                    setValue("cYban", true);
                    cPrefs.write(String.valueOf(bandage.getText()), YELLOW);
                    bandage.setTextColor(Color.parseColor("#ffffff00"));
                    bandage.setChecked(false);
                    if (!bandage.isChecked()) {
                        setValue(String.valueOf(bandage.getText()), true);
                    }
                }

                if (drink.isChecked()) {
                    setValue("cRdrink", false);
                    setValue("cPdrink", false);
                    setValue("cGdrink", false);
                    setValue("cBdrink", false);
                    setValue("cWdrink", false);
                    setValue("cYdrink", true);
                    cPrefs.write(String.valueOf(drink.getText()), YELLOW);
                    drink.setTextColor(Color.parseColor("#ffffff00"));
                    drink.setChecked(false);
                    if (!drink.isChecked()) {
                        setValue(String.valueOf(drink.getText()), true);
                    }
                }

                if (firstAir.isChecked()) {
                    setValue("cRfa", false);
                    setValue("cPfa", false);
                    setValue("cGfa", false);
                    setValue("cBfa", false);
                    setValue("cWfa", false);
                    setValue("cYfa", true);
                    cPrefs.write(String.valueOf(firstAir.getText()), YELLOW);
                    firstAir.setTextColor(Color.parseColor("#ffffff00"));
                    firstAir.setChecked(false);
                    if (!firstAir.isChecked()) {
                        setValue(String.valueOf(firstAir.getText()), true);
                    }
                }

                if (injection.isChecked()) {
                    setValue("cRin", false);
                    setValue("cPin", false);
                    setValue("cGin", false);
                    setValue("cBin", false);
                    setValue("cWin", false);
                    setValue("cYin", true);
                    cPrefs.write(String.valueOf(injection.getText()), YELLOW);
                    injection.setTextColor(Color.parseColor("#ffffff00"));
                    injection.setChecked(false);
                    if (!injection.isChecked()) {
                        setValue(String.valueOf(injection.getText()), true);
                    }
                }

                if (pills.isChecked()) {
                    setValue("cRpills", false);
                    setValue("cPpills", false);
                    setValue("cGpills", false);
                    setValue("cBpills", false);
                    setValue("cWpills", false);
                    setValue("cYpills", true);
                    cPrefs.write(String.valueOf(pills.getText()), YELLOW);
                    pills.setTextColor(Color.parseColor("#ffffff00"));
                    pills.setChecked(false);
                    if (!pills.isChecked()) {
                        setValue(String.valueOf(pills.getText()), true);
                    }
                }

                //Misk
                if (airCraft.isChecked()) {
                    setValue("cRac", false);
                    setValue("cPac", false);
                    setValue("cGac", false);
                    setValue("cBac", false);
                    setValue("cWac", false);
                    setValue("cYac", true);
                    cPrefs.write(String.valueOf(airCraft.getText()), YELLOW);
                    airCraft.setTextColor(Color.parseColor("#ffffff00"));
                    airCraft.setChecked(false);
                    if (!airCraft.isChecked()) {
                        setValue(String.valueOf(airCraft.getText()), true);
                    }
                }

                if (airDrop.isChecked()) {
                    setValue("cRairD", false);
                    setValue("cPairD", false);
                    setValue("cGairD", false);
                    setValue("cBairD", false);
                    setValue("cWairD", false);
                    setValue("cYairD", true);
                    cPrefs.write(String.valueOf(airDrop.getText()), YELLOW);
                    airDrop.setTextColor(Color.parseColor("#ffffff00"));
                    airDrop.setChecked(false);
                    if (!airDrop.isChecked()) {
                        setValue(String.valueOf(airDrop.getText()), true);
                    }
                }

                if (bag2.isChecked()) {
                    setValue("cRb2", false);
                    setValue("cPb2", false);
                    setValue("cGb2", false);
                    setValue("cBb2", false);
                    setValue("cWb2", false);
                    setValue("cYb2", true);
                    cPrefs.write(String.valueOf(bag2.getText()), YELLOW);
                    bag2.setTextColor(Color.parseColor("#ffffff00"));
                    bag2.setChecked(false);
                    if (!bag2.isChecked()) {
                        setValue(String.valueOf(bag2.getText()), true);
                    }
                }

                if (bag3.isChecked()) {
                    setValue("cRb3", false);
                    setValue("cPb3", false);
                    setValue("cGb3", false);
                    setValue("cBb3", false);
                    setValue("cWb3", false);
                    setValue("cYb3", true);
                    cPrefs.write(String.valueOf(bag3.getText()), YELLOW);
                    bag3.setTextColor(Color.parseColor("#ffffff00"));
                    bag3.setChecked(false);
                    if (!bag3.isChecked()) {
                        setValue(String.valueOf(bag3.getText()), true);
                    }
                }

                if (compensator.isChecked()) {
                    setValue("cRcomp", false);
                    setValue("cPcomp", false);
                    setValue("cGcomp", false);
                    setValue("cBcomp", false);
                    setValue("cWcomp", false);
                    setValue("cYcomp", true);
                    cPrefs.write(String.valueOf(compensator.getText()), YELLOW);
                    compensator.setTextColor(Color.parseColor("#ffffff00"));
                    compensator.setChecked(false);
                    if (!compensator.isChecked()) {
                        setValue(String.valueOf(compensator.getText()), true);
                    }
                }

                if (loot.isChecked()) {
                    setValue("cRloot", false);
                    setValue("cPloot", false);
                    setValue("cGloot", false);
                    setValue("cBloot", false);
                    setValue("cWloot", false);
                    setValue("cYloot", true);
                    cPrefs.write(String.valueOf(loot.getText()), YELLOW);
                    loot.setTextColor(Color.parseColor("#ffffff00"));
                    loot.setChecked(false);
                    if (!loot.isChecked()) {
                        setValue(String.valueOf(loot.getText()), true);
                    }
                }

                if (supressor.isChecked()) {
                    setValue("cRsupp", false);
                    setValue("cPsupp", false);
                    setValue("cGsupp", false);
                    setValue("cBsupp", false);
                    setValue("cWsupp", false);
                    setValue("cYsupp", true);
                    cPrefs.write(String.valueOf(supressor.getText()), YELLOW);
                    supressor.setTextColor(Color.parseColor("#ffffff00"));
                    supressor.setChecked(false);
                    if (!supressor.isChecked()) {
                        setValue(String.valueOf(supressor.getText()), true);
                    }
                }

                //Weapons
                if (x2.isChecked()) {
                    setValue("cRx2", false);
                    setValue("cPx2", false);
                    setValue("cGx2", false);
                    setValue("cBx2", false);
                    setValue("cWx2", false);
                    setValue("cYx2", true);
                    cPrefs.write(String.valueOf(x2.getText()), YELLOW);
                    x2.setTextColor(Color.parseColor("#ffffff00"));
                    x2.setChecked(false);
                    if (!x2.isChecked()) {
                        setValue(String.valueOf(x2.getText()), true);
                    }
                }

                if (x3.isChecked()) {
                    setValue("cRx3", false);
                    setValue("cPx3", false);
                    setValue("cGx3", false);
                    setValue("cBx3", false);
                    setValue("cWx3", false);
                    setValue("cYx3", true);
                    cPrefs.write(String.valueOf(x3.getText()), YELLOW);
                    x3.setTextColor(Color.parseColor("#ffffff00"));
                    x3.setChecked(false);
                    if (!x3.isChecked()) {
                        setValue(String.valueOf(x3.getText()), true);
                    }
                }

                if (x4.isChecked()) {
                    setValue("cRx4", false);
                    setValue("cPx4", false);
                    setValue("cGx4", false);
                    setValue("cBx4", false);
                    setValue("cWx4", false);
                    setValue("cYx4", true);
                    cPrefs.write(String.valueOf(x4.getText()), YELLOW);
                    x4.setTextColor(Color.parseColor("#ffffff00"));
                    x4.setChecked(false);
                    if (!x4.isChecked()) {
                        setValue(String.valueOf(x4.getText()), true);
                    }
                }

                if (x6.isChecked()) {
                    setValue("cRx6", false);
                    setValue("cPx6", false);
                    setValue("cGx6", false);
                    setValue("cBx6", false);
                    setValue("cWx6", false);
                    setValue("cYx6", true);
                    cPrefs.write(String.valueOf(x6.getText()), YELLOW);
                    x6.setTextColor(Color.parseColor("#ffffff00"));
                    x6.setChecked(false);
                    if (!x6.isChecked()) {
                        setValue(String.valueOf(x6.getText()), true);
                    }
                }

                if (x8.isChecked()) {
                    setValue("cRx8", false);
                    setValue("cPx8", false);
                    setValue("cGx8", false);
                    setValue("cBx8", false);
                    setValue("cWx8", false);
                    setValue("cYx8", true);
                    cPrefs.write(String.valueOf(x8.getText()), YELLOW);
                    x8.setTextColor(Color.parseColor("#ffffff00"));
                    x8.setChecked(false);
                    if (!x8.isChecked()) {
                        setValue(String.valueOf(x8.getText()), true);
                    }
                }

                //real
                if (k98.isChecked()) {
                    setValue("cRk98", false);
                    setValue("cPk98", false);
                    setValue("cGk98", false);
                    setValue("cBk98", false);
                    setValue("cWk98", false);
                    setValue("cYk98", true);
                    cPrefs.write(String.valueOf(k98.getText()), YELLOW);
                    k98.setTextColor(Color.parseColor("#ffffff00"));
                    k98.setChecked(false);
                    if (!k98.isChecked()) {
                        setValue(String.valueOf(k98.getText()), true);
                    }
                }

                if (akm.isChecked()) {
                    setValue("cRakm", false);
                    setValue("cPakm", false);
                    setValue("cGakm", false);
                    setValue("cBakm", false);
                    setValue("cWakm", false);
                    setValue("cYakm", true);
                    cPrefs.write(String.valueOf(akm.getText()), YELLOW);
                    akm.setTextColor(Color.parseColor("#ffffff00"));
                    akm.setChecked(false);
                    if (!akm.isChecked()) {
                        setValue(String.valueOf(akm.getText()), true);
                    }
                }

                if (aug.isChecked()) {
                    setValue("cRaug", false);
                    setValue("cPaug", false);
                    setValue("cGaug", false);
                    setValue("cBaug", false);
                    setValue("cWaug", false);
                    setValue("cYaug", true);
                    cPrefs.write(String.valueOf(aug.getText()), YELLOW);
                    aug.setTextColor(Color.parseColor("#ffffff00"));
                    aug.setChecked(false);
                    if (!aug.isChecked()) {
                        setValue(String.valueOf(aug.getText()), true);
                    }
                }

                if (awm.isChecked()) {
                    setValue("cRawm", false);
                    setValue("cPawm", false);
                    setValue("cGawm", false);
                    setValue("cBawm", false);
                    setValue("cWawm", false);
                    setValue("cYawm", true);
                    cPrefs.write(String.valueOf(awm.getText()), YELLOW);
                    awm.setTextColor(Color.parseColor("#ffffff00"));
                    awm.setChecked(false);
                    if (!awm.isChecked()) {
                        setValue(String.valueOf(awm.getText()), true);
                    }
                }

                if (bow.isChecked()) {
                    setValue("cRbow", false);
                    setValue("cPbow", false);
                    setValue("cGbow", false);
                    setValue("cBbow", false);
                    setValue("cWbow", false);
                    setValue("cYbow", true);
                    cPrefs.write(String.valueOf(bow.getText()), YELLOW);
                    bow.setTextColor(Color.parseColor("#ffffff00"));
                    bow.setChecked(false);
                    if (!bow.isChecked()) {
                        setValue(String.valueOf(bow.getText()), true);
                    }
                }

                if (dp12.isChecked()) {
                    setValue("cRdp12", false);
                    setValue("cPdp12", false);
                    setValue("cGdp12", false);
                    setValue("cBdp12", false);
                    setValue("cWdp12", false);
                    setValue("cYdp12", true);
                    cPrefs.write(String.valueOf(dp12.getText()), YELLOW);
                    dp12.setTextColor(Color.parseColor("#ffffff00"));
                    dp12.setChecked(false);
                    if (!dp12.isChecked()) {
                        setValue(String.valueOf(dp12.getText()), true);
                    }
                }

                if (dp28.isChecked()) {
                    setValue("cRdp28", false);
                    setValue("cPdp28", false);
                    setValue("cGdp28", false);
                    setValue("cBdp28", false);
                    setValue("cWdp28", false);
                    setValue("cYdp28", true);
                    cPrefs.write(String.valueOf(dp28.getText()), YELLOW);
                    dp28.setTextColor(Color.parseColor("#ffffff00"));
                    dp28.setChecked(false);
                    if (!dp28.isChecked()) {
                        setValue(String.valueOf(dp28.getText()), true);
                    }
                }

                if (deagle.isChecked()) {
                    setValue("cRdeg", false);
                    setValue("cPdeg", false);
                    setValue("cGdeg", false);
                    setValue("cBdeg", false);
                    setValue("cWdeg", false);
                    setValue("cYdeg", true);
                    cPrefs.write(String.valueOf(deagle.getText()), YELLOW);
                    deagle.setTextColor(Color.parseColor("#ffffff00"));
                    deagle.setChecked(false);
                    if (!deagle.isChecked()) {
                        setValue(String.valueOf(deagle.getText()), true);
                    }
                }

                if (redDot.isChecked()) {
                    setValue("cRredDot", false);
                    setValue("cPredDot", false);
                    setValue("cGredDot", false);
                    setValue("cBredDot", false);
                    setValue("cWredDot", false);
                    setValue("cYredDot", true);
                    cPrefs.write(String.valueOf(redDot.getText()), YELLOW);
                    redDot.setTextColor(Color.parseColor("#ffffff00"));
                    redDot.setChecked(false);
                    if (!redDot.isChecked()) {
                        setValue(String.valueOf(redDot.getText()), true);
                    }
                }

                if (flare.isChecked()) {
                    setValue("cRflare", false);
                    setValue("cPflare", false);
                    setValue("cGflare", false);
                    setValue("cBflare", false);
                    setValue("cWflare", false);
                    setValue("cYflare", true);
                    cPrefs.write(String.valueOf(flare.getText()), YELLOW);
                    flare.setTextColor(Color.parseColor("#ffffff00"));
                    flare.setChecked(false);
                    if (!flare.isChecked()) {
                        setValue(String.valueOf(flare.getText()), true);
                    }
                }

                if (g36.isChecked()) {
                    setValue("cRg36", false);
                    setValue("cPg36", false);
                    setValue("cGg36", false);
                    setValue("cBg36", false);
                    setValue("cWg36", false);
                    setValue("cYg36", true);
                    cPrefs.write(String.valueOf(g36.getText()), YELLOW);
                    g36.setTextColor(Color.parseColor("#ffffff00"));
                    g36.setChecked(false);
                    if (!g36.isChecked()) {
                        setValue(String.valueOf(g36.getText()), true);
                    }
                }

                if (grenade.isChecked()) {
                    setValue("cRgrenade", false);
                    setValue("cPgrenade", false);
                    setValue("cGgrenade", false);
                    setValue("cBgrenade", false);
                    setValue("cWgrenade", false);
                    setValue("cYgrenade", true);
                    cPrefs.write(String.valueOf(grenade.getText()), YELLOW);
                    grenade.setTextColor(Color.parseColor("#ffffff00"));
                    grenade.setChecked(false);
                    if (!grenade.isChecked()) {
                        setValue(String.valueOf(grenade.getText()), true);
                    }
                }

                if (groza.isChecked()) {
                    setValue("cRgroza", false);
                    setValue("cPgroza", false);
                    setValue("cGgroza", false);
                    setValue("cBgroza", false);
                    setValue("cWgroza", false);
                    setValue("cYgroza", true);
                    cPrefs.write(String.valueOf(groza.getText()), YELLOW);
                    groza.setTextColor(Color.parseColor("#ffffff00"));
                    groza.setChecked(false);
                    if (!groza.isChecked()) {
                        setValue(String.valueOf(groza.getText()), true);
                    }
                }

                if (hollo.isChecked()) {
                    setValue("cRhollo", false);
                    setValue("cPhollo", false);
                    setValue("cGhollo", false);
                    setValue("cBhollo", false);
                    setValue("cWhollo", false);
                    setValue("cYhollo", true);
                    cPrefs.write(String.valueOf(hollo.getText()), YELLOW);
                    hollo.setTextColor(Color.parseColor("#ffffff00"));
                    hollo.setChecked(false);
                    if (!hollo.isChecked()) {
                        setValue(String.valueOf(hollo.getText()), true);
                    }
                }

                if (m1014.isChecked()) {
                    setValue("cRm1014", false);
                    setValue("cPm1014", false);
                    setValue("cGm1014", false);
                    setValue("cBm1014", false);
                    setValue("cWm1014", false);
                    setValue("cYm1014", true);
                    cPrefs.write(String.valueOf(m1014.getText()), YELLOW);
                    m1014.setTextColor(Color.parseColor("#ffffff00"));
                    m1014.setChecked(false);
                    if (!m1014.isChecked()) {
                        setValue(String.valueOf(m1014.getText()), true);
                    }
                }

                if (m16.isChecked()) {
                    setValue("cRm16", false);
                    setValue("cPm16", false);
                    setValue("cGm16", false);
                    setValue("cBm16", false);
                    setValue("cWm16", false);
                    setValue("cYm16", true);
                    cPrefs.write(String.valueOf(m16.getText()), YELLOW);
                    m16.setTextColor(Color.parseColor("#ffffff00"));
                    m16.setChecked(false);
                    if (!m16.isChecked()) {
                        setValue(String.valueOf(m16.getText()), true);
                    }
                }

                if (m24.isChecked()) {
                    setValue("cRm24", false);
                    setValue("cPm24", false);
                    setValue("cGm24", false);
                    setValue("cBm24", false);
                    setValue("cWm24", false);
                    setValue("cYm24", true);
                    cPrefs.write(String.valueOf(m24.getText()), YELLOW);
                    m24.setTextColor(Color.parseColor("#ffffff00"));
                    m24.setChecked(false);
                    if (!m24.isChecked()) {
                        setValue(String.valueOf(m24.getText()), true);
                    }
                }

                if (m249.isChecked()) {
                    setValue("cRm249", false);
                    setValue("cPm249", false);
                    setValue("cGm249", false);
                    setValue("cBm249", false);
                    setValue("cWm249", false);
                    setValue("cYm249", true);
                    cPrefs.write(String.valueOf(m249.getText()), YELLOW);
                    m249.setTextColor(Color.parseColor("#ffffff00"));
                    m249.setChecked(false);
                    if (!m249.isChecked()) {
                        setValue(String.valueOf(m249.getText()), true);
                    }
                }

                if (m4.isChecked()) {
                    setValue("cRm4", false);
                    setValue("cPm4", false);
                    setValue("cGm4", false);
                    setValue("cBm4", false);
                    setValue("cWm4", false);
                    setValue("cYm4", true);
                    cPrefs.write(String.valueOf(m4.getText()), YELLOW);
                    m4.setTextColor(Color.parseColor("#ffffff00"));
                    m4.setChecked(false);
                    if (!m4.isChecked()) {
                        setValue(String.valueOf(m4.getText()), true);
                    }
                }

                if (m762g.isChecked()) {
                    setValue("cRm762", false);
                    setValue("cPm762", false);
                    setValue("cGm762", false);
                    setValue("cBm762", false);
                    setValue("cWm762", false);
                    setValue("cYm762", true);
                    cPrefs.write(String.valueOf(m762g.getText()), YELLOW);
                    m762g.setTextColor(Color.parseColor("#ffffff00"));
                    m762g.setChecked(false);
                    if (!m762g.isChecked()) {
                        setValue(String.valueOf(m762g.getText()), true);
                    }
                }

                if (mk14.isChecked()) {
                    setValue("cRmk14", false);
                    setValue("cPmk14", false);
                    setValue("cGmk14", false);
                    setValue("cBmk14", false);
                    setValue("cWmk14", false);
                    setValue("cYmk14", true);
                    cPrefs.write(String.valueOf(mk14.getText()), YELLOW);
                    mk14.setTextColor(Color.parseColor("#ffffff00"));
                    mk14.setChecked(false);
                    if (!mk14.isChecked()) {
                        setValue(String.valueOf(mk14.getText()), true);
                    }
                }

                if (mk47.isChecked()) {
                    setValue("cRmk47", false);
                    setValue("cPmk47", false);
                    setValue("cGmk47", false);
                    setValue("cBmk47", false);
                    setValue("cWmk47", false);
                    setValue("cYmk47", true);
                    cPrefs.write(String.valueOf(mk47.getText()), YELLOW);
                    mk47.setTextColor(Color.parseColor("#ffffff00"));
                    mk47.setChecked(false);
                    if (!mk47.isChecked()) {
                        setValue(String.valueOf(mk47.getText()), true);
                    }
                }

                if (mp5k.isChecked()) {
                    setValue("cRmp5k", false);
                    setValue("cPmp5k", false);
                    setValue("cGmp5k", false);
                    setValue("cBmp5k", false);
                    setValue("cWmp5k", false);
                    setValue("cYmp5k", true);
                    cPrefs.write(String.valueOf(mp5k.getText()), YELLOW);
                    mp5k.setTextColor(Color.parseColor("#ffffff00"));
                    mp5k.setChecked(false);
                    if (!mp5k.isChecked()) {
                        setValue(String.valueOf(mp5k.getText()), true);
                    }
                }

                if (mini14.isChecked()) {
                    setValue("cRmini14", false);
                    setValue("cPmini14", false);
                    setValue("cGmini14", false);
                    setValue("cBmini14", false);
                    setValue("cWmini14", false);
                    setValue("cYmini14", true);
                    cPrefs.write(String.valueOf(mini14.getText()), YELLOW);
                    mini14.setTextColor(Color.parseColor("#ffffff00"));
                    mini14.setChecked(false);
                    if (!mini14.isChecked()) {
                        setValue(String.valueOf(mini14.getText()), true);
                    }
                }

                if (molotov.isChecked()) {
                    setValue("cRburn", false);
                    setValue("cPburn", false);
                    setValue("cGburn", false);
                    setValue("cBburn", false);
                    setValue("cWburn", false);
                    setValue("cYburn", true);
                    cPrefs.write(String.valueOf(molotov.getText()), YELLOW);
                    molotov.setTextColor(Color.parseColor("#ffffff00"));
                    molotov.setChecked(false);
                    if (!molotov.isChecked()) {
                        setValue(String.valueOf(molotov.getText()), true);
                    }
                }

                if (pp19.isChecked()) {
                    setValue("cRpp19", false);
                    setValue("cPpp19", false);
                    setValue("cGpp19", false);
                    setValue("cBpp19", false);
                    setValue("cWpp19", false);
                    setValue("cYpp19", true);
                    cPrefs.write(String.valueOf(pp19.getText()), YELLOW);
                    pp19.setTextColor(Color.parseColor("#ffffff00"));
                    pp19.setChecked(false);
                    if (!pp19.isChecked()) {
                        setValue(String.valueOf(pp19.getText()), true);
                    }
                }

                if (pan.isChecked()) {
                    setValue("cRpan", false);
                    setValue("cPpan", false);
                    setValue("cGpan", false);
                    setValue("cBpan", false);
                    setValue("cWpan", false);
                    setValue("cYpan", true);
                    cPrefs.write(String.valueOf(pan.getText()), YELLOW);
                    pan.setTextColor(Color.parseColor("#ffffff00"));
                    pan.setChecked(false);
                    if (!pan.isChecked()) {
                        setValue(String.valueOf(pan.getText()), true);
                    }
                }

                if (qbu.isChecked()) {
                    setValue("cRqbu", false);
                    setValue("cPqbu", false);
                    setValue("cGqbu", false);
                    setValue("cBqbu", false);
                    setValue("cWqbu", false);
                    setValue("cYqbu", true);
                    cPrefs.write(String.valueOf(qbu.getText()), YELLOW);
                    qbu.setTextColor(Color.parseColor("#ffffff00"));
                    qbu.setChecked(false);
                    if (!qbu.isChecked()) {
                        setValue(String.valueOf(qbu.getText()), true);
                    }
                }

                if (qbz.isChecked()) {
                    setValue("cRqbz", false);
                    setValue("cPqbz", false);
                    setValue("cGqbz", false);
                    setValue("cBqbz", false);
                    setValue("cWqbz", false);
                    setValue("cYqbz", true);
                    cPrefs.write(String.valueOf(qbz.getText()), YELLOW);
                    qbz.setTextColor(Color.parseColor("#ffffff00"));
                    qbz.setChecked(false);
                    if (!qbz.isChecked()) {
                        setValue(String.valueOf(qbz.getText()), true);
                    }
                }

                if (s12k.isChecked()) {
                    setValue("cRs12k", false);
                    setValue("cPs12k", false);
                    setValue("cGs12k", false);
                    setValue("cBs12k", false);
                    setValue("cWs12k", false);
                    setValue("cYs12k", true);
                    cPrefs.write(String.valueOf(s12k.getText()), YELLOW);
                    s12k.setTextColor(Color.parseColor("#ffffff00"));
                    s12k.setChecked(false);
                    if (!s12k.isChecked()) {
                        setValue(String.valueOf(s12k.getText()), true);
                    }
                }

                if (s1897.isChecked()) {
                    setValue("cRs1897", false);
                    setValue("cPs1897", false);
                    setValue("cGs1897", false);
                    setValue("cBs1897", false);
                    setValue("cWs1897", false);
                    setValue("cYs1897", true);
                    cPrefs.write(String.valueOf(s1897.getText()), YELLOW);
                    s1897.setTextColor(Color.parseColor("#ffffff00"));
                    s1897.setChecked(false);
                    if (!s1897.isChecked()) {
                        setValue(String.valueOf(s1897.getText()), true);
                    }
                }

                if (s686.isChecked()) {
                    setValue("cRs686", false);
                    setValue("cPs686", false);
                    setValue("cGs686", false);
                    setValue("cBs686", false);
                    setValue("cWs686", false);
                    setValue("cYs686", true);
                    cPrefs.write(String.valueOf(s686.getText()), YELLOW);
                    s686.setTextColor(Color.parseColor("#ffffff00"));
                    s686.setChecked(false);
                    if (!s686.isChecked()) {
                        setValue(String.valueOf(s686.getText()), true);
                    }
                }

                if (scarl.isChecked()) {
                    setValue("cRscar", false);
                    setValue("cPscar", false);
                    setValue("cGscar", false);
                    setValue("cBscar", false);
                    setValue("cWscar", false);
                    setValue("cYscar", true);
                    cPrefs.write(String.valueOf(scarl.getText()), YELLOW);
                    scarl.setTextColor(Color.parseColor("#ffffff00"));
                    scarl.setChecked(false);
                    if (!scarl.isChecked()) {
                        setValue(String.valueOf(scarl.getText()), true);
                    }
                }

                if (sks.isChecked()) {
                    setValue("cRsks", false);
                    setValue("cPsks", false);
                    setValue("cGsks", false);
                    setValue("cBsks", false);
                    setValue("cWsks", false);
                    setValue("cYsks", true);
                    cPrefs.write(String.valueOf(sks.getText()), YELLOW);
                    sks.setTextColor(Color.parseColor("#ffffff00"));
                    sks.setChecked(false);
                    if (!sks.isChecked()) {
                        setValue(String.valueOf(sks.getText()), true);
                    }
                }

                if (slr.isChecked()) {
                    setValue("cRslr", false);
                    setValue("cPslr", false);
                    setValue("cGslr", false);
                    setValue("cBslr", false);
                    setValue("cWslr", false);
                    setValue("cYslr", true);
                    cPrefs.write(String.valueOf(slr.getText()), YELLOW);
                    slr.setTextColor(Color.parseColor("#ffffff00"));
                    slr.setChecked(false);
                    if (!slr.isChecked()) {
                        setValue(String.valueOf(slr.getText()), true);
                    }
                }

                if (stunt.isChecked()) {
                    setValue("cRflash", false);
                    setValue("cPflash", false);
                    setValue("cGflash", false);
                    setValue("cBflash", false);
                    setValue("cWflash", false);
                    setValue("cYflash", true);
                    cPrefs.write(String.valueOf(stunt.getText()), YELLOW);
                    stunt.setTextColor(Color.parseColor("#ffffff00"));
                    stunt.setChecked(false);
                    if (!stunt.isChecked()) {
                        setValue(String.valueOf(stunt.getText()), true);
                    }
                }

                if (tommy.isChecked()) {
                    setValue("cRthomp", false);
                    setValue("cPthomp", false);
                    setValue("cGthomp", false);
                    setValue("cBthomp", false);
                    setValue("cWthomp", false);
                    setValue("cYthomp", true);
                    cPrefs.write(String.valueOf(tommy.getText()), YELLOW);
                    tommy.setTextColor(Color.parseColor("#ffffff00"));
                    tommy.setChecked(false);
                    if (!tommy.isChecked()) {
                        setValue(String.valueOf(tommy.getText()), true);
                    }
                }

                if (ump45.isChecked()) {
                    setValue("cRump45", false);
                    setValue("cPump45", false);
                    setValue("cGump45", false);
                    setValue("cBump45", false);
                    setValue("cWump45", false);
                    setValue("cYump45", true);
                    cPrefs.write(String.valueOf(ump45.getText()), YELLOW);
                    ump45.setTextColor(Color.parseColor("#ffffff00"));
                    ump45.setChecked(false);
                    if (!ump45.isChecked()) {
                        setValue(String.valueOf(ump45.getText()), true);
                    }
                }

                if (uzi.isChecked()) {
                    setValue("cRuzi", false);
                    setValue("cPuzi", false);
                    setValue("cGuzi", false);
                    setValue("cBuzi", false);
                    setValue("cWuzi", false);
                    setValue("cYuzi", true);
                    cPrefs.write(String.valueOf(uzi.getText()), YELLOW);
                    uzi.setTextColor(Color.parseColor("#ffffff00"));
                    uzi.setChecked(false);
                    if (!uzi.isChecked()) {
                        setValue(String.valueOf(uzi.getText()), true);
                    }
                }

                if (vss.isChecked()) {
                    setValue("cRvss", false);
                    setValue("cPvss", false);
                    setValue("cGvss", false);
                    setValue("cBvss", false);
                    setValue("cWvss", false);
                    setValue("cYvss", true);
                    cPrefs.write(String.valueOf(vss.getText()), YELLOW);
                    vss.setTextColor(Color.parseColor("#ffffff00"));
                    vss.setChecked(false);
                    if (!vss.isChecked()) {
                        setValue(String.valueOf(vss.getText()), true);
                    }
                }

                if (vector.isChecked()) {
                    setValue("cRvect", false);
                    setValue("cPvect", false);
                    setValue("cGvect", false);
                    setValue("cBvect", false);
                    setValue("cWvect", false);
                    setValue("cYvect", true);
                    cPrefs.write(String.valueOf(vector.getText()), YELLOW);
                    vector.setTextColor(Color.parseColor("#ffffff00"));
                    vector.setChecked(false);
                    if (!vector.isChecked()) {
                        setValue(String.valueOf(vector.getText()), true);
                    }
                }

                if (win94.isChecked()) {
                    setValue("cRwinchester", false);
                    setValue("cPwinchester", false);
                    setValue("cGwinchester", false);
                    setValue("cBwinchester", false);
                    setValue("cWwinchester", false);
                    setValue("cYwinchester", true);
                    cPrefs.write(String.valueOf(win94.getText()), YELLOW);
                    win94.setTextColor(Color.parseColor("#ffffff00"));
                    win94.setChecked(false);
                    if (!win94.isChecked()) {
                        setValue(String.valueOf(win94.getText()), true);
                    }
                }
            }
        }); //ItemColorYellow

        clrWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dacia.isChecked()) {
                    setValue("clrRedDacia", false);
                    setValue("clrPinkDacia", false);
                    setValue("clrGreenDacia", false);
                    setValue("clrBlueDacia", false);
                    setValue("clrWhiteDacia", true);
                    setValue("clrYellowDacia", false);
                    cPrefs.write(String.valueOf(Dacia.getText()), WHITE);
                    Dacia.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Dacia.setChecked(false);
                    if (!Dacia.isChecked()) {
                        setValue(String.valueOf(Dacia.getText()), true);
                    }
                }
                if (UAZ.isChecked()) {
                    setValue("clrRedUAZ", false);
                    setValue("clrPinkUAZ", false);
                    setValue("clrGreenUAZ", false);
                    setValue("clrBlueUAZ", false);
                    setValue("clrWhiteUAZ", true);
                    setValue("clrYellowUAZ", false);
                    cPrefs.write(String.valueOf(UAZ.getText()), WHITE);
                    UAZ.setTextColor(Color.parseColor("#ffc0c0c0"));
                    UAZ.setChecked(false);
                    if (!UAZ.isChecked()) {
                        setValue(String.valueOf(UAZ.getText()), true);
                    }
                }
                if (Buggy.isChecked()) {
                    setValue("clrRedBuggy", false);
                    setValue("clrPinkBuggy", false);
                    setValue("clrGreenBuggy", false);
                    setValue("clrBlueBuggy", false);
                    setValue("clrWhiteBuggy", true);
                    setValue("clrYellowBuggy", false);
                    cPrefs.write(String.valueOf(Buggy.getText()), WHITE);
                    Buggy.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Buggy.setChecked(false);
                    if (!Buggy.isChecked()) {
                        setValue(String.valueOf(Buggy.getText()), true);
                    }
                }
                if (Bike.isChecked()) {
                    setValue("clrRedBike", false);
                    setValue("clrPinkBike", false);
                    setValue("clrGreenBike", false);
                    setValue("clrBlueBike", false);
                    setValue("clrWhiteBike", true);
                    setValue("clrYellowBike", false);
                    cPrefs.write(String.valueOf(Bike.getText()), WHITE);
                    Bike.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Bike.setChecked(false);
                    if (!Bike.isChecked()) {
                        setValue(String.valueOf(Bike.getText()), true);
                    }
                }
                if (Jet.isChecked()) {
                    setValue("clrRedAquaRail", false);
                    setValue("clrPinkAquaRail", false);
                    setValue("clrGreenAquaRail", false);
                    setValue("clrBlueAquaRail", false);
                    setValue("clrWhiteAquaRail", true);
                    setValue("clrYellowAquaRail", false);
                    cPrefs.write(String.valueOf(Jet.getText()), WHITE);
                    Jet.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Jet.setChecked(false);
                    if (!Jet.isChecked()) {
                        setValue(String.valueOf(Jet.getText()), true);
                    }
                }
                if (Boat.isChecked()) {
                    setValue("clrRedBoat", false);
                    setValue("clrPinkBoat", false);
                    setValue("clrGreenBoat", false);
                    setValue("clrBlueBoat", false);
                    setValue("clrWhiteBoat", true);
                    setValue("clrYellowBoat", false);
                    cPrefs.write(String.valueOf(Boat.getText()), WHITE);
                    Boat.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Boat.setChecked(false);
                    if (!Boat.isChecked()) {
                        setValue(String.valueOf(Boat.getText()), true);
                    }
                }
                if (Bus.isChecked()) {
                    setValue("clrRedBus", false);
                    setValue("clrPinkBus", false);
                    setValue("clrGreenBus", false);
                    setValue("clrBlueBus", false);
                    setValue("clrWhiteBus", true);
                    setValue("clrYellowBus", false);
                    cPrefs.write(String.valueOf(Bus.getText()), WHITE);
                    Bus.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Bus.setChecked(false);
                    if (!Bus.isChecked()) {
                        setValue(String.valueOf(Bus.getText()), true);
                    }
                }
                if (Mirado.isChecked()) {
                    setValue("clrRedMirado", false);
                    setValue("clrPinkMirado", false);
                    setValue("clrGreenMirado", false);
                    setValue("clrBlueMirado", false);
                    setValue("clrWhiteMirado", true);
                    setValue("clrYellowMirado", false);
                    cPrefs.write(String.valueOf(Mirado.getText()), WHITE);
                    Mirado.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Mirado.setChecked(false);
                    if (!Mirado.isChecked()) {
                        setValue(String.valueOf(Mirado.getText()), true);
                    }
                }
                if (Scooter.isChecked()) {
                    setValue("clrRedScooter", false);
                    setValue("clrPinkScooter", false);
                    setValue("clrGreenScooter", false);
                    setValue("clrBlueScooter", false);
                    setValue("clrWhiteScooter", true);
                    setValue("clrYellowScooter", false);
                    cPrefs.write(String.valueOf(Scooter.getText()), WHITE);
                    Scooter.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Scooter.setChecked(false);
                    if (!Scooter.isChecked()) {
                        setValue(String.valueOf(Scooter.getText()), true);
                    }
                }
                if (Rony.isChecked()) {
                    setValue("clrRedRony", false);
                    setValue("clrPinkRony", false);
                    setValue("clrGreenRony", false);
                    setValue("clrBlueRony", false);
                    setValue("clrWhiteRony", true);
                    setValue("clrYellowRony", false);
                    cPrefs.write(String.valueOf(Rony.getText()), WHITE);
                    Rony.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Rony.setChecked(false);
                    if (!Rony.isChecked()) {
                        setValue(String.valueOf(Rony.getText()), true);
                    }
                }
                if (Snowbike.isChecked()) {
                    setValue("clrRedSnowbike", false);
                    setValue("clrPinkSnowbike", false);
                    setValue("clrGreenSnowbike", false);
                    setValue("clrBlueSnowbike", false);
                    setValue("clrWhiteSnowbike", true);
                    setValue("clrYellowSnowbike", false);
                    cPrefs.write(String.valueOf(Snowbike.getText()), WHITE);
                    Snowbike.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Snowbike.setChecked(false);
                    if (!Snowbike.isChecked()) {
                        setValue(String.valueOf(Snowbike.getText()), true);
                    }
                }
                if (Snowmobile.isChecked()) {
                    setValue("clrRedSnowmobile", false);
                    setValue("clrPinkSnowmobile", false);
                    setValue("clrGreenSnowmobile", false);
                    setValue("clrBlueSnowmobile", false);
                    setValue("clrWhiteSnowmobile", true);
                    setValue("clrYellowSnowmobile", false);
                    cPrefs.write(String.valueOf(Snowmobile.getText()), WHITE);
                    Snowmobile.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Snowmobile.setChecked(false);
                    if (!Snowmobile.isChecked()) {
                        setValue(String.valueOf(Snowmobile.getText()), true);
                    }
                }
                if (Tempo.isChecked()) {
                    setValue("clrRedTuk", false);
                    setValue("clrPinkTuk", false);
                    setValue("clrGreenTuk", false);
                    setValue("clrBlueTuk", false);
                    setValue("clrWhiteTuk", true);
                    setValue("clrYellowTuk", false);
                    cPrefs.write(String.valueOf(Tempo.getText()), WHITE);
                    Tempo.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Tempo.setChecked(false);
                    if (!Tempo.isChecked()) {
                        setValue(String.valueOf(Tempo.getText()), true);
                    }
                }
                if (Truck.isChecked()) {
                    setValue("clrRedTruck", false);
                    setValue("clrPinkTruck", false);
                    setValue("clrGreenTruck", false);
                    setValue("clrBlueTruck", false);
                    setValue("clrWhiteTruck", true);
                    setValue("clrYellowTruck", false);
                    cPrefs.write(String.valueOf(Truck.getText()), WHITE);
                    Truck.setTextColor(Color.parseColor("#ffc0c0c0"));
                    Truck.setChecked(false);
                    if (!Truck.isChecked()) {
                        setValue(String.valueOf(Truck.getText()), true);
                    }
                }
                if (BRDM.isChecked()) {
                    setValue("clrRedBRDM", false);
                    setValue("clrPinkBRDM", false);
                    setValue("clrGreenBRDM", false);
                    setValue("clrBlueBRDM", false);
                    setValue("clrWhiteBRDM", true);
                    setValue("clrYellowBRDM", false);
                    cPrefs.write(String.valueOf(BRDM.getText()), WHITE);
                    BRDM.setTextColor(Color.parseColor("#ffc0c0c0"));
                    BRDM.setChecked(false);
                    if (!BRDM.isChecked()) {
                        setValue(String.valueOf(BRDM.getText()), true);
                    }
                }
                if (LadaNiva.isChecked()) {
                    setValue("clrRedLadaNiva", false);
                    setValue("clrPinkLadaNiva", false);
                    setValue("clrGreenLadaNiva", false);
                    setValue("clrBlueLadaNiva", false);
                    setValue("clrWhiteLadaNiva", true);
                    setValue("clrYellowLadaNiva", false);
                    cPrefs.write(String.valueOf(LadaNiva.getText()), WHITE);
                    LadaNiva.setTextColor(Color.parseColor("#ffc0c0c0"));
                    LadaNiva.setChecked(false);
                    if (!LadaNiva.isChecked()) {
                        setValue(String.valueOf(LadaNiva.getText()), true);
                    }
                }
                if (MonsterTruck.isChecked()) {
                    setValue("clrRedMonster", false);
                    setValue("clrPinkMonster", false);
                    setValue("clrGreenMonster", false);
                    setValue("clrBlueMonster", false);
                    setValue("clrWhiteMonster", true);
                    setValue("clrYellowMonster", false);
                    cPrefs.write(String.valueOf(MonsterTruck.getText()), WHITE);
                    MonsterTruck.setTextColor(Color.parseColor("#ffc0c0c0"));
                    MonsterTruck.setChecked(false);
                    if (!MonsterTruck.isChecked()) {
                        setValue(String.valueOf(MonsterTruck.getText()), true);
                    }
                }
                if (CoupeRB.isChecked()) {
                    setValue("clrRedCoupeRB", false);
                    setValue("clrPinkCoupeRB", false);
                    setValue("clrGreenCoupeRB", false);
                    setValue("clrBlueCoupeRB", false);
                    setValue("clrWhiteCoupeRB", true);
                    setValue("clrYellowCoupeRB", false);
                    cPrefs.write(String.valueOf(CoupeRB.getText()), WHITE);
                    CoupeRB.setTextColor(Color.parseColor("#ffc0c0c0"));
                    CoupeRB.setChecked(false);
                    if (!CoupeRB.isChecked()) {
                        setValue(String.valueOf(CoupeRB.getText()), true);
                    }
                }
                if (UTV.isChecked()) {
                    setValue("clrRedUTV", false);
                    setValue("clrPinkUTV", false);
                    setValue("clrGreenUTV", false);
                    setValue("clrBlueUTV", false);
                    setValue("clrWhiteUTV", true);
                    setValue("clrYellowUTV", false);
                    cPrefs.write(String.valueOf(UTV.getText()), WHITE);
                    UTV.setTextColor(Color.parseColor("#ffc0c0c0"));
                    UTV.setChecked(false);
                    if (!UTV.isChecked()) {
                        setValue(String.valueOf(UTV.getText()), true);
                    }
                }

                if (am300.isChecked()) {
                    setValue("cR300mm", false);
                    setValue("cP300mm", false);
                    setValue("cG300mm", false);
                    setValue("cB300mm", false);
                    setValue("cW300mm", true);
                    setValue("cY300mm", false);
                    cPrefs.write(String.valueOf(am300.getText()), WHITE);
                    am300.setTextColor(Color.parseColor("#ffc0c0c0"));
                    am300.setChecked(false);
                    if (!am300.isChecked()) {
                        setValue(String.valueOf(am300.getText()), true);
                    }
                }

                if (am45.isChecked()) {
                    setValue("cRam45", false);
                    setValue("cPam45", false);
                    setValue("cGam45", false);
                    setValue("cBam45", false);
                    setValue("cWam45", true);
                    setValue("cYam45", false);
                    cPrefs.write(String.valueOf(am45.getText()), WHITE);
                    am45.setTextColor(Color.parseColor("#ffc0c0c0"));
                    am45.setChecked(false);
                    if (!am45.isChecked()) {
                        setValue(String.valueOf(am45.getText()), true);
                    }
                }

                if (am12g.isChecked()) {
                    setValue("cRam12g", false);
                    setValue("cPam12g", false);
                    setValue("cGam12g", false);
                    setValue("cBam12g", false);
                    setValue("cWam12g", true);
                    setValue("cYam12g", false);
                    cPrefs.write(String.valueOf(am12g.getText()), WHITE);
                    am12g.setTextColor(Color.parseColor("#ffc0c0c0"));
                    am12g.setChecked(false);
                    if (!am12g.isChecked()) {
                        setValue(String.valueOf(am12g.getText()), true);
                    }
                }

                if (am556.isChecked()) {
                    setValue("cRam556", false);
                    setValue("cPam556", false);
                    setValue("cGam556", false);
                    setValue("cBam556", false);
                    setValue("cWam556", true);
                    setValue("cYam556", false);
                    cPrefs.write(String.valueOf(am556.getText()), WHITE);
                    am556.setTextColor(Color.parseColor("#ffc0c0c0"));
                    am556.setChecked(false);
                    if (!am556.isChecked()) {
                        setValue(String.valueOf(am556.getText()), true);
                    }
                }

                if (am762.isChecked()) {
                    setValue("cRam762", false);
                    setValue("cPam762", false);
                    setValue("cGam762", false);
                    setValue("cBam762", false);
                    setValue("cWam762", true);
                    setValue("cYam762", false);
                    cPrefs.write(String.valueOf(am762.getText()), WHITE);
                    am762.setTextColor(Color.parseColor("#ffc0c0c0"));
                    am762.setChecked(false);
                    if (!am762.isChecked()) {
                        setValue(String.valueOf(am762.getText()), true);
                    }
                }

                if (am9mm.isChecked()) {
                    setValue("cRam9mm", false);
                    setValue("cPam9mm", false);
                    setValue("cGam9mm", false);
                    setValue("cBam9mm", false);
                    setValue("cWam9mm", true);
                    setValue("cYam9mm", false);
                    cPrefs.write(String.valueOf(am9mm.getText()), WHITE);
                    am9mm.setTextColor(Color.parseColor("#ffc0c0c0"));
                    am9mm.setChecked(false);
                    if (!am9mm.isChecked()) {
                        setValue(String.valueOf(am9mm.getText()), true);
                    }
                }

                if (armorLvl2.isChecked()) {
                    setValue("cRar2", false);
                    setValue("cPar2", false);
                    setValue("cGar2", false);
                    setValue("cBar2", false);
                    setValue("cWar2", true);
                    setValue("cYar2", false);
                    cPrefs.write(String.valueOf(armorLvl2.getText()), WHITE);
                    armorLvl2.setTextColor(Color.parseColor("#ffc0c0c0"));
                    armorLvl2.setChecked(false);
                    if (!armorLvl2.isChecked()) {
                        setValue(String.valueOf(armorLvl2.getText()), true);
                    }
                }

                if (armorLvl3.isChecked()) {
                    setValue("cRar3", false);
                    setValue("cPar3", false);
                    setValue("cGar3", false);
                    setValue("cBar3", false);
                    setValue("cWar3", true);
                    setValue("cYar3", false);
                    cPrefs.write(String.valueOf(armorLvl3.getText()), WHITE);
                    armorLvl3.setTextColor(Color.parseColor("#ffc0c0c0"));
                    armorLvl3.setChecked(false);
                    if (!armorLvl3.isChecked()) {
                        setValue(String.valueOf(armorLvl3.getText()), true);
                    }
                }

                if (helmetLvl2.isChecked()) {
                    setValue("cRhel2", false);
                    setValue("cPhel2", false);
                    setValue("cGhel2", false);
                    setValue("cBhel2", false);
                    setValue("cWhel2", true);
                    setValue("cYhel2", false);
                    cPrefs.write(String.valueOf(helmetLvl2.getText()), WHITE);
                    helmetLvl2.setTextColor(Color.parseColor("#ffc0c0c0"));
                    helmetLvl2.setChecked(false);
                    if (!helmetLvl2.isChecked()) {
                        setValue(String.valueOf(helmetLvl2.getText()), true);
                    }
                }

                if (helmetLvl3.isChecked()) {
                    setValue("cRhel3", false);
                    setValue("cPhel3", false);
                    setValue("cGhel3", false);
                    setValue("cBhel3", false);
                    setValue("cWhel3", true);
                    setValue("cYhel3", false);
                    cPrefs.write(String.valueOf(helmetLvl3.getText()), WHITE);
                    helmetLvl3.setTextColor(Color.parseColor("#ffc0c0c0"));
                    helmetLvl3.setChecked(false);
                    if (!helmetLvl3.isChecked()) {
                        setValue(String.valueOf(helmetLvl3.getText()), true);
                    }
                }

                if (bandage.isChecked()) {
                    setValue("cRban", false);
                    setValue("cPban", false);
                    setValue("cGban", false);
                    setValue("cBban", false);
                    setValue("cWban", true);
                    setValue("cYban", false);
                    cPrefs.write(String.valueOf(bandage.getText()), WHITE);
                    bandage.setTextColor(Color.parseColor("#ffc0c0c0"));
                    bandage.setChecked(false);
                    if (!bandage.isChecked()) {
                        setValue(String.valueOf(bandage.getText()), true);
                    }
                }

                if (drink.isChecked()) {
                    setValue("cRdrink", false);
                    setValue("cPdrink", false);
                    setValue("cGdrink", false);
                    setValue("cBdrink", false);
                    setValue("cWdrink", true);
                    setValue("cYdrink", false);
                    cPrefs.write(String.valueOf(drink.getText()), WHITE);
                    drink.setTextColor(Color.parseColor("#ffc0c0c0"));
                    drink.setChecked(false);
                    if (!drink.isChecked()) {
                        setValue(String.valueOf(drink.getText()), true);
                    }
                }

                if (firstAir.isChecked()) {
                    setValue("cRfa", false);
                    setValue("cPfa", false);
                    setValue("cGfa", false);
                    setValue("cBfa", false);
                    setValue("cWfa", true);
                    setValue("cYfa", false);
                    cPrefs.write(String.valueOf(firstAir.getText()), WHITE);
                    firstAir.setTextColor(Color.parseColor("#ffc0c0c0"));
                    firstAir.setChecked(false);
                    if (!firstAir.isChecked()) {
                        setValue(String.valueOf(firstAir.getText()), true);
                    }
                }

                if (injection.isChecked()) {
                    setValue("cRin", false);
                    setValue("cPin", false);
                    setValue("cGin", false);
                    setValue("cBin", false);
                    setValue("cWin", true);
                    setValue("cYin", false);
                    cPrefs.write(String.valueOf(injection.getText()), WHITE);
                    injection.setTextColor(Color.parseColor("#ffc0c0c0"));
                    injection.setChecked(false);
                    if (!injection.isChecked()) {
                        setValue(String.valueOf(injection.getText()), true);
                    }
                }

                if (pills.isChecked()) {
                    setValue("cRpills", false);
                    setValue("cPpills", false);
                    setValue("cGpills", false);
                    setValue("cBpills", false);
                    setValue("cWpills", true);
                    setValue("cYpills", false);
                    cPrefs.write(String.valueOf(pills.getText()), WHITE);
                    pills.setTextColor(Color.parseColor("#ffc0c0c0"));
                    pills.setChecked(false);
                    if (!pills.isChecked()) {
                        setValue(String.valueOf(pills.getText()), true);
                    }
                }

                //Misk
                if (airCraft.isChecked()) {
                    setValue("cRac", false);
                    setValue("cPac", false);
                    setValue("cGac", false);
                    setValue("cBac", false);
                    setValue("cWac", true);
                    setValue("cYac", false);
                    cPrefs.write(String.valueOf(airCraft.getText()), WHITE);
                    airCraft.setTextColor(Color.parseColor("#ffc0c0c0"));
                    airCraft.setChecked(false);
                    if (!airCraft.isChecked()) {
                        setValue(String.valueOf(airCraft.getText()), true);
                    }
                }

                if (airDrop.isChecked()) {
                    setValue("cRairD", false);
                    setValue("cPairD", false);
                    setValue("cGairD", false);
                    setValue("cBairD", false);
                    setValue("cWairD", true);
                    setValue("cYairD", false);
                    cPrefs.write(String.valueOf(airDrop.getText()), WHITE);
                    airDrop.setTextColor(Color.parseColor("#ffc0c0c0"));
                    airDrop.setChecked(false);
                    if (!airDrop.isChecked()) {
                        setValue(String.valueOf(airDrop.getText()), true);
                    }
                }

                if (bag2.isChecked()) {
                    setValue("cRb2", false);
                    setValue("cPb2", false);
                    setValue("cGb2", false);
                    setValue("cBb2", false);
                    setValue("cWb2", true);
                    setValue("cYb2", false);
                    cPrefs.write(String.valueOf(bag2.getText()), WHITE);
                    bag2.setTextColor(Color.parseColor("#ffc0c0c0"));
                    bag2.setChecked(false);
                    if (!bag2.isChecked()) {
                        setValue(String.valueOf(bag2.getText()), true);
                    }
                }

                if (bag3.isChecked()) {
                    setValue("cRb3", false);
                    setValue("cPb3", false);
                    setValue("cGb3", false);
                    setValue("cBb3", false);
                    setValue("cWb3", true);
                    setValue("cYb3", false);
                    cPrefs.write(String.valueOf(bag3.getText()), WHITE);
                    bag3.setTextColor(Color.parseColor("#ffc0c0c0"));
                    bag3.setChecked(false);
                    if (!bag3.isChecked()) {
                        setValue(String.valueOf(bag3.getText()), true);
                    }
                }

                if (compensator.isChecked()) {
                    setValue("cRcomp", false);
                    setValue("cPcomp", false);
                    setValue("cGcomp", false);
                    setValue("cBcomp", false);
                    setValue("cWcomp", true);
                    setValue("cYcomp", false);
                    cPrefs.write(String.valueOf(compensator.getText()), WHITE);
                    compensator.setTextColor(Color.parseColor("#ffc0c0c0"));
                    compensator.setChecked(false);
                    if (!compensator.isChecked()) {
                        setValue(String.valueOf(compensator.getText()), true);
                    }
                }

                if (loot.isChecked()) {
                    setValue("cRloot", false);
                    setValue("cPloot", false);
                    setValue("cGloot", false);
                    setValue("cBloot", false);
                    setValue("cWloot", true);
                    setValue("cYloot", false);
                    cPrefs.write(String.valueOf(loot.getText()), WHITE);
                    loot.setTextColor(Color.parseColor("#ffc0c0c0"));
                    loot.setChecked(false);
                    if (!loot.isChecked()) {
                        setValue(String.valueOf(loot.getText()), true);
                    }
                }

                if (supressor.isChecked()) {
                    setValue("cRsupp", false);
                    setValue("cPsupp", false);
                    setValue("cGsupp", false);
                    setValue("cBsupp", false);
                    setValue("cWsupp", true);
                    setValue("cYsupp", false);
                    cPrefs.write(String.valueOf(supressor.getText()), WHITE);
                    supressor.setTextColor(Color.parseColor("#ffc0c0c0"));
                    supressor.setChecked(false);
                    if (!supressor.isChecked()) {
                        setValue(String.valueOf(supressor.getText()), true);
                    }
                }

                //Weapons
                if (x2.isChecked()) {
                    setValue("cRx2", false);
                    setValue("cPx2", false);
                    setValue("cGx2", false);
                    setValue("cBx2", false);
                    setValue("cWx2", true);
                    setValue("cYx2", false);
                    cPrefs.write(String.valueOf(x2.getText()), WHITE);
                    x2.setTextColor(Color.parseColor("#ffc0c0c0"));
                    x2.setChecked(false);
                    if (!x2.isChecked()) {
                        setValue(String.valueOf(x2.getText()), true);
                    }
                }

                if (x3.isChecked()) {
                    setValue("cRx3", false);
                    setValue("cPx3", false);
                    setValue("cGx3", false);
                    setValue("cBx3", false);
                    setValue("cWx3", true);
                    setValue("cYx3", false);
                    cPrefs.write(String.valueOf(x3.getText()), WHITE);
                    x3.setTextColor(Color.parseColor("#ffc0c0c0"));
                    x3.setChecked(false);
                    if (!x3.isChecked()) {
                        setValue(String.valueOf(x3.getText()), true);
                    }
                }

                if (x4.isChecked()) {
                    setValue("cRx4", false);
                    setValue("cPx4", false);
                    setValue("cGx4", false);
                    setValue("cBx4", false);
                    setValue("cWx4", true);
                    setValue("cYx4", false);
                    cPrefs.write(String.valueOf(x4.getText()), WHITE);
                    x4.setTextColor(Color.parseColor("#ffc0c0c0"));
                    x4.setChecked(false);
                    if (!x4.isChecked()) {
                        setValue(String.valueOf(x4.getText()), true);
                    }
                }

                if (x6.isChecked()) {
                    setValue("cRx6", false);
                    setValue("cPx6", false);
                    setValue("cGx6", false);
                    setValue("cBx6", false);
                    setValue("cWx6", true);
                    setValue("cYx6", false);
                    cPrefs.write(String.valueOf(x6.getText()), WHITE);
                    x6.setTextColor(Color.parseColor("#ffc0c0c0"));
                    x6.setChecked(false);
                    if (!x6.isChecked()) {
                        setValue(String.valueOf(x6.getText()), true);
                    }
                }

                if (x8.isChecked()) {
                    setValue("cRx8", false);
                    setValue("cPx8", false);
                    setValue("cGx8", false);
                    setValue("cBx8", false);
                    setValue("cWx8", true);
                    setValue("cYx8", false);
                    cPrefs.write(String.valueOf(x8.getText()), WHITE);
                    x8.setTextColor(Color.parseColor("#ffc0c0c0"));
                    x8.setChecked(false);
                    if (!x8.isChecked()) {
                        setValue(String.valueOf(x8.getText()), true);
                    }
                }

                //real
                if (k98.isChecked()) {
                    setValue("cRk98", false);
                    setValue("cPk98", false);
                    setValue("cGk98", false);
                    setValue("cBk98", false);
                    setValue("cWk98", true);
                    setValue("cYk98", false);
                    cPrefs.write(String.valueOf(k98.getText()), WHITE);
                    k98.setTextColor(Color.parseColor("#ffc0c0c0"));
                    k98.setChecked(false);
                    if (!k98.isChecked()) {
                        setValue(String.valueOf(k98.getText()), true);
                    }
                }

                if (akm.isChecked()) {
                    setValue("cRakm", false);
                    setValue("cPakm", false);
                    setValue("cGakm", false);
                    setValue("cBakm", false);
                    setValue("cWakm", true);
                    setValue("cYakm", false);
                    cPrefs.write(String.valueOf(akm.getText()), WHITE);
                    akm.setTextColor(Color.parseColor("#ffc0c0c0"));
                    akm.setChecked(false);
                    if (!akm.isChecked()) {
                        setValue(String.valueOf(akm.getText()), true);
                    }
                }

                if (aug.isChecked()) {
                    setValue("cRaug", false);
                    setValue("cPaug", false);
                    setValue("cGaug", false);
                    setValue("cBaug", false);
                    setValue("cWaug", true);
                    setValue("cYaug", false);
                    cPrefs.write(String.valueOf(aug.getText()), WHITE);
                    aug.setTextColor(Color.parseColor("#ffc0c0c0"));
                    aug.setChecked(false);
                    if (!aug.isChecked()) {
                        setValue(String.valueOf(aug.getText()), true);
                    }
                }

                if (awm.isChecked()) {
                    setValue("cRawm", false);
                    setValue("cPawm", false);
                    setValue("cGawm", false);
                    setValue("cBawm", false);
                    setValue("cWawm", true);
                    setValue("cYawm", false);
                    cPrefs.write(String.valueOf(awm.getText()), WHITE);
                    awm.setTextColor(Color.parseColor("#ffc0c0c0"));
                    awm.setChecked(false);
                    if (!awm.isChecked()) {
                        setValue(String.valueOf(awm.getText()), true);
                    }
                }

                if (bow.isChecked()) {
                    setValue("cRbow", false);
                    setValue("cPbow", false);
                    setValue("cGbow", false);
                    setValue("cBbow", false);
                    setValue("cWbow", true);
                    setValue("cYbow", false);
                    cPrefs.write(String.valueOf(bow.getText()), WHITE);
                    bow.setTextColor(Color.parseColor("#ffc0c0c0"));
                    bow.setChecked(false);
                    if (!bow.isChecked()) {
                        setValue(String.valueOf(bow.getText()), true);
                    }
                }

                if (dp12.isChecked()) {
                    setValue("cRdp12", false);
                    setValue("cPdp12", false);
                    setValue("cGdp12", false);
                    setValue("cBdp12", false);
                    setValue("cWdp12", true);
                    setValue("cYdp12", false);
                    cPrefs.write(String.valueOf(dp12.getText()), WHITE);
                    dp12.setTextColor(Color.parseColor("#ffc0c0c0"));
                    dp12.setChecked(false);
                    if (!dp12.isChecked()) {
                        setValue(String.valueOf(dp12.getText()), true);
                    }
                }

                if (dp28.isChecked()) {
                    setValue("cRdp28", false);
                    setValue("cPdp28", false);
                    setValue("cGdp28", false);
                    setValue("cBdp28", false);
                    setValue("cWdp28", true);
                    setValue("cYdp28", false);
                    cPrefs.write(String.valueOf(dp28.getText()), WHITE);
                    dp28.setTextColor(Color.parseColor("#ffc0c0c0"));
                    dp28.setChecked(false);
                    if (!dp28.isChecked()) {
                        setValue(String.valueOf(dp28.getText()), true);
                    }
                }

                if (deagle.isChecked()) {
                    setValue("cRdeg", false);
                    setValue("cPdeg", false);
                    setValue("cGdeg", false);
                    setValue("cBdeg", false);
                    setValue("cWdeg", true);
                    setValue("cYdeg", false);
                    cPrefs.write(String.valueOf(deagle.getText()), WHITE);
                    deagle.setTextColor(Color.parseColor("#ffc0c0c0"));
                    deagle.setChecked(false);
                    if (!deagle.isChecked()) {
                        setValue(String.valueOf(deagle.getText()), true);
                    }
                }

                if (redDot.isChecked()) {
                    setValue("cRredDot", false);
                    setValue("cPredDot", false);
                    setValue("cGredDot", false);
                    setValue("cBredDot", false);
                    setValue("cWredDot", true);
                    setValue("cYredDot", false);
                    cPrefs.write(String.valueOf(redDot.getText()), WHITE);
                    redDot.setTextColor(Color.parseColor("#ffc0c0c0"));
                    redDot.setChecked(false);
                    if (!redDot.isChecked()) {
                        setValue(String.valueOf(redDot.getText()), true);
                    }
                }

                if (flare.isChecked()) {
                    setValue("cRflare", false);
                    setValue("cPflare", false);
                    setValue("cGflare", false);
                    setValue("cBflare", false);
                    setValue("cWflare", true);
                    setValue("cYflare", false);
                    cPrefs.write(String.valueOf(flare.getText()), WHITE);
                    flare.setTextColor(Color.parseColor("#ffc0c0c0"));
                    flare.setChecked(false);
                    if (!flare.isChecked()) {
                        setValue(String.valueOf(flare.getText()), true);
                    }
                }

                if (g36.isChecked()) {
                    setValue("cRg36", false);
                    setValue("cPg36", false);
                    setValue("cGg36", false);
                    setValue("cBg36", false);
                    setValue("cWg36", true);
                    setValue("cYg36", false);
                    cPrefs.write(String.valueOf(g36.getText()), WHITE);
                    g36.setTextColor(Color.parseColor("#ffc0c0c0"));
                    g36.setChecked(false);
                    if (!g36.isChecked()) {
                        setValue(String.valueOf(g36.getText()), true);
                    }
                }

                if (grenade.isChecked()) {
                    setValue("cRgrenade", false);
                    setValue("cPgrenade", false);
                    setValue("cGgrenade", false);
                    setValue("cBgrenade", false);
                    setValue("cWgrenade", true);
                    setValue("cYgrenade", false);
                    cPrefs.write(String.valueOf(grenade.getText()), WHITE);
                    grenade.setTextColor(Color.parseColor("#ffc0c0c0"));
                    grenade.setChecked(false);
                    if (!grenade.isChecked()) {
                        setValue(String.valueOf(grenade.getText()), true);
                    }
                }

                if (groza.isChecked()) {
                    setValue("cRgroza", false);
                    setValue("cPgroza", false);
                    setValue("cGgroza", false);
                    setValue("cBgroza", false);
                    setValue("cWgroza", true);
                    setValue("cYgroza", false);
                    cPrefs.write(String.valueOf(groza.getText()), WHITE);
                    groza.setTextColor(Color.parseColor("#ffc0c0c0"));
                    groza.setChecked(false);
                    if (!groza.isChecked()) {
                        setValue(String.valueOf(groza.getText()), true);
                    }
                }

                if (hollo.isChecked()) {
                    setValue("cRhollo", false);
                    setValue("cPhollo", false);
                    setValue("cGhollo", false);
                    setValue("cBhollo", false);
                    setValue("cWhollo", true);
                    setValue("cYhollo", false);
                    cPrefs.write(String.valueOf(hollo.getText()), WHITE);
                    hollo.setTextColor(Color.parseColor("#ffc0c0c0"));
                    hollo.setChecked(false);
                    if (!hollo.isChecked()) {
                        setValue(String.valueOf(hollo.getText()), true);
                    }
                }

                if (m1014.isChecked()) {
                    setValue("cRm1014", false);
                    setValue("cPm1014", false);
                    setValue("cGm1014", false);
                    setValue("cBm1014", false);
                    setValue("cWm1014", true);
                    setValue("cYm1014", false);
                    cPrefs.write(String.valueOf(m1014.getText()), WHITE);
                    m1014.setTextColor(Color.parseColor("#ffc0c0c0"));
                    m1014.setChecked(false);
                    if (!m1014.isChecked()) {
                        setValue(String.valueOf(m1014.getText()), true);
                    }
                }

                if (m16.isChecked()) {
                    setValue("cRm16", false);
                    setValue("cPm16", false);
                    setValue("cGm16", false);
                    setValue("cBm16", false);
                    setValue("cWm16", true);
                    setValue("cYm16", false);
                    cPrefs.write(String.valueOf(m16.getText()), WHITE);
                    m16.setTextColor(Color.parseColor("#ffc0c0c0"));
                    m16.setChecked(false);
                    if (!m16.isChecked()) {
                        setValue(String.valueOf(m16.getText()), true);
                    }
                }

                if (m24.isChecked()) {
                    setValue("cRm24", false);
                    setValue("cPm24", false);
                    setValue("cGm24", false);
                    setValue("cBm24", false);
                    setValue("cWm24", true);
                    setValue("cYm24", false);
                    cPrefs.write(String.valueOf(m24.getText()), WHITE);
                    m24.setTextColor(Color.parseColor("#ffc0c0c0"));
                    m24.setChecked(false);
                    if (!m24.isChecked()) {
                        setValue(String.valueOf(m24.getText()), true);
                    }
                }

                if (m249.isChecked()) {
                    setValue("cRm249", false);
                    setValue("cPm249", false);
                    setValue("cGm249", false);
                    setValue("cBm249", false);
                    setValue("cWm249", true);
                    setValue("cYm249", false);
                    cPrefs.write(String.valueOf(m249.getText()), WHITE);
                    m249.setTextColor(Color.parseColor("#ffc0c0c0"));
                    m249.setChecked(false);
                    if (!m249.isChecked()) {
                        setValue(String.valueOf(m249.getText()), true);
                    }
                }

                if (m4.isChecked()) {
                    setValue("cRm4", false);
                    setValue("cPm4", false);
                    setValue("cGm4", false);
                    setValue("cBm4", false);
                    setValue("cWm4", true);
                    setValue("cYm4", false);
                    cPrefs.write(String.valueOf(m4.getText()), WHITE);
                    m4.setTextColor(Color.parseColor("#ffc0c0c0"));
                    m4.setChecked(false);
                    if (!m4.isChecked()) {
                        setValue(String.valueOf(m4.getText()), true);
                    }
                }

                if (m762g.isChecked()) {
                    setValue("cRm762", false);
                    setValue("cPm762", false);
                    setValue("cGm762", false);
                    setValue("cBm762", false);
                    setValue("cWm762", true);
                    setValue("cYm762", false);
                    cPrefs.write(String.valueOf(m762g.getText()), WHITE);
                    m762g.setTextColor(Color.parseColor("#ffc0c0c0"));
                    m762g.setChecked(false);
                    if (!m762g.isChecked()) {
                        setValue(String.valueOf(m762g.getText()), true);
                    }
                }

                if (mk14.isChecked()) {
                    setValue("cRmk14", false);
                    setValue("cPmk14", false);
                    setValue("cGmk14", false);
                    setValue("cBmk14", false);
                    setValue("cWmk14", true);
                    setValue("cYmk14", false);
                    cPrefs.write(String.valueOf(mk14.getText()), WHITE);
                    mk14.setTextColor(Color.parseColor("#ffc0c0c0"));
                    mk14.setChecked(false);
                    if (!mk14.isChecked()) {
                        setValue(String.valueOf(mk14.getText()), true);
                    }
                }

                if (mk47.isChecked()) {
                    setValue("cRmk47", false);
                    setValue("cPmk47", false);
                    setValue("cGmk47", false);
                    setValue("cBmk47", false);
                    setValue("cWmk47", true);
                    setValue("cYmk47", false);
                    cPrefs.write(String.valueOf(mk47.getText()), WHITE);
                    mk47.setTextColor(Color.parseColor("#ffc0c0c0"));
                    mk47.setChecked(false);
                    if (!mk47.isChecked()) {
                        setValue(String.valueOf(mk47.getText()), true);
                    }
                }

                if (mp5k.isChecked()) {
                    setValue("cRmp5k", false);
                    setValue("cPmp5k", false);
                    setValue("cGmp5k", false);
                    setValue("cBmp5k", false);
                    setValue("cWmp5k", true);
                    setValue("cYmp5k", false);
                    cPrefs.write(String.valueOf(mp5k.getText()), WHITE);
                    mp5k.setTextColor(Color.parseColor("#ffc0c0c0"));
                    mp5k.setChecked(false);
                    if (!mp5k.isChecked()) {
                        setValue(String.valueOf(mp5k.getText()), true);
                    }
                }

                if (mini14.isChecked()) {
                    setValue("cRmini14", false);
                    setValue("cPmini14", false);
                    setValue("cGmini14", false);
                    setValue("cBmini14", false);
                    setValue("cWmini14", true);
                    setValue("cYmini14", false);
                    cPrefs.write(String.valueOf(mini14.getText()), WHITE);
                    mini14.setTextColor(Color.parseColor("#ffc0c0c0"));
                    mini14.setChecked(false);
                    if (!mini14.isChecked()) {
                        setValue(String.valueOf(mini14.getText()), true);
                    }
                }

                if (molotov.isChecked()) {
                    setValue("cRburn", false);
                    setValue("cPburn", false);
                    setValue("cGburn", false);
                    setValue("cBburn", false);
                    setValue("cWburn", true);
                    setValue("cYburn", false);
                    cPrefs.write(String.valueOf(molotov.getText()), WHITE);
                    molotov.setTextColor(Color.parseColor("#ffc0c0c0"));
                    molotov.setChecked(false);
                    if (!molotov.isChecked()) {
                        setValue(String.valueOf(molotov.getText()), true);
                    }
                }

                if (pp19.isChecked()) {
                    setValue("cRpp19", false);
                    setValue("cPpp19", false);
                    setValue("cGpp19", false);
                    setValue("cBpp19", false);
                    setValue("cWpp19", true);
                    setValue("cYpp19", false);
                    cPrefs.write(String.valueOf(pp19.getText()), WHITE);
                    pp19.setTextColor(Color.parseColor("#ffc0c0c0"));
                    pp19.setChecked(false);
                    if (!pp19.isChecked()) {
                        setValue(String.valueOf(pp19.getText()), true);
                    }
                }

                if (pan.isChecked()) {
                    setValue("cRpan", false);
                    setValue("cPpan", false);
                    setValue("cGpan", false);
                    setValue("cBpan", false);
                    setValue("cWpan", true);
                    setValue("cYpan", false);
                    cPrefs.write(String.valueOf(pan.getText()), WHITE);
                    pan.setTextColor(Color.parseColor("#ffc0c0c0"));
                    pan.setChecked(false);
                    if (!pan.isChecked()) {
                        setValue(String.valueOf(pan.getText()), true);
                    }
                }

                if (qbu.isChecked()) {
                    setValue("cRqbu", false);
                    setValue("cPqbu", false);
                    setValue("cGqbu", false);
                    setValue("cBqbu", false);
                    setValue("cWqbu", true);
                    setValue("cYqbu", false);
                    cPrefs.write(String.valueOf(qbu.getText()), WHITE);
                    qbu.setTextColor(Color.parseColor("#ffc0c0c0"));
                    qbu.setChecked(false);
                    if (!qbu.isChecked()) {
                        setValue(String.valueOf(qbu.getText()), true);
                    }
                }

                if (qbz.isChecked()) {
                    setValue("cRqbz", false);
                    setValue("cPqbz", false);
                    setValue("cGqbz", false);
                    setValue("cBqbz", false);
                    setValue("cWqbz", true);
                    setValue("cYqbz", false);
                    cPrefs.write(String.valueOf(qbz.getText()), WHITE);
                    qbz.setTextColor(Color.parseColor("#ffc0c0c0"));
                    qbz.setChecked(false);
                    if (!qbz.isChecked()) {
                        setValue(String.valueOf(qbz.getText()), true);
                    }
                }

                if (s12k.isChecked()) {
                    setValue("cRs12k", false);
                    setValue("cPs12k", false);
                    setValue("cGs12k", false);
                    setValue("cBs12k", false);
                    setValue("cWs12k", true);
                    setValue("cYs12k", false);
                    cPrefs.write(String.valueOf(s12k.getText()), WHITE);
                    s12k.setTextColor(Color.parseColor("#ffc0c0c0"));
                    s12k.setChecked(false);
                    if (!s12k.isChecked()) {
                        setValue(String.valueOf(s12k.getText()), true);
                    }
                }

                if (s1897.isChecked()) {
                    setValue("cRs1897", false);
                    setValue("cPs1897", false);
                    setValue("cGs1897", false);
                    setValue("cBs1897", false);
                    setValue("cWs1897", true);
                    setValue("cYs1897", false);
                    cPrefs.write(String.valueOf(s1897.getText()), WHITE);
                    s1897.setTextColor(Color.parseColor("#ffc0c0c0"));
                    s1897.setChecked(false);
                    if (!s1897.isChecked()) {
                        setValue(String.valueOf(s1897.getText()), true);
                    }
                }

                if (s686.isChecked()) {
                    setValue("cRs686", false);
                    setValue("cPs686", false);
                    setValue("cGs686", false);
                    setValue("cBs686", false);
                    setValue("cWs686", true);
                    setValue("cYs686", false);
                    cPrefs.write(String.valueOf(s686.getText()), WHITE);
                    s686.setTextColor(Color.parseColor("#ffc0c0c0"));
                    s686.setChecked(false);
                    if (!s686.isChecked()) {
                        setValue(String.valueOf(s686.getText()), true);
                    }
                }

                if (scarl.isChecked()) {
                    setValue("cRscar", false);
                    setValue("cPscar", false);
                    setValue("cGscar", false);
                    setValue("cBscar", false);
                    setValue("cWscar", true);
                    setValue("cYscar", false);
                    cPrefs.write(String.valueOf(scarl.getText()), WHITE);
                    scarl.setTextColor(Color.parseColor("#ffc0c0c0"));
                    scarl.setChecked(false);
                    if (!scarl.isChecked()) {
                        setValue(String.valueOf(scarl.getText()), true);
                    }
                }

                if (sks.isChecked()) {
                    setValue("cRsks", false);
                    setValue("cPsks", false);
                    setValue("cGsks", false);
                    setValue("cBsks", false);
                    setValue("cWsks", true);
                    setValue("cYsks", false);
                    cPrefs.write(String.valueOf(sks.getText()), WHITE);
                    sks.setTextColor(Color.parseColor("#ffc0c0c0"));
                    sks.setChecked(false);
                    if (!sks.isChecked()) {
                        setValue(String.valueOf(sks.getText()), true);
                    }
                }

                if (slr.isChecked()) {
                    setValue("cRslr", false);
                    setValue("cPslr", false);
                    setValue("cGslr", false);
                    setValue("cBslr", false);
                    setValue("cWslr", true);
                    setValue("cYslr", false);
                    cPrefs.write(String.valueOf(slr.getText()), WHITE);
                    slr.setTextColor(Color.parseColor("#ffc0c0c0"));
                    slr.setChecked(false);
                    if (!slr.isChecked()) {
                        setValue(String.valueOf(slr.getText()), true);
                    }
                }

                if (stunt.isChecked()) {
                    setValue("cRflash", false);
                    setValue("cPflash", false);
                    setValue("cGflash", false);
                    setValue("cBflash", false);
                    setValue("cWflash", true);
                    setValue("cYflash", false);
                    cPrefs.write(String.valueOf(stunt.getText()), WHITE);
                    stunt.setTextColor(Color.parseColor("#ffc0c0c0"));
                    stunt.setChecked(false);
                    if (!stunt.isChecked()) {
                        setValue(String.valueOf(stunt.getText()), true);
                    }
                }

                if (tommy.isChecked()) {
                    setValue("cRthomp", false);
                    setValue("cPthomp", false);
                    setValue("cGthomp", false);
                    setValue("cBthomp", false);
                    setValue("cWthomp", true);
                    setValue("cYthomp", false);
                    cPrefs.write(String.valueOf(tommy.getText()), WHITE);
                    tommy.setTextColor(Color.parseColor("#ffc0c0c0"));
                    tommy.setChecked(false);
                    if (!tommy.isChecked()) {
                        setValue(String.valueOf(tommy.getText()), true);
                    }
                }

                if (ump45.isChecked()) {
                    setValue("cRump45", false);
                    setValue("cPump45", false);
                    setValue("cGump45", false);
                    setValue("cBump45", false);
                    setValue("cWump45", true);
                    setValue("cYump45", false);
                    cPrefs.write(String.valueOf(ump45.getText()), WHITE);
                    ump45.setTextColor(Color.parseColor("#ffc0c0c0"));
                    ump45.setChecked(false);
                    if (!ump45.isChecked()) {
                        setValue(String.valueOf(ump45.getText()), true);
                    }
                }

                if (uzi.isChecked()) {
                    setValue("cRuzi", false);
                    setValue("cPuzi", false);
                    setValue("cGuzi", false);
                    setValue("cBuzi", false);
                    setValue("cWuzi", true);
                    setValue("cYuzi", false);
                    cPrefs.write(String.valueOf(uzi.getText()), WHITE);
                    uzi.setTextColor(Color.parseColor("#ffc0c0c0"));
                    uzi.setChecked(false);
                    if (!uzi.isChecked()) {
                        setValue(String.valueOf(uzi.getText()), true);
                    }
                }

                if (vss.isChecked()) {
                    setValue("cRvss", false);
                    setValue("cPvss", false);
                    setValue("cGvss", false);
                    setValue("cBvss", false);
                    setValue("cWvss", true);
                    setValue("cYvss", false);
                    cPrefs.write(String.valueOf(vss.getText()), WHITE);
                    vss.setTextColor(Color.parseColor("#ffc0c0c0"));
                    vss.setChecked(false);
                    if (!vss.isChecked()) {
                        setValue(String.valueOf(vss.getText()), true);
                    }
                }
                if (vector.isChecked()) {
                    setValue("cRvect", false);
                    setValue("cPvect", false);
                    setValue("cGvect", false);
                    setValue("cBvect", false);
                    setValue("cWvect", true);
                    setValue("cYvect", false);
                    cPrefs.write(String.valueOf(vector.getText()), WHITE);
                    vector.setTextColor(Color.parseColor("#ffc0c0c0"));
                    vector.setChecked(false);
                    if (!vector.isChecked()) {
                        setValue(String.valueOf(vector.getText()), true);
                    }
                }

                if (win94.isChecked()) {
                    setValue("cRwinchester", false);
                    setValue("cPwinchester", false);
                    setValue("cGwinchester", false);
                    setValue("cBwinchester", false);
                    setValue("cWwinchester", true);
                    setValue("cYwinchester", false);
                    cPrefs.write(String.valueOf(win94.getText()), WHITE);
                    win94.setTextColor(Color.parseColor("#ffc0c0c0"));
                    win94.setChecked(false);
                    if (!win94.isChecked()) {
                        setValue(String.valueOf(win94.getText()), true);
                    }
                }
            }
        }); //ItemColorWhite


        //displayItem swithc
        final Switch displayAllItems = mView.findViewById(R.id.showallitem);

        displayAllItems.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValue(String.valueOf(displayAllItems.getText()), displayAllItems.isChecked());
                if (b) {
                    //Vehicles
                    Buggy.setChecked(true);
                    UAZ.setChecked(true);
                    Trike.setChecked(true);
                    Bike.setChecked(true);
                    Dacia.setChecked(true);
                    Jet.setChecked(true);
                    Snowmobile.setChecked(true);
                    Truck.setChecked(true);
                    BRDM.setChecked(true);
                    CoupeRB.setChecked(true);
                    Boat.setChecked(true);
                    Bus.setChecked(true);
                    Mirado.setChecked(true);
                    Rony.setChecked(true);
                    Scooter.setChecked(true);
                    Tempo.setChecked(true);
                    MonsterTruck.setChecked(true);
                    UTV.setChecked(true);
                    Snowbike.setChecked(true);
                    LadaNiva.setChecked(true);

                    //Ammos
                    am300.setChecked(true);
                    am45.setChecked(true);
                    am12g.setChecked(true);
                    am556.setChecked(true);
                    am762.setChecked(true);
                    am9mm.setChecked(true);

                    //Weapons
                    x2.setChecked(true);
                    x3.setChecked(true);
                    x4.setChecked(true);
                    x6.setChecked(true);
                    x8.setChecked(true);
                    akm.setChecked(true);
                    k98.setChecked(true);
                    aug.setChecked(true);
                    awm.setChecked(true);
                    bow.setChecked(true);
                    dp12.setChecked(true);
                    dp28.setChecked(true);
                    deagle.setChecked(true);
                    redDot.setChecked(true);
                    flare.setChecked(true);
                    g36.setChecked(true);
                    grenade.setChecked(true);
                    groza.setChecked(true);
                    hollo.setChecked(true);
                    m1014.setChecked(true);
                    m16.setChecked(true);
                    m24.setChecked(true);
                    m249.setChecked(true);
                    m4.setChecked(true);
                    m762g.setChecked(true);
                    mk14.setChecked(true);
                    mk47.setChecked(true);
                    mp5k.setChecked(true);
                    mini14.setChecked(true);
                    molotov.setChecked(true);
                    pp19.setChecked(true);
                    pan.setChecked(true);
                    qbu.setChecked(true);
                    qbz.setChecked(true);
                    s12k.setChecked(true);
                    s1897.setChecked(true);
                    s686.setChecked(true);
                    scarl.setChecked(true);
                    sks.setChecked(true);
                    slr.setChecked(true);
                    stunt.setChecked(true);
                    tommy.setChecked(true);
                    ump45.setChecked(true);
                    uzi.setChecked(true);
                    vss.setChecked(true);
                    vector.setChecked(true);
                    win94.setChecked(true);


                    //Armors
                    armorLvl2.setChecked(true);
                    armorLvl3.setChecked(true);
                    helmetLvl2.setChecked(true);
                    helmetLvl3.setChecked(true);

                    //health
                    bandage.setChecked(true);
                    drink.setChecked(true);
                    firstAir.setChecked(true);
                    injection.setChecked(true);
                    pills.setChecked(true);

                    //misk
                    airCraft.setChecked(true);
                    airDrop.setChecked(true);
                    bag2.setChecked(true);
                    bag3.setChecked(true);
                    compensator.setChecked(true);
                    loot.setChecked(true);
                    supressor.setChecked(true);
                } else {
                    //Vehicles
                    Buggy.setChecked(false);
                    UAZ.setChecked(false);
                    Trike.setChecked(false);
                    Bike.setChecked(false);
                    Dacia.setChecked(false);
                    Jet.setChecked(false);
                    Snowmobile.setChecked(false);
                    Truck.setChecked(false);
                    BRDM.setChecked(false);
                    CoupeRB.setChecked(false);
                    Boat.setChecked(false);
                    Bus.setChecked(false);
                    Mirado.setChecked(false);
                    Rony.setChecked(false);
                    Scooter.setChecked(false);
                    Tempo.setChecked(false);
                    MonsterTruck.setChecked(false);
                    UTV.setChecked(false);
                    Snowbike.setChecked(false);
                    LadaNiva.setChecked(false);

                    //Ammos
                    am300.setChecked(false);
                    am45.setChecked(false);
                    am12g.setChecked(false);
                    am556.setChecked(false);
                    am762.setChecked(false);
                    am9mm.setChecked(false);

                    //Weapons
                    x2.setChecked(false);
                    x3.setChecked(false);
                    x4.setChecked(false);
                    x6.setChecked(false);
                    x8.setChecked(false);
                    akm.setChecked(false);
                    k98.setChecked(false);
                    aug.setChecked(false);
                    awm.setChecked(false);
                    bow.setChecked(false);
                    dp12.setChecked(false);
                    dp28.setChecked(false);
                    deagle.setChecked(false);
                    redDot.setChecked(false);
                    flare.setChecked(false);
                    g36.setChecked(false);
                    grenade.setChecked(false);
                    groza.setChecked(false);
                    hollo.setChecked(false);
                    m1014.setChecked(false);
                    m16.setChecked(false);
                    m24.setChecked(false);
                    m249.setChecked(false);
                    m4.setChecked(false);
                    m762g.setChecked(false);
                    mk14.setChecked(false);
                    mk47.setChecked(false);
                    mp5k.setChecked(false);
                    mini14.setChecked(false);
                    molotov.setChecked(false);
                    pp19.setChecked(false);
                    pan.setChecked(false);
                    qbu.setChecked(false);
                    qbz.setChecked(false);
                    s12k.setChecked(false);
                    s1897.setChecked(false);
                    s686.setChecked(false);
                    scarl.setChecked(false);
                    sks.setChecked(false);
                    slr.setChecked(false);
                    stunt.setChecked(false);
                    tommy.setChecked(false);
                    ump45.setChecked(false);
                    uzi.setChecked(false);
                    vss.setChecked(false);
                    vector.setChecked(false);
                    win94.setChecked(false);


                    //Armors
                    armorLvl2.setChecked(false);
                    armorLvl3.setChecked(false);
                    helmetLvl2.setChecked(false);
                    helmetLvl3.setChecked(false);

                    //health
                    bandage.setChecked(false);
                    drink.setChecked(false);
                    firstAir.setChecked(false);
                    injection.setChecked(false);
                    pills.setChecked(false);

                    //misk
                    airCraft.setChecked(false);
                    airDrop.setChecked(false);
                    bag2.setChecked(false);
                    bag3.setChecked(false);
                    compensator.setChecked(false);
                    loot.setChecked(false);
                    supressor.setChecked(false);
                }
            }
        });//DisplayAllItems

        final Button vehiclesAll = mView.findViewById(R.id.all1);
        final Button vehicleNone = mView.findViewById(R.id.none1);
        final Button ammoAll = mView.findViewById(R.id.all2);
        final Button ammoNone = mView.findViewById(R.id.none2);
        final Button weaponsAll = mView.findViewById(R.id.all3);
        final Button weaponsNone = mView.findViewById(R.id.none3);
        final Button armorsAll = mView.findViewById(R.id.all4);
        final Button armorsNone = mView.findViewById(R.id.none4);
        final Button healthAll = mView.findViewById(R.id.all5);
        final Button healthNone = mView.findViewById(R.id.none5);
        final Button miskAll = mView.findViewById(R.id.all6);
        final Button miskNone = mView.findViewById(R.id.none6);

        /*TODO Vehicle All*/
        vehiclesAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Vehicles
                Buggy.setChecked(true);
                UAZ.setChecked(true);
                Trike.setChecked(true);
                Bike.setChecked(true);
                Dacia.setChecked(true);
                Jet.setChecked(true);
                Snowmobile.setChecked(true);
                Truck.setChecked(true);
                BRDM.setChecked(true);
                CoupeRB.setChecked(true);
                Boat.setChecked(true);
                Bus.setChecked(true);
                Mirado.setChecked(true);
                Rony.setChecked(true);
                Scooter.setChecked(true);
                Tempo.setChecked(true);
                MonsterTruck.setChecked(true);
                UTV.setChecked(true);
                Snowbike.setChecked(true);
                LadaNiva.setChecked(true);
            }
        });

        /*TODO Vehicle None*/
        vehicleNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buggy.setChecked(false);
                UAZ.setChecked(false);
                Trike.setChecked(false);
                Bike.setChecked(false);
                Dacia.setChecked(false);
                Jet.setChecked(false);
                Snowmobile.setChecked(false);
                Truck.setChecked(false);
                BRDM.setChecked(false);
                CoupeRB.setChecked(false);
                Boat.setChecked(false);
                Bus.setChecked(false);
                Mirado.setChecked(false);
                Rony.setChecked(false);
                Scooter.setChecked(false);
                Tempo.setChecked(false);
                MonsterTruck.setChecked(false);
                UTV.setChecked(false);
                Snowbike.setChecked(false);
                LadaNiva.setChecked(false);
            }
        });

        /*TODO Ammos All*/
        ammoAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ammos
                am300.setChecked(true);
                am45.setChecked(true);
                am12g.setChecked(true);
                am556.setChecked(true);
                am762.setChecked(true);
                am9mm.setChecked(true);

            }
        });

        /*TODO Ammos none*/
        ammoNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ammos
                am300.setChecked(false);
                am45.setChecked(false);
                am12g.setChecked(false);
                am556.setChecked(false);
                am762.setChecked(false);
                am9mm.setChecked(false);
            }
        });

        /*TODO Weapons All*/
        weaponsAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Weapons
                x2.setChecked(true);
                x3.setChecked(true);
                x4.setChecked(true);
                x6.setChecked(true);
                x8.setChecked(true);
                akm.setChecked(true);
                k98.setChecked(true);
                aug.setChecked(true);
                awm.setChecked(true);
                bow.setChecked(true);
                dp12.setChecked(true);
                dp28.setChecked(true);
                deagle.setChecked(true);
                redDot.setChecked(true);
                flare.setChecked(true);
                g36.setChecked(true);
                grenade.setChecked(true);
                groza.setChecked(true);
                hollo.setChecked(true);
                m1014.setChecked(true);
                m16.setChecked(true);
                m24.setChecked(true);
                m249.setChecked(true);
                m4.setChecked(true);
                m762g.setChecked(true);
                mk14.setChecked(true);
                mk47.setChecked(true);
                mp5k.setChecked(true);
                mini14.setChecked(true);
                molotov.setChecked(true);
                pp19.setChecked(true);
                pan.setChecked(true);
                qbu.setChecked(true);
                qbz.setChecked(true);
                s12k.setChecked(true);
                s1897.setChecked(true);
                s686.setChecked(true);
                scarl.setChecked(true);
                sks.setChecked(true);
                slr.setChecked(true);
                stunt.setChecked(true);
                tommy.setChecked(true);
                ump45.setChecked(true);
                uzi.setChecked(true);
                vss.setChecked(true);
                vector.setChecked(true);
                win94.setChecked(true);
            }
        });

        /*TODO Weapons none*/
        weaponsNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Weapons
                x2.setChecked(false);
                x3.setChecked(false);
                x4.setChecked(false);
                x6.setChecked(false);
                x8.setChecked(false);
                akm.setChecked(false);
                k98.setChecked(false);
                aug.setChecked(false);
                awm.setChecked(false);
                bow.setChecked(false);
                dp12.setChecked(false);
                dp28.setChecked(false);
                deagle.setChecked(false);
                redDot.setChecked(false);
                flare.setChecked(false);
                g36.setChecked(false);
                grenade.setChecked(false);
                groza.setChecked(false);
                hollo.setChecked(false);
                m1014.setChecked(false);
                m16.setChecked(false);
                m24.setChecked(false);
                m249.setChecked(false);
                m4.setChecked(false);
                m762g.setChecked(false);
                mk14.setChecked(false);
                mk47.setChecked(false);
                mp5k.setChecked(false);
                mini14.setChecked(false);
                molotov.setChecked(false);
                pp19.setChecked(false);
                pan.setChecked(false);
                qbu.setChecked(false);
                qbz.setChecked(false);
                s12k.setChecked(false);
                s1897.setChecked(false);
                s686.setChecked(false);
                scarl.setChecked(false);
                sks.setChecked(false);
                slr.setChecked(false);
                stunt.setChecked(false);
                tommy.setChecked(false);
                ump45.setChecked(false);
                uzi.setChecked(false);
                vss.setChecked(false);
                vector.setChecked(false);
                win94.setChecked(false);
            }
        });

        /*TODO Armors All*/
        armorsAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Armors
                armorLvl2.setChecked(true);
                armorLvl3.setChecked(true);
                helmetLvl2.setChecked(true);
                helmetLvl3.setChecked(true);


            }
        });

        /*TODO Armors none*/
        armorsNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Armors
                armorLvl2.setChecked(false);
                armorLvl3.setChecked(false);
                helmetLvl2.setChecked(false);
                helmetLvl3.setChecked(false);
            }
        });

        /*TODO Health All*/
        healthAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //health
                bandage.setChecked(true);
                drink.setChecked(true);
                firstAir.setChecked(true);
                injection.setChecked(true);
                pills.setChecked(true);


            }
        });

        /*TODO Health none*/
        healthNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //health
                bandage.setChecked(false);
                drink.setChecked(false);
                firstAir.setChecked(false);
                injection.setChecked(false);
                pills.setChecked(false);
            }
        });

        /*TODO Misk all*/
        miskAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //misk
                airCraft.setChecked(true);
                airDrop.setChecked(true);
                bag2.setChecked(true);
                bag3.setChecked(true);
                compensator.setChecked(true);
                loot.setChecked(true);
                supressor.setChecked(true);
            }
        });

        /*TODO Misk None*/
        miskNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                airCraft.setChecked(false);
                airDrop.setChecked(false);
                bag2.setChecked(false);
                bag3.setChecked(false);
                compensator.setChecked(false);
                loot.setChecked(false);
                supressor.setChecked(false);
            }
        });

        final TextView itemStyleBold = mView.findViewById(R.id.styleBold);
        final TextView itemStyleT= mView.findViewById(R.id.styleT);

        final Typeface styleT = ResourcesCompat.getFont(this, R.font.araboto_light400);

        itemStyleT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dacia.isChecked()) {
                    setValue("sTDacia", true);
                    setValue("sBDacia", false);
                    Dacia.setTypeface(styleT);
                    cPrefs.write(daciaStyle, STYLET);
                    Dacia.setChecked(false);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Dacia.isChecked()) {
                        setValue(String.valueOf(Dacia.getText()), true);
                    }
                }
                if (UAZ.isChecked()) {
                    setValue("sTuaz", true);
                    setValue("sBuaz", false);
                    UAZ.setTypeface(styleT);
                    UAZ.setChecked(false);
                    cPrefs.write(uazStyle, STYLET);
                    if (!UAZ.isChecked()) {
                        setValue(String.valueOf(UAZ.getText()), true);
                    }
                }
                if (Buggy.isChecked()) {
                    setValue("sTbuggy", true);
                    setValue("sBbuggy", false);
                    Buggy.setTypeface(styleT);
                    Buggy.setChecked(false);
                    cPrefs.write(buggyStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Buggy.isChecked()) {
                        setValue(String.valueOf(Buggy.getText()), true);
                    }
                }
                if (Bike.isChecked()) {
                    setValue("sTBike", true);
                    setValue("sBBike", false);
                    Bike.setTypeface(styleT);
                    Bike.setChecked(false);
                    cPrefs.write(bikeStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Bike.isChecked()) {
                        setValue(String.valueOf(Bike.getText()), true);
                    }
                }
                if (Jet.isChecked()) {
                    setValue("sTAquaRail", true);
                    setValue("sBAquaRail", false);
                    Jet.setTypeface(styleT);
                    Jet.setChecked(false);
                    cPrefs.write(aquarailStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Jet.isChecked()) {
                        setValue(String.valueOf(Jet.getText()), true);
                    }
                }
                if (Boat.isChecked()) {
                    setValue("sTboat", true);
                    setValue("sBboat", false);
                    Boat.setTypeface(styleT);
                    Boat.setChecked(false);
                    cPrefs.write(boatStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Boat.isChecked()) {
                        setValue(String.valueOf(Boat.getText()), true);
                    }
                }
                if (Bus.isChecked()) {
                    setValue("sTbus", true);
                    setValue("sBbus", false);
                    Bus.setTypeface(styleT);
                    Bus.setChecked(false);
                    cPrefs.write(busStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Bus.isChecked()) {
                        setValue(String.valueOf(Bus.getText()), true);
                    }
                }
                if (Mirado.isChecked()) {
                    setValue("sTmirado", true);
                    setValue("sBmirado", false);
                    Mirado.setTypeface(styleT);
                    Mirado.setChecked(false);
                    cPrefs.write(miradoStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Mirado.isChecked()) {
                        setValue(String.valueOf(Mirado.getText()), true);
                    }
                }
                if (Scooter.isChecked()) {
                    setValue("sTscooter", true);
                    setValue("sBscooter", false);
                    Scooter.setTypeface(styleT);
                    Scooter.setChecked(false);
                    cPrefs.write(scooterStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Scooter.isChecked()) {
                        setValue(String.valueOf(Scooter.getText()), true);
                    }
                }
                if (Rony.isChecked()) {
                    setValue("sTrony", true);
                    setValue("sBrony", false);
                    Rony.setTypeface(styleT);
                    Rony.setChecked(false);
                    cPrefs.write(ronyStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Rony.isChecked()) {
                        setValue(String.valueOf(Rony.getText()), true);
                    }
                }
                if (Snowbike.isChecked()) {
                    setValue("sTsnowB", true);
                    setValue("sBsnowB", false);
                    Snowbike.setTypeface(styleT);
                    Snowbike.setChecked(false);
                    cPrefs.write(snowbikeStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Snowbike.isChecked()) {
                        setValue(String.valueOf(Snowbike.getText()), true);
                    }
                }
                if (Snowmobile.isChecked()) {
                    setValue("sTsnowM", true);
                    setValue("sBsnowM", false);
                    Snowmobile.setTypeface(styleT);
                    Snowmobile.setChecked(false);
                    cPrefs.write(snowMobileStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Snowmobile.isChecked()) {
                        setValue(String.valueOf(Snowmobile.getText()), true);
                    }
                }
                if (Tempo.isChecked()) {
                    setValue("sTtuk", true);
                    setValue("sBtuk", false);
                    Tempo.setTypeface(styleT);
                    Tempo.setChecked(false);
                    cPrefs.write(tukStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Tempo.isChecked()) {
                        setValue(String.valueOf(Tempo.getText()), true);
                    }
                }
                if (Truck.isChecked()) {
                    setValue("sTtruck", true);
                    setValue("sBtruck", false);
                    Truck.setTypeface(styleT);
                    Truck.setChecked(false);
                    cPrefs.write(truckStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Truck.isChecked()) {
                        setValue(String.valueOf(Truck.getText()), true);
                    }
                }
                if (BRDM.isChecked()) {
                    setValue("sTbrdm", true);
                    setValue("sBbrdm", false);
                    BRDM.setTypeface(styleT);
                    BRDM.setChecked(false);
                    cPrefs.write(brdmStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!BRDM.isChecked()) {
                        setValue(String.valueOf(BRDM.getText()), true);
                    }
                }
                if (LadaNiva.isChecked()) {
                    setValue("sTlada", true);
                    setValue("sBlada", false);
                    LadaNiva.setTypeface(styleT);
                    LadaNiva.setChecked(false);
                    cPrefs.write(ladanivaStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!LadaNiva.isChecked()) {
                        setValue(String.valueOf(LadaNiva.getText()), true);
                    }
                }
                if (MonsterTruck.isChecked()) {
                    setValue("sTmoster", true);
                    setValue("sBmoster", false);
                    MonsterTruck.setTypeface(styleT);
                    MonsterTruck.setChecked(false);
                    cPrefs.write(monsterStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!MonsterTruck.isChecked()) {
                        setValue(String.valueOf(MonsterTruck.getText()), true);
                    }
                }
                if (CoupeRB.isChecked()) {
                    setValue("sTcouper", true);
                    setValue("sBcouper", false);
                    CoupeRB.setTypeface(styleT);
                    CoupeRB.setChecked(false);
                    cPrefs.write(coupeStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!CoupeRB.isChecked()) {
                        setValue(String.valueOf(CoupeRB.getText()), true);
                    }
                }
                if (UTV.isChecked()) {
                    setValue("sTutv", true);
                    setValue("sButv", false);
                    UTV.setTypeface(styleT);
                    UTV.setChecked(false);
                    cPrefs.write(daciaStyle, STYLET);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!UTV.isChecked()) {
                        setValue(String.valueOf(UTV.getText()), true);
                    }
                }

                if (am300.isChecked()) {
                    setValue("sT300am", true);
                    setValue("sB300am", false);
                    am300.setTypeface(styleT);
                    am300.setChecked(false);
                    itemStyleT.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!am300.isChecked()) {
                        setValue(String.valueOf(am300.getText()), true);
                    }
                }

                if (am45.isChecked()) {
                    setValue("sTam45", true);
                    setValue("sBam45", false);
                    am45.setTypeface(styleT);
                    am45.setChecked(false);
                    if (!am45.isChecked()) {
                        setValue(String.valueOf(am45.getText()), true);
                    }
                }

                if (am12g.isChecked()) {
                    setValue("sTam12g", true);
                    setValue("sBam12g", false);
                    am12g.setTypeface(styleT);
                    am12g.setChecked(false);
                    if (!am12g.isChecked()) {
                        setValue(String.valueOf(am12g.getText()), true);
                    }
                }

                if (am556.isChecked()) {
                    setValue("sTam556", true);
                    setValue("sBam556", false);
                    am556.setTypeface(styleT);
                    am556.setChecked(false);
                    if (!am556.isChecked()) {
                        setValue(String.valueOf(am556.getText()), true);
                    }
                }

                if (am762.isChecked()) {
                    setValue("sTam762", true);
                    setValue("sBam762", false);
                    am762.setTypeface(styleT);
                    am762.setChecked(false);
                    if (!am762.isChecked()) {
                        setValue(String.valueOf(am762.getText()), true);
                    }
                }

                if (am9mm.isChecked()) {
                    setValue("sTam9mm", true);
                    setValue("sBam9mm", false);
                    am9mm.setTypeface(styleT);
                    am9mm.setChecked(false);
                    if (!am9mm.isChecked()) {
                        setValue(String.valueOf(am9mm.getText()), true);
                    }
                }

                if (armorLvl2.isChecked()) {
                    setValue("sTar2", true);
                    setValue("sBar2", false);
                    armorLvl2.setTypeface(styleT);
                    armorLvl2.setChecked(false);
                    if (!armorLvl2.isChecked()) {
                        setValue(String.valueOf(armorLvl2.getText()), true);
                    }
                }

                if (armorLvl3.isChecked()) {
                    setValue("sTar3", true);
                    setValue("sBar3", false);
                    armorLvl3.setTypeface(styleT);
                    armorLvl3.setChecked(false);
                    if (!armorLvl3.isChecked()) {
                        setValue(String.valueOf(armorLvl3.getText()), true);
                    }
                }

                if (helmetLvl2.isChecked()) {
                    setValue("sThel2", true);
                    setValue("sBhel2", false);
                    helmetLvl2.setTypeface(styleT);
                    helmetLvl2.setChecked(false);
                    if (!helmetLvl2.isChecked()) {
                        setValue(String.valueOf(helmetLvl2.getText()), true);
                    }
                }

                if (helmetLvl3.isChecked()) {
                    setValue("sThel3", true);
                    setValue("sBhel3", false);
                    helmetLvl3.setTypeface(styleT);
                    helmetLvl3.setChecked(false);
                    if (!helmetLvl3.isChecked()) {
                        setValue(String.valueOf(helmetLvl3.getText()), true);
                    }
                }

                if (bandage.isChecked()) {
                    setValue("sTban", true);
                    setValue("sBban", false);
                    bandage.setTypeface(styleT);
                    bandage.setChecked(false);
                    if (!bandage.isChecked()) {
                        setValue(String.valueOf(bandage.getText()), true);
                    }
                }

                if (drink.isChecked()) {
                    setValue("sTdrink", true);
                    setValue("sBdrink", false);
                    drink.setTypeface(styleT);
                    drink.setChecked(false);
                    if (!drink.isChecked()) {
                        setValue(String.valueOf(drink.getText()), true);
                    }
                }

                if (firstAir.isChecked()) {
                    setValue("sTfa", true);
                    setValue("sBfa", true);
                    firstAir.setTypeface(styleT);
                    firstAir.setChecked(false);
                    if (!firstAir.isChecked()) {
                        setValue(String.valueOf(firstAir.getText()), true);
                    }
                }

                if (injection.isChecked()) {
                    setValue("sTin", true);
                    setValue("sBin", false);
                    injection.setTypeface(styleT);
                    injection.setChecked(false);
                    if (!injection.isChecked()) {
                        setValue(String.valueOf(injection.getText()), true);
                    }
                }

                if (pills.isChecked()) {
                    setValue("sTpills", true);
                    setValue("sBpills", false);
                    pills.setTypeface(styleT);
                    pills.setChecked(false);
                    if (!pills.isChecked()) {
                        setValue(String.valueOf(pills.getText()), true);
                    }
                }

                //Misk
                if (airCraft.isChecked()) {
                    setValue("sTac", true);
                    setValue("sBac", false);
                    airCraft.setTypeface(styleT);
                    airCraft.setChecked(false);
                    if (!airCraft.isChecked()) {
                        setValue(String.valueOf(airCraft.getText()), true);
                    }
                }

                if (airDrop.isChecked()) {
                    setValue("sTairD", true);
                    setValue("sBairD", false);
                    airDrop.setTypeface(styleT);
                    airDrop.setChecked(false);
                    if (!airDrop.isChecked()) {
                        setValue(String.valueOf(airDrop.getText()), true);
                    }
                }

                if (bag2.isChecked()) {
                    setValue("sTb2", true);
                    setValue("sBb2", true);
                    bag2.setTypeface(styleT);
                    bag2.setChecked(false);
                    if (!bag2.isChecked()) {
                        setValue(String.valueOf(bag2.getText()), true);
                    }
                }

                if (bag3.isChecked()) {
                    setValue("sTb3", true);
                    setValue("sBb3", false);
                    bag3.setTypeface(styleT);
                    bag3.setChecked(false);
                    if (!bag3.isChecked()) {
                        setValue(String.valueOf(bag3.getText()), true);
                    }
                }

                if (compensator.isChecked()) {
                    setValue("sTcomp", true);
                    setValue("sBcomp", false);
                    compensator.setTypeface(styleT);
                    compensator.setChecked(false);
                    if (!compensator.isChecked()) {
                        setValue(String.valueOf(compensator.getText()), true);
                    }
                }

                if (loot.isChecked()) {
                    setValue("sTloot", true);
                    setValue("sBloot", false);
                    loot.setTypeface(styleT);
                    loot.setChecked(false);
                    if (!loot.isChecked()) {
                        setValue(String.valueOf(loot.getText()), true);
                    }
                }

                if (supressor.isChecked()) {
                    setValue("sTsupp", true);
                    setValue("sBsupp", false);
                    supressor.setTypeface(styleT);
                    supressor.setChecked(false);
                    if (!supressor.isChecked()) {
                        setValue(String.valueOf(supressor.getText()), true);
                    }
                }

                //Weapons
                if (x2.isChecked()) {
                    setValue("sTx2", true);
                    setValue("sBx2", false);
                    x2.setTypeface(styleT);
                    x2.setChecked(false);
                    if (!x2.isChecked()) {
                        setValue(String.valueOf(x2.getText()), true);
                    }
                }

                if (x3.isChecked()) {
                    setValue("sTx3", true);
                    setValue("sBx3", false);
                    x3.setTypeface(styleT);
                    x3.setChecked(false);
                    if (!x3.isChecked()) {
                        setValue(String.valueOf(x3.getText()), true);
                    }
                }

                if (x4.isChecked()) {
                    setValue("sTx4", true);
                    setValue("sBx4", false);
                    x4.setTypeface(styleT);
                    x4.setChecked(false);
                    if (!x4.isChecked()) {
                        setValue(String.valueOf(x4.getText()), true);
                    }
                }

                if (x6.isChecked()) {
                    setValue("sTx6", true);
                    setValue("sBx6", false);
                    x6.setTypeface(styleT);
                    x6.setChecked(false);
                    if (!x6.isChecked()) {
                        setValue(String.valueOf(x6.getText()), true);
                    }
                }

                if (x8.isChecked()) {
                    setValue("sTx8", true);
                    setValue("sBx8", false);
                    x8.setTypeface(styleT);
                    x8.setChecked(false);
                    if (!x8.isChecked()) {
                        setValue(String.valueOf(x8.getText()), true);
                    }
                }

                //real
                if (k98.isChecked()) {
                    setValue("sTk98", true);
                    setValue("sBk98", false);
                    k98.setTypeface(styleT);
                    k98.setChecked(false);
                    if (!k98.isChecked()) {
                        setValue(String.valueOf(k98.getText()), true);
                    }
                }

                if (akm.isChecked()) {
                    setValue("sTakm", true);
                    setValue("sBakm", false);
                    akm.setTypeface(styleT);
                    akm.setChecked(false);
                    if (!akm.isChecked()) {
                        setValue(String.valueOf(akm.getText()), true);
                    }
                }

                if (aug.isChecked()) {
                    setValue("sTaug", true);
                    setValue("sBaug", false);
                    aug.setTypeface(styleT);
                    aug.setChecked(false);
                    if (!aug.isChecked()) {
                        setValue(String.valueOf(aug.getText()), true);
                    }
                }

                if (awm.isChecked()) {
                    setValue("sTawm", true);
                    setValue("sBawm", false);
                    awm.setTypeface(styleT);
                    awm.setChecked(false);
                    if (!awm.isChecked()) {
                        setValue(String.valueOf(awm.getText()), true);
                    }
                }

                if (bow.isChecked()) {
                    setValue("sTbow", true);
                    setValue("sBbow", false);
                    bow.setTypeface(styleT);
                    bow.setChecked(false);
                    if (!bow.isChecked()) {
                        setValue(String.valueOf(bow.getText()), true);
                    }
                }

                if (dp12.isChecked()) {
                    setValue("sTdp12", true);
                    setValue("sBdp12", false);
                    dp12.setTypeface(styleT);
                    dp12.setChecked(false);
                    if (!dp12.isChecked()) {
                        setValue(String.valueOf(dp12.getText()), true);
                    }
                }

                if (dp28.isChecked()) {
                    setValue("sTdp28", true);
                    setValue("sBdp28", false);
                    dp28.setTypeface(styleT);
                    dp28.setChecked(false);
                    if (!dp28.isChecked()) {
                        setValue(String.valueOf(dp28.getText()), true);
                    }
                }

                if (deagle.isChecked()) {
                    setValue("sTdeg", true);
                    setValue("sBdeg", false);
                    deagle.setTypeface(styleT);
                    deagle.setChecked(false);
                    if (!deagle.isChecked()) {
                        setValue(String.valueOf(deagle.getText()), true);
                    }
                }

                if (redDot.isChecked()) {
                    setValue("sTredDot", true);
                    setValue("sBredDot", false);
                    redDot.setTypeface(styleT);
                    redDot.setChecked(false);
                    if (!redDot.isChecked()) {
                        setValue(String.valueOf(redDot.getText()), true);
                    }
                }

                if (flare.isChecked()) {
                    setValue("sTflare", true);
                    setValue("sBflare", false);
                    flare.setTypeface(styleT);
                    flare.setChecked(false);
                    if (!flare.isChecked()) {
                        setValue(String.valueOf(flare.getText()), true);
                    }
                }

                if (g36.isChecked()) {
                    setValue("sTg36", true);
                    setValue("sBg36", false);
                    g36.setTypeface(styleT);
                    g36.setChecked(false);
                    if (!g36.isChecked()) {
                        setValue(String.valueOf(g36.getText()), true);
                    }
                }

                if (grenade.isChecked()) {
                    setValue("sTgrenade", true);
                    setValue("sBgrenade", false);
                    grenade.setTypeface(styleT);
                    grenade.setChecked(false);
                    if (!grenade.isChecked()) {
                        setValue(String.valueOf(grenade.getText()), true);
                    }
                }

                if (groza.isChecked()) {
                    setValue("sTgroza", true);
                    setValue("sBgroza", false);
                    groza.setTypeface(styleT);
                    groza.setChecked(false);
                    if (!groza.isChecked()) {
                        setValue(String.valueOf(groza.getText()), true);
                    }
                }

                if (hollo.isChecked()) {
                    setValue("sThollo", true);
                    setValue("sBhollo", false);
                    hollo.setTypeface(styleT);
                    hollo.setChecked(false);
                    if (!hollo.isChecked()) {
                        setValue(String.valueOf(hollo.getText()), true);
                    }
                }

                if (m1014.isChecked()) {
                    setValue("sTm1014", true);
                    setValue("sBm1014", false);
                    m1014.setTypeface(styleT);
                    m1014.setChecked(false);
                    if (!m1014.isChecked()) {
                        setValue(String.valueOf(m1014.getText()), true);
                    }
                }

                if (m16.isChecked()) {
                    setValue("sTm16", true);
                    setValue("sBm16", false);
                    m16.setTypeface(styleT);
                    m16.setChecked(false);
                    if (!m16.isChecked()) {
                        setValue(String.valueOf(m16.getText()), true);
                    }
                }

                if (m24.isChecked()) {
                    setValue("sTm24", true);
                    setValue("sBm24", false);
                    m24.setTypeface(styleT);
                    m24.setChecked(false);
                    if (!m24.isChecked()) {
                        setValue(String.valueOf(m24.getText()), true);
                    }
                }

                if (m249.isChecked()) {
                    setValue("sTm249", true);
                    setValue("sBm249", false);
                    m249.setTypeface(styleT);
                    m249.setChecked(false);
                    if (!m249.isChecked()) {
                        setValue(String.valueOf(m249.getText()), true);
                    }
                }

                if (m4.isChecked()) {
                    setValue("sTm4", true);
                    setValue("sBm4", false);
                    m4.setTypeface(styleT);
                    m4.setChecked(false);
                    if (!m4.isChecked()) {
                        setValue(String.valueOf(m4.getText()), true);
                    }
                }

                if (m762g.isChecked()) {
                    setValue("sTm762", true);
                    setValue("sBm762", false);
                    m762g.setTypeface(styleT);
                    m762g.setChecked(false);
                    if (!m762g.isChecked()) {
                        setValue(String.valueOf(m762g.getText()), true);
                    }
                }

                if (mk14.isChecked()) {
                    setValue("sTmk14", true);
                    setValue("sBmk14", false);
                    mk14.setTypeface(styleT);
                    mk14.setChecked(false);
                    if (!mk14.isChecked()) {
                        setValue(String.valueOf(mk14.getText()), true);
                    }
                }

                if (mk47.isChecked()) {
                    setValue("sTmk47", true);
                    setValue("sBmk47", false);
                    mk47.setTypeface(styleT);
                    mk47.setChecked(false);
                    if (!mk47.isChecked()) {
                        setValue(String.valueOf(mk47.getText()), true);
                    }
                }

                if (mp5k.isChecked()) {
                    setValue("sTmp5k", true);
                    setValue("sBmp5k", false);
                    mp5k.setTypeface(styleT);
                    mp5k.setChecked(false);
                    if (!mp5k.isChecked()) {
                        setValue(String.valueOf(mp5k.getText()), true);
                    }
                }

                if (mini14.isChecked()) {
                    setValue("sTmini14", true);
                    setValue("sBmini14", false);
                    mini14.setTypeface(styleT);
                    mini14.setChecked(false);
                    if (!mini14.isChecked()) {
                        setValue(String.valueOf(mini14.getText()), true);
                    }
                }

                if (molotov.isChecked()) {
                    setValue("sTburn", true);
                    setValue("sBburn", false);
                    molotov.setTypeface(styleT);
                    molotov.setChecked(false);
                    if (!molotov.isChecked()) {
                        setValue(String.valueOf(molotov.getText()), true);
                    }
                }

                if (pp19.isChecked()) {
                    setValue("sTpp19", true);
                    setValue("sBpp19", false);
                    pp19.setTypeface(styleT);
                    pp19.setChecked(false);
                    if (!pp19.isChecked()) {
                        setValue(String.valueOf(pp19.getText()), true);
                    }
                }

                if (pan.isChecked()) {
                    setValue("sTpan", true);
                    setValue("sBpan", false);
                    pan.setTypeface(styleT);
                    pan.setChecked(false);
                    if (!pan.isChecked()) {
                        setValue(String.valueOf(pan.getText()), true);
                    }
                }

                if (qbu.isChecked()) {
                    setValue("sTqbu", true);
                    setValue("sBqbu", false);
                    qbu.setTypeface(styleT);
                    qbu.setChecked(false);
                    if (!qbu.isChecked()) {
                        setValue(String.valueOf(qbu.getText()), true);
                    }
                }

                if (qbz.isChecked()) {
                    setValue("sTqbz", true);
                    setValue("sBqbz", false);
                    qbz.setTypeface(styleT);
                    qbz.setChecked(false);
                    if (!qbz.isChecked()) {
                        setValue(String.valueOf(qbz.getText()), true);
                    }
                }

                if (s12k.isChecked()) {
                    setValue("sTs12k", true);
                    setValue("sBs12k", false);
                    s12k.setTypeface(styleT);
                    s12k.setChecked(false);
                    if (!s12k.isChecked()) {
                        setValue(String.valueOf(s12k.getText()), true);
                    }
                }

                if (s1897.isChecked()) {
                    setValue("sTs1897", true);
                    setValue("sBs1897", false);
                    s1897.setTypeface(styleT);
                    s1897.setChecked(false);
                    if (!s1897.isChecked()) {
                        setValue(String.valueOf(s1897.getText()), true);
                    }
                }

                if (s686.isChecked()) {
                    setValue("sTs686", true);
                    setValue("sBs686", false);
                    s686.setTypeface(styleT);
                    s686.setChecked(false);
                    if (!s686.isChecked()) {
                        setValue(String.valueOf(s686.getText()), true);
                    }
                }

                if (scarl.isChecked()) {
                    setValue("sTscar", true);
                    setValue("sBscar", false);
                    scarl.setTypeface(styleT);
                    scarl.setChecked(false);
                    if (!scarl.isChecked()) {
                        setValue(String.valueOf(scarl.getText()), true);
                    }
                }

                if (sks.isChecked()) {
                    setValue("sTsks", true);
                    setValue("sBsks", false);
                    sks.setTypeface(styleT);
                    sks.setChecked(false);
                    if (!sks.isChecked()) {
                        setValue(String.valueOf(sks.getText()), true);
                    }
                }

                if (slr.isChecked()) {
                    setValue("sTslr", true);
                    setValue("sBslr", false);
                    slr.setTypeface(styleT);
                    slr.setChecked(false);
                    if (!slr.isChecked()) {
                        setValue(String.valueOf(slr.getText()), true);
                    }
                }

                if (stunt.isChecked()) {
                    setValue("sTflash", true);
                    setValue("sBflash", false);
                    stunt.setTypeface(styleT);
                    stunt.setChecked(false);
                    if (!stunt.isChecked()) {
                        setValue(String.valueOf(stunt.getText()), true);
                    }
                }

                if (tommy.isChecked()) {
                    setValue("sTthomp", true);
                    setValue("sBthomp", false);
                    tommy.setTypeface(styleT);
                    tommy.setChecked(false);
                    if (!tommy.isChecked()) {
                        setValue(String.valueOf(tommy.getText()), true);
                    }
                }

                if (ump45.isChecked()) {
                    setValue("sTump45", true);
                    setValue("sBump45", false);
                    ump45.setTypeface(styleT);
                    ump45.setChecked(false);
                    if (!ump45.isChecked()) {
                        setValue(String.valueOf(ump45.getText()), true);
                    }
                }

                if (uzi.isChecked()) {
                    setValue("sTuzi", true);
                    setValue("sBuzi", false);
                    uzi.setTypeface(styleT);
                    uzi.setChecked(false);
                    if (!uzi.isChecked()) {
                        setValue(String.valueOf(uzi.getText()), true);
                    }
                }

                if (vss.isChecked()) {
                    setValue("sTvss", true);
                    setValue("sBvss", false);
                    vss.setTypeface(styleT);
                    vss.setChecked(false);
                    if (!vss.isChecked()) {
                        setValue(String.valueOf(vss.getText()), true);
                    }
                }
                if (vector.isChecked()) {
                    setValue("sTvect", true);
                    setValue("sBvect", false);
                    vector.setTypeface(styleT);
                    vector.setChecked(false);
                    if (!vector.isChecked()) {
                        setValue(String.valueOf(vector.getText()), true);
                    }
                }

                if (win94.isChecked()) {
                    setValue("sTwinchester", true);
                    setValue("sBwinchester", false);
                    win94.setTypeface(styleT);
                    win94.setChecked(false);
                    if (!win94.isChecked()) {
                        setValue(String.valueOf(win94.getText()), true);
                    }
                }

            }
        });

        itemStyleBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dacia.isChecked()) {
                    setValue("sBDacia", true);
                    setValue("sTDacia", false);
                    Dacia.setTypeface(null, Typeface.BOLD);
                    Dacia.setChecked(false);
                    cPrefs.write(daciaStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Dacia.isChecked()) {
                        setValue(String.valueOf(Dacia.getText()), true);
                    }
                }
                if (UAZ.isChecked()) {
                    setValue("sBuaz", true);
                    setValue("sTuaz", false);
                    UAZ.setTypeface(null, Typeface.BOLD);
                    UAZ.setChecked(false);
                    cPrefs.write(uazStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!UAZ.isChecked()) {
                        setValue(String.valueOf(UAZ.getText()), true);
                    }
                }
                if (Buggy.isChecked()) {
                    setValue("sBbuggy", true);
                    setValue("sTbuggy", false);
                    Buggy.setTypeface(null, Typeface.BOLD);
                    Buggy.setChecked(false);
                    cPrefs.write(buggyStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Buggy.isChecked()) {
                        setValue(String.valueOf(Buggy.getText()), true);
                    }
                }
                if (Bike.isChecked()) {
                    setValue("sBBike", true);
                    setValue("sTBike", false);
                    Bike.setTypeface(null, Typeface.BOLD);
                    Bike.setChecked(false);
                    cPrefs.write(bikeStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Bike.isChecked()) {
                        setValue(String.valueOf(Bike.getText()), true);
                    }
                }
                if (Jet.isChecked()) {
                    setValue("sBAquaRail", true);
                    setValue("sTAquaRail", false);
                    Jet.setTypeface(null, Typeface.BOLD);
                    Jet.setChecked(false);
                    cPrefs.write(aquarailStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Jet.isChecked()) {
                        setValue(String.valueOf(Jet.getText()), true);
                    }
                }
                if (Boat.isChecked()) {
                    setValue("sBboat", true);
                    setValue("sTboat", false);
                    Boat.setTypeface(null, Typeface.BOLD);
                    Boat.setChecked(false);
                    cPrefs.write(boatStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Boat.isChecked()) {
                        setValue(String.valueOf(Boat.getText()), true);
                    }
                }
                if (Bus.isChecked()) {
                    setValue("sBbus", true);
                    setValue("sTbus", false);
                    Bus.setTypeface(null, Typeface.BOLD);
                    Bus.setChecked(false);
                    cPrefs.write(busStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Bus.isChecked()) {
                        setValue(String.valueOf(Bus.getText()), true);
                    }
                }
                if (Mirado.isChecked()) {
                    setValue("sBmirado", true);
                    setValue("sTmirado", false);
                    Mirado.setTypeface(null, Typeface.BOLD);
                    Mirado.setChecked(false);
                    cPrefs.write(miradoStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Mirado.isChecked()) {
                        setValue(String.valueOf(Mirado.getText()), true);
                    }
                }
                if (Scooter.isChecked()) {
                    setValue("sBscooter", true);
                    setValue("sTscooter", false);
                    Scooter.setTypeface(null, Typeface.BOLD);
                    Scooter.setChecked(false);
                    cPrefs.write(scooterStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Scooter.isChecked()) {
                        setValue(String.valueOf(Scooter.getText()), true);
                    }
                }
                if (Rony.isChecked()) {
                    setValue("sBrony", true);
                    setValue("sTrony", false);
                    Rony.setTypeface(null, Typeface.BOLD);
                    Rony.setChecked(false);
                    cPrefs.write(ronyStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Rony.isChecked()) {
                        setValue(String.valueOf(Rony.getText()), true);
                    }
                }
                if (Snowbike.isChecked()) {
                    setValue("sBsnowB", true);
                    setValue("sTsnowB", false);
                    Snowbike.setTypeface(null, Typeface.BOLD);
                    Snowbike.setChecked(false);
                    cPrefs.write(snowbikeStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Snowbike.isChecked()) {
                        setValue(String.valueOf(Snowbike.getText()), true);
                    }
                }
                if (Snowmobile.isChecked()) {
                    setValue("sBsnowM", true);
                    setValue("sTsnowM", false);
                    Snowmobile.setTypeface(null, Typeface.BOLD);
                    Snowmobile.setChecked(false);
                    cPrefs.write(snowMobileStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Snowmobile.isChecked()) {
                        setValue(String.valueOf(Snowmobile.getText()), true);
                    }
                }
                if (Tempo.isChecked()) {
                    setValue("sBtuk", true);
                    setValue("sTtuk", false);
                    Tempo.setTypeface(null, Typeface.BOLD);
                    Tempo.setChecked(false);
                    cPrefs.write(tukStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Tempo.isChecked()) {
                        setValue(String.valueOf(Tempo.getText()), true);
                    }
                }
                if (Truck.isChecked()) {
                    setValue("sBtruck", true);
                    setValue("sTtruck", false);
                    Truck.setTypeface(null, Typeface.BOLD);
                    Truck.setChecked(false);
                    cPrefs.write(truckStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!Truck.isChecked()) {
                        setValue(String.valueOf(Truck.getText()), true);
                    }
                }
                if (BRDM.isChecked()) {
                    setValue("sBbrdm", true);
                    setValue("sTbrdm", false);
                    BRDM.setTypeface(null, Typeface.BOLD);
                    BRDM.setChecked(false);
                    cPrefs.write(brdmStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!BRDM.isChecked()) {
                        setValue(String.valueOf(BRDM.getText()), true);
                    }
                }
                if (LadaNiva.isChecked()) {
                    setValue("sBlada", true);
                    setValue("sTlada", false);
                    LadaNiva.setTypeface(null, Typeface.BOLD);
                    LadaNiva.setChecked(false);
                    cPrefs.write(ladanivaStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!LadaNiva.isChecked()) {
                        setValue(String.valueOf(LadaNiva.getText()), true);
                    }
                }
                if (MonsterTruck.isChecked()) {
                    setValue("sBmoster", true);
                    setValue("sTmoster", false);
                    MonsterTruck.setTypeface(null, Typeface.BOLD);
                    MonsterTruck.setChecked(false);
                    cPrefs.write(monsterStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!MonsterTruck.isChecked()) {
                        setValue(String.valueOf(MonsterTruck.getText()), true);
                    }
                }
                if (CoupeRB.isChecked()) {
                    setValue("sBcouper", true);
                    setValue("sTcouper", false);
                    CoupeRB.setTypeface(null, Typeface.BOLD);
                    CoupeRB.setChecked(false);
                    cPrefs.write(coupeStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!CoupeRB.isChecked()) {
                        setValue(String.valueOf(CoupeRB.getText()), true);
                    }
                }
                if (UTV.isChecked()) {
                    setValue("sButv", true);
                    setValue("sTutv", false);
                    UTV.setTypeface(null, Typeface.BOLD);
                    UTV.setChecked(false);
                    cPrefs.write(daciaStyle, STYLEB);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!UTV.isChecked()) {
                        setValue(String.valueOf(UTV.getText()), true);
                    }
                }

                if (am300.isChecked()) {
                    setValue("sB300am", true);
                    setValue("sT300am", false);
                    am300.setTypeface(null, Typeface.BOLD);
                    am300.setChecked(false);
                    itemStyleBold.setBackgroundColor(Color.parseColor("#63C0C0C0"));
                    if (!am300.isChecked()) {
                        setValue(String.valueOf(am300.getText()), true);
                    }
                }

                if (am45.isChecked()) {
                    setValue("sBam45", true);
                    setValue("sTam45", false);
                    am45.setTypeface(null, Typeface.BOLD);
                    am45.setChecked(false);
                    if (!am45.isChecked()) {
                        setValue(String.valueOf(am45.getText()), true);
                    }
                }

                if (am12g.isChecked()) {
                    setValue("sBam12g", true);
                    setValue("sTam12g", false);
                    am12g.setTypeface(null, Typeface.BOLD);
                    am12g.setChecked(false);
                    if (!am12g.isChecked()) {
                        setValue(String.valueOf(am12g.getText()), true);
                    }
                }

                if (am556.isChecked()) {
                    setValue("sBam556", true);
                    setValue("sTam556", false);
                    am556.setTypeface(null, Typeface.BOLD);
                    am556.setChecked(false);
                    if (!am556.isChecked()) {
                        setValue(String.valueOf(am556.getText()), true);
                    }
                }

                if (am762.isChecked()) {
                    setValue("sBam762", true);
                    setValue("sTam762", false);
                    am762.setTypeface(null, Typeface.BOLD);
                    am762.setChecked(false);
                    if (!am762.isChecked()) {
                        setValue(String.valueOf(am762.getText()), true);
                    }
                }

                if (am9mm.isChecked()) {
                    setValue("sBam9mm", true);
                    setValue("sTam9mm", false);
                    am9mm.setTypeface(null, Typeface.BOLD);
                    am9mm.setChecked(false);
                    if (!am9mm.isChecked()) {
                        setValue(String.valueOf(am9mm.getText()), true);
                    }
                }

                if (armorLvl2.isChecked()) {
                    setValue("sBar2", true);
                    setValue("sTar2", false);
                    armorLvl2.setTypeface(null, Typeface.BOLD);
                    armorLvl2.setChecked(false);
                    if (!armorLvl2.isChecked()) {
                        setValue(String.valueOf(armorLvl2.getText()), true);
                    }
                }

                if (armorLvl3.isChecked()) {
                    setValue("sBar3", true);
                    setValue("sTar3", false);
                    armorLvl3.setTypeface(null, Typeface.BOLD);
                    armorLvl3.setChecked(false);
                    if (!armorLvl3.isChecked()) {
                        setValue(String.valueOf(armorLvl3.getText()), true);
                    }
                }

                if (helmetLvl2.isChecked()) {
                    setValue("sBhel2", true);
                    setValue("sThel2", false);
                    helmetLvl2.setTypeface(null, Typeface.BOLD);
                    helmetLvl2.setChecked(false);
                    if (!helmetLvl2.isChecked()) {
                        setValue(String.valueOf(helmetLvl2.getText()), true);
                    }
                }

                if (helmetLvl3.isChecked()) {
                    setValue("sBhel3", true);
                    setValue("sThel3", false);
                    helmetLvl3.setTypeface(null, Typeface.BOLD);
                    helmetLvl3.setChecked(false);
                    if (!helmetLvl3.isChecked()) {
                        setValue(String.valueOf(helmetLvl3.getText()), true);
                    }
                }

                if (bandage.isChecked()) {
                    setValue("sBban", true);
                    setValue("sTban", false);
                    bandage.setTypeface(null, Typeface.BOLD);
                    bandage.setChecked(false);
                    if (!bandage.isChecked()) {
                        setValue(String.valueOf(bandage.getText()), true);
                    }
                }

                if (drink.isChecked()) {
                    setValue("sBdrink", true);
                    setValue("sTdrink", false);
                    drink.setTypeface(null, Typeface.BOLD);
                    drink.setChecked(false);
                    if (!drink.isChecked()) {
                        setValue(String.valueOf(drink.getText()), true);
                    }
                }

                if (firstAir.isChecked()) {
                    setValue("sBfa", true);
                    setValue("sTfa", false);
                    firstAir.setTypeface(null, Typeface.BOLD);
                    firstAir.setChecked(false);
                    if (!firstAir.isChecked()) {
                        setValue(String.valueOf(firstAir.getText()), true);
                    }
                }

                if (injection.isChecked()) {
                    setValue("sBin", true);
                    setValue("sTin", false);
                    injection.setTypeface(null, Typeface.BOLD);
                    injection.setChecked(false);
                    if (!injection.isChecked()) {
                        setValue(String.valueOf(injection.getText()), true);
                    }
                }

                if (pills.isChecked()) {
                    setValue("sBpills", true);
                    setValue("sTpills", false);
                    pills.setTypeface(null, Typeface.BOLD);
                    pills.setChecked(false);
                    if (!pills.isChecked()) {
                        setValue(String.valueOf(pills.getText()), true);
                    }
                }

                //Misk
                if (airCraft.isChecked()) {
                    setValue("sBac", true);
                    setValue("sTac", false);
                    airCraft.setTypeface(null, Typeface.BOLD);
                    airCraft.setChecked(false);
                    if (!airCraft.isChecked()) {
                        setValue(String.valueOf(airCraft.getText()), true);
                    }
                }

                if (airDrop.isChecked()) {
                    setValue("sBairD", true);
                    setValue("sTairD", false);
                    airDrop.setTypeface(null, Typeface.BOLD);
                    airDrop.setChecked(false);
                    if (!airDrop.isChecked()) {
                        setValue(String.valueOf(airDrop.getText()), true);
                    }
                }

                if (bag2.isChecked()) {
                    setValue("sBb2", true);
                    setValue("sTb2", false);
                    bag2.setTypeface(null, Typeface.BOLD);
                    bag2.setChecked(false);
                    if (!bag2.isChecked()) {
                        setValue(String.valueOf(bag2.getText()), true);
                    }
                }

                if (bag3.isChecked()) {
                    setValue("sBb3", true);
                    setValue("sTb3", false);
                    bag3.setTypeface(null, Typeface.BOLD);
                    bag3.setChecked(false);
                    if (!bag3.isChecked()) {
                        setValue(String.valueOf(bag3.getText()), true);
                    }
                }

                if (compensator.isChecked()) {
                    setValue("sBcomp", true);
                    setValue("sTcomp", false);
                    compensator.setTypeface(null, Typeface.BOLD);
                    compensator.setChecked(false);
                    if (!compensator.isChecked()) {
                        setValue(String.valueOf(compensator.getText()), true);
                    }
                }

                if (loot.isChecked()) {
                    setValue("sBloot", true);
                    setValue("sTloot", false);
                    loot.setTypeface(null, Typeface.BOLD);
                    loot.setChecked(false);
                    if (!loot.isChecked()) {
                        setValue(String.valueOf(loot.getText()), true);
                    }
                }

                if (supressor.isChecked()) {
                    setValue("sBsupp", true);
                    setValue("sTsupp", false);
                    supressor.setTypeface(null, Typeface.BOLD);
                    supressor.setChecked(false);
                    if (!supressor.isChecked()) {
                        setValue(String.valueOf(supressor.getText()), true);
                    }
                }

                //Weapons
                if (x2.isChecked()) {
                    setValue("sBx2", true);
                    setValue("sTx2", false);
                    x2.setTypeface(null, Typeface.BOLD);
                    x2.setChecked(false);
                    if (!x2.isChecked()) {
                        setValue(String.valueOf(x2.getText()), true);
                    }
                }

                if (x3.isChecked()) {
                    setValue("sBx3", true);
                    setValue("sTx3", false);
                    x3.setTypeface(null, Typeface.BOLD);
                    x3.setChecked(false);
                    if (!x3.isChecked()) {
                        setValue(String.valueOf(x3.getText()), true);
                    }
                }

                if (x4.isChecked()) {
                    setValue("sBx4", true);
                    setValue("sTx4", false);
                    x4.setTypeface(null, Typeface.BOLD);
                    x4.setChecked(false);
                    if (!x4.isChecked()) {
                        setValue(String.valueOf(x4.getText()), true);
                    }
                }

                if (x6.isChecked()) {
                    setValue("sBx6", true);
                    setValue("sTx6", false);
                    x6.setTypeface(null, Typeface.BOLD);
                    x6.setChecked(false);
                    if (!x6.isChecked()) {
                        setValue(String.valueOf(x6.getText()), true);
                    }
                }

                if (x8.isChecked()) {
                    setValue("sBx8", true);
                    setValue("sTx8", false);
                    x8.setTypeface(null, Typeface.BOLD);
                    x8.setChecked(false);
                    if (!x8.isChecked()) {
                        setValue(String.valueOf(x8.getText()), true);
                    }
                }

                //real
                if (k98.isChecked()) {
                    setValue("sBk98", true);
                    setValue("sTk98", false);
                    k98.setTypeface(null, Typeface.BOLD);
                    k98.setChecked(false);
                    if (!k98.isChecked()) {
                        setValue(String.valueOf(k98.getText()), true);
                    }
                }

                if (akm.isChecked()) {
                    setValue("sBakm", true);
                    setValue("sTakm", false);
                    akm.setTypeface(null, Typeface.BOLD);
                    akm.setChecked(false);
                    if (!akm.isChecked()) {
                        setValue(String.valueOf(akm.getText()), true);
                    }
                }

                if (aug.isChecked()) {
                    setValue("sBaug", true);
                    setValue("sTaug", false);
                    aug.setTypeface(null, Typeface.BOLD);
                    aug.setChecked(false);
                    if (!aug.isChecked()) {
                        setValue(String.valueOf(aug.getText()), true);
                    }
                }

                if (awm.isChecked()) {
                    setValue("sBawm", true);
                    setValue("sTawm", false);
                    awm.setTypeface(null, Typeface.BOLD);
                    awm.setChecked(false);
                    if (!awm.isChecked()) {
                        setValue(String.valueOf(awm.getText()), true);
                    }
                }

                if (bow.isChecked()) {
                    setValue("sBbow", true);
                    setValue("sTbow", false);
                    bow.setTypeface(null, Typeface.BOLD);
                    bow.setChecked(false);
                    if (!bow.isChecked()) {
                        setValue(String.valueOf(bow.getText()), true);
                    }
                }

                if (dp12.isChecked()) {
                    setValue("sBdp12", true);
                    setValue("sTdp12", false);
                    dp12.setTypeface(null, Typeface.BOLD);
                    dp12.setChecked(false);
                    if (!dp12.isChecked()) {
                        setValue(String.valueOf(dp12.getText()), true);
                    }
                }

                if (dp28.isChecked()) {
                    setValue("sBdp28", true);
                    setValue("sTdp28", false);
                    dp28.setTypeface(null, Typeface.BOLD);
                    dp28.setChecked(false);
                    if (!dp28.isChecked()) {
                        setValue(String.valueOf(dp28.getText()), true);
                    }
                }

                if (deagle.isChecked()) {
                    setValue("sBdeg", true);
                    setValue("sTdeg", false);
                    deagle.setTypeface(null, Typeface.BOLD);
                    deagle.setChecked(false);
                    if (!deagle.isChecked()) {
                        setValue(String.valueOf(deagle.getText()), true);
                    }
                }

                if (redDot.isChecked()) {
                    setValue("sBredDot", true);
                    setValue("sTredDot", false);
                    redDot.setTypeface(null, Typeface.BOLD);
                    redDot.setChecked(false);
                    if (!redDot.isChecked()) {
                        setValue(String.valueOf(redDot.getText()), true);
                    }
                }

                if (flare.isChecked()) {
                    setValue("sBflare", true);
                    setValue("sTflare", false);
                    flare.setTypeface(null, Typeface.BOLD);
                    flare.setChecked(false);
                    if (!flare.isChecked()) {
                        setValue(String.valueOf(flare.getText()), true);
                    }
                }

                if (g36.isChecked()) {
                    setValue("sBg36", true);
                    setValue("sTg36", false);
                    g36.setTypeface(null, Typeface.BOLD);
                    g36.setChecked(false);
                    if (!g36.isChecked()) {
                        setValue(String.valueOf(g36.getText()), true);
                    }
                }

                if (grenade.isChecked()) {
                    setValue("sBgrenade", true);
                    setValue("sTgrenade", false);
                    grenade.setTypeface(null, Typeface.BOLD);
                    grenade.setChecked(false);
                    if (!grenade.isChecked()) {
                        setValue(String.valueOf(grenade.getText()), true);
                    }
                }

                if (groza.isChecked()) {
                    setValue("sBgroza", true);
                    setValue("sTgroza", false);
                    groza.setTypeface(null, Typeface.BOLD);
                    groza.setChecked(false);
                    if (!groza.isChecked()) {
                        setValue(String.valueOf(groza.getText()), true);
                    }
                }

                if (hollo.isChecked()) {
                    setValue("sBhollo", true);
                    setValue("sThollo", false);
                    hollo.setTypeface(null, Typeface.BOLD);
                    hollo.setChecked(false);
                    if (!hollo.isChecked()) {
                        setValue(String.valueOf(hollo.getText()), true);
                    }
                }

                if (m1014.isChecked()) {
                    setValue("sBm1014", true);
                    setValue("sTm1014", false);
                    m1014.setTypeface(null, Typeface.BOLD);
                    m1014.setChecked(false);
                    if (!m1014.isChecked()) {
                        setValue(String.valueOf(m1014.getText()), true);
                    }
                }

                if (m16.isChecked()) {
                    setValue("sBm16", true);
                    setValue("sTm16", false);
                    m16.setTypeface(null, Typeface.BOLD);
                    m16.setChecked(false);
                    if (!m16.isChecked()) {
                        setValue(String.valueOf(m16.getText()), true);
                    }
                }

                if (m24.isChecked()) {
                    setValue("sBm24", true);
                    setValue("sTm24", false);
                    m24.setTypeface(null, Typeface.BOLD);
                    m24.setChecked(false);
                    if (!m24.isChecked()) {
                        setValue(String.valueOf(m24.getText()), true);
                    }
                }

                if (m249.isChecked()) {
                    setValue("sBm249", true);
                    setValue("sTm249", false);
                    m249.setTypeface(null, Typeface.BOLD);
                    m249.setChecked(false);
                    if (!m249.isChecked()) {
                        setValue(String.valueOf(m249.getText()), true);
                    }
                }

                if (m4.isChecked()) {
                    setValue("sBm4", true);
                    setValue("sTm4", false);
                    m4.setTypeface(null, Typeface.BOLD);
                    m4.setChecked(false);
                    if (!m4.isChecked()) {
                        setValue(String.valueOf(m4.getText()), true);
                    }
                }

                if (m762g.isChecked()) {
                    setValue("sBm762", true);
                    setValue("sTm762", false);
                    m762g.setTypeface(null, Typeface.BOLD);
                    m762g.setChecked(false);
                    if (!m762g.isChecked()) {
                        setValue(String.valueOf(m762g.getText()), true);
                    }
                }

                if (mk14.isChecked()) {
                    setValue("sBmk14", true);
                    setValue("sTmk14", false);
                    mk14.setTypeface(null, Typeface.BOLD);
                    mk14.setChecked(false);
                    if (!mk14.isChecked()) {
                        setValue(String.valueOf(mk14.getText()), true);
                    }
                }

                if (mk47.isChecked()) {
                    setValue("sBmk47", true);
                    setValue("sTmk47", false);
                    mk47.setTypeface(null, Typeface.BOLD);
                    mk47.setChecked(false);
                    if (!mk47.isChecked()) {
                        setValue(String.valueOf(mk47.getText()), true);
                    }
                }

                if (mp5k.isChecked()) {
                    setValue("sBmp5k", true);
                    setValue("sTmp5k", false);
                    mp5k.setTypeface(null, Typeface.BOLD);
                    mp5k.setChecked(false);
                    if (!mp5k.isChecked()) {
                        setValue(String.valueOf(mp5k.getText()), true);
                    }
                }

                if (mini14.isChecked()) {
                    setValue("sBmini14", true);
                    setValue("sTmini14", false);
                    mini14.setTypeface(null, Typeface.BOLD);
                    mini14.setChecked(false);
                    if (!mini14.isChecked()) {
                        setValue(String.valueOf(mini14.getText()), true);
                    }
                }

                if (molotov.isChecked()) {
                    setValue("sBburn", true);
                    setValue("sTburn", false);
                    molotov.setTypeface(null, Typeface.BOLD);
                    molotov.setChecked(false);
                    if (!molotov.isChecked()) {
                        setValue(String.valueOf(molotov.getText()), true);
                    }
                }

                if (pp19.isChecked()) {
                    setValue("sBpp19", true);
                    setValue("sTpp19", false);
                    pp19.setTypeface(null, Typeface.BOLD);
                    pp19.setChecked(false);
                    if (!pp19.isChecked()) {
                        setValue(String.valueOf(pp19.getText()), true);
                    }
                }

                if (pan.isChecked()) {
                    setValue("sBpan", true);
                    setValue("sTpan", false);
                    pan.setTypeface(null, Typeface.BOLD);
                    pan.setChecked(false);
                    if (!pan.isChecked()) {
                        setValue(String.valueOf(pan.getText()), true);
                    }
                }

                if (qbu.isChecked()) {
                    setValue("sBqbu", true);
                    setValue("sTqbu", false);
                    qbu.setTypeface(null, Typeface.BOLD);
                    qbu.setChecked(false);
                    if (!qbu.isChecked()) {
                        setValue(String.valueOf(qbu.getText()), true);
                    }
                }

                if (qbz.isChecked()) {
                    setValue("sBqbz", true);
                    setValue("sTqbz", false);
                    qbz.setTypeface(null, Typeface.BOLD);
                    qbz.setChecked(false);
                    if (!qbz.isChecked()) {
                        setValue(String.valueOf(qbz.getText()), true);
                    }
                }

                if (s12k.isChecked()) {
                    setValue("sBs12k", true);
                    setValue("sTs12k", false);
                    s12k.setTypeface(null, Typeface.BOLD);
                    s12k.setChecked(false);
                    if (!s12k.isChecked()) {
                        setValue(String.valueOf(s12k.getText()), true);
                    }
                }

                if (s1897.isChecked()) {
                    setValue("sBs1897", true);
                    setValue("sTs1897", false);
                    s1897.setTypeface(null, Typeface.BOLD);
                    s1897.setChecked(false);
                    if (!s1897.isChecked()) {
                        setValue(String.valueOf(s1897.getText()), true);
                    }
                }

                if (s686.isChecked()) {
                    setValue("sBs686", true);
                    setValue("sTs686", false);
                    s686.setTypeface(null, Typeface.BOLD);
                    s686.setChecked(false);
                    if (!s686.isChecked()) {
                        setValue(String.valueOf(s686.getText()), true);
                    }
                }

                if (scarl.isChecked()) {
                    setValue("sBscar", true);
                    setValue("sTscar", false);
                    scarl.setTypeface(null, Typeface.BOLD);
                    scarl.setChecked(false);
                    if (!scarl.isChecked()) {
                        setValue(String.valueOf(scarl.getText()), true);
                    }
                }

                if (sks.isChecked()) {
                    setValue("sBsks", true);
                    setValue("sTsks", false);
                    sks.setTypeface(null, Typeface.BOLD);
                    sks.setChecked(false);
                    if (!sks.isChecked()) {
                        setValue(String.valueOf(sks.getText()), true);
                    }
                }

                if (slr.isChecked()) {
                    setValue("sBslr", true);
                    setValue("sTslr", false);
                    slr.setTypeface(null, Typeface.BOLD);
                    slr.setChecked(false);
                    if (!slr.isChecked()) {
                        setValue(String.valueOf(slr.getText()), true);
                    }
                }

                if (stunt.isChecked()) {
                    setValue("sBflash", true);
                    setValue("sTflash", false);
                    stunt.setTypeface(null, Typeface.BOLD);
                    stunt.setChecked(false);
                    if (!stunt.isChecked()) {
                        setValue(String.valueOf(stunt.getText()), true);
                    }
                }

                if (tommy.isChecked()) {
                    setValue("sBthomp", true);
                    setValue("sTthomp", false);
                    tommy.setTypeface(null, Typeface.BOLD);
                    tommy.setChecked(false);
                    if (!tommy.isChecked()) {
                        setValue(String.valueOf(tommy.getText()), true);
                    }
                }

                if (ump45.isChecked()) {
                    setValue("sBump45", true);
                    setValue("sTump45", false);
                    ump45.setTypeface(null, Typeface.BOLD);
                    ump45.setChecked(false);
                    if (!ump45.isChecked()) {
                        setValue(String.valueOf(ump45.getText()), true);
                    }
                }

                if (uzi.isChecked()) {
                    setValue("sBuzi", true);
                    setValue("sTuzi", false);
                    uzi.setTypeface(null, Typeface.BOLD);
                    uzi.setChecked(false);
                    if (!uzi.isChecked()) {
                        setValue(String.valueOf(uzi.getText()), true);
                    }
                }

                if (vss.isChecked()) {
                    setValue("sBvss", true);
                    setValue("sTvss", false);
                    vss.setTypeface(null, Typeface.BOLD);
                    vss.setChecked(false);
                    if (!vss.isChecked()) {
                        setValue(String.valueOf(vss.getText()), true);
                    }
                }
                if (vector.isChecked()) {
                    setValue("sBvect", true);
                    setValue("sTvect", false);
                    vector.setTypeface(null, Typeface.BOLD);
                    vector.setChecked(false);
                    if (!vector.isChecked()) {
                        setValue(String.valueOf(vector.getText()), true);
                    }
                }

                if (win94.isChecked()) {
                    setValue("sBwinchester", true);
                    setValue("sTwinchester", false);
                    win94.setTypeface(null, Typeface.BOLD);
                    win94.setChecked(false);
                    if (!win94.isChecked()) {
                        setValue(String.valueOf(win94.getText()), true);
                    }
                }
            }
        });

        final RadioButton fps30 = mView.findViewById(R.id.fps30);
        final RadioButton fps45 = mView.findViewById(R.id.fps45);
        final RadioButton fps60 = mView.findViewById(R.id.fps60);
        final RadioButton fps90 = mView.findViewById(R.id.fps90);

        int CheckFps = getFrameRate();
        if (CheckFps == 30) {
            fps30.setChecked(true);
        } else if (CheckFps == 60) {
            fps60.setChecked(true);
        } else if (CheckFps == 90) {
            fps90.setChecked(true);
        } else {
            fps45.setChecked(true);
        }

        fps30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    fps45.setChecked(false);
                    fps60.setChecked(false);
                    fps90.setChecked(false);
                    setFrameRate(30);
                    CanvasDrawingPaints.ChangeFps(30);
                }
            }
        });

        fps45.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    fps30.setChecked(false);
                    fps60.setChecked(false);
                    fps90.setChecked(false);
                    setFrameRate(45);
                    CanvasDrawingPaints.ChangeFps(45);
                }
            }
        });

        fps60.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    fps30.setChecked(false);
                    fps45.setChecked(false);
                    fps90.setChecked(false);
                    setFrameRate(60);
                    CanvasDrawingPaints.ChangeFps(60);
                }
            }
        });

        fps90.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    fps30.setChecked(false);
                    fps45.setChecked(false);
                    fps60.setChecked(false);
                    setFrameRate(90);
                    CanvasDrawingPaints.ChangeFps(90);
                }
            }
        });

        String dacia = cPrefs.read(String.valueOf(Dacia.getText()));
        String uazClr = cPrefs.read(String.valueOf(UAZ.getText()));
        String BuggyClr = cPrefs.read(String.valueOf(Buggy.getText()));
        String AquarailClr = cPrefs.read(String.valueOf(Jet.getText()));
        String SnowMobileClr = cPrefs.read(String.valueOf(Snowmobile.getText()));
        String TruckClr = cPrefs.read(String.valueOf(Truck.getText()));
        String brdmClr = cPrefs.read(String.valueOf(BRDM.getText()));
        String CoupeClr = cPrefs.read(String.valueOf(CoupeRB.getText()));
        String BoatClr = cPrefs.read(String.valueOf(Boat.getText()));
        String BusClr = cPrefs.read(String.valueOf(Bus.getText()));
        String MiradoClr = cPrefs.read(String.valueOf(Mirado.getText()));
        String RonyClr = cPrefs.read(String.valueOf(Rony.getText()));
        String ScooterClr = cPrefs.read(String.valueOf(Scooter.getText()));
        String tukClr = cPrefs.read(String.valueOf(Tempo.getText()));
        String MonsterClr = cPrefs.read(String.valueOf(MonsterTruck.getText()));
        String UTVClr = cPrefs.read(String.valueOf(UTV.getText()));
        String SnowBikeClr = cPrefs.read(String.valueOf(Snowbike.getText()));
        String LadaNivaClr = cPrefs.read(String.valueOf(LadaNiva.getText()));
        String BikeClr = cPrefs.read(String.valueOf(Bike.getText()));
        if (dacia.equals(RED)) {
            Dacia.setTextColor(Color.parseColor("#FF0000"));
        } else if (dacia.equals(GREEN)) {
            Dacia.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (dacia.equals(PINK)) {
            Dacia.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (dacia.equals(YELLOW)) {
            Dacia.setTextColor(Color.parseColor("#ffffff00"));
        } else if (dacia.equals(BLUE)) {
            Dacia.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (dacia.equals(WHITE)) {
            Dacia.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Dacia.setTextColor(Color.parseColor("#FFFFBEDC"));
        }
        if (uazClr.equals(RED)) {
            UAZ.setTextColor(Color.parseColor("#FF0000"));
        } else if (uazClr.equals(GREEN)) {
            UAZ.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (uazClr.equals(PINK)) {
            UAZ.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (uazClr.equals(YELLOW)) {
            UAZ.setTextColor(Color.parseColor("#ffffff00"));
        } else if (uazClr.equals(BLUE)) {
            UAZ.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (uazClr.equals(WHITE)) {
            UAZ.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            UAZ.setTextColor(Color.parseColor("#FFFFBEDC"));
        }
        if (BuggyClr.equals(RED)) {
            Buggy.setTextColor(Color.parseColor("#FF0000"));
        } else if (BuggyClr.equals(GREEN)) {
            Buggy.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (BuggyClr.equals(PINK)) {
            Buggy.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (BuggyClr.equals(YELLOW)) {
            Buggy.setTextColor(Color.parseColor("#ffffff00"));
        } else if (BuggyClr.equals(BLUE)) {
            Buggy.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (BuggyClr.equals(WHITE)) {
            Buggy.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Buggy.setTextColor(Color.parseColor("#FFFFBEDC"));
        }
        if (AquarailClr.equals(RED)) {
            Jet.setTextColor(Color.parseColor("#FF0000"));
        } else if (AquarailClr.equals(GREEN)) {
            Jet.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (AquarailClr.equals(PINK)) {
            Jet.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (AquarailClr.equals(YELLOW)) {
            Jet.setTextColor(Color.parseColor("#ffffff00"));
        } else if (AquarailClr.equals(BLUE)) {
            Jet.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (AquarailClr.equals(WHITE)) {
            Jet.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Jet.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (SnowMobileClr.equals(RED)) {
            Snowmobile.setTextColor(Color.parseColor("#FF0000"));
        } else if (SnowMobileClr.equals(GREEN)) {
            Snowmobile.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (SnowMobileClr.equals(PINK)) {
            Snowmobile.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (SnowMobileClr.equals(YELLOW)) {
            Snowmobile.setTextColor(Color.parseColor("#ffffff00"));
        } else if (SnowMobileClr.equals(BLUE)) {
            Snowmobile.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (SnowMobileClr.equals(WHITE)) {
            Snowmobile.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Snowmobile.setTextColor(Color.parseColor("#FFFFBEDC"));
        }
        if (TruckClr.equals(RED)) {
            Truck.setTextColor(Color.parseColor("#FF0000"));
        } else if (TruckClr.equals(GREEN)) {
            Truck.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (TruckClr.equals(PINK)) {
            Truck.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (TruckClr.equals(YELLOW)) {
            Truck.setTextColor(Color.parseColor("#ffffff00"));
        } else if (TruckClr.equals(BLUE)) {
            Truck.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (TruckClr.equals(WHITE)) {
            Truck.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Truck.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (brdmClr.equals(RED)) {
            BRDM.setTextColor(Color.parseColor("#FF0000"));
        } else if (brdmClr.equals(GREEN)) {
            BRDM.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (brdmClr.equals(PINK)) {
            BRDM.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (brdmClr.equals(YELLOW)) {
            BRDM.setTextColor(Color.parseColor("#ffffff00"));
        } else if (brdmClr.equals(BLUE)) {
            BRDM.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (brdmClr.equals(WHITE)) {
            BRDM.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            BRDM.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (CoupeClr.equals(RED)) {
            CoupeRB.setTextColor(Color.parseColor("#FF0000"));
        } else if (CoupeClr.equals(GREEN)) {
            CoupeRB.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (CoupeClr.equals(PINK)) {
            CoupeRB.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (CoupeClr.equals(YELLOW)) {
            CoupeRB.setTextColor(Color.parseColor("#ffffff00"));
        } else if (CoupeClr.equals(BLUE)) {
            CoupeRB.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (CoupeClr.equals(WHITE)) {
            CoupeRB.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            CoupeRB.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (BoatClr.equals(RED)) {
            Boat.setTextColor(Color.parseColor("#FF0000"));
        } else if (BoatClr.equals(GREEN)) {
            Boat.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (BoatClr.equals(PINK)) {
            Boat.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (BoatClr.equals(YELLOW)) {
            Boat.setTextColor(Color.parseColor("#ffffff00"));
        } else if (BoatClr.equals(BLUE)) {
            Boat.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (BoatClr.equals(WHITE)) {
            Boat.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Boat.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (BusClr.equals(RED)) {
            Bus.setTextColor(Color.parseColor("#FF0000"));
        } else if (BusClr.equals(GREEN)) {
            Bus.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (BusClr.equals(PINK)) {
            Bus.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (BusClr.equals(YELLOW)) {
            Bus.setTextColor(Color.parseColor("#ffffff00"));
        } else if (BusClr.equals(BLUE)) {
            Bus.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (BusClr.equals(WHITE)) {
            Bus.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Bus.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (MiradoClr.equals(RED)) {
            Mirado.setTextColor(Color.parseColor("#FF0000"));
        } else if (MiradoClr.equals(GREEN)) {
            Mirado.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (MiradoClr.equals(PINK)) {
            Mirado.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (MiradoClr.equals(YELLOW)) {
            Mirado.setTextColor(Color.parseColor("#ffffff00"));
        } else if (MiradoClr.equals(BLUE)) {
            Mirado.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (MiradoClr.equals(WHITE)) {
            Mirado.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Mirado.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (RonyClr.equals(RED)) {
            Rony.setTextColor(Color.parseColor("#FF0000"));
        } else if (RonyClr.equals(GREEN)) {
            Rony.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (RonyClr.equals(PINK)) {
            Rony.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (RonyClr.equals(YELLOW)) {
            Rony.setTextColor(Color.parseColor("#ffffff00"));
        } else if (RonyClr.equals(BLUE)) {
            Rony.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (RonyClr.equals(WHITE)) {
            Rony.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Rony.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (ScooterClr.equals(RED)) {
            Scooter.setTextColor(Color.parseColor("#FF0000"));
        } else if (ScooterClr.equals(GREEN)) {
            Scooter.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (ScooterClr.equals(PINK)) {
            Scooter.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (ScooterClr.equals(YELLOW)) {
            Scooter.setTextColor(Color.parseColor("#ffffff00"));
        } else if (ScooterClr.equals(BLUE)) {
            Scooter.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (ScooterClr.equals(WHITE)) {
            Scooter.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Scooter.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (tukClr.equals(RED)) {
            Tempo.setTextColor(Color.parseColor("#FF0000"));
        } else if (tukClr.equals(GREEN)) {
            Tempo.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (tukClr.equals(PINK)) {
            Tempo.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (tukClr.equals(YELLOW)) {
            Tempo.setTextColor(Color.parseColor("#ffffff00"));
        } else if (tukClr.equals(BLUE)) {
            Tempo.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (tukClr.equals(WHITE)) {
            Tempo.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Tempo.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (MonsterClr.equals(RED)) {
            MonsterTruck.setTextColor(Color.parseColor("#FF0000"));
        } else if (MonsterClr.equals(GREEN)) {
            MonsterTruck.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (MonsterClr.equals(PINK)) {
            MonsterTruck.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (MonsterClr.equals(YELLOW)) {
            MonsterTruck.setTextColor(Color.parseColor("#ffffff00"));
        } else if (MonsterClr.equals(BLUE)) {
            MonsterTruck.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (MonsterClr.equals(WHITE)) {
            MonsterTruck.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            MonsterTruck.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (UTVClr.equals(RED)) {
            UTV.setTextColor(Color.parseColor("#FF0000"));
        } else if (UTVClr.equals(GREEN)) {
            UTV.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (UTVClr.equals(PINK)) {
            UTV.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (UTVClr.equals(YELLOW)) {
            UTV.setTextColor(Color.parseColor("#ffffff00"));
        } else if (UTVClr.equals(BLUE)) {
            UTV.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (UTVClr.equals(WHITE)) {
            UTV.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            UTV.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (SnowBikeClr.equals(RED)) {
            Snowbike.setTextColor(Color.parseColor("#FF0000"));
        } else if (SnowBikeClr.equals(GREEN)) {
            Snowbike.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (SnowBikeClr.equals(PINK)) {
            Snowbike.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (SnowBikeClr.equals(YELLOW)) {
            Snowbike.setTextColor(Color.parseColor("#ffffff00"));
        } else if (SnowBikeClr.equals(BLUE)) {
            Snowbike.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (SnowBikeClr.equals(WHITE)) {
            Snowbike.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Snowbike.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (LadaNivaClr.equals(RED)) {
            LadaNiva.setTextColor(Color.parseColor("#FF0000"));
        } else if (LadaNivaClr.equals(GREEN)) {
            LadaNiva.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (LadaNivaClr.equals(PINK)) {
            LadaNiva.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (LadaNivaClr.equals(YELLOW)) {
            LadaNiva.setTextColor(Color.parseColor("#ffffff00"));
        } else if (LadaNivaClr.equals(BLUE)) {
            LadaNiva.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (LadaNivaClr.equals(WHITE)) {
            LadaNiva.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            LadaNiva.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (BikeClr.equals(RED)) {
            Bike.setTextColor(Color.parseColor("#FF0000"));
        } else if (BikeClr.equals(GREEN)) {
            Bike.setTextColor(Color.parseColor("#ff00ff00")); //FFFFBEDC
        } else if (BikeClr.equals(PINK)) {
            Bike.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (BikeClr.equals(YELLOW)) {
            Bike.setTextColor(Color.parseColor("#ffffff00"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else if (BikeClr.equals(WHITE)) {
            Bike.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            Bike.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        //Items
        /*TODO getValueFromPrefs*/
        String am300Clr = cPrefs.read(String.valueOf(am300.getText()));
        String am45Clr = cPrefs.read(String.valueOf(am45.getText()));
        String am12gClr = cPrefs.read(String.valueOf(am12g.getText()));
        String am556Clr = cPrefs.read(String.valueOf(am556.getText()));
        String am762Clr = cPrefs.read(String.valueOf(am762.getText()));
        String am9mmClr = cPrefs.read(String.valueOf(am9mm.getText()));

        if (am300Clr.equals(RED)) {
            am300.setTextColor(Color.parseColor("#FF0000"));
        } else if (am300Clr.equals(GREEN)) {
            am300.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (am300Clr.equals(PINK)) {
            am300.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (am300Clr.equals(YELLOW)) {
            am300.setTextColor(Color.parseColor("#ffffff00"));
        } else if (am300Clr.equals(WHITE)) {
            am300.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            am300.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (am45Clr.equals(RED)) {
            am45.setTextColor(Color.parseColor("#FF0000"));
        } else if (am45Clr.equals(GREEN)) {
            am45.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (am45Clr.equals(PINK)) {
            am45.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (am45Clr.equals(YELLOW)) {
            am45.setTextColor(Color.parseColor("#ffffff00"));
        } else if (am45Clr.equals(WHITE)) {
            am45.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else {
            am45.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (am12gClr.equals(RED)) {
            am12g.setTextColor(Color.parseColor("#FF0000"));
        } else if (am12gClr.equals(GREEN)) {
            am12g.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (am12gClr.equals(PINK)) {
            am12g.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (am12gClr.equals(YELLOW)) {
            am12g.setTextColor(Color.parseColor("#ffffff00"));
        } else if (am12gClr.equals(WHITE)) {
            am12g.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else { /*TODO*/
            am12g.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (am556Clr.equals(RED)) {
            am556.setTextColor(Color.parseColor("#FF0000"));
        } else if (am556Clr.equals(GREEN)) {
            am556.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (am556Clr.equals(PINK)) {
            am556.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (am556Clr.equals(YELLOW)) {
            am556.setTextColor(Color.parseColor("#ffffff00"));
        } else if (am556Clr.equals(WHITE)) {
            am556.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            am556.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (am762Clr.equals(RED)) {
            am762.setTextColor(Color.parseColor("#FF0000"));
        } else if (am762Clr.equals(GREEN)) {
            am762.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (am762Clr.equals(PINK)) {
            am762.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (am762Clr.equals(YELLOW)) {
            am762.setTextColor(Color.parseColor("#ffffff00"));
        } else if (am762Clr.equals(WHITE)) {
            am762.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            am762.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (am9mmClr.equals(RED)) {
            am9mm.setTextColor(Color.parseColor("#FF0000"));
        } else if (am9mmClr.equals(GREEN)) {
            am9mm.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (am9mmClr.equals(PINK)) {
            am9mm.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (am9mmClr.equals(YELLOW)) {
            am9mm.setTextColor(Color.parseColor("#ffffff00"));
        } else if (am9mmClr.equals(WHITE)) {
            am9mm.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            am9mm.setTextColor(Color.parseColor("#FFFFBEDC"));
        } //AmmodFinished

        /*TODO: save values for armors*/
        String arm2Clr = cPrefs.read(String.valueOf(armorLvl2.getText()));
        String arm3Clr = cPrefs.read(String.valueOf(armorLvl3.getText()));
        String heal2Clr = cPrefs.read(String.valueOf(helmetLvl2.getText()));
        String heal3Clr = cPrefs.read(String.valueOf(helmetLvl3.getText()));

        if (arm2Clr.equals(RED)) {
            armorLvl2.setTextColor(Color.parseColor("#FF0000"));
        } else if (arm2Clr.equals(GREEN)) {
            armorLvl2.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (arm2Clr.equals(PINK)) {
            armorLvl2.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (arm2Clr.equals(YELLOW)) {
            armorLvl2.setTextColor(Color.parseColor("#ffffff00"));
        } else if (arm2Clr.equals(WHITE)) {
            armorLvl2.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            armorLvl2.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (arm3Clr.equals(RED)) {
            armorLvl3.setTextColor(Color.parseColor("#FF0000"));
        } else if (arm3Clr.equals(GREEN)) {
            armorLvl3.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (arm3Clr.equals(PINK)) {
            armorLvl3.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (arm3Clr.equals(YELLOW)) {
            armorLvl3.setTextColor(Color.parseColor("#ffffff00"));
        } else if (arm3Clr.equals(WHITE)) {
            armorLvl3.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            armorLvl3.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (heal2Clr.equals(RED)) {
            helmetLvl2.setTextColor(Color.parseColor("#FF0000"));
        } else if (heal2Clr.equals(GREEN)) {
            helmetLvl2.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (heal2Clr.equals(PINK)) {
            helmetLvl2.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (heal2Clr.equals(YELLOW)) {
            helmetLvl2.setTextColor(Color.parseColor("#ffffff00"));
        } else if (heal2Clr.equals(WHITE)) {
            helmetLvl2.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            helmetLvl2.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (heal3Clr.equals(RED)) {
            helmetLvl3.setTextColor(Color.parseColor("#FF0000"));
        } else if (heal3Clr.equals(GREEN)) {
            helmetLvl3.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (heal3Clr.equals(PINK)) {
            helmetLvl3.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (heal3Clr.equals(YELLOW)) {
            helmetLvl3.setTextColor(Color.parseColor("#ffffff00"));
        } else if (heal3Clr.equals(WHITE)) {
            helmetLvl3.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            helmetLvl3.setTextColor(Color.parseColor("#FFFFBEDC"));
        } //ArmorsFinished here

        /*TODO: Health Color here*/
        String bandClr = cPrefs.read(String.valueOf(bandage.getText()));
        String drinkClr = cPrefs.read(String.valueOf(drink.getText()));
        String fAidClr = cPrefs.read(String.valueOf(firstAir.getText()));
        String injClr = cPrefs.read(String.valueOf(injection.getText()));
        String pKillerClr = cPrefs.read(String.valueOf(pills.getText()));


        if (bandClr.equals(RED)) {
            bandage.setTextColor(Color.parseColor("#FF0000"));
        } else if (bandClr.equals(GREEN)) {
            bandage.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (bandClr.equals(PINK)) {
            bandage.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (bandClr.equals(YELLOW)) {
            bandage.setTextColor(Color.parseColor("#ffffff00"));
        } else if (bandClr.equals(WHITE)) {
            bandage.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            bandage.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (drinkClr.equals(RED)) {
            drink.setTextColor(Color.parseColor("#FF0000"));
        } else if (drinkClr.equals(GREEN)) {
            drink.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (drinkClr.equals(PINK)) {
            drink.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (drinkClr.equals(YELLOW)) {
            drink.setTextColor(Color.parseColor("#ffffff00"));
        } else if (drinkClr.equals(WHITE)) {
            drink.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            drink.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (fAidClr.equals(RED)) {
            firstAir.setTextColor(Color.parseColor("#FF0000"));
        } else if (fAidClr.equals(GREEN)) {
            firstAir.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (fAidClr.equals(PINK)) {
            firstAir.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (fAidClr.equals(YELLOW)) {
            firstAir.setTextColor(Color.parseColor("#ffffff00"));
        } else if (fAidClr.equals(WHITE)) {
            firstAir.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            firstAir.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (injClr.equals(RED)) {
            injection.setTextColor(Color.parseColor("#FF0000"));
        } else if (injClr.equals(GREEN)) {
            injection.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (injClr.equals(PINK)) {
            injection.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (injClr.equals(YELLOW)) {
            injection.setTextColor(Color.parseColor("#ffffff00"));
        } else if (injClr.equals(WHITE)) {
            injection.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            injection.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (pKillerClr.equals(RED)) {
            pills.setTextColor(Color.parseColor("#FF0000"));
        } else if (pKillerClr.equals(GREEN)) {
            pills.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (pKillerClr.equals(PINK)) {
            pills.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (pKillerClr.equals(YELLOW)) {
            pills.setTextColor(Color.parseColor("#ffffff00"));
        } else if (pKillerClr.equals(WHITE)) {
            pills.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            pills.setTextColor(Color.parseColor("#FFFFBEDC"));
        }//HealthItemsFinished here

        /*TODO: Misk items start from here*/
        String airCraftClr = cPrefs.read(String.valueOf(airCraft.getText()));
        String airDropClr = cPrefs.read(String.valueOf(airDrop.getText()));
        String bag2Clr = cPrefs.read(String.valueOf(bag2.getText()));
        String bag3Clr = cPrefs.read(String.valueOf(bag3.getText()));
        String compoClr = cPrefs.read(String.valueOf(compensator.getText()));
        String lootClr = cPrefs.read(String.valueOf(loot.getText()));
        String supprClr = cPrefs.read(String.valueOf(supressor.getText()));

        if (airCraftClr.equals(RED)) {
            airCraft.setTextColor(Color.parseColor("#FF0000"));
        } else if (airCraftClr.equals(GREEN)) {
            airCraft.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (airCraftClr.equals(PINK)) {
            airCraft.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (airCraftClr.equals(YELLOW)) {
            airCraft.setTextColor(Color.parseColor("#ffffff00"));
        } else if (airCraftClr.equals(WHITE)) {
            airCraft.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            airCraft.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (airDropClr.equals(RED)) {
            airDrop.setTextColor(Color.parseColor("#FF0000"));
        } else if (airDropClr.equals(GREEN)) {
            airDrop.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (airDropClr.equals(PINK)) {
            airDrop.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (airDropClr.equals(YELLOW)) {
            airDrop.setTextColor(Color.parseColor("#ffffff00"));
        } else if (airDropClr.equals(WHITE)) {
            airDrop.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            airDrop.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (bag2Clr.equals(RED)) {
            bag2.setTextColor(Color.parseColor("#FF0000"));
        } else if (bag2Clr.equals(GREEN)) {
            bag2.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (bag2Clr.equals(PINK)) {
            bag2.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (bag2Clr.equals(YELLOW)) {
            bag2.setTextColor(Color.parseColor("#ffffff00"));
        } else if (bag2Clr.equals(WHITE)) {
            bag2.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            bag2.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (bag3Clr.equals(RED)) {
            bag3.setTextColor(Color.parseColor("#FF0000"));
        } else if (bag3Clr.equals(GREEN)) {
            bag3.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (bag3Clr.equals(PINK)) {
            bag3.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (bag3Clr.equals(YELLOW)) {
            bag3.setTextColor(Color.parseColor("#ffffff00"));
        } else if (bag3Clr.equals(WHITE)) {
            bag3.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            bag3.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (compoClr.equals(RED)) {
            compensator.setTextColor(Color.parseColor("#FF0000"));
        } else if (compoClr.equals(GREEN)) {
            compensator.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (compoClr.equals(PINK)) {
            compensator.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (compoClr.equals(YELLOW)) {
            compensator.setTextColor(Color.parseColor("#ffffff00"));
        } else if (compoClr.equals(WHITE)) {
            compensator.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            compensator.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (lootClr.equals(RED)) {
            loot.setTextColor(Color.parseColor("#FF0000"));
        } else if (lootClr.equals(GREEN)) {
            loot.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (lootClr.equals(PINK)) {
            loot.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (lootClr.equals(YELLOW)) {
            loot.setTextColor(Color.parseColor("#ffffff00"));
        } else if (lootClr.equals(WHITE)) {
            loot.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            loot.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (supprClr.equals(RED)) {
            supressor.setTextColor(Color.parseColor("#FF0000"));
        } else if (supprClr.equals(GREEN)) {
            supressor.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (supprClr.equals(PINK)) {
            supressor.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (supprClr.equals(YELLOW)) {
            supressor.setTextColor(Color.parseColor("#ffffff00"));
        } else if (supprClr.equals(WHITE)) {
            supressor.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (BikeClr.equals(BLUE)) {
            Bike.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            supressor.setTextColor(Color.parseColor("#FFFFBEDC"));
        }//Misk Items Finished Here

        /*TODO: Weapons color*/
        String x2Clr = cPrefs.read(String.valueOf(x2.getText()));
        String x3Clr = cPrefs.read(String.valueOf(x3.getText()));
        String x4Clr = cPrefs.read(String.valueOf(x4.getText()));
        String x6Clr = cPrefs.read(String.valueOf(x6.getText()));
        String x8Clr = cPrefs.read(String.valueOf(x8.getText()));
        String k98Clr = cPrefs.read(String.valueOf(k98.getText()));
        String akmClr = cPrefs.read(String.valueOf(akm.getText()));
        String augClr = cPrefs.read(String.valueOf(aug.getText()));
        String awmClr = cPrefs.read(String.valueOf(awm.getText()));
        String bowClr = cPrefs.read(String.valueOf(bow.getText()));
        String dp12Clr = cPrefs.read(String.valueOf(dp12.getText()));
        String dp28Clr = cPrefs.read(String.valueOf(dp28.getText()));
        String deagleClr = cPrefs.read(String.valueOf(deagle.getText()));
        String redDotClr = cPrefs.read(String.valueOf(redDot.getText()));
        String flareClr = cPrefs.read(String.valueOf(flare.getText()));
        String g36Clr = cPrefs.read(String.valueOf(g36.getText()));
        String grenadeClr = cPrefs.read(String.valueOf(grenade.getText()));
        String grozaClr = cPrefs.read(String.valueOf(groza.getText()));
        String hollowClr = cPrefs.read(String.valueOf(hollo.getText()));
        String m1014Clr = cPrefs.read(String.valueOf(m1014.getText()));
        String m16Clr = cPrefs.read(String.valueOf(m16.getText()));
        String m24Clr = cPrefs.read(String.valueOf(m24.getText()));
        String m249Clr = cPrefs.read(String.valueOf(m249.getText()));
        String m4Clr = cPrefs.read(String.valueOf(m4.getText()));
        String m762Clr = cPrefs.read(String.valueOf(m762g.getText()));
        String mk14Clr = cPrefs.read(String.valueOf(mk14.getText()));
        String mk47Clr = cPrefs.read(String.valueOf(mk47.getText()));
        String mpk5Clr = cPrefs.read(String.valueOf(mp5k.getText()));
        String mini14Clr = cPrefs.read(String.valueOf(mini14.getText()));
        String moltovClr = cPrefs.read(String.valueOf(molotov.getText()));
        String pp19Clr = cPrefs.read(String.valueOf(pp19.getText()));
        String panClr = cPrefs.read(String.valueOf(pan.getText()));
        String qbuClr = cPrefs.read(String.valueOf(qbu.getText()));
        String qbzClr = cPrefs.read(String.valueOf(qbz.getText()));
        String s12kClr = cPrefs.read(String.valueOf(s12k.getText()));
        String s1897Clr = cPrefs.read(String.valueOf(s1897.getText()));
        String s686Clr = cPrefs.read(String.valueOf(s686.getText()));
        String scarlClr = cPrefs.read(String.valueOf(scarl.getText()));
        String sksClr = cPrefs.read(String.valueOf(sks.getText()));
        String slrClr = cPrefs.read(String.valueOf(slr.getText()));
        String stungClr = cPrefs.read(String.valueOf(stunt.getText()));
        String tommyClr = cPrefs.read(String.valueOf(tommy.getText()));
        String ump45Clr = cPrefs.read(String.valueOf(ump45.getText()));
        String uziClr = cPrefs.read(String.valueOf(uzi.getText()));
        String vssClr = cPrefs.read(String.valueOf(vss.getText()));
        String vectorClr = cPrefs.read(String.valueOf(vector.getText()));
        String winChesterClr = cPrefs.read(String.valueOf(win94.getText()));

        if (x2Clr.equals(RED)) {
            x2.setTextColor(Color.parseColor("#FF0000"));
        } else if (x2Clr.equals(GREEN)) {
            x2.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (x2Clr.equals(PINK)) {
            x2.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (x2Clr.equals(YELLOW)) {
            x2.setTextColor(Color.parseColor("#ffffff00"));
        } else if (x2Clr.equals(WHITE)) {
            x2.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (x2Clr.equals(BLUE)) {
            x2.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            x2.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (x3Clr.equals(RED)) {
            x3.setTextColor(Color.parseColor("#FF0000"));
        } else if (x3Clr.equals(GREEN)) {
            x3.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (x3Clr.equals(PINK)) {
            x3.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (x3Clr.equals(YELLOW)) {
            x3.setTextColor(Color.parseColor("#ffffff00"));
        } else if (x3Clr.equals(WHITE)) {
            x3.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (x3Clr.equals(BLUE)) {
            x3.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            x3.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (x4Clr.equals(RED)) {
            x4.setTextColor(Color.parseColor("#FF0000"));
        } else if (x4Clr.equals(GREEN)) {
            x4.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (x4Clr.equals(PINK)) {
            x4.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (x4Clr.equals(YELLOW)) {
            x4.setTextColor(Color.parseColor("#ffffff00"));
        } else if (x4Clr.equals(WHITE)) {
            x4.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (x4Clr.equals(BLUE)) {
            x4.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            x4.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (x6Clr.equals(RED)) {
            x6.setTextColor(Color.parseColor("#FF0000"));
        } else if (x6Clr.equals(GREEN)) {
            x6.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (x6Clr.equals(PINK)) {
            x6.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (x6Clr.equals(YELLOW)) {
            x6.setTextColor(Color.parseColor("#ffffff00"));
        } else if (x6Clr.equals(WHITE)) {
            x6.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (x6Clr.equals(BLUE)) {
            x6.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            x6.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (x8Clr.equals(RED)) {
            x8.setTextColor(Color.parseColor("#FF0000"));
        } else if (x8Clr.equals(GREEN)) {
            x8.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (x8Clr.equals(PINK)) {
            x8.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (x8Clr.equals(YELLOW)) {
            x8.setTextColor(Color.parseColor("#ffffff00"));
        } else if (x8Clr.equals(WHITE)) {
            x8.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (x8Clr.equals(BLUE)) {
            x8.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            x8.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (k98Clr.equals(RED)) {
            k98.setTextColor(Color.parseColor("#FF0000"));
        } else if (k98Clr.equals(GREEN)) {
            k98.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (k98Clr.equals(PINK)) {
            k98.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (k98Clr.equals(YELLOW)) {
            k98.setTextColor(Color.parseColor("#ffffff00"));
        } else if (k98Clr.equals(WHITE)) {
            k98.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (k98Clr.equals(BLUE)) {
            k98.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            k98.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (akmClr.equals(RED)) {
            akm.setTextColor(Color.parseColor("#FF0000"));
        } else if (akmClr.equals(GREEN)) {
            akm.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (akmClr.equals(PINK)) {
            akm.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (akmClr.equals(YELLOW)) {
            akm.setTextColor(Color.parseColor("#ffffff00"));
        } else if (akmClr.equals(WHITE)) {
            akm.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (akmClr.equals(BLUE)) {
            akm.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            akm.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (augClr.equals(RED)) {
            aug.setTextColor(Color.parseColor("#FF0000"));
        } else if (augClr.equals(GREEN)) {
            aug.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (augClr.equals(PINK)) {
            aug.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (augClr.equals(YELLOW)) {
            aug.setTextColor(Color.parseColor("#ffffff00"));
        } else if (augClr.equals(WHITE)) {
            aug.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (augClr.equals(BLUE)) {
            aug.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            aug.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (awmClr.equals(RED)) {
            awm.setTextColor(Color.parseColor("#FF0000"));
        } else if (awmClr.equals(GREEN)) {
            awm.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (awmClr.equals(PINK)) {
            awm.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (awmClr.equals(YELLOW)) {
            awm.setTextColor(Color.parseColor("#ffffff00"));
        } else if (awmClr.equals(WHITE)) {
            awm.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (awmClr.equals(BLUE)) {
            awm.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            awm.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (bowClr.equals(RED)) {
            bow.setTextColor(Color.parseColor("#FF0000"));
        } else if (bowClr.equals(GREEN)) {
            bow.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (bowClr.equals(PINK)) {
            bow.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (bowClr.equals(YELLOW)) {
            bow.setTextColor(Color.parseColor("#ffffff00"));
        } else if (bowClr.equals(WHITE)) {
            bow.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (bowClr.equals(BLUE)) {
            bow.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            bow.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (dp12Clr.equals(RED)) {
            dp12.setTextColor(Color.parseColor("#FF0000"));
        } else if (dp12Clr.equals(GREEN)) {
            dp12.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (dp12Clr.equals(PINK)) {
            dp12.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (dp12Clr.equals(YELLOW)) {
            dp12.setTextColor(Color.parseColor("#ffffff00"));
        } else if (dp12Clr.equals(WHITE)) {
            dp12.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (dp12Clr.equals(BLUE)) {
            dp12.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            dp12.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (dp28Clr.equals(RED)) {
            dp28.setTextColor(Color.parseColor("#FF0000"));
        } else if (dp28Clr.equals(GREEN)) {
            dp28.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (dp28Clr.equals(PINK)) {
            dp28.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (dp28Clr.equals(YELLOW)) {
            dp28.setTextColor(Color.parseColor("#ffffff00"));
        } else if (dp28Clr.equals(WHITE)) {
            dp28.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (dp28Clr.equals(BLUE)) {
            dp28.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            dp28.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (deagleClr.equals(RED)) {
            deagle.setTextColor(Color.parseColor("#FF0000"));
        } else if (deagleClr.equals(GREEN)) {
            deagle.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (deagleClr.equals(PINK)) {
            deagle.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (deagleClr.equals(YELLOW)) {
            deagle.setTextColor(Color.parseColor("#ffffff00"));
        } else if (deagleClr.equals(WHITE)) {
            deagle.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (deagleClr.equals(BLUE)) {
            deagle.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            deagle.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (redDotClr.equals(RED)) {
            redDot.setTextColor(Color.parseColor("#FF0000"));
        } else if (redDotClr.equals(GREEN)) {
            redDot.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (redDotClr.equals(PINK)) {
            redDot.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (redDotClr.equals(YELLOW)) {
            redDot.setTextColor(Color.parseColor("#ffffff00"));
        } else if (redDotClr.equals(WHITE)) {
            redDot.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (redDotClr.equals(BLUE)) {
            redDot.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            redDot.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (flareClr.equals(RED)) {
            flare.setTextColor(Color.parseColor("#FF0000"));
        } else if (flareClr.equals(GREEN)) {
            flare.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (flareClr.equals(PINK)) {
            flare.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (flareClr.equals(YELLOW)) {
            flare.setTextColor(Color.parseColor("#ffffff00"));
        } else if (flareClr.equals(WHITE)) {
            flare.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (flareClr.equals(BLUE)) {
            flare.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            flare.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (g36Clr.equals(RED)) {
            g36.setTextColor(Color.parseColor("#FF0000"));
        } else if (g36Clr.equals(GREEN)) {
            g36.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (g36Clr.equals(PINK)) {
            g36.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (g36Clr.equals(YELLOW)) {
            g36.setTextColor(Color.parseColor("#ffffff00"));
        } else if (g36Clr.equals(WHITE)) {
            g36.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (g36Clr.equals(BLUE)) {
            g36.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            g36.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (grenadeClr.equals(RED)) {
            grenade.setTextColor(Color.parseColor("#FF0000"));
        } else if (grenadeClr.equals(GREEN)) {
            grenade.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (grenadeClr.equals(PINK)) {
            grenade.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (grenadeClr.equals(YELLOW)) {
            grenade.setTextColor(Color.parseColor("#ffffff00"));
        } else if (grenadeClr.equals(WHITE)) {
            grenade.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (grenadeClr.equals(BLUE)) {
            grenade.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            grenade.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (grozaClr.equals(RED)) {
            groza.setTextColor(Color.parseColor("#FF0000"));
        } else if (grozaClr.equals(GREEN)) {
            groza.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (grozaClr.equals(PINK)) {
            groza.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (grozaClr.equals(YELLOW)) {
            groza.setTextColor(Color.parseColor("#ffffff00"));
        } else if (grozaClr.equals(WHITE)) {
            groza.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (grozaClr.equals(BLUE)) {
            groza.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            groza.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (hollowClr.equals(RED)) {
            hollo.setTextColor(Color.parseColor("#FF0000"));
        } else if (hollowClr.equals(GREEN)) {
            hollo.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (hollowClr.equals(PINK)) {
            hollo.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (hollowClr.equals(YELLOW)) {
            hollo.setTextColor(Color.parseColor("#ffffff00"));
        } else if (hollowClr.equals(WHITE)) {
            hollo.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (hollowClr.equals(BLUE)) {
            hollo.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            hollo.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (m1014Clr.equals(RED)) {
            m1014.setTextColor(Color.parseColor("#FF0000"));
        } else if (m1014Clr.equals(GREEN)) {
            m1014.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (m1014Clr.equals(PINK)) {
            m1014.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (m1014Clr.equals(YELLOW)) {
            m1014.setTextColor(Color.parseColor("#ffffff00"));
        } else if (m1014Clr.equals(WHITE)) {
            m1014.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (m1014Clr.equals(BLUE)) {
            m1014.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            m1014.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (m16Clr.equals(RED)) {
            m16.setTextColor(Color.parseColor("#FF0000"));
        } else if (m16Clr.equals(GREEN)) {
            m16.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (m16Clr.equals(PINK)) {
            m16.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (m16Clr.equals(YELLOW)) {
            m16.setTextColor(Color.parseColor("#ffffff00"));
        } else if (m16Clr.equals(WHITE)) {
            m16.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (m16Clr.equals(BLUE)) {
            m16.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            m16.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (m24Clr.equals(RED)) {
            m24.setTextColor(Color.parseColor("#FF0000"));
        } else if (m24Clr.equals(GREEN)) {
            m24.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (m24Clr.equals(PINK)) {
            m24.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (m24Clr.equals(YELLOW)) {
            m24.setTextColor(Color.parseColor("#ffffff00"));
        } else if (m24Clr.equals(WHITE)) {
            m24.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (m24Clr.equals(BLUE)) {
            m24.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            m24.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (m24Clr.equals(RED)) {
            m249.setTextColor(Color.parseColor("#FF0000"));
        } else if (m24Clr.equals(GREEN)) {
            m249.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (m24Clr.equals(PINK)) {
            m249.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (m24Clr.equals(YELLOW)) {
            m249.setTextColor(Color.parseColor("#ffffff00"));
        } else if (m24Clr.equals(WHITE)) {
            m249.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (m249Clr.equals(BLUE)) {
            m249.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            m249.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (m4Clr.equals(RED)) {
            m4.setTextColor(Color.parseColor("#FF0000"));
        } else if (m4Clr.equals(GREEN)) {
            m4.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (m4Clr.equals(PINK)) {
            m4.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (m4Clr.equals(YELLOW)) {
            m4.setTextColor(Color.parseColor("#ffffff00"));
        } else if (m4Clr.equals(WHITE)) {
            m4.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (m4Clr.equals(BLUE)) {
            m4.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            m4.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (m762Clr.equals(RED)) {
            m762g.setTextColor(Color.parseColor("#FF0000"));
        } else if (m762Clr.equals(GREEN)) {
            m762g.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (m762Clr.equals(PINK)) {
            m762g.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (m762Clr.equals(YELLOW)) {
            m762g.setTextColor(Color.parseColor("#ffffff00"));
        } else if (m762Clr.equals(WHITE)) {
            m762g.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (m762Clr.equals(BLUE)) {
            m762g.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            m762g.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (mk14Clr.equals(RED)) {
            mk14.setTextColor(Color.parseColor("#FF0000"));
        } else if (mk14Clr.equals(GREEN)) {
            mk14.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (mk14Clr.equals(PINK)) {
            mk14.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (mk14Clr.equals(YELLOW)) {
            mk14.setTextColor(Color.parseColor("#ffffff00"));
        } else if (mk14Clr.equals(WHITE)) {
            mk14.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (mk14Clr.equals(BLUE)) {
            mk14.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            mk14.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (mk47Clr.equals(RED)) {
            mk47.setTextColor(Color.parseColor("#FF0000"));
        } else if (mk47Clr.equals(GREEN)) {
            mk47.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (mk47Clr.equals(PINK)) {
            mk47.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (mk47Clr.equals(YELLOW)) {
            mk47.setTextColor(Color.parseColor("#ffffff00"));
        } else if (mk47Clr.equals(WHITE)) {
            mk47.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (mk47Clr.equals(BLUE)) {
            mk47.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            mk47.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (mpk5Clr.equals(RED)) {
            mp5k.setTextColor(Color.parseColor("#FF0000"));
        } else if (mpk5Clr.equals(GREEN)) {
            mp5k.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (mpk5Clr.equals(PINK)) {
            mp5k.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (mpk5Clr.equals(YELLOW)) {
            mp5k.setTextColor(Color.parseColor("#ffffff00"));
        } else if (mpk5Clr.equals(WHITE)) {
            mp5k.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (mpk5Clr.equals(BLUE)) {
            mp5k.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            mp5k.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (mini14Clr.equals(RED)) {
            mini14.setTextColor(Color.parseColor("#FF0000"));
        } else if (mini14Clr.equals(GREEN)) {
            mini14.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (mini14Clr.equals(PINK)) {
            mini14.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (mini14Clr.equals(YELLOW)) {
            mini14.setTextColor(Color.parseColor("#ffffff00"));
        } else if (mini14Clr.equals(WHITE)) {
            mini14.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (mini14Clr.equals(BLUE)) {
            mini14.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            mini14.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (moltovClr.equals(RED)) {
            molotov.setTextColor(Color.parseColor("#FF0000"));
        } else if (moltovClr.equals(GREEN)) {
            molotov.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (moltovClr.equals(PINK)) {
            molotov.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (moltovClr.equals(YELLOW)) {
            molotov.setTextColor(Color.parseColor("#ffffff00"));
        } else if (moltovClr.equals(WHITE)) {
            molotov.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (moltovClr.equals(BLUE)) {
            molotov.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            molotov.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (pp19Clr.equals(RED)) {
            pp19.setTextColor(Color.parseColor("#FF0000"));
        } else if (pp19Clr.equals(GREEN)) {
            pp19.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (pp19Clr.equals(PINK)) {
            pp19.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (pp19Clr.equals(YELLOW)) {
            pp19.setTextColor(Color.parseColor("#ffffff00"));
        } else if (pp19Clr.equals(WHITE)) {
            pp19.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (pp19Clr.equals(BLUE)) {
            pp19.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            pp19.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (panClr.equals(RED)) {
            pan.setTextColor(Color.parseColor("#FF0000"));
        } else if (panClr.equals(GREEN)) {
            pan.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (panClr.equals(PINK)) {
            pan.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (panClr.equals(YELLOW)) {
            pan.setTextColor(Color.parseColor("#ffffff00"));
        } else if (panClr.equals(WHITE)) {
            pan.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (panClr.equals(BLUE)) {
            pan.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            pan.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (qbuClr.equals(RED)) {
            qbu.setTextColor(Color.parseColor("#FF0000"));
        } else if (qbuClr.equals(GREEN)) {
            qbu.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (qbuClr.equals(PINK)) {
            qbu.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (qbuClr.equals(YELLOW)) {
            qbu.setTextColor(Color.parseColor("#ffffff00"));
        } else if (qbuClr.equals(WHITE)) {
            qbu.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (qbuClr.equals(BLUE)) {
            qbu.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            qbu.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (qbzClr.equals(RED)) {
            qbz.setTextColor(Color.parseColor("#FF0000"));
        } else if (qbzClr.equals(GREEN)) {
            qbz.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (qbzClr.equals(PINK)) {
            qbz.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (qbzClr.equals(YELLOW)) {
            qbz.setTextColor(Color.parseColor("#ffffff00"));
        } else if (qbzClr.equals(WHITE)) {
            qbz.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (qbzClr.equals(BLUE)) {
            qbz.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            qbz.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (s12kClr.equals(RED)) {
            s12k.setTextColor(Color.parseColor("#FF0000"));
        } else if (s12kClr.equals(GREEN)) {
            s12k.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (s12kClr.equals(PINK)) {
            s12k.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (s12kClr.equals(YELLOW)) {
            s12k.setTextColor(Color.parseColor("#ffffff00"));
        } else if (s12kClr.equals(WHITE)) {
            s12k.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (s12kClr.equals(BLUE)) {
            s12k.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            s12k.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (s1897Clr.equals(RED)) {
            s1897.setTextColor(Color.parseColor("#FF0000"));
        } else if (s1897Clr.equals(GREEN)) {
            s1897.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (s1897Clr.equals(PINK)) {
            s1897.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (s1897Clr.equals(YELLOW)) {
            s1897.setTextColor(Color.parseColor("#ffffff00"));
        } else if (s1897Clr.equals(WHITE)) {
            s1897.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (s1897Clr.equals(BLUE)) {
            s1897.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            s1897.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (s686Clr.equals(RED)) {
            s686.setTextColor(Color.parseColor("#FF0000"));
        } else if (s686Clr.equals(GREEN)) {
            s686.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (s686Clr.equals(PINK)) {
            s686.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (s686Clr.equals(YELLOW)) {
            s686.setTextColor(Color.parseColor("#ffffff00"));
        } else if (s686Clr.equals(WHITE)) {
            s686.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (s686Clr.equals(BLUE)) {
            s686.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            s686.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (scarlClr.equals(RED)) {
            scarl.setTextColor(Color.parseColor("#FF0000"));
        } else if (scarlClr.equals(GREEN)) {
            scarl.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (scarlClr.equals(PINK)) {
            scarl.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (scarlClr.equals(YELLOW)) {
            scarl.setTextColor(Color.parseColor("#ffffff00"));
        } else if (scarlClr.equals(WHITE)) {
            scarl.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (scarlClr.equals(BLUE)) {
            scarl.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            scarl.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (sksClr.equals(RED)) {
            sks.setTextColor(Color.parseColor("#FF0000"));
        } else if (sksClr.equals(GREEN)) {
            sks.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (sksClr.equals(PINK)) {
            sks.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (sksClr.equals(YELLOW)) {
            sks.setTextColor(Color.parseColor("#ffffff00"));
        } else if (sksClr.equals(WHITE)) {
            sks.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (sksClr.equals(BLUE)) {
            sks.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            sks.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (slrClr.equals(RED)) {
            slr.setTextColor(Color.parseColor("#FF0000"));
        } else if (slrClr.equals(GREEN)) {
            slr.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (slrClr.equals(PINK)) {
            slr.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (slrClr.equals(YELLOW)) {
            slr.setTextColor(Color.parseColor("#ffffff00"));
        } else if (slrClr.equals(WHITE)) {
            slr.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (slrClr.equals(BLUE)) {
            slr.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            slr.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (stungClr.equals(RED)) {
            stunt.setTextColor(Color.parseColor("#FF0000"));
        } else if (stungClr.equals(GREEN)) {
            stunt.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (stungClr.equals(PINK)) {
            stunt.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (stungClr.equals(YELLOW)) {
            stunt.setTextColor(Color.parseColor("#ffffff00"));
        } else if (stungClr.equals(WHITE)) {
            stunt.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (stungClr.equals(BLUE)) {
            stunt.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            stunt.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (tommyClr.equals(RED)) {
            tommy.setTextColor(Color.parseColor("#FF0000"));
        } else if (tommyClr.equals(GREEN)) {
            tommy.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (tommyClr.equals(PINK)) {
            tommy.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (tommyClr.equals(YELLOW)) {
            tommy.setTextColor(Color.parseColor("#ffffff00"));
        } else if (tommyClr.equals(WHITE)) {
            tommy.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (tommyClr.equals(BLUE)) {
            tommy.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            tommy.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (ump45Clr.equals(RED)) {
            ump45.setTextColor(Color.parseColor("#FF0000"));
        } else if (ump45Clr.equals(GREEN)) {
            ump45.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (ump45Clr.equals(PINK)) {
            ump45.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (ump45Clr.equals(YELLOW)) {
            ump45.setTextColor(Color.parseColor("#ffffff00"));
        } else if (ump45Clr.equals(WHITE)) {
            ump45.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (ump45Clr.equals(BLUE)) {
            ump45.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            ump45.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (uziClr.equals(RED)) {
            uzi.setTextColor(Color.parseColor("#FF0000"));
        } else if (uziClr.equals(GREEN)) {
            uzi.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (uziClr.equals(PINK)) {
            uzi.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (uziClr.equals(YELLOW)) {
            uzi.setTextColor(Color.parseColor("#ffffff00"));
        } else if (uziClr.equals(WHITE)) {
            uzi.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (uziClr.equals(BLUE)) {
            uzi.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            uzi.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (vssClr.equals(RED)) {
            vss.setTextColor(Color.parseColor("#FF0000"));
        } else if (vssClr.equals(GREEN)) {
            vss.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (vssClr.equals(PINK)) {
            vss.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (vssClr.equals(YELLOW)) {
            vss.setTextColor(Color.parseColor("#ffffff00"));
        } else if (vssClr.equals(WHITE)) {
            vss.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (vssClr.equals(BLUE)) {
            vss.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            vss.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (vectorClr.equals(RED)) {
            vector.setTextColor(Color.parseColor("#FF0000"));
        } else if (vectorClr.equals(GREEN)) {
            vector.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (vectorClr.equals(PINK)) {
            vector.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (vectorClr.equals(YELLOW)) {
            vector.setTextColor(Color.parseColor("#ffffff00"));
        } else if (vectorClr.equals(WHITE)) {
            vector.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (vectorClr.equals(BLUE)) {
            vector.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            vector.setTextColor(Color.parseColor("#FFFFBEDC"));
        }

        if (winChesterClr.equals(RED)) {
            win94.setTextColor(Color.parseColor("#FF0000"));
        } else if (winChesterClr.equals(GREEN)) {
            win94.setTextColor(Color.parseColor("#ff00ff00"));
        } else if (winChesterClr.equals(PINK)) {
            win94.setTextColor(Color.parseColor("#FFFFBEDC"));
        } else if (winChesterClr.equals(YELLOW)) {
            win94.setTextColor(Color.parseColor("#ffffff00"));
        } else if (winChesterClr.equals(WHITE)) {
            win94.setTextColor(Color.parseColor("#ffc0c0c0"));
        } else if (winChesterClr.equals(BLUE)) {
            win94.setTextColor(Color.parseColor("#ff00ffff"));
        } else {
            win94.setTextColor(Color.parseColor("#FFFFBEDC"));
        }
        /*TODO All color saved in shared preferences finish here*/

        /*
         * TODO Add fontTypeHere*/
        final Typeface styleNormal = ResourcesCompat.getFont(this, R.font.arabotonormal);
        if (cPrefs.read(buggyStyle).equals(STYLET)) {
            Buggy.setTypeface(styleT);
        } else if (cPrefs.read(buggyStyle).equals(STYLEB)) {
            Buggy.setTypeface(null, Typeface.BOLD);
        } else {
            Buggy.setTypeface(styleNormal);
        }

        if (cPrefs.read(uazStyle).equals(STYLET)) {
            UAZ.setTypeface(styleT);
        } else if (cPrefs.read(uazStyle).equals(STYLEB)) {
            UAZ.setTypeface(null, Typeface.BOLD);
        } else {
            UAZ.setTypeface(styleNormal);
        }

        if (cPrefs.read(bikeStyle).equals(STYLET)) {
            Bike.setTypeface(styleT);
        } else if (cPrefs.read(bikeStyle).equals(STYLEB)) {
            Bike.setTypeface(null, Typeface.BOLD);
        } else {
            Bike.setTypeface(styleNormal);
        }

        if (cPrefs.read(daciaStyle).equals(STYLET)) {
            Dacia.setTypeface(styleT);
        } else if (cPrefs.read(daciaStyle).equals(STYLEB)) {
            Dacia.setTypeface(null, Typeface.BOLD);
        } else {
            Dacia.setTypeface(styleNormal);
        }

        if (cPrefs.read(aquarailStyle).equals(STYLET)) {
            Jet.setTypeface(styleT);
        } else if (cPrefs.read(aquarailStyle).equals(STYLEB)) {
            Jet.setTypeface(null, Typeface.BOLD);
        } else {
            Jet.setTypeface(styleNormal);
        }

        if (cPrefs.read(snowMobileStyle).equals(STYLET)) {
            Snowmobile.setTypeface(styleT);
        } else if (cPrefs.read(snowMobileStyle).equals(STYLEB)) {
            Snowmobile.setTypeface(null, Typeface.BOLD);
        } else {
            Snowmobile.setTypeface(styleNormal);
        }

        if (cPrefs.read(truckStyle).equals(STYLET)) {
            Truck.setTypeface(styleT);
        } else if (cPrefs.read(truckStyle).equals(STYLEB)) {
            Truck.setTypeface(null, Typeface.BOLD);
        } else {
            Truck.setTypeface(styleNormal);
        }

        if (cPrefs.read(brdmStyle).equals(STYLET)) {
            BRDM.setTypeface(styleT);
        } else if (cPrefs.read(brdmStyle).equals(STYLEB)) {
            BRDM.setTypeface(null, Typeface.BOLD);
        } else {
            BRDM.setTypeface(styleNormal);
        }

        if (cPrefs.read(coupeStyle).equals(STYLET)) {
            CoupeRB.setTypeface(styleT);
        } else if (cPrefs.read(coupeStyle).equals(STYLEB)) {
            CoupeRB.setTypeface(null, Typeface.BOLD);
        } else {
            CoupeRB.setTypeface(styleNormal);
        }

        if (cPrefs.read(boatStyle).equals(STYLET)) {
            Boat.setTypeface(styleT);
        } else if (cPrefs.read(boatStyle).equals(STYLEB)) {
            Boat.setTypeface(null, Typeface.BOLD);
        } else {
            Boat.setTypeface(styleNormal);
        }

        if (cPrefs.read(busStyle).equals(STYLET)) {
            Bus.setTypeface(styleT);
        } else if (cPrefs.read(busStyle).equals(STYLEB)) {
            Bus.setTypeface(null, Typeface.BOLD);
        } else {
            Bus.setTypeface(styleNormal);
        }

        if (cPrefs.read(miradoStyle).equals(STYLET)) {
            Mirado.setTypeface(styleT);
        } else if (cPrefs.read(miradoStyle).equals(STYLEB)) {
            Mirado.setTypeface(null, Typeface.BOLD);
        } else {
            Mirado.setTypeface(styleNormal);
        }

        if (cPrefs.read(ronyStyle).equals(STYLET)) {
            Rony.setTypeface(styleT);
        } else if (cPrefs.read(ronyStyle).equals(STYLEB)) {
            Rony.setTypeface(null, Typeface.BOLD);
        } else {
            Rony.setTypeface(styleNormal);
        }

        if (cPrefs.read(scooterStyle).equals(STYLET)) {
            Scooter.setTypeface(styleT);
        } else if (cPrefs.read(scooterStyle).equals(STYLEB)) {
            Scooter.setTypeface(null, Typeface.BOLD);
        } else {
            Scooter.setTypeface(styleNormal);
        }

        if (cPrefs.read(tukStyle).equals(STYLET)) {
            Tempo.setTypeface(styleT);
        } else if (cPrefs.read(tukStyle).equals(STYLEB)) {
            Tempo.setTypeface(null, Typeface.BOLD);
        } else {
            Tempo.setTypeface(styleNormal);
        }

        if (cPrefs.read(monsterStyle).equals(STYLET)) {
            MonsterTruck.setTypeface(styleT);
        } else if (cPrefs.read(monsterStyle).equals(STYLEB)) {
            MonsterTruck.setTypeface(null, Typeface.BOLD);
        } else {
            MonsterTruck.setTypeface(styleNormal);
        }

        if (cPrefs.read(utvStyle).equals(STYLET)) {
            UTV.setTypeface(styleT);
        } else if (cPrefs.read(utvStyle).equals(STYLEB)) {
            UTV.setTypeface(null, Typeface.BOLD);
        } else {
            UTV.setTypeface(styleNormal);
        }

        if (cPrefs.read(snowbikeStyle).equals(STYLET)) {
            Snowbike.setTypeface(styleT);
        } else if (cPrefs.read(snowbikeStyle).equals(STYLEB)) {
            Snowbike.setTypeface(null, Typeface.BOLD);
        } else {
            Snowbike.setTypeface(styleNormal);
        }

        if (cPrefs.read(ladanivaStyle).equals(STYLET)) {
            LadaNiva.setTypeface(styleT);
        } else if (cPrefs.read(ladanivaStyle).equals(STYLEB)) {
            LadaNiva.setTypeface(null, Typeface.BOLD);
        } else {
            LadaNiva.setTypeface(styleNormal);
        }
        /*TODO Vehicles style saving finished*/

    }//init
    public native void SettingValue(int setting_code, boolean value);
}
class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return true;
    }
}
