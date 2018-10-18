package com.csci.cloud.client.test.credio;


import com.csci.cloud.client.CreditClient;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.CredioPlushIssueVo;
import com.csci.cloud.client.model.ResponseVo;
import com.csci.cloud.client.test.BaseClientTest;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * 金融资产及交易接口测试.
 */
public class CredioPlusAssetClientTest extends BaseClientTest {


    /**
     * 资产发布.
     */
    @Test
    public void issue() throws Exception {
        String uri = "/chain/api/data/asset/issue";
        CredioPlushIssueVo credioPlushIssueVo = new CredioPlushIssueVo();
        String randomAssetId = RandomStringUtils.randomAlphabetic(32);
        credioPlushIssueVo.setAssetId(randomAssetId);
        String assetInfo = String.format("{\"assetId\":\"%s\",\"assetType\":\"INV\",\"upperAssetId\":\"\"," +
                "\"category1\":\"1\",\"category2\":\"2\",\"channelCode\":\"123\",\"assetName\":\"fabiao\"," +
                "\"status\":\"01\",\"totalAmt\":\"10000\",\"startDate\":\"2018-10-12T00:00:00\"," +
                "\"endDate\":\"2018-12-12T00:00:00\",\"issuer\":\"issuer1\",\"issuerId\":\"123\",\"owner\":\"owner\"," +
                "\"ownerId\":\"12323\",\"period\":\"2\",\"metaData\":\"asfasdfasdf\",\"transType\":\"lj\"," +
                "\"encryptedFields\":\"sdfas|asdfas\",\"issueTime\":\"2018-12-12T00:00:00\"}",randomAssetId);
        credioPlushIssueVo.setAssetInfo(assetInfo);
        credioPlushIssueVo.setHash(Hashing.md5().hashString(assetInfo, Charsets.UTF_8).toString());
        credioPlushIssueVo.setNeedEncrypted(false);
        credioPlushIssueVo.setEncryptedFields("name|idNo|cellphone");

        ResponseVo responseVo = creditClient.executeJson(uri,
                "POST",
                JsonUtils.toJson(Lists.newArrayList(credioPlushIssueVo)),
                Maps.newHashMap(),
                defaultHeaderMap);

        System.out.println(responseVo);

    }

}
