// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("l0");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("l0")
//      }
//    }
#include <jni.h>
extern "C" JNIEXPORT void JNICALL
Java_com_example_myapplication_JNIUtils_cppMethod(JNIEnv *env, jobject /* this */) {
// 在这里编写 CPP 逻辑代码
    class cppClassTest{

    };
}
