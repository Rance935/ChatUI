package com.rance.chatui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.rance.chatui.R;
import com.rance.chatui.util.Utils;

import java.util.ArrayList;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 * 自定义表情底部指示器
 */
public class IndicatorView extends LinearLayout {

    private Context mContext;
    private ArrayList<View> mImageViews;//所有指示器集合
    private int size = 6;
    private int marginSize = 15;
    private int pointSize;//指示器的大小
    private int marginLeft;//间距

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        pointSize = Utils.dp2px(context, size);
        marginLeft = Utils.dp2px(context, marginSize);
    }

    /**
     * 初始化指示器
     *
     * @param count 指示器的数量
     */
    public void initIndicator(int count) {
        mImageViews = new ArrayList<>();
        this.removeAllViews();
        LayoutParams lp;
        for (int i = 0; i < count; i++) {
            View v = new View(mContext);
            lp = new LayoutParams(pointSize, pointSize);
            if (i != 0)
                lp.leftMargin = marginLeft;
            v.setLayoutParams(lp);
            if (i == 0) {
                v.setBackgroundResource(R.drawable.bg_circle_white);
            } else {
                v.setBackgroundResource(R.drawable.bg_circle_gary);
            }
            mImageViews.add(v);
            this.addView(v);
        }
    }

    /**
     * 页面移动时切换指示器
     */
    public void playByStartPointToNext(int startPosition, int nextPosition) {
        if (startPosition < 0 || nextPosition < 0 || nextPosition == startPosition) {
            startPosition = nextPosition = 0;
        }
        final View ViewStrat = mImageViews.get(startPosition);
        final View ViewNext = mImageViews.get(nextPosition);
        ViewNext.setBackgroundResource(R.drawable.bg_circle_white);
        ViewStrat.setBackgroundResource(R.drawable.bg_circle_gary);
    }

}
