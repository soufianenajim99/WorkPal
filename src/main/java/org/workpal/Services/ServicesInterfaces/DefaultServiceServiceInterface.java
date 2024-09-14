package org.workpal.Services.ServicesInterfaces;

import org.workpal.Models.DefaultService;

import java.util.List;
import java.util.Optional;

public interface DefaultServiceServiceInterface extends ServiceServiceInterface{
    Optional<DefaultService> findById(int id);
    List<DefaultService> findAll();
}
