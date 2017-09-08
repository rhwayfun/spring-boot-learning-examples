package com.rhwayfun.springboot.memory;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chubin on 2017/8/20.
 */
public class RiderBasicInfoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5897192528635535802L;

    /**
     * 骑手Id
     */
    private Integer id;
    /**
     * 城市Id
     */
    private Integer cityId;
    /**
     * 设备号
     */
    private String imei;
    /**
     * 骑手身份证号
     */
    private String code;
    /**
     * 审核通过时间
     */
    private Date verifiedTm;
    /**
     * 注册城市
     */
    private Integer registCityId;
    /**
     * 骑手姓名
     */
    private String name;
    /**
     * 骑手手机号码
     */
    private String mobile;
    /**
     * 骑手状态
     */
    private Byte status;
    /**
     * 骑手等级
     */
    private Byte level;
    /**
     * 骑手类型
     */
    private Byte type;
    /**
     * 审核结果
     */
    private Byte verified;
    /**
     * 客户端信息
     */
    private String client;
    /**
     * 骑士工号
     */
    private String jobNumber;
    /**
     * 黑名单(屏蔽的商家id列表)
     */
    private String blackList;
    /**
     * 接单模式
     */
    private Byte takeMode;
    /**
     * 接单上限
     */
    private Integer orderCelling;
    /**
     * 所属区域ID
     */
    private Integer regionId;
    /**
     * 附加信息
     */
    private String feature;
    /**
     * 骑手邀请码
     */
    private String inviteId;
    /**
     * 被邀请码
     */
    private String invitedId;
    /**
     * 禁用截止时间
     */
    private Date forbiddenTime;
    /**
     * 审批或拒绝原因记录
     */
    private String remarks;

    /**
     * 是否离线派单--（尚未启用，所有返回都是null）
     */
    private Boolean enableLeaveShopDispatch;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Date getVerifiedTm() {
        return verifiedTm;
    }
    public void setVerifiedTm(Date verifiedTm) {
        this.verifiedTm = verifiedTm;
    }
    public Integer getRegistCityId() {
        return registCityId;
    }
    public void setRegistCityId(Integer registCityId) {
        this.registCityId = registCityId;
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
    public Byte getLevel() {
        return level;
    }
    public void setLevel(Byte level) {
        this.level = level;
    }
    public Byte getType() {
        return type;
    }
    public void setType(Byte type) {
        this.type = type;
    }
    public Byte getVerified() {
        return verified;
    }
    public void setVerified(Byte verified) {
        this.verified = verified;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public String getJobNumber() {
        return jobNumber;
    }
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
    public String getBlackList() {
        return blackList;
    }
    public void setBlackList(String blackList) {
        this.blackList = blackList;
    }
    public Byte getTakeMode() {
        return takeMode;
    }
    public void setTakeMode(Byte takeMode) {
        this.takeMode = takeMode;
    }
    public Integer getOrderCelling() {
        return orderCelling;
    }
    public void setOrderCelling(Integer orderCelling) {
        this.orderCelling = orderCelling;
    }
    public Integer getRegionId() {
        return regionId;
    }
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    public String getFeature() {
        return feature;
    }
    public void setFeature(String feature) {
        this.feature = feature;
    }
    public String getInviteId() {
        return inviteId;
    }
    public void setInviteId(String inviteId) {
        this.inviteId = inviteId;
    }
    public String getInvitedId() {
        return invitedId;
    }
    public void setInvitedId(String invitedId) {
        this.invitedId = invitedId;
    }
    public Date getForbiddenTime() {
        return forbiddenTime;
    }
    public void setForbiddenTime(Date forbiddenTime) {
        this.forbiddenTime = forbiddenTime;
    }
    public Byte getStatus() {
        return status;
    }
    public void setStatus(Byte status) {
        this.status = status;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getUseLeaveShopDispatch() {
        return enableLeaveShopDispatch;
    }
    public void setUseLeaveShopDispatch(Boolean enableLeaveShopDispatch) {
        this.enableLeaveShopDispatch = enableLeaveShopDispatch;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((blackList == null) ? 0 : blackList.hashCode());
        result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((feature == null) ? 0 : feature.hashCode());
        result = prime * result + ((forbiddenTime == null) ? 0 : forbiddenTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((imei == null) ? 0 : imei.hashCode());
        result = prime * result + ((inviteId == null) ? 0 : inviteId.hashCode());
        result = prime * result + ((invitedId == null) ? 0 : invitedId.hashCode());
        result = prime * result + ((jobNumber == null) ? 0 : jobNumber.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((orderCelling == null) ? 0 : orderCelling.hashCode());
        result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
        result = prime * result + ((registCityId == null) ? 0 : registCityId.hashCode());
        result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((takeMode == null) ? 0 : takeMode.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((verified == null) ? 0 : verified.hashCode());
        result = prime * result + ((verifiedTm == null) ? 0 : verifiedTm.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RiderBasicInfoDTO other = (RiderBasicInfoDTO) obj;
        if (blackList == null) {
            if (other.blackList != null)
                return false;
        } else if (!blackList.equals(other.blackList))
            return false;
        if (cityId == null) {
            if (other.cityId != null)
                return false;
        } else if (!cityId.equals(other.cityId))
            return false;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (feature == null) {
            if (other.feature != null)
                return false;
        } else if (!feature.equals(other.feature))
            return false;
        if (forbiddenTime == null) {
            if (other.forbiddenTime != null)
                return false;
        } else if (!forbiddenTime.equals(other.forbiddenTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (imei == null) {
            if (other.imei != null)
                return false;
        } else if (!imei.equals(other.imei))
            return false;
        if (inviteId == null) {
            if (other.inviteId != null)
                return false;
        } else if (!inviteId.equals(other.inviteId))
            return false;
        if (invitedId == null) {
            if (other.invitedId != null)
                return false;
        } else if (!invitedId.equals(other.invitedId))
            return false;
        if (jobNumber == null) {
            if (other.jobNumber != null)
                return false;
        } else if (!jobNumber.equals(other.jobNumber))
            return false;
        if (level == null) {
            if (other.level != null)
                return false;
        } else if (!level.equals(other.level))
            return false;
        if (mobile == null) {
            if (other.mobile != null)
                return false;
        } else if (!mobile.equals(other.mobile))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (orderCelling == null) {
            if (other.orderCelling != null)
                return false;
        } else if (!orderCelling.equals(other.orderCelling))
            return false;
        if (regionId == null) {
            if (other.regionId != null)
                return false;
        } else if (!regionId.equals(other.regionId))
            return false;
        if (registCityId == null) {
            if (other.registCityId != null)
                return false;
        } else if (!registCityId.equals(other.registCityId))
            return false;
        if (remarks == null) {
            if (other.remarks != null)
                return false;
        } else if (!remarks.equals(other.remarks))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (takeMode == null) {
            if (other.takeMode != null)
                return false;
        } else if (!takeMode.equals(other.takeMode))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (verified == null) {
            if (other.verified != null)
                return false;
        } else if (!verified.equals(other.verified))
            return false;
        if (verifiedTm == null) {
            if (other.verifiedTm != null)
                return false;
        } else if (!verifiedTm.equals(other.verifiedTm))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "RiderBasicInfoDTO{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", imei='" + imei + '\'' +
                ", code='" + code + '\'' +
                ", verifiedTm=" + verifiedTm +
                ", registCityId=" + registCityId +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", level=" + level +
                ", type=" + type +
                ", verified=" + verified +
                ", client='" + client + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", blackList='" + blackList + '\'' +
                ", takeMode=" + takeMode +
                ", orderCelling=" + orderCelling +
                ", regionId=" + regionId +
                ", feature='" + feature + '\'' +
                ", inviteId='" + inviteId + '\'' +
                ", invitedId='" + invitedId + '\'' +
                ", forbiddenTime=" + forbiddenTime +
                ", remarks='" + remarks + '\'' +
                ", enableLeaveShopDispatch=" + enableLeaveShopDispatch +
                '}';
    }
}

