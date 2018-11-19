package com.csci.cloud.client.test.credio;

import com.csci.cloud.client.model.ResponseVo;
import com.google.common.collect.Maps;
import java.util.Map;
import org.junit.Test;

public class CredioPushAssetQueryClientTest extends AbstractCredioPushTest {


  /**
   *  交易易最新状态查询
   * @throws Exception
   */
  @Test
  public void queryAsset() throws Exception {
    String assetId = "RQoPSZUYQHjDYbWXfxYggIzbXHkWopOt";
    String uri = "/chain/api/es-query/client/assetOnChain/findLatestStatusAssetDetailByAssetId";
    Map<String, String> paraMap = Maps.newHashMap();
    paraMap.put("assetId",assetId);
    paraMap.put("productName","1234");
    ResponseVo responseVo = creditClient.executeJson(uri,
        "GET",
        null,
        paraMap,
        initLoginMap());
    System.out.println(responseVo);
  }

  /**
   *  资产流转记录查询(由交易易往前追溯)
   * @throws Exception
   */
  @Test
  public void findTransFlowByTransId() throws Exception {
    String transId = "123213";
    String uri = "/chain/api/es-query/client/assetOnChain/findTransFlowByTransId";
    Map<String, String> paraMap = Maps.newHashMap();
    paraMap.put("transId",transId);
    paraMap.put("productName","ASSET_2_BLOCKCHAIN");
    ResponseVo responseVo = creditClient.executeJson(uri,
        "GET",
        null,
        paraMap,
        initLoginMap());
    System.out.println(responseVo);
  }

}
