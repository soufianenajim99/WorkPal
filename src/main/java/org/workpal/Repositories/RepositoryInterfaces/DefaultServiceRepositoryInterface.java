package org.workpal.Repositories.RepositoryInterfaces;

import org.workpal.Models.DefaultService;

import java.util.List;
import java.util.Optional;

public interface DefaultServiceRepositoryInterface extends ServiceRepositoryInterface {
    Optional<DefaultService> findById(int id);
    List<DefaultService> findAll();
}
