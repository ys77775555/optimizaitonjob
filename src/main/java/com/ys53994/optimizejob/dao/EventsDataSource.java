package com.ys53994.optimizejob.dao;

import com.ys53994.optimizejob.domain.Event;

public interface EventsDataSource {

    ResultCursor<Event> getEventsSince(int i);

}
