package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.enums.EventType;
import lombok.Data;

@Data
public class Event {
    private EventType eventType;
    private String message;

    public Event(EventType eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

}
