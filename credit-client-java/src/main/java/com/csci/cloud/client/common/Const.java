package com.csci.cloud.client.common;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import okhttp3.MediaType;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Map;

public final class Const {

    public static final String API_HEAD_KEY = "apiKey";
    public static final String API_HEAD_TIMESTAMP = "timestamp";
    public static final String API_HEAD_SIGN = "sign";


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");



}
