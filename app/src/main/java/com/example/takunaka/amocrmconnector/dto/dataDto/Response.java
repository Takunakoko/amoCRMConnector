
package com.example.takunaka.amocrmconnector.dto.dataDto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("leads")
    @Expose
    private List<Lead> leads = null;

    public List<Lead> getLeads() {
        return leads;
    }


}
