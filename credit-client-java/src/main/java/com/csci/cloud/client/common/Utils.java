package com.csci.cloud.client.common;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.csci.cloud.client.common.Const.API_HEAD_KEY;
import static com.csci.cloud.client.common.Const.API_HEAD_TIMESTAMP;

public final class Utils {

    /**
     * 计算签名.
     * @param apiKey
     * @param secret
     * @param timestamp
     * @param uri
     * @param immutableQueryMap
     * @return
     */
    public static String calSign(String apiKey,
                                 String secret,
                                 Long timestamp, String uri,
                                 ImmutableMap<String, String> immutableQueryMap) {

        Map<String,String> queryMap = Maps.newHashMap(immutableQueryMap);
        queryMap.putIfAbsent(API_HEAD_KEY, apiKey);
        queryMap.putIfAbsent(API_HEAD_TIMESTAMP,timestamp+"");
        StringBuffer result = new StringBuffer(uri).append("?");
        String reduce = queryMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce(StringUtils.EMPTY, (a, b) -> a + "&" + b);

        result.append(reduce.substring(1)).append(secret);
        return Hashing.md5().hashString(result.toString(), Charsets.UTF_8).toString();
    }
}
