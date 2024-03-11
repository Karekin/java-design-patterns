package com.iluwatar.observer.workflow;

public class KafkaMQAdapter implements MessageBroker {
    @Override
    // public void sendMessage(Event event) {
    public void sendMessage(String message) {
        System.out.println("MessageQueueAdapter: Sending message - " + /*event.getMessage()*/ message);
        // 这里模拟发送消息到消息队列
    }

    @Override
    public void registerListener(MessageListener listener) {
        // 这里模拟从消息队列接收消息
        Event receivedEvent = new Event("Message from message queue");
        listener.onMessageReceived(receivedEvent);
    }
}
