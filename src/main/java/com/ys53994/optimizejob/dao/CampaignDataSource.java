package com.ys53994.optimizejob.dao;


import com.ys53994.optimizejob.domain.Campaign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CampaignDataSource {

    public ResultCursor<Campaign> getCampaigns() {
        return new ResultCursor<>();
    }
}
