package com.iluwatar.observer.workflow;

public interface EventBus {
    void publish(Event event);
    void subscribe(MessageListener listener);
}

