package org.workpal.Services.ServicesInterfaces;

import org.workpal.Models.Event;

import java.util.List;
import java.util.Optional;

public interface EventServiceInterface {
    Event register(Event event);
    Optional<Event> findById(int id);
    List<Event> findAll();
    void update(Event event);
    void delete(int id);
}
