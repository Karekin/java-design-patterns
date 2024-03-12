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

    @Override
    public void sendMessage(Event event) throws JsonProcessingException {
        System.out.println("MessageQueueAdapter: Sending message - " + event.getMessage());
        // 这里模拟发送消息到消息队列
        ObjectMapper mapper = new ObjectMapper();
        String jstr = mapper.writeValueAsString(event);
        kafkaTemplate.send(event.getEventType().getEventType(), jstr);
    }

    @Override
    public void registerListener(MessageListener listener) {

    }

//    @KafkaListener(topics = "#event.match(T(com.iluwatar.observer.workflow.enums.EventType).REFRESH)", containerFactory = "wfContainerFactory")
    @KafkaListener(topics = "#{T(com.iluwatar.observer.workflow.enums.EventType).getTopics()}")
    public void registerListener(ConsumerRecord<String, String> record) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Event event = mapper.readValue(record.value(), Event.class);
        List<Pair<ResponseType, ResponseMode>> associationsForEvent
                = EventResponseManager.getAssociationsForEvent(event.getEventType());

        for (Pair<ResponseType, ResponseMode> pair: associationsForEvent) {
            ResponseType responseType = pair.getKey();
            ResponseMode responseMode = pair.getValue();

            switch (responseType) {
                case SEND_TODO:
                    processEntityNodeTreeUpdate(event);
                    break;
                case CLEAR_WF_CACHE:
                    processEntityTreeUpdate(event);
                    break;
                default:
                    // Log or handle unknown message type
                    break;
            }
        }
    }

    public void registerListener(ConsumerRecord<String, String> record) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Event event = mapper.readValue(record.value(), Event.class);

        // 根据事件类型获取相应的策略类名
        String strategyClassName = getStrategyClassName(event.getResponseType(), event.getResponseMode());

        try {
            // 使用反射来创建策略实例
            Class<?> strategyClass = Class.forName(strategyClassName);
            EventHandlerStrategy strategy = (EventHandlerStrategy) strategyClass.newInstance();

            // 执行策略
            strategy.execute(event);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            // 处理异常，比如未找到策略类
        }
    }

    private String getStrategyClassName(ResponseType responseType, ResponseMode responseMode) {
        // 根据responseType和responseMode返回对应策略类的全限定名
        // 这里需要实现这个方法，根据实际情况映射策略类名
        return "com.example.strategies." + responseType.name() + responseMode.name() + "Strategy";
    }



    private void processEntityNodeTreeUpdate(Event event) {
        // Implementation for handling ENTITY_NODE_TREE update messages
        System.out.println(event.toString());
    }

    private void processEntityTreeUpdate(Event event) {
        // Implementation for handling ENTITY_TREE update messages
        System.out.println(event.toString());
    }

    private void processSubWorkflowUpdate(Event event) {
        // Implementation for handling SUB_WORKFLOW update messages
        System.out.println(event.toString());
    }
}
