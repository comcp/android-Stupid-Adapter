package com.stupid.method.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.stupid.method.app.XActivity;
import com.stupid.method.demo.R;

public class MainActivity extends XActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public int getLayoutId() {
		return 0;
	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bton_linnrlayout:
			break;
		case R.id.bton_listview:
			startActivity(new Intent(this, ListDemoActivity.class));
			break;
		case R.id.bton_gridview:
			startActivity(new Intent(this, ListDemoActivity.class).putExtra(
					ListDemoActivity.LIST_TYPE_INT,
					ListDemoActivity.type_grid_view));

			break;

		default:
			break;
		}
	}

}
