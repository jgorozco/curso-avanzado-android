package com.tid.Ejemplo101_parcelables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tid.Ejemplo101_parcelables.model.DTOParcel;
import com.tid.Ejemplo101_parcelables.model.DTOSerialz;

public class Ejemplo101_parcelables extends Activity {
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
		DTOParcel parcel=new DTOParcel("jose", "666555444", "arboleada 22");
		DTOSerialz serial=new DTOSerialz("luis", "444333222", "Distrito p");
		i.putExtra("_datosParcelable", parcel);
		i.putExtra("_datosSerealizable", serial);
		startActivity(i);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	//	Log.d("TID_EXAMPLE","resultado es..."+data.getExtras().getString("datos_vuelta"));
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
}