package com.dsa.subscription.stream.producer;

import com.dsa.subscription.exception.InfrastructureException;
import com.dsa.subscription.stream.SubscriptionChannel;
import com.dsa.subscription.stream.dto.SubscriptionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
@EnableBinding(SubscriptionChannel.class)
public class SubscriptionProducer {

    private final SubscriptionChannel channel;

    public void send(SubscriptionDTO subscriptionDTO) {

        try {
            channel
                    .sendSubscription()
                    .send(buildMessage(subscriptionDTO));
        } catch (Exception e) {
            log.error("Erro ao enviar mensagem para messageria.", e);
            throw new InfrastructureException("Erro ao enviar mensagem para messageria.", e);
        }
    }

    private Message<?> buildMessage(SubscriptionDTO subscriptionDTO) {

        return MessageBuilder.withPayload(subscriptionDTO).build();
    }
}
