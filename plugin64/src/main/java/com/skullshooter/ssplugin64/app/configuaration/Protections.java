package com.skullshooter.ssplugin64.app.configuaration;

public class Protections {
    public static final String p1 = "/data/app/com.tencent.ig*/lib/arm64/" + "{libapp.so,libc++_shared.so,libflutter.so,libgamemaster.so,libgcloudarch.so,libhelpshiftlistener.so,libigshare.so,liblbs.so,libmarsxlog.so,libmmkv.so,libnpps-jni.so,libsentry.so,libsentry-android.so,libst-engine.so,libtgpa.so,libzip.so,libBugly.so,libImSDK.so,libkk-image.so,libsoundtouch.so}";
    public static final String p2 = "/data/app/*/com.tencent.ig*/lib/arm64/" + "{libapp.so,libc++_shared.so,libflutter.so,libgamemaster.so,libgcloudarch.so,libhelpshiftlistener.so,libigshare.so,liblbs.so,libmarsxlog.so,libmmkv.so,libnpps-jni.so,libsentry.so,libsentry-android.so,libst-engine.so,libtgpa.so,libzip.so,libBugly.so,libImSDK.so,libkk-image.so,libsoundtouch.so}";
    public static final String dirP1 = "/data/app/com.tencent.ig*/lib/arm64/lib*"; //chmod 504
    public static final String dirP2 = "/data/app/com.tencent.ig*/oat/arm64/base*"; //chmod 004
    public static final String dirP3 = "/data/app/*/com.tencent.ig*/lib/arm64/lib*"; //chmod 504
    public static final String dirP4 = "/data/app/*/com.tencent.ig*/oat/arm64/base*"; //chmod004
}
