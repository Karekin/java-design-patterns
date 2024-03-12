package com.iluwatar.observer.workflow.config;

import com.iluwatar.observer.workflow.EventBusImpl;
import com.iluwatar.observer.workflow.EventHandler;
import com.iluwatar.observer.workflow.enums.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

@Configuration
public class EventHandlerConfig {

    @Autowired
    private EventBusImpl eventBus;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void registerEventHandlers() {
        Map<String, Object> handlerBeans = applicationContext.getBeansWithAnnotation(EventHandlerComponent.class);
        for (Object bean : handlerBeans.values()) {
            EventHandlerComponent annotation = bean.getClass().getAnnotation(EventHandlerComponent.class);
            EventType eventType = annotation.value();
            eventBus.registerHandler(eventType, (EventHandler) bean);
        }
    }
}

