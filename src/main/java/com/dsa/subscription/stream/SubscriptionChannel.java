package com.dsa.subscription.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SubscriptionChannel {

    String SUBSCRIPTIONS_IN = "subscriptions_in";
    String SUBSCRIPTIONS_OUT = "subscriptions_out";

    @Output(SUBSCRIPTIONS_OUT)
    MessageChannel sendSubscription();

    @Input(SUBSCRIPTIONS_IN)
    MessageChannel receiveSubscription();
}
