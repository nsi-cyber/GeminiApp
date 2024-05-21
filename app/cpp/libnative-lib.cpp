#include <jni.h>
#include <string>



extern "C" jstring
Java_com_papara_geminiapp_common_ApiKeyProvider_00024Companion_getApiKey(
        JNIEnv *env,
        jobject /* this */
) {
    std::string app_secret = "AIzaSyCDOf1x4xf_0-_kT4np8Oyj-o1m0cGDUmA";
    return env->NewStringUTF(app_secret.c_str());
}