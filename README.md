# 信用云客户端工具

该项目是辅助开发者调用中证信用云开放API.项目是基于`Java8`开发

## 开始使用

创建`CreditClient`对象

```java
CreditClient creditClient = new CreditClient.Builder()
        .apiKey(API_KEY)
        .secret(SECRET)
        .basePath(HOST)
        .build();
```

调用接口

```java
   MirrorFraud001ReqVo reqVo = new MirrorFraud001ReqVo();
    reqVo.setIdNo("341225198906274952");
    reqVo.setName("马化腾");
    reqVo.setOrgName("腾通讯");
    reqVo.setMobile("19015699662");
    String uri = "/api/v1/mirror/anti-fraud/001";
    HashMap<String, String> queryMap = Maps.newHashMap();
    ResponseVo responseVo = creditClient
        .executeJson(uri, "POST", JsonUtils.toJson(reqVo), queryMap, Maps.newHashMap());
    System.out.println(responseVo);
```



## 更多例子

请参考`credit-client-java/test/java` 目录下的例子