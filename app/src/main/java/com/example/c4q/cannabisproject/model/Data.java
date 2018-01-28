package com.example.c4q.cannabisproject.model;

import java.io.Serializable;

/**
 * Created by melg on 1/28/18.
 */

public class Data {
    private String updatedAt;
    private String createdAt;
    private String image;
    private String url;
    private String qr;
    private String ocpc;
    private String name;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getOcpc() {
        return ocpc;
    }

    public void setOcpc(String ocpc) {
        this.ocpc = ocpc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
