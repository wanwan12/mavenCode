package com.wanwan.mavencode;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhl.cbdialog.CBDialogBuilder;
import com.zhl.cbdialog.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;


public class SignalActivity extends AppCompatActivity  {

    private Context mContext;
    private int curSelectedItemPos=0;

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
                        .setMessageTextColor(Color.BLUE)
                        .setConfirmButtonText("确定")
//                        .setConfirmBackgroundResouce(R.drawable.custom_button_background_right)
                        .setConfirmButtonTextColor(Color.WHITE)
                        .setCancelButtonText("Quit")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .create().show();
            }
        });
        findViewById(R.id.main_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CBDialogBuilder(mContext)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(false)
                        .setTitle("单个按钮")
                        .setMessage("this is a normal CBDialog")
                        .setConfirmButtonText("确定")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .create().show();
            }
        });
        findViewById(R.id.main_fail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CBDialogBuilder(mContext)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(true)
                        .setTitle("这是一个有按钮监听的对话框")
                        .setMessage("this is a normal CBDialog with listener")
                        .setConfirmButtonText("ok")
                        .setCancelButtonText("cancel")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_BOTTOM)
                        .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                switch (whichBtn) {
                                    case BUTTON_CONFIRM:
                                        Toast.makeText(context, "点击了确认按钮", Toast.LENGTH_SHORT).show();
                                        break;
                                    case BUTTON_CANCEL:
                                        Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        })
                        .create().show();
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
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
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
                                viewHolder.txView.setTextColor(getResources().getColor(R.color.item_text_color));
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
                new CBDialogBuilder(mContext, CBDialogBuilder.DIALOG_STYLE_PROGRESS, 0.5f)
                        .showCancelButton(true)
                        .setMessage("正在加载请稍后...")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setOnProgressOutTimeListener(1, new CBDialogBuilder.onProgressOutTimeListener() {
                            @Override
                            public void onProgressOutTime(Dialog dialog, TextView dialogMsgTextView) {
//                                dialogMsgTextView.setText("出错啦");
                            }
                        })
                        .setProgressTimeOutLimit(false)
//                        .setProgressStyleColorRes(new int[]{0xFF37474F,0xFF263238,0xFF21272B,0xFF80CBC4,0xFF009688,0xFFDE6262,0xFF0F519F})
                        .create().show();
            }
        });

        findViewById(R.id.main_system).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CBDialogBuilder(mContext, CBDialogBuilder.DIALOG_STYLE_PROGRESS_AVLOADING)
                        .setTouchOutSideCancelable(false)
                        .showCancelButton(true)
                        .setMessage("正在加载请稍后...")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
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
                new CBDialogBuilder(mContext, CBDialogBuilder.DIALOG_STYLE_NORMAL)
                        .showIcon(false)
                        .setTouchOutSideCancelable(false)
                        .showCancelButton(false)
                        .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                        .setTitle("请输入姓名")
                        .setView(R.layout.cb_custom_edit_view)
                        .setConfirmButtonText("确定")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .create().show();
            }
        });
//        u0_a280   8011  2923  1474704 209280 SyS_epoll_ 0000000000 S me.leefeng.beida

        findViewById(R.id.main_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CBDialogBuilder(mContext, CBDialogBuilder.DIALOG_STYLE_NORMAL)
                        .showIcon(false)
                        .setTouchOutSideCancelable(false)
                        .showCancelButton(false)
                        .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                        .setTitle("请输入姓名")
                        .setView(R.layout.cb_custom_progressbar_view)
                        .setConfirmButtonText("确定")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
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
