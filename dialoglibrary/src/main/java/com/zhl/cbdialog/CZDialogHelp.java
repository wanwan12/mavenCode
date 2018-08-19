package com.zhl.cbdialog;

import android.app.Dialog;
import android.content.Context;

/**
 * 类名称：对话框工具类 类描述：创建对话框的工具类，可以设置不同样式，和动画风格
 */
public class CZDialogHelp {
    public static Dialog creatLoadingDialog(Context mContext) {
        return new CZDialogLoadingBuilder(mContext, CZDialogBaseBuilder.DIALOG_TYPE_LOADING_AVLOAD, true)
                .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                .setLoadingText("加载中。。。")
                .create();
    }

    public static Dialog creatTipsDialog(Context mContext, String contentStr, boolean showCancel, final CZDialogBaseBuilder.OnBtnClickListen listen) {
        return new CZDialogTipsBuilder(mContext, CZDialogBaseBuilder.DIALOG_TYPE_TIPS, true)
                .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                .setTitle("提示")
                .setContent(contentStr)
                .showCancelButton(showCancel)
                .setBtnClickListen(listen)
                .setConfirmText("确定")
                .setCancelText("取消")
                .create();
    }

    public static Dialog creatTipsDialog(Context mContext, String titleStr, String contentStr, boolean showCancel, final CZDialogBaseBuilder.OnBtnClickListen listen) {
        return new CZDialogTipsBuilder(mContext, CZDialogBaseBuilder.DIALOG_TYPE_TIPS, true)
                .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                .setTitle(titleStr)
                .setContent(contentStr)
                .showCancelButton(showCancel)
                .setBtnClickListen(listen)
                .setConfirmText("确定")
                .setCancelText("取消")
                .create();
    }

    public static Dialog creatTipsDialog(Context mContext, String titleStr, String contentStr, String confirmStr, String cancelStr, boolean showCancel, final CZDialogBaseBuilder.OnBtnClickListen listen) {
        return new CZDialogTipsBuilder(mContext, CZDialogBaseBuilder.DIALOG_TYPE_TIPS, true)
                .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                .setTitle(titleStr)
                .setContent(contentStr)
                .showCancelButton(showCancel)
                .setBtnClickListen(listen)
                .setConfirmText(confirmStr)
                .setCancelText(cancelStr)
                .create();
    }

    public static Dialog creatSelSingleDialog(Context mContext, String[] items, int cueSelPos, CZDialogBaseBuilder.OnItemClickListen listen) {
        return new CZDialogSelectBuilder(mContext, CZDialogSelectBuilder.DIALOG_TYPE_SELECT_SINGLE, true)
                .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                .setItemClickListen(listen)
                .setItems(items, cueSelPos)
                .create();
    }

    public static Dialog creatInputDialog(Context mContext, String titleStr, String hintStr, boolean showCancel, CZDialogBaseBuilder.OnBtnClickListen listen) {
        return new CZDialogInputBuilder(mContext, CZDialogBaseBuilder.DIALOG_TYPE_EDIT, true)
                .setDialogTheme(CZDialogBaseBuilder.DIALOG_THEME_PINK)
                .setTitle(titleStr)
                .setHint(hintStr)
                .showCancelButton(showCancel)
                .setBtnClickListen(listen)
                .setConfirmText("确定")
                .setCancelText("取消")
                .create();
    }


}
