package com.zhl.cbdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.zhl.cbdialog.view.CBHorizontalProgressBar;


/**
 * 类名称：对话框工具类 类描述：创建对话框的工具类，可以设置不同样式，和动画风格
 */
public class CZDialogProgressBuilder extends CZDialogBaseBuilder {

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


    private int maxCount = 100;


    private OnStartListen mListen;


    /**
     * 构造器一 创建一个基本dialog
     *
     * @param context
     */
    public CZDialogProgressBuilder(Context context, int dialogType, boolean dimEnable) {
        super(context, dialogType, dimEnable);
    }

    /**
     * 设置系统级弹窗
     */
    public CZDialogProgressBuilder setSystemAlert(boolean isSystemAlert) {
        super.setSystemAlert(isSystemAlert);
        return this;
    }


    /**
     * 设置对话框宽度
     */
    public CZDialogProgressBuilder setDialogAnim(int dialogAnim) {
        super.setDialogAnim(dialogAnim);
        return this;
    }

    /**
     * 设置对话框宽度
     */
    public CZDialogProgressBuilder setDialogWidth(float dialogWidth) {
        super.setDialogWidth(dialogWidth);
        return this;
    }

    /**
     * 设置对话框透明度
     */
    public CZDialogProgressBuilder setDialogAlpha(float dialogAlpha) {
        super.setDialogAlpha(dialogAlpha);
        return this;
    }

    /**
     * 设置对话框位置
     */
    public CZDialogProgressBuilder setDialogLocation(int dialogLocation) {
        super.setDialogLocation(dialogLocation);
        return this;
    }


    /**
     * 设置对话框是否显示取消键
     */
    public CZDialogProgressBuilder showCancelButton(boolean showCancelButton) {
        super.showCancelButton(showCancelButton);
        return this;
    }

    /**
     * 设置对话框是否显示确定键
     */
    public CZDialogProgressBuilder showConfirmButton(boolean showConfirmbtn) {
        super.showConfirmButton(showConfirmbtn);
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    public CZDialogProgressBuilder touchOutSideCancel(boolean touchOutSideCancel) {
        super.touchOutSideCancel(touchOutSideCancel);
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    public CZDialogProgressBuilder cancelEnable(boolean cancelEnable) {
        super.cancelEnable(cancelEnable);
        return this;
    }

    /**
     * 设置点击监听
     */
    public CZDialogProgressBuilder setBtnClickListen(OnBtnClickListen btnClickListen) {
        super.setBtnClickListen(btnClickListen);
        return this;
    }

    /**
     * 设置确定按键文本
     */
    public CZDialogProgressBuilder setConfirmText(String confirmStr) {
        super.setConfirmText(confirmStr);
        return this;
    }

    /**
     * 设置取消按键文本
     */
    public CZDialogProgressBuilder setCancelText(String cancelStr) {
        super.setCancelText(cancelStr);
        return this;
    }


    public CZDialogProgressBuilder setMaxProgress(int maxProgress) {
        maxCount = maxProgress;
        return this;
    }

    public CZDialogProgressBuilder setCurProgress(int curProgress) {
        if (mViewHolder.cbhProgress != null) {
            mViewHolder.cbhProgress.setProgress(curProgress * 100 / maxCount);
        }
        return this;
    }


    public CZDialogProgressBuilder setStartListen(OnStartListen listen) {
        this.mListen = listen;
        return this;
    }

    public CZDialogProgressBuilder start(){
        if(mListen!=null&&mViewHolder.cbhProgress!=null){
            mListen.onStart(mViewHolder.cbhProgress);
        }
        return this;
    }


    /**
     * 设置对话框主题色
     */
    public CZDialogProgressBuilder setDialogTheme(int dialogTheme) {
        this.curDialogTheme = dialogTheme;
        if (mViewHolder.cbhProgress != null) {
            if (curDialogTheme == DIALOG_THEME_BLUE) {
                mViewHolder.cbhProgress.setColor(context.getResources().getColor(R.color.cb_item_color_blue));
            } else if (curDialogTheme == DIALOG_THEME_GREEN) {
                mViewHolder.cbhProgress.setColor(context.getResources().getColor(R.color.cb_item_color_green));
            } else if (curDialogTheme == DIALOG_THEME_PINK) {
                mViewHolder.cbhProgress.setColor(context.getResources().getColor(R.color.cb_item_color_pink));
            } else {
                mViewHolder.cbhProgress.setColor(context.getResources().getColor(R.color.cb_item_color_blue));
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
    public CZDialogProgressBuilder setTitle(String title) {
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
    public CZDialogProgressBuilder setContent(String content) {
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
    public CZDialogProgressBuilder setImg(@NonNull int resId) {
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

    public interface OnStartListen{
        void onStart(CBHorizontalProgressBar progressBar);
    }


}
