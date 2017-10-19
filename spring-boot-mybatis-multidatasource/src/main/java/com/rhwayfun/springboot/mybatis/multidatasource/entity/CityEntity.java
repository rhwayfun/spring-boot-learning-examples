package com.rhwayfun.springboot.mybatis.multidatasource.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市实体类，纯POJO
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
public class CityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private boolean isActive;

    private Date createTime;

    private Date modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

}
