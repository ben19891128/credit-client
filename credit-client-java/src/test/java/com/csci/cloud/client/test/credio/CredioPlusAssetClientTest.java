package com.csci.cloud.client.test.credio;


import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.ResponseVo;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * 金融资产及交易接口测试.
 */
public class CredioPlusAssetClientTest extends AbstractCredioPushTest {


    /**
     * 资产发布.
     */
    @Test
    public void issue() throws Exception {
        String issue = createIssue();
        System.out.println(issue);
    }

    @Test
    public void updateIssue() throws Exception {
        String assetId = createIssue();
        TimeUnit.SECONDS.sleep(5);//由于资产发布时异步过程，在此休息2s然后再更新
        String uri = "/chain/api/data/asset/updateStatus";
        ResponseVo responseVo = creditClient.executeJson(uri,
            "PUT",
            JsonUtils.toJson(new Builder<>().put("assetId",assetId).put("status","05").build()),
            Maps.newHashMap(),
            defaultHeaderMap);
        System.out.println(responseVo);
    }


    /**
     * 查询上链资产.
     * @throws Exception
     */
    @Test
    public void queryAssetInfoById() throws Exception {
        String uri = "/chain/api/data/asset/queryAssetInfoById/INV0001";
        Map queryMap = Maps.newHashMap();
        ResponseVo responseVo = creditClient.executeForm(uri,"GET",null,queryMap, initLoginMap());
        System.out.println(responseVo);
    }


    /**
     * 查询上链交易易信息.
     * @throws Exception
     */
    @Test
    public void queryTransInfoById() throws Exception {
        String uri = "/chain/api/data/asset/queryTransInfoById/123213";
        Map queryMap = Maps.newHashMap();
        ResponseVo responseVo = creditClient.executeForm(uri,"GET",null,queryMap, initLoginMap());
        System.out.println(responseVo);
    }


}
