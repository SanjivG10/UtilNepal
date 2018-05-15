package techniary.com.utilnepal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.security.acl.Permission;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 5/14/18.
 */

public class RechargeAdapter {
    private Context context;
    private CameraSource cameraSource;

    public RechargeAdapter(Context c) {
        context = c;
    }

    public void createCameraSource(boolean autoFocus, boolean useFlash) {

        TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();

        // checking if it works for the device, we are also checking device storage

        if (!textRecognizer.isOperational()) {
            Toast.makeText(context, " Some issue occured ", Toast.LENGTH_LONG).show();
        }

        cameraSource = new CameraSource.Builder(context, textRecognizer)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1280, 1024)
                .setRequestedFps(150.f)
                .build();


    }


}
