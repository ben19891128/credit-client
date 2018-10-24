package com.csci.cloud.client.test.credio;

import com.csci.cloud.client.CreditClient;
import com.csci.cloud.client.common.Const;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.CredioPlushIssueVo;
import com.csci.cloud.client.model.ResponseVo;
import com.csci.cloud.client.test.BaseClientTest;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import java.util.HashMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;

public class AbstractCredioPushTest extends BaseClientTest {
  private String token = "bearer 2460bbb3-010b-4bbc-881f-372274fc8fb4";

  public static final String CERT_DEMO = "-----BEGIN CERTIFICATE-----\n" +
      "MIICFDCCAbugAwIBAgIQXaC5je7igJQYWo++E2OkcjAKBggqhkjOPQQDAjBlMQsw\n" +
      "CQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZy\n" +
      "YW5jaXNjbzESMBAGA1UEChMJb3JnMS10ZXN0MRUwEwYDVQQDEwxjYS5vcmcxLXRl\n" +
      "c3QwHhcNMTgwOTI2MDY0NTIwWhcNMjgwOTIzMDY0NTIwWjBlMQswCQYDVQQGEwJV\n" +
      "UzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZyYW5jaXNjbzEP\n" +
      "MA0GA1UECxMGY2xpZW50MRgwFgYDVQQDDA9BZG1pbkBvcmcxLXRlc3QwWTATBgcq\n" +
      "hkjOPQIBBggqhkjOPQMBBwNCAAR8MA3I/ojUOoM6AsUKu16CkYb3AUJ1VIu2GQtr\n" +
      "RzVdLt6cDB9lesLTfJWEEFSbATPL4/rbfYsMjYhBtUPziQXeo00wSzAOBgNVHQ8B\n" +
      "Af8EBAMCB4AwDAYDVR0TAQH/BAIwADArBgNVHSMEJDAigCAe4Rq6D2Y/E4i8Wn4K\n" +
      "hSMpGqlMUqVB5lKQgCjuhD0QbDAKBggqhkjOPQQDAgNHADBEAiBciYitpF9DCFTt\n" +
      "JaCgB0WyPmsMuUWsfoE8KFM5YBWpsgIgD86eiqZVo7CvDF+SSOFXd0c8elnTWSc8\n" +
      "5uCjgLLyblU=\n" +
      "-----END CERTIFICATE-----";

  public static final String PRIVATE_KEY_DEMO = "-----BEGIN PRIVATE KEY-----\n" +
      "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgnMrgOYDMMu4mYD0Y\n" +
      "y3q3IyR8QPEw0bygYZbImGJh/0ahRANCAAR8MA3I/ojUOoM6AsUKu16CkYb3AUJ1\n" +
      "VIu2GQtrRzVdLt6cDB9lesLTfJWEEFSbATPL4/rbfYsMjYhBtUPziQXe\n" +
      "-----END PRIVATE KEY-----";


  protected HashMap<String, String> defaultHeaderMap = Maps.newHashMap();

  @Before
  public void before() {

    defaultHeaderMap.put(Const.CREDIO_PLUS_CLOUD_TOKEN_HEADER,token);
  }


  protected String createIssue () throws Exception {
    String uri = "/chain/api/data/asset/issue";
    CredioPlushIssueVo credioPlushIssueVo = new CredioPlushIssueVo();
    String randomAssetId = RandomStringUtils.randomAlphabetic(32);
    credioPlushIssueVo.setAssetId(randomAssetId);
    String assetInfo = String.format("{\"assetId\":\"%s\",\"assetType\":\"INV\",\"upperAssetId\":\"\"," +
        "\"category1\":\"1\",\"category2\":\"2\",\"channelCode\":\"123\",\"assetName\":\"fabiao\"," +
        "\"status\":\"01\",\"totalAmt\":\"10000\",\"startDate\":\"2018-10-12T00:00:00\"," +
        "\"endDate\":\"2018-12-12T00:00:00\",\"issuer\":\"issuer1\",\"issuerId\":\"123\",\"owner\":\"owner\"," +
        "\"ownerId\":\"12323\",\"period\":\"2\",\"metaData\":\"hello\",\"transType\":\"lj\"," +
        "\"encryptedFields\":\"ownerId|issuer1\",\"issueTime\":\"2018-12-12T00:00:00\"}",randomAssetId);
    credioPlushIssueVo.setAssetInfo(assetInfo);
    credioPlushIssueVo.setHash(Hashing.md5().hashString(assetInfo, Charsets.UTF_8).toString());
    credioPlushIssueVo.setNeedEncrypted(false);
    credioPlushIssueVo.setEncryptedFields("name|idNo|cellphone");
    ResponseVo responseVo = creditClient.executeJson(uri,
        "POST",
        JsonUtils.toJson(Lists.newArrayList(credioPlushIssueVo)),
        Maps.newHashMap(),
        defaultHeaderMap);
    return randomAssetId;
  }
}
