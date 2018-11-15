package com.csci.cloud.client.test.mercury;

import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.MercuryReqVo;
import com.csci.cloud.client.model.ResponseVo;
import com.csci.cloud.client.test.BaseClientTest;
import java.util.List;
import org.junit.Test;

public class IndustryClientTest extends BaseClientTest {


  /**
   * 行政处罚.
   */
  @Test
  public void testAdminPenalty() throws Exception {
    String uri = "/api/v1/enterprise/basic/admin-penalty";
    MercuryReqVo adminPenaltyReqVo = new MercuryReqVo();
    adminPenaltyReqVo.setCompanyName("上海申能星火热电有限责任公司");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(adminPenaltyReqVo), null, null);
    System.out.println(JsonUtils.toJson(responseVo));
  }

  /**
   * 债券违约.
   */
  @Test
  public void testBondDefaults() throws Exception {
    String uri = "/api/v1/enterprise/basic/bond-defaults";
    MercuryReqVo bondDefaultsReqVo =  new MercuryReqVo();
    bondDefaultsReqVo.setCompanyName("萧县建设投资有限责任公司");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(bondDefaultsReqVo), null, null);

    System.out.println(JsonUtils.toJson(responseVo));

  }

  /**
   * 股权冻结.
   */
  @Test
  public void testFrozenShare() throws Exception {
    String uri = "/api/v1/enterprise/basic/frozen-share";
    MercuryReqVo frozenShareReqVo =  new MercuryReqVo();
    frozenShareReqVo.setCompanyName("华能国际电力股份有限公司");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(frozenShareReqVo), null, null);

    System.out.println(JsonUtils.toJson(responseVo));

  }

  /**
   * 企业主体评级.
   */
  @Test
  public void testCreditRating() throws Exception {
    MercuryReqVo baseReqVo = new MercuryReqVo();
    String uri = "/api/v1/enterprise/basic/credit-rating";

    baseReqVo.setCompanyName("萧县建设投资有限责任公司");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(baseReqVo), null, null);

    System.out.println(JsonUtils.toJson(responseVo));
  }

  /**
   * 企业基本信息 .
   */
  @Test
  public void testBasicInfo() throws Exception {
    MercuryReqVo baseReqVo = new MercuryReqVo();
    String uri = "/api/v1/enterprise/basic/base-info";

    baseReqVo.setCompanyName("中证信用增进股份有限公司");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(baseReqVo), null, null);

    System.out.println(JsonUtils.toJson(responseVo));
  }

  /**
   * 企业高管信息 .
   */
  @Test
  public void testManagerInfo() throws Exception {
    MercuryReqVo baseReqVo = new MercuryReqVo();
    String uri = "/api/v1/enterprise/basic/manage-info";

    baseReqVo.setCompanyName("中国平安");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(baseReqVo), null, null);

    System.out.println(JsonUtils.toJson(responseVo));
  }

  /**
   * 企业工商信息变更记录 .
   */
  @Test
  public void testChangeRecord() throws Exception {
    MercuryReqVo baseReqVo = new MercuryReqVo();
    String uri = "/api/v1/enterprise/basic/business-change-record";

    baseReqVo.setCompanyName("中证信用增进股份有限公司");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(baseReqVo), null, null);

    System.out.println(JsonUtils.toJson(responseVo));
  }


  /**
   * 股权出质 .
   */
  @Test
  public void testEquityPledge() throws Exception {
    MercuryReqVo baseReqVo = new MercuryReqVo();
    String uri = "/api/v1/enterprise/basic/equity-pledge";

    baseReqVo.setCompanyName("保亭黎族苗族自治县农村信用合作联社");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(baseReqVo), null, null);

    System.out.println(JsonUtils.toJson(responseVo));
  }

  /**
   * 动产抵押 .
   */
  @Test
  public void testChattelMortgage() throws Exception {
    MercuryReqVo baseReqVo = new MercuryReqVo();
    String uri = "/api/v1/enterprise/basic/chattel-mortgage";

    baseReqVo.setCompanyName("柳州东风容泰化工股份有限公司");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(baseReqVo), null, null);

    System.out.println(JsonUtils.toJson(responseVo));
  }

  /**
   * 企业严重违法.
   */
  @Test
  public void testSeriousOffence() throws Exception {
    MercuryReqVo baseReqVo = new MercuryReqVo();
    String uri = "/api/v1/enterprise/basic/serious-offence";

    baseReqVo.setCompanyName("倬可儿服饰(深圳)有限公司");
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(baseReqVo), null, null);

    System.out.println(JsonUtils.toJson(responseVo));
  }



}
