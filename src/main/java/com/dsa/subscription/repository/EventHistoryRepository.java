package com.dsa.subscription.repository;

import com.dsa.subscription.entity.EventHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventHistoryRepository extends CrudRepository<EventHistory, Integer> {
}
