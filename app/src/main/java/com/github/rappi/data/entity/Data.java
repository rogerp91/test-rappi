package com.github.rappi.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roger Patiño on 05/09/2016.
 */

public class Data {

    @SerializedName("modhash")
    private String modhash;
    @SerializedName("children")
    private List<Child> children = new ArrayList<>();

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

}