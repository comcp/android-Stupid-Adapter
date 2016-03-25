package com.stupid.method.demo.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.stupid.method.adapter.XViewHolder;
import com.stupid.method.demo.R;
import com.stupid.method.demo.bean.MsgModel;

public class MsgViewHodler extends XViewHolder<MsgModel> {
	ViewStub viewStub_send, viewStub_receive;
	TextView system_msg, sen_messg, rec_messg;

	@Override
	public void onCreate(Context c) {
		viewStub_send = (ViewStub) findViewById(R.id.viewStub_send);
		viewStub_receive = (ViewStub) findViewById(R.id.viewStub_receive);
		system_msg = (TextView) findViewById(R.id.system_msg);
		getView().setOnClickListener(this);

	}

	@Override
	public int getLayoutId() {
		return R.layout.msg_view_hodler;
	}

	@Override
	public void onResetView(MsgModel data, int p) {
		viewStub_send.setVisibility(View.GONE);
		viewStub_receive.setVisibility(View.GONE);
		system_msg.setVisibility(View.GONE);

		switch (data.getType()) {
		case MsgModel.type_receive:
			viewStub_send.setVisibility(View.VISIBLE);
			getSen_messg().setText(data.getMsg());
			break;
		case MsgModel.type_sender:
			viewStub_receive.setVisibility(View.VISIBLE);

			getRec_messg().setText(data.getMsg());
			break;
		case MsgModel.type_system:
			system_msg.setVisibility(View.VISIBLE);
			getSystem_msg().setText(data.getMsg());
			break;

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
		if (sen_messg == null)
			sen_messg = (TextView) findViewById(R.id.sen_messg);
		return sen_messg;
	}

	public TextView getRec_messg() {
		if (rec_messg == null)
			rec_messg = (TextView) findViewById(R.id.rec_messg);
		return rec_messg;
	}

}
