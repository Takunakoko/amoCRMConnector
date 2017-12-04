
package com.example.takunaka.amocrmconnector.dto.LeadsDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lead {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date_create")
    @Expose
    private Integer dateCreate;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("status_id")
    @Expose
    private String statusId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDateCreate() {
        return dateCreate;
    }

    public String getPrice() {
        return price;
    }

    public String getStatusId() {
        return statusId;
    }


}
