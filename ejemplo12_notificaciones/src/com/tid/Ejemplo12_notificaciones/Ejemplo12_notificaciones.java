package com.tid.Ejemplo12_notificaciones;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class Ejemplo12_notificaciones extends Activity {
    /** Called when the activity is first created. */
	public static int NOTIFY_NUM=1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    public void notificacionSimple(View Target)
    {
    	NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	int icon = R.drawable.icon;
    	CharSequence tickerText = "notifico algo";
    	long when = System.currentTimeMillis();

    	Notification notification = new Notification(icon, tickerText, when);
    	
    	Context context = getApplicationContext();
    	CharSequence contentTitle = "titulo de notificacion";
    	CharSequence contentText = "texto de notificacion";
    	Intent notificationIntent = new Intent(this, activityNotificacion.class);
    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

    	notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
       	mNotificationManager.notify(NOTIFY_NUM, notification);
    }

    public void notificacionCompleja(View Target)
    {
     	NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	int icon = R.drawable.icon;
    	CharSequence tickerText = "notifico algo mas";
    	long when = System.currentTimeMillis();

    	Notification notification = new Notification(icon, tickerText, when);
    	RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.mynotif);
    	contentView.setTextViewText(R.id.txt_1, "Pongo el texto que meinteresa");
    	
    	
    	Intent notificationIntent2 = new Intent(this, activityNotificacion.class);
    	notificationIntent2.putExtra("CLOSE", true);
    	PendingIntent contentIntent2 = PendingIntent.getActivity(this, 0, notificationIntent2, 0);
    	notification.contentView = contentView;
    	notification.flags |= Notification.FLAG_AUTO_CANCEL;
    	notification.contentIntent = contentIntent2;
    	mNotificationManager.notify(R.layout.mynotif, notification);
    	
    }    
    
}