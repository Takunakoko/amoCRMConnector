package com.example.takunaka.amocrmconnector.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.takunaka.amocrmconnector.R;
import com.example.takunaka.amocrmconnector.presenter.MainPresenter;


public class MainActivity extends AppCompatActivity{

    private MainPresenter mMainPresenter;
    public SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.getData();
        sPref = getPreferences(MODE_PRIVATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.saveBackup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.loadBackup();
    }

}
