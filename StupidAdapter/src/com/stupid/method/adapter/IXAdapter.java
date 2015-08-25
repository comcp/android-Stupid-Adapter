package com.stupid.method.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IXAdapter<T> {
	public View convertView(int position, View convertView, ViewGroup parent,
			List<T> mData, LayoutInflater inflater);

}
