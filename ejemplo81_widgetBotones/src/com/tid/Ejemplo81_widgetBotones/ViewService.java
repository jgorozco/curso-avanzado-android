package com.tid.Ejemplo81_widgetBotones;

import java.util.Random;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class ViewService extends Service {
	public static String TAG="TID_EXAMPLE";
	public ComponentName projectWidget;
	public RemoteViews  remoteViews;
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "service::::onBind:");
		return null;
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "service::::onCreate:");
		 remoteViews = new RemoteViews(this.getPackageName(), R.layout.widget_layout);
        projectWidget = new ComponentName(this, MyWidgetProvider.class);
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		final String packag=intent.getPackage();
		String PACKAGE="com.tid.Ejemplo81_widgetBotones";
		final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
		ComponentName thisAppWidget = new ComponentName(PACKAGE, PACKAGE+"."+"MyWidgetProvider");
        final int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
		Log.d(TAG, "service::::onStart["+String.valueOf(startId)+"]:"+intent.getClass().toString());
		new Thread(new Runnable() {
			public void run() {
				try {//TODO no funciona por que no accede a los elementos del widget, probar mañana
					while(true) {

						Thread.sleep(1000);
						Log.d(TAG, "actualiza!!");
						if (appWidgetManager.getAppWidgetIds(projectWidget).length > 0) {
							int number = (new Random().nextInt(100));
				            remoteViews.setTextViewText(R.id.textView, "update with["+String.valueOf(number)+"] widgets["+String.valueOf(appWidgetIds.length)+"]");
				            appWidgetManager.updateAppWidget(projectWidget, remoteViews);
				        }
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "service::::onDestroy:");
		super.onDestroy();
	}



}
