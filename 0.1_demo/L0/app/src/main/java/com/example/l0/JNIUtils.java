package com.example.l0;

public class JNIUtils {
    static {
        System.loadLibrary("l0");
    }
    public native void cppMethod();
}

class useCpp{
    public static void use(){
        JNIUtils jniUtils = new JNIUtils();
        jniUtils.cppMethod();
    }
}
