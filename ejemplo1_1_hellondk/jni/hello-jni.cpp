#include <string.h>
#include <jni.h>

jstring Java_com_tid_Ejemplo11Ndk_EjemploDendk_stringFromJNI( JNIEnv* env,
                                                  jobject thiz )
{
    return (*env)->NewStringUTF(env, "escrito desde jni");
}
