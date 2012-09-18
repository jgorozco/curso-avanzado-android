package com.tid.servicioclient.service;

import android.os.Handler;

public class ThreadExecuted extends Thread {

	Handler handler;
	public int MyId=(int) Math.round((Math.random()*1000));
	public ThreadExecuted(Handler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public void run() {
		try {
            while(true) {
                sleep(1000);
                handler.sendEmptyMessage(MAX_PRIORITY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	
}
