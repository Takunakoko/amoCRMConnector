package com.example.takunaka.amocrmconnector.presenter;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.takunaka.amocrmconnector.R;
import com.example.takunaka.amocrmconnector.api.AmoCRMClient;
import com.example.takunaka.amocrmconnector.dto.Data;
import com.example.takunaka.amocrmconnector.dto.dataDto.DataDto;
import com.example.takunaka.amocrmconnector.dto.dataDto.Response;
import com.example.takunaka.amocrmconnector.dto.statesDto.LeadsStatus;
import com.example.takunaka.amocrmconnector.dto.statesDto.States;
import com.example.takunaka.amocrmconnector.utils.Constants;
import com.example.takunaka.amocrmconnector.view.MainActivity;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    MainActivity activity;

    private RecyclerView mRecyclerView;

    private RecyclerViewAdapter adapter;

    private Data data;

    public MainPresenter(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    public void getData() {
        Observable.combineLatest(AmoCRMClient.getApi().getDataAuth(50, Constants.USER_LOGIN, Constants.USER_HASH),
                AmoCRMClient.getApi().getStatesAuth(Constants.USER_LOGIN, Constants.USER_HASH), (DataDto dto, States statesDto) -> {
                    data = new Data(dto.getResponse().getLeads(), statesDto.getResponse().getAccount().getLeadsStatuses());
                    try {
                        Thread.sleep(1500);
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
            mRecyclerView = (RecyclerView) activity.findViewById(R.id.recyclerView);
            adapter = new RecyclerViewAdapter(data.getLeads(), activity, this);
            //инициаизируем адаптер списоком с сортировкой и метками
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(activity);
            mRecyclerView.setLayoutManager(llm);
        }
    }

    public LeadsStatus checkState(String statusId){
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
    }

    public void saveBackup() {
        SharedPreferences.Editor ed = activity.sPref.edit();
        //сериализация класса Data для сохранения в SP
        ed.putString(Constants.SAVED_DTO, new Gson().toJson(data));
        ed.apply();
    }

    public void loadBackup() {
        data = new Gson().fromJson(activity.sPref.getString(Constants.SAVED_DTO, ""), Data.class);
        //проверка получен ли объект obj
        if (data != null) {
            setData(data);
        }
    }

}
