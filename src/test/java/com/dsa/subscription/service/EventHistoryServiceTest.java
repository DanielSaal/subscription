package com.dsa.subscription.service;

import com.dsa.subscription.converter.EventHistoryConverter;
import com.dsa.subscription.repository.EventHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.dsa.subscription.util.MockUtils.buildEventHistory;
import static com.dsa.subscription.util.MockUtils.buildSubscription;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventHistoryServiceTest {

    @InjectMocks
    EventHistoryService service;

    @Mock
    EventHistoryRepository repository;

    @Spy
    EventHistoryConverter converter;

    @Test
    public void whenValidSubscription_thenSaveEventHistoryWithSuccess() {

        when(repository.save(buildEventHistory()))
                .thenReturn(buildEventHistory());

        service.save(buildSubscription());

        verify(repository, times(1)).save(buildEventHistory());
    }
}
