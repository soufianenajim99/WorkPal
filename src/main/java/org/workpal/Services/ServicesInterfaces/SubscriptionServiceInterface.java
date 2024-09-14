package org.workpal.Services.ServicesInterfaces;

import org.workpal.Models.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionServiceInterface {
    void save(Subscription subscription);
    void update(Subscription subscription);
    void delete(int id);
    Optional<Subscription> findById(int id);
    List<Subscription> findAll();
}
