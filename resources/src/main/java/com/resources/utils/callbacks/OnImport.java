package com.resources.utils.callbacks;


public interface OnImport {

    void onStartImport();

    void onSuccessImport();

    void onErrorImport(Exception exception);

}
