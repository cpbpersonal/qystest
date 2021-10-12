package com.qys.server.entity;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
/*
* 文件大小
* 文件类型
* 原始文件名
* 创建时间
* 文件保存目录
* */

public class UploadFile {
    /*文件大小*/
    private double size;
    /*文件类型*/
    private String type;
    /*原始文件名*/
    private String oldName;
    /*创建时间*/
    private Date time;
    /*文件保存目录*/
    private String url;
    /*文件UUID*/
    private String uuidName;
    /*文件ID*/
    private int id;

    public UploadFile(double size, String type, String oldName, Date time, String url, String uuidName) {
        this.size = size;
        this.type = type;
        this.oldName = oldName;
        this.time = time;
        this.url = url;
        this.uuidName = uuidName;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUuidName() {
        return uuidName;
    }

    public void setUuidName(String uuidName) {
        this.uuidName = uuidName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "size=" + size +
                ", oldName='" + oldName + '\'' +
                ", uuidName='" + uuidName + '\'' +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", time=" + time +
                ", url='" + url + '\'' +
                '}';
    }
}
