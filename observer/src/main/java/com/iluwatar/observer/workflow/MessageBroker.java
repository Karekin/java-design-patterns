package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.model.GenericEvent;

public interface MessageBroker {
    void sendMessage(GenericEvent event) throws JsonProcessingException;
//    void registerListener(MessageListener listener);
}