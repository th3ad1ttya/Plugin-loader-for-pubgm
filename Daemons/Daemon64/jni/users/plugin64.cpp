#include <jni.h>
#include <string>
#include "engine/PerceptionEngine.h"
#include "canvases/CanvasDrawings.h"

PerceptionEngine espOverlay;
int type=1,utype=2;
extern "C" JNIEXPORT void JNICALL
//xyz_xncos_pluginframework_plugin_plugin
Java_com_skullshooter_ssplugin64_app_services_WindowOverlay_DrawOn(JNIEnv *env, jclass , jobject espView, jobject canvas) {
    espOverlay = PerceptionEngine(env, espView, canvas);
    if (espOverlay.isValid()){
        DrawCanvas(espOverlay, espOverlay.getWidth(), espOverlay.getHeight());
    }
}
//com_skullshooter_ssplugin64_app_services
extern "C" JNIEXPORT void JNICALL
Java_com_skullshooter_ssplugin64_app_services_WindowOverlay_Close(JNIEnv *, jobject ) {
    Close();
}
extern "C" JNIEXPORT void JNICALL
Java_com_skullshooter_ssplugin64_app_services_SsFlawMain_SettingValue(JNIEnv *, jobject , jint code, jboolean jboolean1) {
    switch((int)code){
        case 1:
            actorsName = jboolean1;
            break;
        case 2:
            actorsCounter = jboolean1;
            break;
        case 3:
            actorsSkeleton = jboolean1;
            break;
        case 4:
            actorsBoundingBoxNormal = jboolean1;
            break;
        case 5:
            actorsBoundingBoxLegecy = jboolean1;
            break;
        case 6:
            worldEdgeWarnings = jboolean1;
            break;
        case 7:
            actorsTeamID = jboolean1;
            break;
        case 8:
            actorDistance = jboolean1;
            break;
        case 9:
            actorLine = jboolean1;
            break;
        case 10:
            actorsRedDots = jboolean1;
            break;
        case 11: actorsHealth = jboolean1;
            break;
        case 12: actorsWeapons = jboolean1;
            break;
        case 13: actorsWeaponAmmo = jboolean1;
            break;

        case 14: worldThrowableWarning = jboolean1;
            break;

            //Aimbot
        case 15: onScreenFOVDisplay = jboolean1;
            break;

            //Hide overlay
        case 16: hideOverlay = jboolean1;
            break;


    }
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_skullshooter_ssplugin64_app_services_WindowOverlay_getReady(JNIEnv *, jclass , int typeofgame) {
    int sockCheck=1;
    if (!Create()) {
        perror("Creation failed");
        return false;
    }
    setsockopt(sock,SOL_SOCKET,SO_REUSEADDR,&sockCheck, sizeof(int));
    if (!Bind()) {
        perror("Bind failed");
        return false;
    }

    if (!Listen()) {
        perror("Listen failed");
        return false;
    }
    if (Accept()) {
        SetValue sv{};
        sv.mode=typeofgame;
        sv.type=utype;
        send((void*)&sv,sizeof(sv));
        // Close();
        return true;
    }

}