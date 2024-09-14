package org.workpal.Repositories.RepositoryInterfaces;

import org.workpal.Models.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceRepositoryInterface {
    void save(Service service);
    void update(Service service);
    void delete(int id);
    Optional<Service> findById(int id);
    List<Service> findAll();
}
