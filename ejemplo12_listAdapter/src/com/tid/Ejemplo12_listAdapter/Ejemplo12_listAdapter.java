package com.tid.Ejemplo12_listAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
public class Ejemplo12_listAdapter extends Activity {
    /** Called when the activity is first created. */
	public EditText editText;
	public ListView listado;
    public MyListAdapter adapter;
    public ArrayList<HashMap<String, String>> listadoData;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        editText=(EditText) findViewById(R.id.editText1);
        listado=(ListView)findViewById(R.id.listView1);
        listadoData=new ArrayList<HashMap<String,String>>();
    	HashMap<String, String> map=new HashMap<String, String>();
    	map.put("name", "defecto");
    	map.put("number", "444435415");
		listadoData.add(map);     
        adapter=new MyListAdapter(listadoData);
        listado.setAdapter(adapter);
    }
    
    public void aniadirListado(View target)
    {
    	String dato=editText.getText().toString();
    	editText.setText("");
    	String random=String.valueOf(Math.random()*100);
    	HashMap<String, String> map=new HashMap<String, String>();
    	map.put("name", dato);
    	map.put("number", random);
    	listadoData.add(map);
    	adapter.notifyDataSetChanged();
	//	InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	//	imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

    }
}