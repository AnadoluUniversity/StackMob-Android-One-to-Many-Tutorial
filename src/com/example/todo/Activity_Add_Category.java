package com.example.todo;

import java.util.Date;

import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.exception.StackMobException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_Add_Category extends Activity {
	private Menu optionsMenu;
	private Button Save;
	private Button Delete;
	private String bbb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.add_category);
		setTitle("KATEGORÝ EKLE");
		Save = (Button) findViewById(R.id.atdSaveBtn);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setOnClickListeners();

	}

	public boolean onCreateOptionsMenu(Menu menu) {

		return super.onCreateOptionsMenu(menu);
		//
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ic_delete:

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setOnClickListeners() {
		final EditText myEditText2 = (EditText) findViewById(R.id.atdEditTextTitle);

		Save.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				bbb = myEditText2.getText().toString();
				final Kategori myTask = new Kategori(bbb, new Date());
// Saves input as Category
				myTask.save(new StackMobModelCallback() {
					@Override
					public void success() {
						// the call succeeded
						Toast.makeText(getApplicationContext(), "Kaydedildi",
								Toast.LENGTH_LONG).show();
					}

					@Override
					public void failure(StackMobException arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(),
								"Kayýt Baþarýsýz", Toast.LENGTH_LONG).show();

					}
				});
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(myEditText2.getWindowToken(), 0);
				myEditText2.setText("");

			}
		});

	}

}
