package com.tid.Ejemplo12_notificaciones;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;


public class activityNotificacion extends Activity {
	public static int NOTIFY_NUM=1;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

		        setContentView(R.layout.notif);
		        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		       // mNotificationManager.cancel(NOTIFY_NUM);	        	
	       
	        

	    }
}
