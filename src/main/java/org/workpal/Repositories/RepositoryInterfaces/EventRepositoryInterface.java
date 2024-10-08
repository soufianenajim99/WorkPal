package org.workpal.Repositories.RepositoryInterfaces;
import org.workpal.Models.Event;

import java.util.List;
import java.util.Optional;
public interface EventRepositoryInterface {
    Event save(Event event);
    Optional<Event> findById(int id);
    List<Event> findAll();
    void update(Event event);
    void delete(int id);
}
