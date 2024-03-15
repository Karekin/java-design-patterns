package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.model.GenericEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class EventBusImpl implements EventBus {
    private final Map<EventType, MessageBroker> brokerMapping = new HashMap<>();
    private final MessageBroker defaultBroker;

    @Autowired
    public EventBusImpl(List<MessageBroker> brokers, @Qualifier("defaultBroker") MessageBroker defaultBroker) {
        this.defaultBroker = defaultBroker;
        // 假设每个MessageBroker实现都能处理一种特定类型的事件
//        for (MessageBroker broker : brokers) {
//            // 这里的getHandledEventType方法是假设的，需要在MessageBroker接口中定义，
//            // 用于获取该broker能处理的事件类型
////            EventType handledType = broker.getHandledEventType();
////            brokerMapping.put(handledType, broker);
//            brokerMapping.put(EventType.REFRESH, broker);
//        }
    }

    @Override
    public void publish(GenericEvent event) throws JsonProcessingException {
        // 根据事件类型选择对应的MessageBroker，如果没有找到特定类型的，使用默认的broker
        MessageBroker broker = brokerMapping.getOrDefault(event.getEventType(), defaultBroker);
        broker.sendMessage(event);
    }
}




