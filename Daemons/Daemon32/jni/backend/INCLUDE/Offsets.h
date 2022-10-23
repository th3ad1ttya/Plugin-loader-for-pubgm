#ifndef Offsets_f
#define Offsets_f

namespace Offsets {
    //Global, Korean, BGMI, Taiwan 2.1
    uintptr_t GNames = "0x7767450";
    uintptr_t GWorld = "0x7A6DE04";
    uintptr_t ViewMatrix = "0x7A56F18";

    //Vietnam 2.1
    uintptr_t GNames_VN = "0x777CFD0";
    uintptr_t GWorld_VN = "0x7A839C4";
    uintptr_t ViewMatrix_VN = "0x7A56F18";

    //Included with gname gworld viewmat
    uintptr_t PersistsLevelShadowTrackerExtra = "0x3c";
    uintptr_t PersistentLevel = "0x20"; //2.1
    uintptr_t ULevelToAActors = "0x70"; //2.1
    uintptr_t ViewMatrixLevel = "0x7C"; //2.1

    //ItemsData
    uintptr_t Positions = "0x150";
    uintptr_t RootComponents = "0x164";

    //PlayerData
    uintptr_t SkeletonBaseAddr = "0x344";
    uintptr_t SkeletonMatrix = "0x140";
    uintptr_t bonAddress = "0x5e8";
    uintptr_t Health = "0x9a4";
    uintptr_t ActorsName = "0x668";
    uintptr_t ActorsID = "0x69c";
    uintptr_t isAI = "0x714";
}

#endif //Offsets_h