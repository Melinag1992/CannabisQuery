package com.example.c4q.cannabisproject.network;

import java.util.List;

/**
 * Created by c4q on 1/23/18.
 */

public class Strain {
    private List<Strain> allstrains;
    private String id;
    private String name;
    private String race;
    private String desc;

    public List<Strain> getAllstrains() {
        return allstrains;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public String getDesc() {
        return desc;
    }
}
