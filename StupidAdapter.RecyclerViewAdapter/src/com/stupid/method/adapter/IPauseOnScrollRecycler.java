package com.stupid.method.adapter;

import com.stupid.method.adapter.IPauseOnScroll.IXPauseListener;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

public class IPauseOnScrollRecycler extends OnScrollListener {
	private OnScrollListener listener2;
	IXPauseListener adapter;

	public IPauseOnScrollRecycler(IXPauseListener adapter,
			OnScrollListener listener) {
		this.adapter = adapter;
		this.listener2 = listener;
	}

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

		super.onScrolled(recyclerView, dx, dy);
		if (listener2 != null)
			listener2.onScrolled(recyclerView, dx, dy);
	}

	@Override
	public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
		super.onScrollStateChanged(recyclerView, newState);
		switch (newState) {
		case android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
			adapter.onResume();
			break;
		case android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			// adapter.pause();
			adapter.onResume();
			break;
		case android.widget.AbsListView.OnScrollListener.SCROLL_STATE_FLING:
			adapter.onPause();
			break;
		}
		if (listener2 != null)
			listener2.onScrollStateChanged(recyclerView, newState);

	}
}
