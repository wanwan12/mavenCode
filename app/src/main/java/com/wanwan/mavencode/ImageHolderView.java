package com.wanwan.mavencode;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.ecarx.xkbanner.holder.Holder;
import com.ecarx.xkbanner.util.DensityUtil;

/**
 * 本地、网络图片加载
 * Created by fengqingxiuyi on 17/9/1.
 */

public class ImageHolderView implements Holder<String> {

    private int bannerHeight;
    private int bannerBgColor;


    // 点击事件
    private ImageClickListener clickListener;

    interface ImageClickListener {
        void click(View view, int position, String path);
    }

    public void setClickListener(ImageClickListener clickListener) {
        this.clickListener = clickListener;
    }

    // 长按事件
    private ImageLongClickListener longListener;

    interface ImageLongClickListener {
        void longClick(View view, int position, String path);
    }

    public void setLongClickListener(ImageLongClickListener longListener) {
        this.longListener = longListener;
    }

    public ImageHolderView() {
        this.bannerHeight = LayoutParams.MATCH_PARENT;
    }

    /**
     * 你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
     */
    @Override
    public View createView(Context context) {
        ImageView imageView = new ImageView(context);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, bannerHeight);
        imageView.setLayoutParams(params);

        if (bannerBgColor != 0) {
            imageView.setBackgroundColor(bannerBgColor);
        }
        imageView.setImageResource(R.drawable.custom_img);
        return imageView;
    }

    @Override
    public void updateUI(Context context, final View view, final int position, final String data) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != clickListener) {
                    clickListener.click(view, position, data);
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != longListener) {
                    longListener.longClick(view, position, data);
                }
                return false;
            }
        });
    }

    public void setBannerHeight(Context context, int bannerHeight) {
        this.bannerHeight = DensityUtil.dip2px(context, bannerHeight);
    }

}
