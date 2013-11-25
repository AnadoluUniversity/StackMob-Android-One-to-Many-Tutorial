package com.example.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_List_Tasks extends Activity {
	private Menu optionsMenu;
	private Todoadaptertasks mTodoAdapter;
	private final Context thisContext = this;
	List<Tasks> mTodos = new ArrayList<Tasks>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.list_task);
		Intent i= getIntent();
		//get and set category name as title
		String title=i.getExtras().getString("name");
		setTitle(title);


		final ListView btnOpenPopup = (ListView) findViewById(R.id.myListViewTask);
		btnOpenPopup
			.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//deletes item on long click
			@Override
			public boolean onItemLongClick(final AdapterView<?> parent,
					final View view, final int position, long id) {
				// TODO Auto-generated method stub
//returns clicked item
				final Tasks item = (Tasks) parent
						.getItemAtPosition(position);

			//removes item from tasks table
				item.destroy();
				Intent i = getIntent();
				String mert = i.getExtras().getString("id");
	
				final Kategori mytask = new Kategori();
				mytask.setID(mert);
				mytask.fetch(new StackMobModelCallback() {
					//removes item from "cocuk" field inside Category table
					@Override
					public void success() {
						mytask.getTasks().remove(position);
						mytask.save(StackMobOptions.depthOf(1));
						for(int i=0;i<2;i++)
						fetcher();
						// the call succeeded
					}

					@Override
					public void failure(StackMobException e) {
						// the call failed
					}
				});


				return true;
			}
		});

		for(int z=0;z<2;z++)
			fetcher();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		this.optionsMenu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.app_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.input_plus:
			Intent intentMain = new Intent(Activity_List_Tasks.this,
					Activity_Add_Task.class);
			Intent i = getIntent();
			String mert = i.getExtras().getString("id");
			String mert2 = i.getExtras().getString("name");
			intentMain.putExtra("idd", mert);
			intentMain.putExtra("namee", mert2);
			Activity_List_Tasks.this.startActivity(intentMain);

		case R.id.ic_delete:

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onResume() {
		super.onResume();

		fetcher();
	}
	private void fetcher() {
		final ListView myListView1 = (ListView) findViewById(R.id.myListViewTask);

		Intent i = getIntent();
		String mert = i.getExtras().getString("id");
		String title=i.getExtras().getString("name");

		Tasks.query(Tasks.class, new StackMobQuery().fieldIsEqualTo("owner", title),
				StackMobOptions.depthOf(1),
				new StackMobQueryCallback<Tasks>() {
					@Override
					public void success(List<Tasks> result) {
						// You've now got a list of the tasks

						mTodos = result;

						mTodoAdapter = new Todoadaptertasks(thisContext,
								android.R.layout.simple_list_item_1, mTodos);

						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								myListView1.setAdapter(mTodoAdapter);

							}

						});

					}

					@Override
					public void failure(StackMobException e) {

					}
				});
	}
}
