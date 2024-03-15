package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.model.GenericEvent;

import java.util.function.Consumer;

/**
 * 主要职责：EventBus 专注于事件的分发和处理器的管理
 * 事件处理流程：事件通过EventBusImpl发布到Kafka，KafkaMQAdapter监听Kafka中的消息，
 *            接收到消息后反序列化为Event对象，并通过EventBusImpl的handleEvent方法分发给相应的处理器。
 */
public interface EventBus {
    void publish(GenericEvent event) throws JsonProcessingException;

//    // 重命名subscribe方法为registerHandler，并调整实现逻辑
//    void registerHandler(EventType eventType, Consumer<GenericEvent> handler);
//
//    // 新增方法，处理接收到的事件
//    void handleEvent(GenericEvent event);
}

