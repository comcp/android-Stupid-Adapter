package com.stupid.method.demo.holder;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;
import com.stupid.method.adapter.XAdapter;
import com.stupid.method.adapter.XViewHolder;
import com.stupid.method.demo.R;
import com.stupid.method.demo.bean.Vlist;

public class VlistViewHolder extends XViewHolder<Vlist> {
	public static int type = R.layout.vlist_view_holder;
	ImageView imageView1;
	TextView vt, title;
	AQuery q;
	static ImageOptions options = new ImageOptions();
	static {
		options.setAnchor(1).setRound(10).setRatio(1);

	}

	@Override
	public void onCreate(XAdapter<Vlist> adapter, Context context) {

		title = (TextView) findViewById(R.id.title);
		vt = (TextView) findViewById(R.id.vt);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		q = new AQuery(imageView1);

	}

	@Override
	public int getLayoutId() {

		return type;
	}

	@Override
	public void onResetView(Vlist data, int position) {
		if (isOnScrolling()) {
			q.image(R.drawable.ic_launcher);
		} else {

			q.image(data.getVpic(), options);
		}
		vt.setText(data.getVt());
		title.setText(data.getShortTitle());

	}

}
