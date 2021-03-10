package com.stu.otseaclient.pojo;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/2/25 0:07
 * @Description:
 */
public class PostInfo {
    private Post post;
    private Resource resource;
    private UserInfo authorInfo;


    @Override
    public String toString() {
        return "PostInfo{" +
                "post=" + post +
                ", resource=" + resource +
                ", authorInfo=" + authorInfo +
                '}';
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public UserInfo getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(UserInfo authorInfo) {
        this.authorInfo = authorInfo;
    }
}