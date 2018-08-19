package com.wanwan.mavencode;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhl.cbdialog.CBDialogBuilder;
import com.zhl.cbdialog.CZDialogBaseBuilder;
import com.zhl.cbdialog.CZDialogHelp;
import com.zhl.cbdialog.CZDialogInputBuilder;
import com.zhl.cbdialog.CZDialogLoadingBuilder;
import com.zhl.cbdialog.CZDialogProgressBuilder;
import com.zhl.cbdialog.CZDialogSelectBuilder;


public class SignalActivity extends AppCompatActivity {

    private Context mContext;
    private int curSelectedItemPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();

    }


    private void initView() {


        findViewById(R.id.main_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CBDialogBuilder(mContext)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(true)
                        .showIcon(false)
                        .setTitle("提示")
                        .setMessage("你是否需要退出")
                        .setConfirmButtonText("确定")
                        .setCancelButtonText("取消")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .create().show();
            }
        });

        findViewById(R.id.main_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                main_failview.setMode(FailView.MODE_REFRESH);
                new CBDialogBuilder(mContext)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(true)
                        .setTitle("设置按钮和信息文字样式")
                        .setMessage("this is a normal CBDialog")
                        .setMessageTextSize(16)
                        .setConfirmButtonText("确定")
                        .setCancelButtonText("取消")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .create().show();
            }
        });
        findViewById(R.id.main_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CZDialogHelp.creatInputDialog(mContext, "请输入姓名", "请输入姓名", true, new CZDialogBaseBuilder.OnBtnClickListen() {
                    @Override
                    public void onBtnClick(Dialog dialog, int whichBtn, Object result) {
                        if (whichBtn == CZDialogBaseBuilder.OnBtnClickListen.BUTTON_CONFIRM) {
                            Toast.makeText(mContext, "结果:" + result, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
            }
        });
        findViewById(R.id.main_fail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] itemOptions = new String[]{"较小", "中等", "较大", "巨无霸"};
                new CZDialogSelectBuilder(mContext, CZDialogSelectBuilder.DIALOG_TYPE_SELECT_SINGLE, true)
                        .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                        .setItemClickListen(new CZDialogBaseBuilder.OnItemClickListen() {
                            @Override
                            public void onItmeClick(Dialog dialog, int which, Object result) {
                                dialog.dismiss();
                            }
                        })
                        .setItems(itemOptions, 0)
                        .create()
                        .show();

            }
        });

        findViewById(R.id.main_warn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] itemOptions = new String[]{"较小", "中等", "较大", "巨无霸"};
                new CBDialogBuilder(mContext)
                        .setTouchOutSideCancelable(false)
                        .showConfirmButton(false)
                        .setTitle("选择文字大小")
                        .setConfirmButtonText("ok")
                        .setCancelButtonText("cancel")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_NORMAL_Fast)
                        .setItems(itemOptions, new CBDialogBuilder.onDialogItemClickListener() {

                            @Override
                            public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter ItemAdapter,
                                                          Context context, CBDialogBuilder dialogbuilder, Dialog dialog,
                                                          int position) {
                                curSelectedItemPos = position;
                                //TODO 保存选中设置
                                dialog.dismiss();
                            }
                        }, new CBDialogBuilder.OnConvertItemViewListener() {
                            class ViewHolder {
                                TextView txView;
                            }

                            @Override
                            public View convertItemView(int position, View convertView, ViewGroup parent) {
                                ViewHolder viewHolder = null;
                                if (convertView == null) {
                                    viewHolder = new ViewHolder();
                                    convertView = LayoutInflater.from(mContext).inflate(
                                            R.layout.custon_item_option_text, parent, false);
                                    viewHolder.txView = (TextView) convertView
                                            .findViewById(R.id.item_tx);
                                    convertView.setTag(viewHolder);
                                } else {
                                    viewHolder = (ViewHolder) convertView.getTag();
                                }
                                viewHolder.txView.setTextColor(getResources().getColor(R.color.cb_item_color_default));
                                if (position == curSelectedItemPos) {
                                    viewHolder.txView.setBackgroundResource(R.drawable.custom_option_item_tx_background);
                                } else {
                                    viewHolder.txView.setBackgroundResource(R.color.color_transparent);
                                }
                                viewHolder.txView.setText(itemOptions[position]);
                                return convertView;
                            }
                        }, curSelectedItemPos)
                        .create().show();
            }
        });

        findViewById(R.id.main_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CZDialogLoadingBuilder(mContext, CZDialogBaseBuilder.DIALOG_TYPE_LOADING_AVLOAD, true)
                        .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                        .setLoadingText("加载中。。。")
                        .create().show();
            }
        });

        findViewById(R.id.main_system).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CBDialogBuilder(mContext, CBDialogBuilder.DIALOG_STYLE_LOADING_AVLOADING, false)
                        .setTouchOutSideCancelable(false)
                        .showCancelButton(true)
                        .setMessage("正在加载请稍后...")
                        .setAVLoadingColor(Color.RED)
                        .setOnProgressOutTimeListener(1, new CBDialogBuilder.onProgressOutTimeListener() {
                            @Override
                            public void onProgressOutTime(Dialog dialog, TextView dialogMsgTextView) {

                            }
                        })
                        .create().show();

            }
        });
        findViewById(R.id.main_customer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CZDialogInputBuilder(mContext, CZDialogBaseBuilder.DIALOG_TYPE_EDIT, true)
                        .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                        .setTitle("输入姓名")
                        .setHint("wanwan")
                        .showCancelButton(true)
                        .setBtnClickListen(new CZDialogBaseBuilder.OnBtnClickListen() {
                            @Override
                            public void onBtnClick(Dialog dialog, int whichBtn, Object result) {
                                if (whichBtn == CZDialogBaseBuilder.OnBtnClickListen.BUTTON_CONFIRM) {
                                    Toast.makeText(mContext, "确定  " + result, Toast.LENGTH_SHORT).show();
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        })
                        .setConfirmText("立即更新")
                        .setCancelText("取消更新")
                        .create().show();
            }
        });
//        u0_a280   8011  2923  1474704 209280 SyS_epoll_ 0000000000 S me.leefeng.beida

        findViewById(R.id.main_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CZDialogProgressBuilder(mContext, CZDialogBaseBuilder.DIALOG_TYPE_PROGRESS, true)
                        .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                        .setTitle("进度条")
                        .setCurProgress(30)
                        .create().show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mInstance = null;
    }
}
