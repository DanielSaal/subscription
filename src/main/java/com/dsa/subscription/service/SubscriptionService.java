package com.dsa.subscription.service;

import com.dsa.subscription.controller.dto.RequestSubscriptionDTO;
import com.dsa.subscription.converter.SubscriptionConverter;
import com.dsa.subscription.entity.Subscription;
import com.dsa.subscription.exception.BusinessException;
import com.dsa.subscription.repository.SubscriptionRepository;
import com.dsa.subscription.stream.dto.SubscriptionDTO;
import com.dsa.subscription.stream.producer.SubscriptionProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionProducer producer;
    private final SubscriptionRepository repository;
    private final SubscriptionConverter converter;
    private final StatusService statusService;
    private final EventHistoryService eventHistoryService;

    @Transactional
    public void save(SubscriptionDTO subscriptionDTO) {

        try {
            Subscription subscription;
            Optional<Subscription> subscriptionOptional = repository.findById(subscriptionDTO.getSubscription());

            if (subscriptionOptional.isPresent()) {
                subscription = subscriptionOptional.get();
                subscription.setStatus(statusService.save(subscriptionDTO.getNotificationType()));
            } else {
                subscription = converter.toEntity(
                        subscriptionDTO.getSubscription(),
                        statusService.save(subscriptionDTO.getNotificationType())
                );
            }

            subscription = repository.save(subscription);
            eventHistoryService.save(subscription);
        } catch (Exception e) {
            log.error("Erro ao salvar uma assinatura.", e);
            throw new BusinessException("Erro ao salvar uma assinatura.", e);
        }
    }

    public void send(RequestSubscriptionDTO requestSubscriptionDTO) {

        producer.send(converter.toDTO(requestSubscriptionDTO));
    }
}
