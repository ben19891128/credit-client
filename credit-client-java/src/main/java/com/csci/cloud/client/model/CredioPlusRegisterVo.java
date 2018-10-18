package com.csci.cloud.client.model;

/**
 * 用户注册.
 */
public class CredioPlusRegisterVo {

    /**
     * ⽤用户所属的affiliationId, 由具体场景确定.
     */
    private Integer blockchainAffiliationId;

    /**
     * ⽤用户⻆角⾊色, 必须为CLIENT.
     */
    private String role;

    private String username;
    private String password;

    public Integer getBlockchainAffiliationId() {
        return blockchainAffiliationId;
    }

    public void setBlockchainAffiliationId(Integer blockchainAffiliationId) {
        this.blockchainAffiliationId = blockchainAffiliationId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
}
