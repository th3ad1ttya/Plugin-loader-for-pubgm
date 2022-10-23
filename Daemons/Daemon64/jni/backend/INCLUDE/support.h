 #include "socket.h"


ssize_t process_v(pid_t __pid,   struct iovec* __local_iov, unsigned long __local_iov_count, struct iovec* __remote_iov, unsigned long __remote_iov_count, unsigned long __flags) {
    return syscall(process_vm_readv_syscall, __pid, __local_iov, __local_iov_count, __remote_iov, __remote_iov_count, __flags);
}
int pvm(uintptr_t address, void* buffer,int size) {
    struct iovec local[1];
    struct iovec remote[1];

    local[0].iov_base = (void*)buffer;
    local[0].iov_len = size;
    remote[0].iov_base = (void*)address;
    remote[0].iov_len = size;

ssize_t bytes = process_v(pid, local, 1, remote, 1, 0);
    return bytes == size;
}

struct D3DMatrix ToMatrixWithScale(struct Vect3 translation,struct Vect3 scale,struct Vec4 rot)
 {
struct D3DMatrix m;
 m._41 = translation.X;
 m._42 = translation.Y;
 m._43 = translation.Z;
            

float x2 = rot.X + rot.X;
float y2 = rot.Y + rot.Y;
float z2 = rot.Z + rot.Z;


float xx2 = rot.X * x2;
float yy2 = rot.Y * y2;
float zz2 = rot.Z * z2;

m._11 = (1.0f - (yy2 + zz2)) * scale.X;
m._22 = (1.0f - (xx2 + zz2)) * scale.Y;
m._33 = (1.0f - (xx2 + yy2)) * scale.Z;


float yz2 = rot.Y * z2;
float wx2 = rot.W * x2;

m._32 = (yz2 - wx2) * scale.Z;
m._23 = (yz2 + wx2) * scale.Y;

float xy2 = rot.X * y2;
float wz2 = rot.W * z2;

m._21 = (xy2 - wz2) * scale.Y;
m._12 = (xy2 + wz2) * scale.X;


float xz2 = rot.X * z2;
float wy2 = rot.W * y2;

m._31 = (xz2 + wy2) * scale.Z;
m._13 = (xz2 - wy2) * scale.X;


m._14 = 0.0f;
m._24 = 0.0f;
m._34 = 0.0f;
m._44 = 1.0f;

return m;
}
struct Vect3 mat2Cord(struct D3DMatrix pM1,struct D3DMatrix pM2){
struct  Vect3 pOut;

pOut.X = pM1._41 * pM2._11 + pM1._42 * pM2._21 + pM1._43 * pM2._31 + pM1._44 * pM2._41;
pOut.Y = pM1._41 * pM2._12 + pM1._42 * pM2._22 + pM1._43 * pM2._32 + pM1._44 * pM2._42;
pOut.Z = pM1._41 * pM2._13 + pM1._42 * pM2._23 + pM1._43 * pM2._33 + pM1._44 * pM2._43;
           
return pOut;
}
uintptr_t getBase(){
    FILE *fp;
    uintptr_t addr = 0;
    char filename[40], buffer[1024];
    snprintf(filename, sizeof(filename), "/proc/%d/maps", pid);
    fp = fopen(filename, "rt");
    if (fp != NULL) {
        while (fgets(buffer, sizeof(buffer), fp)) {
            if (strstr(buffer, "libUE4.so")) {
                addr = (uintptr_t)strtoull(buffer, NULL, 16);
                break;
            }
        }
        fclose(fp);
    }
    return addr;
}
pid_t getPid(char * name){
    char text[69];
    pid_t pid = 0;
    sprintf(text,"pidof %s",name);
FILE *chkRun = popen(text, "r");
    if (chkRun){
        char output[10];
        fgets(output ,10,chkRun);
        pclose(chkRun);
        pid= atoi(output);
        }
    if (pid < 10) {
        DIR* dir = NULL;
        struct dirent* ptr = NULL;
        FILE* fp = NULL;
        char filepath[256];
        char filetext[128];
        dir = opendir("/proc");
        if (NULL != dir)
        {
            while ((ptr = readdir(dir)) != NULL)
            {
                if ((strcmp(ptr->d_name, ".") == 0) || (strcmp(ptr->d_name, "..") == 0))
                    continue;
                if (ptr->d_type != DT_DIR)
                    continue;
                sprintf(filepath, "/proc/%s/cmdline", ptr->d_name);
                fp = fopen(filepath, "r");
                if (NULL != fp)
                {
                    fgets(filetext, sizeof(filetext), fp);


                    if (strcmp(filetext, name) == 0)
                    {
                        fclose(fp);
                        break;
                    }
                    fclose(fp);
                }
            }
        }
        if (readdir(dir) == NULL)
        {
            closedir(dir);
            return 0;
        }
        closedir(dir);
        pid= atoi(ptr->d_name);
    }
        return pid;
}
int isValidItem(int id) {
    if (id >= 100000 && id < 999999)
        return 1;
    return 0;
}
float getF(uintptr_t address) {
    float buff;
    pvm(address, &buff, 4);
    return buff;
}
uintptr_t getA(uintptr_t address) {
    uintptr_t buff;
    pvm(address, &buff, SIZE);
    return buff;
}
int getI(uintptr_t address) {
    int buff;
    pvm(address, &buff, 4);
    return buff;
}
int isValid64(uintptr_t addr) {
    if (addr < 0x1000000000 || addr>0xefffffffff || addr % SIZE != 0)
        return 0;
    return 1;
}
int isValid32(uintptr_t addr) {
    if (addr < 0x10000000 || addr>0xefffffff || addr % SIZE != 0)
        return 0;
    return 1;
}
float getDistance(struct Vect3 mxyz,struct Vect3 exyz){
return sqrt ((mxyz.X-exyz.X)*(mxyz.X-exyz.X)+(mxyz.Y-exyz.Y)*(mxyz.Y-exyz.Y)+(mxyz.Z-exyz.Z)*(mxyz.Z-exyz.Z))/100;
    
}

struct Vect3 World2Screen(struct D3DMatrix viewMatrix, struct Vect3 pos) {
    struct Vect3 screen;
    float screenW = (viewMatrix._14 * pos.X) + (viewMatrix._24 * pos.Y) + (viewMatrix._34 * pos.Z) + viewMatrix._44;

    if (screenW < 0.01f)
        screen.Z = 1;
    else
        screen.Z = 0;


    float screenX = (viewMatrix._11 * pos.X) + (viewMatrix._21 * pos.Y) + (viewMatrix._31 * pos.Z) + viewMatrix._41;
    float screenY = (viewMatrix._12 * pos.X) + (viewMatrix._22 * pos.Y) + (viewMatrix._32 * pos.Z) + viewMatrix._42;
    screen.Y = (height / 2) - (height / 2) * screenY / screenW;
    screen.X = (width / 2) + (width / 2) * screenX / screenW;


    return screen;

}
struct Vect2 World2ScreenMain(struct D3DMatrix viewMatrix, struct Vect3 pos) {
    struct Vect2 screen;
    float screenW = (viewMatrix._14 * pos.X) + (viewMatrix._24 * pos.Y) + (viewMatrix._34 * pos.Z) + viewMatrix._44;

    float screenX = (viewMatrix._11 * pos.X) + (viewMatrix._21 * pos.Y) + (viewMatrix._31 * pos.Z) + viewMatrix._41;
    float screenY = (viewMatrix._12 * pos.X) + (viewMatrix._22 * pos.Y) + (viewMatrix._32 * pos.Z) + viewMatrix._42;
    screen.Y = (height / 2) - (height / 2) * screenY / screenW;
    screen.X = (width / 2) + (width / 2) * screenX / screenW;
    return screen;

}
struct D3DMatrix getOMatrix(uintptr_t boneAddr){
    float oMat[11];
    pvm(boneAddr,&oMat,sizeof(oMat));
    rot.X=oMat[0];
    rot.Y=oMat[1];
    rot.Z=oMat[2];
    rot.W=oMat[3];
            
    tran.X=oMat[4];
    tran.Y=oMat[5];
    tran.Z=oMat[6];
            
    scale.X=oMat[8];
    scale.Y=oMat[9];
    scale.Z=oMat[10];
            
    return ToMatrixWithScale(tran,scale,rot);
}
char* getText(uintptr_t addr) {
    static char txt[42];
    memset(txt, 0, 42);
    char buff[41];
    pvm(addr + 4+SIZE, &buff, 41);
    int i;
    for (i = 0; i < 41; i++) {
        if (buff[i] == 0)
            break;

        txt[i] = buff[i];

        if (buff[i] == 67 && i > 10)
            break;

    }
    txt[i + 1] = '\0';
    return txt;
}
void dump(const uintptr_t gaddr, const int gsize, char* name) {
    char buff[0x100000];
    uintptr_t addr = gaddr;
    int size = gsize;
    FILE* fp = fopen(name, "w");
    while (size > 0) {
        if (size < 0x100000) {
            pvm(addr, buff, size);

            for (int i = 0; i < size; i++)
                fwrite(&buff[i], 1, 1, fp);

        }
        else {
            pvm(addr, buff, 0x100000);

            for (int i = 0; i < 0x100000; i++)
                fwrite(&buff[i], 1, 1, fp);
        }

        addr += 0x100000;
        size -= 0x100000;
    }
    fclose(fp);

}


