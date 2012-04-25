package com.tid.opengljava;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Ejemplo_opengl_java extends Activity {
    public static final String TID_DEMO = "TID_DEMO";
	/** Called when the activity is first created. */
	public GLSurfaceView surface;
	//http://blog.jayway.com/2009/12/03/opengl-es-tutorial-for-android-part-i/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        surface=(GLSurfaceView) findViewById(R.id.surfaceView1);
        Renderer renderer=createRender();
		surface.setRenderer(renderer);
		surface.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.d(TID_DEMO, "touchListener");
				return false;
			}
		});
    }
    

	public void onclick1(View target)
    {
    	
    	
    }
    public void onclick2(View target)
    {
    	
    	
    }

    
    
    private Renderer createRender() {
    	
		Renderer render=new Renderer() {
		    private float _red = 0.9f;
		    private float _green = 0.2f;
		    private float _blue = 0.2f;
		    float giro=0.0f;
		    private ShortBuffer _indexBuffer;
		    private FloatBuffer _vertexBuffer;
		    private short[] _indicesArray = {0, 1, 2};
		 	private int _nrOfVertices = 3;
		    FloatBuffer diffuseMaterialBuffer=OpenGLUtils.allocateFloatBuffer(4*4);

			@Override
			public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
				ByteBuffer vbb = ByteBuffer.allocateDirect(_nrOfVertices * 3 * 4);
			    vbb.order(ByteOrder.nativeOrder());
			    _vertexBuffer = vbb.asFloatBuffer();
				 float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		  	     float light_position[] = { 1.0f, 1.0f, 1.0f, 0.0f };			 
			    // short has 2 bytes
			    ByteBuffer ibb = ByteBuffer.allocateDirect(_nrOfVertices * 2);
			    ibb.order(ByteOrder.nativeOrder());
			    _indexBuffer = ibb.asShortBuffer();
			 
			    float[] coords = {
			        -0.5f, -0.5f, 0f, // (x1, y1, z1)
			        0.5f, -0.5f, 0f, // (x2, y2, z2)
			        0f, 0.5f, 0f // (x3, y3, z3)
			    };
			 
			    _vertexBuffer.put(coords);
			    _indexBuffer.put(_indicesArray);
			 
			    _vertexBuffer.position(0);
			    _indexBuffer.position(0);
			    
			  	gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, diffuseMaterialBuffer);
			  	gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, FloatBuffer.wrap(mat_specular));
			  	gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 25.0f);
			  	gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, FloatBuffer.wrap(light_position));
			  	gl.glEnable(GL10.GL_LIGHTING);
			  	gl.glEnable(GL10.GL_LIGHT0);
			    
			    
				
			}
			
			@Override
			public void onSurfaceChanged(GL10 gl, int width, int height) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDrawFrame(GL10 gl) {
				//Log.d(TID_DEMO, "drawing frame!"+String.valueOf(giro));
				   gl.glClearColor(_red, _green, _blue, 1.0f);
				   
				    // clear the color buffer to show the ClearColor we called above...
				    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
				 
				    // set the color of our element
				    gl.glColor4f(0.5f, 0f, 0f, 0.5f);

				    // define the vertices we want to draw
				    gl.glRotatef(1.0f, 0.0f, 1.0f, 1.0f);
				    GLUT.glutSolidBox(gl, 1.0f,  1.0f,  1.0f);
				    // finally draw the vertices
				
			}
		};
		return render;
	}


    
}