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

import com.curso.ejemplos.R;

/**
 * Example of use of Alarm Service
 * http://developer.android.com/reference/android/app/AlarmManager.html
 *
 */
public class AlarmExampleActivity extends Activity {
	
	//Instace of AlarmManager
	private AlarmManager mAlarmManager;

	//Views
	private EditText mTime;
	private EditText mTimeRep;

	private Button mButtonToast;
	private Button mButtonNotif;
	private Button mButtonCall;
	private Button mButtonToastRep;
	
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
				launchToastRep(getTime());
			}
		});
    }
    
    private long getTime(){
    	String time = mTime.getText().toString();
    	
    	long intTime = System.currentTimeMillis() + Integer.valueOf(time)*1000;
    	
    	return intTime;
    }
    
    private void launchToast(long time){
    	//TODO
    }
    
    private void launchNotif(long time){
    	//TODO
    }

    /**
     * Prepares an alarm to launch Phone app
     * 
     * @param time
     */
    private void launchCall(long time){
    	
    	//Prepare the pending intent
    	//ACTION_DIAL and a phone number
    	Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PHONE_NUMBER));
    	PendingIntent pending = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
    	
    	//Set the alarm
    	//RTC (Alarm time in System.currentTimeMillis() (wall clock time in UTC)).
    	mAlarmManager.set(AlarmManager.RTC, time, pending);
    }
    
    private void launchToastRep(long time){
    	//TODO
    }
    
    
    
}