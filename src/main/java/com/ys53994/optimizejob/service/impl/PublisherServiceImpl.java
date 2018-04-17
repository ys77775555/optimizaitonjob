package com.ys53994.optimizejob.service.impl;

import com.ys53994.optimizejob.domain.Publisher;
import com.ys53994.optimizejob.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Override
    public Optional<Publisher> findById(int pk) {
        return Optional.empty();
    }
}
