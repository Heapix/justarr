package com.heapix.justarr.service;

import com.heapix.justarr.controller.bo.CreateResponseBo;
import com.heapix.justarr.controller.bo.EventInfoBo;
import com.heapix.justarr.controller.bo.UpdateResponseBo;
import com.heapix.justarr.controller.converter.EventConverter;
import com.heapix.justarr.persistence.model.Event;
import com.heapix.justarr.persistence.repository.EventRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mgergalov
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventConverter eventConverter;

    @Override
    public EventInfoBo getEventInfo(Long id) throws NotFoundException {
        Event response = eventRepository.getOne(id);
        return eventConverter.toInfoBo(response);
    }

    @Override
    public List<EventInfoBo> getAll() {
        List<Event> events = eventRepository.findAll();
        return eventConverter.toDtos(events);
    }

    @Override
    public List<EventInfoBo> getByRole(Long role) {
        List<Event> events = eventRepository.findByRoleGreaterThanEqual(role);
        return eventConverter.toDtos(events);
    }

    @Override
    public UpdateResponseBo updateEvent(Event event, Long id) {
        Event entity = eventRepository.getOne(id);
        entity.setDate(event.getDate());
        entity.setLocation(event.getLocation());
        entity.setInfo(event.getInfo());
        entity.setMaxNumberOfRegistrations(event.getMaxNumberOfRegistrations());
        entity.setMinNumberOfRegistrations(event.getMinNumberOfRegistrations());
        return new UpdateResponseBo(eventRepository.save(entity).getId());
    }

    @Override
    public CreateResponseBo createEvent(Event event) {
        return new CreateResponseBo(eventRepository.save(event).getId());
    }


    @Override
    public void remove(Long id) {
        eventRepository.deleteById(id);
    }
}
