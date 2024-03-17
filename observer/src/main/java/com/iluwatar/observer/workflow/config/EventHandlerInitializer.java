package com.iluwatar.observer.workflow.config;

import com.iluwatar.observer.workflow.EventBus;
import com.iluwatar.observer.workflow.EventHandler;
import com.iluwatar.observer.workflow.enums.EventResponseManager;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;
import com.iluwatar.observer.workflow.model.GenericEvent;
import javafx.util.Pair;
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
            if (bean instanceof EventHandler) {
                EventHandlerComponent annotation = bean.getClass().getAnnotation(EventHandlerComponent.class);
                ResponseType type = annotation.type();
                ResponseMode mode = annotation.mode();
                Pair<ResponseType, ResponseMode> pair = new Pair<>(type, mode);
                EventResponseManager.registerHandler(pair,
                        event -> ((EventHandler) bean).handle(event));
            }
        });
    }
}


