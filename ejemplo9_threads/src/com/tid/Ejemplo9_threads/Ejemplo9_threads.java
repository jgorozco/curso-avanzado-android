package com.tid.Ejemplo9_threads;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Ejemplo9_threads extends Activity implements Runnable {
	/** Called when the activity is first created. */
	public static String TAG="TID_EXAMPLE";
	public TextView myText;
	public Boolean keeponloop=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myText=(TextView) findViewById(R.id.text_data);
		Log.d(TAG, "ON CREATE");
//		keeponloop=true;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onPause()
	{
		Log.d(TAG, "onPause");
//		keeponloop=false;
	}

	public void updateView()
	{
	//	runOnUiThread(new Runnable() {
	//		public void run() {
				Log.d(TAG, "ON updateView");
				int numero=(int) (Math.random()*1000);
				myText.setText("Updating view with "+numero);
	//		}
	//	});
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				Log.d(TAG, "ON keeponloop");

				updateView();


			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		// TODO Auto-generated method stub

	}
}