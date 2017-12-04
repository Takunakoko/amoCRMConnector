package com.example.takunaka.amocrmconnector.dto;

import com.example.takunaka.amocrmconnector.dto.LeadsDto.Lead;
import com.example.takunaka.amocrmconnector.dto.statesDto.LeadsStatus;

import java.util.List;


public class Data {
    private List<Lead> leads;
    private List<LeadsStatus> statuses;

    public Data(List<Lead> leads, List<LeadsStatus> statuses) {
        this.leads = leads;
        this.statuses = statuses;
    }

    public List<Lead> getLeads() {
        return leads;
    }

    public List<LeadsStatus> getStatuses() {
        return statuses;
    }

}
