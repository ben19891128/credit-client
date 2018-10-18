package com.csci.cloud.client.test;

import com.csci.cloud.client.CreditClient;
import com.csci.cloud.client.model.ResponseVo;
import com.google.common.collect.Maps;
import okhttp3.FormBody;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 区块链单元测试类.
 */
public class CredioPlusClientTest extends BaseClientTest {
    private static String HOST = "http://localhost:8020";

    private String token = StringUtils.EMPTY;

    @Test
    public void login() throws Exception {
        String uri = "/api/v1/chain/uaa/oauth/token";

        FormBody formBody = new FormBody.Builder()
                .add("username","ADMIN")
                .add("password","adminpw")
                .add("grant_type","password")
                .build();

        ResponseVo responseVo = CreditClient.executeForm(HOST, uri,"POST", API_KEY, SECRET, formBody, Maps.newHashMap());
        System.out.println(responseVo);
    }


}
