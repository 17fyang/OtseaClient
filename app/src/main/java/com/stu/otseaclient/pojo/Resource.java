package com.stu.otseaclient.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/6 14:59
 * @Description:
 */
public class Resource implements Serializable {
    private static final long serialVersionUID = -86805436642257825L;
    /**
     * 资源id，自增主键
     */
    private Integer resourceId;
    /**
     * 资源路径
     */
    private String resourcePath;
    /**
     * 资源类型，0：图片，10，视频
     */
    private Short resourceType;
    /**
     * 扩展字段，json格式
     */
    private String information;
    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Short getResourceType() {
        return resourceType;
    }

    public void setResourceType(Short resourceType) {
        this.resourceType = resourceType;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}