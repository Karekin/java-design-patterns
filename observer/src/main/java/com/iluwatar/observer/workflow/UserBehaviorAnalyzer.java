package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.config.EventHandlerComponent;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;
import com.iluwatar.observer.workflow.model.GenericEvent;
import org.springframework.stereotype.Component;

@Component
@EventHandlerComponent(type = ResponseType.SEND_TODO, mode = ResponseMode.SYNC_MULTI)
public class UserBehaviorAnalyzer implements EventHandler {
    @Override
    public void handle(GenericEvent event) {
        System.out.println("Received event: " + event.getMessage());
        // 根据Event的类型和内容执行相应的处理逻辑
    }
}

