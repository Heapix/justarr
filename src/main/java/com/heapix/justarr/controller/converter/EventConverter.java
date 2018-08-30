package com.heapix.justarr.controller.converter;

import com.heapix.justarr.controller.bo.EventInfoBo;
import com.heapix.justarr.controller.dto.CreateEventDto;
import com.heapix.justarr.controller.dto.UpdateEventDto;
import com.heapix.justarr.persistence.model.Event;

import java.util.List;

/**
 * @author mgergalov
 */
public interface EventConverter {

    Event toModel(CreateEventDto event);
    Event toModel(UpdateEventDto event);


    EventInfoBo toInfoBo(Event event);


    List<EventInfoBo> toDtos(List<Event> events);

}
