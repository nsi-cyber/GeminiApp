#include <jni.h>
#include <string>

std::string getData() {
    std::string app_secret = "Null";

     app_secret = "AIzaSyCDOf1x4xf_0-_kT4np8Oyj-o1m0cGDUmA"; //The first key that you want to protect against decompilation

    // The number of parameters to be protected can be increased.

    return app_secret;
}

extern "C" jstring
Java_com_papara_geminiapp_common_ApiKeyProvider_getApiKey(
        JNIEnv *env,
        jobject /* this */,
        jint id) {
    std::string app_secret = "Null";
    app_secret = getData();
    return env->NewStringUTF(app_secret.c_str());
}