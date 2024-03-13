package com.iluwatar.observer.workflow.config;

import com.iluwatar.observer.workflow.EventBus;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.model.GenericEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class EventHandlerInitializer {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void init() {
        // 获取所有标有@EventHandlerComponent注解的bean
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(EventHandlerComponent.class);
        beans.forEach((name, bean) -> {
            EventHandlerComponent annotation = bean.getClass().getAnnotation(EventHandlerComponent.class);
            EventType eventType = annotation.value();
            eventBus.registerHandler(eventType, (Consumer<GenericEvent>) bean);
        });
    }
}


