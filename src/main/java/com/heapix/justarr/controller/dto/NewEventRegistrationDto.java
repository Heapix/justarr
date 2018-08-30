package com.heapix.justarr.controller.dto;

/**
 * @author mgergalov
 */
public class NewEventRegistrationDto {

    private Long eventId;
    private String info;


    public NewEventRegistrationDto(Long eventId, String info) {
        this.eventId = eventId;
        this.info =  info;
    }

    public NewEventRegistrationDto() {
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

}
