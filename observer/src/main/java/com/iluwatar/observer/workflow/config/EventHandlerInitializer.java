package com.iluwatar.observer.workflow.config;

import com.iluwatar.observer.workflow.Event;
import com.iluwatar.observer.workflow.EventBus;
import com.iluwatar.observer.workflow.enums.EventResponseManager;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
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
            EventType eventType = annotation.value(); // 这里的value()需要调整为EventType
            // 基于eventType获取所有关联的ResponseType和ResponseMode
            List<Pair<ResponseType, ResponseMode>> associations
                    = EventResponseManager.getAssociationsForEvent(eventType);

            associations.forEach(association -> {eventBus.registerHandler(
                    association.getKey(), association.getValue(), (Consumer<Event>) bean);
            });
        });
    }
}


