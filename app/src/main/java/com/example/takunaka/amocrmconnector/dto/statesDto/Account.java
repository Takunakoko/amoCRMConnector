
package com.example.takunaka.amocrmconnector.dto.statesDto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("leads_statuses")
    @Expose
    private List<LeadsStatus> leadsStatuses = null;

    public List<LeadsStatus> getLeadsStatuses() {
        return leadsStatuses;
    }

}
