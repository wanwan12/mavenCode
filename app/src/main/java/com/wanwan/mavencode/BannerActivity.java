package com.wanwan.mavencode;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ecarx.xkbanner.holder.HolderCreator;
import com.ecarx.xkbanner.view.XKBanner;

import java.util.Arrays;

public class BannerActivity extends AppCompatActivity {

    private Context context;

    private XKBanner turnBanner;

    private Button loopBtn;
    private Button scrollBtn;

    private String[] images = {
            "http://img2.3lian.com/2014/f4/25/d/85.jpg",
            "http://img2.3lian.com/2014/f4/25/d/82.jpg",
            "http://img2.3lian.com/2014/f4/25/d/88.jpg",
            "http://img2.3lian.com/2014/f4/25/d/90.jpg",
            "http://img2.3lian.com/2014/f4/25/d/89.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        context = getApplicationContext();

        turnBanner = (XKBanner) findViewById(R.id.banner);
//        // 设置数据
        turnBanner.init(new HolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolderView();
            }
        }, Arrays.asList(images));

        loopBtn = (Button) findViewById(R.id.loop_btn);
        scrollBtn = (Button) findViewById(R.id.scroll_btn);

        initEvent();
    }

    private void initEvent() {
        // 开关 无限循环
        loopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turnBanner.isTurnLoop()) {
                    turnBanner.setTurnLoop(false);
                    loopBtn.setText("开启无限循环");
                } else {
                    turnBanner.setTurnLoop(true);
                    loopBtn.setText("关闭无限循环");
                }
            }
        });
//        // 开关 banner滚动
//        scrollBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (turnBanner.Sc()) {
//                    turnBanner.setCanScroll(false);
//                    scrollBtn.setText("开启滚动");
//                } else {
//                    turnBanner.setCanScroll(true);
//                    scrollBtn.setText("关闭滚动");
//                }
//            }
//        });
    }

    /**
     * 指示点类型1 居中展示
     */
    public void pointerType1Center(View view) {
//        turnBanner.setPointViewVisible(true);
//        turnBanner.setPageIndicatorAlign(TurnBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    /**
     * 指示点类型自定义 居右展示
     */
    public void pointerTypeCustomRight(View view) {
//        turnBanner.setPointViewVisible(true);
//        turnBanner.setPageIndicatorAlign(TurnBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    }

    /**
     * 指示点 隐藏
     */
    public void pointerHide(View view) {
        turnBanner.setPointViewVisible(false);
    }

    /**
     * 开始自动轮播
     */
    public void startAutoTurn(View view) {
        turnBanner.startTurn();
    }

    /**
     * 暂停自动轮播
     */
    public void pauseAutoTurn(View view) {
        turnBanner.pauseTurn();
    }

    /**
     * 停止自动轮播
     */
    public void stopAutoTurn(View view) {
        turnBanner.stopTurn();
    }

    private void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 防止内存泄漏
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        turnBanner.destroy();
    }
}
