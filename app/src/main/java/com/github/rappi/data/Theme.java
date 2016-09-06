package com.github.rappi.data;

import com.github.rappi.data.entity.Data;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Roger Patiño on 05/09/2016.
 */

public class Theme {

    @SerializedName("kind")
    private String kind;
    @SerializedName("data")
    private Data data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}