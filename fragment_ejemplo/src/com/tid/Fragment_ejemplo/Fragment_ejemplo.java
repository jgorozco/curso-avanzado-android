package com.tid.Fragment_ejemplo;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class Fragment_ejemplo extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setListAdapter(ArrayAdapter.createFromResource(getApplicationContext(), R.array.list_elements,
        		android.R.layout.simple_list_item_1));
        getListView().setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				    Intent showContent = new Intent(getApplicationContext(),
				            DetailsActivity.class);
				    TextView tv=(TextView) arg1.findViewById(android.R.id.text1);
			        String s = tv.getText().toString();

				    showContent.setData(Uri.parse(s));
				    startActivity(showContent);
				
			}
        	
		});
    }
}