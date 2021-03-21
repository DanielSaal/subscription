package com.dsa.subscription.util;

import com.dsa.subscription.controller.dto.RequestSubscriptionDTO;
import com.dsa.subscription.entity.EventHistory;
import com.dsa.subscription.entity.Status;
import com.dsa.subscription.entity.Subscription;
import com.dsa.subscription.stream.dto.SubscriptionDTO;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class MockUtils {

    public static final String SUBSCRIPTION_ID = "5793cf6b3fd833521db8c420955e6f01";
    public static final Integer STATUS_ID = 1;
    public static final String STATUS_NAME = "SUBSCRIPTION_PURCHASED";
    public static final Integer NEW_STATUS_ID = 2;
    public static final String NEW_STATUS_NAME = "SUBSCRIPTION_CANCELED";

    public static Subscription buildSubscription() {

        return Subscription.builder()
                .id(SUBSCRIPTION_ID)
                .status(buildStatus())
                .build();
    }

    public static Status buildStatus() {

        return Status.builder()
                .id(STATUS_ID)
                .name(STATUS_NAME)
                .build();
    }

    public static Status buildNewStatus() {

        return Status.builder()
                .id(NEW_STATUS_ID)
                .name(NEW_STATUS_NAME)
                .build();
    }


    public static EventHistory buildEventHistory() {

        return EventHistory.builder()
                .subscription(buildSubscription())
                .type(STATUS_NAME)
                .build();
    }

    public static RequestSubscriptionDTO buildRequestSubscriptionDTO() {

        return RequestSubscriptionDTO.builder()
                .notificationType(STATUS_NAME)
                .subscription(SUBSCRIPTION_ID)
                .build();
    }

    public static SubscriptionDTO buildSubscriptionDTO() {

        return SubscriptionDTO.builder()
                .notificationType(STATUS_NAME)
                .subscription(SUBSCRIPTION_ID)
                .build();
    }
}
