package com.dsa.subscription.stream.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDTO {

    @NotNull
    private String notificationType;

    @NotNull
    private String subscription;
}
