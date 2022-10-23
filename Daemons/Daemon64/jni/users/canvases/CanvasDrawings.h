//
// Created by Adittya on 6/18/2022.
//

#ifndef PLUGIN_CANVASDRAWINGS_H
#define PLUGIN_CANVASDRAWINGS_H

#include "../engine/obfuscate.h"
#include <chrono>
#include "../SocketInformations/GameSocket.h"
#include "../engine/PerceptionEngine.h"
#include "FRAGColor.h"

uint64_t GetTickCount() {
    using namespace std::chrono;
    return duration_cast<milliseconds>(steady_clock::now().time_since_epoch()).count();
}

class Interval {
private:
    int initial_;

public:
    inline Interval() : initial_(GetTickCount()) {}

    virtual ~Interval() {}

    inline unsigned int value() const {
        return GetTickCount() - initial_;
    }
};

class FPS {
protected:
    int32_t m_fps;
    int32_t m_fpscount;
    Interval m_fpsinterval;

public:
    FPS() : m_fps(0), m_fpscount(0) {}

    void Update() {
        m_fpscount++;
        if (m_fpsinterval.value() > 1000) {
            m_fps = m_fpscount;
            m_fpscount = 0;
            m_fpsinterval = Interval();
        }
    }

    int32_t get() const {
        return m_fps;
    }
};

FPS m_fps;

Request mRequest;
Response mResponse;
float x,y,yPosName, yTeamIDpos;
char extra[30];
int aiCount,actorsCount;
Color color,colorFilled,colorHead,colorHealth,colorID,colorAlert, colorBoundingBox1;
bool IsInCenter(Rect box, Vect2 Crosshair) {
    return box.x < Crosshair.x && box.y < Crosshair.y && box.x + box.width > Crosshair.x && box.y + box.height  > Crosshair.y;
}


void DrawCanvas(PerceptionEngine esp, int displayWidth, int displayHeight) {
    // if(isESP) {
    aiCount = 0;
    actorsCount = 0;
    mRequest.DisplayHeight = displayHeight;
    mRequest.DisplayWidth = displayWidth;
    mRequest.Mode = InitMode;
    send((void *) &mRequest, sizeof(mRequest));
    receive((void *) &mResponse);
    char hello[50] = "failed";
    int count = mResponse.ActorCount;
    if (mResponse.Success) {
        Vect2 screen(displayWidth, displayHeight);
        float mScale = displayHeight / (float) 1080;
        float textsize = displayHeight / 38;
        float itemSize = displayHeight / 45;

        if (!hideOverlay) {
            for (int i = 0; i < mResponse.ActorCount; i++) {
                x = mResponse.Actors[i].HeadPosition.x;
                y = mResponse.Actors[i].HeadPosition.y;
                float wizard_number = (mResponse.Actors[i].Distance * mResponse.fov);
                float mx = (displayWidth / 4) / wizard_number;
                float my = (displayWidth / 1.38) / wizard_number;
                float top = y - my + (displayWidth / 1.7) / wizard_number;
                float bottom =
                        mResponse.Actors[i].Bone.root.y + my - (displayWidth / 1.7) / wizard_number;
                yPosName = top - displayHeight / 21;
                yTeamIDpos = top - displayHeight / 21;
                float P_Height = bottom - top;
                float P_Width = P_Height * 0.65f;
                Vect2 vBox(x - (P_Width / 2), y);
                Rect BoxRect(vBox.x, vBox.y, P_Width, P_Height);


                ////=================ColoringFor AI and Players================////
                if (mResponse.Actors[i].isAI) {
                    aiCount++;
                    color = Color(255, 255, 255, 255);
                    colorFilled = Color(255, 255, 255, 56);
                    colorBoundingBox1 = Color(255, 255, 255, 15);
                    colorHead = Color(255, 255, 255, 255);
                    colorAlert = Color(255, 255, 255, 140);
                } else if (!mResponse.Actors[i].isAI) {
                    color = Color(255, 0, 0, 255);
                    colorFilled = Color(255, 0, 0, 56);
                    colorBoundingBox1 = Color(255, 0, 0, 15);
                    colorHead = Color(255, 0, 0, 255);
                    colorAlert = Color(255, 0, 0, 140);
                }//ColoringFor AI and Players
                if (mResponse.Actors[i].ActorTeamID == 1) {
                    colorID = Color(0, 245, 0, 200);
                } else if (mResponse.Actors[i].ActorTeamID == 2) {
                    colorID = Color(255, 142, 65);
                }
                if (IsInCenter(BoxRect, Vect2(displayWidth / 2, displayHeight / 2))) {
                    color = Color(0, 255, 0, 255);
                    colorFilled = Color(0, 255, 0, 50);
                    colorBoundingBox1 = Color(0, 255, 0, 15);
                    colorHead = Color(0, 255, 0, 255);
                }

                ////=================Skeleton mesh================////
                if (mResponse.Actors[i].HeadPosition.z != 1) {
                    if (x > -50 && x < displayWidth + 50) {
                        if (actorsSkeleton && mResponse.Actors[i].Bone.isBone) {
                            esp.DrawFilledCircle(colorHead,
                                                 Vect2(mResponse.Actors[i].HeadPosition.x,
                                                       mResponse.Actors[i].HeadPosition.y),
                                                 displayHeight / 22 / wizard_number);
                            esp.DrawLine(color, 4.3, Vect2(x, y),
                                         Vect2(mResponse.Actors[i].Bone.neck.x,
                                               mResponse.Actors[i].Bone.neck.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.neck.x,
                                                           mResponse.Actors[i].Bone.neck.y),
                                         Vect2(mResponse.Actors[i].Bone.chest.x,
                                               mResponse.Actors[i].Bone.chest.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.chest.x,
                                                           mResponse.Actors[i].Bone.chest.y),
                                         Vect2(mResponse.Actors[i].Bone.pelvis.x,
                                               mResponse.Actors[i].Bone.pelvis.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.neck.x,
                                                           mResponse.Actors[i].Bone.neck.y),
                                         Vect2(mResponse.Actors[i].Bone.lSh.x,
                                               mResponse.Actors[i].Bone.lSh.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.neck.x,
                                                           mResponse.Actors[i].Bone.neck.y),
                                         Vect2(mResponse.Actors[i].Bone.rSh.x,
                                               mResponse.Actors[i].Bone.rSh.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.lSh.x,
                                                           mResponse.Actors[i].Bone.lSh.y),
                                         Vect2(mResponse.Actors[i].Bone.lElb.x,
                                               mResponse.Actors[i].Bone.lElb.y));

                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.rSh.x,
                                                           mResponse.Actors[i].Bone.rSh.y),
                                         Vect2(mResponse.Actors[i].Bone.rElb.x,
                                               mResponse.Actors[i].Bone.rElb.y));

                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.lElb.x,
                                                           mResponse.Actors[i].Bone.lElb.y),
                                         Vect2(mResponse.Actors[i].Bone.lWr.x,
                                               mResponse.Actors[i].Bone.lWr.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.rElb.x,
                                                           mResponse.Actors[i].Bone.rElb.y),
                                         Vect2(mResponse.Actors[i].Bone.rWr.x,
                                               mResponse.Actors[i].Bone.rWr.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.pelvis.x,
                                                           mResponse.Actors[i].Bone.pelvis.y),
                                         Vect2(mResponse.Actors[i].Bone.lTh.x,
                                               mResponse.Actors[i].Bone.lTh.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.pelvis.x,
                                                           mResponse.Actors[i].Bone.pelvis.y),
                                         Vect2(mResponse.Actors[i].Bone.rTh.x,
                                               mResponse.Actors[i].Bone.rTh.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.lTh.x,
                                                           mResponse.Actors[i].Bone.lTh.y),
                                         Vect2(mResponse.Actors[i].Bone.lKn.x,
                                               mResponse.Actors[i].Bone.lKn.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.rTh.x,
                                                           mResponse.Actors[i].Bone.rTh.y),
                                         Vect2(mResponse.Actors[i].Bone.rKn.x,
                                               mResponse.Actors[i].Bone.rKn.y));

                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.lKn.x,
                                                           mResponse.Actors[i].Bone.lKn.y),
                                         Vect2(mResponse.Actors[i].Bone.lAn.x,
                                               mResponse.Actors[i].Bone.lAn.y));
                            esp.DrawLine(color, 4.3, Vect2(mResponse.Actors[i].Bone.rKn.x,
                                                           mResponse.Actors[i].Bone.rKn.y),
                                         Vect2(mResponse.Actors[i].Bone.rAn.x,
                                               mResponse.Actors[i].Bone.rAn.y));
                        }

                        ////=================Bounding box normal================////
                        if (actorsBoundingBoxNormal) {
                            esp.DrawRect(color, 4.5, Vect2(mResponse.Actors[i].Bone.neck.x - mx, top),
                                         Vect2(mResponse.Actors[i].Bone.rKn.x + mx, bottom));
                            esp.DrawFilledRect(colorBoundingBox1,
                                               Vect2(mResponse.Actors[i].Bone.neck.x - mx, top),
                                               Vect2(mResponse.Actors[i].Bone.rKn.x + mx, bottom));
                        }
                        ////=================Bounding box legacy================////
                        if (actorsBoundingBoxLegecy) {
                            esp.DrawRect(
                                    color,
                                    4.5, Vect2(mResponse.Actors[i].Bone.neck.x - mx, top),
                                    Vect2(mResponse.Actors[i].Bone.rKn.x + mx, bottom)
                            );
                            esp.DrawFilledRect(
                                    colorFilled,
                                    Vect2(mResponse.Actors[i].Bone.neck.x - mx, top),
                                    Vect2(mResponse.Actors[i].Bone.rKn.x + mx, bottom)
                            );
                        }
                        ////=================Actors Health================////
                        if (actorsHealth) {
                            float healthLength = displayWidth / 22;
                            if (healthLength < mx)
                                healthLength = mx;
                            if (mResponse.Actors[i].ActorHealth < 25)
                                colorHealth = Color(255, 0, 0, 145);
                            else if (mResponse.Actors[i].ActorHealth < 50)
                                colorHealth = Color(255, 165, 0, 145);
                            else if (mResponse.Actors[i].ActorHealth < 75)
                                colorHealth = Color(255, 255, 0, 145);
                            else
                                colorHealth = Color(30, 230, 15, 80);
                                esp.DrawFilledRect(colorHealth,
                                                   Vect2(x - healthLength, top - displayHeight / 13),
                                                   Vect2(x - healthLength + (2 * healthLength) *
                                                                            mResponse.Actors[i].ActorHealth /
                                                                            100,
                                                         top - displayHeight / 28));
                                esp.DrawRect(Color(0, 0, 0), 1.5f,
                                             Vect2(x - healthLength, top - displayHeight / 13),
                                             Vect2(x + healthLength, top - displayHeight / 28));
                            if (mResponse.Actors[i].ActorHealth == 0) {
                                esp.DrawRect(Color(0, 0, 0), 1.5f,
                                             Vect2(x - healthLength, top - displayHeight / 13),
                                             Vect2(x + healthLength, top - displayHeight / 28));
                            }
                        }

                        ////=================Actors Name================////
                        if (actorsName && mResponse.Actors[i].isAI) {
                            esp.DrawOTH(Vect2(textsize + (displayHeight / 144), textsize),
                                        Vect2(x - displayWidth / 42.5, top - displayHeight / 14.5));
                            esp.DrawText(Color::White(), "[AI]",
                                         Vect2(x + 9, yPosName), textsize);
                        } else if (actorsName) {
                            esp.DrawName(Color().White(), mResponse.Actors[i].ActorNameByte,
                                         Vect2(x, yPosName), textsize);
                        }


                        ////=================Actors TeamID================////
                        if (actorsTeamID) {
                            if (!mResponse.Actors[i].isAI) {
                                float sizing = x;
                                float ax = displayWidth / 25;
                                if (actorsName) {
                                    sizing = x - actorsTeamID - ax;
                                }
                                if (actorsName) {
                                    esp.DrawTeamID(Color(36, 255, 3), mResponse.Actors[i].ActorTeamID,
                                                   Vect2(sizing, yPosName),
                                                   textsize);
                                } else {
                                    esp.DrawTeamID(Color(36, 255, 3), mResponse.Actors[i].ActorTeamID,
                                                   Vect2(x, yTeamIDpos),
                                                   textsize);
                                }
                            }
                        } //Team id

                        ////=================Actors distance================////
                        if (actorDistance) {
                            if (!mResponse.Actors[i].isAI) {
                                sprintf(extra, "%0.0fm", mResponse.Actors[i].Distance);
                                esp.DrawText1(Color(255, 165, 0), extra,
                                              Vect2(x, top - displayHeight / 130),
                                              textsize);
                            } else {
                                sprintf(extra, "%0.0fm", mResponse.Actors[i].Distance);
                                esp.DrawText(Color(255, 255, 255), extra,
                                             Vect2(x, top - displayHeight / 130),
                                             textsize);
                            }
                        }
                        ////=================Actor weapons================////
                        if (actorsWeapons) {
                            if (!mResponse.Actors[i].isAI) {
                                if (mResponse.Actors[i].Weapon.playerWeapon)
                                    esp.DrawWeapon(Color(255, 165, 0), mResponse.Actors[i].Weapon.id,
                                                   Vect2(x, bottom + displayHeight / 60), textsize);
                            } else {
                                if (mResponse.Actors[i].Weapon.playerWeapon)
                                    esp.DrawWeapon(Color(255, 255, 255), mResponse.Actors[i].Weapon.id,
                                                   Vect2(x, bottom + displayHeight / 60), textsize);
                            }
                        }

                        ////=================Actors weapon Ammos================////
                        if (actorsWeaponAmmo) {
                            if (!mResponse.Actors[i].isAI) {
                                if (actorsWeapons) {
                                    float xxx = x;
                                    float a = 76;
                                    if (actorsWeapons) {
                                        xxx = x - actorsWeaponAmmo + a;
                                    }
                                    esp.DrawWeaponAmmo(Color(255, 165, 0),
                                                       mResponse.Actors[i].Weapon.id,
                                                       mResponse.Actors[i].Weapon.ammo,
                                                       Vect2(xxx, bottom + displayHeight / 60), textsize);
                                } else {
                                    esp.DrawWeaponAmmo(Color(255, 165, 0),
                                                       mResponse.Actors[i].Weapon.id,
                                                       mResponse.Actors[i].Weapon.ammo,
                                                       Vect2(x, bottom + displayHeight / 60), textsize);
                                }
                            } else {
                                if (actorsWeapons) {
                                    float xxx = x;
                                    float a = 76;
                                    if (actorsWeapons) {
                                        xxx = x - actorsWeaponAmmo + a;
                                    }
                                    esp.DrawWeaponAmmo(Color::White(),
                                                       mResponse.Actors[i].Weapon.id,
                                                       mResponse.Actors[i].Weapon.ammo,
                                                       Vect2(xxx, bottom + displayHeight / 60), textsize);
                                } else {
                                    esp.DrawWeaponAmmo(Color::White(),
                                                       mResponse.Actors[i].Weapon.id,
                                                       mResponse.Actors[i].Weapon.ammo,
                                                       Vect2(x, bottom + displayHeight / 60), textsize);
                                }
                            }
                        }


                    }
                }
                ////=================EDGE warnings================////
                Vect2 location(mResponse.Actors[i].HeadPosition.x, mResponse.Actors[i].HeadPosition.y);
                if (worldEdgeWarnings && isOutsideSafeZone(location, screen)) {
                    Vect2 hintDotRenderPos = pushToScreenBorder(location, screen,
                                                                (int) ((mScale * 100) / 3));
                    Vect2 hintTextRenderPos = pushToScreenBorder(location, screen,
                                                                 -(int) ((mScale * 36)));
                    esp.DrawFilledCircle(colorAlert, hintDotRenderPos, (mScale * 100));
                    sprintf(extra, "%0.0fm", mResponse.Actors[i].Distance);
                    esp.DrawText1(Color(255, 255, 255, 255), extra, hintTextRenderPos, textsize);
                }
                if (mResponse.Actors[i].HeadPosition.z == 1.0f) {
                    if (y > displayHeight - displayHeight / 12)
                        y = displayHeight - displayHeight / 12;
                    else if (y < displayHeight / 12)
                        y = displayHeight / 12;
                } else if (x < -displayWidth / 10 || x > displayWidth + displayWidth / 10) {
                    if (y > displayHeight - displayHeight / 12)
                        y = displayHeight - displayHeight / 12;
                    else if (y < displayHeight / 12)
                        y = displayHeight / 12;
                    ////=================Lines================////
                } else if (actorLine) {
                    if (actorsHealth) {
                        esp.DrawLine(color, displayHeight / 400,
                                     Vect2(displayWidth / 2, displayHeight / 10.5),
                                     Vect2(x, top - displayHeight / 13));
                    } else {
                        esp.DrawLine(color, displayHeight / 400,
                                     Vect2(displayWidth / 2, displayHeight / 10.5), Vect2(x, top));
                    }
                }//Line
            }
            ////=================Vehicles================////
            for(int i = 0; i < mResponse.VehicleCount; i++)
            {
                if(mResponse.Vehicles[i].Location.z!=1.0f)
                {
                    esp.DrawVehicles(
                            mResponse.Vehicles[i].VehicleName,
                            mResponse.Vehicles[i].Distance,
                            Vect2(mResponse.Vehicles[i].Location.x,
                                  mResponse.Vehicles[i].Location.y - 8),
                            itemSize
                    );
                }
            }

            ////=================GRENADE Alert================////
            for (int i = 0; i < mResponse.GrenadeCount; i++){
                if(!worldThrowableWarning)
                    continue;
                sprintf(extra, "Warning! (%d) Throwables", mResponse.GrenadeCount);
                esp.DrawText(Color(255, 0, 0), extra,
                             Vect2(displayWidth/2,displayHeight/8),textsize);
                if(mResponse.Grenade[i].Location.z!=1.0f){
                    if(mResponse.Grenade[i].type==1 ) {
                        sprintf(extra, "Grenade(%0.0f)", mResponse.Grenade[i].Distance);
                        esp.DrawText(FRAGColor(),extra,
                                     Vect2(mResponse.Grenade[i].Location.x,mResponse.Grenade[i].Location.y - 25),
                                     textsize);
                        esp.DrawCircle(Color::Red(), Vect2(mResponse.Grenade[i].Location.x, mResponse.Grenade[i].Location.y), displayHeight / 92);

                        Vect2 location(mResponse.Grenade[i].Location.x, mResponse.Grenade[i].Location.y);
                        if (worldThrowableWarning && isOutsideSafeZone(location, screen)) {
                            Vect2 hintDotRenderPos = pushToScreenBorder(location, screen,
                                                                        (int) ((mScale * 100) / 3));
                            Vect2 hintTextRenderPos = pushToScreenBorder(location, screen,
                                                                         -(int) ((mScale * 25)));
                            esp.DrawFilledCircle(FRAGColor(), hintDotRenderPos, (mScale * 115));
                            sprintf(extra, "Grenade(%0.0f)", mResponse.Grenade[i].Distance);
                            esp.DrawText1(Color(255, 255, 255, 255), extra, hintTextRenderPos, textsize);
                        }
                    }
                    if (mResponse.Grenade[i].type == 2) {
                        sprintf(extra, "Molotov(%0.0f)", mResponse.Grenade[i].Distance);
                        esp.DrawText(FRAGColor(),extra,
                                     Vect2(mResponse.Grenade[i].Location.x,mResponse.Grenade[i].Location.y - 25),
                                     textsize);
                        esp.DrawCircle(FRAGColor(), Vect2(mResponse.Grenade[i].Location.x, mResponse.Grenade[i].Location.y), displayHeight / 92);
                        Vect2 location(mResponse.Grenade[i].Location.x, mResponse.Grenade[i].Location.y);
                        if (worldThrowableWarning && isOutsideSafeZone(location, screen)) {
                            Vect2 hintDotRenderPos = pushToScreenBorder(location, screen,
                                                                        (int) ((mScale * 100) / 3));
                            Vect2 hintTextRenderPos = pushToScreenBorder(location, screen,
                                                                         -(int) ((mScale * 25)));
                            esp.DrawFilledCircle(FRAGColor(), hintDotRenderPos, (mScale * 115));
                            sprintf(extra, "Molotov(%0.0f)", mResponse.Grenade[i].Distance);
                            esp.DrawText1(Color(255, 255, 255, 255), extra, hintTextRenderPos, textsize);
                        }
                    }
                    if (mResponse.Grenade[i].type == 3) {
                        sprintf(extra, "Stun(%0.0f)", mResponse.Grenade[i].Distance);
                        esp.DrawText(FRAGColor(),extra,
                                     Vect2(mResponse.Grenade[i].Location.x,mResponse.Grenade[i].Location.y - 25),
                                     textsize);
                        esp.DrawCircle(FRAGColor(), Vect2(mResponse.Grenade[i].Location.x, mResponse.Grenade[i].Location.y), displayHeight / 92);
                        Vect2 location(mResponse.Grenade[i].Location.x, mResponse.Grenade[i].Location.y);
                        if (worldThrowableWarning && isOutsideSafeZone(location, screen)) {
                            Vect2 hintDotRenderPos = pushToScreenBorder(location, screen,
                                                                        (int) ((mScale * 100) / 3));
                            Vect2 hintTextRenderPos = pushToScreenBorder(location, screen,
                                                                         -(int) ((mScale * 25)));
                            esp.DrawFilledCircle(FRAGColor(), hintDotRenderPos, (mScale * 115));
                            sprintf(extra, "Stun(%0.0f)", mResponse.Grenade[i].Distance);
                            esp.DrawText1(Color(255, 255, 255, 255), extra, hintTextRenderPos, textsize);
                        }
                    }
                    if (mResponse.Grenade[i].type == 4) {
                        sprintf(extra, "Smoke(%0.0f)", mResponse.Grenade[i].Distance);
                        esp.DrawText(FRAGColor(),extra,
                                     Vect2(mResponse.Grenade[i].Location.x,mResponse.Grenade[i].Location.y - 25),
                                     textsize);
                        esp.DrawCircle(FRAGColor(), Vect2(mResponse.Grenade[i].Location.x, mResponse.Grenade[i].Location.y), displayHeight / 92);
                        Vect2 location(mResponse.Grenade[i].Location.x, mResponse.Grenade[i].Location.y);
                        if (worldThrowableWarning && isOutsideSafeZone(location, screen)) {
                            Vect2 hintDotRenderPos = pushToScreenBorder(location, screen,
                                                                        (int) ((mScale * 100) / 3));
                            Vect2 hintTextRenderPos = pushToScreenBorder(location, screen,
                                                                         -(int) ((mScale * 25)));
                            esp.DrawFilledCircle(FRAGColor(), hintDotRenderPos, (mScale * 115));
                            sprintf(extra, "Smoke(%0.0f)", mResponse.Grenade[i].Distance);
                            esp.DrawText1(Color(255, 255, 255, 255), extra, hintTextRenderPos, textsize);
                        }
                    }
                }
            }//Grenade

            ////=================Items================////
            for(int i = 0; i < mResponse.ItemCount; i++){
                if(mResponse.Items[i].Location.z!=1.0f) {
                    esp.DrawItems(mResponse.Items[i].ItemName,mResponse.Items[i].Distance,
                                  Vect2(mResponse.Items[i].Location.x,mResponse.Items[i].Location.y),itemSize);

                }
            }
        } //isNotHideOverlay
    }//onResponse
    //DrawActors/EnemyCount
    if (!hideOverlay) {
        if (actorsCounter) {
            int colors;
            if (count != 0) {
                colors = 2;
                sprintf(extra, "%d", count);
            } else {
                colors = 1;
                strcpy(extra, OBFUSCATE("CLEAR"));
            }
            esp.DrawActorsCount(colors, Vect2(esp.getWidth() / 2, 15));
            esp.EnemyCount(Color().Black(), extra, Vect2(esp.getWidth() / 2, 100), 24);
        }//DrawActors/EnemyCount
    }


    if (!hideOverlay) {
        m_fps.Update();
        char *tsr = OBFUSCATE("FPS: ");
        std::string Str = std::string(tsr);
        Str += std::to_string((int) m_fps.get());
        esp.DrawText(Color::Red(), Str.c_str(), Vect2(esp.getWidth() / 10.3, 47),
                     displayHeight / 37);
        esp.DrawText(Color::Red(), OBFUSCATE("[SkullShooter™]"), Vect2(esp.getWidth() / 5.2, 160), displayHeight / 30);
        esp.DrawTextName(Color(255, 0, 0), OBFUSCATE(" "),
                         Vect2(esp.getWidth() / 20, 45), displayHeight / 37);
    }
}
#endif //PLUGIN_CANVASDRAWINGS_H
