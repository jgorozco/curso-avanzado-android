package com.tid.Ejemplo73_manifest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class Activity_imagen extends Activity {

	public static String TAG="TID_EXAMPLE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Uri uri = (Uri) getIntent().getExtras().getParcelable(Intent.EXTRA_STREAM);
		if (uri!=null)
		{
			Log.d(TAG,"Estamos intentando abrir la imagen:"+uri.getPath());
		}else
		{
			Log.d(TAG,"no tenemos imagen");
		}
		
	}

}
