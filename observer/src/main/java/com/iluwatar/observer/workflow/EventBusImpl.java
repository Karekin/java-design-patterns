package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class EventBusImpl implements EventBus {
    private MessageBroker broker;
    private final Map<Pair<ResponseType, ResponseMode>, Consumer<Event>> handlerMap = new HashMap<>();

    public EventBusImpl(MessageBroker broker) {
        this.broker = broker;
    }

    @Override
    public void publish(Event event) throws JsonProcessingException {
        // 假设Event中有方法可以获取到ResponseType和ResponseMode
////        Pair<ResponseType, ResponseMode> key = new Pair<>(event.getResponseType(), event.getResponseMode());
//        Consumer<Event> handler = handlerMap.get(key);
//        if (handler != null) {
//            handler.accept(event);
//        } else {
//            System.out.println("No handler registered for " + key);
//            // 如果没有找到处理器，可以选择将事件通过broker发送出去
//            broker.sendMessage(event);
//        }
        broker.sendMessage(event);
    }

    @Override
    public void handleEvent(Event event, ResponseType responseType, ResponseMode responseMode) {
        Pair<ResponseType, ResponseMode> key = new Pair<>(responseType, responseMode);
        Consumer<Event> handler = handlerMap.get(key);
        if (handler != null) {
            handler.accept(event);
        } else {
            System.out.println("No handler registered for " + key);
        }
    }

    @Override
    public void registerHandler(ResponseType responseType, ResponseMode responseMode, Consumer<Event> handler) {
        handlerMap.put(new Pair<>(responseType, responseMode), handler);
    }
}

