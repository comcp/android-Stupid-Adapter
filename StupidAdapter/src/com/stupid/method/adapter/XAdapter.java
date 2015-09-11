package com.stupid.method.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @说明 懒惰使人进步
 * 
 * @author comcp@126.com
 * 
 * @version v1.3
 * 
 * @创建时间：2014-12-3上午11:21:31
 * 
 */
public abstract class XAdapter<T> extends BaseAdapter {
	protected IXAdapter<T> adapterInterface;
	protected Context context;
	protected LayoutInflater inflater;
	protected List<T> mData;

	private boolean onScrolling = false;

	public XAdapter(Context context, List<T> mData,
			IXAdapter<T> adapterInterface) {
		this.mData = mData;
		if (this.mData == null) {
			this.mData = new ArrayList<T>();
		}
		this.adapterInterface = adapterInterface;
		this.context = context;
		inflater = LayoutInflater.from(context);

	}

	public XAdapter<T> add(int location, T object) {
		mData.add(location, object);
		return this;

	}

	public boolean add(T object) {
		if (mData != null)
			return mData.add(object);
		else
			return false;
	}

	public boolean addAll(Collection<? extends T> collection) {
		if (mData != null)
			return mData.addAll(collection);
		else
			return false;
	}

	public XAdapter<T> clear() {
		mData.clear();
		return this;
	}

	public IXAdapter<T> getAdapterInterface() {
		return adapterInterface;
	}

	@Override
	public int getCount() {

		return mData == null ? 0 : mData.size();
	}

	@Override
	public T getItem(int position) {

		return position > -1 ? mData == null ? null : mData.get(position)
				: null;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	public List<T> getmData() {
		return mData;
	}

	public IPauseOnScroll getOnScrollListener(OnScrollListener l) {
		return new IPauseOnScroll(this, l);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (adapterInterface != null)
			return adapterInterface.convertView(position, convertView, parent,
					mData, inflater);
		else {
			TextView tv = new TextView(context);
			tv.setPadding(10, 10, 10, 10);
			tv.setText("没有设置 ISuperAdapter");
			return tv;
		}
	}

	/**
	 * @return the onScrolling
	 */
	public boolean isOnScrolling() {
		return onScrolling;
	}

	public void pause() {
		setOnScrolling(true);
	}

	public T remove(int location) {
		if (mData != null)
			return mData.remove(location);
		else
			return null;
	}

	public boolean remove(Object object) {
		if (mData != null)
			return mData.remove(object);
		else
			return false;
	}

	public boolean removeAll(Collection<?> collection) {
		if (mData != null)
			return mData.removeAll(collection);
		else
			return false;
	}

	public void resume() {
		setOnScrolling(false);
		notifyDataSetChanged();

	}

	public void setAdapterInterface(IXAdapter<T> adapterInterface) {
		this.adapterInterface = adapterInterface;
	}

	public void setmData(List<T> mData) {
		this.mData = mData;
		this.notifyDataSetChanged();
	}

	/**
	 * @param onScrolling
	 *            the onScrolling to set
	 */
	public void setOnScrolling(boolean onScrolling) {
		this.onScrolling = onScrolling;
	}

	/***
	 * 更新整个列表 <br>
	 * 先删除列表内数据,再更新
	 * */
	public void upData(List<T> mData) {
		if (mData == null) {
			this.mData = mData;
		} else {
			this.mData.clear();
			this.mData.addAll(mData);

		}

		this.notifyDataSetChanged();

	}
}