package com.tid.Ejemplo102_barcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Ejemplo102_barcode extends Activity {
    /** Called when the activity is first created. */
	TextView barcode;
	TextView type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        barcode=(TextView) findViewById(R.id.textView1);
        type=(TextView) findViewById(R.id.textView2);
    }
    
    public void scannCode(View target)
    {
    	  Intent intent = new Intent("com.google.zxing.client.android.SCAN");
          intent.setPackage("com.google.zxing.client.android");
          intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
          
          //http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/Intents.java
          startActivityForResult(intent, 0);
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("TID_EXAMPLE","req["+String.valueOf(requestCode)+"] resut["+String.valueOf(resultCode)+"]");
		 if (requestCode == 0) {
		        if (resultCode == RESULT_OK) {
		        	barcode.setText(data.getStringExtra("SCAN_RESULT"));
		        	type.setText(data.getStringExtra("SCAN_RESULT_FORMAT"));
		            // Handle successful scan
		        } else if (resultCode == RESULT_CANCELED) {
		            // Handle cancel
		        }
		    }
		super.onActivityResult(requestCode, resultCode, data);
	}
    
    
}