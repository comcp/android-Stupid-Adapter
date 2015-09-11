package com.stupid.method.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * * @调用顺序 <br>
 * 
 * 1. {@link #setInflater(LayoutInflater)}<br>
 * 2. {@link #getLayoutId()}<br>
 * 3. {@link #onCreat(XAdapter, android.content.Context)}<br>
 * 4. 如果已实例化该类,会先调用onDestiny();<br>
 * 5. {@link #getView(Object, int)}<br>
 * ----------- <br>
 * 6. {@link #onResetView(Object, int)} <br>
 * [1,2,3] 只执行一遍<br>
 * [4,5,6]会重复执行
 * 
 * **/
public interface IXViewHolder {
	int getLayoutId();

	View getView();

	View getView(Object data, int position, boolean onScrolling);

	void onCreat(XAdapter adapter, Context context);

	void onDestory(int nextPosition, int count);

	View setInflater(LayoutInflater inflater);

	void setOnClickItemListener(OnClickItemListener itemListener);

	void setOnLongClickItemListener(
			OnLongClickItemListener longClickItemListener);

}
