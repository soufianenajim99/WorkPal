package org.workpal.Repositories.RepositoryInterfaces;

import org.workpal.Models.Espace;

import java.util.List;
import java.util.Optional;

public interface EspaceRepositoryInterface {
    void save(Espace espace);
    Optional<Espace> findById(int id);
    List<Espace> findAll();
    void update(Espace espace);
    void delete(int id);
}
