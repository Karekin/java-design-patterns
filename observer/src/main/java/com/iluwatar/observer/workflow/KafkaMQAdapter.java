package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iluwatar.observer.workflow.enums.EventResponseManager;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.model.ExecutableEvent;
import com.iluwatar.observer.workflow.model.GenericEvent;
import com.iluwatar.observer.workflow.model.MessageQueueEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

/**
 * 主要职责：负责作为消息队列（Kafka）的适配器，处理消息的发送和接收
 */
@Service
@Qualifier("defaultBroker")
public class KafkaMQAdapter implements MessageBroker {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Set<ResponseMode> getHandledEventModes() {
        // 假设SpringEventAdapter可以处理任务创建和任务删除事件
        return EnumSet.of(ResponseMode.SYNC_MULTI, ResponseMode.ASYNC_MULTI);
    }

    @Override
    public void sendMessage(ExecutableEvent event) throws JsonProcessingException {
        // 发送消息到Kafka
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(event);
        kafkaTemplate.send(event.getOriginalEvent().getEventType().getEventType(), jsonStr);
//        kafkaTemplate.send(event.getOriginalEvent().getEventType().getEventType(), event);
    }

    // 使用KafkaListener注解监听Kafka消息，并转发给EventBus处理
    @KafkaListener(topics = {"TASKFLOW_REFRESH"}, groupId = "my-group-id")
    public void onMessageReceived(ConsumerRecord<Integer, String> record) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
//        MessageQueueEvent event = mapper.readValue(record.value(), MessageQueueEvent.class);
        ExecutableEvent event = mapper.readValue(record.value(), ExecutableEvent.class);
        EventResponseManager.handleEvent(event); // 转发事件到EventBus处理
    }
}

