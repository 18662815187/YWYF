package com.lwyykj.core.bean.text;

import java.io.Serializable;
import java.util.Date;

public class Drugstore implements Serializable {
    private Integer id;

    private String name;

    private String phone;

    private String pharmacy;

    private String password;

    /**
     * 执业医生资格证书
     */
    private String cdqc;

    /**
     * 营业执照
     */
    private String businesslicense;

    /**
     * GSP证
     */
    private String gsp;

    /**
     * 管理员手持身份证照片
     */
    private String handheldphoto;

    /**
     * 经营许可证
     */
    private String businesspermit;

    /**
     * 0= 未通过，1=通过
     */
    private Boolean status;

    private Date addtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy == null ? null : pharmacy.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCdqc() {
        return cdqc;
    }

    public void setCdqc(String cdqc) {
        this.cdqc = cdqc == null ? null : cdqc.trim();
    }

    public String getBusinesslicense() {
        return businesslicense;
    }

    public void setBusinesslicense(String businesslicense) {
        this.businesslicense = businesslicense == null ? null : businesslicense.trim();
    }

    public String getGsp() {
        return gsp;
    }

    public void setGsp(String gsp) {
        this.gsp = gsp == null ? null : gsp.trim();
    }

    public String getHandheldphoto() {
        return handheldphoto;
    }

    public void setHandheldphoto(String handheldphoto) {
        this.handheldphoto = handheldphoto == null ? null : handheldphoto.trim();
    }

    public String getBusinesspermit() {
        return businesspermit;
    }

    public void setBusinesspermit(String businesspermit) {
        this.businesspermit = businesspermit == null ? null : businesspermit.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", pharmacy=").append(pharmacy);
        sb.append(", password=").append(password);
        sb.append(", cdqc=").append(cdqc);
        sb.append(", businesslicense=").append(businesslicense);
        sb.append(", gsp=").append(gsp);
        sb.append(", handheldphoto=").append(handheldphoto);
        sb.append(", businesspermit=").append(businesspermit);
        sb.append(", status=").append(status);
        sb.append(", addtime=").append(addtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}