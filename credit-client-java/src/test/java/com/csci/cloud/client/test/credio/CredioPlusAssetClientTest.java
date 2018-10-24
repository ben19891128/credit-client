package com.csci.cloud.client.test.credio;


import com.csci.cloud.client.CreditClient;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.CredioPlushIssueVo;
import com.csci.cloud.client.model.ResponseVo;
import com.csci.cloud.client.test.BaseClientTest;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
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


}
