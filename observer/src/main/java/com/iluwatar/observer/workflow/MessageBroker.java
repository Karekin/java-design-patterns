package com.iluwatar.observer.workflow;

public interface MessageBroker {
    // void sendMessage(Event event);
    void sendMessage(String message);
    void registerListener(MessageListener listener);
}