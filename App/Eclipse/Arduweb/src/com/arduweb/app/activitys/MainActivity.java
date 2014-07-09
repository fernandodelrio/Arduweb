package com.arduweb.app.activitys;

import com.arduweb.app.views.AppView;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new AppView(this));
	}
}
