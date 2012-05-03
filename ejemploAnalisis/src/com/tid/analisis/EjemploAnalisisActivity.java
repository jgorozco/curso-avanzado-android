package com.tid.analisis;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class EjemploAnalisisActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    public void clickBtn1(View target)
    {
    	quemarCPU();
    }

    public void clickBtn2(View target)
    {
    	quemarMemoria();
    }

    
    public void quemarCPU()
    {
    	long i=0;
    	long total=0;
    	while (i<1000000000)
    	{
    		total=total+i;
    		Log.d("TID_EX", "haciendo.:"+String.valueOf(total));
    	}
    	
    }
    
    public void quemarMemoria()
    {
    	long i=0;
    	ArrayList<Object> lista=new ArrayList<Object>();
    	while (i<1000000000)
    	{
    		Object objeto=new Object();
    		lista.add(objeto);
    		Log.d("TID_EX", "creando");
    	}
    	
    }

}