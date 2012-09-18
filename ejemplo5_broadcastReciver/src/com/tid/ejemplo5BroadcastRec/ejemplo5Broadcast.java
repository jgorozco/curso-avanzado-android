package com.tid.ejemplo5BroadcastRec;

import android.app.Activity;
import android.os.Bundle;

public class ejemplo5Broadcast extends Activity {
    /** Called when the activity is first created. */
	private RecibidorPrincipal miRecibidor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        miRecibidor=new RecibidorPrincipal(this);
    }

	@Override
	protected void onPause() {
		miRecibidor.unregisterRecivers();
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		miRecibidor.registerRecivers();
		// TODO Auto-generated method stub
		super.onResume();
	}
    
}