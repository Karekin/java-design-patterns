package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.enums.EventType;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class EventCenterAdapter implements MessageBroker {
    @Override
    public void sendMessage(Event event) {
        System.out.println("EventCenterAdapter: Publishing event - " + event.getMessage());
        // 这里模拟发布事件到事件中心
//        Map<String, List<String>> map = (Map<String, List<String>>) request.get("workflow");
//        try {
//            BusinessEventBuilder businessEventBuilder = new BusinessEventBuilder();
//            EventType eventType = event.getEventType();
//            businessEventBuilder.setSourceId(eventType.getSourceID());
//            businessEventBuilder.setEventType(eventType.getEventType());
//
//            businessEventBuilder.setUserObject(map);
//            businessEventBuilder.setTenantCode(InvocationInfoProxy.getTenantid());
//            //如果需要租户默认token，tenantCode要传入yht租户id
//            BusinessEvent businessEvent = businessEventBuilder.build();
//            eventSendService.sendEvent(businessEvent);
//        } catch (Exception e) {
//            throw new BusinessException(e.getMessage());
//        }
    }

//    public void registerListener(MessageListener listener) {
//        // 这里模拟从事件中心接收事件
//        Event receivedEvent = new Event(EventType.APPROVE, "Message from event center");
//        listener.onMessageReceived(receivedEvent, null, null);
//    }
}