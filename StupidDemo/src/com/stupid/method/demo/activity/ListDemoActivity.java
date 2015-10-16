package com.stupid.method.demo.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.widget.AbsListView;

import com.alibaba.fastjson.JSON;
import com.stupid.method.adapter.OnClickItemListener;
import com.stupid.method.adapter.OnLongClickItemListener;
import com.stupid.method.adapter.XAdapter2;
import com.stupid.method.demo.R;
import com.stupid.method.demo.bean.Joke;
import com.stupid.method.demo.bean.Joke.Jokes;
import com.stupid.method.demo.holder.JokeViewHolder;

public class ListDemoActivity extends BaseActivity implements
		OnClickItemListener, OnLongClickItemListener, OnRefreshListener {

	public static final String LIST_TYPE_INT = "LIST_TYPE_INT";
	public static final int type_list_view = 1;
	public static final int type_grid_view = 2;
	AbsListView listView;
	public int type = 0;
	XAdapter2<Joke> adapter;
	android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent() != null)
			type = getIntent().getIntExtra(LIST_TYPE_INT, 0);
		switch (type) {

		case type_grid_view:
			setContentView(R.layout.activity_grid_demo);
			break;
		default:
		case type_list_view:
			setContentView(R.layout.activity_list_demo);
		}
		adapter = new XAdapter2<Joke>(this, null, JokeViewHolder.class);
		adapter.setClickItemListener(this);// 设置item的点击事件;
		adapter.setLongClickItemListener(this);// 设置item的长按事件;
		listView = (AbsListView) findViewById(R.id.bton_listview);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(adapter.getOnScrollListener(null));

		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swrefresh);
		swipeRefreshLayout.setOnRefreshListener(this);
		onRefresh();

	}

	@Override
	public void onServerResult(int resultCode, String data, boolean state,
			int statusCode) {
		super.onServerResult(resultCode, data, state, statusCode);
		swipeRefreshLayout.setRefreshing(false);
		Jokes jokes = JSON.parseObject(data, Jokes.class);
		adapter.addAll(jokes.getJokes());
		adapter.notifyDataSetChanged();
	}

	public int getLayoutId() {
		return 0;
	}

	public void onClickItem(View v, int p) {
		showToast("点击:" + p);
	}

	public boolean onLongClickItem(View v, int p) {
		showToast("长按:" + p);
		return true;
	}

	public void onRefresh() {
		getHttp().get(50, "http://xiaohua.hao.360.cn/m/itxt?page=1", this);

	}

}
