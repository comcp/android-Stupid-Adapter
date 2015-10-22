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
final class IPauseOnScroll implements OnScrollListener {
	XAdapter adapter;
	OnScrollListener listener;

	public IPauseOnScroll(XAdapter adapter, OnScrollListener listener) {
		this.adapter = adapter;
		this.listener = listener;

	}

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
			adapter.resume();
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			// adapter.pause();
			adapter.resume();
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			adapter.pause();
			break;
		}
		if (listener != null)
			listener.onScrollStateChanged(view, scrollState);
	}

}
