package com.example.c4q.cannabisproject.model;

import java.io.Serializable;

/**
 * Created by melg on 1/28/18.
 */

public class CannabisStrain {

    private String ocpc;
    private String image;
    private String url;
    private String name;


    public CannabisStrain(){

    }

    public CannabisStrain(String ocpc, String image, String url, String name) {
        this.ocpc = ocpc;
        this.image = image;
        this.url = url;
        this.name = name;
    }

    public String getOcpc() {
        return ocpc;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
