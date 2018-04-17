package com.ys53994.optimizejob.domain;


public class OptimizationProps {

    private final int threshold;

    private final EventType sourceEvent;

    private final EventType measuredEvent;

    private final float ratioThreshold;

    public OptimizationProps(int threshold, EventType stringsourceEvent, EventType measuredEvent, float ratioThreshold) {
        this.threshold = threshold;
        this.sourceEvent = stringsourceEvent;
        this.measuredEvent = measuredEvent;
        this.ratioThreshold = ratioThreshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public EventType getSourceEvent() {
        return sourceEvent;
    }

    public EventType getMeasuredEvent() {
        return measuredEvent;
    }

    public float getRatioThreshold() {
        return ratioThreshold;
    }
}
