package org.workpal.Services.ServicesInterfaces;

import org.workpal.Models.AdditionalService;

import java.util.List;
import java.util.Optional;

public interface AdditionalServiceServiceInterface extends ServiceServiceInterface{
    Optional<AdditionalService> findById(int id);
    List<AdditionalService> findAll();
}
