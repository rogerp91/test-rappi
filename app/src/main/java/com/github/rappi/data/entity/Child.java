package com.github.rappi.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Roger Patiño on 05/09/2016.
 */

public class Child {

    @SerializedName("kind")
    private String kind;
    @SerializedName("data")
    private Data_ data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }

}