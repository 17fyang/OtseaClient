package com.stu.otseaclient.pojo;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/22 14:39
 * @Description:
 */
public class LessonInfo {
    private String imageUrl;
    private String lessonTitle;
    private String authorName;

    public LessonInfo(String imageUrl, String lessonTitle, String authorName) {
        this.imageUrl = imageUrl;
        this.lessonTitle = lessonTitle;
        this.authorName = authorName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
