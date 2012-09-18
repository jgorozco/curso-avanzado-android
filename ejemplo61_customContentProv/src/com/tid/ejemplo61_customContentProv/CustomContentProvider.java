package com.tid.ejemplo61_customContentProv;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class CustomContentProvider extends ContentProvider {
	public static String TAG="TID_EXAMPLE";
	public static final String CONTENT="com.tid.ejemplo61_customContentProv.mycustomprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://"+CONTENT);
	public static ArrayList<HashMap<String, String>> elementos; 
	
	public static final String DBNAME="mydata";
	public static final String NAME="name";
	public static final String DETAILS="details";
	public static final String DATE="date";
	public static final String TIME="time";
    private static final String DATABASE_NAME = "basedatos.db";
    private DatabaseHelper dbHelper;
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		 SQLiteDatabase db = dbHelper.getWritableDatabase();
	        int count;
	        count = db.delete(DBNAME, selection, selectionArgs);
	        return count;
	}

	
	@Override
	public String getType(Uri uri) {
		Log.d(TAG,"CustomContentProvider: getType");
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		Log.d(TAG,"CustomContentProvider: insert");
		
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insert(DBNAME, DETAILS, values);
		return  Uri.parse("content://"+CONTENT+"/"+String.valueOf(rowId));
	}

	@Override
	public boolean onCreate() {
		Log.d(TAG,"CustomContentProvider: onCreate");
		dbHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(DBNAME);
//		qb.setProjectionMap(notesProjectionMap);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
//yaps		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		count = db.update(DBNAME, values, selection, selectionArgs);
		//        getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	static
	{
		Log.d(TAG,"CustomContentProvider: static!");
		elementos=new ArrayList<HashMap<String,String>>();
		
	}

	
	private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        	String creaciondb="CREATE TABLE " +DBNAME+" (id_datos INTEGER PRIMARY KEY AUTOINCREMENT," +
			NAME + " VARCHAR(255)," + 
			DETAILS+ "  VARCHAR(255)," +
			DATE +" VARCHAR(255),"+
			TIME+" VARCHAR(255)"+
			");";
        	Log.d(TAG,"CustomContentProvider:CREACION["+creaciondb+"]");
            db.execSQL(creaciondb);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                    + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS mydata");
            onCreate(db);
        }
    }

   
	
	
	
}
