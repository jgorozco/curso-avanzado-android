package com.tid.Ejemplo8_widget;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

public class Widget8 extends AppWidgetProvider {
	public static String TAG="TID_EXAMPLE";
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
        final int N = appWidgetIds.length;
		Log.d(TAG, "widget::::onUpdate:"+String.valueOf(N));
		 for (int i=0; i<N; i++)  {
			 int appWidgetId = appWidgetIds[i];
			int number = (new Random().nextInt(100));

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget_layout);
			views.setTextViewText(R.id.textView, String.valueOf(number));
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}

	@Override
	public void onDisabled(Context context) {
		Log.d(TAG, "widget::::onDisabled");
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		Log.d(TAG, "widget::::onEnabled");
		super.onEnabled(context);
	}
	
	
}
