package com.dsa.subscription.converter;

import com.dsa.subscription.entity.Subscription;
import com.dsa.subscription.stream.dto.SubscriptionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.dsa.subscription.util.MockUtils.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionConverterTest {

    @InjectMocks
    SubscriptionConverter converter;

    @Test
    public void whenValidRequestSubscriptionDTO_thenReturnSubscriptionDTO() {

        SubscriptionDTO subscriptionDTOConverted = converter.toDTO(buildRequestSubscriptionDTO());

        Assertions.assertEquals(buildSubscriptionDTO(), subscriptionDTOConverted);
    }

    @Test
    public void whenValidSubscriptionDTO_thenReturnSubscriptionEntity() {

        Subscription subscriptionConverted = converter.toEntity(SUBSCRIPTION_ID, buildStatus());

        Assertions.assertEquals(buildSubscription(), subscriptionConverted);
    }
}
