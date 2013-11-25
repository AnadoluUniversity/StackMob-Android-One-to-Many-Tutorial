package com.example.todo;

import com.stackmob.android.sdk.common.StackMobAndroid;

import android.R.integer;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;

import com.example.todo.Todoadapter;
import com.google.gson.Gson;

import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.stackmob.sdk.api.StackMob;
import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.api.StackMobQueryField;
import com.stackmob.sdk.callback.StackMobCallback;
import com.stackmob.sdk.callback.StackMobCountCallback;
import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;
import com.stackmob.sdk.model.StackMobModel;

public class MainActivity extends Activity {
	private Button but;
	private Button buttonPopup;
	private String bbb;
	public String mert;
	private Kategori todo;
	private Todoadapter mTodoAdapter;
	private final Context thisContext = this;
	public Integer myId = 1;
	private Menu optionsMenu;
	List<Kategori> mTodos = new ArrayList<Kategori>();
	List<Tasks> mto = new ArrayList<Tasks>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
// Added Stackmob Development Keys
		StackMobAndroid.init(getApplicationContext(), 0,
				"ffda4d44-ffd5-4009-b8b0-4576c49eec2d");

		setTitle("KATEGORÝLER");
		
		
// Delete long clicked item from Kategori table
		final ListView btnOpenPopup = (ListView) findViewById(R.id.myListView);
		btnOpenPopup
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(final AdapterView<?> parent,
							final View view, final int position, long id) {
						// TODO Auto-generated method stub
						
// Returns item from clicked position
						final Kategori item = (Kategori) parent
								.getItemAtPosition(position);
	//get ID and name of the long clicked item					
						final String myid = item.getID();						
						final String ownerName=item.getName();
						
						final Kategori taskk = new Kategori();
						taskk.setID(myid);
					
								//search for the clicked item's child objects
								Tasks.query(Tasks.class, new StackMobQuery().fieldIsEqualTo("owner",ownerName), new StackMobQueryCallback<Tasks>() {

									@Override
									public void failure(StackMobException res) {
										// TODO Auto-generated method stub
									
									}

									@Override
									public void success(List<Tasks> res) {
										// Removes the clicked item
										item.destroy();
										
										// TODO Auto-generated method stub
										
										//removes the all child objects of clicked item by checking owner names
										for(int i=0;;i=i+1){
											if(res.get(i)==null){
												break;
											}
								res.get(i).destroy();
								
										}
										for(int z=0;z<2;z++)
											fetcher();
									}
								});
		
						
							fetcher();

						return true;
					}
					
				});

		//
		btnOpenPopup
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(final AdapterView<?> parent,
							final View view, final int position, long id) {

						final Kategori item = (Kategori) parent
								.getItemAtPosition(position);
						
					
						final Kategori itemname = (Kategori) parent
								.getItemAtPosition(position);


						if (item != null) {

							Intent intentMain = new Intent(MainActivity.this,
									Activity_List_Tasks.class);
							//
							intentMain.putExtra("id", itemname.getID());
							intentMain.putExtra("name",item.getName());
							startActivity(intentMain);
							//

						} else {
							//
						}

						//
					}
					//
					// popupWindow.dismiss();
					//

				});
		// setOnClickListeners();
		fetcher();

	}

	public <T extends StackMobModel> void removeAndDelete(String field,
			List<T> objs, StackMobCallback callback) {

	}

	//

	private void fetcher() {
		final ListView myListView1 = (ListView) findViewById(R.id.myListView);
		Kategori.query(Kategori.class, new StackMobQuery(),
				new StackMobQueryCallback<Kategori>() {
					@Override
					public void success(List<Kategori> result) {
						// You've now got a list of the tasks

						mTodos = result;

						mTodoAdapter = new Todoadapter(thisContext,
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

	
	@Override
	public void onResume() {
		super.onResume();

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
			Intent intentMain = new Intent(MainActivity.this,
					Activity_Add_Category.class);
			MainActivity.this.startActivity(intentMain);
			// Complete with your code
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
