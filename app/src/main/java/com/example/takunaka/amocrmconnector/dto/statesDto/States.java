
package com.example.takunaka.amocrmconnector.dto.statesDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class States {

    @SerializedName("statesResponse")
    @Expose
    private StatesResponse statesResponse;

    public StatesResponse getStatesResponse() {
        return statesResponse;
    }


}
