package com.wanwan.mavencode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecordingConfiguration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ecarx.sdk.vrcontrol.tip.VrTipCtrlAPI;

import java.util.List;

public class AudioManageActivity extends AppCompatActivity {

    private VrTipCtrlAPI tipCtrlAPI;
    private Context mContext;
    private boolean tipApiConnect = false;
    private MicCallBack mMicCallBack;
    private Handler mHandler = new Handler();

    private AudioManager mAudioManager;
    private TextView tvLog;
    private StringBuilder logBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manage_test);
        mContext = this;
        tvLog = findViewById(R.id.tv_log);
        logBuilder = new StringBuilder("log:\n");
        tvLog.setText(logBuilder.toString());

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMicCallBack = new MicCallBack();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mAudioManager.registerAudioRecordingCallback(mMicCallBack, mHandler);
        }

        List<AudioRecordingConfiguration> configs = mAudioManager.getActiveRecordingConfigurations();
        printAudioRecordingConfiguration(configs);
    }

    private void printAudioRecordingConfiguration(List<AudioRecordingConfiguration> configs) {
        logBuilder.append("AudioRecordingConfiguration size:" + configs.size() + "\n");
        Log.d("===PromptYTTSActivity===>", "AudioRecordingConfiguration size:" + configs.size());
        for (AudioRecordingConfiguration arc : configs) {
            logBuilder.append("session:" + arc.getClientAudioSessionId()
                    + " -- source:" + arc.getClientAudioSource()
                    + " -- uid:" + arc.getClientAudioSessionId()
                    + " -- AudioDevice:" + arc.getAudioDevice().getType()
                    + " -- ClientFormat:" + arc.getFormat().toString() + "\n");
        }
        tvLog.setText(logBuilder.toString());
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
