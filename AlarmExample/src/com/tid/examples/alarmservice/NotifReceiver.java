package com.tid.examples.alarmservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotifReceiver extends BroadcastReceiver {
	
	private static final String TAG = "NotifReceiver";
	private static final String MESSAGE = "¡Hola, soy una notificación desde una alarma!";

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i(TAG, "Entering onReceive");
		
		NotificationManager notificationService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification notification = new Notification(R.drawable.ic_launcher, MESSAGE, System.currentTimeMillis());
		
		notificationService.notify(10, notification);
	}

}
