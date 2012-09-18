package com.tid.ejemplondk;

public class NdkLib {
    static {
        System.loadLibrary("ndklib");
    }

    public static native void peticion1(String dato);
    public static native String peticion2(String dato);
}
