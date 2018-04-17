package com.ys53994.optimizejob;

import com.ys53994.optimizejob.dao.CampaignDataSource;
import com.ys53994.optimizejob.dao.EventsDataSource;
import com.ys53994.optimizejob.dao.ResultCursor;
import com.ys53994.optimizejob.domain.Campaign;
import com.ys53994.optimizejob.domain.Event;
import com.ys53994.optimizejob.domain.EventType;
import com.ys53994.optimizejob.domain.OptimizationProps;
import com.ys53994.optimizejob.domain.Publisher;
import com.ys53994.optimizejob.job.OptimizationJob;
import com.ys53994.optimizejob.service.CampaignService;
import com.ys53994.optimizejob.service.PublisherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class OptimizejobApplicationTests {


    @Mock
    EventsDataSource eventsDataSource;

    @Mock
    CampaignDataSource campaignDataSource;

    @Mock
    CampaignService campaignService;

    @Mock
    PublisherService publisherService;

    @InjectMocks
    OptimizationJob optimizationJob;

    final Campaign campaign1 = new Campaign(1, new OptimizationProps(1, EventType.install, EventType.purchase, 100.0f));

    final Campaign campaign2 = new Campaign(2, new OptimizationProps(1, EventType.install, EventType.purchase, 100.0f));

    final Campaign campaign3 = new Campaign(3, new OptimizationProps(1, EventType.install, EventType.app_open, 55.0f));

    @Before
    public void setUp() {

    }

    @Test
    public void shouldBlacklistedPublishersWhichCrossRation() {
        createMockData();

        Stream.of(campaign1,campaign2, campaign3).forEach(campaign -> assertEquals(campaign.getBlacklistedPublishers().size(),0));
        optimizationJob.run();

        assertTrue(campaign3.getBlacklistedPublishers().size() > 0);


    }

    private void createMockData() {
        final ResultCursor<Event> events = new ResultCursor<>();


        Mockito.when(campaignService.findById(1)).thenReturn(Optional.of(campaign1));
        Mockito.when(campaignService.findById(2)).thenReturn(Optional.of(campaign2));
        Mockito.when(campaignService.findById(3)).thenReturn(Optional.of(campaign3));
        events.add(new Event(EventType.install, 1, 1));
        events.add(new Event(EventType.install, 2, 2));
        events.add(new Event(EventType.install, 3, 3));
        Mockito.when(eventsDataSource.getEventsSince(3600 * 24 * 14)).thenReturn(events);


        final Publisher publisher1 = new Publisher(1);
        final Publisher publisher2 = new Publisher(2);
        final Publisher publisher3 = new Publisher(3);

        Mockito.when(publisherService.findById(1)).thenReturn(Optional.of(publisher1));
        Mockito.when(publisherService.findById(2)).thenReturn(Optional.of(publisher2));
        Mockito.when(publisherService.findById(3)).thenReturn(Optional.of(publisher3));
    }

}
