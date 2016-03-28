package com.stupid.method.demo.bean;

import java.io.Serializable;

import android.support.annotation.IntDef;

public class MsgModel implements Serializable {

	public static final int type_sender = 1, type_receive = 2, type_system = 3,
			type_news = 4;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@IntDef({ type_sender, type_receive, type_system, type_news })
	public @interface type {

	}

	int type;
	String msg;
	private String headUrl;

	public int getType() {
		return type;
	}

	public MsgModel setType(@type int type) {
		this.type = type;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public MsgModel setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public int getHead() {
		return head;
	}

	public MsgModel setHead(int head) {
		this.head = head;
		return this;
	}

	int head;

	public static MsgModel newInstance(int head, int type, String msg) {
		MsgModel m = new MsgModel();
		m.setHead(head);
		m.setType(type);
		m.setMsg(msg);
		return m;

	}

	public String getHeadUrl() {
		return headUrl;
	}

	public MsgModel setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
		return this;
	}

}
