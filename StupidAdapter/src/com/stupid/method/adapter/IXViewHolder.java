package com.stupid.method.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * * @调用顺序 <br>
 * 
 * 1. {@link #setInflater(LayoutInflater)}<br>
 * 2. {@link #getLayoutId()}<br>
 * 3. {@link #onCreate(XAdapter, android.content.Context)}<br>
 * 4. 如果已实例化该类,会先调用onDestiny();<br>
 * 5. {@link #getView(Object, int)}<br>
 * ----------- <br>
 * 6. {@link #onResetView(Object, int)} <br>
 * [1,2,3] 只执行一遍<br>
 * [4,5,6]会重复执行
 * 
 * **/
public interface IXViewHolder<T> {
	int getLayoutId();

	/**
	 * @return
	 */
	View getView();

	/**
	 * @param data
	 * @param position
	 * @param onScrolling
	 * @return
	 */
	View getView(T data, int position, boolean onScrolling);

	/**
	 * @param context
	 */
	void onCreate(Context context);

	/**
	 * @param nextPosition
	 * @param count
	 */
	void onDestory(int nextPosition, int count);

	/**
	 * 当前容器的数据长度
	 * 
	 * @param size
	 */
	void setListSize(int size);

	/**
	 * @param inflater
	 * @param parent
	 * @return
	 */
	View setInflater(LayoutInflater inflater, ViewGroup parent);

	/**
	 * @param itemListener
	 */
	void setOnClickItemListener(OnClickItemListener itemListener);

	/**
	 * @param longClickItemListener
	 */
	void setOnLongClickItemListener(
			OnLongClickItemListener longClickItemListener);

}
