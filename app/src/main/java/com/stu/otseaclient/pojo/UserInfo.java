package com.stu.otseaclient.pojo;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/6 15:00
 * @Description:
 */
public class UserInfo {
    private String userId;
    private UserBaseInfo userBaseInfo;


    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userBaseInfo=" + userBaseInfo +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }
}
