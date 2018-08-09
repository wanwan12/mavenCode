package com.wanwan.mavencode;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class SignalActivity extends AppCompatActivity  {



    private PromptDialog promptDialog;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();

    }


    private void initView() {
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);


        findViewById(R.id.main_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialog.showWarn("注意");
            }
        });

        findViewById(R.id.main_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                main_failview.setMode(FailView.MODE_REFRESH);
                promptDialog.showLoading("正在登录");
            }
        });
        findViewById(R.id.main_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialog.showSuccess("登陆成功");
            }
        });
        findViewById(R.id.main_fail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialog.showError("登录失败");
            }
        });

        //按钮的定义，创建一个按钮的对象
        final PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                Toast.makeText(mContext, button.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        confirm.setTextColor(Color.parseColor("#DAA520"));
        confirm.setFocusBacColor(Color.parseColor("#FAFAD2"));
        confirm.setDelyClick(true);//点击后，是否再对话框消失后响应按钮的监听事件
        findViewById(R.id.main_warn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialog.showWarnAlert("你确定要退出登录？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                        Toast.makeText(mContext, button.getText(), Toast.LENGTH_SHORT).show();
                    }
                }), confirm);
            }
        });

        findViewById(R.id.main_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialog.showInfo("成功了");
            }
        });

        findViewById(R.id.main_system).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //可创建android效果的底部Sheet选择，默认IOS效果，sheetCellPad=0为Android效果的Sheet
//                promptDialog.getAlertDefaultBuilder().sheetCellPad(0).round(0);
                //设置按钮的特点，颜色大小什么的，具体看PromptButton的成员变量
                PromptButton cancle = new PromptButton("取消", null);
                cancle.setTextColor(Color.parseColor("#0076ff"));
                //设置显示的文字大小及颜色
//                promptDialog.getAlertDefaultBuilder().textSize(12).textColor(Color.GRAY);
                //默认两个按钮为Alert对话框，大于三个按钮的为底部SHeet形式展现
                promptDialog.showAlertSheet("", true, cancle,
                        new PromptButton("选项1", null), new PromptButton("选项2", null),
                        new PromptButton("选项3", null), new PromptButton("选项4", null));

            }
        });
        findViewById(R.id.main_customer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialog.showCustom(R.mipmap.ic_launcher, "自定义图标的");
            }
        });
//        u0_a280   8011  2923  1474704 209280 SyS_epoll_ 0000000000 S me.leefeng.beida

        findViewById(R.id.main_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialog.getDefaultBuilder().backAlpha(150);
//                Glide.with(MainActivity.this).load("https://timgsa.baidu.com/timg?image&quality=80&" +
//                        "size=b9999_10000&sec=1495518782659&di=25b120262114749ae8543652d2de5715&" +
//                        "imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F160316%2F9-160316152R5.jpg")
////                        .placeholder(getResources().getDrawable(R.drawable.ic_prompt_holder))
//                        .into(promptDialog.showAd(true, new OnAdClickListener() {
//                            @Override
//                            public void onAdClick() {
//                                Toast.makeText(MainActivity.this,"点击了广告",Toast.LENGTH_SHORT).show();
//                            }
//                        }));
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mInstance = null;
    }
}
