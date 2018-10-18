package com.csci.cloud.client;

import com.csci.cloud.client.common.Const;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.common.Utils;
import com.csci.cloud.client.model.ResponseVo;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreditClient {

    private static final Logger logger = LoggerFactory.getLogger(CreditClient.class);
    public final static OkHttpClient okHttpClient = new OkHttpClient
            .Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();


    private static String executeRaw(String httpMethod, String url, RequestBody requestBody,
                                      Map<String, String> queryMap,
                                      Map<String, String> headerMap) throws Exception {

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
                .method(httpMethod, requestBody)
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
                                + ", 错误信息：" + repsStr);

            } else {
                throw new RuntimeException("HttpClient调用失败"
                        + ", URL=" + response.request().url()
                        + "，错误信息：" + responseVo.toString());
            }
        }
        String body = response.body().string();
        return body;
    }

    /**
     * 发送post请求. Content-Type : application/json .
     *
     * @param httpMethod  请求方法 GET,POST,DELETE,PATCH,OPTION
     * @param url         资源路径
     * @param requestBody request body
     * @param queryMap    请求路径参数
     * @param headerMap   请求头
     */

    private static ResponseVo execute(String httpMethod, String url, RequestBody requestBody,
                                      Map<String, String> queryMap,
                                      Map<String, String> headerMap) throws Exception {

        String body = executeRaw(httpMethod, url, requestBody, queryMap, headerMap);
        ResponseVo responseVo = JsonUtils.toObj(body, ResponseVo.class);
        return responseVo;
    }

    public static ResponseVo execute(String basePath,
                                     String uri,
                                     String httpMethod,
                                     String apiKey,
                                     String secret,
                                     RequestBody requestBody,
                                     Map<String, String> queryMap,
                                     Map<String,String> headMap) throws Exception {

        if (null != queryMap) {
            queryMap = Maps.newHashMap();
        }
        long timestamp = System.currentTimeMillis();
         headMap = new ImmutableMap.Builder().putAll(headMap).put(Const.API_HEAD_KEY, apiKey)
                .put(Const.API_HEAD_TIMESTAMP, timestamp + "")
                .put(Const.API_HEAD_SIGN,
                        Utils.calSign(apiKey, secret, timestamp,
                                uri, ImmutableMap.copyOf(queryMap)))
                .build();
        return execute(httpMethod, basePath + uri, requestBody, queryMap, headMap);
    }


    public static String download(String basePath,
                                     String uri,
                                     String httpMethod,
                                     String apiKey,
                                     String secret,
                                     RequestBody requestBody,
                                     Map<String, String> queryMap,
                                     Map<String,String> headMap) throws Exception {

        if (null != queryMap) {
            queryMap = Maps.newHashMap();
        }
        long timestamp = System.currentTimeMillis();
        headMap = new ImmutableMap.Builder().putAll(headMap).put(Const.API_HEAD_KEY, apiKey)
                .put(Const.API_HEAD_TIMESTAMP, timestamp + "")
                .put(Const.API_HEAD_SIGN,
                        Utils.calSign(apiKey, secret, timestamp,
                                uri, ImmutableMap.copyOf(queryMap)))
                .build();
        return executeRaw(httpMethod, basePath + uri, requestBody, queryMap, headMap);
    }



    /**
     *
     * @param basePath 请求的地址.
     * @param uri 请求uri
     * @param httpMethod 请求方法 GET POST DELETE,OPTION等.
     * @param requestBody 请求body json 字符串.
     * @param apiKey 开发者key
     * @param secret 开发者秘钥
     * @param queryMap url请求参数
     * @return
     * @throws Exception
     */
    public static ResponseVo executeJson(String basePath,
                                         String uri,
                                         String httpMethod,
                                         String apiKey,
                                         String secret,
                                         String requestBody,
                                         Map<String, String> queryMap,
                                         Map<String,String> headMap) throws Exception {

        RequestBody body = null != requestBody ? RequestBody.create(Const.JSON, requestBody) : null;
        return execute( basePath, uri,httpMethod,  apiKey, secret, body,queryMap,headMap);
    }


    /**
     *
     * @param basePath 请求的地址.
     * @param uri 请求uri
     * @param httpMethod 请求方法 GET POST DELETE,OPTION等.
     * @param formBody 请求表单数据.
     * @param apiKey 开发者key
     * @param secret 开发者秘钥
     * @param queryMap url请求参数
     * @return
     * @throws Exception
     */
    public static ResponseVo executeForm(String basePath,
                                         String uri,
                                         String httpMethod,
                                         String apiKey,
                                         String secret,
                                         FormBody formBody,
                                         Map<String, String> queryMap,Map<String,String> headMap) throws Exception {

        return execute(basePath, uri, httpMethod,  apiKey, secret, formBody,queryMap,headMap);
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
