package org.workpal.Services;

import org.workpal.Services.ServicesInterfaces.EspaceServiceInterface;
import org.workpal.Models.Espace;
import org.workpal.Repositories.EspaceRepository;
import org.workpal.Repositories.RepositoryInterfaces.EspaceRepositoryInterface;

import java.util.List;
import java.util.Optional;
public class EspaceService implements EspaceServiceInterface {
    private final EspaceRepositoryInterface espaceRepository;

    public EspaceService() {
        this.espaceRepository = new EspaceRepository();
    }

    @Override
    public Espace register(Espace espace) {
        return espaceRepository.save(espace);
    }

    @Override
    public Optional<Espace> findById(int id) {
        return espaceRepository.findById(id);
    }

    @Override
    public List<Espace> findAll() {
        return espaceRepository.findAll();
    }

    @Override
    public void update(Espace espace) {
        espaceRepository.update(espace);
    }

    @Override
    public void delete(int id) {
        espaceRepository.delete(id);
    }
}
