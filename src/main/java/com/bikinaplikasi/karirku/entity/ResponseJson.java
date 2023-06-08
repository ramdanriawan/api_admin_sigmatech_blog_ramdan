package com.bikinaplikasi.karirku.entity;

public class ResponseJson {

    public boolean success;
    public String message;
    public Object data;

    public ResponseJson(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
