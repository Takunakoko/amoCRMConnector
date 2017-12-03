package com.example.takunaka.amocrmconnector.api;

import com.example.takunaka.amocrmconnector.utils.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by takunaka on 02.12.17.
 */

public class AmoCRMClient {
    private static Retrofit getRetrofitInstance() {
        //возврат клента ретрофита для запроса в гугл
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static AmoCRMApi getApi() {
        return getRetrofitInstance().create(AmoCRMApi.class);
    }

}
