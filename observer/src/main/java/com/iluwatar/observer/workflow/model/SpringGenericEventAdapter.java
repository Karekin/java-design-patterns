package com.iluwatar.observer.workflow.model;

import com.iluwatar.observer.workflow.enums.EventType;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Arrays;

public class SpringGenericEventAdapter extends ApplicationEvent implements GenericEvent {
    private EventType eventType;
    private String message;

    public SpringGenericEventAdapter(Object source) {
        super(source);
    }

    public SpringGenericEventAdapter(Object source, EventType eventType, String message) {
        super(source);
        this.eventType = eventType;
        this.message = message;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    public void setEventType(EventType eventType) {this.eventType = eventType;}

    public void setMessage(String message) {this.message = message;}
}

