package com.stupid.method.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;

abstract public class XRecyclerViewHolder<T> extends XViewHolder<T> {
	ViewHolder v7viewHolder;

	public ViewHolder getViewHolder() {
		setTag(this);
		if (v7viewHolder == null)
			v7viewHolder = new ViewHolder(getView()) {
			};
		return v7viewHolder;
	}

}
