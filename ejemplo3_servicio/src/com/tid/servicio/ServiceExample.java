package com.tid.servicio;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ServiceExample extends Service {
	public static String TAG="TID_EXAMPLE";
	private final IBinder binder=new ServiceBinder();

	public class ServiceBinder extends Binder{
		ServiceExample getService()
		{
			return ServiceExample.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		Log.d(TAG, "ServiceExample:Iniciando servicio");
		return binder;
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "ServiceExample:_______________________________");
		Log.d(TAG, "ServiceExample:_________     INIT    _________");
		Log.d(TAG, "ServiceExample:__       AWESOME SERVICE     __");
		Log.d(TAG, "ServiceExample:_______________________________");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "ServiceExample:_______________________________");
		Log.d(TAG, "ServiceExample:_________ !!!!KILL!!!! ________");
		Log.d(TAG, "ServiceExample:__       AWESOME SERVICE     __");
		Log.d(TAG, "ServiceExample:_______________________________");

		Log.d(TAG, "ServiceExample:onDestroy");
		super.onDestroy();
	}

	@Override
	public void onRebind(Intent intent) {
		Log.d(TAG, "ServiceExample:onRebind");
		super.onRebind(intent);
	}
	
    public String PingServicio()
    {
    	
    	return "Sigo vivo! random:"+String.valueOf(Math.random());
    }

	@Override
	public void onStart(Intent intent, int startId) {
		Log.d(TAG, "____ServiceExample:onStart______");
		super.onStart(intent, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "ServiceExample:onUnbind");
		return super.onUnbind(intent);
	}

	
}
