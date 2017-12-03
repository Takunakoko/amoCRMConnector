
package com.example.takunaka.amocrmconnector.dto.statesDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeadsStatus {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("color")
    @Expose
    private String color;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }



}
