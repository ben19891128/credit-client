package com.csci.cloud.client.model;

/**
 * 资产发布.
 */
public class CredioPlushIssueVo {

    /**
     * 资产唯一编号.
     */
    private String assetId;

    /**
     * 资产原始信息,json string，具体定义⻅见资产信息详 情定义内容.
     */
    private String assetInfo;

    /**
     * 针对资产信息(assetInfo)的hash值.
     */
    private String hash;

    /**
     * 是否需要平台对资产信息的字段进⾏行行加密.
     */
    private Boolean needEncrypted;

    /**
     * 加密字段集，使⽤用竖线分割，必填.
     */
    private String encryptedFields;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetInfo() {
        return assetInfo;
    }

    public void setAssetInfo(String assetInfo) {
        this.assetInfo = assetInfo;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Boolean getNeedEncrypted() {
        return needEncrypted;
    }

    public void setNeedEncrypted(Boolean needEncrypted) {
        this.needEncrypted = needEncrypted;
    }

    public String getEncryptedFields() {
        return encryptedFields;
    }

    public void setEncryptedFields(String encryptedFields) {
        this.encryptedFields = encryptedFields;
    }


}
