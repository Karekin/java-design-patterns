package com.iluwatar.observer.workflow;

public class EventCenterAdapter implements MessageBroker {
    @Override
    // public void sendMessage(Event event) {
    public void sendMessage(String message) {
        System.out.println("EventCenterAdapter: Publishing event - " + /*event.getMessage()*/ message);
        // 这里模拟发布事件到事件中心
    }

    @Override
    public void registerListener(MessageListener listener) {
        // 这里模拟从事件中心接收事件
        Event receivedEvent = new Event("Message from event center");
        listener.onMessageReceived(receivedEvent);
    }
}