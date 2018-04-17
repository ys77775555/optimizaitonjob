package com.ys53994.optimizejob.dao;

import com.ys53994.optimizejob.domain.Event;
import org.springframework.stereotype.Component;

@Component
public class EventsDataSourceImpl implements EventsDataSource {

    public ResultCursor<Event> getEventsSince(int i) {
        return new ResultCursor();
    }
}
