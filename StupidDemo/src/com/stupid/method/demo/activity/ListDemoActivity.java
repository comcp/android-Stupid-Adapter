package com.stupid.method.demo.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.stupid.method.adapter.IXDataListener;
import com.stupid.method.adapter.OnClickItemListener;
import com.stupid.method.adapter.OnLongClickItemListener;
import com.stupid.method.adapter.XAdapter2;
import com.stupid.method.demo.R;
import com.stupid.method.demo.bean.IqiyiRoot;
import com.stupid.method.demo.bean.Vlist;
import com.stupid.method.demo.holder.VlistViewHolder;
import com.stupid.method.demo.util.JsonUtils;

public class ListDemoActivity extends BaseActivity implements
		OnClickItemListener, OnLongClickItemListener, OnRefreshListener {

	public static final String LIST_TYPE_INT = "LIST_TYPE_INT";
	public static final int type_list_view = 1;
	public static final int type_grid_view = 2;
	AbsListView listView;
	public int type = 0;
	XAdapter2<Vlist> adapter;
	android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent() != null)
			type = getIntent().getIntExtra(LIST_TYPE_INT, 0);
		switch (type) {

		case type_grid_view:
			setContentView(R.layout.activity_grid_demo);
			VlistViewHolder.type = R.layout.vlist_view_holder2;
			break;
		default:
		case type_list_view:
			setContentView(R.layout.activity_list_demo);
			VlistViewHolder.type = R.layout.vlist_view_holder;
		}
		adapter = new XAdapter2<Vlist>(this, null, VlistViewHolder.class);
		adapter.setClickItemListener(this);// 设置item的点击事件;
		adapter.setLongClickItemListener(this);// 设置item的长按事件;
		adapter.setOnDataChang(new IXDataListener() {

			TextView textView = new TextView(getBaseContext());
			{
				textView.setGravity(Gravity.CENTER);
				textView.setText("没有数据");
				ViewGroup v = (ViewGroup) findViewById(getLayoutId());
				v.addView(textView, 0);
			}

			@Override
			public void onDataEmpty() {

				textView.setVisibility(View.VISIBLE);

			}

			@Override
			public void onDataChange() {
				if (textView.getVisibility() == View.VISIBLE)
					textView.setVisibility(View.GONE);
			}
		});

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

		IqiyiRoot root = JsonUtils.parseObject(
				data.replace("var tvInfoJs=", ""), IqiyiRoot.class);
		if (null == root) {
			showToast("服务器异常");

		}

		// Jokes jokes = JsonUtils.parseObject(data, Jokes.class);
		// if (null == jokes) {
		// showToast("服务器数据异常");
		// return;
		// }
		adapter.addAll(root.getData().getVlist());
		adapter.notifyDataSetInvalidated();
	}

	public void onClickItem(View v, int p) {

		showToast("点击:" + p);

	}

	public boolean onLongClickItem(View v, int p) {
		showToast("长按:" + p);
		return true;
	}

	public void onRefresh() {
		getHttp().get(50, "http://cache.video.iqiyi.com/jp/avlist/202861101/",
				this);

	}

}
