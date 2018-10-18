package com.csci.cloud.client.common;

import okhttp3.MediaType;

public final class Const {

    public static final String API_HEAD_KEY = "apiKey";
    public static final String API_HEAD_TIMESTAMP = "timestamp";
    public static final String API_HEAD_SIGN = "sign";


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");



}
