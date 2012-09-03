package com.moob;

import java.nio.ByteBuffer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.itwizard.mezzofanti.OCR;

public class AR_dniActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888; 

    /** Called when the activity is first created. */
	private static final String SDCARD_TEMP_JPG = "/mnt/sdcard/DCIM/temp_demotival.jpg";
	public TextView texto;
	public FrameLayout fl;
	public Preview preview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fl=(FrameLayout) findViewById(R.id.frameLayout1);
        texto=(TextView) findViewById(R.id.textView1);
        texto.setText(OCR.get().libVer());
   //     preview = new Preview(this);
   //     fl.addView(preview);
    }
    
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_REQUEST) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
            int bytes = photo.getWidth()*photo.getHeight()*4; //calculate how many bytes our image consists of. Use a different value than 4 if you don't use 32bit images.

            ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
            photo.copyPixelsToBuffer(buffer); //Move the byte data to the buffer

            byte[] array = buffer.array();
            String str=OCR.get().ImgOCRAndFilter(array, photo.getWidth(), photo.getHeight(), photo.getDensity());
            texto.setText("-->"+str);
        }  
    } 
    public void clickOCR(View target)
    {
    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
        startActivityForResult(cameraIntent, CAMERA_REQUEST); 
    	
    }
    
    public void __clickOCR(View target)
    {
        
    	
    	Log.d("TAG","tratar imagen");
        /** Handles data for raw picture */
        PictureCallback rawCallback = new PictureCallback() {
                public void onPictureTaken(byte[] data, Camera camera) {
                      Log.d("TAG", "onPictureTaken - raw");
                //      texto.setText("Texto modificado");
                      Bitmap bm=BitmapFactory.decodeByteArray(data, 0, data.length);
                      texto.setText(OCR.get().ImgOCRAndFilter(data, 
                    		  bm.getWidth(), 
                    		  bm.getHeight(), bm.getDensity()));
                }
        };
        ShutterCallback shutterCallback = new ShutterCallback() {
            public void onShutter() {
            //      Log.d(TAG, "onShutter'd");
            }
    };
        /** Handles data for jpeg picture */
        PictureCallback jpegCallback = new PictureCallback() {
                public void onPictureTaken(byte[] data, Camera camera) {
              /*          FileOutputStream outStream = null;
                        try {

                                outStream = new FileOutputStream(SDCARD_TEMP_JPG);
                                outStream.write(data);
                                outStream.flush();
                                outStream.close();
                                
                                
                        //      Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
                        } catch (FileNotFoundException e) {
                                e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        } finally {
                        }
                //      Log.d(TAG, "onPictureTaken - jpeg");*/
                }
        };
        preview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);    	
    	
    	
    }
    
    
    AutoFocusCallback myAutoFocusCallback = new AutoFocusCallback(){

    	  @Override
    	  public void onAutoFocus(boolean arg0, Camera arg1) {
    	   // TODO Auto-generated method stub
    		  Log.d("TAG", "on autofocus");
    	//   .setEnabled(true);
    	  }};
 /*   @Override
    protected void onStart() {
            Log.d("OOPPS","onStart!");
            super.onStart();
            if(preview.camera==null) {    
            	preview.camera = Camera.open();
            	Camera.Parameters parameters = preview.camera.getParameters();
        		parameters.setJpegQuality(50);
        		parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        		preview.camera.setParameters(parameters);
        		preview.camera.startPreview();
                preview.camera.autoFocus(myAutoFocusCallback);

            }
            else
            {
                    preview.camera.release();
                    preview.camera.open();
                    preview.camera.autoFocus(myAutoFocusCallback);
                    
                    preview.requestLayout();
            }
    }



    @Override
    protected void onStop() {
            Log.d("OOPPS","onStop!");
            super.onStop();
            if (preview.camera != null) {
                    preview.camera.release();
                    preview.camera = null;
            }
    }
*/
}