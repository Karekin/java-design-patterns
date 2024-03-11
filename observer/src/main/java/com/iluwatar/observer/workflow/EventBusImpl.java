package com.iluwatar.observer.workflow;

public class EventBusImpl implements EventBus {
    private MessageBroker broker;

    public EventBusImpl(MessageBroker broker) {
        this.broker = broker;
    }

    @Override
    public void publish(Event event) {
        // broker.sendMessage(event);
        // 转换Event为字符串消息，这里简化为直接使用Event的message属性
        String messageContent = event.getMessage();
        broker.sendMessage(messageContent);
    }

    @Override
    public void subscribe(MessageListener listener) {
        broker.registerListener(listener);
    }
}