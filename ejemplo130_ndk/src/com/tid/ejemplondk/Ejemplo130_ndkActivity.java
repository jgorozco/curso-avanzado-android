package com.tid.ejemplondk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Ejemplo130_ndkActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        NdkLib.peticion1("enviado desde c++");
        String salida=NdkLib.peticion2("entrada 2º peticon");
        Log.d("TID_EXAMPLE", salida);
    }
}