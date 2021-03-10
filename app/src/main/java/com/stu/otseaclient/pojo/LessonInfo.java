package com.stu.otseaclient.pojo;


/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/1/3 15:07
 * @Description: 前端展示的一个课程的vo
 */
public class LessonInfo {
    private Lesson lesson;
    private Resource titleImage;
    private UserInfo authorInfo;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Resource getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(Resource titleImage) {
        this.titleImage = titleImage;
    }

    public UserInfo getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(UserInfo authorInfo) {
        this.authorInfo = authorInfo;
    }
}
