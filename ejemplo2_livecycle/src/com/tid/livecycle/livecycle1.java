package com.tid.livecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class livecycle1 extends Activity {
    /** Called when the activity is first created. */
	public static String TAG="TID_EXAMPLE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    public void ClickNext(View Target)
    {
		 Intent i = new Intent(getApplicationContext(), LiveCycle2.class);
		 startActivity(i);
    }
    
    @Override
	protected void onPause() {
		 Log.d(TAG, "livecycle1:onPause");		
		super.onPause();
	}

	@Override
	protected void onPostResume() {
		 Log.d(TAG, "livecycle1:onPostResume");		
		super.onPostResume();
	}

	@Override
	protected void onRestart() {
		 Log.d(TAG, "livecycle1:onRestart");		
		super.onRestart();
	}

	@Override
	protected void onResume() {
		 Log.d(TAG, "livecycle1:onResume");		
		super.onResume();
	}

	@Override
	protected void onStart() {
		 Log.d(TAG, "livecycle1:onStart");		
		super.onStart();
	}

	@Override
	protected void onStop() {
		 Log.d(TAG, "Seminario1:onStop");		
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		 Log.d(TAG, "livecycle1:onDestroy");		
		super.onDestroy();
	}   
    
}