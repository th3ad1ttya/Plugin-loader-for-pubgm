//
//Created by: Fr2nk3nst31n on 17/09/2022
//
namespace Offsets64 {
	//Vietnam
	uintptr_t GWorldVN = 0xA9715C0;
	uintptr_t GNamesVN = 0xA531AA0;
    uintptr_t ViewMatrixVN = 0xA950430;
	//Global and others
    uintptr_t GWorld = 0x7DEC600;
	uintptr_t GNames = 0x7ADB1B0;
    uintptr_t ViewMatrix = 0x7DD56C8;
	
    uintptr_t ViewMatrixLevel = 0x510;
    uintptr_t Position = 0x150; // int[] ParachuteEquipItems 
    uintptr_t RootComponent = 0x164; // SceneComponent* RootComponent
    uintptr_t SkeletalMeshComponent = 0x340; // SkeletalMeshComponent* Mesh
    uintptr_t FixAttachInfoList = 0x140; // FixAttachInfoList
    uintptr_t MinLOD = 0x5e8; // int MinLOD?
    uintptr_t Health = 0x9b8; // float Health
    uintptr_t bDead = 0x9d0; // bool bDead
    //Class: UAECharacter.Character.Pawn.Actor.Object
    uintptr_t PlayerName = 0x670; // FString PlayerName
    uintptr_t TeamID = 0x6a4; // int TeamID
    uintptr_t Role = 0x100;//byte Role
    uintptr_t bIsAI = 0x71c; // bool bIsAI
    uintptr_t ObserverCameraFPPMode = 0x3314; //ObserverCameraComponent* ObserverCameraFPPMode
    uintptr_t ObserverLevel = 0xfc;
    // Class: STExtraWeapon.Actor.Object
    uintptr_t ActorChildren = 0x158; // Actor*[] Children
    uintptr_t WeaponEntityComp = 0x5fc; //WeaponEntity* WeaponEntityComp
    ///Class: WeaponEntity.WeaponLogicBaseComponent.ActorComponent.Object 
    uintptr_t DrawShootLineTime = 0xec; // DrawShootLineTime
    uintptr_t UploadInterval = 0x100; // UploadInterval
    uintptr_t CurBulletNumInClip = 0xb00; // int CurBulletNumInClip
	
    uintptr_t bIsGunADS = 0xb85;// bool bIsGunADS
    uintptr_t bIsWeaponFiring = 0xfb0; //bool bIsWeaponFiring
    uintptr_t Controller = 0x320; //Class: Pawn.Actor.Object to Controller* Controller
    uintptr_t ControlRotation = 0x31c;//Class: Controller.Actor.Object to Rotator ControlRotation
	
	uintptr_t cLoc = 0x6D0;
	uintptr_t fovPntr = 0x5e0;
	uintptr_t vMatrix = 0x510;
	//uintptr_t oType = 0x60;
	
	uintptr_t WeaponManagerComponent = 0x17b0; //CharacterWeaponManagerComponent* WeaponManagerComponent
	uintptr_t CurrentWeaponReplicated = 0x46c; //STExtraWeapon* CurrentWeaponReplicated
	uintptr_t ShootWeaponEntityComp = 0xc10;  //ShootWeaponEntity* ShootWeaponEntityComp
	
	//Class: ShootWeaponEntity.WeaponEntity.WeaponLogicBaseComponent.ActorComponent.Object
	uintptr_t BulletFireSpeed = 0x4d0;
	
	uintptr_t AccessoriesVRecoilFactor = 0xa18;
	uintptr_t AccessoriesHRecoilFactor = 0xa1c;
	uintptr_t AccessoriesRecoveryFactor = 0xa20;
	uintptr_t RecoilKickADS = 0xb28 ;
	uintptr_t ExtraHitPerformScale = 0xb2c;
    
	uintptr_t ShootInterval = 0x4f8;
	
	uintptr_t GameDeviationFactor = 0xa74;
	
    uintptr_t AnimationKick = 0xb40;
    
	uintptr_t SwitchWeaponSpeedScale = 0x23d8; //float SwitchWeaponSpeedScale 
}