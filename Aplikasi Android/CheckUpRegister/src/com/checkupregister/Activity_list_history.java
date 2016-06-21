package com.checkupregister;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Activity_list_history extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_history);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_history, menu);
		return true;
	}

}
