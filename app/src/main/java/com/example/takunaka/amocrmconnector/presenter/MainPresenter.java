package com.example.takunaka.amocrmconnector.presenter;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.takunaka.amocrmconnector.R;
import com.example.takunaka.amocrmconnector.api.AmoCRMClient;
import com.example.takunaka.amocrmconnector.dto.Data;
import com.example.takunaka.amocrmconnector.dto.LeadsDto.DataDto;
import com.example.takunaka.amocrmconnector.dto.statesDto.LeadsStatus;
import com.example.takunaka.amocrmconnector.dto.statesDto.States;
import com.example.takunaka.amocrmconnector.utils.Constants;
import com.example.takunaka.amocrmconnector.view.MainActivity;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private MainActivity activity;

    private Data data;

    public MainPresenter(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    public void getData() {
        Observable.combineLatest(AmoCRMClient.getApi().getDataAuth(50, Constants.USER_LOGIN, Constants.USER_HASH),
                AmoCRMClient.getApi().getStatesAuth(Constants.USER_LOGIN, Constants.USER_HASH), (DataDto dto, States statesDto) -> {
                    data = new Data(dto.getLeadsResponse().getLeads(), statesDto.getStatesResponse().getAccount().getLeadsStatuses());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return data;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::showError);
    }

    private void setData(Data data) {
        Log.d("setData:", " success");
        if (data != null) {
            RecyclerView mRecyclerView = (RecyclerView) activity.findViewById(R.id.recyclerView);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(data.getLeads(), activity, this);
            //инициаизируем адаптер списоком с сортировкой и метками
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(activity);
            mRecyclerView.setLayoutManager(llm);
        }
    }

    LeadsStatus checkState(String statusId){
        List<LeadsStatus> leadsStatuses = data.getStatuses();
        LeadsStatus selectedStatuses = null;
        if (leadsStatuses != null) {
            for (LeadsStatus lead : leadsStatuses) {
                if (lead.getId().toString().equals(statusId)) {
                    selectedStatuses = lead;
                }
            }
        }
        return selectedStatuses;
    }

    private void showError(Throwable throwable) {
        Log.e("error", throwable.getMessage());
        if (throwable instanceof HttpException){
            HttpException exception = (HttpException) throwable;
            new AlertDialog.Builder(new android.view.ContextThemeWrapper(activity, R.style.Theme_AppCompat_Dialog))
                    .setTitle(String.valueOf(exception.code()))
                    .setMessage(exception.message())
                    .setPositiveButton("Ok", null)
                    .show();
        }
    }

    public void saveBackup() {
        SharedPreferences.Editor ed = activity.sPref.edit();
        ed.putString(Constants.SAVED_DTO, new Gson().toJson(data));
        ed.apply();
    }

    public void loadBackup() {
        data = new Gson().fromJson(activity.sPref.getString(Constants.SAVED_DTO, ""), Data.class);
        if (data != null) {
            setData(data);
        }
    }

}
