package com.tid.Ejemplo11_alm_bbdd;

import java.util.Iterator;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Ejemplo11_alm_bbdd extends Activity {
    /** Called when the activity is first created. */
	public EditText nombre;
	public EditText telef;
	public EditText addr;
	public TextView myText;
	public DatabaseHelper myHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        nombre=(EditText) findViewById(R.id.name);
        telef=(EditText) findViewById(R.id.phone);
        addr=(EditText) findViewById(R.id.addr);
        myText=(TextView) findViewById(R.id.alltext);
        myHelper=new DatabaseHelper(getApplicationContext(), "agenda.db", null, 1);
    }
    
    public void addElement(View target)
    {
    	SQLiteDatabase db = myHelper.getWritableDatabase();
    	ContentValues values=new ContentValues();
    	values.put(DatabaseHelper.NAME, nombre.getText().toString());
    	values.put(DatabaseHelper.PHONE, telef.getText().toString());
    	values.put(DatabaseHelper.ADDR, addr.getText().toString());
		db.insert(DatabaseHelper.TABLE_NAME, null, values);

    }

    public void deleteElement(View target)
    {
    	String name=nombre.getText().toString();
    	if ((name!=null)||(name.length()>1))
    	{
        	SQLiteDatabase db = myHelper.getWritableDatabase();
        	ContentValues values=new ContentValues();
        	db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.NAME+"='"+name+"'",null);

    	}
    }
    
    public void refreshElements(View target)
    {
    	SQLiteDatabase db = myHelper.getReadableDatabase();
    	Cursor c=db.query(DatabaseHelper.TABLE_NAME,null,null,null,null,null,null);
    	String datos="";
    	while (c.moveToNext()) {
			int cols=c.getColumnCount();
			
			for (int i=0;i<cols;i++)
			{
				datos=datos+"::"+c.getColumnName(i)+"="+c.getString(i);
			}
			datos=datos+"\n";
			
		}
    	myText.setText(datos);
    }
}