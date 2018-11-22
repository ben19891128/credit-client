package com.csci.cloud.client;

import com.csci.cloud.client.common.Const;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.common.Utils;
import com.csci.cloud.client.model.ResponseVo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 信用云http 客户端工具类.
 */
public class CreditClient {

    private static final Logger logger = LoggerFactory.getLogger(CreditClient.class);
    public  OkHttpClient okHttpClient;

    public static final long DEFAULT_READ_TIMEOUT = 30*1000;
    public static final long DEFAULT_WRITE_TIMEOUT = 30*1000;
    public static final long DEFAULT_CONNECT_TIMEOUT = 5*1000;

    private String apiKey;
    private String basePath;
    private String secret;

    private CreditClient(String apiKey,String secret,String basePath,Long readTimeout,Long writeTimeout,Long connectTimeout) {
        this.apiKey = apiKey;
        this.basePath = basePath;
        this.secret = secret;
        okHttpClient = new OkHttpClient
                .Builder()
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    private CreditClient(String apiKey,String secret,String basePath) {
        this(apiKey,secret,basePath, DEFAULT_READ_TIMEOUT,DEFAULT_WRITE_TIMEOUT,DEFAULT_CONNECT_TIMEOUT);
    }

    private  String executeRaw(String httpMethod, String url, RequestBody requestBody,
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
            logger.warn(repsStr);
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


    public  ResponseVo execute(String uri,
                                     String httpMethod,
                                     RequestBody requestBody,
                                     Map<String, String> paraMap,
                                     Map<String,String> headMap) throws Exception {

        if (null == paraMap) {
            paraMap = Maps.newHashMap();
        }
        if (null == headMap) {
            headMap = Maps.newHashMap();
        }
        long timestamp = System.currentTimeMillis()/1000;
         headMap = new ImmutableMap.Builder().putAll(headMap).put(Const.API_HEAD_KEY, apiKey)
                .put(Const.API_HEAD_TIMESTAMP, timestamp + "")
                .put(Const.API_HEAD_SIGN,
                        Utils.calSign(apiKey, secret, timestamp,
                                uri, ImmutableMap.copyOf(paraMap)))
                .build();
         String body = executeRaw(httpMethod, basePath + uri, requestBody, paraMap, headMap);
        ResponseVo responseVo = JsonUtils.toObj(body, ResponseVo.class);
        return responseVo;
    }


    /**
     * 下载文件
     * @param uri
     * @param httpMethod
     * @param requestBody
     * @param queryMap
     * @param headMap
     * @return 返回下载内容字符串.
     * @throws Exception
     */
    public  String download(String uri,
                                     String httpMethod,
                                     RequestBody requestBody,
                                     Map<String, String> queryMap,
                                     Map<String,String> headMap) throws Exception {

        if (null != queryMap) {
            queryMap = Maps.newHashMap();
        }
        long timestamp = System.currentTimeMillis()/1000; //转换为秒.
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
     * @param uri 请求uri
     * @param httpMethod 请求方法 GET POST DELETE,OPTION等.
     * @param bodyRaw 请求body json 字符串.
     * @param paraMap url请求参数
     * @return
     * @throws Exception
     */
    public  ResponseVo executeJson(String uri,
                                         String httpMethod,
                                         String bodyRaw,
                                         Map<String, String> paraMap,
                                         Map<String,String> headMap) throws Exception {

        RequestBody requestBody = null != bodyRaw ? RequestBody.create(Const.JSON, bodyRaw) : null;
        return execute( uri, httpMethod,requestBody, paraMap, headMap);
    }


    /**
     *
     * @param uri 请求uri
     * @param httpMethod 请求方法 GET POST DELETE,OPTION等.
     * @param formBody 请求表单数据.
     * @param queryMap url请求参数
     * @return
     * @throws Exception
     */
    public  ResponseVo executeForm(String uri,
                                   String httpMethod,
                                   FormBody formBody,
                                   Map<String, String> queryMap,Map<String,String> headMap) throws Exception {

        return execute(uri,httpMethod,formBody,queryMap,headMap);
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


    public static class Builder {
        private String apiKey;
        private String basePath;
        private String secret;
        private Long readTimeout;
        private Long writeTimeout;
        private Long connectTimeout;

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }
        public Builder secret(String secret) {
            this.secret = secret;
            return this;
        }

        public Builder basePath(String basePath) {
            this.basePath = basePath;
            return this;
        }

        public Builder readTimeout(Long readTimeout) {
            this.readTimeout =readTimeout;
            return this;
        }

        public Builder writeTimeout(Long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder connectTimeout(Long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }
        public CreditClient build() {
            if (StringUtils.isBlank(apiKey) || StringUtils.isBlank(secret) || StringUtils.isBlank(basePath)) {
                throw new RuntimeException("apiKey,secret,basePath must not be blank");
            }
            if (null == readTimeout) readTimeout = DEFAULT_READ_TIMEOUT;
            if (null == writeTimeout) writeTimeout = DEFAULT_WRITE_TIMEOUT;
            if (null == connectTimeout) connectTimeout = DEFAULT_CONNECT_TIMEOUT;
            CreditClient creditClient = new CreditClient(apiKey,secret,basePath,readTimeout,writeTimeout,connectTimeout);
            return creditClient;
        }

    }
}
