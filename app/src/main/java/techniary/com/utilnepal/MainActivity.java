package techniary.com.utilnepal;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private CardView trending;
    private CardView posts;
    private CardView flash;
    private CardView sponsored;
    private CardView explore;
    private CardView recharge;

    private ImageView flashLightImageView;
    private Boolean is_flash_on;
    private Boolean hasFlash;
    private android.hardware.Camera camera;
    android.hardware.Camera.Parameters p;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        trending = findViewById(R.id.cardViewTrending);
        posts = findViewById(R.id.cardViewPosts);
        flash = findViewById(R.id.cardViewFlash);
        sponsored = findViewById(R.id.cardViewSponsored);
        explore = findViewById(R.id.cardViewPlaces);
        recharge = findViewById(R.id.cardViewRecharge);
        flashLightImageView = findViewById(R.id.imageViewFlash);

        is_flash_on = false;
        hasFlash= getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

    trending.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });

    posts.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });

    flash.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            flash_func();
        }
    });

    sponsored.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });


    recharge.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RechargeAdapter rechargeAdapter = new RechargeAdapter(getApplicationContext());
            rechargeAdapter.createCameraSource(true,false);
        }
    });

    }

    private void flash_func() {

        if (hasFlash) {
            if (is_flash_on)
                flashLightOff();
            else
                flashLightOn();
        } else {
            Toast.makeText(MainActivity.this, "No flash available on your device",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void flashLightOff() {
        try {
            flashLightImageView.setImageResource(R.drawable.flash_light);
            camera.stopPreview();
            camera.release();
            is_flash_on = false;
        }
        catch (Exception e)
        {
            Toast.makeText(this," Unknown Error Occured",Toast.LENGTH_SHORT).show();
        }

    }

    private void flashLightOn()
    {

        try
        {
            flashLightImageView.setImageResource(R.drawable.flash_green);
            camera = android.hardware.Camera.open();
            p = camera.getParameters();
            p.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);
            camera.startPreview();
            is_flash_on=true;
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Unknown Error Occured",Toast.LENGTH_SHORT).show();
        }
    }





    @Override
    protected void onStop() {
        super.onStop();
        if(is_flash_on)
        {
            flashLightOff();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(is_flash_on)
        {
            flashLightOff();
        }
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(is_flash_on)
        {
            flashLightOn();
        }
    }
}



