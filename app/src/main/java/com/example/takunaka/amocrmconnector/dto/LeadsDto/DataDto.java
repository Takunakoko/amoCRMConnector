
package com.example.takunaka.amocrmconnector.dto.LeadsDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDto {

    @SerializedName("leadsResponse")
    @Expose
    private LeadsResponse leadsResponse;

    public LeadsResponse getLeadsResponse() {
        return leadsResponse;
    }

}
