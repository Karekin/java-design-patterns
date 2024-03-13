package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.config.EventHandlerComponent;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.model.GenericEvent;

@EventHandlerComponent(EventType.SUBMIT)
public class UserBehaviorAnalyzer implements EventHandler {
    @Override
    public void handle(GenericEvent event) {
        System.out.println("Received event: " + event.getMessage());
        // 根据Event的类型和内容执行相应的处理逻辑
    }
}

