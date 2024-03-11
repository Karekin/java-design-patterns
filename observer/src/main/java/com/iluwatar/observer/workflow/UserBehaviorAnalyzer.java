package com.iluwatar.observer.workflow;

public class UserBehaviorAnalyzer implements MessageListener {
    @Override
    public void onMessageReceived(Event event) {
        System.out.println("Received event: " + event.getMessage());
        // 根据Event的类型和内容执行相应的处理逻辑
    }
}
