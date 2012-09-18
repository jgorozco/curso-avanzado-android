package com.tid.broadcastWakeup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ejemplo5_broadcastWakeup extends Activity {
	public static String TAG="TID_EXAMPLE";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG,"levantamos la aplicacion desde el reciver");
        
    }
}