package com.stupid.method.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

/**
 * @说明： 执行顺序 {@link XViewHolder#getLayoutId()}
 *      {@link XViewHolder#onCreat(XAdapter, android.content.Context)}
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
	protected View mRoot;
	protected LayoutInflater inflater;
	private OnClickItemListener itemListener;
	private OnLongClickItemListener longClickItemListener;
	protected T mData;
	private int position;

	@Override
	public abstract int getLayoutId();

	@Override
	public void setOnClickItemListener(OnClickItemListener itemListener) {
		this.itemListener = itemListener;
	}

	@Override
	public void setOnLongClickItemListener(
			OnLongClickItemListener longClickItemListener) {
		this.longClickItemListener = longClickItemListener;

	}

	public View getView(Object data, int position) {
		this.position = position;
		mRoot.setTag(this);
		onResetView((T) data, position);
		return mRoot;
	}

	public View getView() {
		return mRoot;
	}

	protected void setData(T data) {
		mData = data;

	}

	public abstract void onResetView(T data, int position);

	protected View findViewById(int id) {
		if (mRoot != null)
			return mRoot.findViewById(id);
		else
			return null;
	}

	@Override
	public View setInflater(LayoutInflater inflater) {

		mRoot = inflater.inflate(getLayoutId(), null);
		return mRoot;
	}

	@Override
	public void onClick(View v) {
		if (itemListener != null) {
			itemListener.onClickItem(v, position);
		}

	}

	@Override
	public boolean onLongClick(View v) {
		if (longClickItemListener != null) {
			return longClickItemListener.onLongClickItem(v, position);
		} else
			return false;
	}

	@Override
	public void onDestory(int nextPosition, int count) {

//		System.out.println(String.format("Now:%d  Next:%d  ", position,
//				nextPosition));
	}
}
