package org.workpal.Repositories.RepositoryInterfaces;

import org.workpal.Models.AdditionalService;

import java.util.List;
import java.util.Optional;

public interface AdditionalServiceRepositoryInterface extends ServiceRepositoryInterface{

    Optional<AdditionalService> findById(int id);

    List<AdditionalService> findAll();
}
