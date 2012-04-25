package com.tid.examples.alarmservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotifReceiver extends BroadcastReceiver {
	
	private static final String TAG = "NotifReceiver";
	
	private static final String MESSAGE = "Notificación de alarma";
	private static final String TITLE = "Alarmas";
	private static final String TEXT = "¡Hola, soy una notificación desde una alarma!";

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i(TAG, "Entering onReceive");
		
		//Instanciamos el NotificationManager
		NotificationManager notificationService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		//Creamos la notificación
		Notification notification = new Notification(R.drawable.ic_launcher, MESSAGE, System.currentTimeMillis());
		
		//Añadimos el título y el texto, y la acción posterior
		//Como acción nos vale volver a la Activity principal
		Intent notificationIntent = new Intent(context, AlarmExampleActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		notification.setLatestEventInfo(context, TITLE, TEXT, contentIntent);

		//Lanzamos la notificación
		notificationService.notify(1, notification);
	}

}
