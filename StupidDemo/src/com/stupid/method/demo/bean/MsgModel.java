package com.stupid.method.demo.bean;

import java.io.Serializable;

import android.support.annotation.IntDef;

public class MsgModel implements Serializable {

	public static final int type_sender = 1, type_receive = 2, type_system = 3;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@IntDef({ type_sender, type_receive, type_system })
	public @interface type {

	}

	int type;
	String msg;

	public int getType() {
		return type;
	}

	public void setType(@type int type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	int head;

	public static MsgModel newInstance(int head, int type, String msg) {
		MsgModel m = new MsgModel();
		m.setHead(head);
		m.setType(type);
		m.setMsg(msg);
		return m;

	}

}
