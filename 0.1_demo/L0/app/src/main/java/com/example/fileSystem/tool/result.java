package com.example.fileSystem.tool;


public class result<T> {
    private boolean isOk;
    private T value;
    private Exception exception;
    public result(T value) {
        isOk = true;
        this.value = value;
    }
    public result(Exception exception) {
        isOk = false;
        this.exception = exception;
    }
    public boolean isOk() {
        return isOk;
    }
    public T val() {
        return value;
    }
    public Exception err() {
        return exception;
    }
}

//public class result {
//    private boolean isOk;
//    private Exception exception;
//    public result() {
//        isOk = true;
//    }
//    public result(badResult bad) {
//        isOk = false;
//        exception = bad.getException();
//    }
//    public boolean isOk() {
//        return isOk;
//    }
//    public Exception err() {
//        return exception;
//    }
//}


//class OperationResult<T> {
//    private boolean success;
//    private T value;
//    private Exception exception;
//
//    public OperationResult(boolean success, T value, Exception exception) {
//        this.success = success;
//        this.value = value;
//        this.exception = exception;
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public T getValue() {
//        return value;
//    }
//
//    public Exception getException() {
//        return exception;
//    }
//}