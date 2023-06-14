package com.camera_platform_channel.camera_platform_channel;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.os.Bundle;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugins.GeneratedPluginRegistrant;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;

import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;


public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "platform_channel";

    private static final String CHANNEL_ID = "my.channel.id";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(new FlutterEngine(this));

//        setContentView(R.layout.activity_main);
//        findViewById(R.id.btn_start_camera).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //UsbFaceVerifyActivity.start4Login(MainActivity.this, "1280_720", "http://10.2.72.10:8080/");
//                //CameraActivity.start(MainActivity.this, "http://10.2.72.10:8080/");
////                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
////                intent.putExtra("base_url", "http://10.2.72.10:8080/");
////                startActivityForResult(intent, CameraActivity.REQ_START_CAMERA);
//                //Intent intent = new Intent(MainActivity.this, UsbFaceVerifyActivity.class);
//                //intent.putExtra("doWhat", UsbFaceVerifyActivity.FOR_USER_INFO);
//                //intent.putExtra("size", "1280_720");
//                //intent.putExtra("base_url", "http://10.2.72.10:8080/");
//                //intent.putExtra("authorization", "lalalal");
//                //MainActivity.this.startActivityForResult(intent, UsbFaceVerifyActivity.REQ_START_USB_CAMERA);
//            }
//        });




        BinaryMessenger messenger = getFlutterEngine().getDartExecutor().getBinaryMessenger();

        MethodChannel battery_channel = new MethodChannel(messenger, CHANNEL);
        battery_channel.setMethodCallHandler((call, result) -> {
            if(call.method.equals("getBatteryLevel")){
                Map<String, String> arguments = call.arguments();
                assert arguments != null;
                String name = arguments.get("name");

                int batteryLevel = getBatteryLevel();
                result.success(name + " says: " + batteryLevel + "%");
            }else{
                result.notImplemented();
            }
        });

    }




    private int getBatteryLevel() {
        int batteryLevel = -1;
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP){
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        } else {
            Intent intent = new ContextWrapper(getApplicationContext()).
                    registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
                    intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }

        return batteryLevel;
    }
}