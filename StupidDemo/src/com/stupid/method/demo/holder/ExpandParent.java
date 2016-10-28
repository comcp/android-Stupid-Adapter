package com.stupid.method.demo.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.stupid.method.adapter.expand.XExpadnViewHolder;

public class ExpandParent extends XExpadnViewHolder<String> {
	TextView tv;

	@Override
	public View getView() {
		if (super.getView() == null)
			return tv = new TextView(context);
		else
			return super.getView();
	}

	@Override
	public void onCreate(Context arg0) {

	}

	@Override
	protected void onNodeIsChild(boolean arg0, int arg1) {

	}

	@Override
	protected void onNodeIsParent(boolean arg0, int arg1) {

	}

	@Override
	public int getLayoutId() {
		return 0;
	}

	@Override
	public void onResetView(String arg0, int arg1) {
		tv.setText(arg0);
	}

}
