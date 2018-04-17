package com.ys53994.optimizejob.service;


import com.ys53994.optimizejob.domain.Publisher;

public interface EmailService {

    void notifyPublisher(Publisher publisherId);

}
