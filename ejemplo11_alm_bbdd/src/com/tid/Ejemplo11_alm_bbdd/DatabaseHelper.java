package com.tid.Ejemplo11_alm_bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static String TABLE_NAME="mis_datos";
	public static String NAME="nombre";
	public static String PHONE="telefono";
	public static String ADDR="direccion";
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		
		super(context, name, factory, version);
		Log.d("TID_EXAMPLE","C:DatabaseHelper");
	
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		Log.d("TID_EXAMPLE","onCreate");
		arg0.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
				   "id_"+TABLE_NAME  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                   NAME + " VARCHAR(255)," +
                   PHONE+ " VARCHAR(255)," + 
                   ADDR+  " VARCHAR(255));");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("TID_EXAMPLE","onUpgrade");
		  db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
          onCreate(db);

	}

}
