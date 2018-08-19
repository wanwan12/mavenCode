package com.wanwan.mavencode;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

/**
 * Created by changzhengwan on 2018/8/16.
 */

public class MyAccessibility extends AccessibilityService {

    private static final String TAG = "MyAccessibility";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent: " + event.toString());
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
                (event.getPackageName().equals("com.android.packageinstaller")||
                event.getPackageName().equals("com.miui.packageinstaller"))) {
            Toast.makeText(getApplicationContext(),"程序安装",Toast.LENGTH_SHORT).show();
            Log.e(TAG,"soft installer");
        }
    }

    @Override
    public void onInterrupt() {

    }
}
