package com.csci.cloud.client.model;


/**
 * Created by ben on 2018/9/26. benkris1@126.com
 * 反欺诈业务.
 */

public class MirrorFraud001ReqVo {

  /**
   * 客户身份证号.
   */
  private String idNo;
  /**
   * 客户姓名.
   */
  private String name;
  /**
   * 客户手机号码.
   */
  private String mobile;
  /**
   * 客户单位名称.
   */
  private String orgName;
  /**
   * 客户单位电话.
   */
  private String orgPhone;
  /**
   * 客户单位GPS位置 格式为：精度,维度.
   */
  private String orgLocGps;
  /**
   * 外部数据（如滴滴）获取单位地址GPS，格式：精度,纬度如114.06128224887138,22.54775263769751.
   */
  private String orgLoc3rdGps;
  /**
   * 外部数据（如滴滴）获取家庭地址GPS，格式：精度,纬度如114.06128224887138,22.54775263769751.
   */
  private String homeLoc3rdGps;
  /**
   * 工作城市 如 “深圳市”.
   */
  private String workCity;
  /**
   * 授信额度.
   */
  private String creditLimit;
  /**
   * 客户学历：博士/硕士/本科/大专/中专/高中/初中/小学/其他.
   */
  private String eduLevel;
  /**
   * 婚姻状态：未婚/已婚/离异/其他.
   */
  private String marriageStatus;
  /**
   * 手机安装app数量.
   */
  private Integer mobileInstallApp;
  /**
   * 客户登录时打开搜索引擎APP，查询高风险类字词.
   */
  private String appSearchWords;
  /**
   *客户同时打开贷款申请APP数量.
   */
  private String mobileRunApp;
  /**
   *客户手机语言 如中文”zh-CN”.
   */
  private String mobileLanguage;
  /**
   * 客户手机是否root。 是:”Y” 否:”N”.
   */
  private String  mobileIsRoot;
  /**
   * 是否为模拟器登录。是:”Y” 否:”N”.
   */
  private String mobileIsSimulator;
  /**
   * 客户是否使用代理IP登录。是:”Y” 否:”N”.
   */
  private String mobileIsProxyIp;
  /**
   *客户手机sim卡个数。
   */
  private Integer mobileSimCount;
  /**
   *客户手机Line1手机号.
   */
  private String mobileLine1Num;
  /**
   * 客户手机GPS，格式：精度,纬度 如114.06128224887138,22.54775263769751.
   */
  private String mobileLocGps;
  /**
   * 常用GPS，格式：精度,纬度 如114.06128224887138,22.54775263769751.
   */
  private String commonUsedLocGps;
  /**
   * 客户手机接入的wifi ip地址。如172.168.10.1.
   */
  private String wifiIp;
  /**
   * 客户手机接入的wifi的网关地址。如172.168.10.1.
   */
  private String wifiGateway;
  /**
   * 附近wifi的名称。多个用逗号“,”隔开。如“tplink001,tplink002”.
   */
  private String nearWifis;
  /**
   * 客户连接wifi的ssid.
   */
  private String wifiSsid;
  /**
   * 客户连接wifi的bssid.
   */
  private String wifiBssid;

  public String getIdNo() {
    return idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOrgPhone() {
    return orgPhone;
  }

  public void setOrgPhone(String orgPhone) {
    this.orgPhone = orgPhone;
  }

  public String getOrgLocGps() {
    return orgLocGps;
  }

  public void setOrgLocGps(String orgLocGps) {
    this.orgLocGps = orgLocGps;
  }

  public String getOrgLoc3rdGps() {
    return orgLoc3rdGps;
  }

  public void setOrgLoc3rdGps(String orgLoc3rdGps) {
    this.orgLoc3rdGps = orgLoc3rdGps;
  }

  public String getHomeLoc3rdGps() {
    return homeLoc3rdGps;
  }

  public void setHomeLoc3rdGps(String homeLoc3rdGps) {
    this.homeLoc3rdGps = homeLoc3rdGps;
  }

  public String getWorkCity() {
    return workCity;
  }

  public void setWorkCity(String workCity) {
    this.workCity = workCity;
  }

  public String getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(String creditLimit) {
    this.creditLimit = creditLimit;
  }

  public String getEduLevel() {
    return eduLevel;
  }

  public void setEduLevel(String eduLevel) {
    this.eduLevel = eduLevel;
  }

  public String getMarriageStatus() {
    return marriageStatus;
  }

  public void setMarriageStatus(String marriageStatus) {
    this.marriageStatus = marriageStatus;
  }

  public Integer getMobileInstallApp() {
    return mobileInstallApp;
  }

  public void setMobileInstallApp(Integer mobileInstallApp) {
    this.mobileInstallApp = mobileInstallApp;
  }

  public String getAppSearchWords() {
    return appSearchWords;
  }

  public void setAppSearchWords(String appSearchWords) {
    this.appSearchWords = appSearchWords;
  }

  public String getMobileRunApp() {
    return mobileRunApp;
  }

  public void setMobileRunApp(String mobileRunApp) {
    this.mobileRunApp = mobileRunApp;
  }

  public String getMobileLanguage() {
    return mobileLanguage;
  }

  public void setMobileLanguage(String mobileLanguage) {
    this.mobileLanguage = mobileLanguage;
  }

  public String getMobileIsRoot() {
    return mobileIsRoot;
  }

  public void setMobileIsRoot(String mobileIsRoot) {
    this.mobileIsRoot = mobileIsRoot;
  }

  public String getMobileIsSimulator() {
    return mobileIsSimulator;
  }

  public void setMobileIsSimulator(String mobileIsSimulator) {
    this.mobileIsSimulator = mobileIsSimulator;
  }

  public String getMobileIsProxyIp() {
    return mobileIsProxyIp;
  }

  public void setMobileIsProxyIp(String mobileIsProxyIp) {
    this.mobileIsProxyIp = mobileIsProxyIp;
  }

  public Integer getMobileSimCount() {
    return mobileSimCount;
  }

  public void setMobileSimCount(Integer mobileSimCount) {
    this.mobileSimCount = mobileSimCount;
  }

  public String getMobileLine1Num() {
    return mobileLine1Num;
  }

  public void setMobileLine1Num(String mobileLine1Num) {
    this.mobileLine1Num = mobileLine1Num;
  }

  public String getMobileLocGps() {
    return mobileLocGps;
  }

  public void setMobileLocGps(String mobileLocGps) {
    this.mobileLocGps = mobileLocGps;
  }

  public String getCommonUsedLocGps() {
    return commonUsedLocGps;
  }

  public void setCommonUsedLocGps(String commonUsedLocGps) {
    this.commonUsedLocGps = commonUsedLocGps;
  }

  public String getWifiIp() {
    return wifiIp;
  }

  public void setWifiIp(String wifiIp) {
    this.wifiIp = wifiIp;
  }

  public String getWifiGateway() {
    return wifiGateway;
  }

  public void setWifiGateway(String wifiGateway) {
    this.wifiGateway = wifiGateway;
  }

  public String getNearWifis() {
    return nearWifis;
  }

  public void setNearWifis(String nearWifis) {
    this.nearWifis = nearWifis;
  }

  public String getWifiSsid() {
    return wifiSsid;
  }

  public void setWifiSsid(String wifiSsid) {
    this.wifiSsid = wifiSsid;
  }

  public String getWifiBssid() {
    return wifiBssid;
  }

  public void setWifiBssid(String wifiBssid) {
    this.wifiBssid = wifiBssid;
  }
}
