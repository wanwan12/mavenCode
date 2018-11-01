package com.zhl.cbdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zhl.cbdialog.pnikosis.materialishprogress.ProgressWheel;
import com.zhl.cbdialog.titanic.TitanicTextView;
import com.zhl.cbdialog.view.CBHorizontalProgressBar;


/**
 * 类名称：对话框工具类 类描述：创建对话框的工具类，可以设置不同样式，和动画风格
 */
public class CZDialogBaseBuilder implements OnClickListener {
    /**
     * 普通提示对话框
     */
    public static final int DIALOG_TYPE_TIPS = R.layout.cz_dialog_tips;
    /**
     * 带图片提示对话框
     */
    public static final int DIALOG_TYPE_TIPS_IMG = R.layout.cz_dialog_tips_img;
    /**
     * 单选对话框
     */
    protected static final int DIALOG_TYPE_SELECT_SINGLE = R.layout.cz_dialog_select_single;
    /**
     * 多选对话框
     */
    protected static final int DIALOG_TYPE_SELECT_MULT = R.layout.cz_dialog_select_mult;
    /**
     * 加载中 对话框
     */
    public static final int DIALOG_TYPE_LOADING = R.layout.cz_dialog_loading;
    /**
     * avload 加载中对话框
     */
    public static final int DIALOG_TYPE_LOADING_AVLOAD = R.layout.cz_dialog_loading_avload;
    /**
     * titanic; 加载中对话框
     */
    public static final int DIALOG_TYPE_LOADING_TITANIC = R.layout.cz_dialog_loading_titanic;
    /**
     * 文本输入对话框
     */
    public static final int DIALOG_TYPE_EDIT = R.layout.cz_dialog_edit;
    /**
     * 进度条对话框
     */
    public static final int DIALOG_TYPE_PROGRESS = R.layout.cz_dialog_progress;


    /**
     * 缩放动画
     */
    public static final int DIALOG_ANIM_NORMAL = R.style.DialogAnimation;
    /**
     *
     * */
    public static final int DIALOG_ANIM_NORMAL_FAST = R.style.DialogAnimationFast;
    /**
     * 从下往上滑动动画
     */
    public static final int DIALOG_ANIM_SLID_BOTTOM = R.style.DialogAnimationSlidBottom;
    /**
     * 从上往下滑动动画
     */
    public static final int DIALOG_ANIM_SLID_TOP = R.style.DialogAnimationSlidTop;
    /**
     * 从右往左滑动动画
     */
    public static final int DIALOG_ANIM_SLID_RIGHT = R.style.DialogAnimationSlidRight;


    /**
     * 对话框处于屏幕顶部位置
     */
    public static final int DIALOG_LOCATION_TOP = 12;
    /**
     * 对话框处于屏幕中间位置
     */
    public static final int DIALOG_LOCATION_CENTER = 10;
    /**
     * 对话框处于屏幕底部位置
     */
    public static final int DIALOG_LOCATION_BOTTOM = 11;


    /**
     * 对话框处于屏幕顶部位置
     */
    public static final int DIALOG_THEME_PINK = 0;
    /**
     * 对话框处于屏幕中间位置
     */
    public static final int DIALOG_THEME_GREEN = 1;
    /**
     * 对话框处于屏幕底部位置
     */
    public static final int DIALOG_THEME_BLUE = 2;


    /**
     * 输入框类型
     */
    public static final int INPUT_TYPE_NUMBER = 1;
    /**
     * 输入框类型
     */
    public static final int INPUT_TYPE_TEXT = 2;
    /**
     * 输入框类型
     */
    public static final int INPUT_TYPE_PHONE = 3;


    /**
     * 当前对话框类型
     */
    public int curDialogType = DIALOG_TYPE_TIPS;

    /**
     * 当前对话框出入场动画
     */
    private int curDialogAnim = DIALOG_ANIM_NORMAL;
    /**
     * 当前对话框占屏幕横向比例
     */
    private float curDialogWidth = 0.75f;
    /**
     * 当前对话框透明比例
     */
    private float curDialogAlphafactor = 1.0f;
    /**
     * 当前对话框透明比例
     */
    private int curDialogLocation = DIALOG_LOCATION_CENTER;

    /**
     * 当前对话框主题
     */
    public int curDialogTheme = DIALOG_THEME_BLUE;
    /**
     * 是否显示cancelbutton;
     */
    public boolean showCancelButton = false;
    /**
     * 是否显示确认按钮
     */
    public boolean showConfirmBtn = true;


    public Context context;
    public Dialog dialog;
    public ViewHolder mViewHolder;
    public OnBtnClickListen btnClickListen;
    public OnItemClickListen itemClickListen;


    /**
     * 构造器一 创建一个基本dialog
     *
     * @param context
     */
    public CZDialogBaseBuilder(Context context, int dialogType, boolean dimEnable) {
        curDialogType = dialogType;
        this.context = context;
        dialog = null;
        if (dimEnable) {
            dialog = new Dialog(context, R.style.Dialog);
        } else {
            dialog = new Dialog(context, R.style.DialogDim);
        }
        // 设置对话框风格
        dialog.setContentView(curDialogType);
        mViewHolder = new ViewHolder(dialog);
    }

    /**
     * 设置系统级弹窗
     */
    protected CZDialogBaseBuilder setSystemAlert(boolean isSystemAlert) {
        Window window = dialog.getWindow();
        if (isSystemAlert) {
            window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        return this;
    }


    /**
     * 设置对话框宽度
     */
    protected CZDialogBaseBuilder setDialogAnim(int dialogAnim) {
        this.curDialogAnim = dialogAnim;
        return this;
    }

    /**
     * 设置对话框宽度
     */
    protected CZDialogBaseBuilder setDialogWidth(float dialogWidth) {
        this.curDialogWidth = dialogWidth;
        return this;
    }

    /**
     * 设置对话框透明度
     */
    protected CZDialogBaseBuilder setDialogAlpha(float dialogAlpha) {
        this.curDialogAlphafactor = dialogAlpha;
        return this;
    }

    /**
     * 设置对话框位置
     */
    protected CZDialogBaseBuilder setDialogLocation(int dialogLocation) {
        this.curDialogLocation = dialogLocation;
        return this;
    }


    /**
     * 设置对话框是否显示取消键
     */
    protected CZDialogBaseBuilder showCancelButton(boolean showCancelButton) {
        this.showCancelButton = showCancelButton;
        return this;
    }

    /**
     * 设置对话框是否显示确定键
     */
    protected CZDialogBaseBuilder showConfirmButton(boolean showConfirmbtn) {
        this.showConfirmBtn = showConfirmbtn;
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    protected CZDialogBaseBuilder touchOutSideCancel(boolean touchOutSideCancel) {
        this.dialog.setCanceledOnTouchOutside(touchOutSideCancel);
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    protected CZDialogBaseBuilder cancelEnable(boolean cancelEnable) {
        this.dialog.setCancelable(cancelEnable);
        return this;
    }

    /**
     * 设置点击监听
     */
    protected CZDialogBaseBuilder setBtnClickListen(OnBtnClickListen btnClickListen) {
        this.btnClickListen = btnClickListen;
        return this;
    }

    /**
     * 设置点击监听
     */
    protected CZDialogBaseBuilder setItemClickListen(OnItemClickListen itemClickListen) {
        this.itemClickListen = itemClickListen;
        return this;
    }

    /**
     * 设置确定按键文本
     */
    public CZDialogBaseBuilder setConfirmText(String confirmStr) {
        if (mViewHolder.btnComfire != null && confirmStr != null) {
            mViewHolder.btnComfire.setText(confirmStr);
        }
        return this;
    }

    /**
     * 设置取消按键文本
     */
    public CZDialogBaseBuilder setCancelText(String cancelStr) {
        if (mViewHolder.btnCancel != null && cancelStr != null) {
            mViewHolder.btnCancel.setText(cancelStr);
        }
        return this;
    }


    /**
     * 创建对话框
     *
     * @return
     */
    public Dialog create() {

        setBaseParams();
        setBtnShow();


        if (context instanceof Activity) {
            dialog.setOwnerActivity((Activity) context);
        }
        return dialog;
    }

    public CZDialogBaseBuilder setBaseParams() {
        Window window = dialog.getWindow();
        window.setWindowAnimations(curDialogAnim);

        //宽度
        DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenwidth = metrics.widthPixels;
        int width = 0;
        if (curDialogWidth > 0) {
            width = (int) (screenwidth * curDialogWidth);
        } else {
            width = (int) (screenwidth * curDialogWidth);
        }
        window.getAttributes().width = width;
        //透明度
        WindowManager.LayoutParams lp = window.getAttributes();
        if (curDialogAlphafactor > 1.0f || curDialogAlphafactor < 0f) {
            lp.alpha = 1.0f;
        } else {
            lp.alpha = curDialogAlphafactor;
        }
        window.setAttributes(lp);
        //对话框显示位置
        if (curDialogLocation == DIALOG_LOCATION_BOTTOM) {
            window.setGravity(Gravity.BOTTOM);
        } else if (curDialogLocation == DIALOG_LOCATION_TOP) {
            window.setGravity(Gravity.TOP);
        } else {
            window.setGravity(Gravity.CENTER);
        }

        return this;
    }

    private void setBtnShow() {
        if (mViewHolder.linBtn != null) {
            if (showConfirmBtn && showCancelButton) {
                mViewHolder.btnComfire.setVisibility(View.VISIBLE);
                mViewHolder.btnCancel.setVisibility(View.VISIBLE);
                mViewHolder.viewVerline.setVisibility(View.VISIBLE);
                mViewHolder.btnCancel.setBackgroundResource(R.drawable.cb_button_background_left);
                mViewHolder.btnComfire.setBackgroundResource(R.drawable.cb_button_background_right);
                mViewHolder.btnComfire.setOnClickListener(this);
                mViewHolder.btnCancel.setOnClickListener(this);
            } else if (showConfirmBtn && !showCancelButton) {
                mViewHolder.btnComfire.setVisibility(View.VISIBLE);
                mViewHolder.btnCancel.setVisibility(View.GONE);
                mViewHolder.viewVerline.setVisibility(View.GONE);
                mViewHolder.btnComfire.setBackgroundResource(R.drawable.cb_button_background);
                mViewHolder.btnComfire.setOnClickListener(this);
            } else {
                mViewHolder.linBtn.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_comfire) {
            onComfireClick();
        } else if (v.getId() == R.id.btn_cancel) {
            onCancelClick();
        }
    }

    public void onComfireClick() {

    }

    public void onCancelClick() {

    }


    class ViewHolder {
        /**
         * 标题
         */
        TextView tvTitle;
        LinearLayout linContent;


        /**
         * 提示
         */
        ImageView imgTips;
        TextView tvContent;

        /**
         * 单选
         */
        ListView listSingle;

        /**
         * 多选
         */
        ListView list_mult;

        /**
         * 进度条
         */
        CBHorizontalProgressBar cbhProgress;

        /**
         * 文本输入
         */
        EditText etContent;

        /**
         * 加载中
         */
        ProgressWheel loadingWheel;
        TextView tvLoading;

        /**
         * avload 加载中
         */
        AVLoadingIndicatorView loadingAvload;

        /**
         * titanic; 加载中
         */
        TitanicTextView loadingTitanic;

        /**
         * 确定取消按钮
         */
        LinearLayout linBtn;
        View viewVerline;
        Button btnCancel;
        Button btnComfire;


        public ViewHolder(Dialog view) {
            if (curDialogType == DIALOG_TYPE_TIPS
                    || curDialogType == DIALOG_TYPE_TIPS_IMG
                    || curDialogType == DIALOG_TYPE_EDIT) {
                tvTitle = (TextView) view.findViewById(R.id.tv_title);
                linContent = (LinearLayout) view.findViewById(R.id.lin_content);
                linBtn = (LinearLayout) view.findViewById(R.id.lin_btn);
                viewVerline = view.findViewById(R.id.view_verline);
                btnCancel = (Button) view.findViewById(R.id.btn_cancel);
                btnComfire = (Button) view.findViewById(R.id.btn_comfire);
                if (curDialogType == DIALOG_TYPE_TIPS_IMG) {
                    imgTips = (ImageView) view.findViewById(R.id.img_tips);
                    tvContent = (TextView) view.findViewById(R.id.tv_content);
                } else if (curDialogType == DIALOG_TYPE_TIPS) {
                    tvContent = (TextView) view.findViewById(R.id.tv_content);
                } else if (curDialogType == DIALOG_TYPE_EDIT) {
                    etContent = (EditText) view.findViewById(R.id.et_content);
                }
            } else if (curDialogType == DIALOG_TYPE_LOADING) {
                tvLoading = (TextView) view.findViewById(R.id.tv_loading);
                loadingWheel = (ProgressWheel) view.findViewById(R.id.loading_wheel);
            } else if (curDialogType == DIALOG_TYPE_LOADING_AVLOAD) {
                tvLoading = (TextView) view.findViewById(R.id.tv_loading);
                loadingAvload = (AVLoadingIndicatorView) view.findViewById(R.id.loading_avload);
            } else if (curDialogType == DIALOG_TYPE_LOADING_TITANIC) {
                loadingTitanic = (TitanicTextView) view.findViewById(R.id.loading_titanic);
            } else if (curDialogType == DIALOG_TYPE_PROGRESS) {
                tvTitle = (TextView) view.findViewById(R.id.tv_title);
                linContent = (LinearLayout) view.findViewById(R.id.lin_content);
                cbhProgress = (CBHorizontalProgressBar) view.findViewById(R.id.cbh_progress);
            } else if (curDialogType == DIALOG_TYPE_SELECT_SINGLE) {
                tvTitle = (TextView) view.findViewById(R.id.tv_title);
                linContent = (LinearLayout) view.findViewById(R.id.lin_content);
                listSingle = (ListView) view.findViewById(R.id.list_single);
            } else if (curDialogType == DIALOG_TYPE_SELECT_MULT) {
                tvTitle = (TextView) view.findViewById(R.id.tv_title);
                linContent = (LinearLayout) view.findViewById(R.id.lin_content);
                list_mult = (ListView) view.findViewById(R.id.list_mult);
                linBtn = (LinearLayout) view.findViewById(R.id.lin_btn);
                viewVerline = view.findViewById(R.id.view_verline);
                btnCancel = (Button) view.findViewById(R.id.btn_cancel);
                btnComfire = (Button) view.findViewById(R.id.btn_comfire);
            }
        }
    }

    /**
     * 监听器监听对话框按钮点击
     *
     * @author zhl
     */
    public interface OnBtnClickListen {
        /**
         * （区分点击的事左边按钮还是右边按钮）--确认
         */
        static final int BUTTON_CONFIRM = 0;
        /**
         * （区分点击的事左边按钮还是右边按钮）--取消
         */
        static final int BUTTON_CANCEL = 1;

        /**
         * @param dialog   点击的哪个对话框
         * @param whichBtn 点击的哪个按钮
         */
        void onBtnClick(Dialog dialog, int whichBtn, Object result);

    }

    /**
     * 监听器监听对话框按钮点击
     *
     * @author zhl
     */
    public interface OnItemClickListen {
        /**
         * @param dialog 点击的哪个对话框
         * @param which  点击的哪个按钮
         */
        void onItmeClick(Dialog dialog, int which, Object result);

    }


}
