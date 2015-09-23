package com.stupid.method.demo.holder;

import android.content.Context;
import android.widget.TextView;

import com.stupid.method.adapter.XAdapter;
import com.stupid.method.adapter.XViewHolder;
import com.stupid.method.demo.R;
import com.stupid.method.demo.bean.Joke;

public class JokeViewHolder extends XViewHolder<Joke> {
	TextView tv1, tv2;

	public void onCreate(XAdapter adapter, Context context) {

		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
	}

	@Override
	public int getLayoutId() {
		return R.layout.joke_view_holder;
	}

	@Override
	public void onResetView(Joke data, int position) {
		if (onScrolling) {
			tv1.setText("¹ö¶¯ÖÐ....");
			tv2.setText("Scrolling...");
		} else {
			tv1.setText(data.getTitle());
			tv2.setText(data.getContent());
		}
	}

}
