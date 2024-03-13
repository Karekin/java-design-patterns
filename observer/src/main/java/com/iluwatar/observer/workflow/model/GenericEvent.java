package com.iluwatar.observer.workflow.model;

import com.iluwatar.observer.workflow.enums.EventType;

public interface GenericEvent {
    EventType getEventType();
    String getMessage();
    Object getSource();
}

