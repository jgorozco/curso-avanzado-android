package com.demo;

import java.io.File;
import java.util.LinkedList;

import android.content.Context;
import dalvik.system.DexClassLoader;

public class MultiDexClassLoader extends ClassLoader {
	private static MultiDexClassLoader instance = null;
	private LinkedList<DexClassLoader> classLoaders;

	public static MultiDexClassLoader getInstance() {
		if (instance == null) {
			instance = new MultiDexClassLoader();
		}
		return instance;
	}

	private MultiDexClassLoader() {
		super(ClassLoader.getSystemClassLoader());
		this.classLoaders = new LinkedList<DexClassLoader>();
	}

	public void install(Context context, String jarPath) {
		File dexOutputDir = context.getDir("dex", 0);
		DexClassLoader dexClassLoader = new DexClassLoader(jarPath,
				dexOutputDir.getAbsolutePath(), null, context.getClassLoader());
		this.classLoaders.addLast(dexClassLoader);
	}

	@Override
	public Class<?> findClass(String className) throws ClassNotFoundException {
		Class<?> clazz = null;
		for (DexClassLoader classLoader : this.classLoaders) {
			try {
				clazz = classLoader.loadClass(className);
			} catch (ClassNotFoundException e) {
				continue;
			}
			if (clazz != null) {
				return clazz;
			}
		}

		throw new ClassNotFoundException(className + " in loader " + this);
	}
}
