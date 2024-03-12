package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface EventBus {
    void publish(Event event) throws JsonProcessingException;
    void subscribe(MessageListener listener);
}

