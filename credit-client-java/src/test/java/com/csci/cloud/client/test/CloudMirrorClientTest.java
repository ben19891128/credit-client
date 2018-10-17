package com.csci.cloud.client.test;

import com.csci.cloud.client.CreditClient;
import com.csci.cloud.client.model.ResponseVo;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.MirrorFraud001ReqVo;
import com.google.common.collect.Maps;
import org.junit.Test;

/**
 * 个人反欺诈业务.
 */
public class CloudMirrorClientTest extends BaseClientTest {
    private static String HOST = "http://localhost:8020";


    @Test
    public void fraud001() throws Exception {
        MirrorFraud001ReqVo reqVo = new MirrorFraud001ReqVo();

        reqVo.setIdNo("341225198906274952");
        reqVo.setName("马传林");
        reqVo.setOrgName("腾通讯");
        reqVo.setMobile("15618579663");
        String uri = "/api/v1/mirror/anti-fraud/001";

        ResponseVo responseVo = CreditClient.executeJson(HOST, uri, "POST", JsonUtils.toJson(reqVo), API_KEY, SECRET, Maps.newHashMap());
        System.out.println(responseVo);
    }
}