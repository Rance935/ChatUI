package com.rance.chatui.util;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
public class PopupWindowFactory {

    private Context mContext;

    private PopupWindow mPop;

    /**
     * @param mContext 上下文
     * @param view PopupWindow显示的布局文件
     */
    public PopupWindowFactory(Context mContext, View view){
        this(mContext,view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * @param mContext 上下文
     * @param view PopupWindow显示的布局文件
     * @param width PopupWindow的宽
     * @param heigth PopupWindow的高
     */
    public PopupWindowFactory(Context mContext, View view, int width, int heigth){
        init(mContext,view,width,heigth);
    }


    private void init(Context mContext, View view, int width, int heigth){
        this.mContext = mContext;

        //下面这两个必须有！！
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);

        // PopupWindow(布局，宽度，高度)
        mPop = new PopupWindow(view,width,heigth,true);
        mPop.setFocusable(true);

        // 重写onKeyListener,按返回键消失
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mPop.dismiss();
                    return true;
                }
                return false;
            }
        });

        //点击其他地方消失
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mPop != null && mPop.isShowing()) {
                    mPop.dismiss();
                    return true;
                }
                return false;
            }});


    }

    public PopupWindow getPopupWindow(){
        return mPop;
    }


    /**
     * 以触发弹出窗的view为基准，出现在view的内部上面，弹出的pop_view左上角正对view的左上角
     * @param parent view
     * @param gravity 在view的什么位置 Gravity.CENTER、Gravity.TOP......
     * @param x 与控件的x坐标距离
     * @param y 与控件的y坐标距离
     */
    public void showAtLocation(View parent, int gravity, int x, int y){

        if(mPop.isShowing()){
            return ;
        }
        mPop.showAtLocation(parent, gravity, x, y);

    }

    /**
     * 以触发弹出窗的view为基准，出现在view的正下方，弹出的pop_view左上角正对view的左下角
     * @param anchor view
     */
    public void showAsDropDown(View anchor){
        showAsDropDown(anchor,0,0);
    }

    /**
     * 以触发弹出窗的view为基准，出现在view的正下方，弹出的pop_view左上角正对view的左下角
     * @param anchor view
     * @param xoff 与view的x坐标距离
     * @param yoff 与view的y坐标距离
     */
    public void showAsDropDown(View anchor, int xoff, int yoff){
        if(mPop.isShowing()){
            return ;
        }

        mPop.showAsDropDown(anchor, xoff, yoff);
    }

    /**
     * 隐藏PopupWindow
     */
    public void dismiss(){
        if (mPop.isShowing()) {
            mPop.dismiss();
        }
    }

}
