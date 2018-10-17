package com.csci.cloud.client;

import com.csci.cloud.client.common.Const;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.common.Utils;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import okhttp3.*;
import okio.BufferedSink;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreditClient {
    public final static OkHttpClient okHttpClient = new OkHttpClient
            .Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();


    /**
     * 发送post请求. Content-Type : application/json .
     *
     * @param httpMethod    请求方法
     * @param url         资源路径
     * @param requestBody request body
     * @param queryMap     请求路径参数
     * @param headerMap    请求头
     */
    private static ResponseVo executeJson(String httpMethod, String url, String requestBody,
                                         Map<String, String> queryMap,
                                         Map<String, String> headerMap) throws Exception {

        RequestBody body = null != requestBody ? getRequestBody(Const.JSON, requestBody) : null;
        url = url + createOrderedUrlParamFromMap(queryMap);

        //组装Headers
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headerMap != null && !headerMap.isEmpty()) {
            headerMap.forEach((k, v) -> headerBuilder.add(k, v));
        }
        //组装Request
        Request request
                = new Request.Builder()
                .url(url)
                .method(httpMethod, body)
                .headers(headerBuilder.build())
                .build();

        //发送请求
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        //校验返回
        if (!response.isSuccessful()) {
            //解析错误信息
            String repsStr = response.body().string();
            ResponseVo responseVo = JsonUtils.toObj(repsStr, ResponseVo.class);
            if (responseVo == null || responseVo.getCode() == null) {
                throw new RuntimeException(
                        "HttpClient调用失败"
                                + ", httpStatus=" + response.code()
                                + ", URL=" + response.request().url()
                                + ", 错误信息：" + response.message());

            } else {
                throw new RuntimeException("HttpClient调用失败"
                        + ", URL=" + response.request().url()
                        + "，错误信息：" + responseVo.getErrorMessage());
            }
        }
        ResponseVo responseVo = JsonUtils.toObj(response.body().string(), ResponseVo.class);
        return responseVo;
    }


    public static ResponseVo executeJson(String basePath, String uri, String httpMethod,String requestBody,
                                         String apiKey,
                                         String secret,
                                         Map<String, String> queryMap) throws Exception {

        long timestamp = System.currentTimeMillis();
        Map headMap = new ImmutableMap.Builder().put(Const.API_HEAD_KEY,apiKey)
                .put(Const.API_HEAD_TIMESTAMP,timestamp+"")
                .put(Const.API_HEAD_SIGN, Utils.calSign(apiKey,secret,timestamp,uri,ImmutableMap.copyOf(queryMap))).build();

        return executeJson(httpMethod,basePath+uri,requestBody,queryMap,headMap);

    }



    private static RequestBody getRequestBody(MediaType mediaType, String requestBody) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                if (requestBody != null && requestBody.length() > 0) {
                    sink.writeString(requestBody, Charsets.UTF_8);
                }
                sink.flush();
            }
        };

    }


    /**
     * 根据map组装成url参数，参数的顺序按照key排序.
     *
     * @return url参数，如：?age=18&name=tom
     */
    public static String createOrderedUrlParamFromMap(Map<String, ?> queryMap) {
        if (queryMap == null || queryMap.isEmpty()) {
            return "";
        }
        StringBuffer params = new StringBuffer();
        queryMap.keySet().stream()
                .filter(k -> null != queryMap.get(k))
                .sorted()
                .forEach(k -> params.append("&").append(k.trim()).append("=")
                        .append(queryMap.get(k).toString().trim()));
        return "?" + params.subSequence(1, params.length()).toString();
    }
}
