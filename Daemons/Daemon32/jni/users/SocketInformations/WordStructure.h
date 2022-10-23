//
// Created by Adittya on 6/18/2022.
//

#ifndef PLUGIN_WORDSTRUCTURE_H
#define PLUGIN_WORDSTRUCTURE_H

#define maxactorCount 100
#define maxvahicleCount 50
#define maxitemCount 400
#define maxgrenadeCount 10


class Color
{
public:
    float r;
    float g;
    float b;
    float a;

    Color()
    {
        this->r = 0;
        this->g = 0;
        this->b = 0;
        this->a = 0;
    } //Color()

    Color(float r, float g, float b, float a)
    {
        this->r = r;
        this->g = g;
        this->b = b;
        this->a = a;
    }//Color

    Color(float r, float g, float b)
    {
        this->r = r;
        this->g = g;
        this->b = b;
        this->a = 255;
    }//Color

    static Color White()
    {
        return Color(255, 255, 255);
    }//White

    static Color Red()
    {
        return Color(255, 0, 0);
    }//Red

    static Color Black()
    {
        return Color(0, 0, 0);
    }//Black

    static Color Green()
    {
        return Color(0, 255, 0);
    }//Green
};//Class Color

class Rect {
public:
    float x;
    float y;
    float width;
    float height;
    Rect() {
        this->x = 0;
        this->y = 0;
        this->width = 0;
        this->height = 0;
    }
    Rect(float x, float y, float width, float height) {
        this->x = x;
        this->y = y;
        this->width = width;
        this->height = height;
    }
    bool operator==(const Rect &src) const {
        return (src.x == this->x && src.y == this->y && src.height == this->height &&
                src.width == this->width);
    }
    bool operator!=(const Rect &src) const {
        return (src.x != this->x && src.y != this->y && src.height != this->height &&
                src.width != this->width);
    }
};

struct Vect3
{
    float x, y, z;
};

class Vect2
{
public:
    float x;
    float y;

    Vect2()
    {
        this->x = 0;
        this->y = 0;
    }

    Vect2(float x, float y)
    {
        this->x = x;
        this->y = y;
    }

    static Vect2 Zero()
    {
        return Vect2(0.0f, 0.0f);
    }

    bool operator!=(const Vect2 & src) const
    {
        return (src.x != x) || (src.y !=y);
    }

    Vect2 & operator+=(const Vect2 &v)
    {
        x += v.x;
        y += v.y;
        return *this;
    }

    Vect2 &operator-=(const Vect2 &v)
    {
        x -= v.x;
        y -= v.y;
        return *this;
    }
};


struct ActorBone
{
    bool isBone = false;
    Vect2 neck;
    Vect2 chest;
    Vect2 pelvis;
    Vect2 lSh;
    Vect2 rSh;
    Vect2 lElb;
    Vect2 rElb;
    Vect2 lWr;
    Vect2 rWr;
    Vect2 lTh;
    Vect2 rTh;
    Vect2 lKn;
    Vect2 rKn;
    Vect2 lAn;
    Vect2 rAn;
    Vect2 root;
};

struct AimStruct
{
    float x = 0;
    float y = 0;
    long int Ox;
    float ScreenDistance = 0;
    float WorldDistance = 0;
} Aim[100];//struct AimStruct

struct PlayerWeapon
{
    bool playerWeapon = false;
    int id;
    int ammo;
};

enum Mode
{
    InitMode = 1,
    ESPMode = 2,
    HackMode = 3,
    StopMode = 4,
};

//=====New Added=====//
struct memoryFunctions {
    bool LessRecoil;
};

struct Request
{
    int Mode;
    int DisplayWidth;
    int DisplayHeight;
    memoryFunctions memory;
};

struct SetValue
{
    int mode;
    int type;
};

struct VehicleInfo
{
    char VehicleName[50];
    float Distance;
    Vect3 Location;
};

struct ItemInfo
{
    char ItemName[50];
    float Distance;
    Vect3 Location;
};

struct GrenadeInfo
{
    int type;
    float Distance;
    Vect3 Location;
};

struct ActorData
{
    char ActorNameByte[100];
    int ActorTeamID;
    float ActorHealth;
    float Distance;
    bool isAI;
    Vect3 HeadPosition;
    PlayerWeapon Weapon;
    ActorBone Bone;
};

struct Response
{
    bool Success;
    int ActorCount;
    int VehicleCount;
    int ItemCount;
    int GrenadeCount;

    float fov;
    ActorData Actors[maxactorCount];
    VehicleInfo Vehicles[maxvahicleCount];
    ItemInfo Items[maxitemCount];
    GrenadeInfo Grenade[maxgrenadeCount];
};

#endif //PLUGIN_WORDSTRUCTURE_H
