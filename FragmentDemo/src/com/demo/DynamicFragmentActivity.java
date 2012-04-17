package com.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class DynamicFragmentActivity extends FragmentActivity {
	private final static String TAG = "FragmentDemoActivity";

	@Override
	public ClassLoader getClassLoader() {
		return MultiDexClassLoader.getInstance();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dynamic_fragment);

		// We only want to load a new Fragment if the Activity
		// is built from scratch.
		if (savedInstanceState == null) {
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			ft.add(R.id.static_layout, new TestFragment()).commit();

			String className = this.getIntent().getStringExtra("class_name");

			try {
				@SuppressWarnings("unchecked")
				Class<Fragment> fragmentClass = (Class<Fragment>) MultiDexClassLoader
						.getInstance().loadClass(className);
				Fragment fragment = fragmentClass.newInstance();
				ft = getSupportFragmentManager().beginTransaction();
				ft.add(R.id.dynamic_layout, fragment).commit();
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
	}

}