package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;

import java.util.function.Consumer;

/**
 * EventBus同时充当了发布者和订阅者的角色，管理事件的整个生命周期
 * 以及根据事件的 ResponseType 和 ResponseMode 调用对应的处理器
 */
public interface EventBus {
    void publish(Event event) throws JsonProcessingException;

//    void eventHandle(Event event);

    void handleEvent(Event event, ResponseType responseType, ResponseMode responseMode);

//    void subscribe(MessageListener listener);
    void registerHandler(ResponseType responseType, ResponseMode responseMode, Consumer<Event> handler);
}

