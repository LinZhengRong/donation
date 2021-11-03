package com.donation.demo.model;

public class Image {

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    private Integer errno;
    private String[] data;
    public static Image success(String path) {
        Image image = new Image();
        image.setErrno(0);
        image.setData(new String[]{path});
        return image;
    }

    public static Image error() {
        Image image = new Image();
        //非0代表失败
        image.setErrno(1);
        return image;
    }
}
