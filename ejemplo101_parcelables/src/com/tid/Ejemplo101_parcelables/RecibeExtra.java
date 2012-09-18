package com.tid.Ejemplo101_parcelables;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tid.Ejemplo101_parcelables.model.DTOParcel;
import com.tid.Ejemplo101_parcelables.model.DTOSerialz;

public class RecibeExtra extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.recibir);
		 Bundle elementos=getIntent().getExtras();
		 Log.d("TID_EXAMPLE","tenemos los elementos["+String.valueOf(elementos.size())+"]");
		 DTOParcel parcel=elementos.getParcelable("_datosParcelable");
		 DTOSerialz serial=(DTOSerialz) elementos.getSerializable("_datosSerealizable");
		 Log.d("TID_EXAMPLE", "parcelable:"+parcel.getName()+"/"+parcel.getPhone()+"/"+parcel.getAddress());
		 Log.d("TID_EXAMPLE", "serial    :"+serial.getName()+"/"+serial.getPhone()+"/"+serial.getAddress());		 
	}

	@Override
	protected void onPause() {
		finish();
		// TODO Auto-generated method stub
		super.onPause();
	}
	public void backActivity(View target)
	{
		
/*		Intent i=new Intent();
		i.putExtra("datos_vuelta", "datosVuelta");
		getParent().setResult(Activity.RESULT_OK, i);*/
		finish();
	}
	

}
