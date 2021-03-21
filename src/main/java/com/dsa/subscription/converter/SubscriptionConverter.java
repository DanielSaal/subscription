package com.dsa.subscription.converter;

import com.dsa.subscription.controller.dto.RequestSubscriptionDTO;
import com.dsa.subscription.entity.Status;
import com.dsa.subscription.entity.Subscription;
import com.dsa.subscription.exception.BusinessException;
import com.dsa.subscription.stream.dto.SubscriptionDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SubscriptionConverter {

    public SubscriptionDTO toDTO(RequestSubscriptionDTO request) {

        return SubscriptionDTO.builder()
                .notificationType(request.getNotificationType())
                .subscription(request.getSubscription())
                .build();
    }

    public Subscription toEntity(String id, Status status) {

        return Subscription.builder()
                .id(id)
                .status(status)
                .build();
    }
}
