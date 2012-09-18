package com.tid.Ejemplo81_widgetBotones;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {
	public static String TAG="TID_EXAMPLE";
	public Intent myService=null;
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Log.d(TAG, "widget::::onReceive:");
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.d(TAG, "widget::::onDeleted:");
		if (myService!=null){
			context.stopService(myService);
		}
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		Log.d(TAG, "widget::::onDisabled:");
		if (myService!=null){
			context.stopService(myService);
		}
		super.onDisabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "widget::::onReceive:");
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		final int N = appWidgetIds.length;
		Log.d(TAG, "widget::::onUpdate:"+String.valueOf(N));
		for (int i=0; i<N; i++)  {
			int appWidgetId = appWidgetIds[i];

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget_layout);
			views.setTextViewText(R.id.textView, "Init widget");
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
		myService=new Intent(context, ViewService.class);
		context.startService(myService);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}
