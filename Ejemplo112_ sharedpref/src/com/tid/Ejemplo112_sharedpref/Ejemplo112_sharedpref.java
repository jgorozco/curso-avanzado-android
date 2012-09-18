package com.tid.Ejemplo112_sharedpref;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Ejemplo112_sharedpref extends Activity {
    /** Called when the activity is first created. */
    public EditText name;
    public EditText pass;
    public SharedPreferences settings;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        settings = getSharedPreferences("credenciales", 0);
        name=(EditText) findViewById(R.id.editText1);
        pass=(EditText) findViewById(R.id.editText2);
        String nm=settings.getString("name", "");
        if (!nm.equals(""))
        {
        	name.setText(nm);
        }
        String ps=settings.getString("pass", "");
        if (!ps.equals(""))
        {
        	pass.setText(ps);
        }
    }
    
    public void saveShared(View target)
    {
    	String nm=name.getText().toString();
    	String ps=pass.getText().toString();
    	if ((nm!=null)&&
   			(ps!=null)&&
   			(nm.length()>1)&&
   			(ps.length()>1)){
    		 SharedPreferences.Editor editor = settings.edit();
    		 editor.putString("name", nm);
    		 editor.putString("pass", ps);
    		 editor.commit();
    	}
    	
    }
}