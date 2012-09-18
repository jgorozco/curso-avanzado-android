package com.aurigae.seminario1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SeminarioAlt extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
  	    setContentView(R.layout.alternative);
		Log.d("EJEMPLO", "SeminarioAlt:onCreate");
		Button b= (Button)findViewById(R.id.alt_btn_close);
		b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {
					SeminarioAlt.this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SeminarioAlt.this.finish();				
			}
		});
	}
	@Override
	protected void onPause() {
		 Log.d("EJEMPLO", "SeminarioAlt:onPause");		
		super.onPause();
	}

	@Override
	protected void onPostResume() {
		 Log.d("EJEMPLO", "SeminarioAlt:onPostResume");		
		super.onPostResume();
	}

	@Override
	protected void onRestart() {
		 Log.d("EJEMPLO", "SeminarioAlt:onRestart");		
		super.onRestart();
	}

	@Override
	protected void onResume() {
		 Log.d("EJEMPLO", "SeminarioAlt:onResume");		
		super.onResume();
	}

	@Override
	protected void onStart() {
		 Log.d("EJEMPLO", "SeminarioAlt:onStart");		
		super.onStart();
	}

	@Override
	protected void onStop() {
		 Log.d("EJEMPLO", "SeminarioAlt:onStop");		
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		 Log.d("EJEMPLO", "SeminarioAlt:onDestroy");		
		super.onDestroy();
	}
	
	
}
