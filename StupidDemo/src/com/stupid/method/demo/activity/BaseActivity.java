package com.stupid.method.demo.activity;

import com.stupid.method.app.XActivity;
import com.stupid.method.util.http.IXHttp;
import com.stupid.method.util.http.aquery.AQueryHttp;
import com.stupid.method.util.http.impl.XHttp;

public class BaseActivity extends XActivity {
	public static IXHttp http;

	@Override
	public int getLayoutId() {

		return android.R.id.content;
	}

	@Override
	public IXHttp getHttp() {
		if (http == null)
			http = new XHttp(new AQueryHttp(getApplicationContext()));
		return http;
	}

}
