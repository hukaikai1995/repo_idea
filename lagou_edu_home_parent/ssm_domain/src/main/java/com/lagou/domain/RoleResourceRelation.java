package com.lagou.domain;

import java.util.Date;

/*
    id int(11) NOT NULL关系id
    resource_id int(11) NOT NULL资源id
    role_id int(11) NOT NULL角色id
    created_time datetime NOT NULL创建时间
    updated_time datetime NOT NULL更新时间
    created_by varchar(100) NOT NULL创建人
    updated_by varchar(100) NOT NULL更新人
 */
public class RoleResourceRelation {

    private int id;
    private int resourceId;
    private int roleId;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedby;

    @Override
    public String toString() {
        return "RoleResourceRelation{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", roleId=" + roleId +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", createdBy='" + createdBy + '\'' +
                ", updatedby='" + updatedby + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }
}
