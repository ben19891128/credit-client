package com.csci.cloud.client.test.credio;

import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.CredioPlusRegisterVo;
import com.csci.cloud.client.model.ResponseVo;
import com.google.common.collect.Maps;
import java.util.Map;
import org.junit.Test;

/**
 * Created by ben on 2018/11/19. benkris1@126.com
 */
public class CredioPlushNodeInteractionITest extends AbstractCredioPushTest{

  /**
   * 合约调用
   */
  @Test
  public void invokeChainNode() throws Exception {

    String uri = "/chain/api/chain/client/chaincode/invoke";

    Map bodyMap = Maps.newHashMap();
    bodyMap.put("productId",1234+"");
    bodyMap.put("methodName","test");
    bodyMap.put("parameters",Maps.newHashMap());
    bodyMap.put("transientMap",Maps.newHashMap());
    ResponseVo responseVo = creditClient.executeJson(uri,
        "POST",
        JsonUtils.toJson(bodyMap),
        Maps.newHashMap(),defaultHeaderMap);
    System.out.println(responseVo);
  }
}
