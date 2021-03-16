package com.stu.otseaclient.pojo;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/16 22:17
 * @Description:
 */
public class LoginPassVo {
    private String token;
    private UserInfo userInfoVo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfoVo() {
        return userInfoVo;
    }

    public void setUserInfoVo(UserInfo userInfoVo) {
        this.userInfoVo = userInfoVo;
    }
}
