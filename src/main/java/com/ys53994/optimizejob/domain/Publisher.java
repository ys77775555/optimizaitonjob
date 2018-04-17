package com.ys53994.optimizejob.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Publisher {

    private final int id;

    private final Set<Event> events = new HashSet<>();

    public Publisher(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id == publisher.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public Set<Event> getEvents() {
        return events;
    }
}
