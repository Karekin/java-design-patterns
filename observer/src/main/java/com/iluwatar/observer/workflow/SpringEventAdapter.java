package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.enums.EventResponseManager;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.model.ExecutableEvent;
import com.iluwatar.observer.workflow.model.GenericEvent;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.EnumSet;
import java.util.Set;

@Service
public class SpringEventAdapter implements MessageBroker  {
    @Resource
    ApplicationEventPublisher publisher;

    @Override
    public Set<ResponseMode> getHandledEventModes() {
        // 假设SpringEventAdapter可以处理任务创建和任务删除事件
        return EnumSet.of(ResponseMode.SYNC_SINGLE, ResponseMode.ASYNC_SINGLE);
    }

    @Override
    public void sendMessage(ExecutableEvent event) {
        publisher.publishEvent(event);
    }

    @Order(0)
    @EventListener(classes = {ExecutableEvent.class},
            condition = "#event.match(T(com.iluwatar.observer.workflow.enums.EventType).REFRESH)")
//    @Transactional(rollbackFor = {Exception.class})
    public void onMessageReceived(ExecutableEvent event) {
        EventResponseManager.handleEvent(event);
    }
}
