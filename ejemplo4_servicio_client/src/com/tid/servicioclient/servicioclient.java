package com.tid.servicioclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.tid.servicioclient.service.IServiceExample2;
import com.tid.servicioclient.service.ServiceExample2;

public class servicioclient extends Activity {
	public ServiceExample2 myService=null;
	public static String TAG="TID_EXAMPLE";
	public ServiceConnection connection;
	public IServiceExample2 interfaceServicio;
	public Intent intentServicio;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        intentServicio=new Intent(IServiceExample2.class.getName());
        startService(intentServicio);
    }
    
    public void InitService(View target)
    {
    	Log.d(TAG, "ServicioManager:Iniciando servicio");
    	connection=new ServiceConnection() {
			
			public void onServiceDisconnected(ComponentName name) {
				Log.d(TAG, "______ServicioManager:onServiceDisconnected:________-");
				try {
					interfaceServicio.killService();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				interfaceServicio=null;
				
			}
			
			public void onServiceConnected(ComponentName name, IBinder service) {
				Log.d(TAG, "ServicioManager:peticion...:["+name+"]");
				interfaceServicio=IServiceExample2.Stub.asInterface((IBinder)service);
			}
		};
		if (intentServicio==null){
			 intentServicio=new Intent(IServiceExample2.class.getName());
		}
		bindService(intentServicio, connection, Context.BIND_AUTO_CREATE);
    }
    

    
    
    @Override
	protected void onStop() {
		finish();
		super.onStop();
	}

	public void StopService(View target)
    {
		
		
    	Log.d(TAG, "ServicioManager:parando servicio");
    	try {
    		//Es necesario tanto hacer un unbind como parar el servicio, a menos que en el unbind del servicio lo mates directamente
    		unbindService(connection);
		//	servicioEx.killService();
			stopService(intentServicio);
		} catch (Exception e) {
			Log.d(TAG, "imposible matarlo, intentandolo de otra manera");
			stopService(intentServicio);
			
		}
    	
    }
    
    
    public void PingService(View target)
    {
    	Log.d(TAG, "ServicioManager:Ping al servicio servicio");
    	if (interfaceServicio!=null)
    	{
    		String salida;
			try {
				salida = interfaceServicio.PingServicio();
				Log.d(TAG, "ServicioManager:contestacion:"+salida);
			} catch (RemoteException e) {
				Log.d(TAG, "ServicioManager:EXCEPT:"+e.getMessage());
				e.printStackTrace();
			}
    		
    	}else
    	{
    		Log.d(TAG, "ServicioManager:No conectado");	
    	}
    	
		
    	
    }
}