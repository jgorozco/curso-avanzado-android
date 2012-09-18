package com.tid.Ejemplo10_setextra;

import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class RecibeExtra extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.recibir);
		 Bundle elementos=getIntent().getExtras();
		 Log.d("TID_EXAMPLE","tenemos los elementos["+String.valueOf(elementos.size())+"]");
		 Set<String> Keys = elementos.keySet();
		 for (String string : Keys) {
			 Log.d("TID_EXAMPLE", string+"=="+String.valueOf(elementos.get(string)));
		}
	}

	@Override
	protected void onPause() {
		finish();
		// TODO Auto-generated method stub
		super.onPause();
	}
	public void backActivity(View target)
	{
		finish();
	}

}
