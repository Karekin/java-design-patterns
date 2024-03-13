package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.model.GenericEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
@Primary  // TODO 之后去掉，先测试
public class SpringEventAdapter implements MessageBroker  {
    @Resource
    ApplicationEventPublisher publisher;
    @Resource
    EventBus eventBus; // 确保EventBus能够处理接收到的事件

    @Override
    public void sendMessage(GenericEvent event) {
        publisher.publishEvent(event);
    }

    @Order(0)
    @EventListener(classes = {GenericEvent.class},
            condition = "#event.match(T(com.iluwatar.observer.workflow.enums.EventType).REFRESH)")
//    @Transactional(rollbackFor = {Exception.class})
    public void onMessageReceived(GenericEvent event) {
        eventBus.handleEvent(event);
    }
}
