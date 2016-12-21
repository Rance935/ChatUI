package com.rance.chatui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
public class EmotionPagerAdapter extends PagerAdapter {

	private List<GridView> gvs;

	public EmotionPagerAdapter(List<GridView> gvs) {
		this.gvs = gvs;
	}

	@Override
	public int getCount() {
		return gvs.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView(gvs.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		((ViewPager) container).addView(gvs.get(position));
		return gvs.get(position);
	}

}
