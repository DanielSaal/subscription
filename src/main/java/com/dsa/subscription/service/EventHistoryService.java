package com.dsa.subscription.service;

import com.dsa.subscription.converter.EventHistoryConverter;
import com.dsa.subscription.entity.Subscription;
import com.dsa.subscription.repository.EventHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EventHistoryService {

    private final EventHistoryRepository repository;
    private final EventHistoryConverter converter;

    public void save(Subscription subscription) {

        repository.save(converter.toEntity(subscription));
    }
}
