package com.tid.broadcastWakeup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastWakeup extends BroadcastReceiver {
	public static String TAG="TID_EXAMPLE";
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Log.d(TAG,"WAKE UP!action["+action+"]== ["+intent.getDataString()+"] ["+intent.getPackage()+"] ");
		 Intent i = new Intent(context, ejemplo5_broadcastWakeup.class);
		 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 context.startActivity(i);

	}

}
