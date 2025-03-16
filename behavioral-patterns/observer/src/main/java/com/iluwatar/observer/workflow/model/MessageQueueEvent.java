package com.iluwatar.observer.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iluwatar.observer.workflow.enums.EventType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageQueueEvent implements GenericEvent {
    private Object source;
    private EventType eventType;
    private String message;

    public MessageQueueEvent() {
    }

    public MessageQueueEvent(Object source, EventType eventType, String message) {
        this.source = source;
        this.eventType = eventType;
        this.message = message;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Object getSource() {
        return source;
    }
}


//@Getter
//public class Event extends SpringEvent<User> {
//    private final User user;
//
//    public Event(User source, EventType eventType, String message) {
//        super(source, eventType, message);
//        this.user = source; // 假设User是注册用户的相关信息
//    }
//}