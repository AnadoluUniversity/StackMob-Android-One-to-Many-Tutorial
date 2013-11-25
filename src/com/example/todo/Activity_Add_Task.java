package com.example.todo;

import java.util.Date;

import com.google.gson.Gson;
import com.stackmob.sdk.api.StackMobOptions;
import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.exception.StackMobException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Add_Task extends Activity {
	private Button Save;
	private String bbb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.add_task);
		setTitle("GÖREV EKLE");
		Save = (Button) findViewById(R.id.atdSaveBtn);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setOnClickListeners();

	}

	private void setOnClickListeners() {
		final EditText myEditText2 = (EditText) findViewById(R.id.atdEditTextTitle);

		Save.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				bbb = myEditText2.getText().toString();
				Intent i = getIntent();
				
				//gets name and id of the owner category of this object
				final String owner =i.getExtras().getString("namee");
				String mert = i.getExtras().getString("idd");

				final Kategori mytask = new Kategori();
				mytask.setID(mert);
				mytask.fetch(new StackMobModelCallback() {
					//save added object
					@Override
					public void success() {
//						mytask.getTasks().clear();
						mytask.getTasks().add(new Tasks(bbb, new Date(),owner));
						mytask.save(StackMobOptions.depthOf(1));

						// the call succeeded
					}

					@Override
					public void failure(StackMobException e) {
						// the call failed
					}
				});

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(myEditText2.getWindowToken(), 0);
				myEditText2.setText("");

			}
		});

	}
}
