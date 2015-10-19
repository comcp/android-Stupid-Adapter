package com.stupid.method.adapter;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

/**
 * @说明： 执行顺序 {@link XViewHolder#getLayoutId()}
 *      {@link XViewHolder#onCreate(XAdapter, android.content.Context)}
 *      {@link XViewHolder#onDestory(int)}
 *      {@link XViewHolder#onResetView(Object, int)}
 * 
 * @警告 请不要手动调用 {@link IXViewHolder #getView(Object, int)}
 * 
 * 
 * @author comcp@126.com <br>
 *         github: https://github.com/comcp/android-StupidMethod
 * @version v1.6
 * 
 * @创建时间：2014-12-3上午11:21:31
 * 
 */
abstract public class XViewHolder<T> implements IXViewHolder<T>,
		OnClickListener, OnLongClickListener {

	public String tag = null;

	protected LayoutInflater inflater;

	private OnClickItemListener itemListener;

	private OnLongClickItemListener longClickItemListener;

	protected T mData;

	protected View mRoot;

	private boolean onScrolling;
	private int position;

	public XViewHolder() {
		if (tag == null)
			tag = this.getClass().getSimpleName();
	}

	protected View findViewById(int id) {
		if (mRoot != null)
			return mRoot.findViewById(id);
		else
			return null;
	}

	@Override
	public abstract int getLayoutId();

	public Resources getResources() {
		return mRoot.getResources();
	}

	@Override
	public View getView() {
		return mRoot;
	}

	@Override
	public View getView(Object data, int position, boolean onScrolling) {
		this.setOnScrolling(onScrolling);
		this.setPosition(position);
		mRoot.setTag(this);
		try {
			// 会出现强制类型转换问题
			onResetView((T) data, position);
		} catch (Exception e) {

			Log.e(tag, String.format("data类型:%s", data.getClass()));
			Log.e(tag, this.getClass() + ".getView() 内的data类型不能进行强制转换", e);

		}
		return mRoot;
	}

	@Override
	public void onClick(View v) {
		if (itemListener != null) {
			itemListener.onClickItem(v, getPosition());
		}

	}

	@Override
	public void onDestory(int nextPosition, int count) {

		// System.out.println(String.format("Now:%d  Next:%d  ", position,
		// nextPosition));
	}

	@Override
	public boolean onLongClick(View v) {
		if (longClickItemListener != null) {
			return longClickItemListener.onLongClickItem(v, getPosition());
		} else
			return false;
	}

	public abstract void onResetView(T data, int position);

	public boolean post(Runnable action) {
		return mRoot.post(action);
	}

	public void setBackgroundColor(int color) {
		mRoot.setBackgroundColor(color);
	}

	public void setBackgroundDrawable(Drawable d) {
		mRoot.setBackgroundDrawable(d);
	}

	public void setBackgroundResource(int resid) {
		mRoot.setBackgroundResource(resid);
	}

	protected void setData(T data) {

		mData = data;

	}

	@Override
	public View setInflater(LayoutInflater inflater) {

		mRoot = inflater.inflate(getLayoutId(), null);
		return mRoot;
	}

	public void setLongClickable(boolean longClickable) {
		mRoot.setLongClickable(longClickable);
	}

	@Override
	public void setOnClickItemListener(OnClickItemListener itemListener) {
		this.itemListener = itemListener;
	}

	public void setOnClickListener(OnClickListener l) {
		if (mRoot != null)
			mRoot.setOnClickListener(l);
	}

	public void setOnKeyListener(OnKeyListener l) {
		mRoot.setOnKeyListener(l);

	}

	@Override
	public void setOnLongClickItemListener(
			OnLongClickItemListener longClickItemListener) {
		this.longClickItemListener = longClickItemListener;

	}

	public void setOnLongClickListener(OnLongClickListener l) {
		if (mRoot != null)
			mRoot.setOnLongClickListener(l);
	}

	public void setOnTouchListener(OnTouchListener l) {
		mRoot.setOnTouchListener(l);
	}

	public void setTag(int key, Object tag) {
		mRoot.setTag(key, tag);
	}

	public void setTag(Object tag) {
		mRoot.setTag(tag);
	}

	public boolean isOnScrolling() {
		return onScrolling;
	}

	public void setOnScrolling(boolean onScrolling) {
		this.onScrolling = onScrolling;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
