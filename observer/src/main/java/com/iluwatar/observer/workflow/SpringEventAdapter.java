package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.enums.EventResponseManager;
import com.iluwatar.observer.workflow.model.GenericEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
@Qualifier("defaultBroker")
public class SpringEventAdapter implements MessageBroker  {
    @Resource
    ApplicationEventPublisher publisher;

    @Override
    public void sendMessage(GenericEvent event) {
        publisher.publishEvent(event);
    }

    @Order(0)
    @EventListener(classes = {GenericEvent.class},
            condition = "#event.match(T(com.iluwatar.observer.workflow.enums.EventType).REFRESH)")
//    @Transactional(rollbackFor = {Exception.class})
    public void onMessageReceived(GenericEvent event) {
        EventResponseManager.handleEvent(event);
    }
}
