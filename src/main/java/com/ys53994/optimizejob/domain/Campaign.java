package com.ys53994.optimizejob.domain;


import java.util.HashMap;
import java.util.Map;

public class Campaign {

    private OptimizationProps optProps;

    private int id;

    private Map<Integer, BlackList> blacklistedPublishers = new HashMap<>();

    public Campaign(int id, OptimizationProps optProps) {
        this.optProps = optProps;
        this.id = id;
        this.blacklistedPublishers = new HashMap<>();
    }

    public void saveBlackList(BlackList blackList) {
        //
    }


    public static final class BlackList {
        //
    }

    public OptimizationProps getOptProps() {
        return optProps;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, BlackList> getBlacklistedPublishers() {
        return blacklistedPublishers;
    }
}
