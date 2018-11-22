package com.csci.cloud.client.test;

import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.MirrorFraud001ReqVo;
import com.csci.cloud.client.model.ResponseVo;
import com.google.common.collect.Maps;
import java.util.HashMap;
import org.junit.Test;

public class CloudMirrorClientTest extends BaseClientTest {

  @Test
  public void fraud001() throws Exception {
    MirrorFraud001ReqVo reqVo = new MirrorFraud001ReqVo();
    reqVo.setIdNo("341225198906274952");
    reqVo.setName("马化腾");
    reqVo.setOrgName("腾通讯");
    reqVo.setMobile("19015699662");
    String uri = "/api/v1/mirror/anti-fraud/001";
    HashMap<String, String> queryMap = Maps.newHashMap();
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(reqVo), queryMap, Maps.newHashMap());
    System.out.println(responseVo);
  }

}