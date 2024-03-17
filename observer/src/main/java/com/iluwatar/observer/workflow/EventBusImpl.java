package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.enums.EventResponseManager;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;
import com.iluwatar.observer.workflow.model.ExecutableEvent;
import com.iluwatar.observer.workflow.model.GenericEvent;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
public class EventBusImpl implements EventBus {
    private final Map<ResponseMode, MessageBroker> brokerMapping = new HashMap<>();
    private final MessageBroker defaultBroker;

    @Autowired
    public EventBusImpl(List<MessageBroker> brokers, @Qualifier("defaultBroker") MessageBroker defaultBroker) {
        this.defaultBroker = defaultBroker;
        for (MessageBroker broker : brokers) {
            for (ResponseMode type : broker.getHandledEventModes()) {
                if ((type == ResponseMode.SYNC_MULTI
                        || type ==ResponseMode.ASYNC_MULTI)
                        && !(broker instanceof KafkaMQAdapter)) {
                    continue;
                }
                brokerMapping.put(type, broker);
            }
        }
    }

    /**
     * 一个事件可能会引发多个响应，不同响应可能需要不同的 broker。
     * 例如，提交操作，会发待办、执行规则
     * @param event
     * @throws JsonProcessingException
     */
    @Override
    public void publish(GenericEvent event) throws JsonProcessingException {
        // 根据事件类型选择对应的MessageBroker，如果没有找到特定类型的，使用默认的broker
        EventType eventType = event.getEventType();
        List<Pair<ResponseType, ResponseMode>> associationsForEvent
                = EventResponseManager.getAssociationsForEvent(eventType);
        // 校验：associationsForEvent为空，说明eventType没有与之关联的响应
        for(Pair<ResponseType, ResponseMode> pair : associationsForEvent) {
            ResponseMode mode = pair.getValue();
            ResponseType type = pair.getKey();

            ExecutableEvent executEvent = new ExecutableEvent();
            executEvent.setOriginalEvent(event);
            executEvent.setResponseType(type);
            executEvent.setResponseMode(mode);
            MessageBroker broker = brokerMapping.get(mode);
            broker.sendMessage(executEvent);
        }
    }
}




