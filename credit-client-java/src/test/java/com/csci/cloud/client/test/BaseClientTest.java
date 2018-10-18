package com.csci.cloud.client.test;

import com.csci.cloud.client.CreditClient;
import com.csci.cloud.client.common.Const;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import org.junit.Before;

import javax.xml.ws.Holder;
import java.util.HashMap;

public abstract class BaseClientTest {

    public static final String API_KEY = "ntjhb0v6thrwaujqttytbzayow5ozw";
    public static final String SECRET = "m2i5oddjmgzhmgi0ndk2m2jhytjkmznjmzdhymfkmwq";
    protected static String HOST = "http://localhost:8020";
    private String token = "bearer 927fd4be-23ec-4d41-b0d3-ceec35e320f4";

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


    protected  HashMap<String, String> defaultHeaderMap = Maps.newHashMap();

    protected CreditClient creditClient ;

    @Before
    public void before() {

        creditClient = new CreditClient.Builder()
                .apiKey(API_KEY)
                .secret(SECRET)
                .basePath(HOST)
                .build();
        defaultHeaderMap.put(Const.CREDIO_PLUS_CLOUD_TOKEN_HEADER,token);
    }

}
