package org.workpal.Services;

import org.workpal.Models.Event;
import org.workpal.Repositories.EventRepository;
import org.workpal.Services.ServicesInterfaces.EventServiceInterface;

import java.util.List;
import java.util.Optional;

public class EventService implements EventServiceInterface {
    private final EventRepository eventRepository;

    public EventService() {
        this.eventRepository = new EventRepository();
    }

    @Override
    public Event register(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(int id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public void update(Event event) {
        eventRepository.update(event);
    }

    @Override
    public void delete(int id) {
        eventRepository.delete(id);
    }
}
