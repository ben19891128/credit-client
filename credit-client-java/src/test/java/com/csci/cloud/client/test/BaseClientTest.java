package com.csci.cloud.client.test;

import com.csci.cloud.client.CreditClient;

public abstract class BaseClientTest {

    public static final String API_KEY = "ntjhb0v6thrwaujqttytbzayow5ozw";
    public static final String SECRET = "m2i5oddjmgzhmgi0ndk2m2jhytjkmznjmzdhymfkmwq";
    protected static String HOST = "http://localhost:8020";

    protected CreditClient creditClient = new CreditClient.Builder()
        .apiKey(API_KEY)
        .secret(SECRET)
        .basePath(HOST)
        .build();





}
