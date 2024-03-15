package com.iluwatar.observer.workflow.enums;

import com.iluwatar.observer.workflow.model.GenericEvent;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 中心化的事件处理器注册和分发管理器
 */
public class EventResponseManager {
    private static final List<EventResponseAssociation> associations = new ArrayList<>();

    // 这个映射的目的是为了能够根据事件的类型快速找到所有注册的、可以处理该类型事件的处理器，并执行它们
    private static final Map<EventType, List<Consumer<GenericEvent>>> eventHandlers = new HashMap<>();

    static {
        // 初始化关联，同时指定响应模式
        associations.add(new EventResponseAssociation(EventType.SUBMIT, ResponseType.SEND_TODO, ResponseMode.ASYNCHRONOUS));
        // 可以根据需要添加更多的关联规则
    }

    // 获取给定事件类型对应的响应类型列表，现在包含响应模式
    public static List<Pair<ResponseType, ResponseMode>> getAssociationsForEvent(EventType eventType) {
        return associations.stream()
                .filter(association -> association.eventType == eventType)
                .map(association -> new Pair<>(association.responseType, association.responseMode))
                .collect(Collectors.toList());
    }

    // 添加新的事件、响应和响应模式关联
    public static void addAssociation(EventType eventType, ResponseType responseType, ResponseMode responseMode) {
        associations.add(new EventResponseAssociation(eventType, responseType, responseMode));
    }

    // 内部类，用于表示事件与响应之间的关联，现在包括响应模式
    private static class EventResponseAssociation {
        final EventType eventType;
        final ResponseType responseType;
        final ResponseMode responseMode; // 新增的响应模式属性

        public EventResponseAssociation(EventType eventType, ResponseType responseType, ResponseMode responseMode) {
            this.eventType = eventType;
            this.responseType = responseType;
            this.responseMode = responseMode;
        }
    }

    // 重命名subscribe方法为registerHandler，并调整实现逻辑
    public static void registerHandler(EventType eventType, Consumer<GenericEvent> handler) {
        eventHandlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    // 新增方法，处理接收到的事件
    public static void handleEvent(GenericEvent event) {
        // 根据事件类型找到注册的所有处理器并执行
        List<Consumer<GenericEvent>> handlers = eventHandlers.get(event.getEventType());
        if (handlers != null) {
            handlers.forEach(handler -> handler.accept(event));
        } else {
            System.out.println("No handler registered for event type: " + event.getEventType());
        }
    }
}

