package com.example.c4q.cannabisproject.model;

import java.util.List;

/**
 * Created by melg on 1/28/18.
 */

public class ListOfStrains {
    List<Data> data;
    public Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }








    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}
