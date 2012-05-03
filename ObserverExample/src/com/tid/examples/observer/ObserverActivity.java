package com.tid.examples.observer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Ejemplo de ContentObserver
 * http://developer.android.com/reference/android/database/ContentObserver.html
 *
 */
public class ObserverActivity extends Activity {
	
	private MediaObserver mObserver;
	private Handler mHandler;
	
	private Button mRegisterButton;
	private Button mUnregisterButton;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mHandler = new Handler();
        mObserver = new MediaObserver(this, mHandler);

        mRegisterButton = (Button) findViewById(R.id.register);
        mUnregisterButton = (Button) findViewById(R.id.unregister);
        
        mRegisterButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mObserver.registerObserver();
				Toast.makeText(ObserverActivity.this, "Observer registrado", Toast.LENGTH_SHORT).show();
			}
		});
        
        mUnregisterButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mObserver.unregisterObserver();
				Toast.makeText(ObserverActivity.this, "Observer deregistrado", Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    
}