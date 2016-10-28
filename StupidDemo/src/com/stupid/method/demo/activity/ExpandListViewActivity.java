package com.stupid.method.demo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.stupid.method.adapter.expand.XExpadnAdapter;
import com.stupid.method.demo.R;
import com.stupid.method.demo.holder.ExpandChild;
import com.stupid.method.demo.holder.ExpandParent;

public class ExpandListViewActivity extends Activity {
	ExpandableListView expandableListView1;
	XExpadnAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expand_list_view_activity);
		expandableListView1 = (ExpandableListView) findViewById(R.id.expandableListView1);
		adapter = new XExpadnAdapter<String, String>(this, ExpandParent.class,
				ExpandChild.class);
		expandableListView1.setAdapter(adapter);

		Map<String, List<String>> data = new HashMap<String, List<String>>();

		for (int i = 0; i < 10; i++) {
			List<String> list;
			data.put("parent_" + i, list = new ArrayList<String>());
			for (int j = 0, s = 10 + i; j < s; j++) {

				list.add("child_" + i + "_" + j);
			}

		}
		adapter.addAll(data);
		adapter.notifyDataSetChanged();
	}
}
