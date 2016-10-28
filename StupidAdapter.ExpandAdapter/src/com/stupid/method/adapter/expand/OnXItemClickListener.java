package com.stupid.method.adapter.expand;

import com.stupid.method.adapter.OnClickItemListener;

import android.view.View;

public interface OnXItemClickListener extends OnClickItemListener {

	public void onClickChild(View view, int parentId, int childPosition);

}