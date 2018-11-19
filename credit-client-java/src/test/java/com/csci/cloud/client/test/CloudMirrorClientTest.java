package com.csci.cloud.client.test;

import com.csci.cloud.client.CreditClient;
import com.csci.cloud.client.model.AntiFraudScoreReqVo;
import com.csci.cloud.client.model.CreditScoreReqVo;
import com.csci.cloud.client.model.ResponseVo;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.MirrorFraud001ReqVo;
import com.google.common.collect.Maps;
import java.util.HashMap;
import org.junit.Test;

/**
 * 个人反欺诈业务.
 */
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

  /**
   * 个人信用评分.
   */
  @Test
  public void testCreditScoring() throws Exception {
    String uri = "/api/v1/mirror/credit-scoring";
    CreditScoreReqVo reqVo = new CreditScoreReqVo();
    reqVo.setIdNo("3412258198906275950");
    reqVo.setMobile("15618579664");
    reqVo.setName("马传林");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(reqVo),Maps.newHashMap() , Maps.newHashMap());
    System.out.println(responseVo);
  }


  /**
   * 个人反欺诈评分.
   */
  @Test
  public void testAntiFraudScoring() throws Exception {
    String uri = "/api/v1/mirror/anti-fraud-scoring";
    AntiFraudScoreReqVo reqVo = new AntiFraudScoreReqVo();
    reqVo.setIdNo("341225198906274952");
    reqVo.setName("马传林");
    reqVo.setMobile("15618579663");

    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(reqVo),Maps.newHashMap() , Maps.newHashMap());
    System.out.println(responseVo);
  }
}