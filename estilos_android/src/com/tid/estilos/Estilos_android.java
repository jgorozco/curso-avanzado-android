package com.tid.estilos;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Estilos_android extends Activity {
    /** Called when the activity is first created. */
	//http://developer.android.com/guide/topics/resources/drawable-resource.html
	//xml dependiendo del sistema
	//estilos personalizados
	//fuentes personalizadas
	//drawables basados en formas, shapes
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView ctv=(TextView) findViewById(R.id.custom_font);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/harabara.ttf");  
        ctv.setTypeface(font);  
    }
}