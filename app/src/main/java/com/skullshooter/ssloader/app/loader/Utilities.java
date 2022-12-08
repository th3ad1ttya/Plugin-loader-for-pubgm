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
 | LastModified: Sep 10, 2022                               |
 |                                                          |
 \**********************************************************/
package com.skullshooter.ssloader.app.loader;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.io.File;

/**
 * It deletes a file or directory recursively and triggers a rebirth of the app
 */
public class Utilities {
    /**
     * If the file is a directory, delete all the files in it, then delete the directory
     *
     * @param fileOrDirectory The file or directory to delete.
     */
    public static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory == null)
            return;

        if (fileOrDirectory.isDirectory()){
            if(fileOrDirectory.listFiles() == null)
                return;

            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);
        }

        fileOrDirectory.delete();
    }

    /**
     * > It gets the launch intent for the current application, gets the component name from the
     * intent, creates a new intent with the component name, and starts the activity
     *
     * @param context The context of the application.
     */
    public static void triggerRebirth(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }
}
