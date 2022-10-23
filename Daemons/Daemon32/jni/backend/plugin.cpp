#include "INCLUDE/struct.h"
#include "Offsets.h"
#include "INCLUDE/Obfuscation.h"
uintptr_t getMatrix(uintptr_t base);
char* getBone(uintptr_t pBase, struct D3DMatrix viewMatrix);
uintptr_t getEntityAddr(uintptr_t base);
char* getWeaponId(uintptr_t base);
char* getNameByte(uintptr_t address);
PlayerWeapon getPlayerWeapon(uintptr_t base);
ActorBone getActorBone(uintptr_t pBase, struct D3DMatrix viewMatrix);
void p_write(uintptr_t address, void* buffer, int size) {



}
uintptr_t GWorld = 0, GNames = 0, ViewMatrix = 0;

int main()
{
    SetValue sv{};
    char sText[400];
    if (!isBeta)
    {
        if (!Create())
        {
            perror(OBFUSCATE("Creation Failed"));
            return 0;
        }
        if (!Connect())
        {
            perror(OBFUSCATE("Connection Failed"));
            return 0;
        }
        int no;


        receive((void*)&sv);
        no = sv.mode;


        if (no == 1) {
            GNames = Offsets64::GNames;
            GWorld = Offsets64::GWorld;
            ViewMatrix = Offsets64::ViewMatrix;
            strcpy(version, "com.tencent.ig");
        } else if (no == 2) {
            GNames = Offsets64::GNames;
            GWorld = Offsets64::GWorld;
            ViewMatrix = Offsets64::ViewMatrix;
            strcpy(version, "com.pubg.krmobile");
        } else if (no == 3) {
            GNames = Offsets64::GNamesVN;
            GWorld = Offsets64::GWorldVN;
            ViewMatrix = Offsets64::ViewMatrixVN;
            strcpy(version, "com.vng.pubgmobile");
        } else if (no == 4) {
            GNames = Offsets64::GNames;
            GWorld = Offsets64::GWorld;
            ViewMatrix = Offsets64::ViewMatrix;
            strcpy(version, "com.rekoo.pubgm");
        } else if (no == 5) {
            GNames = Offsets64::GNames;
            GWorld = Offsets64::GWorld;
            ViewMatrix = Offsets64::ViewMatrix;
            strcpy(version, "com.pubg.imobile");
        }
    }




    // betaend
	//RootComponent: 0x164
	//Position: 0x150

    pid = getPid(version);
    if (pid < 10)
    {
        printf("\nGame is not running");
        Close();
        return 0;
    }
    if (isBeta == 1)
        printf(OBFUSCATE("\n Game Pid: %d"), pid);

    isPremium = true;
    uintptr_t base = getBase();
    if (isBeta == 1)
        printf(OBFUSCATE("\n Base: %lX\n"), base);

    uintptr_t vMatrix = getMatrix(base);
    if (!vMatrix)
    {
        if (isBeta == 1)
            puts(OBFUSCATE("\nMatrix Not Found"));
        return 0;
    }
    if (isBeta == 1)
        printf(OBFUSCATE("\nvMatrix: %lX"), vMatrix);


    // looooooooooooooop
	uintptr_t enAddrPntr;
	float xy0, xy1;
	struct Vect3 xyz;
	struct Vect3 screen;
	struct Vect3 exyz;
	int isBack = 0, type = 69;
	int changed = 1;
	int myteamID = 101;
	uintptr_t cLoc = vMatrix + Offsets64::cLoc; //Done
	uintptr_t fovPntr = vMatrix + Offsets64::fovPntr;//Done
    vMatrix = vMatrix + Offsets64::vMatrix;//Done
	char loaded[0x4000], loadedpn[20];
	char name[100];
	uintptr_t gname_buff[30];
	uintptr_t gname = getA(getA(base + GNames) + 0x88);//Done
	pvm(gname, &gname_buff, sizeof(gname_buff));
	char cont[0x500];
	char boneData[1024];
	struct D3DMatrix vMat;
	char weaponData[100];	


    Request request{};
    Response response{};

    while (isBeta || (receive((void*)&request) > 0)) {
        if (!isBeta) {
            height = request.DisplayHeight;
            width = request.DisplayWidth;
        }

        response.Success = false;
        response.ActorCount = 0;
        response.VehicleCount = 0;
        response.ItemCount = 0;
        response.GrenadeCount = 0;
        pvm(cLoc, &xyz, sizeof(xyz));
        if ((xyz.Z == 88.441124f || xyz.X == 0 || xyz.Z == 5278.43f || xyz.Z == 88.440918f) && !isBeta)
        {
            changed = 1;
            send((void*)&response, sizeof(response));
            continue;
        }
        pvm(fovPntr, &response.fov, 4);


        pvm(vMatrix, &vMat, sizeof(vMat));
        if (isBeta)
            printf(OBFUSCATE("\nvMatChk: %0.1f | FOV: %0.2f | XYZ: %f %f %f"), vMat._43, response.fov, xyz.X, xyz.Y, xyz.Z);
        // end


        // enList
        if (changed == 1) {
            enAddrPntr = getEntityAddr(base);
            changed = 0;
        }
        Ulevel ulevel;
        pvm(enAddrPntr, &ulevel, sizeof(ulevel));
        if (ulevel.size < 1 || ulevel.size > 0x1000 || !isValid32(ulevel.addr)) {
            if (isBeta)
                puts(OBFUSCATE("\nWrong Entity Address"));
            changed = 1;
            if (!isBeta) {
                send((void*)&response, sizeof(response));
                continue;
            }
        }
        if (isBeta)
            printf(OBFUSCATE("\nEntity Address: %lX | Size: %d"), enAddrPntr, ulevel.size);

        memset(loaded, 0, 1000);
        for (int i = 0; i < ulevel.size; i++) {
            uintptr_t pBase = getA(ulevel.addr + i * SIZE);
            if (!isValid32(pBase))
                continue;
            if (getI(pBase + SIZE) != 8)
                continue;
            int ids = getI(pBase + 8 + 2 * SIZE);
            int page = ids / 0x4000;
            int index = ids % 0x4000;
            if (page < 1 || page>30)
                continue;
            if (gname_buff[page] == 0)
                gname_buff[page] = getA(gname + page * SIZE);
            strcpy(name, getText(getA(gname_buff[page] + index * SIZE)));
            if (strlen(name) < 5)
                continue;
            if (strstr(name, OBFUSCATE("BP_PlayerLobbyPawn"))) {

                break;
            }
            if (strstr(name, OBFUSCATE("BP_AirDropPlane_C"))) {

                break;
            }
            if (strstr(name, "BP_PlayerPawn")||strstr(name, "BP_PlayerCharacter")||strstr(name, "PlanET_FakePlayer")||strstr(name, "BP_PlayerCharacter_PlanX_C")||strstr(name, "PlanX_PlayerCharacter_AI_C")) {//Player
                sprintf(loadedpn, OBFUSCATE("%lx,"), pBase);
                if (strstr(loaded, loadedpn))
                    continue;
                strcat(loaded, loadedpn);

                /*int oType = getI(pBase + 0x60);
                if (oType != 0x1d85400 && oType != 0x1d85000) //Fixed skeleton glitch
                continue;*/

                 if (getI(pBase + Offsets64::bDead))
                    continue;

                pvm(pBase + Offsets64::Health, healthbuff, sizeof(healthbuff));
                if (healthbuff[1] > 200.0f || healthbuff[1] < 50.0f || healthbuff[0]>healthbuff[1] || healthbuff[0] < 0.0f)
                    continue;

                ActorData* data = &response.Actors[response.ActorCount];

                data->ActorHealth = healthbuff[0] / healthbuff[1] * 100;

                data->ActorTeamID = getI(pBase + Offsets64::TeamID);

                //me
                uintptr_t me = getI(pBase + Offsets64::Role);

                if (me == 258) {
                    if (isBeta)
                        printf(OBFUSCATE("\nMe(%d): %lX "), data->ActorTeamID, pBase);
                    myteamID = data->ActorTeamID;
                    continue;
                }
                else if (me != 257)
                    continue;


                if (data->ActorTeamID == myteamID && myteamID <= 100)
                    continue;

                pvm(getA(pBase + Offsets64::ObserverCameraFPPMode) + Offsets64::ObserverLevel, &exyz, sizeof(exyz));

                data->HeadPosition = World2Screen(vMat, exyz);

                data->Distance = getDistance(xyz, exyz);
                if (data->Distance > 600.0f)
                    continue;
                pvm(pBase + Offsets64::bIsAI, &data->isAI, sizeof(data->isAI));

                strcpy(data->ActorNameByte, "66:111:116:");
                if (data->HeadPosition.Z != 1.0f && data->HeadPosition.X < width + 100 && data->HeadPosition.X > -50) {
                    data->Bone = getActorBone(pBase, vMat);
                    if (isPremium)
                        strcpy(data->ActorNameByte, getNameByte(getA(pBase + Offsets64::PlayerName)));
                    if (strlen(data->ActorNameByte) < 4)
                        continue;
                    if (isPremium)
                        data->Weapon = getPlayerWeapon(pBase);
                }


                if (response.ActorCount >= maxactorCount) {
                    continue;
                }

                response.ActorCount++;

                if (isBeta)
                    printf(OBFUSCATE("\nE | %lX > TI:%d | H:%0.1f | XY: %0.1f %0.1f | %d"), pBase, data->ActorTeamID, data->ActorHealth, data->HeadPosition.X, data->HeadPosition.Y, data->isAI);

            }
            else if (strstr(name, "VH") || (strstr(name, "PickUp_") && !strstr(name, "BP")) || strstr(name, "Rony") || strstr(name, "Mirado") || strstr(name, "LadaNiva") || strstr(name, "AquaRail")) {//Vehicle
				if (!isPremium)
					continue;
				VehicleInfo* data = &response.Vehicles[response.VehicleCount];
				pvm(getA(pBase + Offsets64::RootComponent) + Offsets64::Position, &exyz, sizeof(exyz));

				data->Location = World2Screen(vMat, exyz);
				if (data->Location.Z == 1.0f || data->Location.X > width + 200 || data->Location.X < -200)
					continue;
				data->Distance = getDistance(xyz, exyz);
				strcpy(data->VehicleName, name);
				if (response.VehicleCount >= maxvahicleCount) {
					continue;
				}
				response.VehicleCount++;

				if (isBeta)
					printf("\nV | %lX > XY: %0.1f %0.1f | N: %s", pBase, data->Location.X, data->Location.Y, name);
			}
            else if (strstr(name, "Pickup_C") || strstr(name, "PickUp") || strstr(name, "BP_Ammo") || strstr(name, "BP_QK") || strstr(name, "Wrapper")) {//Items
				if (!isPremium)
					continue;
				ItemInfo* data = &response.Items[response.ItemCount];
				pvm(getA(pBase + Offsets64::RootComponent) + Offsets64::Position, &exyz, sizeof(exyz));
				data->Location = World2Screen(vMat, exyz);
				if (data->Location.Z == 1.0f || data->Location.X > width + 100 || data->Location.X < -50)
					continue;
				data->Distance = getDistance(xyz, exyz);
				if (data->Distance > 200.0f)
					continue;
				strcpy(data->ItemName, name);
				if (response.ItemCount >= maxitemCount) {
					continue;
				}
				response.ItemCount++;

				if (isBeta)
					printf("\nI | %lX > XY: %0.1f %0.1f | D:%0.1fm %s", pBase, data->Location.X, data->Location.Y, data->Distance, name);
			}
			else if (strstr(name, "BP_AirDropPlane_C") || strstr(name, "PlayerDeadInventoryBox_C") || strstr(name, "BP_AirDropBox_C")) {//Items
				if (!isPremium)
					continue;

				ItemInfo* data = &response.Items[response.ItemCount];
				pvm(getA(pBase + Offsets64::RootComponent) + Offsets64::Position, &exyz, sizeof(exyz));
				data->Location = World2Screen(vMat, exyz);
				if (data->Location.Z == 1.0f || data->Location.X > width + 100 || data->Location.X < -50)
					continue;
				data->Distance = getDistance(xyz, exyz);
				strcpy(data->ItemName, name);
				if (response.ItemCount >= maxitemCount) {
					continue;
				}
				response.ItemCount++;

				if (isBeta)
					printf("\nSp | %lX > XY: %0.1f %0.1f | D:%0.1fm %s", pBase, data->Location.X, data->Location.Y, data->Distance, name);
			}
            else if (strstr(name, "BP_Grenade_Shoulei_C") || strstr(name, "BP_Grenade_Burn_C") || strstr(name, "BP_Grenade_Stun_C") || strstr(name, "BP_Grenade_Smoke_C")) {//Grenade Warning
				if (!isPremium)
					continue;
				GrenadeInfo* data = &response.Grenade[response.GrenadeCount];
				pvm(getA(pBase + Offsets64::RootComponent) + Offsets64::Position, &exyz, sizeof(exyz));
				data->Location = World2Screen(vMat, exyz);

				data->Distance = getDistance(xyz, exyz);
				if (data->Distance > 150.0f)
					continue;
				if (strstr(name, "Shoulei"))
					data->type = 1;
				else
                if (strstr(name, "Burn"))     
					data->type = 2;									
			    else
		    	if (strstr(name, "Stun"))
		    	    data->type = 3;
		    	else
		        if (strstr(name, "Smoke"))
		    	    data->type = 4;
				if (response.GrenadeCount >= maxgrenadeCount) {
					continue;
				}
				response.GrenadeCount++;

				if (isBeta)
					printf("\nGW | %lX > XY: %0.1f %0.1f | D:%0.1fm %d", pBase, data->Location.X, data->Location.Y, data->Distance, name);
			}


        }

		if (response.ActorCount + response.ItemCount + response.VehicleCount + response.GrenadeCount + response.GrenadeCount > 0)
			response.Success = true;

		    if (isBeta) {
            printf("\nPlayers: %d | Vehicle: %d | Items: %d ", response.ActorCount, response.VehicleCount, response.ItemCount);
            break;
        }

        send((void*)&response, sizeof(response));

    }


    if (isBeta)
        puts(OBFUSCATE("\n\nScript Completed "));
}


uintptr_t getMatrix(uintptr_t base)
{
    return getA(getA(base + Offsets64::ViewMatrix) + 0x7c);//done
}


uintptr_t getEntityAddr(uintptr_t base)
{
    return getA(getA(getA(base + Offsets64::GWorld) + 0x3c) + 0x20) + 0x70; //Done
}

char* getNameByte(uintptr_t address)
{
    char static lj[64];
    memset(lj, 0, 64);
    unsigned short int nameI[32];
    pvm(address, nameI, sizeof(nameI));
    char s[10];
    int i;
    for (i = 0; i < 32; i++)
    {
        if (nameI[i] == 0)
            break;
        sprintf(s, OBFUSCATE("%d:"), nameI[i]);
        strcat(lj, s);
    }
    lj[63] = '\0';

    return lj;
}


ActorBone getActorBone(uintptr_t pBase, struct D3DMatrix viewMatrix)
{
    ActorBone b;
    b.isBone = true;
    struct D3DMatrix oMatrix;
    uintptr_t boneAddr = getA(pBase + Offsets64::SkeletalMeshComponent);//Done
    struct D3DMatrix baseMatrix = getOMatrix(boneAddr + Offsets64::FixAttachInfoList);//Done
    int bones[] = {6, 5, 2, 12, 33, 13, 34, 64, 63, 53, 57, 54, 58, 55, 59};
    boneAddr = getA(boneAddr + Offsets64::MinLOD);//Done
    //neck 0
    oMatrix = getOMatrix(boneAddr + (bones[0] + 1) * 48);
    b.neck = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //cheast 1
    oMatrix = getOMatrix(boneAddr + (bones[1] + 1) * 48);
    b.chest = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //pelvis 2
    oMatrix = getOMatrix(boneAddr + (bones[2] + 1) * 48);
    b.pelvis = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lSh 3
    oMatrix = getOMatrix(boneAddr + (bones[3] + 1) * 48);
    b.lSh = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rSh 4
    oMatrix = getOMatrix(boneAddr + (bones[4] + 1) * 48);
    b.rSh = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lElb 5
    oMatrix = getOMatrix(boneAddr + (bones[5] + 1) * 48);
    b.lElb = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rElb 6
    oMatrix = getOMatrix(boneAddr + (bones[6] + 1) * 48);
    b.rElb = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lWr 7
    oMatrix = getOMatrix(boneAddr + (bones[7] + 1) * 48);
    b.lWr = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rWr 8
    oMatrix = getOMatrix(boneAddr + (bones[8] + 1) * 48);
    b.rWr = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lTh 9
    oMatrix = getOMatrix(boneAddr + (bones[9] + 1) * 48);
    b.lTh = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rTh 10
    oMatrix = getOMatrix(boneAddr + (bones[10] + 1) * 48);
    b.rTh = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lKn 11
    oMatrix = getOMatrix(boneAddr + (bones[11] + 1) * 48);
    b.lKn = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rKn 12
    oMatrix = getOMatrix(boneAddr + (bones[12] + 1) * 48);
    b.rKn = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //lAn 13 
    oMatrix = getOMatrix(boneAddr + (bones[13] + 1) * 48);
    b.lAn = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //rAn 14
    oMatrix = getOMatrix(boneAddr + (bones[14] + 1) * 48);
    b.rAn = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));
    //root
    oMatrix = getOMatrix(boneAddr + (0) * 48);
    b.root = World2ScreenMain(viewMatrix, mat2Cord(oMatrix, baseMatrix));

    return b;
}
PlayerWeapon getPlayerWeapon(uintptr_t base) {
    PlayerWeapon p;
    uintptr_t addr[3];
    pvm(getA(base + Offsets64::ActorChildren), addr, sizeof(addr));//Done //Actor*[] Children;//[Offset:

    if (isValid32(addr[0]) && getI(addr[0] + Offsets64::DrawShootLineTime) == 2) {
        p.playerWeapon = true;
        p.id = getI(getA(addr[0] + Offsets64::WeaponEntityComp) + Offsets64::UploadInterval); //ShootWeaponEntity* ShootWeaponEntityComp;//[Offset: 
        p.ammo = getI(addr[0] + Offsets64::CurBulletNumInClip); //int CurBulletNumInClip;//[Offset: 0x9d0
    } else if (isValid32(addr[1]) && getI(addr[1] + Offsets64::DrawShootLineTime) == 2) {
        p.playerWeapon = true;
        p.id = getI(getA(addr[1] + Offsets64::WeaponEntityComp) + Offsets64::UploadInterval); //STExtraPlayerCharacter* Pawn;//[Offset:
        p.ammo = getI(addr[1] + Offsets64::CurBulletNumInClip);
    } else if (isValid32(addr[2]) && getI(addr[2] + Offsets64::DrawShootLineTime) == 2) {
        p.playerWeapon = true;
        p.id = getI(getA(addr[2] + Offsets64::WeaponEntityComp) + Offsets64::UploadInterval);
        p.ammo = getI(addr[2] + Offsets64::CurBulletNumInClip);
    }


    return p;
}
