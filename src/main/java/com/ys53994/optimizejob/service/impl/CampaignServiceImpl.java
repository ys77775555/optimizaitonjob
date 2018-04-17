package com.ys53994.optimizejob.service.impl;

import com.ys53994.optimizejob.domain.Campaign;
import com.ys53994.optimizejob.domain.Publisher;
import com.ys53994.optimizejob.service.CampaignService;
import com.ys53994.optimizejob.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final EmailService emailService;

    @Autowired
    public CampaignServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void blacklistPublisher(Campaign campaign, Publisher publisher) {
        campaign.getBlacklistedPublishers().putIfAbsent(publisher.getId(),new Campaign.BlackList());
        emailService.notifyPublisher(publisher);
    }

    @Override
    public void removePublisherFromBlackList(Campaign campaign, Publisher publisher) {
        campaign.getBlacklistedPublishers().remove(publisher.getId());
        emailService.notifyPublisher(publisher);
    }

    @Override
    public Optional<Campaign> findById(int campaignId) {
        return Optional.empty();
    }
}
