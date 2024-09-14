package org.workpal.Services.ServicesInterfaces;

import org.workpal.Models.Espace;

import java.util.List;
import java.util.Optional;

public interface EspaceServiceInterface {
    Espace register(Espace espace);
    Optional<Espace> findById(int id);
    List<Espace> findAll();
    void update(Espace espace);
    void delete(int id);
}
