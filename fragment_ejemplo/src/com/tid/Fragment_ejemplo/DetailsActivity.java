package com.tid.Fragment_ejemplo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class DetailsActivity extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       setContentView(R.layout.details);
	       WebView wb=(WebView) findViewById(R.id.web_view);
	       String content = getIntent().getData().toString();
	       wb.loadUrl(content);
	        
	 }
}
