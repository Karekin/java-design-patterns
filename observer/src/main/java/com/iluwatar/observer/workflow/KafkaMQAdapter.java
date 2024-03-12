package com.iluwatar.observer.workflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iluwatar.observer.workflow.enums.EventResponseManager;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;
import javafx.util.Pair;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaMQAdapter implements MessageBroker {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    MessageListenerDelegate delegate = new MessageListenerDelegate();

    @Override
    public void sendMessage(Event event) throws JsonProcessingException {
        System.out.println("MessageQueueAdapter: Sending message - " + event.getMessage());
        // 这里模拟发送消息到消息队列
        ObjectMapper mapper = new ObjectMapper();
        String jstr = mapper.writeValueAsString(event);
        kafkaTemplate.send(event.getEventType().getEventType(), jstr);
    }


    @KafkaListener(topics = "#{T(com.iluwatar.observer.workflow.enums.EventType).getTopics()}")
    public void registerListener(ConsumerRecord<String, String> record) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Event event = mapper.readValue(record.value(), Event.class);
        // 根据event的具体类型和模式，调用delegate的handleEventWithMode方法
        List<Pair<ResponseType, ResponseMode>> associationsForEvent
                = EventResponseManager.getAssociationsForEvent(event.getEventType());

        for (Pair<ResponseType, ResponseMode> pair : associationsForEvent) {
            delegate.onMessageReceived(event, pair.getKey(), pair.getValue());
        }
    }
}
