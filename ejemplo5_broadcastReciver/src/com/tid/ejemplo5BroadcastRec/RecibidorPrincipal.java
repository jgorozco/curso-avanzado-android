package com.tid.ejemplo5BroadcastRec;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;

public class RecibidorPrincipal extends BroadcastReceiver {

	private Activity myActivity;
	public static String TAG="TID_EXAMPLE";
	public RecibidorPrincipal(Activity myActivity) {
		super();
		this.myActivity = myActivity;
	}

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		String action = arg1.getAction();
		WifiManager _myWifiManager = (WifiManager) myActivity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		Log.d(TAG,"action["+action+"]== ["+arg1.getDataString()+"] ["+arg1.getPackage()+"] ");
		Log.d(TAG,"["+arg1.toString()+"]+wifi state["+_myWifiManager.getConnectionInfo().getSupplicantState().toString()+"]");

	}

	public void registerRecivers() {
	/*	myActivity.registerReceiver(this, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		myActivity.registerReceiver(this, new IntentFilter(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION));
		myActivity.registerReceiver(this, new IntentFilter(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION));
		
		myActivity.registerReceiver(this, new IntentFilter(WifiManager.EXTRA_SUPPLICANT_ERROR));
		myActivity.registerReceiver(this, new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));*/
	//	myActivity.registerReceiver(this, new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION));
		myActivity.registerReceiver(this, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
	}

	/**
	 * Unregister all recivers.
	 */
	public void unregisterRecivers() {
		myActivity.unregisterReceiver(this);

	}	
	
}
