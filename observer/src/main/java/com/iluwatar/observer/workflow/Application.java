package com.iluwatar.observer.workflow;

public class Application {
    public static void main(String[] args) {
        // 选择使用消息队列适配器或事件中心适配器
        MessageBroker broker = new KafkaMQAdapter(); // 或 new EventCenterAdapter();

        EventBus eventBus = new EventBusImpl(broker);

        // 注册监听器
        eventBus.subscribe(event -> System.out.println("Received event: " + event.getMessage()));

        // 发布事件
        eventBus.publish(new Event("Hello World"));
    }
}