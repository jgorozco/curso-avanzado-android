package com.tid.examples.alarmservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ToastReceiver extends BroadcastReceiver {
	
	private static final String TAG = "ToastReceiver";
	private static final String MESSAGE = "¡Hola, soy un mensaje de alarma!";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Entering onReceive");
		Toast.makeText(context, MESSAGE, Toast.LENGTH_SHORT).show();
	}

}
