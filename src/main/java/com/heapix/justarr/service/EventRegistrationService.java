package com.heapix.justarr.service;

import com.heapix.justarr.controller.bo.CreateResponseBo;
import com.heapix.justarr.controller.bo.EventRegistrationBo;
import com.heapix.justarr.controller.dto.NewEventRegistrationDto;

import java.util.List;

/**
 * @author mgergalov
 */
public interface EventRegistrationService {

    CreateResponseBo registerEvent(NewEventRegistrationDto eventRegistration, Long userId) throws Exception;

    EventRegistrationBo getEventRegistration(Long eventId, Long userId);

    void unregisterEvent(Long eventId, Long userId);

    List<EventRegistrationBo> getEventRegistrations(long eventId);
}
