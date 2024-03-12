package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;

public class Application {
    public static void main(String[] args) throws JsonProcessingException {
        // 选择使用消息队列适配器或事件中心适配器
        MessageBroker broker = new KafkaMQAdapter(); // 或 new EventCenterAdapter();

        EventBus eventBus = new EventBusImpl(broker);

        // 注册监听器
//        eventBus.subscribe(event -> System.out.println("Received event: " + event.getMessage()));

        // 发布事件
        eventBus.publish(new Event(EventType.REFRESH, "Hello World"));
    }
}