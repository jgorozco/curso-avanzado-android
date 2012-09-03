package com.moob;


import java.io.IOException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

class Preview extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG = "Preview";

	SurfaceHolder mHolder;
	public Camera camera=null;
	public Context cnxt;

	public int scuareW;
	Preview(Context context) {
		super(context);
		cnxt=context;
		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, acquire the camera and tell it where
		// to draw.
		try{
		Display display = ((WindowManager) cnxt.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		if (display.getRotation()==0){
			camera.setDisplayOrientation(90);
		}
		if (display.getRotation()==270){
			camera.setDisplayOrientation(180);
		}
		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}catch (Exception e) {
			Log.d("TAG", "ERROR AL USAR EL GET ROTATION:"+e.getLocalizedMessage());
		}
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
		if (camera != null) {
			//          mSupportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
			requestLayout();
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// Surface will be destroyed when we return, so stop the preview.
		// Because the CameraDevice object is not a shared resource, it's very
		// important to release it when the activity is paused.
		//	camera.stopPreview();
		//	camera = null;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// Now that the size is known, set up the camera parameters and begin
		// the preview.
		Camera.Parameters parameters = camera.getParameters();
		parameters.setJpegQuality(50);
		parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		camera.setParameters(parameters);
		camera.startPreview();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Display display = ((WindowManager) cnxt.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		if (display.getRotation()==0){
			camera.setDisplayOrientation(90);
		}
		if (display.getRotation()==270){
			camera.setDisplayOrientation(180);
		}
		super.onDraw(canvas);
	}

	@Override
	public void draw(Canvas canvas) {

		super.draw(canvas);
		Paint p = new Paint(Color.RED);
		Log.d(TAG, "draw");
		canvas.drawText("PREVIEW", canvas.getWidth() ,
				canvas.getHeight(), p);
	}
}