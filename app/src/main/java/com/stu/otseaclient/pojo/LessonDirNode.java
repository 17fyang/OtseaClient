package com.stu.otseaclient.pojo;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/10 18:41
 * @Description:
 */
public class LessonDirNode {
    private String name;
    private int link;
    private List<LessonDirNode> son;

    public LessonDirNode() {
    
    }

    public LessonDirNode(String nodeName, int link) {
        this.name = nodeName;
        this.link = link;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public List<LessonDirNode> getSon() {
        return son;
    }

    public void setSon(List<LessonDirNode> son) {
        this.son = son;
    }
}
