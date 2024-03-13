package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.model.GenericEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class EventBusImpl implements EventBus {
    private MessageBroker broker = new SpringEventAdapter();
    // 用于注册事件与处理器的映射
    private final Map<EventType, List<Consumer<GenericEvent>>> eventHandlers = new HashMap<>();

    public EventBusImpl(MessageBroker broker) {
        this.broker = broker;
    }

    @Override
    public void publish(GenericEvent event) throws JsonProcessingException {
        // 发布事件到消息队列
        broker.sendMessage(event);
    }

    // 重命名subscribe方法为registerHandler，并调整实现逻辑
    @Override
    public void registerHandler(EventType eventType, Consumer<GenericEvent> handler) {
        eventHandlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    // 新增方法，处理接收到的事件
    @Override
    public void handleEvent(GenericEvent event) {
        // 根据事件类型找到注册的所有处理器并执行
        List<Consumer<GenericEvent>> handlers = eventHandlers.get(event.getEventType());
        if (handlers != null) {
            handlers.forEach(handler -> handler.accept(event));
        } else {
            System.out.println("No handler registered for event type: " + event.getEventType());
        }
    }
}



