package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iluwatar.observer.workflow.config.EventHandlerInitializer;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.model.SpringGenericEventAdapter;
import com.iluwatar.observer.workflow.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
@SpringBootTest(classes = {Application.class, EventHandlerInitializer.class})
public class testqWFEvent {

    @Autowired
    private EventBus eventBus;

    @Test
    public void testSyncSend() throws JsonProcessingException {
//        MessageBroker broker = new SpringEventAdapter(); // 或 new EventCenterAdapter();
//
//        EventBus eventBus = new EventBusImpl(broker);
        UserInfo user = UserInfo.builder()
                .username("john_doe")
                .email("john.doe@example.com")
                .build();

        // 创建并发布用户注册事件
        SpringGenericEventAdapter event = new SpringGenericEventAdapter(
                user,
                EventType.REFRESH,
                "User has been registered successfully"
        );

        eventBus.publish(event);
    }


}