package com.tid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Sensores_ejemplo extends Activity {
    private static final String TID_EX = "TID_EX";
	/* http://developer.android.com/reference/android/hardware/SensorEvent.html#values. */
	private TextView sensor1;
	private TextView sensor2;
	private TextView sensor3;
	
	private TextView sensor_a;
	private TextView sensor_b;
	private TextView sensor_c;
	
	private TextView sensor_x;
	private TextView sensor_y;
	private TextView general;
	ArrayList<TextView> textData;
	private SensorManager sensorManager;
	private SensorEventListener listenerACC;
	private SensorEventListener listenerMAG;
	private SensorEventListener listenerLight;
	private SensorEventListener listenerPROX;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initXMLElements();
        initSensorListener();
    }

	@Override
	protected void onStop() {
		Log.d(TID_EX, "onStop");
		sensorManager.unregisterListener(listenerACC);
		sensorManager.unregisterListener(listenerMAG);
		sensorManager.unregisterListener(listenerLight);
		sensorManager.unregisterListener(listenerPROX);
		super.onStop();
	}

	private void initSensorListener() {
		sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Sensor sensor = (Sensor) iterator.next();
			appendDetails(":["+sensor.getName()+"] V:["+sensor.getVendor()+"] T:"+sensor.getType());
		}
		
		
		listenerACC=new SensorEventListener() {
			
			@Override
			public void onSensorChanged(SensorEvent event) {
				if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

					// assign directions
					float x=event.values[0];
					float y=event.values[1];
					float z=event.values[2];
					updateTextView("X: "+x, 0);
					updateTextView("Y: "+y, 1);
					updateTextView("Z: "+z, 2);

				}
				
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				appendDetails(sensor.getName()+"::"+String.valueOf(accuracy));
				
			}
		};
		sensorManager.registerListener(listenerACC,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
				SensorManager.SENSOR_DELAY_FASTEST);
		
		
		listenerMAG=new SensorEventListener() {
			
			@Override
			public void onSensorChanged(SensorEvent event) {
				if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){

					// assign directions
					float x=event.values[0];
					float y=event.values[1];
					float z=event.values[2];
					updateTextView("X: "+x, 3);
					updateTextView("Y: "+y, 4);
					updateTextView("Z: "+z, 5);

				}
				
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				appendDetails(sensor.getName()+"::"+String.valueOf(accuracy));
				
			}
		};
		sensorManager.registerListener(listenerMAG,
				sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
				SensorManager.SENSOR_DELAY_UI);
		
		listenerLight=new SensorEventListener() {
			
			@Override
			public void onSensorChanged(SensorEvent event) {
					float x=event.values[0];
					updateTextView("LUX: "+x, 6);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				appendDetails(sensor.getName()+"::"+String.valueOf(accuracy));
				
			}
		};
		sensorManager.registerListener(listenerLight,
				sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), 
				SensorManager.SENSOR_DELAY_GAME);
		
		listenerPROX=new SensorEventListener() {
			
			@Override
			public void onSensorChanged(SensorEvent event) {
					float x=event.values[0];
					updateTextView("PROX: "+x, 7);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				appendDetails(sensor.getName()+"::"+String.valueOf(accuracy));
				
			}
		};
		sensorManager.registerListener(listenerPROX,
				sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), 
				SensorManager.SENSOR_DELAY_GAME);
			
		
		
	}

	private void initXMLElements() {
		textData=new ArrayList<TextView>();
		sensor1=(TextView) findViewById(R.id.txt_sensor1);
		sensor2=(TextView) findViewById(R.id.txt_sensor2);
		sensor3=(TextView) findViewById(R.id.txt_sensor3);
		sensor_a=(TextView) findViewById(R.id.txt_sensor_a);
		sensor_b=(TextView) findViewById(R.id.txt_sensor_b);
		sensor_c=(TextView) findViewById(R.id.txt_sensor_c);
		sensor_x=(TextView) findViewById(R.id.txt_sensor_x);
		sensor_y=(TextView) findViewById(R.id.txt_sensor_y);
		general=(TextView) findViewById(R.id.txt_detalles);
		textData.add(sensor1);
		textData.add(sensor2);
		textData.add(sensor3);
		textData.add(sensor_a);
		textData.add(sensor_b);
		textData.add(sensor_c);
		textData.add(sensor_x);
		textData.add(sensor_y);
	}
	
	public void updateTextView(final String newData,final int posSensor)
	{
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
		//		Log.d(TID_EX,"data:"+newData);
				textData.get(posSensor).setText(newData);
				
			}
		});
	}
	
	public void appendDetails(final String newData)
	{
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if (newData==null)
					general.setText("");
				Log.d(TID_EX, "data:::"+newData);
				general.append("\n"+newData);
				
			}
		});
	}	
	
}