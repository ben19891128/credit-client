package com.csci.cloud.client.model;

/**
 * 用户注册.
 */
public class CredioPlusRegisterVo {

    /**
     * ⽤用户所属的affiliationId, 由具体场景确定.
     */
    private Integer businessAffiliationId;



    private String username;
    private String password;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBusinessAffiliationId() {
        return businessAffiliationId;
    }

    public void setBusinessAffiliationId(Integer businessAffiliationId) {
        this.businessAffiliationId = businessAffiliationId;
    }
}
