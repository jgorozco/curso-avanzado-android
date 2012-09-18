package com.tid.Ejemplo10_setextra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Ejemplo10_setextra extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	public void NextIntent(View target)
	{
		Intent i=new Intent(getApplicationContext(), RecibeExtra.class);
		i.putExtra("_datos", "datos en string");
		i.putExtra("_integer", 3);
		startActivity(i);
	}
}