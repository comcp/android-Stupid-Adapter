package com.stupid.method.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

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
abstract public class XViewHolder<T> implements IXViewHolder, OnClickListener,
		OnLongClickListener {
	public static String tag = null;
	protected LayoutInflater inflater;
	private OnClickItemListener itemListener;
	private OnLongClickItemListener longClickItemListener;
	protected T mData;
	protected View mRoot;
	protected boolean onScrolling;
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

	@Override
	public View getView() {
		return mRoot;
	}

	@Override
	public View getView(Object data, int position, boolean onScrolling) {
		this.onScrolling = onScrolling;
		this.position = position;
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
			itemListener.onClickItem(v, position);
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
			return longClickItemListener.onLongClickItem(v, position);
		} else
			return false;
	}

	public abstract void onResetView(T data, int position);

	protected void setData(T data) {

		mData = data;

	}

	@Override
	public View setInflater(LayoutInflater inflater) {

		mRoot = inflater.inflate(getLayoutId(), null);
		return mRoot;
	}

	@Override
	public void setOnClickItemListener(OnClickItemListener itemListener) {
		this.itemListener = itemListener;
	}

	public void setOnClickListener(OnClickListener l) {
		if (mRoot != null)
			mRoot.setOnClickListener(l);
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
}
