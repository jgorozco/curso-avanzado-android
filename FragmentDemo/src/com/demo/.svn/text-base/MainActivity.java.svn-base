package com.demo;

import java.io.File;
import java.io.FilenameFilter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final static int PROGRESS_DIALOG = 1;
	private final static String APP_DIRECTORY = "fragmentdemo";
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);

		Button downloadFragment = (Button) this
				.findViewById(R.id.download_fragment);
		downloadFragment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new FragmentDownloader()
						.download("http://upload.visusnet.de/ECi/fragment.zip");
			}
		});

		Button loadFragmentCache = (Button) this
				.findViewById(R.id.load_fragment_cache);
		loadFragmentCache.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new FragmentCacheLoader().execute();
			}
		});

		Button startDemo = (Button) this.findViewById(R.id.start_demo);
		startDemo.setEnabled(false);
		startDemo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						DynamicFragmentActivity.class);
				intent.putExtra("class_name", "com.demo.DemoFragment");
				startActivity(intent);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case PROGRESS_DIALOG:
			this.progressDialog = new ProgressDialog(this);
			return this.progressDialog;
		default:
			return null;
		}
	}

	private class FragmentDownloader extends FileDownloader {
		public void download(String jarUrl) {
			String fragmentDirectory = Environment
					.getExternalStorageDirectory() + "/" + APP_DIRECTORY;
			this.execute(jarUrl, fragmentDirectory);
		}

		@Override
		protected void onPreExecute() {
			showDialog(PROGRESS_DIALOG);
			progressDialog.setMessage("Downloading Fragment...");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMax(100);
		}

		@Override
		protected void onProgressUpdate(final Integer... values) {
			progressDialog.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(String jarPath) {
			if (jarPath != null) {
				// Add the APK/JAR/ZIP file's classes to the class loader.
				MultiDexClassLoader.getInstance().install(MainActivity.this,
						jarPath);

				Button startDemo = (Button) findViewById(R.id.start_demo);
				startDemo.setEnabled(true);
			}

			dismissDialog(PROGRESS_DIALOG);
		}
	}

	private class FragmentCacheLoader extends AsyncTask<Void, Void, Integer> {

		@Override
		protected void onPreExecute() {
			showDialog(PROGRESS_DIALOG);
			progressDialog.setMessage("Loading Fragment Cache...");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		}

		@Override
		protected Integer doInBackground(Void... params) {
			String fragmentDirectory = Environment
					.getExternalStorageDirectory() + "/" + APP_DIRECTORY;
			File fragmentDir = new File(fragmentDirectory);

			if (fragmentDir.exists()) {
				int numFragments = 0;
				for (File fragmentFile : fragmentDir
						.listFiles(new FragmentFileFilter())) {
					MultiDexClassLoader.getInstance().install(
							MainActivity.this, fragmentFile.getAbsolutePath());
					numFragments++;
				}
				return numFragments;
			}

			return 0;
		}

		@Override
		protected void onPostExecute(Integer result) {
			dismissDialog(PROGRESS_DIALOG);
			if (result > 0) {
				Button startDemo = (Button) findViewById(R.id.start_demo);
				startDemo.setEnabled(true);
			} else {
				Toast.makeText(MainActivity.this,
						"Could not find any cached Fragments",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	private class FragmentFileFilter implements FilenameFilter {
		@Override
		public boolean accept(File dir, String filename) {
			if (filename.endsWith(".apk") || filename.endsWith(".jar")
					|| filename.endsWith(".zip")) {
				return true;
			}
			return false;
		}
	}
}
