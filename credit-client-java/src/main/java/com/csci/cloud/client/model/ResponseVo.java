package com.csci.cloud.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseVo<T> {


  private String code;
  private String errorMessage;
  private T data;
  private Boolean success;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }


  @Override
  public String toString() {
    return "ResponseVo{" +
            "code='" + code + '\'' +
            ", errorMessage='" + errorMessage + '\'' +
            ", data=" + data +
            ", success=" + success +
            '}';
  }
}
