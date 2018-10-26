package com.csci.cloud.client.test.credio;

import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.ResponseVo;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class CredioPushAssetQueryClientTest extends AbstractCredioPushTest {


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
        defaultHeaderMap);
    System.out.println(responseVo);
  }

}
