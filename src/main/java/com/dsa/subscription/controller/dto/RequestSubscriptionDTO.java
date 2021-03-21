package com.dsa.subscription.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestSubscriptionDTO {

    @JsonProperty("notification_type")
    @NotNull
    private String notificationType;

    @JsonProperty("subscription")
    @NotNull
    private String subscription;
}
