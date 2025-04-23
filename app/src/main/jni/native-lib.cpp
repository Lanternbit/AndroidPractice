#include <jni.h>
#include <string>
#include <cstring>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_internandroid_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Main Activity!";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_internandroid_SecondActivity_stringFromJNI(
        JNIEnv* env,
jobject /* this */) {
std::string hello = "Second Activity!";
return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_internandroid_ThirdActivity_stringFromJNI(
        JNIEnv* env,
jobject /* this */) {
std::string hello = "Third Activity!";
return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_internandroid_ComposeActivity2Kt_printValueLength(JNIEnv *env, jclass clazz,
                                                                   jstring param) {
    int len = env->GetStringUTFLength(param);
    len = 999;
    return len;
}