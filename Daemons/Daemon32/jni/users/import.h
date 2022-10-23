//
// Created by Adittya on 6/19/2022.
//

#ifndef PLUGIN_IMPORT_H
#define PLUGIN_IMPORT_H

#include <jni.h>
#include <string>
#include <cstdlib>
#include <unistd.h>
#include <sys/mman.h>
#include <android/log.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <cerrno>
#include <sys/un.h>
#include <cstring>
#include <string>
#include <cmath>
#include "SocketInformations/WordStructure.h"

bool actorsName = true; //name
bool actorsTeamID = true; //Team id
bool actorsCounter = true; //count
bool actorsSkeleton = true; //skeleton
bool actorsBoundingBoxLegecy = true; //legecyBox
bool actorsBoundingBoxNormal = true; //normal Box
bool worldEdgeWarnings = true; //360alert
bool worldThrowableWarning = true; //grenadeWarning
bool actorsWeapons = true;//Weapon
bool actorsWeaponAmmo = true;//WeaponAmmo
bool actorDistance = true;//distance
bool actorLine = true; //Line
bool actorsRedDots = true; //RedDots
bool actorsHealth = true; //Health
bool onScreenFOVDisplay = true; //AimbotFOV
bool hideOverlay = false; //hideESP

int espScallingSize;
int verticalOffsets = 0;




Vect2 pushToScreenBorder(Vect2 Pos, Vect2 screen, int offset) {
    int x = (int)Pos.x;
    int y = (int)Pos.y;
    if (Pos.y < 0) {
        // top
        y = -offset;
    }
    if (Pos.x > screen.x) {
        // right
        x = (int)screen.x + offset;
    }
    if (Pos.y > screen.y) {
        // bottom
        y = (int)screen.y + offset;
    }
    if (Pos.x < 0) {
        // left
        x = -offset;
    }
    return Vect2(x, y);
}

bool isOutsideSafeZone(Vect2 pos, Vect2 screen) {
    if (pos.y < 0) {
        return true;
    }
    if (pos.x > screen.x) {
        return true;
    }
    if (pos.y > screen.y) {
        return true;
    }
    return pos.x < 0;
}









#endif //PLUGIN_IMPORT_H
