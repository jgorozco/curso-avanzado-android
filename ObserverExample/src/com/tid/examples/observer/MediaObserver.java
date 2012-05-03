package com.tid.examples.observer;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

/**
 * El MediaObserver encapsula diferentes observers (Image, Audio y Video) 
 * para poder diferenciar donde se estan produciendo los cambios en el ContentProvider
 *  
 * Cada Provider llama a la funcion correspondiente en su metodo 'onChange' para hacer 
 * el trabajo que queramos
 * 
 * IMPORTANTE: el metodo onChange solo devuelve que ha habido un cambio en el provider,
 * pero no podemos saber si es un elemento creado, borrado, etc.
 * Nos tenemos que hacer cargo de esa logica nosotros mismos, como se ve en estos ejemplos.
 *
 */
public class MediaObserver {
	
	private static final String TAG = "MediaObserver";

	public static final Uri IMAGES_CONTENT_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	public static final Uri AUDIO_CONTENT_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	public static final Uri VIDEOS_CONTENT_URI = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

	public static String[] ID_PROJECTION = {MediaStore.MediaColumns._ID};
	
	private Context mContext;
	private Handler mHandler;
	
	//Observers
	private ImageObserver mImageObserver;
	private AudioObserver mAudioObserver;
	private VideoObserver mVideoObserver;
	
	//Fake web service
	private FakeWebService mService;
	
	public MediaObserver(Context context, Handler handler) {
		
		mContext = context;
		mHandler = handler;

		mImageObserver = new ImageObserver(mHandler);
		mAudioObserver = new AudioObserver(mHandler);
		mVideoObserver = new VideoObserver(mHandler);
		
		mService = new FakeWebService(context);
	}
	
	private class ImageObserver extends ContentObserver {
		public ImageObserver(Handler handler) {
			super(handler);
		}
		
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Log.i(TAG, "Notification in ImageObserver");
			updateImages();
		}
	}
	
	private class AudioObserver extends ContentObserver {
		public AudioObserver(Handler handler) {
			super(handler);
		}
		
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Log.i(TAG, "Notification in AudioObserver");
			updateAudio();
		}
	}
	
	private class VideoObserver extends ContentObserver {
		public VideoObserver(Handler handler) {
			super(handler);
		}
		
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Log.i(TAG, "Notification in VideoObserver");
			updateVideos();
		}
	}

	public void registerObserver(){
		mContext.getContentResolver().registerContentObserver(IMAGES_CONTENT_URI, true, mImageObserver);
		mContext.getContentResolver().registerContentObserver(AUDIO_CONTENT_URI, true, mAudioObserver);
		mContext.getContentResolver().registerContentObserver(VIDEOS_CONTENT_URI, true, mVideoObserver);
		
		Log.i(TAG, "Registering observer for: " + IMAGES_CONTENT_URI);
		Log.i(TAG, "Registering observer for: " + AUDIO_CONTENT_URI);
		Log.i(TAG, "Registering observer for: " + VIDEOS_CONTENT_URI);
	}
	
	public void unregisterObserver(){
		mContext.getContentResolver().unregisterContentObserver(mImageObserver);
		mContext.getContentResolver().unregisterContentObserver(mAudioObserver);
		mContext.getContentResolver().unregisterContentObserver(mVideoObserver);
	}
	
	private void updateImages(){
		
		Cursor c = mContext.getContentResolver().
				query(IMAGES_CONTENT_URI, ID_PROJECTION, null, null, null);
		
		int remoteElementsNum = mService.getImagesCount();
		
		//Comparamos el numero de elementos locales y remotos
		//Si es distinto hay que sincronizar
		if (c.getCount() != remoteElementsNum){
			
			//Almacenamos los ids en un set
			Set<Long> ids = new HashSet<Long>();
			
			if (c.moveToFirst()){
				do {
					long id = c.getLong(c.getColumnIndex(MediaStore.MediaColumns._ID));
					ids.add(id);
				} while (c.moveToNext());
			}
			
			if (c != null)
				c.close();
			
			//Obtenemos los elementos creados y borrados
			Set<Long> newElements = getNewElements(ids, mService.getImageIds());
			Set<Long> deletedElements = getDeletedElements(ids, mService.getImageIds());

			Log.i(TAG, "Elementos a crear: " + newElements);
			Log.i(TAG, "Elementos a borrar: " + deletedElements);
			
			//Llamamos al FakeWebService para realizar las operaciones convenientes
			mService.addImages(newElements);
			mService.deleteImages(deletedElements);
		}
	}
	
	private void updateAudio(){
		
		Cursor c = mContext.getContentResolver().
				query(AUDIO_CONTENT_URI, ID_PROJECTION, null, null, null);
		
		int remoteElementsNum = mService.getAudioCount();
		
		//Comparamos el numero de elementos locales y remotos
		//Si es distinto hay que sincronizar
		if (c.getCount() != remoteElementsNum){
			
			//Almacenamos los ids en un set
			Set<Long> ids = new HashSet<Long>();
			
			if (c.moveToFirst()){
				do {
					long id = c.getLong(c.getColumnIndex(MediaStore.MediaColumns._ID));
					ids.add(id);
				} while (c.moveToNext());
			}
			
			if (c != null)
				c.close();
			
			//Obtenemos los elementos creados y borrados
			Set<Long> newElements = getNewElements(ids, mService.getAudioIds());
			Set<Long> deletedElements = getDeletedElements(ids, mService.getAudioIds());

			Log.i(TAG, "Elementos a crear: " + newElements);
			Log.i(TAG, "Elementos a borrar: " + deletedElements);
			
			//Llamamos al FakeWebService para realizar las operaciones convenientes
			mService.addAudios(newElements);
			mService.deleteAudios(deletedElements);
		}
	}
	
	private void updateVideos(){
		
		Cursor c = mContext.getContentResolver().
				query(VIDEOS_CONTENT_URI, ID_PROJECTION, null, null, null);
		
		int remoteElementsNum = mService.getVideoCount();
		
		//Comparamos el numero de elementos locales y remotos
		//Si es distinto hay que sincronizar
		if (c.getCount() != remoteElementsNum){
			
			//Almacenamos los ids en un set
			Set<Long> ids = new HashSet<Long>();
			
			if (c.moveToFirst()){
				do {
					long id = c.getLong(c.getColumnIndex(MediaStore.MediaColumns._ID));
					ids.add(id);
				} while (c.moveToNext());
			}
			
			if (c != null)
				c.close();
			
			//Obtenemos los elementos creados y borrados
			Set<Long> newElements = getNewElements(ids, mService.getVideoIds());
			Set<Long> deletedElements = getDeletedElements(ids, mService.getVideoIds());

			Log.i(TAG, "Elementos a crear: " + newElements);
			Log.i(TAG, "Elementos a borrar: " + deletedElements);
			
			//Llamamos al FakeWebService para realizar las operaciones convenientes
			mService.addVideos(newElements);
			mService.deleteVideos(deletedElements);
		}
		
	}
	
	/**
	 * Compara a partir de las dos primeras listas los elementos locales 
	 * y remotos y devuelve un set con los elementos nuevos
	 * 
	 * @param localList ids locales
	 * @param remoteList ids remotos
	 * @returns elementos locales creados (para incluir en remoto)
	 */
	private Set<Long> getNewElements(Set<Long> localList, Set<Long> remoteList){
	
		Set<Long> newElements = new HashSet<Long>(localList);
		newElements.removeAll(remoteList);
		
		return newElements;
	}
	
	/**
	 * Compara a partir de las dos primeras listas los elementos locales 
	 * y remotos y devuelve un set con los elementos borrados
	 * 
	 * @param localList ids locales
	 * @param remoteList ids remotos
	 * @returns elementos locales borrados (para borrar en remoto)
	 */
	private Set<Long> getDeletedElements(Set<Long> localList, Set<Long> remoteList){
	
		Set<Long> deleted = new HashSet<Long>(remoteList);
		deleted.removeAll(localList);
		
		return deleted;
	}
	
	

}
