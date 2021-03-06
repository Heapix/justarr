package com.heapix.justarr.controller;

import com.heapix.justarr.controller.bo.CreateResponseBo;
import com.heapix.justarr.controller.bo.EventInfoBo;
import com.heapix.justarr.controller.converter.EventConverter;
import com.heapix.justarr.controller.dto.CreateEventDto;
import com.heapix.justarr.controller.dto.UpdateEventDto;
import com.heapix.justarr.persistence.model.Event;
import com.heapix.justarr.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.heapix.justarr.controller.EventUtils.getUserRole;

/**
 * @author mgergalov
 */
@RestController
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventConverter eventConverter;


    @GetMapping
    @PreAuthorize("permitAll()")
    public List<EventInfoBo> getAllEvents() {
        return eventService.getByRole(getUserRole().getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public EventInfoBo getEventInfo(@NotNull @PathVariable long id) throws Exception {
         EventInfoBo event = eventService.getEventInfo(id);
        if( event != null && event.getRole() < getUserRole().getId()) throw new Exception("access denied");
        return event;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator')")
    public ResponseEntity addEvent(@RequestBody CreateEventDto eventDto) {
        //impl
        CreateResponseBo response = eventService.createEvent(eventConverter.toModel(eventDto));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator')")
    public ResponseEntity updateEvent(@NotEmpty @RequestBody UpdateEventDto eventDto,
                                      @PathVariable long id) {
        //impl
        Event event = eventConverter.toModel(eventDto);
        return new ResponseEntity(eventService.updateEvent(event, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator')")
    public ResponseEntity removeEvent(@PathVariable long id) {
        //impl
        eventService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }



}
