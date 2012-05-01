package com.tid.examples.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

/**
 * Android Bluetooth Example
 * 
 * Referencias:
 * 
 * http://developer.android.com/guide/topics/wireless/bluetooth.html
 * http://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html
 * 
 *
 */
public class BluetoothActivity extends Activity {

	private static final String TAG = "BluetoothActivity";

	//UUID generado aleatoriamente
	//Se usa tanto en el cliente como en el servidor
	private static final UUID APP_UUID = UUID.fromString("66b354c0-93c3-11e1-b0c4-0800200c9a66");

	private static final int SUBACTIVITY_ENABLE_BT = 10;

	private BluetoothAdapter mBluetoothAdapter;
	private BtArrayAdapter mArrayAdapter;

	private BluetoothConnectionThread mThread;

	private Button mScanButton;
	private Button mStopButton;
	private TextView mScanning;
	private ListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mScanButton = (Button) findViewById(R.id.scan);
		mStopButton = (Button) findViewById(R.id.stop);
		mScanning = (TextView) findViewById(R.id.scanning);
		mListView = (ListView) findViewById(R.id.listview);


		//Listeners

		mScanButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				scan();
			}
		});

		mStopButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cancel();
			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Cancel discovery
				cancel();

				//Conectar con el dispositivo
				BluetoothDevice device = mArrayAdapter.getItem(position);
				connect(device);
			}
		});
		
		if (mArrayAdapter != null)
			mArrayAdapter.clear();
	}



	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mBluetoothReceiverRegistered)
			unregisterReceiver(mBluetoothReceiver);
	}

	//BUSCAR DISPOSITIVOS

	private void scan(){

		//Instanciar el BluetoothAdapter
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		//Comprobar
		if (mBluetoothAdapter == null) {
			Toast.makeText(BluetoothActivity.this, "ERROR: BluetoothAdapter could not be instantiated.", Toast.LENGTH_SHORT).show();
			return;
		}

		//Si no está activado, damos la opción de activarlo (BluetoothAdapter.ACTION_REQUEST_ENABLE)
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, SUBACTIVITY_ENABLE_BT);
		} else {

			//Si lo está, empezamos a escanear
			IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
			registerReceiver(mBluetoothReceiver, filter);
			mBluetoothReceiverRegistered = true;
			mArrayAdapter = new BtArrayAdapter(this, android.R.layout.simple_list_item_2);

			mScanning.setVisibility(View.VISIBLE);

			//Start discovery
			mBluetoothAdapter.startDiscovery();
		}

	}

	private void cancel(){

		//Cancel discovery
		if (mBluetoothAdapter != null)
			mBluetoothAdapter.cancelDiscovery();

		mScanning.setVisibility(View.GONE);
	}

	//ArrayAdapter para mostrar una lista
	private final class BtArrayAdapter extends ArrayAdapter<BluetoothDevice>{

		public BtArrayAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TwoLineListItem view;
			if (convertView == null){
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = (TwoLineListItem) inflater.inflate(android.R.layout.simple_expandable_list_item_2, null);
			} else {
				view = (TwoLineListItem) convertView;
			}

			//Obtenemos el nombre y la direccion mac
			String name = mArrayAdapter.getItem(position).getName();
			String mac = mArrayAdapter.getItem(position).getAddress();

			if (name != null && name.trim().length() != 0)
				view.getText1().setText(name);
			else view.getText1().setText("<" + 
					getResources().getString(R.string.unknown) + ">");
			view.getText2().setText(mac);

			return view;
		}
	}

	//BroadcastReceiver para recibir los datos del bluetooth
	private final BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				Log.i(TAG, "Dispositivo: " + device.getName() + " " + device.getAddress());

				mArrayAdapter.add(device);
				mListView.setAdapter(mArrayAdapter);
			}
		}
	};

	private boolean mBluetoothReceiverRegistered = false;


	// CONECTAR CON DISPOSITIVOS
	private void connect(BluetoothDevice device){
		//Toast.makeText(this, "Connecting to " + device.getName(), Toast.LENGTH_SHORT).show();
		mThread = new BluetoothConnectionThread(device);
		mThread.start();
	}

	// Thread para conectar
	private class BluetoothConnectionThread extends Thread {

		private final BluetoothSocket mSocket;
		private final BluetoothDevice mDevice;

		public BluetoothConnectionThread(BluetoothDevice device) {

			BluetoothSocket tmp = null;
			mDevice = device;

			//Usamos la llamada createRfcommSocketToServiceRecord para obtener el BluetoothSocket
			try {
				Log.i(TAG, "Creando socket ");
				tmp = device.createRfcommSocketToServiceRecord(APP_UUID);
			} catch (IOException e) { 
				Log.i(TAG, "No se pudo conectar");
			}
			mSocket = tmp;
			Log.i(TAG, "BluetoothSocket creado con dispositivo " + mDevice.getName());
			Toast.makeText(BluetoothActivity.this, "BluetoothSocket creado", Toast.LENGTH_SHORT).show();
		}

		public void run() {

			// Parar la busqueda
			Log.i(TAG, "BluetoothSocket creado");
			mBluetoothAdapter.cancelDiscovery();

			try {
				//Conectar
				Log.i(TAG, "Conectando socket");
				mSocket.connect();
				Log.i(TAG, "BluetoothSocket conectado con dispositivo " + mDevice.getName());
			} catch (IOException connectException) {
				try {
					mSocket.close();
				} catch (IOException closeException) { 
					Log.i(TAG, "No se pudo conectar");
					Toast.makeText(BluetoothActivity.this, "No se pudo conectar", Toast.LENGTH_SHORT).show();
				}
				return;
			}

			//Una vez que tenemos el socket podemos trabajar con el
			//...
			//...
		}

		/** Will cancel an in-progress connection, and close the socket */
		public void cancel() {
			try {
				mSocket.close();
			} catch (IOException e) { }
		}

	}
}