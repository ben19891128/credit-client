package com.csci.cloud.client.test.credio;

import com.csci.cloud.client.CreditClient;
import com.csci.cloud.client.common.JsonUtils;
import com.csci.cloud.client.model.CredioPlusRegisterVo;
import com.csci.cloud.client.model.ResponseVo;
import com.csci.cloud.client.test.BaseClientTest;
import com.google.common.collect.Maps;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.junit.Test;

/**
 * 区块链单元测试类.
 */
public class CredioPlusUserClientTest extends BaseClientTest {

    private static final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");

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
        ResponseVo responseVo = creditClient.executeJson( uri,
                "POST",
                JsonUtils.toJson(credioPlusRegisterVo),
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

        ResponseVo responseVo = creditClient.executeForm(uri,"POST",formBody, Maps.newHashMap(),Maps.newHashMap());
        System.out.println(responseVo);
    }

    /**
     * 获取用户数量.
     * @throws Exception
     */
    @Test
    public void getUserCount() throws Exception {
        String uri = "/chain/api/user/client/user/count";
        ResponseVo responseVo = creditClient.executeJson(uri,"GET",null,Maps.newHashMap(), defaultHeaderMap);
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
        ResponseVo responseVo = creditClient.execute(uri, "POST",requestBody, Maps.newHashMap(), defaultHeaderMap);
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
        ResponseVo responseVo = creditClient.execute(uri, "POST",requestBody, Maps.newHashMap(), defaultHeaderMap);
        System.out.println(responseVo);
    }

    /**
     * 下载私钥.
     * @throws Exception
     */
    private String downLoadPrivateKeyRaw() throws Exception {
        String uri = "/chain/api/user/client/user/privateKey";
        String rawText = creditClient.download(uri,"GET",null,Maps.newHashMap(), defaultHeaderMap);
        return rawText;
    }


    /**
     * 下载证书.
     * @throws Exception
     */
    private String downLoadCertificateRaw() throws Exception {
        String uri = "/chain/api/user/client/user/cert";
        String rawText = creditClient.download(uri,"GET",null,Maps.newHashMap(), defaultHeaderMap);
        return rawText;
    }


}
