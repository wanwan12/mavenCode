package com.ecarx.xkbanner.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecarx.xkbanner.holder.Holder;
import com.ecarx.xkbanner.holder.HolderCreator;
import com.ecarx.xkbanner.view.BannerViewPager;

import java.util.List;

public class BannerPageAdapter<T> extends PagerAdapter {
    private static final String TAG = BannerPageAdapter.class.getSimpleName();

    private ViewPager viewPager;
    private HolderCreator holderCreator;
    protected List<T> data;
    private boolean canLoop = true;  // 是否支持无限循环

    private int childCount = 0;

    public BannerPageAdapter(HolderCreator holderCreator, List<T> data) {
        this.holderCreator = holderCreator;
        this.data = data;
    }

    public BannerPageAdapter(ViewPager viewPager, HolderCreator holderCreator, List<T> data, boolean canLoop) {
        this.holderCreator = holderCreator;
        this.data = data;
        this.viewPager = viewPager;
        this.canLoop = canLoop;
    }

    public void setViewPager(BannerViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * 获取实际view的个数
     */
    public int getRealCount() {
        return data == null ? 0 : data.size();
    }

    /**
     * 设置当前显示的实际是第几个View
     */
    public int getRealPosition(int position) {
        int realCount = getRealCount();
        if (0 == realCount) {
            return 0;
        } else {
            if (canLoop) {
                return position % realCount;
            } else {
                return position;
            }
        }
    }

    /**
     * 获取显示的View
     */
    private View getView(ViewGroup container, int position, View view) {
        Holder holder;
        if (view == null) {
            holder = (Holder) holderCreator.createHolder();
            view = LayoutInflater.from(container.getContext()).inflate(holderCreator.getLayoutId(), container, false);
            holder.initView(view);
            view.setTag(this);
        } else {
            holder = (Holder) view.getTag();
        }
        if (data != null && data.size() > position) {
            holder.updateUI(container.getContext(), view, position, data.get(position));
        }
        return view;
    }

    /**
     * 获取第一项
     */
    public int getFirstItem() {
        return canLoop ? getRealCount() : 0;
    }

    /**
     * 获取最后一项
     */
    public int getLastItem() {
        return getRealCount() - 1;
    }


    @Override
    public int getCount() {
        int realCount = getRealCount();
        if (0 == realCount) {
            return 0;
        } else {
            if (canLoop) {
                return Integer.MAX_VALUE;
            } else {
                return realCount;
            }
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = getRealPosition(position);
        View view = getView(container, realPosition, null);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.e(TAG, "destroyItem ");
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        Log.e(TAG, "finishUpdate ");
        int position = viewPager.getCurrentItem();
        if (position == 0) {
            position = getFirstItem();
        } else if (position == getCount() - 1) {
            position = getLastItem();
        }
        try {
            viewPager.setCurrentItem(position, false);
            Log.e(TAG, "cur item = " + position);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回POSITION_NONE，会刷新页面
     */
    @Override
    public int getItemPosition(Object object) {
        if (childCount > 0) {
            childCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public void notifyDataSetChanged() {
        childCount = getCount();
        super.notifyDataSetChanged();
    }


}
