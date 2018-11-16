package com.csci.cloud.client.test.credio;

import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.CredioPlusRegisterVo;
import com.csci.cloud.client.model.ResponseVo;
import com.google.common.collect.Maps;
import java.util.Map;
import okhttp3.FormBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * 企业信息管理单元测试.
 */
public class CredioBusinessClientTest extends AbstractCredioPushTest {


  /**
   * 创建区块链组织信息.
   */
  @Test
  public void createBlockchainAffiliation() throws Exception {
    String uri = "/chain/api/user/admin/blockchain/createBlockchainAffiliation";

    Map reqMap = Maps.newHashMap();
    reqMap.put("name","马传林_代号:"+ RandomStringUtils.randomAlphabetic(32));
    reqMap.put("mspId","中证mspId");
    reqMap.put("description","描述");

    ResponseVo responseVo = creditClient.executeJson(uri,
        "POST",
        JsonUtils.toJson(reqMap),
        Maps.newHashMap(),initLoginMap());
    System.out.println(responseVo);
  }

  /**
   * 查询企业列表.
   * @throws Exception
   */
  @Test
  public void listBlockChain() throws Exception {
    String uri = "/chain/api/user/admin/blockchain/businessAffiliationList";
    Map queryMap = Maps.newHashMap();
    queryMap .put("blockchainAffiliationId",1+"");
    queryMap .put("page",1+"");
    queryMap .put("pageSize",10+"");
    ResponseVo responseVo = creditClient.executeForm(uri,"GET",null,queryMap, initLoginMap());
    System.out.println(responseVo);
  }


  /**
   * 创建企业信息.
   */
  @Test
  public void createBu() throws Exception {
    String uri = "/chain/api/user/admin/business/createBU";

    Map reqMap = Maps.newHashMap();
    reqMap.put("name","马传林_代号:"+ RandomStringUtils.randomAlphabetic(32));
    reqMap.put("creditNo","3310");
    reqMap.put("description","描述");
    reqMap.put("blockchainAffiliationId",1);

    ResponseVo responseVo = creditClient.executeJson(uri,
        "POST",
        JsonUtils.toJson(reqMap),
        Maps.newHashMap(),initLoginMap());
    System.out.println(responseVo);
  }

  /**
   * 获取企业用户列表.
   * @throws Exception
   */
  @Test
  public void getBuUserList() throws Exception {
    String uri = "/chain/api/user/admin/business/userList";
    Map queryMap = Maps.newHashMap();
    queryMap .put("businessAffiliationId",1+"");
    queryMap .put("page",1+"");
    queryMap .put("pageSize",10+"");
    ResponseVo responseVo = creditClient.executeForm(uri,"GET",null,queryMap, initLoginMap());
    System.out.println(responseVo);
  }


  /**
   * 创建企业信息.
   */
  @Test
  public void updateBu() throws Exception {
    String uri = "/chain/api/user/admin/business/update";

    Map reqMap = Maps.newHashMap();
    reqMap.put("id",1);
    reqMap.put("creditNo","41234234");
    reqMap.put("name","马传林的测试");

    ResponseVo responseVo = creditClient.executeJson(uri,
        "POST",
        JsonUtils.toJson(reqMap),
        Maps.newHashMap(),initLoginMap());
    System.out.println(responseVo);
  }
}
