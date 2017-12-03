
package com.example.takunaka.amocrmconnector.dto.dataDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDto {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

}
