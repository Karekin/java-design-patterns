package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MessageListenerDelegate implements MessageListener {
    private final Map<Pair<ResponseType, ResponseMode>, Consumer<Event>> handlerMap = new HashMap<>();

    public void registerHandler(ResponseType responseType, ResponseMode responseMode, Consumer<Event> handler) {
        handlerMap.put(new Pair<>(responseType, responseMode), handler);
    }

    @Override
    public void onMessageReceived(Event event, ResponseType responseType, ResponseMode responseMode) {
        Pair<ResponseType, ResponseMode> key = new Pair<>(responseType, responseMode);
        Consumer<Event> handler = handlerMap.get(key);
        if (handler != null) {
            handler.accept(event);
        } else {
            System.out.println("No handler registered for " + key);
        }
    }
}

