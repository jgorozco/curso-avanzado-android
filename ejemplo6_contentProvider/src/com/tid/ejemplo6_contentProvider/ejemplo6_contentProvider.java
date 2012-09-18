package com.tid.ejemplo6_contentProvider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.Browser.BookmarkColumns;
import android.util.Log;

public class ejemplo6_contentProvider extends Activity {
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
     //   addingToContentProvider();
        updatingToContentProvider();
        Log.d(TAG, "_____________LISTING___________");
        Log.d(TAG, "__________ONE MORE TIME________");
        listingContentProvider();
    }
	private void deletingFromContentProvider() {

		getContentResolver().delete(Browser.BOOKMARKS_URI, BookmarkColumns.TITLE+"=?", new String[]{"ejemplo4tid5"});
		
	}
	private void updatingToContentProvider() {
		ContentValues values = new ContentValues();

		values.put(Browser.BookmarkColumns.TITLE, "ejemplo4tid6");
		values.put(Browser.BookmarkColumns.URL, "http://www.mobot.es/index.html");
		values.put(Browser.BookmarkColumns.BOOKMARK,1);
		values.put(Browser.BookmarkColumns.VISITS,"2");
		values.put(Browser.BookmarkColumns.DATE, "0");
		values.put(Browser.BookmarkColumns.CREATED,"1307615875");
		values.put("folder","0");
		getContentResolver().update(Browser.BOOKMARKS_URI, values, BookmarkColumns._ID+"=?", new String[]{"5"});
	//	getContentResolver().insert(Browser.BOOKMARKS_URI, values);
		
	}
	
	
	private void addingToContentProvider() {
		ContentValues values = new ContentValues();
	//	values.put(Browser.BookmarkColumns._ID, "3");
		values.put(Browser.BookmarkColumns.TITLE, "ejemplo4tid");
		values.put(Browser.BookmarkColumns.URL, "http://www.mobot.es/index.html");
		values.put(Browser.BookmarkColumns.BOOKMARK,"2");
		values.put(Browser.BookmarkColumns.VISITS,"1");
		values.put(Browser.BookmarkColumns.CREATED,"1307615875");
		values.put("folder","0");
		getContentResolver().insert(Browser.BOOKMARKS_URI, values);
		
	}

	private void listingContentProvider() {
		// Cursor cur=managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,null, null, null, null);
   //     Cursor cur=managedQuery(Contacts.CONTENT_URI,null, null, null, null);// necesita de manifest
   //     Cursor cur=managedQuery(Settings.System.CONTENT_URI,null, null, null, null);
   //     Cursor cur=managedQuery(CallLog.Calls.CONTENT_URI,null, null, null, null);
		    Cursor cur=managedQuery(Browser.BOOKMARKS_URI,null, null, null, null);//necesita de manifest
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