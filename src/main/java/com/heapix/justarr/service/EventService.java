package com.heapix.justarr.service;

import com.heapix.justarr.controller.bo.CreateResponseBo;
import com.heapix.justarr.controller.bo.EventInfoBo;
import com.heapix.justarr.controller.bo.UpdateResponseBo;
import com.heapix.justarr.persistence.model.Event;
import javassist.NotFoundException;

import java.util.List;

/**
 * @author mgergalov
 */
public interface EventService {

    CreateResponseBo createEvent(Event event);
    EventInfoBo getEventInfo(Long id) throws NotFoundException;
    List<EventInfoBo> getAll();
    List<EventInfoBo> getByRole(Long role);
    UpdateResponseBo updateEvent(Event event, Long id);
    void remove(Long id);

}
