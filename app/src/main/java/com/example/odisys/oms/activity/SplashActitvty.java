package com.example.odisys.oms.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.odisys.oms.R;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActitvty extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private static final int CEK_PERMISSON = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_splash_actitvty);
        onChekPermissionTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onChekPermissionTask();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void onChekPermissionTask(){
        String[] mParamsPermission = {Manifest.permission.CAMERA};

        if (EasyPermissions.hasPermissions(this,mParamsPermission)){
            SplashApp();
        }else {
            EasyPermissions.requestPermissions(this,getString(R.string.returned_from_app_settings_to_activity),CEK_PERMISSON,mParamsPermission);
        }
    }

    private void SplashApp(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2000);
                }catch (InterruptedException e){
                    e.fillInStackTrace();
                }finally {
                    Intent i = new Intent(SplashActitvty.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        };
        thread.start();

        final Animation ianimRotate = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        ianimRotate.getStartOffset();
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.activity_splash_actitvty);
        relativeLayout.setAnimation(ianimRotate);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("SplasScreen", "onPermissionGranted : " + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("SplasScreen", "onPermissionGranted : " + requestCode + ":" + perms.size());

        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
}
