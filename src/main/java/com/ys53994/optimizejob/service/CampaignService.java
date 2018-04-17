package com.ys53994.optimizejob.service;


import com.ys53994.optimizejob.domain.Campaign;
import com.ys53994.optimizejob.domain.Publisher;

import java.util.Optional;

public interface CampaignService {

    void blacklistPublisher(Campaign campaign, Publisher publisherId);

    void removePublisherFromBlackList(Campaign campaign, Publisher publisherId);


    Optional<Campaign> findById(int campaignId);

}
