package com.hfad.iplay.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;


public class Permissions {
    private Context context;
    public static String [] PERMISSIONS;
    public static final int REQUEST_PERMISSIONS=1234;


    public Permissions(Context context) {
        this.context = context;
    }

    public static void setPERMISSIONS(String[] stringPermission)
    {
        PERMISSIONS=stringPermission;

    }


    @SuppressLint("NewApi")
    public boolean isPermissionGranted()
    {
        for(int i=0;i<PERMISSIONS.length;i++)
        {
       if(context.checkSelfPermission(PERMISSIONS[i])!=PackageManager.PERMISSION_GRANTED)
       {

          return false;
       }


        }
        return true;
    }
}
