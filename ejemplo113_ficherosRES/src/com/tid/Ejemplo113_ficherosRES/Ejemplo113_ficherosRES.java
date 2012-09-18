package com.tid.Ejemplo113_ficherosRES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
//http://developer.android.com/guide/topics/resources/providing-resources.html
public class Ejemplo113_ficherosRES extends Activity {
    public static final String TID_EXAMPLE = "TID_EXAMPLE";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
        	/*String FILENAME = "fichero_pruebaPrivado";
        	String string = "fichero de prueba";

        	FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        	fos.write(string.getBytes());
        	fos.close();*/
            //acceso a fichero RAW
            InputStream inputStream = getApplication().getResources().openRawResource(R.raw.fichero);
        	InputStreamReader inputreader = new InputStreamReader(inputStream);
			BufferedReader buffreader = new BufferedReader(inputreader);
			String line=buffreader.readLine();
			while ((line!=null)&&(line.length()>0))
			{
				Log.d(TID_EXAMPLE, "RAW>"+line);
				line=buffreader.readLine();
			}
            //acceso a fichero XML
			XmlResourceParser xpp = getApplication().getResources().getXml(R.xml.fichero);
			Log.d(TID_EXAMPLE, "+"+xpp.toString());   
            
            //acceso a assets
        	inputreader = new InputStreamReader(getApplication().getAssets().open("fichero.bin"));
			buffreader = new BufferedReader(inputreader);
			line=buffreader.readLine();
			while ((line!=null)&&(line.length()>0))
			{
				Log.d(TID_EXAMPLE, "ASSET>"+line);
				line=buffreader.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}