package com.wanwan.mavencode;

import android.graphics.Bitmap;

import com.ecarx.sdk.vrcontrol.tip.TipInfo;

/**
 * TipInfoImpl.java is XiaoKa App...。
 *
 * @author Yulong Jin
 * @version 1.0 2018/4/18
 * @modify
 */
public class TipInfoImpl extends TipInfo {
    private int tipType;

    private String tipTitle;

    private String tipContent;

    private String tipContent2;

    private String tipTTS;

    private Bitmap tipBitmap;

    private int tipPriority;

    @Override
    public int getTipType() {
        return tipType;
    }

    public void setTipType(int tipType) {
        this.tipType = tipType;
    }

    @Override
    public String getTipTitle() {
        return tipTitle;
    }

    public void setTipTitle(String tipTitle) {
        this.tipTitle = tipTitle;
    }

    @Override
    public String getTipContent() {
        return tipContent;
    }

    public void setTipContent(String tipContent) {
        this.tipContent = tipContent;
    }

    @Override
    public String getTipContent2() {
        return tipContent2;
    }

    public void setTipContent2(String tipContent2) {
        this.tipContent2 = tipContent2;
    }

    @Override
    public String getTipTTS() {
        return tipTTS;
    }

    public void setTipTTS(String tipTTS) {
        this.tipTTS = tipTTS;
    }

    @Override
    public Bitmap getTipBitmap() {
        return tipBitmap;
    }

    public void setTipBitmap(Bitmap tipBitmap) {
        this.tipBitmap = tipBitmap;
    }

    @Override
    public int getTipPriority() {
        return tipPriority;
    }

    public void setTipPriority(int tipPriority) {
        this.tipPriority = tipPriority;
    }
}
