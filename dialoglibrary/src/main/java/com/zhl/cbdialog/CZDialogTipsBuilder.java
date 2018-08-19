package com.zhl.cbdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;


/**
 * 类名称：对话框工具类 类描述：创建对话框的工具类，可以设置不同样式，和动画风格
 */
public class CZDialogTipsBuilder extends CZDialogBaseBuilder {

    /**
     * 消息位于对话框的位置 居左
     */
    public static final int MSG_LAYOUT_LEFT = 1;
    /**
     * 消息位于对话框的位置 居中
     */
    public static final int MSG_LAYOUT_CENTER = 0;


    /**
     * 当前对话框msg对齐方式
     */
    private int curMsgLayout = MSG_LAYOUT_CENTER;


    /**
     * 构造器一 创建一个基本dialog
     *
     * @param context
     */
    public CZDialogTipsBuilder(Context context, int dialogType, boolean dimEnable) {
        super(context, dialogType, dimEnable);
    }

    /**
     * 设置系统级弹窗
     */
    public CZDialogTipsBuilder setSystemAlert(boolean isSystemAlert) {
        super.setSystemAlert(isSystemAlert);
        return this;
    }


    /**
     * 设置对话框宽度
     */
    public CZDialogTipsBuilder setDialogAnim(int dialogAnim) {
        super.setDialogAnim(dialogAnim);
        return this;
    }

    /**
     * 设置对话框宽度
     */
    public CZDialogTipsBuilder setDialogWidth(float dialogWidth) {
        super.setDialogWidth(dialogWidth);
        return this;
    }

    /**
     * 设置对话框透明度
     */
    public CZDialogTipsBuilder setDialogAlpha(float dialogAlpha) {
        super.setDialogAlpha(dialogAlpha);
        return this;
    }

    /**
     * 设置对话框位置
     */
    public CZDialogTipsBuilder setDialogLocation(int dialogLocation) {
        super.setDialogLocation(dialogLocation);
        return this;
    }


    /**
     * 设置对话框是否显示取消键
     */
    public CZDialogTipsBuilder showCancelButton(boolean showCancelButton) {
        super.showCancelButton(showCancelButton);
        return this;
    }

    /**
     * 设置对话框是否显示确定键
     */
    public CZDialogTipsBuilder showConfirmButton(boolean showConfirmbtn) {
        super.showConfirmButton(showConfirmbtn);
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    public CZDialogTipsBuilder touchOutSideCancel(boolean touchOutSideCancel) {
        super.touchOutSideCancel(touchOutSideCancel);
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    public CZDialogTipsBuilder cancelEnable(boolean cancelEnable) {
        super.cancelEnable(cancelEnable);
        return this;
    }

    /**
     * 设置点击监听
     */
    public CZDialogTipsBuilder setBtnClickListen(OnBtnClickListen btnClickListen) {
        super.setBtnClickListen(btnClickListen);
        return this;
    }

    /**
     * 设置确定按键文本
     */
    public CZDialogTipsBuilder setConfirmText(String confirmStr) {
        super.setConfirmText(confirmStr);
        return this;
    }

    /**
     * 设置取消按键文本
     */
    public CZDialogTipsBuilder setCancelText(String cancelStr) {
        super.setCancelText(cancelStr);
        return this;
    }


    /**
     * 设置对话框内容的位置
     */
    public CZDialogTipsBuilder setMsgLocation(int msgLocation) {
        this.curMsgLayout = msgLocation;
        if (mViewHolder.tvContent != null && msgLocation > 0) {
            if (msgLocation == MSG_LAYOUT_LEFT) {
                mViewHolder.tvContent.setGravity(Gravity.LEFT);
            } else if (msgLocation == MSG_LAYOUT_CENTER) {
                mViewHolder.tvContent.setGravity(Gravity.CENTER);
            }
        }
        return this;
    }

    /**
     * 设置对话框主题色
     */
    public CZDialogTipsBuilder setDialogTheme(int dialogTheme) {
        this.curDialogTheme = dialogTheme;
        if (mViewHolder.btnComfire != null) {
            if (curDialogTheme == DIALOG_THEME_BLUE) {
                mViewHolder.btnComfire.setTextColor(context.getResources().getColor(R.color.cb_item_color_blue));
            } else if (curDialogTheme == DIALOG_THEME_GREEN) {
                mViewHolder.btnComfire.setTextColor(context.getResources().getColor(R.color.cb_item_color_green));
            } else if (curDialogTheme == DIALOG_THEME_PINK) {
                mViewHolder.btnComfire.setTextColor(context.getResources().getColor(R.color.cb_item_color_pink));
            } else {
                mViewHolder.btnComfire.setTextColor(context.getResources().getColor(R.color.cb_item_color_blue));
            }
        }
        return this;
    }


    /**
     * 设置对话框标题
     *
     * @param title
     * @return
     */
    public CZDialogTipsBuilder setTitle(String title) {
        if (mViewHolder.tvTitle != null) {
            if (title != null) {
                mViewHolder.tvTitle.setText(title);
            } else {
                mViewHolder.tvTitle.setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 设置对话框标题
     *
     * @param content
     * @return
     */
    public CZDialogTipsBuilder setContent(String content) {
        if (mViewHolder.tvContent != null) {
            if (content != null) {
                mViewHolder.tvContent.setText(content);
            } else {
                mViewHolder.tvContent.setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 设置对话框标题
     *
     * @param resId
     * @return
     */
    public CZDialogTipsBuilder setImg(@NonNull int resId) {
        if (mViewHolder.imgTips != null) {
            mViewHolder.imgTips.setImageResource(resId);
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
