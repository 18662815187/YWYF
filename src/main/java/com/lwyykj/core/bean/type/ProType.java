package com.lwyykj.core.bean.type;

import java.io.Serializable;

public class ProType implements Serializable {
    private Integer id;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 分类图片
     */
    private String typeLogo;

    private Integer addTime;

    /**
     * 父级ID
     */
    private Integer parentId;

    private Boolean isDel;

    /**
     * 临时字段父级名字
     */
    private String pTypeName;
    
    private static final long serialVersionUID = 1L;

    public String getpTypeName() {
		return pTypeName;
	}

	public void setpTypeName(String pTypeName) {
		this.pTypeName = pTypeName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeLogo() {
        return typeLogo;
    }

    public void setTypeLogo(String typeLogo) {
        this.typeLogo = typeLogo == null ? null : typeLogo.trim();
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typeName=").append(typeName);
        sb.append(", typeLogo=").append(typeLogo);
        sb.append(", addTime=").append(addTime);
        sb.append(", parentId=").append(parentId);
        sb.append(", isDel=").append(isDel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}