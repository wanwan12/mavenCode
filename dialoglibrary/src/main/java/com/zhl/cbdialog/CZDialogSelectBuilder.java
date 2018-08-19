package com.zhl.cbdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * 类名称：对话框工具类 类描述：创建对话框的工具类，可以设置不同样式，和动画风格
 */
public class CZDialogSelectBuilder extends CZDialogBaseBuilder {

    /**
     * 单选对话框
     */
    public static final int DIALOG_TYPE_SELECT_SINGLE = R.layout.cz_dialog_select_single;
    /**
     * 多选对话框
     */
    protected static final int DIALOG_TYPE_SELECT_MULT = R.layout.cz_dialog_select_mult;

    private String[] items;
    private int curSelectedPos = -1;


    /**
     * 构造器一 创建一个基本dialog
     *
     * @param context
     */
    public CZDialogSelectBuilder(Context context, int dialogType, boolean dimEnable) {
        super(context, dialogType, dimEnable);
    }

    /**
     * 设置系统级弹窗
     */
    public CZDialogSelectBuilder setSystemAlert(boolean isSystemAlert) {
        super.setSystemAlert(isSystemAlert);
        return this;
    }


    /**
     * 设置对话框宽度
     */
    public CZDialogSelectBuilder setDialogAnim(int dialogAnim) {
        super.setDialogAnim(dialogAnim);
        return this;
    }

    /**
     * 设置对话框宽度
     */
    public CZDialogSelectBuilder setDialogWidth(float dialogWidth) {
        super.setDialogWidth(dialogWidth);
        return this;
    }

    /**
     * 设置对话框透明度
     */
    public CZDialogSelectBuilder setDialogAlpha(float dialogAlpha) {
        super.setDialogAlpha(dialogAlpha);
        return this;
    }

    /**
     * 设置对话框位置
     */
    public CZDialogSelectBuilder setDialogLocation(int dialogLocation) {
        super.setDialogLocation(dialogLocation);
        return this;
    }


    /**
     * 设置对话框是否显示取消键
     */
    public CZDialogSelectBuilder showCancelButton(boolean showCancelButton) {
        super.showCancelButton(showCancelButton);
        return this;
    }

    /**
     * 设置对话框是否显示确定键
     */
    public CZDialogSelectBuilder showConfirmButton(boolean showConfirmbtn) {
        super.showConfirmButton(showConfirmbtn);
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    public CZDialogSelectBuilder touchOutSideCancel(boolean touchOutSideCancel) {
        super.touchOutSideCancel(touchOutSideCancel);
        return this;
    }

    /**
     * 设置点击对话框外面，对话框是否取消
     */
    public CZDialogSelectBuilder cancelEnable(boolean cancelEnable) {
        super.cancelEnable(cancelEnable);
        return this;
    }

    /**
     * 设置点击监听
     */
    public CZDialogSelectBuilder setBtnClickListen(OnBtnClickListen btnClickListen) {
        super.setBtnClickListen(btnClickListen);
        return this;
    }

    /**
     * 设置点击监听
     */
    public CZDialogSelectBuilder setItemClickListen(OnItemClickListen itemClickListen) {
        super.setItemClickListen(itemClickListen);
        return this;
    }

    /**
     * 设置确定按键文本
     */
    public CZDialogSelectBuilder setConfirmText(String confirmStr) {
        super.setConfirmText(confirmStr);
        return this;
    }

    /**
     * 设置取消按键文本
     */
    public CZDialogSelectBuilder setCancelText(String cancelStr) {
        super.setCancelText(cancelStr);
        return this;
    }

    /**
     * 给对话框设置一个数组列表
     *
     * @param items
     * @param curSelectedPos 当前选中的position
     * @return
     */
    public CZDialogSelectBuilder setItems(String[] items, int curSelectedPos) {
       this.items = items;
       this.curSelectedPos = curSelectedPos;
       if(mViewHolder.listSingle!=null){

       }
        final DialogItemAdapter adapter = new DialogItemAdapter(items, curSelectedPos);
        mViewHolder.listSingle.setAdapter(adapter);
        // 给listview里面的选项设置监听器
        mViewHolder.listSingle.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 让调用者决定是否关闭对话框
                // dialog.dismiss();
                adapter.setSelectedPos(position);
                adapter.notifyDataSetChanged();
                if (itemClickListen != null) {
                    itemClickListen.onItmeClick( dialog, position,position);
                }
            }
        });

        return this;
    }


    /**
     * 设置对话框主题色
     */
    public CZDialogSelectBuilder setDialogTheme(int dialogTheme) {
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
    public CZDialogSelectBuilder setTitle(String title) {
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
    public CZDialogSelectBuilder setContent(String content) {
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
    public CZDialogSelectBuilder setImg(@NonNull int resId) {
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

    /**
     * dialog列表选项的适配器
     *
     * @author zhl
     */
    public class DialogItemAdapter extends BaseAdapter {
        private String[] dataArrays;
        private int selectedPos;

        public DialogItemAdapter(String[] dataArrays, int selectedPos) {
            this.dataArrays = dataArrays;
            this.selectedPos = selectedPos;
        }

        @Override
        public int getCount() {
            return dataArrays == null ? 0 : dataArrays.length;
        }

        @Override
        public Object getItem(int position) {
            return dataArrays[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public void setSelectedPos(int position) {
            this.selectedPos = position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.cb_item_option_text, parent, false);
                viewHolder.txView = (TextView) convertView
                        .findViewById(R.id.item_tx);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            if (position == selectedPos) {
                if (curDialogTheme == DIALOG_THEME_BLUE) {
                    viewHolder.txView.setTextColor(context.getResources().getColor(R.color.cb_item_color_blue));
                } else if (curDialogTheme == DIALOG_THEME_GREEN) {
                    viewHolder.txView.setTextColor(context.getResources().getColor(R.color.cb_item_color_green));
                } else if (curDialogTheme == DIALOG_THEME_PINK) {
                    viewHolder.txView.setTextColor(context.getResources().getColor(R.color.cb_item_color_pink));
                }
            } else {
                viewHolder.txView.setBackgroundResource(R.color.color_transparent);
                viewHolder.txView.setTextColor(context.getResources().getColor(R.color.cb_item_color_default));
            }
            viewHolder.txView.setText(dataArrays[position]);
            return convertView;
        }

        public class ViewHolder {
            TextView txView;
        }

    }


}
