package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iluwatar.observer.workflow.enums.EventResponseManager;
import com.iluwatar.observer.workflow.model.GenericEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 主要职责：负责作为消息队列（Kafka）的适配器，处理消息的发送和接收
 */
@Service
public class KafkaMQAdapter implements MessageBroker {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(GenericEvent event) throws JsonProcessingException {
        // 发送消息到Kafka
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(event);
        kafkaTemplate.send(event.getEventType().getEventType(), jsonStr);
    }

    // 使用KafkaListener注解监听Kafka消息，并转发给EventBus处理
    @KafkaListener(topics = "#{T(com.iluwatar.observer.workflow.enums.EventType).getTopics()}", groupId = "my-group-id")
    public void onMessageReceived(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        GenericEvent event = mapper.readValue(message, GenericEvent.class);
        EventResponseManager.handleEvent(event); // 转发事件到EventBus处理
    }
}

