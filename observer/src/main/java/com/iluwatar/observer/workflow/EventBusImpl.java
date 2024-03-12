package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.EventType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventBusImpl implements EventBus {
    private MessageBroker broker;
    private Map<EventType, List<EventHandler>> handlerMap = new HashMap<>();

    public EventBusImpl(MessageBroker broker) {
        this.broker = broker;
    }

    @Override
    public void publish(Event event) throws JsonProcessingException {
        // broker.sendMessage(event);
        broker.sendMessage(event);
    }

//    @Override
//    public void subscribe(MessageListener listener) {
//        broker.registerListener(listener);
//    }

    @Override
    public void registerHandler(EventType eventType, EventHandler handler) {
        handlerMap.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }
}
