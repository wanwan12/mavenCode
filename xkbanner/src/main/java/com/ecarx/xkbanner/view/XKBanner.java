package com.ecarx.xkbanner.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ecarx.xkbanner.R;
import com.ecarx.xkbanner.adapter.BannerPageAdapter;
import com.ecarx.xkbanner.holder.HolderCreator;
import com.ecarx.xkbanner.listener.CustomPageChangeListener;
import com.ecarx.xkbanner.listener.OnItemClickListener;
import com.ecarx.xkbanner.transformer.AccordionTransformer;
import com.ecarx.xkbanner.transformer.BackgroundToForegroundTransformer;
import com.ecarx.xkbanner.transformer.CubeInTransformer;
import com.ecarx.xkbanner.transformer.CubeOutTransformer;
import com.ecarx.xkbanner.transformer.DefaultTransformer;
import com.ecarx.xkbanner.transformer.DepthPageTransformer;
import com.ecarx.xkbanner.transformer.FlipHorizontalTransformer;
import com.ecarx.xkbanner.transformer.FlipVerticalTransformer;
import com.ecarx.xkbanner.transformer.ForegroundToBackgroundTransformer;
import com.ecarx.xkbanner.transformer.RotateDownTransformer;
import com.ecarx.xkbanner.transformer.RotateUpTransformer;
import com.ecarx.xkbanner.transformer.ScaleInOutTransformer;
import com.ecarx.xkbanner.transformer.StackTransformer;
import com.ecarx.xkbanner.transformer.TabletTransformer;
import com.ecarx.xkbanner.transformer.ZoomInTransformer;
import com.ecarx.xkbanner.transformer.ZoomOutSlideTransformer;
import com.ecarx.xkbanner.transformer.ZoomOutTranformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 页面翻转控件
 */
public class XKBanner<T> extends LinearLayout {
    private static final int TF_ABASE = 0, TF_ACCORDION = 1, TF_BTOF = 2, TF_CUBEIN = 3, TF_CUBEOUT = 4, TF_DEFAULT = 5,
            TF_DEPTHPAGE = 6, TF_FLIPHOR = 7, TF_FTOB = 8, TF_ROTATEDOWN = 9, TF_ROTATEUP = 10, TF_STACK = 11, TF_SCALEINOUT = 12,
            TF_TABLET = 13, TF_ZOOMIN = 14, TF_ZOOMOUTSLIDE = 15, TF_ZOOMOUT = 16, TF_FLIPVER = 17;
    private static final int MSG_AUTO_TURN = 1; // 自动翻页的消息标识

    public enum PageIndicatorAlign {
        ALIGN_PARENT_LEFT, ALIGN_PARENT_RIGHT, CENTER_HORIZONTAL
    }

    // layout
    private BannerViewPager bannerViewPager;
    private ViewGroup pointerContainer;
    private ViewPagerScroller scroller;
    private CustomPageChangeListener customPageChangeListener;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private List<T> data;
    private List<ImageView> pointViews = new ArrayList<>();


    // attr
    private long turnTime = 6000;  // 翻页时间
    private long turnDuration = 2000; //翻页持续时间
    private boolean turnAuto = true; // 能否自动触发翻页
    private boolean turnLoop = true; // 是否支持无限循环
    private boolean scrollEnable = true; //是否支持滑动
    private int[] pointImgIds = {R.drawable.xkbanner_shape_indicator_selected, R.drawable.xkbanner_shape_indicator_unselect};
    private int transformerType = TF_DEFAULT;

    private boolean isTurning = false; // 能否手动触发翻页


    public XKBanner(Context context) {
        this(context, null);
        init(context, null);
    }

    public XKBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XKBanner);
            turnTime = typedArray.getInteger(R.styleable.XKBanner_xkbannerTurnTime, 6000);
            turnDuration = typedArray.getInteger(R.styleable.XKBanner_xkbannerTurnDuration, 2000);
            turnAuto = typedArray.getBoolean(R.styleable.XKBanner_xkbannerTurnAuto, true);
            turnLoop = typedArray.getBoolean(R.styleable.XKBanner_xkbannerTurnLoop, true);
            scrollEnable = typedArray.getBoolean(R.styleable.XKBanner_xkbannerScrollEnable, true);
            turnTime = typedArray.getInteger(R.styleable.XKBanner_xkbannerTurnTime, 6000);
            pointImgIds[0] = typedArray.getResourceId(R.styleable.XKBanner_xkbannerIndicatorOn, R.drawable.xkbanner_shape_indicator_selected);
            pointImgIds[1] = typedArray.getResourceId(R.styleable.XKBanner_xkbannerIndicatorOff, R.drawable.xkbanner_shape_indicator_unselect);
            transformerType = typedArray.getInt(R.styleable.XKBanner_xkbannerTransformer, TF_DEFAULT);
            typedArray.recycle();
        }

        View view = LayoutInflater.from(context).inflate(R.layout.view_xkbanner, this, true);
        bannerViewPager = (BannerViewPager) view.findViewById(R.id.banner_view_pager);
        pointerContainer = (ViewGroup) view.findViewById(R.id.banner_point_container);

        // 初始化ViewPager的滑动速度
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            scroller = new ViewPagerScroller(getContext());
            mScroller.set(bannerViewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化页面 并且 初始化数据
     */
    public XKBanner init(HolderCreator holderCreator, List<T> data) {
        this.data = data;
        bannerViewPager.initData(holderCreator, data, turnLoop, scrollEnable);
        ViewPager.PageTransformer transformer;
        switch (transformerType) {
            case TF_ABASE:
                transformer = new DefaultTransformer();
                break;
            case TF_ACCORDION:
                transformer = new AccordionTransformer();
                break;
            case TF_BTOF:
                transformer = new BackgroundToForegroundTransformer();
                break;
            case TF_CUBEIN:
                transformer = new CubeInTransformer();
                break;
            case TF_CUBEOUT:
                transformer = new CubeOutTransformer();
                break;
            case TF_DEFAULT:
                transformer = new DefaultTransformer();
                break;
            case TF_DEPTHPAGE:
                transformer = new DepthPageTransformer();
                break;
            case TF_FLIPHOR:
                transformer = new FlipHorizontalTransformer();
                break;
            case TF_FTOB:
                transformer = new ForegroundToBackgroundTransformer();
                break;
            case TF_ROTATEDOWN:
                transformer = new RotateDownTransformer();
                break;
            case TF_ROTATEUP:
                transformer = new RotateUpTransformer();
                break;
            case TF_STACK:
                transformer = new StackTransformer();
                break;
            case TF_SCALEINOUT:
                transformer = new ScaleInOutTransformer();
                break;
            case TF_TABLET:
                transformer = new TabletTransformer();
                break;
            case TF_ZOOMIN:
                transformer = new ZoomInTransformer();
                break;
            case TF_ZOOMOUTSLIDE:
                transformer = new ZoomOutSlideTransformer();
                break;
            case TF_ZOOMOUT:
                transformer = new ZoomOutTranformer();
                break;
            case TF_FLIPVER:
                transformer = new FlipVerticalTransformer();
                break;
            default:
                transformer = new DefaultTransformer();
                break;
        }
        bannerViewPager.setPageTransformer(false, transformer);
        if(turnAuto){
            startTurn();
        }
        return this;
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_AUTO_TURN) {
                removeMessages(MSG_AUTO_TURN);
                updataViewPager();
            }
        }
    };

    public void updataViewPager() {
        if (bannerViewPager != null  && turnAuto) {
            int page = bannerViewPager.getCurrentItem() + 1;
            bannerViewPager.setCurrentItem(page);
            if (turnAuto) {
                handler.sendEmptyMessageDelayed(MSG_AUTO_TURN, turnTime);
            }
        }
    }


    /**
     * 仅初始化数据
     */
    public XKBanner setData(List<T> data) {
        this.data = data;
        bannerViewPager.setDataList(data);
        refreshPageIndicator();
        return this;
    }

    public int getDataSize() {
        return null == data ? 0 : data.size();
    }

    public List<T> getData() {
        return data;
    }

    public XKBanner startTurn() {
        stopTurn();
        // 设置可以翻页并开启翻页
        isTurning = true;
        turnAuto = true;
        handler.sendEmptyMessageDelayed(MSG_AUTO_TURN, turnTime);
        return this;
    }

    public void pauseTurn() {
        turnAuto = false;
        handler.removeMessages(MSG_AUTO_TURN);
    }

    public void stopTurn() {
        turnAuto = false;
        isTurning = false;
        handler.removeMessages(MSG_AUTO_TURN);
    }

    /***
     * 是否开启了翻页
     */
    public boolean isTurnAuto() {
        return turnAuto;
    }

    /**
     * 设置可以循环播放
     */
    public void setTurnLoop(boolean turnLoop) {
        this.turnLoop = turnLoop;
        bannerViewPager.setCanLoop(turnLoop);
    }

    public boolean isTurnLoop() {
        return bannerViewPager.isCanLoop();
    }

    /**
     * 设置可以滚动
     */
    public void setCanScroll(boolean scrollEnable) {
        bannerViewPager.setCanScroll(scrollEnable);
    }

    public boolean scrollEnable() {
        return bannerViewPager.isCanScroll();
    }

    /**
     * 触碰控件的时候，翻页应该停止，离开的时候如果之前是开启了翻页的话则重新启动翻页
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_CANCEL ||
                action == MotionEvent.ACTION_OUTSIDE) {
            // 开始翻页
            if (isTurning) {
                startTurn();
            }
        } else if (action == MotionEvent.ACTION_DOWN) {
            // 暂停翻页
            if (isTurning) {
                pauseTurn();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 设置当前的页面index
     *
     * @param index
     */
    public void setcurrentitem(int index) {
        if (bannerViewPager != null) {
            bannerViewPager.setCurrentItem(index);
        }
    }

    /**
     * 获取当前的页面index
     */
    public int getCurrentItem() {
        if (bannerViewPager != null) {
            return bannerViewPager.getRealItem();
        }
        return -1;
    }

    /**
     * 自定义指示器样式：. . . . .
     * <p>
     * pointImgIds大小只能为2
     */
    public XKBanner refreshPageIndicator() {
        pointerContainer.removeAllViews();
        pointViews.clear();

        if (null == data) {
            return this;
        }
        for (int count = 0; count < data.size(); count++) {
            ImageView pointView = new ImageView(getContext());
            pointView.setPadding(0, 0, 10, 10);
            if (pointViews.isEmpty()) {
                pointView.setImageResource(pointImgIds[0]);
            } else {
                pointView.setImageResource(pointImgIds[1]);
            }
            pointViews.add(pointView);
            pointerContainer.addView(pointView);
        }
        if (null == customPageChangeListener) {
            customPageChangeListener = new CustomPageChangeListener(pointViews, pointImgIds);
        } else {
            customPageChangeListener.setPointImgData(pointViews, pointImgIds);
        }
        bannerViewPager.setOnPageChangeListener(customPageChangeListener);
        if (null != onPageChangeListener)
            customPageChangeListener.setPageChangeListener(onPageChangeListener);
        return this;
    }


    /**
     * 设置底部指示器是否可见
     */
    public XKBanner setPointViewVisible(boolean visible) {
        pointerContainer.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 指示器的方向
     *
     * @param align 三个方向：
     *              居左 （RelativeLayout.ALIGN_PARENT_LEFT），
     *              居中 （RelativeLayout.CENTER_HORIZONTAL），
     *              居右 （RelativeLayout.ALIGN_PARENT_RIGHT）
     */
    public XKBanner setPageIndicatorAlign(PageIndicatorAlign align) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) pointerContainer.getLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, align == PageIndicatorAlign.ALIGN_PARENT_LEFT ? RelativeLayout.TRUE : 0);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, align == PageIndicatorAlign.ALIGN_PARENT_RIGHT ? RelativeLayout.TRUE : 0);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, align == PageIndicatorAlign.CENTER_HORIZONTAL ? RelativeLayout.TRUE : 0);
        pointerContainer.setLayoutParams(layoutParams);
        return this;
    }

    /**
     * 自定义翻页动画效果
     */
    public XKBanner setPageTransformer(ViewPager.PageTransformer transformer) {
        bannerViewPager.setPageTransformer(true, transformer);
        return this;
    }

    public BannerViewPager getBannerViewPager() {
        return bannerViewPager;
    }

    /**
     * 设置翻页监听器
     */
    public XKBanner setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
        // 如果有默认的监听器（即是使用了默认的翻页指示器）则把用户设置的依附到默认的上面，否则就直接设置
        if (customPageChangeListener != null) {
            customPageChangeListener.setPageChangeListener(onPageChangeListener);
        } else {
            bannerViewPager.setOnPageChangeListener(onPageChangeListener);
        }
        return this;
    }

    /**
     * 监听item点击
     */
    public XKBanner setOnItemClickListener(OnItemClickListener onItemClickListener) {
        bannerViewPager.setOnItemClickListener(onItemClickListener);
        return this;
    }

    public void destroy() {
        if (null != handler) {
            handler.removeCallbacksAndMessages(null);
        }
    }

}
