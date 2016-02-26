package com.stupid.method.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @说明 懒惰使人更进一步
 * 
 *     使用反射做adapter<br>
 *     使用的时候只需要写一个ViewHolder 继承自{@link XViewHolder}<br>
 *     当@link
 *     {@link XAdapter2#convertView(int, View, ViewGroup, ArrayList, LayoutInflater)}
 *     时对viewholder进行实例化. 初始化view
 * 
 * @author comcp@126.com
 * 
 * @version v1.6
 * 
 * @创建时间：2015-2-3
 * **/
public class XAdapter2<T> extends XAdapter<T> implements IXAdapter<T> {
	private static final String tag = "XAdapter2";
	private OnClickItemListener clickItemListener;
	private OnLongClickItemListener longClickItemListener;
	private Class<T> viewBean;

	public XAdapter2(Context context, List<T> mData,
			Class<? extends IXViewHolder<T>> xViewHolder) {
		super(context, mData, null);
		super.setAdapterInterface(this);

		try {
			this.viewBean = (Class<T>) Class.forName(xViewHolder.getName());
		} catch (ClassNotFoundException e) {
			this.viewBean = null;
			e.printStackTrace();

		}

	}

	@Override
	public View convertView(int position, View convertView, ViewGroup parent,
			List<T> mData, LayoutInflater inflater) {
		IXViewHolder holder = null;

		if (convertView == null) {

			if (viewBean != null) {

				try {
					holder = (IXViewHolder) viewBean.newInstance();
					holder.setInflater(inflater);
					holder.onCreate(context);
					holder.setOnClickItemListener(this.clickItemListener);
					holder.setOnLongClickItemListener(longClickItemListener);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			} else {
				Log.e(tag, "没有设置 IXViewHolder ");
				return super.getView(position, convertView, parent);
			}

		} else {
			holder = (IXViewHolder) convertView.getTag();
			holder.onDestory(position, getCount());
		}
		if (holder != null)
			return holder.getView(mData.get(position), position,
					isOnScrolling());
		else
			return super.getView(position, convertView, parent);
	}

	public OnClickItemListener getClickItemListener() {
		return clickItemListener;
	}

	public OnLongClickItemListener getLongClickItemListener() {
		return longClickItemListener;
	}

	public void setClickItemListener(OnClickItemListener clickItemListener) {
		this.clickItemListener = clickItemListener;
	}

	public void setLongClickItemListener(
			OnLongClickItemListener longClickItemListener) {
		this.longClickItemListener = longClickItemListener;
	}
}