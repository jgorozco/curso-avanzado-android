package com.tid.Ejemplo71_multipantalla;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ejemplo71_multipantalla extends Activity {
    /** Called when the activity is first created. */
	public static String TAG="TID_EXAMPLE";
	Button botonPrincipal;
	EditText texto;
	TextView listadoTexto;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "------onCreate");
        setContentView(R.layout.main);
        texto=(EditText) findViewById(R.id.main_edittext);
        listadoTexto=(TextView) findViewById(R.id.main_textview);
    }
    
    
    @Override
	protected void onDestroy() {
		Log.d(TAG, "------onDestroy");
		super.onDestroy();
	}


	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d(TAG, "---->>>>>onRestoreInstanceState ["+findViewById(texto.getId()).getClass().getName()+"]");
		String cuadro=	savedInstanceState.getString(String.valueOf(texto.getId()));
		String textolargo=savedInstanceState.getString(String.valueOf(listadoTexto.getId()));
		
		texto.setText(cuadro);
		listadoTexto.setText(textolargo);
		super.onRestoreInstanceState(savedInstanceState);
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d(TAG, "----<<<<<<onSaveInstanceState");
		String cuadro=	texto.getText().toString();
		String textolargo=listadoTexto.getText().toString();
		outState.putString(String.valueOf(texto.getId()), cuadro);
		outState.putString(String.valueOf(listadoTexto.getId()),textolargo);
		super.onSaveInstanceState(outState);
	}


	@Override
	protected void onPause() {
		Log.d(TAG, "--onPause");
		super.onPause();
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onPostCreate");
		super.onPostCreate(savedInstanceState);
	}


	@Override
	protected void onPostResume() {
		Log.d(TAG, "onPostResume");
		super.onPostResume();
	}


	@Override
	protected void onRestart() {
		Log.d(TAG, "<--->onRestart");
		super.onRestart();
	}


	@Override
	protected void onResume() {
		Log.d(TAG, "--onResume");
		super.onResume();
	}


	@Override
	protected void onStart() {
		Log.d(TAG, "----onStart");
		super.onStart();
	}


	@Override
	protected void onStop() {
		Log.d(TAG, "----onStop");
		super.onStop();
	}


	public void ClickButton(View Target)
    {
    	listadoTexto.append("\n"+texto.getText().toString());
    	texto.setText("");
    }
}