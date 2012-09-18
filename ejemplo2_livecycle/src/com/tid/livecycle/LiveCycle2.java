package com.tid.livecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LiveCycle2 extends Activity {
    /** Called when the activity is first created. */
	public static String TAG="TID_EXAMPLE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alternative);
    }
    
    
    public void ClickBack(View Target)
    {
    	finish();
    }
    
    @Override
	protected void onPause() {
		 Log.d(TAG, "-----livecycle2:onPause");		
		super.onPause();
	}

	@Override
	protected void onPostResume() {
		 Log.d(TAG, "-----livecycle2:onPostResume");		
		super.onPostResume();
	}

	@Override
	protected void onRestart() {
		 Log.d(TAG, "-----livecycle2:onRestart");		
		super.onRestart();
	}

	@Override
	protected void onResume() {
		 Log.d(TAG, "-----livecycle2:onResume");		
		super.onResume();
	}

	@Override
	protected void onStart() {
		 Log.d(TAG, "-----livecycle2:onStart");		
		super.onStart();
	}

	@Override
	protected void onStop() {
		 Log.d(TAG, "-----livecycle2:onStop");		
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		 Log.d(TAG, "-----livecycle2:onDestroy");		
		super.onDestroy();
	}   
}
