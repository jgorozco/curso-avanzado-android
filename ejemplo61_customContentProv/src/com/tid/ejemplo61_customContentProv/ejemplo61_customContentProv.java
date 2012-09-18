package com.tid.ejemplo61_customContentProv;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class ejemplo61_customContentProv extends Activity {
	public static String TAG="TID_EXAMPLE";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "_____________LISTING___________");
        Log.d(TAG, "_______________________________");
        listingContentProvider();
        Log.d(TAG, "_____________ADDING____________");
        deletingFromContentProvider();
        Log.d(TAG, "_______________________________");
        addingToContentProvider();
        updatingToContentProvider();
        Log.d(TAG, "_____________LISTING___________");
        Log.d(TAG, "__________ONE MORE TIME________");
        listingContentProvider();
    }
    
    
	private void deletingFromContentProvider() {

		getContentResolver().delete(CustomContentProvider.CONTENT_URI, CustomContentProvider.NAME+"=?", new String[]{"ejemplo primero"});
		
	}
	private void updatingToContentProvider() {
		ContentValues values = new ContentValues();

		values.put(CustomContentProvider.NAME, "ejemplo primero2");
		values.put(CustomContentProvider.DETAILS, "detalles de ejemplo2");
		values.put(CustomContentProvider.DATE,"22/33/33333");
		values.put(CustomContentProvider.TIME,"11/11/1122");

		getContentResolver().update(CustomContentProvider.CONTENT_URI, values, CustomContentProvider.NAME+"=?", new String[]{"11/11/1111"});
	//	getContentResolver().insert(Browser.BOOKMARKS_URI, values);
		
	}
	
	
	private void addingToContentProvider() {
		ContentValues values = new ContentValues();
	//	values.put(Browser.BookmarkColumns._ID, "3");
		values.put(CustomContentProvider.NAME, "ejemplo primero3");
		values.put(CustomContentProvider.DETAILS, "detalles de ejemplo");
		values.put(CustomContentProvider.DATE,"22/33/33333");
		values.put(CustomContentProvider.TIME,"11/11/3311");
		getContentResolver().insert(CustomContentProvider.CONTENT_URI, values);
		
	}

	private void listingContentProvider() {
		    Cursor cur=managedQuery(CustomContentProvider.CONTENT_URI,null, null, null, null);//necesita de manifest
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