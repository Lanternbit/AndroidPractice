#include <jni.h>
#include <string>

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