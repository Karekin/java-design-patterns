package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.EventType;

public interface EventBus {
    void publish(Event event) throws JsonProcessingException;
//    void subscribe(MessageListener listener);

    void registerHandler(EventType eventType, EventHandler handler);
}

