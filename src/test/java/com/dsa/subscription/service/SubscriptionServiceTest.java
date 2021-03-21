package com.dsa.subscription.service;

import com.dsa.subscription.converter.SubscriptionConverter;
import com.dsa.subscription.exception.BusinessException;
import com.dsa.subscription.repository.SubscriptionRepository;
import com.dsa.subscription.stream.producer.SubscriptionProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.dsa.subscription.util.MockUtils.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest {

    @InjectMocks
    SubscriptionService service;

    @Mock
    SubscriptionProducer producer;

    @Mock
    SubscriptionRepository repository;

    @Mock
    SubscriptionConverter converter;

    @Mock
    StatusService statusService;

    @Mock
    EventHistoryService eventHistoryService;

    @Test
    public void whenNewSubscription_thenSaveSubscriptionWithSuccess() {

        Mockito.<Optional<Object>>when(Optional.of(repository.findById(SUBSCRIPTION_ID)))
                .thenReturn(Optional.empty());
        Mockito.when(statusService.save(STATUS_NAME))
                .thenReturn(buildStatus());
        Mockito.when(converter.toEntity(SUBSCRIPTION_ID, buildStatus()))
                .thenReturn(buildSubscription());
        Mockito.when(repository.save(buildSubscription()))
                .thenReturn(buildSubscription());
        Mockito.doNothing().when(eventHistoryService).save(buildSubscription());

        service.save(buildSubscriptionDTO());

        Mockito.verify(statusService, Mockito.times(1)).save(STATUS_NAME);
        Mockito.verify(repository, Mockito.times(1)).findById(SUBSCRIPTION_ID);
        Mockito.verify(repository, Mockito.times(1)).save(buildSubscription());
        Mockito.verify(eventHistoryService, Mockito.times(1)).save(buildSubscription());
    }

    @Test
    public void whenExistingSubscription_thenUpdateSubscription() {

        Mockito.<Optional<Object>>when(Optional.of(repository.findById(SUBSCRIPTION_ID)))
                .thenReturn(Optional.of(buildSubscription()));
        Mockito.when(statusService.save(STATUS_NAME))
                .thenReturn(buildStatus());
        Mockito.when(repository.save(buildSubscription()))
                .thenReturn(buildSubscription());
        Mockito.doNothing().when(eventHistoryService).save(buildSubscription());

        service.save(buildSubscriptionDTO());

        Mockito.verify(statusService, Mockito.times(1)).save(STATUS_NAME);
        Mockito.verify(repository, Mockito.times(1)).findById(SUBSCRIPTION_ID);
        Mockito.verify(repository, Mockito.times(1)).save(buildSubscription());
        Mockito.verify(eventHistoryService, Mockito.times(1)).save(buildSubscription());
    }

    @Test
    public void whenExceptionOccur_thenThrowBusinessException() {

        Mockito.<Optional<Object>>when(Optional.of(repository.findById(SUBSCRIPTION_ID)))
                .thenReturn(Optional.of(buildSubscription()));
        Mockito.when(statusService.save(STATUS_NAME))
                .thenThrow(NullPointerException.class);

        Assertions.assertThrows(BusinessException.class, () -> {
            service.save(buildSubscriptionDTO());
        });

        Mockito.verify(statusService, Mockito.times(1)).save(STATUS_NAME);
        Mockito.verify(repository, Mockito.times(1)).findById(SUBSCRIPTION_ID);
        Mockito.verify(repository, Mockito.times(0)).save(buildSubscription());
        Mockito.verify(eventHistoryService, Mockito.times(0)).save(buildSubscription());
    }

    @Test
    public void whenValidRequestSubscriptionDTO_thenSend() {

        Mockito.when(converter.toDTO(buildRequestSubscriptionDTO()))
                .thenReturn(buildSubscriptionDTO());
        Mockito.doNothing().when(producer).send(buildSubscriptionDTO());

        service.send(buildRequestSubscriptionDTO());

        Mockito.verify(producer, Mockito.times(1)).send(any());
    }
}
