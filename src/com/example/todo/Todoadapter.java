package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Todoadapter extends ArrayAdapter<Kategori> {
	private List<Kategori> mTodos = new ArrayList<Kategori>();

	public Todoadapter(Context context, int textViewResourceId,
			List<Kategori> objects) {
		super(context, textViewResourceId, objects);
		mTodos = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final LayoutInflater mInflater = LayoutInflater.from(getContext());
		TodoListRowHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.todorow, parent, false);
			holder = new TodoListRowHolder();
			holder.mTodoTitle = (TextView) convertView
					.findViewById(R.id.todoTitleTV);
			convertView.setTag(holder);

		} else {
			holder = (TodoListRowHolder) convertView.getTag();
		}

		holder.mTodoTitle.setText(mTodos.get(position).getName());

		return convertView;
	}

	@Override
	public int getCount() {
		return this.mTodos.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public Kategori getItem(int position) {
		return this.mTodos.get(position);
	}

	/**
	 * This is a class to hold our row view
	 */
	static class TodoListRowHolder {
		TextView mTodoTitle;
	}

}
