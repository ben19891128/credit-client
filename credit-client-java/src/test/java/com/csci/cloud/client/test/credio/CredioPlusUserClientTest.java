package com.csci.cloud.client.test.credio;

import com.csci.cloud.client.CreditClient;
import com.csci.cloud.client.common.Const;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.CredioPlusRegisterVo;
import com.csci.cloud.client.model.ResponseVo;
import com.csci.cloud.client.test.BaseClientTest;
import com.google.common.collect.Maps;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * 区块链单元测试类.
 */
public class CredioPlusUserClientTest extends BaseClientTest {
    private static String HOST = "http://localhost:8020";

    private String token = "bearer b8ccc00b-d8bd-4306-a7b4-a7447d70221e";

    private static final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");

    HashMap<String, String> headerMap = Maps.newHashMap();

    @Before
    public void before() {

        headerMap.put(Const.CREDIO_PLUS_CLOUD_TOKEN_HEADER,token);
    }


    /**
     * 登录.
     * @throws Exception
     */
    @Test
    public void register() throws Exception {
        String uri = "/chain/api/user/client/user/create";
        CredioPlusRegisterVo credioPlusRegisterVo = new CredioPlusRegisterVo();
        credioPlusRegisterVo.setBlockchainAffiliationId(0);
        credioPlusRegisterVo.setRole("CLIENT");
        credioPlusRegisterVo.setUsername("ben.ma");
        credioPlusRegisterVo.setPassword("123445567");
        ResponseVo responseVo = CreditClient.executeJson(HOST, uri,"POST",
                API_KEY, SECRET, JsonUtils.toJson(credioPlusRegisterVo),
                Maps.newHashMap(),Maps.newHashMap());
        System.out.println(responseVo);
    }

    /**
     * 登录.
     * @throws Exception
     */
    @Test
    public void login() throws Exception {
        String uri = "/chain/uaa/oauth/token";

        FormBody formBody = new FormBody.Builder()
                .add("username","ADMIN")
                .add("password","adminpw")
                .add("grant_type","password")
                .build();

        ResponseVo responseVo = CreditClient.executeForm(HOST, uri,"POST", API_KEY, SECRET, formBody, Maps.newHashMap(),Maps.newHashMap());
        System.out.println(responseVo);
    }

    /**
     * 获取用户数量.
     * @throws Exception
     */
    @Test
    public void getUserCount() throws Exception {
        String uri = "/chain/api/user/client/user/count";
        ResponseVo responseVo = CreditClient.executeJson(HOST,uri,"GET",API_KEY,SECRET,null,Maps.newHashMap(),headerMap);
        System.out.println(responseVo);
    }

    /**
     * 下载证书.
     */
    @Test
    public void downLoadCertificate() throws Exception {
        String cert = downLoadCertificateRaw();
        System.out.println(cert);
    }

    /**
     * 上传证书.
     */
    @Test
    public void uploadCertificate() throws Exception {

        String rawCert = downLoadCertificateRaw();
        String uri = "/chain/api/user/client/user/cert";
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "cert.txt",
                        RequestBody.create(MEDIA_TYPE_STREAM, rawCert))
                .build();
        ResponseVo responseVo = CreditClient.execute(HOST, uri, "POST", API_KEY, SECRET, requestBody, Maps.newHashMap(), headerMap);
        System.out.println(responseVo);
    }

    /**
     * 下载私钥.
     */
    @Test
    public void downLoadPrivateKey() throws Exception {
        String cert = downLoadPrivateKeyRaw();
        System.out.println(cert);
    }

    /**
     * 上传私钥.
     */
    @Test
    public void uploadPrivateKey() throws Exception {

        String privateKeyRaw = downLoadPrivateKeyRaw();
        String uri = "/chain/api/user/client/user/privateKey";
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "key.txt",
                        RequestBody.create(MEDIA_TYPE_STREAM, privateKeyRaw))
                .build();
        ResponseVo responseVo = CreditClient.execute(HOST, uri, "POST", API_KEY, SECRET, requestBody, Maps.newHashMap(), headerMap);
        System.out.println(responseVo);
    }

    /**
     * 下载私钥.
     * @throws Exception
     */
    private String downLoadPrivateKeyRaw() throws Exception {
        String uri = "/chain/api/user/client/user/privateKey";
        String rawText = CreditClient.download(HOST,uri,"GET",API_KEY,SECRET,null,Maps.newHashMap(),headerMap);
        return rawText;
    }


    /**
     * 下载证书.
     * @throws Exception
     */
    private String downLoadCertificateRaw() throws Exception {
        String uri = "/chain/api/user/client/user/cert";
        String rawText = CreditClient.download(HOST,uri,"GET",API_KEY,SECRET,null,Maps.newHashMap(),headerMap);
        return rawText;
    }


}
