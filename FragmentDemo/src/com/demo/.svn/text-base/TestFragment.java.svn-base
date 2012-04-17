package com.demo;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.demo.AsyncImageLoader.IImageLoadListener;

public class TestFragment extends Fragment implements IImageLoadListener {
	private final static int TAKE_PICTURE = 1;
	private ImageView image;
	private Uri imageUri;
	private AsyncImageLoader asyncImageLoader;

	public TestFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.asyncImageLoader = new AsyncImageLoader();
	}

	// There is a known bug and this is the bug fix.
	// See: http://code.google.com/p/android/issues/detail?id=8488
	@Override
	public void onPause() {
		super.onPause();
		System.gc();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		this.image = null;
		this.imageUri = null;
		this.asyncImageLoader = null;

		System.gc();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelable("image_uri", this.imageUri);
		super.onSaveInstanceState(outState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_fragment, null);

		Button button = (Button) view.findViewById(R.id.camera_button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				File targetDir = new File(Environment
						.getExternalStorageDirectory(), Commons.APP_DIRECTORY);
				File photo = new File(targetDir, "demo.jpg");
				imageUri = Uri.fromFile(photo);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
				startActivityForResult(intent, TAKE_PICTURE);
			}
		});

		this.image = (ImageView) view.findViewById(R.id.image);

		if (savedInstanceState != null) {
			this.imageUri = savedInstanceState.getParcelable("image_uri");
			if (this.imageUri != null) {
				this.asyncImageLoader.loadDrawable(this.imageUri.toString(),
						this);
			}
		}

		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
			if (this.imageUri != null) {
				this.asyncImageLoader.loadDrawable(this.imageUri.toString(),
						this);
			}
		}
	}

	@Override
	public void imageLoaded(Drawable imageDrawable, String imageUrl) {
		this.image.setImageDrawable(imageDrawable);
	}
}
