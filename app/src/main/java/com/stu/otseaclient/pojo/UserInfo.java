package com.stu.otseaclient.pojo;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/6 15:00
 * @Description:
 */
public class UserInfo {
    private String userId;
    private String intro;
    private String headImage;
    private String name;

    public UserInfo() {
    }

    public UserInfo(String userId, String intro, String headImage, String name) {
        this.userId = userId;
        this.intro = intro;
        this.headImage = headImage;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
