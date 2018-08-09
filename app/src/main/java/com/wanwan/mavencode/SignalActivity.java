package com.wanwan.mavencode;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SignalActivity extends AppCompatActivity  {


    public static SignalActivity getmInstance() {
        return mInstance;
    }

    private static SignalActivity mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstance = this;
        initView();

    }


    private void initView() {
    }

    private void setCurPosition(int position) {
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInstance = null;
    }
}
