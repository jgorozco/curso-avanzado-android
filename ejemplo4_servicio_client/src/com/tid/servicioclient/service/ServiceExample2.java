package com.tid.servicioclient.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.Handler.Callback;
import android.util.Log;

public class ServiceExample2 extends Service {
	public static String TAG="TID_EXAMPLE";
	public Handler hand;
	public ThreadExecuted localThread;
	public int MyId=(int) Math.round((Math.random()*1000));
	private final class ServiceExample2Reporter extends IServiceExample2.Stub
	{

		public String PingServicio() throws RemoteException {
			return ServiceExample2.this.PingServicio();//"Sigo vivo! random:"+String.valueOf(Math.random());
		}
		
		public void killService()
		{
	//		ServiceExample2.this.killService();
		}
		
	}
	
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		Log.d(TAG, "ServiceExample:Iniciando servicio");
		return new ServiceExample2Reporter();//binder;
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "ServiceExample:_______________________________");
		Log.d(TAG, "ServiceExample:_________     INIT    _________");
		Log.d(TAG, "ServiceExample:__       AWESOME SERVICE     __");
		Log.d(TAG, "ServiceExample:_______________________________");
		
		super.onCreate();
		hand=new Handler(new Callback() {
			
			public boolean handleMessage(Message msg) {
				String random=PingServicio();
				Log.d(TAG, "SIGO FUNCIONANDO id["+String.valueOf(MyId)+"]:"+random);
				return false;
			}
		});
		localThread=new ThreadExecuted(hand);
		localThread.start();
		
	}

	/*public void killService()
	{
		Log.d(TAG, "ServiceExample:_________ !!!!KILL!!!! ________");
		if (localThread!=null)
		{localThread.interrupt();
		localThread=null;
		}
		ServiceExample2.this.stopSelf();
		try {
			finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@Override
	public void onDestroy() {
		Log.d(TAG, "ServiceExample:_______________________________");
		Log.d(TAG, "ServiceExample:_________ !!!!KILL!!!! ________");
		Log.d(TAG, "ServiceExample:__       AWESOME SERVICE     __");
		Log.d(TAG, "ServiceExample:_______________________________");
		Log.d(TAG, "ServiceExample:onDestroy");
		if (localThread!=null)
		{
			localThread.interrupt();
		}
		super.onDestroy();
	}

	@Override
	public void onRebind(Intent intent) {
		Log.d(TAG, "ServiceExample:onRebind");
		super.onRebind(intent);
	}
	
    public String PingServicio()
    {
    	
    	return "Sigo vivo! random1:"+String.valueOf(Math.random());
    }

	@Override
	public void onStart(Intent intent, int startId) {
		Log.d(TAG, "____ServiceExample:onStart______");
		
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	//	super.onStartCommand(intent, flags, startId);
		Log.d(TAG, "____ServiceExample:onStartCommand______");
		return  START_NOT_STICKY;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "ServiceExample:onUnbind");
		return super.onUnbind(intent);
	}

}
