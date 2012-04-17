package com.demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.util.Log;

public class FileDownloader extends AsyncTask<String, Integer, String> {
	private final static String TAG = "FileDownloader";

	@Override
	protected final String doInBackground(String... targets) {
		if (targets.length < 2) {
			return null;
		}

		this.publishProgress(0);

		String jarUrl = targets[0];
		String targetDirectoryPath = targets[1];
		String targetFilename;
		if (targets.length == 3) {
			targetFilename = targets[2];
		} else {
			String[] urlParts = jarUrl.split("/");
			targetFilename = urlParts[urlParts.length - 1];
		}

		// Create the target directory if necessary.
		File file = new File(targetDirectoryPath);
		if (!file.exists()) {
			file.mkdirs();
		}

		int count;
		try {
			URL url = new URL(jarUrl);
			URLConnection connection = url.openConnection();
			connection.connect();

			int lenghtOfFile = connection.getContentLength();

			String targetFilePath = targetDirectoryPath + "/" + targetFilename;

			InputStream input = new BufferedInputStream(url.openStream());
			OutputStream output = new FileOutputStream(targetFilePath);

			byte data[] = new byte[1024];

			long total = 0;

			while ((count = input.read(data)) != -1) {
				total += count;
				this.publishProgress((int) (total * 100 / lenghtOfFile));
				output.write(data, 0, count);
			}

			output.flush();
			output.close();
			input.close();

			return targetFilePath;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}

		this.publishProgress(100);

		return null;
	}
}