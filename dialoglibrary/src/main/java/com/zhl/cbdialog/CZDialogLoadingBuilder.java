package com.zhl.cbdialog;

import android.content.Context;
import android.view.View;


/**
 * 类名称：对话框工具类 类描述：创建对话框的工具类，可以设置不同样式，和动画风格
 */
public class CZDialogLoadingBuilder extends CZDialogBaseBuilder {

    /**
     * 构造器一 创建一个基本dialog
     *
     * @param context
     */
    public CZDialogLoadingBuilder(Context context, int dialogType, boolean dimEnable) {
        super(context, dialogType, dimEnable);
    }

    /**
     * 设置系统级弹窗
     */
    public CZDialogBaseBuilder setSystemAlert(boolean isSystemAlert) {
        super.setSystemAlert(isSystemAlert);
        return this;
    }


    /**
     * 设置对话框宽度
     */
    public CZDialogBaseBuilder setDialogAnim(int dialogAnim) {
        super.setDialogAnim(dialogAnim);
        return this;
    }

    /**
     * 设置对话框宽度
     */
    public CZDialogBaseBuilder setDialogWidth(float dialogWidth) {
        super.setDialogWidth(dialogWidth);
        return this;
    }

    /**
     * 设置对话框透明度
     */
    public CZDialogBaseBuilder setDialogAlpha(float dialogAlpha) {
        super.setDialogAlpha(dialogAlpha);
        return this;
    }

    /**
     * 设置对话框位置
     */
    public CZDialogBaseBuilder setDialogLocation(int dialogLocation) {
        super.setDialogLocation(dialogLocation);
        return this;
    }


    /**
     * 设置对话框是否显示取消键
     */
    public CZDialogBaseBuilder showCancelButton(boolean showCancelButton) {
        super.showCancelButton(showCancelButton);
        return this;
    }

    /**
     * 设置对话框是否显示确定键
     */
    public CZDialogBaseBuilder showConfirmButton(boolean showConfirmbtn) {
        super.showConfirmButton(showConfirmbtn);
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    public CZDialogBaseBuilder touchOutSideCancel(boolean touchOutSideCancel) {
        super.touchOutSideCancel(touchOutSideCancel);
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    public CZDialogBaseBuilder cancelEnable(boolean cancelEnable) {
        super.cancelEnable(cancelEnable);
        return this;
    }

    /**
     * 设置点击监听
     */
    public CZDialogBaseBuilder setBtnClickListen(OnBtnClickListen btnClickListen) {
        super.setBtnClickListen(btnClickListen);
        return this;
    }

    /**
     * 设置确定按键文本
     */
    public CZDialogBaseBuilder setConfirmText(String confirmStr) {
        super.setConfirmText(confirmStr);
        return this;
    }

    /**
     * 设置取消按键文本
     */
    public CZDialogBaseBuilder setCancelText(String cancelStr) {
        super.setCancelText(cancelStr);
        return this;
    }


    /**
     * 设置对话框主题色
     */
    public CZDialogLoadingBuilder setDialogTheme(int dialogTheme) {
        this.curDialogTheme = dialogTheme;
        if (curDialogType == DIALOG_TYPE_LOADING_AVLOAD) {
            if (curDialogTheme == DIALOG_THEME_BLUE) {
                mViewHolder.loadingAvload.setIndicatorColor(context.getResources().getColor(R.color.cb_item_color_blue));
                mViewHolder.tvLoading.setTextColor(context.getResources().getColor(R.color.cb_item_color_blue));
            } else if (curDialogTheme == DIALOG_THEME_GREEN) {
                mViewHolder.loadingAvload.setIndicatorColor(context.getResources().getColor(R.color.cb_item_color_green));
                mViewHolder.tvLoading.setTextColor(context.getResources().getColor(R.color.cb_item_color_green));
            } else if (curDialogTheme == DIALOG_THEME_PINK) {
                mViewHolder.loadingAvload.setIndicatorColor(context.getResources().getColor(R.color.cb_item_color_pink));
                mViewHolder.tvLoading.setTextColor(context.getResources().getColor(R.color.cb_item_color_pink));
            } else {
                mViewHolder.loadingAvload.setIndicatorColor(context.getResources().getColor(R.color.cb_item_color_blue));
                mViewHolder.tvLoading.setTextColor(context.getResources().getColor(R.color.cb_item_color_blue));
            }
        }
        return this;
    }


    /**
     * 设置对话框标题
     *
     * @param loadingText
     * @return
     */
    public CZDialogLoadingBuilder setLoadingText(String loadingText) {
        if (mViewHolder.tvLoading != null) {
            if (loadingText != null) {
                mViewHolder.tvLoading.setText(loadingText);
            } else {
                mViewHolder.tvLoading.setVisibility(View.GONE);
            }
        }
        return this;
    }


    @Override
    public void onComfireClick() {
        super.onComfireClick();
        if (btnClickListen != null) {
            btnClickListen.onBtnClick(dialog, OnBtnClickListen.BUTTON_CONFIRM, null);
        } else {
            dialog.dismiss();
        }
    }

    @Override
    public void onCancelClick() {
        super.onCancelClick();
        if (btnClickListen != null) {
            btnClickListen.onBtnClick(dialog, OnBtnClickListen.BUTTON_CANCEL, null);
        } else {
            dialog.dismiss();
        }
    }


}
