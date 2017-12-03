package com.example.takunaka.amocrmconnector.presenter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.takunaka.amocrmconnector.R;
import com.example.takunaka.amocrmconnector.dto.dataDto.Lead;
import com.example.takunaka.amocrmconnector.dto.statesDto.LeadsStatus;
import com.example.takunaka.amocrmconnector.utils.DateConverter;
import com.example.takunaka.amocrmconnector.view.MainActivity;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Lead> listLeads;
    private MainActivity activity;
    private MainPresenter presenter;

    public RecyclerViewAdapter(List<Lead> leads, MainActivity activity, MainPresenter mainPresenter) {
        this.listLeads = leads;
        this.activity = activity;
        this.presenter = mainPresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lead currentLead = listLeads.get(position);
        holder.name.setText(currentLead.getName());
        holder.budget.setText(String.format("%s %s", currentLead.getPrice(), activity.getString(R.string.rubles)));
        holder.create_date.setText(DateConverter.getDateFromUts(currentLead.getDateCreate()));
        LeadsStatus leadsStatus = presenter.checkState(currentLead.getStatusId());
        if (leadsStatus != null) {
            holder.state.setText(leadsStatus.getName());
            holder.state.setTextColor(Color.parseColor(leadsStatus.getColor()));
        }
    }

    @Override
    public int getItemCount() {
        return listLeads == null ? 0 : listLeads.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView budget;
        TextView create_date;
        TextView state;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
            budget = (TextView) itemView.findViewById(R.id.item_budget);
            create_date = (TextView) itemView.findViewById(R.id.item_create_date);
            state = (TextView) itemView.findViewById(R.id.item_state);
        }
    }
}

