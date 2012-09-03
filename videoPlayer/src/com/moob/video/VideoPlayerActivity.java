package com.moob.video;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

public class VideoPlayerActivity extends Activity {
    /** Called when the activity is first created. */
	public VideoView vista1;
	public VideoView vista2;
	public VideoView vista3;
	public VideoView vista4;
	public VideoView vista5;
	public VideoView vista6;
	public ArrayList<String> urls;
	public ArrayList<VideoView> videos;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        videos=new ArrayList<VideoView>();
        vista1 =(VideoView) findViewById(R.id.videoView1);
        vista2 =(VideoView) findViewById(R.id.videoView2);
        vista3 =(VideoView) findViewById(R.id.videoView3);
        vista4 =(VideoView) findViewById(R.id.videoView4);
        vista5 =(VideoView) findViewById(R.id.videoView5);
        vista6 =(VideoView) findViewById(R.id.videoView6);
        videos.add(vista1);
        videos.add(vista2);
        videos.add(vista3);
        videos.add(vista4);
        videos.add(vista5);
        videos.add(vista6);
        
        
        urls=new ArrayList<String>();
        urls.add("rtsp://v5.cache3.c.youtube.com/CiILENy73wIaGQmJpIH6s-gCvRMYDSANFEgGUgZ2aWRlb3MM/0/0/0/video.3gp");
        urls.add("rtsp://v1.cache1.c.youtube.com/CiILENy73wIaGQlI28H9wT_t1RMYESARFEgGUgZ2aWRlb3MM/0/0/0/video.3gp");
        urls.add("rtsp://v1.cache2.c.youtube.com/CiILENy73wIaGQmAnU4e9TLq5RMYESARFEgGUgZ2aWRlb3MM/0/0/0/video.3gp");
        urls.add("rtsp://v7.cache1.c.youtube.com/CiILENy73wIaGQlkj-8U0R7r0xMYESARFEgGUgZ2aWRlb3MM/0/0/0/video.3gp");
        urls.add("rtsp://v7.cache8.c.youtube.com/CiILENy73wIaGQmsObW1caaIhxMYESARFEgGUgZ2aWRlb3MM/0/0/0/video.3gp");
        urls.add("rtsp://v7.cache2.c.youtube.com/CiILENy73wIaGQlXuZR407T8rBMYESARFEgGUgZ2aWRlb3MM/0/0/0/video.3gp");
    }
    
    
    private class DownloadImageTask extends AsyncTask<Integer, Void, String> {
        /** The system calls this to perform work in a worker thread and
          * delivers it the parameters given to AsyncTask.execute() 
         * @return */
        protected String doInBackground(Integer... position) {
        	Integer i=position[0];
        	if (videos.get(i.intValue()).isPlaying())
        	{
        		Log.d("EX", "Parando");
        		videos.get(i.intValue()).stopPlayback();
        		
        	}else
        	{
        		Log.d("EX", "empezando");
        		videos.get(i.intValue()).setVideoURI(Uri.parse(urls.get(i.intValue())));
        		videos.get(i.intValue()).requestFocus();
        		videos.get(i.intValue()).showContextMenu();
        		videos.get(i.intValue()).start();  
        	}
        	
        	return "";
        }
        
    }
    
    public void executeVideo(int position)
    {
    	Integer i=new Integer(position);
    	if (videos.get(i.intValue()).isPlaying())
    	{
    		Log.d("EX", "Parando");
    		videos.get(i.intValue()).stopPlayback();
    		
    	}else
    	{
    		Log.d("EX", "empezando");
    		videos.get(i.intValue()).setVideoURI(Uri.parse(urls.get(i.intValue())));
    		//videos.get(i.intValue()).requestFocus();
    		//videos.get(i.intValue()).showContextMenu();
    		videos.get(i.intValue()).start();  
    	}
    	
    	
    	
    }
    
    public void video1Play(View target)
    {
    	executeVideo(0);
    }
    public void video2Play(View target)
    {
    	executeVideo(1);
    }
    public void video3Play(View target)
    {
    	executeVideo(2);
    }
    public void video4Play(View target)
    {
    	executeVideo(3);
    }
    public void video5Play(View target)
    {
    	executeVideo(4);
    }
    public void video6Play(View target)
    {
    	executeVideo(5);
    }
    
}