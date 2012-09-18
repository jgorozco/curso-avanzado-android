package com.tid.widget82_widgetboton;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class WidgetBoton1 extends AppWidgetProvider {
	public static String TAG="TID_EXAMPLE";
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.d(TAG, "widget:::: onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		Log.d(TAG, "widget::::onDisabled");
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		Log.d(TAG, "widget:::: onEnabled");
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "widget:::: onReceive");
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.d(TAG, "widget:::: onUpdate");
		ComponentName projectWidget = new ComponentName(context, WidgetBoton1.class);
		if (appWidgetManager.getAppWidgetIds(projectWidget).length > 0) {
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget_layout);
			views.setTextViewText(R.id.textView1, "change!");
			Intent intent=new Intent(context, WIget82_widgetBoton.class);
			//TODO que es un pending intent
			PendingIntent pi = PendingIntent.getActivity(context,0, intent,0);
			views.setOnClickPendingIntent(R.id.button1,pi);
			appWidgetManager.updateAppWidget(projectWidget, views);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}
