package com.ecarx.xkbanner.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.ecarx.xkbanner.adapter.BannerPageAdapter;
import com.ecarx.xkbanner.holder.HolderCreator;
import com.ecarx.xkbanner.listener.OnItemClickListener;

import java.util.List;

public class BannerViewPager<T> extends ViewPager {

    private OnPageChangeListener listener;
    private OnItemClickListener onItemClickListener;
    private BannerPageAdapter<T> mAdapter;

    private boolean isCanScroll = true; // 能否滑动视图
    private boolean canLoop = true; // 是否支持无限循环


    private float oldX = 0, newX = 0;
    private static final float sens = 5;


    public BannerViewPager(Context context) {
        super(context);
        init();
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        super.setOnPageChangeListener(new OnPageChangeListener() {
            private float previousPosition = -1;

            @Override
            public void onPageSelected(int position) {
                int realPosition = mAdapter.getRealPosition(position);
                if (previousPosition != realPosition) {
                    previousPosition = realPosition;
                    if (listener != null) {
                        listener.onPageSelected(realPosition);
                    }
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (listener != null) {
                    if (position != mAdapter.getRealCount() - 1) {
                        listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    } else {
                        if (positionOffset > .5) {
                            listener.onPageScrolled(0, 0, 0);
                        } else {
                            listener.onPageScrolled(position, 0, 0);
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (listener != null) {
                    listener.onPageScrollStateChanged(state);
                }
            }
        });
    }

    public void initData(HolderCreator holderCreator, List<T> data, boolean canLoop, boolean isCanScroll) {
        mAdapter = new BannerPageAdapter<T>(this, holderCreator, data, canLoop);
        super.setAdapter(this.mAdapter);
        setCanScroll(isCanScroll);
        setCurrentItem(mAdapter.getFirstItem(), false);
    }

    public void setDataList(List<T> data) {
        if (mAdapter != null) {
            mAdapter.setData(data);
        }
    }

    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    public boolean isCanScroll() {
        return isCanScroll;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
        if (mAdapter == null)
            return;

        if (!canLoop) {
            setCurrentItem(getRealItem(), false);
        }

        mAdapter.setCanLoop(canLoop);
        mAdapter.notifyDataSetChanged();
    }

    public boolean isCanLoop() {
        return canLoop;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public BannerPageAdapter getAdapter() {
        return mAdapter;
    }


    public int getRealItem() {
        return mAdapter != null ? mAdapter.getRealPosition(super.getCurrentItem()) : 0;
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.listener = listener;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            try {
                return super.onInterceptTouchEvent(ev);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 处理ViewPager里面的item点击事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            if (onItemClickListener != null) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldX = ev.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        newX = ev.getX();
                        if (Math.abs(oldX - newX) < sens) {
                            onItemClickListener.onItemClick((getRealItem()));
                        }
                        oldX = 0;
                        newX = 0;
                        break;
                }
            }
            try {
                return super.onTouchEvent(ev);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
