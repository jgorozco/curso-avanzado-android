package com.tid.ejemplo52_broadcastEmiter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ejemplo52_broadcastEmiter extends Activity {
	public static String TAG="TID_EXAMPLE";
	public static final String CUSTOM_INTENT = "com.tid.ejemplo51.broadcast_1";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    public void sendBroadcast(View target)
    {
    	Log.d(TAG,"ENVIANDO BROADCAST ["+CUSTOM_INTENT+"]");
    	Intent i = new Intent();
    	i.setAction(CUSTOM_INTENT);
    	sendBroadcast(i);
    	
    }
}