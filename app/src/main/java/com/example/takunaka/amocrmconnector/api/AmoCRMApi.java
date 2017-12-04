package com.example.takunaka.amocrmconnector.api;

import android.support.annotation.NonNull;

import com.example.takunaka.amocrmconnector.dto.LeadsDto.DataDto;
import com.example.takunaka.amocrmconnector.dto.statesDto.States;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AmoCRMApi {
    @NonNull
    @GET("/private/api/v2/json/leads/list")
    Observable<DataDto> getDataAuth(@Query("limit_rows") int limit, @Query("USER_LOGIN") String login, @Query("USER_HASH") String hash);

    @NonNull
    @GET("/private/api/v2/json/accounts/current?leads_statuses")
    Observable<States> getStatesAuth(@Query("USER_LOGIN") String login, @Query("USER_HASH") String hash);

}
