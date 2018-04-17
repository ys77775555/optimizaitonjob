package com.ys53994.optimizejob.domain;


public class Event {

    private final EventType type;

    private final int campaignId;

    private final int publisherId;

    public Event(EventType type, int compaignId, int publisherId) {
        this.type = type;
        this.campaignId = compaignId;
        this.publisherId = publisherId;
    }

    public EventType getType() {
        return type;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public int getPublisherId() {
        return publisherId;
    }
}
