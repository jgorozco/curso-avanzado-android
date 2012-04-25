package com.tid.testingui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
//http://developer.android.com/guide/developing/tools/monkey.html
//http://tools.android.com/tips/lint
//./adb shell monkey -p com.tid.testingui -v 1500
public class Ejemplo_testingui extends Activity {
	
    private static final String TID_TAG = "TID_TAG";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        EditText txt=(EditText) findViewById(R.id.edittext);
        txt.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d(TID_TAG, "tocado texto:"+event.toString());
				return false;
			}
		});
        
    }
    
    
    public void clickBtn1(View target)
    {
    	Log.d(TID_TAG,"clickado boton1");
    	
    }
    public void clickBtn2(View target)
    {
    	Log.d(TID_TAG,"clickado boton2");
    	
    }

    public void clickBtn3(View target)
    {
    	Log.d(TID_TAG,"clickado boton3");
  	
    	
    }

    public void clickBtn4(View target)
    {
    	Log.d(TID_TAG,"clickado boton4");
   	
    	
    }

    public void clickBtn5(View target)
    {
    	Log.d(TID_TAG,"clickado boton5");
   	
    }

    public void clickBtn6(View target)
    {
    	Log.d(TID_TAG,"clickado boton6");
   	
    }

}