package org.workpal.Services;

import org.workpal.Models.Subscription;
import org.workpal.Repositories.RepositoryInterfaces.SubscriptionRepositoryInterface;
import org.workpal.Services.ServicesInterfaces.SubscriptionServiceInterface;

import java.util.List;
import java.util.Optional;

public class SubscriptionService implements SubscriptionServiceInterface {
    private final SubscriptionRepositoryInterface repository;

    public SubscriptionService(SubscriptionRepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public void save(Subscription subscription) {
        repository.save(subscription);
    }

    @Override
    public void update(Subscription subscription) {
        repository.update(subscription);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Optional<Subscription> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Subscription> findAll() {
        return repository.findAll();
    }
}
