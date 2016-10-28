package com.stupid.method.adapter.expand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.stupid.method.adapter.OnClickItemListener;
import com.stupid.method.adapter.XAdapter2;

public class XExpadnAdapter<ParentModel, ChildModel> extends
		BaseExpandableListAdapter {

	private OnXItemClickListener onItemClickListener;

	final Map<ParentModel, XAdapter2<ChildModel>> mMap;
	final XAdapter2<ParentModel> parentAdapter;

	final Class<? extends XExpadnViewHolder<ParentModel>> parentViewClass;
	final Class<? extends XExpadnViewHolder<ChildModel>> childViewClass;
	final Context mContext;

	private class OnClickItemListenerImpl implements OnClickItemListener {
		private int parentId;

		@Override
		public void onClickItem(View view, int childPosition) {
			if (onItemClickListener != null) {
				onItemClickListener.onClickChild(view, getParentId(),
						childPosition);
			}
		}

		public OnClickItemListenerImpl(int pid) {
			this.parentId = pid;
		}

		public int getParentId() {
			return parentId;
		}

	}

	public XExpadnAdapter(Context context,
			Class<? extends XExpadnViewHolder<ParentModel>> parentViewClass,
			Class<? extends XExpadnViewHolder<ChildModel>> childViewClass) {
		this(context, parentViewClass, childViewClass, null);
	}

	public XExpadnAdapter(Context context,
			Class<? extends XExpadnViewHolder<ParentModel>> parentViewClass,
			Class<? extends XExpadnViewHolder<ChildModel>> childViewClass,
			Map<ParentModel, List<ChildModel>> data) {
		mContext = context;
		this.parentViewClass = parentViewClass;
		this.childViewClass = childViewClass;
		mMap = new HashMap<ParentModel, XAdapter2<ChildModel>>();
		parentAdapter = new XAdapter2<ParentModel>(context, parentViewClass);
		addAll(data);
	}

	public void addAll(Map<ParentModel, List<ChildModel>> data) {

		if (data == null) {
			return;
		}
		Set<ParentModel> set = data.keySet();
		int i = 0;
		for (ParentModel p : set) {
			parentAdapter.add(p);
			XAdapter2<ChildModel> childaAdapter = mMap.get(p);
			if (childaAdapter == null) {
				childaAdapter = new XAdapter2<ChildModel>(mContext,
						data.get(p), childViewClass);
				mMap.put(p, childaAdapter);

			} else {
				childaAdapter.setData(data.get(p));
			}
			// childaAdapter.setClickItemListener(clickItemListener);
			i++;
		}
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {

		return mMap.get(parentAdapter.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		Log.d("XAdapter3", "getChildId:" + groupPosition);

		return mMap.get(parentAdapter.get(groupPosition)).hashCode();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		Log.d("XAdapter3", "getChildrenCount:" + groupPosition);
		return mMap == null ? 0 : (parentAdapter == null ? 0 : parentAdapter
				.get(groupPosition) == null ? 0 : mMap.get(parentAdapter
				.get(groupPosition)) == null ? 0 : mMap.get(
				parentAdapter.get(groupPosition)).size());
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mMap.get(parentAdapter.get(groupPosition)).getView(
					childPosition, convertView, parent);
		}
		if (convertView.getTag() instanceof XExpadnViewHolder) {
			XExpadnViewHolder<ChildModel> holder = (XExpadnViewHolder<ChildModel>) convertView
					.getTag();
			holder.onNodeIsChild(isLastChild, groupPosition);
			holder.setParentId(groupPosition);
			holder.setOnClickItemListener(new OnClickItemListenerImpl(
					groupPosition));
			convertView = holder.getView(
					mMap.get(parentAdapter.get(groupPosition)).getItem(
							childPosition), childPosition, false);
		}
		return convertView;
	}

	@Override
	public Object getGroup(int groupPosition) {

		return parentAdapter == null ? null : parentAdapter.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return parentAdapter == null ? 0 : parentAdapter.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return parentAdapter.get(groupPosition).hashCode();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		convertView = parentAdapter.getView(groupPosition, convertView, parent);
		if (convertView.getTag() instanceof XExpadnViewHolder) {
			XExpadnViewHolder holder = (XExpadnViewHolder) convertView.getTag();
			holder.onNodeIsParent(isExpanded,
					mMap.get(parentAdapter.get(groupPosition)).size());
		}
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public OnXItemClickListener getClickItemListener() {
		return onItemClickListener;
	}

	public void setClickItemListener(OnXItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
		parentAdapter.setClickItemListener(onItemClickListener);
	}

	public void clear() {

		mMap.clear();
		parentAdapter.clear();

	}

	public ParentModel getItem(int position) {

		return parentAdapter == null ? null : parentAdapter.getItem(position);
	}

	public int size() {
		return getGroupCount();
	}

}