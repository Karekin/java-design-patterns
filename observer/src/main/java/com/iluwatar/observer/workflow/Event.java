package com.iluwatar.observer.workflow;

public class Event {
    private String message;

    public Event(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
