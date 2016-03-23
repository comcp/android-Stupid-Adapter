package com.stupid.method.demo.activity;

import android.app.Activity;
import android.widget.Toast;

import com.stupid.method.http.IXHttp;
import com.stupid.method.http.IXServerResultListener;
import com.stupid.method.http.aquery.AQueryHttp;
import com.stupid.method.http.impl.XHttp;

public class BaseActivity extends Activity implements IXServerResultListener {
	public static IXHttp http;

	public int getLayoutId() {

		return android.R.id.content;
	}

	public IXHttp getHttp() {
		if (http == null)
			http = new XHttp(new AQueryHttp(getApplicationContext()));
		return http;
	}

	@Override
	public void onServerResult(int arg0, String arg1, boolean arg2, int arg3) {

	}

	protected void showToast(CharSequence text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

	}

}
