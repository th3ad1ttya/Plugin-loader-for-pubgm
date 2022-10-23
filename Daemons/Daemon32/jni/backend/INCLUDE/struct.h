#include "support.h"
#include "init.h"
#include <string>
#define maxactorCount 100
#define maxvahicleCount 50
#define maxitemCount 400
#define maxgrenadeCount 10
using namespace std;


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

struct Request
{
    int Mode;
    int DisplayWidth;
    int DisplayHeight;
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
