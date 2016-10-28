package com.stupid.method.demo.activity;

import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.stupid.method.demo.R;

public class MainActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.Expandedlistview:
			startActivity(new Intent(this, ExpandListViewActivity.class));
			break;
		case R.id.bton_linnrlayout:
			startActivity(new Intent(this, RecylerActivity.class));
			break;
		case R.id.bton_listview:
			startActivity(new Intent(this, ListDemoActivity.class));
			break;
		case R.id.bton_gridview:
			startActivity(new Intent(this, ListDemoActivity.class).putExtra(
					ListDemoActivity.LIST_TYPE_INT,
					ListDemoActivity.type_grid_view));

			break;
		case R.id.button1:
			startActivity(new Intent(this, ListActivity.class));

		default:
			break;
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("quit_time", new Date().toGMTString());
		super.onSaveInstanceState(outState);
	}

}
