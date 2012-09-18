package com.tid.ejemplo62_externalContent;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class ejemplo62_externalContent extends Activity {
	public static String TAG="TID_EXAMPLE";
	public static final String CONTENT="com.tid.ejemplo61_customContentProv.mycustomprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://"+CONTENT);
	public static final String NAME="name";
	public static final String DETAILS="details";
	public static final String DATE="date";
	public static final String TIME="time";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listingContentProvider();
        Log.d(TAG, "_____________ADDING____________");
        addingToContentProvider();
    }
    
	private void addingToContentProvider() {
		ContentValues values = new ContentValues();
	//	values.put(Browser.BookmarkColumns._ID, "3");
		values.put(NAME, "ejemplo primero3");
		values.put(DETAILS, "detalles de ejemplo4");
		values.put(DATE,"00/33/33333");
		values.put(TIME,"00/11/3311");
		getContentResolver().insert(CONTENT_URI, values);
		
	}
    
    
    private void listingContentProvider() {
		    Cursor cur=managedQuery(CONTENT_URI,null, null, null, null);//necesita de manifest
		    String columnas[]=cur.getColumnNames();
		    String filas[]=new String[columnas.length];
		    Log.d(TAG, "HEADERS["+String.valueOf(cur.getCount())+"]:"+getMensaje(columnas));
		    int rows=0;
		    while (cur.moveToNext())
		    {
		    	for (int i = 0; i < filas.length; i++) {
		    		try{
		    		filas[i]=":"+cur.getString(i);
		    		}catch(Exception e)
		    		{
		    			
		    		}
		    //		 Log.d(TAG,"-->"+cur.getString(i));
		    		
				}
		    	 rows++;
		    	 Log.d(TAG, "colum["+String.valueOf(rows)+"]:"+getMensaje(filas));
		    	 
		    	
		    }
	}
    
    public String getMensaje(String[] palabras){
		String mensaje="";
		for(int i=0; i<palabras.length; i++){	
			mensaje+=":"+palabras[i];
		}
		return mensaje;
	}    
    
}