package com.stupid.method.adapter;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/**
 * 
 * @说明:
 * 
 *      当absListview滚动时向IXViewholder发送滚动事件
 * 
 * @author: comcp@126.com
 * 
 * @version: v1
 * 
 * @创建时间：2015-9-11
 * **/
class IPauseOnScroll
// extends android.support.v7.widget.RecyclerView.OnScrollListener
		implements OnScrollListener {
	interface IXPauseListener {
		void onResume();

		void onPause();
	}

	IXPauseListener adapter;
	OnScrollListener listener;

	// private android.support.v7.widget.RecyclerView.OnScrollListener
	// listener2;

	public IPauseOnScroll(IXPauseListener adapter, OnScrollListener listener) {
		this.adapter = adapter;
		this.listener = listener;
	}

	// public IPauseOnScroll(IXPauseListener adapter,
	// android.support.v7.widget.RecyclerView.OnScrollListener listener) {
	// this.adapter = adapter;
	// this.listener2 = listener;
	// }

	// @Override
	// public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
	//
	// super.onScrolled(recyclerView, dx, dy);
	// if (listener2 != null)
	// listener2.onScrolled(recyclerView, dx, dy);
	// }

	// @Override
	// public void onScrollStateChanged(RecyclerView recyclerView, int newState)
	// {
	// super.onScrollStateChanged(recyclerView, newState);
	// switch (newState) {
	// case OnScrollListener.SCROLL_STATE_IDLE:
	// adapter.onResume();
	// break;
	// case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
	// // adapter.pause();
	// adapter.onResume();
	// break;
	// case OnScrollListener.SCROLL_STATE_FLING:
	// adapter.onPause();
	// break;
	// }
	// if (listener2 != null)
	// listener2.onScrollStateChanged(recyclerView, newState);
	//
	// }

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (listener != null)
			listener.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount);

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_IDLE:
			adapter.onResume();
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			// adapter.pause();
			adapter.onResume();
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			adapter.onPause();
			break;
		}
		if (listener != null)
			listener.onScrollStateChanged(view, scrollState);
	}

}
