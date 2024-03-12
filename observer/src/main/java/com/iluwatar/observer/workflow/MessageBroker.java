package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageBroker {
    void sendMessage(Event event) throws JsonProcessingException;
    void registerListener(MessageListener listener);
}