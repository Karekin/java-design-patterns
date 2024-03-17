package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.model.ExecutableEvent;
import com.iluwatar.observer.workflow.model.GenericEvent;

import java.util.Set;

public interface MessageBroker {
    Set<ResponseMode> getHandledEventModes();

    void sendMessage(ExecutableEvent event) throws JsonProcessingException;
//    void registerListener(MessageListener listener);
}