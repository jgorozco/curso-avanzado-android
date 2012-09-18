package com.aurigae.seminario1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class seminario1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d("EJEMPLO", "Seminario1:onCreate");			
        Button b=(Button) findViewById(R.id.main_btn1);
        b.setOnClickListener(new OnClickListener() {
    	
			public void onClick(View v) {
				 Intent i = new Intent(getApplicationContext(), SeminarioAlt.class);
				 startActivity(i);
			}
		});
    }

	@Override
	protected void onPause() {
		 Log.d("EJEMPLO", "Seminario1:onPause");		
		super.onPause();
	}

	@Override
	protected void onPostResume() {
		 Log.d("EJEMPLO", "Seminario1:onPostResume");		
		super.onPostResume();
	}

	@Override
	protected void onRestart() {
		 Log.d("EJEMPLO", "Seminario1:onRestart");		
		super.onRestart();
	}

	@Override
	protected void onResume() {
		 Log.d("EJEMPLO", "Seminario1:onResume");		
		super.onResume();
	}

	@Override
	protected void onStart() {
		 Log.d("EJEMPLO", "Seminario1:onStart");		
		super.onStart();
	}

	@Override
	protected void onStop() {
		 Log.d("EJEMPLO", "Seminario1:onStop");		
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		 Log.d("EJEMPLO", "Seminario1:onDestroy");		
		super.onDestroy();
	}   
    
    
}