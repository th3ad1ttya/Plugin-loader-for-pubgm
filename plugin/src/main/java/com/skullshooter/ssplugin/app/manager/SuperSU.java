/**********************************************************\
 |                                                          |
 | Made by: Adittya Hasan (Frankenstein404)                 |
 |                                                          |
 | Android plugin loader for create online update based     |
 | loader                                                   |
 |                                                          |
 | Credits:                                                 |
 |      Frankenstein(Adittya)                               |
 |      Original X Ninja Cheats                             |
 |                                                          |
 | Code Authors: Adittya <theadittyaadi@icloud.com>         |
 | LastModified: Jun 10, 2021                               |
 |                                                          |
 \**********************************************************/
package com.skullshooter.ssplugin.app.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Objects;

public class SuperSU {
    public static boolean isRootAvailable(){
        for (String pathDir : Objects.requireNonNull(System.getenv("PATH")).split(":")){
            if (new File(pathDir, "su").exists()){
                return true;
            }
        }
        return false;
    }

    public static boolean isRootGiven(){
        if (isRootAvailable()){
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(new String[]{"su", "-c", "id"});
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = in.readLine();

                if (output != null && output.toLowerCase().contains("uid=0"))
                    return true;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (process !=null)
                    process.destroy();
            }
        }
        return false;
    }
}
