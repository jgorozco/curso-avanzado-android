package com.tid.examples.observer;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

/**
 * Usaremos esta clase como un web service fake que simula el repositorio con los datos del servidor
 * Contiene una lista de los ids de los elementos para cada provider (Image, Audio, Video)
 * 
 */
public class FakeWebService {

	private static final String TAG = "FakeWebService";

	private Context mContext;

	private static Set<Long> mImageList;
	private static Set<Long> mAudioList;
	private static Set<Long> mVideoList;

	public FakeWebService(Context context){

		mContext = context;

		mImageList = new HashSet<Long>();
		mAudioList = new HashSet<Long>();
		mVideoList = new HashSet<Long>();

		//Al construir el objeto, rellenamos las listas con los valores existentes
		init(MediaObserver.IMAGES_CONTENT_URI, mImageList);
		init(MediaObserver.AUDIO_CONTENT_URI, mAudioList);
		init(MediaObserver.VIDEOS_CONTENT_URI, mVideoList);
	}

	/**
	 * Recoge los valores de los ids de un provider y rellena la lista
	 * 
	 * @param uri
	 * @param list
	 */
	private void init(Uri uri, Set<Long> list){
		Cursor c = mContext.getContentResolver().query(uri, 
				MediaObserver.ID_PROJECTION, null, null, null);

		if (c.moveToFirst()){
			do {
				long id = c.getLong(c.getColumnIndex(MediaStore.MediaColumns._ID));
				list.add(id);
			} while (c.moveToNext());
		}

		if (c != null)
			c.close();

		Log.i(TAG, "Leidos " + list.size() + " elementos en " + uri);
	}

	public int getImagesCount(){
		return mImageList.size();
	}

	public int getAudioCount(){
		return mAudioList.size();
	}

	public int getVideoCount(){
		return mVideoList.size();
	}

	public Set<Long> getImageIds(){
		return mImageList;
	}

	public Set<Long> getAudioIds(){
		return mAudioList;
	}

	public Set<Long> getVideoIds(){
		return mVideoList;
	}

	public void addImages(Set<Long> newElements){
		printNew(newElements);

		//Fake: aqui tendria que tomar mas informacion del provider y subirla al repositorio
		mImageList.addAll(newElements);
	}

	public void addAudios(Set<Long> newElements){
		printNew(newElements);
		//Fake: aqui tendria que tomar mas informacion del provider y subirla al repositorio
		mAudioList.addAll(newElements);
	}

	public void addVideos(Set<Long> newElements){
		printNew(newElements);
		//Fake: aqui tendria que tomar mas informacion del provider y subirla al repositorio
		mVideoList.addAll(newElements);
	}

	public void deleteImages(Set<Long> deletedElements){
		printDelete(deletedElements);
		//Fake: aqui tendria que eliminar info del repositorio
		mImageList.removeAll(deletedElements);
	}

	public void deleteAudios(Set<Long> deletedElements){
		printDelete(deletedElements);
		//Fake: aqui tendria que eliminar info del repositorio
		mAudioList.removeAll(deletedElements);
	}

	public void deleteVideos(Set<Long> deletedElements){
		printDelete(deletedElements);
		//Fake: aqui tendria que eliminar info del repositorio
		mVideoList.removeAll(deletedElements);
	}

	private void printNew(Set<Long> list){
		if (!list.isEmpty()){
			StringBuilder sb = new StringBuilder();
			for (long id: list){
				sb.append(id + ", ");
			}
			Toast.makeText(mContext, "Added: " + sb.toString(), Toast.LENGTH_SHORT).show();
		}
	}

	private void printDelete(Set<Long> list){
		if (!list.isEmpty()){
			StringBuilder sb = new StringBuilder();
			for (long id: list){
				sb.append(id + ", ");
			}
			Toast.makeText(mContext, "Deleted: " + sb.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
