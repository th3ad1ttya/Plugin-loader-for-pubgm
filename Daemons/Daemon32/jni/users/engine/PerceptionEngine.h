//
// Created by Adittya on 6/18/2022.
//

#ifndef PLUGIN_PERCEPTIONENGINE_H
#define PLUGIN_PERCEPTIONENGINE_H

#include "../SocketInformations/WordStructure.h"
#include "../import.h"

class PerceptionEngine
        {
private:
    JNIEnv *_env;
    jobject _cvsView;
    jobject _cvs;


public:
    PerceptionEngine()
    {
        _env = nullptr;
        _cvsView = nullptr;
        _cvs = nullptr;
    }

    PerceptionEngine(JNIEnv *env, jobject cvsView, jobject cvs)
    {
        this->_env = env;
        this->_cvsView = cvsView;
        this->_cvs = cvs;
    }

    bool isValid() const
    {
        return (_env != nullptr && _cvsView != nullptr && _cvs != nullptr);
    }

    int getWidth() const
    {
        if (isValid()) {
            jclass canvas = _env->GetObjectClass(_cvs);
            jmethodID width = _env->GetMethodID(canvas, "getWidth", "()I");
            return _env->CallIntMethod(_cvs, width);
        }
        return 0;
    }

    int getHeight() const
    {
        if (isValid())
        {
            jclass canvas = _env->GetObjectClass(_cvs);
            jmethodID width = _env->GetMethodID(canvas, "getHeight", "()I");
            return _env->CallIntMethod(_cvs, width);
        }
        return 0;
    }

    void DrawLine(Color color, float thickness, Vect2 start, Vect2 end)
    {
        if (isValid())
        {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawline = _env->GetMethodID(canvasView, "DrawLine",
                                                   "(Landroid/graphics/Canvas;IIIIFFFFF)V");
            _env->CallVoidMethod(_cvsView, drawline, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 thickness,
                                 start.x, start.y, end.x, end.y);
        }
    }

    void DrawText(Color color, const char *txt, Vect2 pos, float size)
    {
        if (isValid())
        {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawText",
                                                   "(Landroid/graphics/Canvas;IIIILjava/lang/String;FFF)V");
            jstring s=_env->NewStringUTF(txt);
            _env->CallVoidMethod(_cvsView, drawtext, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 s, pos.x, pos.y, size);
            _env->DeleteLocalRef(s);
        }
    }
    void DrawTextWarning(Color color, const char *txt, Vect2 pos, float size)
    {
        if (isValid())
        {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtextwarn = _env->GetMethodID(canvasView, "DrawTextWarning",
                                                   "(Landroid/graphics/Canvas;IIIILjava/lang/String;FFF)V");
            jstring s=_env->NewStringUTF(txt);
            _env->CallVoidMethod(_cvsView, drawtextwarn, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 s, pos.x, pos.y, size);
            _env->DeleteLocalRef(s);
        }
    }
    void DrawName(Color color, const char *txt, Vect2 pos, float size) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawName",
                                                   "(Landroid/graphics/Canvas;IIIILjava/lang/String;FFF)V");
            jstring s=_env->NewStringUTF(txt);
            _env->CallVoidMethod(_cvsView, drawtext, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 s, pos.x, pos.y, size);
            _env->DeleteLocalRef(s);
        }
    }

    void DrawActorsCount(int colors, Vect2 d)
    {
        if (isValid())
        {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawActorsCount",
                                                   "(Landroid/graphics/Canvas;IFF)V");
            _env->CallVoidMethod(_cvsView, drawtext, _cvs, colors, d.x, d.y);
        }
    }//DrawCount

    void drawRectAngle(Color color, float strokeSize, Vect2 start, Vect2 end)
    {
        if (isValid())
        {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID  drawRectAngle = _env->GetMethodID(canvasView, "drawRectAngle",
                    "(Landroid/graphics/Canvas;IIIIFFFFF)V");
            _env->CallVoidMethod(_cvsView, drawRectAngle, _cvs, (int) color.a, (int) color.r,
                    (int) color.g, (int) color.b, strokeSize, start.x, start.y, end.x, end.y);
        }
    }

    void DrawFilledCircle(Color color, Vect2 pos, float radius) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawfilledcircle = _env->GetMethodID(canvasView, "DrawFilledCircle",
                                                           "(Landroid/graphics/Canvas;IIIIFFF)V");
            _env->CallVoidMethod(_cvsView, drawfilledcircle, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b, pos.x, pos.y, radius);
        }
    }

    void DrawRect(Color color, float thickness, Vect2 start, Vect2 end) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawline = _env->GetMethodID(canvasView, "DrawRect",
                                                   "(Landroid/graphics/Canvas;IIIIFFFFF)V");
            _env->CallVoidMethod(_cvsView, drawline, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 thickness,
                                 start.x, start.y, end.x, end.y);
        }
    }

    void DrawFilledRect(Color color, Vect2 start, Vect2 end) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawline = _env->GetMethodID(canvasView, "DrawFilledRect",
                                                   "(Landroid/graphics/Canvas;IIIIFFFF)V");
            _env->CallVoidMethod(_cvsView, drawline, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 start.x, start.y, end.x, end.y);
        }
    }

    void DrawTeamID(Color color, int teamid, Vect2 pos, float size) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawTeamID",
                                                   "(Landroid/graphics/Canvas;IIIIIFFF)V");
            _env->CallVoidMethod(_cvsView, drawtext, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 teamid, pos.x, pos.y, size);
        }
    }

    void DrawOTH(Vect2 lebar, Vect2 start) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawline = _env->GetMethodID(canvasView, "DrawOTH",
                                                   "(Landroid/graphics/Canvas;IIFF)V");
            _env->CallVoidMethod(_cvsView, drawline, _cvs, (int) lebar.x, (int) lebar.y, start.x, start.y);
        }
    }

    void DrawTextName(Color color, const char *txt, Vect2 pos, float size) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawTextName",
                                                   "(Landroid/graphics/Canvas;IIIILjava/lang/String;FFF)V");
            jstring s=_env->NewStringUTF(txt);
            _env->CallVoidMethod(_cvsView, drawtext, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 s, pos.x, pos.y, size);
            _env->DeleteLocalRef(s);
        }
    }

    void DrawText1(Color color, const char *txt, Vect2 pos, float size) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawText1",
                                                   "(Landroid/graphics/Canvas;IIIILjava/lang/String;FFF)V");
            jstring s=_env->NewStringUTF(txt);
            _env->CallVoidMethod(_cvsView, drawtext, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 s, pos.x, pos.y, size);
            _env->DeleteLocalRef(s);
        }
    }


    void DrawLineRadar(Color color, float thickness, Vect2 start, Vect2 end) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawline = _env->GetMethodID(canvasView, "DrawLineRadar",
                                                   "(Landroid/graphics/Canvas;IIIIFFFFF)V");
            _env->CallVoidMethod(_cvsView, drawline, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 thickness,
                                 start.x, start.y, end.x, end.y);
        }
    }
    void DrawWeapon(Color color, int wid, Vect2 pos, float size) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawWeapon",
                                                   "(Landroid/graphics/Canvas;IIIIIIFFF)V");
            _env->CallVoidMethod(_cvsView, drawtext, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 wid, pos.x, pos.y, size);
        }
    }

    void DrawWeaponAmmo(Color color, int wid,int ammo, Vect2 pos, float size) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawWeaponAmmo",
                                                   "(Landroid/graphics/Canvas;IIIIIIFFF)V");
            _env->CallVoidMethod(_cvsView, drawtext, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 wid,ammo, pos.x, pos.y, size);
        }
    }


    void EnemyCount(Color color, const char *txt, Vect2 pos, float size)
    {
        if (isValid())
        {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawEnemyCount = _env->GetMethodID(canvasView, "EnemyCount",
                                                   "(Landroid/graphics/Canvas;IIIILjava/lang/String;FFF)V");
            jstring s=_env->NewStringUTF(txt);
            _env->CallVoidMethod(_cvsView, drawEnemyCount, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b,
                                 s, pos.x, pos.y, size);
            _env->DeleteLocalRef(s);
        }
    }

    void DrawVehicles(const char *txt, float distance, Vect2 pos, float size) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawVehicles",
                                                   "(Landroid/graphics/Canvas;Ljava/lang/String;FFFF)V");
            jstring s=_env->NewStringUTF(txt);
            _env->CallVoidMethod(_cvsView, drawtext, _cvs,
                                 s ,distance, pos.x, pos.y, size);
            _env->DeleteLocalRef(s);
        }
    }

    void DrawItems(const char *txt, float distance, Vect2 pos, float size) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawtext = _env->GetMethodID(canvasView, "DrawItems",
                                                   "(Landroid/graphics/Canvas;Ljava/lang/String;FFFF)V");
            jstring s=_env->NewStringUTF(txt);
            _env->CallVoidMethod(_cvsView, drawtext, _cvs,
                                 s,distance, pos.x, pos.y, size);
            _env->DeleteLocalRef(s);
        }
    }

    void DrawCircle(Color color, Vect2 c, float radius) {
        if (isValid()) {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID drawCircle = _env->GetMethodID(canvasView, "DrawCircle",
                                                     "(Landroid/graphics/Canvas;IIIIFFF)V");
            _env->CallVoidMethod(_cvsView, drawCircle, _cvs, (int) color.a, (int) color.r,
                                 (int) color.g, (int) color.b, c.x, c.y, radius);
        }
    }

    void DrawFOV(Color color, Vect2 pos, float radius, float thickness)
    {
        if (isValid())
        {
            jclass canvasView = _env->GetObjectClass(_cvsView);
            jmethodID  drawfov = _env->GetMethodID(canvasView, "DrawFOV",
                    "(Landroid/graphics/Canvas;IIIIFFFF)V");
            _env->CallVoidMethod(_cvsView, drawfov, _cvs, (int) color.a, (int) color.r, (int) color.g, (int) color.b, pos.x, pos.y, radius);
        }
    }


};

#endif //PLUGIN_PERCEPTIONENGINE_H
