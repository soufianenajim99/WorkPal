package org.workpal.Services;

import org.workpal.Models.Service;
import org.workpal.Repositories.RepositoryInterfaces.ServiceRepositoryInterface;
import org.workpal.Services.ServicesInterfaces.ServiceServiceInterface;

import java.util.List;
import java.util.Optional;

public class ServiceService implements ServiceServiceInterface {
    private final ServiceRepositoryInterface serviceRepo;

    public ServiceService(ServiceRepositoryInterface serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    @Override
    public void save(Service service) {
        serviceRepo.save(service);
    }

    @Override
    public void update(Service service) {
        serviceRepo.update(service);
    }

    @Override
    public void delete(int id) {
        serviceRepo.delete(id);
    }


}
