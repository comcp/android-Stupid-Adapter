package com.stupid.method.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stupid.method.adapter.IPauseOnScroll.IXPauseListener;

public class XRecylerAdapter<T> extends Adapter<ViewHolder> implements
		Collection<T>, IXPauseListener {
	private static final String tag = "XRecylerAdapter";
	private List<T> mData;
	Class<? extends XViewHolder<T>> cls;
	private OnClickItemListener clickItemListener;
	private boolean onScrolling = false;
	private IXDataListener onDataChang;

	public IXDataListener getOnDataChang() {
		return onDataChang;
	}

	{
		registerAdapterDataObserver(new AdapterDataObserver() {
			@Override
			public void onChanged() {
				super.onChanged();
				onDataChange();
			}
		});
	}

	private void onDataChange() {
		if (getOnDataChang() != null)
			if (getItemCount() == 0)
				getOnDataChang().onDataEmpty();
			else
				getOnDataChang().onDataChange();
	}

	public void add(int location, T object) {
		mData.add(location, object);
	}

	public boolean add(T object) {
		return mData.add(object);
	}

	public boolean addAll(int location, Collection<? extends T> collection) {
		return mData.addAll(location, collection);
	}

	public boolean addAll(Collection<? extends T> collection) {
		return mData.addAll(collection);
	}

	public void clear() {
		mData.clear();
	}

	public boolean contains(Object object) {
		return mData.contains(object);
	}

	public boolean containsAll(Collection<?> collection) {
		return mData.containsAll(collection);
	}

	public boolean equals(Object object) {
		return mData.equals(object);
	}

	public T get(int location) {
		return mData.get(location);
	}

	public int hashCode() {
		return mData.hashCode();
	}

	public int indexOf(Object object) {
		return mData.indexOf(object);
	}

	public boolean isEmpty() {
		return mData.isEmpty();
	}

	public Iterator<T> iterator() {
		return mData.iterator();
	}

	public int lastIndexOf(Object object) {
		return mData.lastIndexOf(object);
	}

	public ListIterator<T> listIterator() {
		return mData.listIterator();
	}

	public ListIterator<T> listIterator(int location) {
		return mData.listIterator(location);
	}

	public T remove(int location) {
		return mData.remove(location);
	}

	public boolean remove(Object object) {
		return mData.remove(object);
	}

	public boolean removeAll(Collection<?> collection) {
		return mData.removeAll(collection);
	}

	public boolean retainAll(Collection<?> collection) {
		return mData.retainAll(collection);
	}

	public T set(int location, T object) {
		return mData.set(location, object);
	}

	public int size() {
		return mData.size();
	}

	public List<T> subList(int start, int end) {
		return mData.subList(start, end);
	}

	public Object[] toArray() {
		return mData.toArray();
	}

	public <T> T[] toArray(T[] array) {
		return mData.toArray(array);
	}

	private OnLongClickItemListener longClickItemListener;

	public XRecylerAdapter(Class<? extends XViewHolder<T>> clz, List<T> datas) {
		cls = clz;
		this.mData = datas;
		if (this.mData == null) {
			this.mData = new ArrayList<T>();
		}

	}

	@Override
	public int getItemCount() {

		return mData == null ? 0 : mData.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder vh, int position) {
		XViewHolder<T> holder = null;
		if (vh.itemView.getTag() instanceof XViewHolder) {
			holder = (XViewHolder<T>) vh.itemView.getTag();
			holder.onDestory(position, getItemCount());
			holder.setView(vh.itemView).getView(mData.get(position), position,
					isOnScrolling());
		}

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup v, int p) {

		return creatViewHolder(v);

	}

	public IPauseOnScroll getOnScrollListener(OnScrollListener l) {
		return new IPauseOnScroll(this, l);
	}

	ViewHolder creatViewHolder(ViewGroup v) {

		XViewHolder<T> holder = null;

		if (cls != null) {

			try {
				holder = (XViewHolder<T>) cls.newInstance();
				holder.setInflater(LayoutInflater.from(v.getContext()));
				holder.onCreate(v.getContext());
				holder.setOnClickItemListener(getClickItemListener());
				holder.setOnLongClickItemListener(getLongClickItemListener());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (holder != null)
				return holder.getViewHolder();

		} else {
			Log.e(tag, "没有设置 IXViewHolder ");
		}
		return null;

	}

	public OnLongClickItemListener getLongClickItemListener() {
		return longClickItemListener;
	}

	public void setLongClickItemListener(
			OnLongClickItemListener longClickItemListener) {
		this.longClickItemListener = longClickItemListener;
	}

	public OnClickItemListener getClickItemListener() {
		return clickItemListener;
	}

	public void setClickItemListener(OnClickItemListener clickItemListener) {
		this.clickItemListener = clickItemListener;
	}

	public void onPause() {
		setOnScrolling(true);
	}

	public void onResume() {
		if (isOnScrolling()) {// 如果已经滚动停止,就别刷新列表了
			setOnScrolling(false);
			notifyDataSetChanged();

		}

	}

	public boolean isOnScrolling() {
		return onScrolling;
	}

	public void setOnScrolling(boolean onScrolling) {
		this.onScrolling = onScrolling;
	}

	public void setOnDataChang(IXDataListener onDataChang) {
		this.onDataChang = onDataChang;
	}

}
