package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;

public class EventBusImpl implements EventBus {
    private MessageBroker broker;

    public EventBusImpl(MessageBroker broker) {
        this.broker = broker;
    }

    @Override
    public void publish(Event event) throws JsonProcessingException {
        // broker.sendMessage(event);
        broker.sendMessage(event);
    }

    @Override
    public void subscribe(MessageListener listener) {
        broker.registerListener(listener);
    }
}