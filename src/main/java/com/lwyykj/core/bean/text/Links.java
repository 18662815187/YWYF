package com.lwyykj.core.bean.text;

import java.io.Serializable;

public class Links implements Serializable {
    private Integer id;

    private String linkUrl;

    /**
     * 0=正常，1=禁用，2=已删除
     */
    private Integer linkStatus;

    /**
     * 排序值0-9
     */
    private Integer listorder;

    private String linkName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Integer getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
    }

    public Integer getListorder() {
        return listorder;
    }

    public void setListorder(Integer listorder) {
        this.listorder = listorder;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", linkStatus=").append(linkStatus);
        sb.append(", listorder=").append(listorder);
        sb.append(", linkName=").append(linkName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}