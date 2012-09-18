package com.tid.Ejemplo113_ficherosSd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Ejemplo113_ficherosSd extends Activity {
    public static final String TAG = "TID_EXAMPLE";
    public EditText texto;
    public TextView listado;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        texto=(EditText) findViewById(R.id.editText1);
        listado=(TextView) findViewById(R.id.textView1);
        leerFichero();
    }

    
    public void appendFichero(String dato)
    { 
    	FileOutputStream ficheroSalida;
    	String pathCarpeta=Environment.getExternalStorageDirectory()+"/ficherosSD";
    	String pathFichero=pathCarpeta+"/guardado.txt";
       
    	 File fichero=new File(pathFichero);
         if (!fichero.exists())
         {
        	 try {
         		new File(pathCarpeta).mkdirs();
				fichero.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         try {
				ficheroSalida=new FileOutputStream(fichero,true);
				OutputStreamWriter output = new OutputStreamWriter(ficheroSalida);
				BufferedWriter writer=new BufferedWriter(output);
				writer.write(dato+"\n");
				writer.flush();
				writer.close();
				output.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     	
    }
    
    public void escribirDatos(View Target)
    {
    	appendFichero(texto.getText().toString());
    	leerFichero();
    }
    
	private void leerFichero() {
		FileInputStream ficheroEntrada;
        String pathFichero=Environment.getExternalStorageDirectory()+"/ficherosSD/guardado.txt";

        File fichero=new File(pathFichero);
        if (fichero.exists())
        {
        	try {
        		ficheroEntrada=new FileInputStream(fichero);
        		InputStreamReader inputreader = new InputStreamReader(ficheroEntrada);
        		BufferedReader buffreader = new BufferedReader(inputreader);
        		String completo;
        		String line=buffreader.readLine();
        		completo=line;
        		while ((line!=null)&&(line.length()>0))
        		{
        			Log.d(TAG, "readline>"+line);
        			line=buffreader.readLine();
        			completo=completo+"\n"+line;
        		}
        		buffreader.close();
        		ficheroEntrada.close();
        		listado.setText(completo);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }else
        {
        	listado.setText("fichero no creado");
        }
	}
}