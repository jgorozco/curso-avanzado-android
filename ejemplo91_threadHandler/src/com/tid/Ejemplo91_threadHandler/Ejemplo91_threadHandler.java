package com.tid.Ejemplo91_threadHandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.util.Log;
import android.widget.TextView;

public class Ejemplo91_threadHandler extends Activity {
    /** Called when the activity is first created. */
    TextView texto;
	Handler myhaHandler;
	 ThreadLoop loop;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        texto=(TextView) findViewById(R.id.text_data);
        texto.setText("iniciado todo");
        myhaHandler=new Handler(new Callback() {
			
			public boolean handleMessage(Message msg) {
			
				String data=msg.getData().getString("datos");
				Log.d("TID_EXAMPLE","Mensaje recibido:"+data);
				texto.setText(data);
				return true;
			}
		});
        loop=new ThreadLoop(myhaHandler);
        loop.start();
    }
	@Override
	protected void onPause() {
		if ((loop!=null)&&(loop.isAlive()))
		{
			loop.interrupt();
		}
		finish();
		super.onPause();
		
	}
	
	
}