package com.csci.cloud.client.model;


/**
 * @author ben
 */
public class CreditScoreReqVo {

  /**
   * 身份证号.
   */
  private String idNo;
  /**
   * 姓名.
   */
  private String name;
  /**
   * 电话.
   */
  private String mobile;

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


}
