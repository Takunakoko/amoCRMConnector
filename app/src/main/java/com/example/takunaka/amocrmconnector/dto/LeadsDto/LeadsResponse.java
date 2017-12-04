
package com.example.takunaka.amocrmconnector.dto.LeadsDto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeadsResponse {

    @SerializedName("leads")
    @Expose
    private List<Lead> leads = null;

    public List<Lead> getLeads() {
        return leads;
    }


}
