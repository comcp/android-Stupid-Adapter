package com.stupid.method.demo.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;
import com.stupid.method.adapter.XViewHolder;
import com.stupid.method.demo.R;
import com.stupid.method.demo.bean.MsgModel;

public class MsgViewHodler extends XViewHolder<MsgModel> {
	ViewStub viewStub_send, viewStub_receive;
	TextView system_msg, sen_messg, rec_messg, textView1;
	private ImageView sen_headImg;
	ImageView rec_headImg;
	AQuery $, $2;
	static final ImageOptions options = new ImageOptions();
	static {
		options.setAnchor(1).setRound(10).setRatio(1);

	}

	@Override
	public void onCreate(Context c) {
		viewStub_send = (ViewStub) findViewById(R.id.viewStub_send);
		viewStub_receive = (ViewStub) findViewById(R.id.viewStub_receive);
		system_msg = (TextView) findViewById(R.id.system_msg);
		textView1 = (TextView) findViewById(R.id.textView1);
		getView().setOnClickListener(this);
		// 如果需要点击事件,需要在这里设置点击事件为this
		// 如果一个item里有多个地方需要点击事件,则以此给
	}

	@Override
	public int getLayoutId() {
		return R.layout.msg_view_hodler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stupid.method.adapter.XViewHolder#onResetView(java.lang.Object,
	 * int)
	 */
	@Override
	public void onResetView(MsgModel data, int p) {
		viewStub_send.setVisibility(View.GONE);
		viewStub_receive.setVisibility(View.GONE);
		system_msg.setVisibility(View.GONE);
		textView1.setVisibility(View.GONE);

		switch (data.getType()) {
		case MsgModel.type_receive:
			viewStub_receive.setVisibility(View.VISIBLE);
			setRecHeadImg(data.getHeadUrl());
			getRec_messg().setText(data.getMsg());
			break;
		case MsgModel.type_sender:
			viewStub_send.setVisibility(View.VISIBLE);
			setSenHeadImg(data.getHeadUrl());
			getSen_messg().setText(data.getMsg());
			break;
		case MsgModel.type_system:
			system_msg.setVisibility(View.VISIBLE);
			getSystem_msg().setText(data.getMsg());
			break;
		case MsgModel.type_news:
			textView1.setText(data.getMsg() + getEntityClass());
			textView1.setVisibility(View.VISIBLE);
		default:
			break;
		}
	}

	public TextView getSystem_msg() {

		if (system_msg == null)
			system_msg = (TextView) findViewById(R.id.system_msg);
		return system_msg;
	}

	public TextView getSen_messg() {
		if (sen_messg == null) {
			sen_messg = (TextView) findViewById(R.id.sen_messg);
			sen_messg.setOnClickListener(this);// 将当前view的点击事件抛给父类处理,父类会抛给声明adapter的类进行处理
		}
		return sen_messg;
	}

	public TextView getRec_messg() {
		if (rec_messg == null) {
			rec_messg = (TextView) findViewById(R.id.rec_messg);
			rec_messg.setOnClickListener(this);
		}
		return rec_messg;
	}

	public void setSenHeadImg(String url) {
		if (sen_headImg == null) {
			sen_headImg = (ImageView) findViewById(R.id.sen_headImg);
			$ = new AQuery(sen_headImg);
		}
		$.image(url, options);
	}

	public void setRecHeadImg(String url) {
		if (rec_headImg == null) {
			rec_headImg = (ImageView) findViewById(R.id.rec_headImg);
			rec_headImg.setOnClickListener(this);
			$2 = new AQuery(rec_headImg);
		}
		$2.image(url, options);
	}
}
