//
//Created by: Fr2nk3nst31n on 17/09/2022
//
namespace Offsets64 {
	//Vietnam
	uintptr_t GWorldVN = 0xAE87740;
	uintptr_t GNamesVN = 0xAA272D0;
    uintptr_t ViewMatrixVN = 0xAE65000;
	//Global and others
    uintptr_t GWorld = 0xAE74AC0;
	uintptr_t GNames = 0xAA14650;
    uintptr_t ViewMatrix = 0xAE52380;
	
    uintptr_t ViewMatrixLevel = 0xc0;
    uintptr_t Position = 0x1b0; // int[] ParachuteEquipItems 
    uintptr_t RootComponent = 0x1c0; // SceneComponent* RootComponent -- updated
    uintptr_t SkeletalMeshComponent = 0x438; // SkeletalMeshComponent* Mesh
    uintptr_t FixAttachInfoList = 0x1a0; // FixAttachInfoList
    uintptr_t MinLOD = 0x748; // int MinLOD?
    uintptr_t Health = 0xce0; // float Health -- updated
    uintptr_t bDead = 0xcfc; // bool bDead
    //Class: UAECharacter.Character.Pawn.Actor.Object
    uintptr_t PlayerName = 0x890; // FString PlayerName
    uintptr_t TeamID = 0x8d8; // int TeamID
    uintptr_t Role = 0x148;//byte Role
    uintptr_t bIsAI = 0x968; // bool bIsAI
    uintptr_t ObserverCameraFPPMode = 0x3f78; //ObserverCameraComponent* ObserverCameraFPPMode
    uintptr_t ObserverLevel = 0x1b0;
    // Class: STExtraWeapon.Actor.Object
    uintptr_t ActorChildren = 0x1b0; // Actor*[] Children
    uintptr_t WeaponEntityComp = 0xf48; //WeaponEntity* WeaponEntityComp 
    ///Class: WeaponEntity.WeaponLogicBaseComponent.ActorComponent.Object 
    uintptr_t DrawShootLineTime = 0x134; // DrawShootLineTime
    uintptr_t UploadInterval = 0x170; // UploadInterval
    uintptr_t CurBulletNumInClip = 0xe00; // int CurBulletNumInClip
    uintptr_t bIsGunADS = 0xf69;// bool bIsGunADS
    uintptr_t bIsWeaponFiring = 0x1500; //bool bIsWeaponFiring
    uintptr_t Controller = 0x410; //Class: Pawn.Actor.Object to Controller* Controller
    uintptr_t ControlRotation = 0x408;//Class: Controller.Actor.Object to Rotator ControlRotation
	
	uintptr_t cLoc = 0x750;
	uintptr_t fovPntr = 0x660;
	uintptr_t vMatrix = 0x590;
	//uintptr_t oType = 0x60;
	
	uintptr_t WeaponManagerComponent = 0x1ec8; //CharacterWeaponManagerComponent* WeaponManagerComponent
	uintptr_t CurrentWeaponReplicated = 0x580; //STExtraWeapon* CurrentWeaponReplicated
	uintptr_t ShootWeaponEntityComp = 0xf48;  //ShootWeaponEntity* ShootWeaponEntityComp
	
	//Class: ShootWeaponEntity.WeaponEntity.WeaponLogicBaseComponent.ActorComponent.Object
	uintptr_t BulletFireSpeed = 0x4d0;
	
	uintptr_t AccessoriesVRecoilFactor = 0xa20;
	uintptr_t AccessoriesHRecoilFactor = 0xa24;
	uintptr_t AccessoriesRecoveryFactor = 0xa28;
	uintptr_t RecoilKickADS = 0xb30 ;
	uintptr_t ExtraHitPerformScale = 0xb2c;
    
	uintptr_t ShootInterval = 0x4f8;
	
	uintptr_t GameDeviationFactor = 0xa7c;
	
    uintptr_t AnimationKick = 0xb4c;
    
	uintptr_t SwitchWeaponSpeedScale = 0x2418; //float SwitchWeaponSpeedScale 
}