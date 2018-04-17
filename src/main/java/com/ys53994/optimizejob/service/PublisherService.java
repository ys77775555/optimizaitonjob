package com.ys53994.optimizejob.service;

import com.ys53994.optimizejob.domain.Publisher;

import java.util.Optional;

public interface PublisherService {

    Optional<Publisher> findById(int pk);
}
