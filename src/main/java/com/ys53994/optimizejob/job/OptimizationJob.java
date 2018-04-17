package com.ys53994.optimizejob.job;


import com.ys53994.optimizejob.dao.CampaignDataSource;
import com.ys53994.optimizejob.dao.EventsDataSource;
import com.ys53994.optimizejob.dao.ResultCursor;
import com.ys53994.optimizejob.domain.Campaign;
import com.ys53994.optimizejob.domain.Event;
import com.ys53994.optimizejob.domain.EventType;
import com.ys53994.optimizejob.domain.Publisher;
import com.ys53994.optimizejob.service.CampaignService;
import com.ys53994.optimizejob.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class OptimizationJob {

    private final EventsDataSource eventsDataSource;

    private final CampaignDataSource campaignDataSource;

    private final CampaignService campaignService;

    private final PublisherService publisherService;


    @Autowired
    public OptimizationJob(EventsDataSource eventsDataSource, CampaignDataSource campaignDataSource,
                           CampaignService campaignService,
                           PublisherService publisherService) {
        this.eventsDataSource = eventsDataSource;
        this.campaignDataSource = campaignDataSource;
        this.campaignService = campaignService;
        this.publisherService = publisherService;
    }

    public void run() {
        /** query results from two weeks ago */
        processEvents(eventsDataSource.getEventsSince(3600 * 24 * 14).stream().collect(Collectors.toSet()));

    }

    private void processEvents(Set<Event> events) {
        final AtomicInteger countOfSourceEvents = new AtomicInteger();
        final AtomicInteger countOfMeasurementEvents = new AtomicInteger();

        events.forEach(event -> {
            final Campaign campaign = campaignService.findById(event.getCampaignId()).orElseThrow(RuntimeException::new);

            if (campaign.getOptProps().getSourceEvent() == EventType.install) {
                countOfSourceEvents.incrementAndGet();
            }
            if (campaign.getOptProps().getMeasuredEvent() == EventType.purchase) {
                countOfMeasurementEvents.incrementAndGet();
            }
        });

        events.forEach(event -> {
            final Campaign campaign = campaignService.findById(event.getCampaignId()).orElseThrow(RuntimeException::new);
            final Publisher publisher = publisherService.findById(event.getPublisherId()).orElseThrow(RuntimeException::new);

            final int threshold = campaign.getOptProps().getThreshold();
            final float ratioThreshold = campaign.getOptProps().getRatioThreshold();
            final long countOfSourceInstallEvents = publisher.getEvents().stream().filter(event1 -> event.getType() == EventType.install).count();
            /*
             * threshold - the minimum of occurrences of sourceEvent, if a publisher has less sourceEvents that the threshold, then he should not be blacklisted;
             */
            if (countOfSourceInstallEvents > threshold) {
                //blacklisted because publisher has
                campaignService.blacklistPublisher(campaign, publisher);
            }
            float calculatedRation = (countOfMeasurementEvents.floatValue() / countOfSourceEvents.floatValue()) * 100;
            //cross the ratio case
            if (calculatedRation > ratioThreshold) {
                campaignService.removePublisherFromBlackList(campaign, publisher);
            }

        });

    }


}
