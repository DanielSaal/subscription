package com.dsa.subscription.service;

import com.dsa.subscription.entity.Status;
import com.dsa.subscription.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class StatusService {

    private final StatusRepository repository;

    public Status save(String name) {

        Status status = repository.findByName(name);

        return Objects.nonNull(status) ? status : repository.save(new Status(null, name));
    }
}
