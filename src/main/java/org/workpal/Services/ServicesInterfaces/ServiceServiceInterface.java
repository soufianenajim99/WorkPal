package org.workpal.Services.ServicesInterfaces;

import org.workpal.Models.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceServiceInterface {
    void save(Service service);
    void update(Service service);
    void delete(int id);
    Optional<Service> findById(int id);
    List<Service> findAll();
}
