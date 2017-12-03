
package com.example.takunaka.amocrmconnector.dto.statesDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class States {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }


}
