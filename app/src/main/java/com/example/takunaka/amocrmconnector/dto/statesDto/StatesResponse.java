
package com.example.takunaka.amocrmconnector.dto.statesDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatesResponse {

    @SerializedName("account")
    @Expose
    private Account account;

    public Account getAccount() {
        return account;
    }

}
