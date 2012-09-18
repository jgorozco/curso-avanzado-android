package com.tid.ejemplo41;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.tid.servicioclient.service.IServiceExample2;

public class ejemplo41_clienteExterno extends Activity {
    /** Called when the activity is first created. */
	public static String TAG="TID_EXAMPLE";
	public ServiceConnection connection;
	public IServiceExample2 servicioEx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startService(new Intent(IServiceExample2.class.getName()));
        InitService();
    }
    
    
    public void InitService()
    {
    	Log.d(TAG, "ejemplo41_clienteExterno:Iniciando servicio");
    	connection=new ServiceConnection() {
			
			public void onServiceDisconnected(ComponentName name) {
				Log.d(TAG, "ejemplo41_clienteExterno:onServiceDisconnected:");
				servicioEx=null;
				
			}
			
			public void onServiceConnected(ComponentName name, IBinder service) {
				IServiceExample2  servicioEx=IServiceExample2.Stub.asInterface((IBinder)service);
				try {
					String random=servicioEx.PingServicio();
					Log.d(TAG, "ejemplo41_clienteExterno:salida del servicio"+random);
				} catch (RemoteException e) {
					Log.d(TAG, "ejemplo41_clienteExterno:El servicio ha dado error!");
				}
				
		//		myService=((ServiceBinder)service).getService();
				
				
				
			}
		};
		//Intent bindintent=new Intent(servicioclient.this,ServiceExample2.class);
		Log.d(TAG, "ejemplo41_clienteExterno:Iniciando servicio");
		bindService(new Intent(IServiceExample2.class.getName()), connection, Context.BIND_AUTO_CREATE);
    //	
    }
}