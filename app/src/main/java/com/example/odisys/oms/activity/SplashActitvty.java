package com.example.odisys.oms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.odisys.oms.R;

public class SplashActitvty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_splash_actitvty);
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
    protected void onPause() {
        super.onPause();
        finish();
    }
}
