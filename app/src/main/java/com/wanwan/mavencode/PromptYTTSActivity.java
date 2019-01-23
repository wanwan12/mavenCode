package com.wanwan.mavencode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.AudioRecordingConfiguration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.ecarx.sdk.openapi.ECarXApiClient;
import com.ecarx.sdk.vrcontrol.common.IResponse;
import com.ecarx.sdk.vrcontrol.tip.TipInfo;
import com.ecarx.sdk.vrcontrol.tip.TipInfoListener;
import com.ecarx.sdk.vrcontrol.tip.VrTipCtrlAPI;

import java.util.List;

public class PromptYTTSActivity extends AppCompatActivity {

    private VrTipCtrlAPI tipCtrlAPI;
    private Context mContext;
    private boolean tipApiConnect = false;
    private MicCallBack mMicCallBack;
    private Handler mHandler = new Handler();

    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_test);
        mContext = this;
        demoTip();
        findViewById(R.id.btn_prompt_ful).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("demo_reminder");
                intent.putExtra("reminder_type", 1);
                intent.putExtra("tts_text", "TTS提示");
                sendBroadcast(intent);
            }
        });
        findViewById(R.id.btn_prompt_navi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipApiConnect) {
                    Resources res = getResources();
                    Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);
                    TipInfoImpl info = new TipInfoImpl();
                    info.setTipType(0);
                    info.setTipTitle("微信好友向你推送目的地");
                    info.setTipContent("收到位置");
                    info.setTipContent2("浙江省杭州市西湖区南山路15号");
                    info.setTipBitmap(bmp);
                    info.setTipPriority(TipInfo.TIP_PRIORITY_HIGH);
                    info.setTipTTS("测试读一段话");
                    tipCtrlAPI.createTipInfoCtrl(info, new TipInfoListener() {
                        @Override
                        public void onTipResult(int result, IResponse response) {
                            Log.d("onTipResult", result + "");
//                            Toast.makeText(mContext,result+"",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        findViewById(R.id.btn_prompt_tts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("demo_reminder");
                intent.putExtra("reminder_type", 3);
                intent.putExtra("tts_text", "TTS提示");
                sendBroadcast(intent);
            }
        });
        findViewById(R.id.test_miccallback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAudioManager == null) {
                    mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                }
                if (mMicCallBack == null) {
                    mMicCallBack = new MicCallBack();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mAudioManager.registerAudioRecordingCallback(mMicCallBack, mHandler);
                    }
                }
            }
        });
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMicCallBack = new MicCallBack();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mAudioManager.registerAudioRecordingCallback(mMicCallBack, mHandler);
        }

        List<AudioRecordingConfiguration> configs = mAudioManager.getActiveRecordingConfigurations();
        printAudioRecordingConfiguration(configs);
    }

    private void printAudioRecordingConfiguration(List<AudioRecordingConfiguration> configs) {
        Log.d("===PromptYTTSActivity===>", "AudioRecordingConfiguration size:" + configs.size());
        for (AudioRecordingConfiguration arc : configs) {
            Log.d("===PromptYTTSActivity===>", new String("session:" + arc.getClientAudioSessionId()
                    + " -- source:" + arc.getClientAudioSource()
                    + " -- uid:" + arc.getClientAudioSessionId()
                    + " -- AudioDevice:" + arc.getAudioDevice().getProductName()
                    + " -- ClientFormat=" + arc.getClientFormat().toString()));
        }
    }

    /**
     * a
     * 测试Tip提示框
     *
     * @return
     */
    private boolean demoTip() {
        tipCtrlAPI = VrTipCtrlAPI.get(this);
        tipCtrlAPI.connect(new ECarXApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(Bundle bundle) {
                tipApiConnect = true;
                Toast.makeText(mContext, "连接成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnectionSuspended(int i) {
                tipApiConnect = false;
                Toast.makeText(mContext, "连接失败", Toast.LENGTH_SHORT).show();
            }
        });

        return true;
    }

    @Override
    protected void onResume() {
//        setImmersive();
        super.onResume();
    }


    private void setImmersive() {
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

    @SuppressLint("NewApi")
    class MicCallBack extends AudioManager.AudioRecordingCallback {
        @Override
        public void onRecordingConfigChanged(List<AudioRecordingConfiguration> configs) {
            super.onRecordingConfigChanged(configs);
            printAudioRecordingConfiguration(configs);
        }
    }
}
