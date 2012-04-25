package com.tid.examples.alarmservice;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tid.examples.alarmservice.R;

/**
 * Example of use of Alarm Service
 * http://developer.android.com/reference/android/app/AlarmManager.html
 *
 */
public class AlarmExampleActivity extends Activity {
	
	//Instancia del AlarmManager
	private AlarmManager mAlarmManager;
	
	//Guardamos como atributo el pending intent para poder cancelarlo
	private PendingIntent mPendingIntent;

	//Views
	private EditText mTime;
	private EditText mTimeRep;

	private Button mButtonToast;
	private Button mButtonNotif;
	private Button mButtonCall;
	private Button mButtonToastRep;
	private Button mCancel;
	
	private static final int PHONE_NUMBER = 123;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Getting the AlarmManager
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Find views
        mTime = (EditText) findViewById(R.id.time);
        mTimeRep = (EditText) findViewById(R.id.time_rep);

        mButtonToast = (Button) findViewById(R.id.buttonToast);
        mButtonNotif = (Button) findViewById(R.id.buttonNotif);
        mButtonCall = (Button) findViewById(R.id.buttonCall);
        mButtonToastRep = (Button) findViewById(R.id.buttonToastRep);
        mCancel = (Button) findViewById(R.id.cancelRep);
        
        //Set listeners
        
        mButtonToast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchToast(getTime());
			}
		});
        
        mButtonNotif.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchNotif(getTime());
			}
		});
        
        mButtonCall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchCall(getTime());
			}
		});
        
        mButtonToastRep.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchToastRep(getRepeating());
			}
		});
        
        mCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancelRep();
			}
		});
    }
    
    /**
     * Toma el tiempo del EditText y lo pasa a formato de milisegundos UTC
     * 
     * @return
     */
    private long getTime(){
    	String time = mTime.getText().toString();
    	
    	long longTime = System.currentTimeMillis() + Integer.valueOf(time)*1000;
    	
    	return longTime;
    }
    
    private long getRepeating(){
    	String time = mTimeRep.getText().toString();
    	
    	long longTime = Integer.valueOf(time)*1000;
    	
    	return longTime;
    }
    
    private void launchToast(long time){
    	
    	//Preparar el PendingIntent
    	//Se lanza a la clase ToastReceiver.java
    	Intent i = new Intent(this, ToastReceiver.class);
    	
    	//Creamos el PendingIntent para BroadcastReceiver (getBroadcast)
    	//Solo se lanza una vez (FLAG_ONE_SHOT)
    	mPendingIntent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
    	
    	//Ponemos la alarma
    	//RTC (Usa System.currentTimeMillis()).
    	mAlarmManager.set(AlarmManager.RTC, time, mPendingIntent);
    }
    
    private void launchNotif(long time){
    	
    	//Preparar el PendingIntent
    	//Se lanza a la clase NotifReceiver.java
    	Intent i = new Intent(this, NotifReceiver.class);
    	
    	//Creamos el PendingIntent para BroadcastReceiver (getBroadcast)
    	//Solo se lanza una vez (FLAG_ONE_SHOT)
    	mPendingIntent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
    	
    	//Ponemos la alarma
    	//RTC (Usa System.currentTimeMillis()).
    	mAlarmManager.set(AlarmManager.RTC, time, mPendingIntent);
    }

    /**
     * Prepares an alarm to launch Phone app
     * 
     * @param time
     */
    private void launchCall(long time){
    	
    	//Preparar el PendingIntent
    	//En este caso es un intent implicito, con accion ACTION_DIAL y un numero de telefono
    	Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PHONE_NUMBER));
    	
    	//Creamos el PendingIntent para Activity (getActivity)
    	//Solo se lanza una vez (FLAG_ONE_SHOT)
    	PendingIntent pending = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
    	
    	//Ponemos la alarma
    	//RTC (Usa System.currentTimeMillis()).
    	mAlarmManager.set(AlarmManager.RTC, time, pending);
    }
    
    private void launchToastRep(long repeteating){
    	
    	//Preparar el PendingIntent
    	//Se lanza a la clase ToastReceiver.java
    	Intent i = new Intent(this, ToastReceiver.class);
    	
    	//Creamos el PendingIntent para BroadcastReceiver (getBroadcast)
    	//Se repite (ponemos FLAG_UPDATE_CURRENT)
    	mPendingIntent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

    	//Ponemos la alarma con repeticion
    	//RTC (Usa System.currentTimeMillis()).
    	mAlarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), repeteating, mPendingIntent);
    }
    
    private void cancelRep(){
    	//Cancela el PendingIntent (el ultimo)
    	mAlarmManager.cancel(mPendingIntent);
    	Toast.makeText(this, "Cancelada", Toast.LENGTH_SHORT).show();
    }
    
    
    
}