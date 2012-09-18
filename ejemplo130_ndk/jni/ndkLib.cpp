#include <jni.h>
#include <android/log.h>
#include <math.h>
#include <string.h>
#define  LOG_TAG    "TID_EXAMPLE"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)


void peticion1(JNIEnv * env,jstring dato)
{
	 LOGI("NDK-SAMPLE: %s \n", env->GetStringUTFChars(dato, NULL));
}

jstring peticion2(JNIEnv * env,jstring dato)
{
    char buf[128];
    const char* strCIn =  env->GetStringUTFChars(dato, NULL);
    if (strCIn == NULL) {
        return NULL; /* OutOfMemoryError already thrown */
    }
    LOGI("NDK-SAMPLE: %s \n", strCIn);
    env->ReleaseStringUTFChars(dato, strCIn);
    /* We assume here that the user does not type more than
     * 127 characters */

    return   env->NewStringUTF("salida de c++2");

}



extern "C" {
    JNIEXPORT void JNICALL Java_com_tid_ejemplondk_NdkLib_peticion1(JNIEnv * env, jobject obj,jstring dato);
    JNIEXPORT jstring JNICALL Java_com_tid_ejemplondk_NdkLib_peticion2(JNIEnv * env, jobject obj,jstring dato);
};

JNIEXPORT void JNICALL Java_com_tid_ejemplondk_NdkLib_peticion1(JNIEnv * env, jobject obj,jstring dato)
{
    peticion1(env,dato);
}

JNIEXPORT jstring JNICALL Java_com_tid_ejemplondk_NdkLib_peticion2(JNIEnv * env, jobject obj, jstring dato)
{
	return peticion2(env,dato);
}
