package com.dsa.subscription.converter;

import com.dsa.subscription.entity.EventHistory;
import com.dsa.subscription.util.MockUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.dsa.subscription.util.MockUtils.buildEventHistory;

@ExtendWith(MockitoExtension.class)
public class EventHistoryConverterTest {

    @InjectMocks
    EventHistoryConverter converter;

    @Test
    public void whenValidSubscription_thenReturnEventHistoryEntity() {

        EventHistory eventHistoryConverted = converter.toEntity(MockUtils.buildSubscription());

        Assertions.assertEquals(buildEventHistory(), eventHistoryConverted);
    }
}
