package com.dsa.subscription.repository;

import com.dsa.subscription.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer> {

    Status findByName(String name);
}
