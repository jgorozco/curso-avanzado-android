package com.tid.servicio;

import com.tid.servicio.ServiceExample.ServiceBinder;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ServicioManager extends Activity {
    /** Called when the activity is first created. */
	public static String TAG="TID_EXAMPLE";
	public TextView myLog;
	public ServiceExample myService=null;
	public ServiceConnection connection;
	public Intent bindintent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myLog=(TextView) findViewById(R.id.text_log);
    }
    
    public void InitService(View target)
    {
    	Log.d(TAG, "ServicioManager:Iniciando servicio");
    	connection=new ServiceConnection() {
			
			public void onServiceDisconnected(ComponentName name) {
				Log.d(TAG, "ServicioManager:onServiceDisconnected:");
				myService=null;
				
			}
			
			public void onServiceConnected(ComponentName name, IBinder service) {
				Log.d(TAG, "ServicioManager:peticion...:");
				myService=((ServiceBinder)service).getService();
				
				
				
			}
		};
		bindintent=new Intent(ServicioManager.this,ServiceExample.class);
		getApplication().bindService(bindintent, connection, Context.BIND_AUTO_CREATE);
    //	startService(new Intent(this,ServiceExample.class));
    }
    

    
    
    public void StopService(View target)
    {
    	Log.d(TAG, "ServicioManager:parando servicio");
   // 	unbindService(connection);
    	stopService(bindintent);
    	if (myService!=null){
    		myService.stopSelf();
    		myService=null;
    	}
   // 	stopService(new Intent(this, ServiceExample.class));
    }
    
    
    public void PingService(View target)
    {
    	Log.d(TAG, "ServicioManager:Ping al servicio servicio");
    	if (myService!=null)
    	{
    		String salida=myService.PingServicio();
    		Log.d(TAG, "ServicioManager:contestacion:"+salida);
    	}else
    	{
    		Log.d(TAG, "ServicioManager:No conectado");	
    	}
    	
		
    	
    }
    
}