package com.iluwatar.observer.workflow.model;

import com.iluwatar.observer.workflow.enums.EventType;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Arrays;

@Data
public class MessageQueueEvent implements GenericEvent {
    private final EventType eventType;
    private final String message;
    private final Object source;

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