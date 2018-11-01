package com.wanwan.mavencode;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wanwan.mavencode.widget.CustomProgressBar;
import com.wanwan.mavencode.widget.FlikerProgressBar;
import com.zhl.cbdialog.CBDialogBuilder;
import com.zhl.cbdialog.CZDialogBaseBuilder;
import com.zhl.cbdialog.CZDialogHelp;
import com.zhl.cbdialog.CZDialogInputBuilder;
import com.zhl.cbdialog.CZDialogLoadingBuilder;
import com.zhl.cbdialog.CZDialogProgressBuilder;
import com.zhl.cbdialog.CZDialogSelectBuilder;

import java.util.List;


public class SignalActivity extends AppCompatActivity {

    private static final String TAG = SignalActivity.class.getSimpleName();
    private Context mContext;
    private int curSelectedItemPos = 0;

    private EditText etData;
    private FlikerProgressBar flikerProgressBar;
    private CustomProgressBar customProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
//        etData = (EditText) findViewById(R.id.et_data);
//        etData.requestFocusFromTouch();
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ecarx.intent.action.voice.boot.completed");
        registerReceiver(myBroadcastReceiver, filter);
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
                CZDialogHelp.creatInputDialog(mContext, "请输入姓名", "请输入姓名", "", new CZDialogBaseBuilder.OnBtnClickListen() {
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
        findViewById(R.id.btn_checkDVR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean state = hasRegisterExit("ecarx.intent.broadcast.category.ECARX_VR_APP_CLOSE_DVR", 1);
                Intent intent = new Intent("/");
                ComponentName cm = new ComponentName("com.neusoft.optimus.wheeljack.setting", "com.neusoft.optimus.wheeljack.setting.WirelessSettings");
                intent.setComponent(cm);
                intent.setAction("android.intent.action.VIEW");
                startActivityForResult(intent, 0);
            }
        });

        flikerProgressBar = (FlikerProgressBar) findViewById(R.id.id_flikerProgressBar);
        flikerProgressBar.setProgress(50);
        customProgressBar = (CustomProgressBar) findViewById(R.id.cpb_progresbar2);
        customProgressBar.setCurProgress(50);
    }

    private boolean hasRegisterExit(String category, int closeType) {
        Intent intent = new Intent();
        intent.setAction("ecarx.intent.broadcast.action.ECARX_VR_APP_CLOSE");
        intent.addCategory(category);
        intent.putExtra("close_type", closeType);

        Log.w(TAG, "hasRegister start:" + System.currentTimeMillis());
        PackageManager pm = mContext.getApplicationContext().getPackageManager();
        List<ResolveInfo> resolveInfos = pm.queryBroadcastReceivers(intent, 0);
        Log.w(TAG, "hasRegister stop :" + System.currentTimeMillis());
        if (resolveInfos != null && !resolveInfos.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        analysisKeyEvent(event);
        return true;

    }

    /**
     * 扫码设备事件解析
     *
     * @param event
     */
    public void analysisKeyEvent(KeyEvent event) {
        Log.e("LBSSignActivity", event.getKeyCode() + "     " + event.toString());
        int keyCode = event.getKeyCode();

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_0:
                    inputStr.append("0");
                    break;
                case KeyEvent.KEYCODE_1:
                    inputStr.append("1");
                    break;
                case KeyEvent.KEYCODE_2:
                    inputStr.append("2");
                    break;
                case KeyEvent.KEYCODE_3:
                    inputStr.append("3");
                    break;
                case KeyEvent.KEYCODE_4:
                    inputStr.append("4");
                    break;
                case KeyEvent.KEYCODE_5:
                    inputStr.append("5");
                    break;
                case KeyEvent.KEYCODE_6:
                    inputStr.append("6");
                    break;
                case KeyEvent.KEYCODE_7:
                    inputStr.append("7");
                    break;
                case KeyEvent.KEYCODE_8:
                    inputStr.append("8");
                    break;
                case KeyEvent.KEYCODE_9:
                    inputStr.append("9");
                    break;
                case KeyEvent.KEYCODE_ENTER:
                    Toast.makeText(mContext, inputStr.toString(), Toast.LENGTH_SHORT).show();
                    inputStr = new StringBuilder();
                    break;
            }
        }

    }

    private StringBuilder inputStr = new StringBuilder();

    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(mContext, intent.getAction(), Toast.LENGTH_SHORT).show();
        }
    }
}
