package com.csci.cloud.client.test.credio;

import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.ResponseVo;
import com.google.common.collect.Maps;
import java.util.Map;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by ben on 2018/11/19. benkris1@126.com
 */
public class CredioEventHubClientTest extends AbstractCredioPushTest{

  /**
   * 添加订阅事件.
   */
  @Test
  @Ignore
  public void addHub() throws Exception {
    String uri = "/chain/api/eventhub/manager/add";

    Map bodyMap = Maps.newHashMap();
    bodyMap.put("channelId",0);
    bodyMap.put("peerName","testPeer");
    ResponseVo responseVo = creditClient.executeJson(uri,
        "POST",
        JsonUtils.toJson(bodyMap),
        Maps.newHashMap(),defaultHeaderMap);
    System.out.println(responseVo);
  }
}
