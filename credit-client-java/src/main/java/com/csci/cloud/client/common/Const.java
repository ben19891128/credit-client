package com.csci.cloud.client.common;

import okhttp3.MediaType;

public final class Const {

    public static final String API_HEAD_KEY = "apiKey";
    public static final String API_HEAD_TIMESTAMP = "timestamp";
    public static final String API_HEAD_SIGN = "sign";
    public static final String CREDIO_PLUS_CLOUD_TOKEN_HEADER = "X-CP-Token";//信用云对应区块链请求token 名称

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");
}
