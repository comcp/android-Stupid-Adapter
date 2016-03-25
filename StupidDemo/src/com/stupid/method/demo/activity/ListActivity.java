package com.stupid.method.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stupid.method.adapter.OnClickItemListener;
import com.stupid.method.adapter.XAdapter2;
import com.stupid.method.demo.R;
import com.stupid.method.demo.bean.MsgModel;
import com.stupid.method.demo.holder.MsgViewHodler;

public class ListActivity extends Activity {
	ListView listView1;

	XAdapter2<MsgModel> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		listView1 = (ListView) findViewById(R.id.listView1);
		setTitle("模拟微信聊天记录");
		adapter = new XAdapter2<MsgModel>(this, MsgViewHodler.class);

		String[] data = new String[] { "你拍一，我拍一，一个小孩坐飞机", "你拍二，我拍二，两个小孩丢手绢",
				"你拍三，我拍三，三个小孩来搬砖", "你拍四，我拍四，四个小孩写大字", "你拍五，我拍五，五个小孩敲锣鼓",
				"你拍六，我拍六，六个小孩拣豆豆", "你拍七，我拍七，七个小孩穿新衣", "你拍八，我拍八，八个小孩吃西瓜",
				"你拍九，我拍九，九个小孩齐步走", "你拍十，我拍十，十个小孩在学习" };
		for (String string : data) {

			adapter.add(MsgModel.newInstance(0, 1, string));
			adapter.add(MsgModel.newInstance(0, 2, string));

		}
		TextView head = new TextView(this);
		head.setText("listview头");
		listView1.addHeaderView(head);
		listView1.setAdapter(adapter);
		adapter.setClickItemListener(new OnClickItemListener() {

			@Override
			public void onClickItem(View v, int position) {
				Toast.makeText(getBaseContext(), "p" + position,
						Toast.LENGTH_SHORT).show();

			}
		});
	}

}
