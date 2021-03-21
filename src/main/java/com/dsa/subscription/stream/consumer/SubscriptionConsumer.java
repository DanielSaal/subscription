package com.dsa.subscription.stream.consumer;

import com.dsa.subscription.service.SubscriptionService;
import com.dsa.subscription.stream.SubscriptionChannel;
import com.dsa.subscription.stream.dto.SubscriptionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
@EnableBinding(SubscriptionChannel.class)
public class SubscriptionConsumer {

    private final SubscriptionService subscriptionService;

    @StreamListener(SubscriptionChannel.SUBSCRIPTIONS_IN)
    public void receive(Message<SubscriptionDTO> subscriptionDTOMessage) {

        log.info("Mensagem consumida: {}", subscriptionDTOMessage.getPayload());
        subscriptionService.save(subscriptionDTOMessage.getPayload());
    }
}
