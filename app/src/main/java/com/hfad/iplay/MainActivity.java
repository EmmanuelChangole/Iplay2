package com.hfad.iplay;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.ActivityManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.hfad.iplay.util.Permissions;


public class MainActivity extends AppCompatActivity
{
    private String title;
    private NavigationView mainNav;
    private DrawerLayout mainDrawer;
    private ActionBarDrawerToggle toggle;
    private String StringPermission[];
    private Permissions permission;
    private boolean isMusicPlayerInit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StringPermission= new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        permission=new Permissions(this);
        Permissions.setPERMISSIONS(StringPermission);




    }

    private void setWidgets()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        toggle=new ActionBarDrawerToggle(this,mainDrawer,R.string.open,R.string.close)
        {

            @Override
            public void onDrawerOpened(View drawerView)
            {
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };
        toggle.syncState();
        mainDrawer.setDrawerListener(toggle);


        mainNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                onOptionsItemSelected(item);
                return true;
            }
        });


        selectItem(R.id.nav_library);

    }

    private void intWidgets()
    {
        mainDrawer=findViewById(R.id.main_drawer);
        mainNav=findViewById(R.id.mainNav);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
       if(isMusicPlayerInit)
           if(mainDrawer.isDrawerOpen(mainNav))
            menu.findItem(R.id.share).setVisible(false);


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
      if(item.isCheckable())
          item.setChecked(false);

        if(toggle.onOptionsItemSelected(item))
            return true;
         selectItem(item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int id)
    {
        Fragment fragment;
        switch (id)
        {

            case R.id.nav_playlist:
                fragment=new PlaylistFragment();
                title="Playlist";
                break;
            case R.id.nav_folder:
                fragment=new FolderFragment();
                title="Folder";
                break;
            case R.id.nav_plyingQueue:
                fragment=new NowPlayingFragment();
                title="Playing Queue";
                break;
            case R.id.nav_nowPlaying:
                fragment=new NowPlayingFragment();
                title="NowPlaying";
                break;
            case R.id.nav_settings:
                fragment=new SettingsFragment();
                title="Settings";
                break;
            case R.id.nav_about:
                 setDialog();
                title="Playlist";
            default:
                fragment=new LibraryFragment(this);
                title="Library";

        }
        getSupportActionBar().setTitle(title);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container,fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        mainDrawer.closeDrawers();



    }

    private void setDialog()
    {
        final AlertDialog.Builder info=new AlertDialog.Builder(this);
        info.setTitle("Iplay");
        info.setMessage("Iplay version 1.0 create by Manu Changole");
        info.create();
        info.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
            }
        });
        info.show();



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       if(permission.isPermissionGranted())
        {
          onResume();
        }
        else
            {
                ((ActivityManager)(this.getSystemService(ACTIVITY_SERVICE))).clearApplicationUserData();
                recreate();
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !permission.isPermissionGranted()) {
            requestPermissions(Permissions.PERMISSIONS, Permissions.REQUEST_PERMISSIONS);
            return;
        }
        if(!isMusicPlayerInit)
        {
            intWidgets();
            setWidgets();
            isMusicPlayerInit=true;

        }

    }}

