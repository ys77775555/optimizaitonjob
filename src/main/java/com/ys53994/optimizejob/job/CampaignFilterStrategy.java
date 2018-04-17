package com.ys53994.optimizejob.job;


import com.ys53994.optimizejob.domain.Campaign;
import com.ys53994.optimizejob.domain.EventType;

import java.util.function.Predicate;

public class CampaignFilterStrategy implements Predicate<Campaign> {

    private Predicate<Campaign> eventTypeCheck = campaign -> campaign.getOptProps().getSourceEvent() == EventType.install
             && campaign.getOptProps().getMeasuredEvent() == EventType.purchase;

    private Predicate<Campaign> thresHoldCheck = campaign -> true;
    @Override
    public boolean test(Campaign campaign) {
       return eventTypeCheck.and(thresHoldCheck).test(campaign);
    }
}
