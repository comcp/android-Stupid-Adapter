package com.stupid.method.adapter.expand;

import com.stupid.method.adapter.XViewHolder;

/**
 * @author wangx
 * 
 * @param <T>
 */
public abstract class XExpadnViewHolder<T> extends XViewHolder<T> {

	/**
	 * run in {@link XViewHolder#getView(Object, int, boolean)} after;
	 * 
	 * @param isLastChild
	 *            当前节点是否是最后一个节点
	 * @param parentPosition
	 *            父节点id
	 */
	protected abstract void onNodeIsChild(boolean isLastChild,
			int parentPosition);

	OnXItemClickListener onItemClickListener;
	private int parentId;

	/**
	 * run in {@link XViewHolder#getView(Object, int, boolean)} after;
	 * 
	 * @param isExpanded
	 *            当前节点是否展开
	 * @param childCount
	 *            子节点数量
	 */
	protected abstract void onNodeIsParent(boolean isExpanded, int childCount);

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
