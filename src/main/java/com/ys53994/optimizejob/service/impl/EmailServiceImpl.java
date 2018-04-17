package com.ys53994.optimizejob.service.impl;

import com.ys53994.optimizejob.domain.Publisher;
import com.ys53994.optimizejob.service.EmailService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Async
    public void notifyPublisher(Publisher publisherId){
        //
    }
}
