package com.stu.otseaclient.pojo;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/6 17:25
 * @Description:
 */
public class UserBaseInfo {
    private String signature;
    private String headImage;
    private String name;

    @Override
    public String toString() {
        return "UserBaseInfo{" +
                "signature='" + signature + '\'' +
                ", headImage='" + headImage + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
