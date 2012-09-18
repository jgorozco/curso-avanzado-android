package com.tid.Ejemplo91_threadHandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ThreadLoop extends Thread {

	public Handler manejador;
	
	
	
	
	public ThreadLoop(Handler manejador) {
		super();
		this.manejador = manejador;
	}




	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				Log.d("TID_EXAMPLE", "ThreadLoop keeponloop");
				int num=(int) (Math.random()*1000);
				Message msg=new Message();
				Bundle data=new Bundle();
				data.putString("datos", "enviado desde el thread por mensaje"+String.valueOf(num));
				msg.setData(data);
				manejador.sendMessage(msg);


			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

}
