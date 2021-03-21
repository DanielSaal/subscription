package com.dsa.subscription.converter;

import com.dsa.subscription.entity.EventHistory;
import com.dsa.subscription.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class EventHistoryConverter {

    public EventHistory toEntity(Subscription subscription) {

        return EventHistory.builder()
                .subscription(subscription)
                .type(subscription.getStatus().getName())
                .build();
    }
}
