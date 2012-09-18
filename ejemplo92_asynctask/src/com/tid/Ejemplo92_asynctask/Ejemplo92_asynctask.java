package com.tid.Ejemplo92_asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Ejemplo92_asynctask extends Activity {
    /** Called when the activity is first created. */
    TextView texto;
    TareaAsync tarea;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        texto=(TextView) findViewById(R.id.text_data);
        texto.setText("iniciado todo");
        initAsyncTask();
    }
	private void initAsyncTask() {
		tarea=new TareaAsync();
		tarea.execute("13");
		
	}
	
	private void updateUi(String str)
	{
		texto.setText(str);
	}
	
	
	
	@Override
	protected void onStop() {
		Log.d("TID_EXAMPLE","pasamos por onStop");
		tarea.cancel(true);	
	//	this.finish();
		try {
			finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onPause();
	}



	public class TareaAsync extends AsyncTask<String, Integer, Integer> {

		public int proceso=0;
		public int total;
		
		@Override
		protected Integer doInBackground(String... arg0) {
			Log.d("TID_EXAMPLE","doInBackground with"+arg0[arg0.length-1]);
			String entrada=arg0[arg0.length-1];
			total=Integer.parseInt(entrada);
			while (total>proceso)
			{
				try {
					Thread.sleep(1000);
					Log.d("TID_EXAMPLE","click");
					proceso=proceso+1;
					publishProgress(proceso);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return total;
		}

		@Override
		protected void onCancelled() {
			Log.d("TID_EXAMPLE","onCancelled");
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(Integer result) {
			Log.d("TID_EXAMPLE","onPostExecute["+String.valueOf(result)+"]");
			
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			Log.d("TID_EXAMPLE","onPreExecute");
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Log.d("TID_EXAMPLE","onProgressUpdate["+String.valueOf(values[0])+"]");
			updateUi(String.valueOf(values[0]));
			super.onProgressUpdate(values);
		}
		

	}
	
}