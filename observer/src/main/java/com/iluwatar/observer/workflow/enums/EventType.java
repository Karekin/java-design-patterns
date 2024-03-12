package com.iluwatar.observer.workflow.enums;

import java.util.Arrays;

/**
 * 事件序号：1
 * 事件源：任务流
 * 事件类型：任务流刷新、启动、提交、审批、撤回、退回
 */
public enum EventType {
    // rpc://yonbip-fi-epmpbf@c87e2267-1001-4c70-bb2a-ab41f3b81aa3/asyncReceiveService
    REFRESH("TASK_FLOW", "TASKFLOW_REFRESH"),
    // 提交
    SUBMIT("11", "22"),
    // 批准
    APPROVE("11", "22"),
    // 撤回
    REJECT("11", "22");

    private final String sourceID;
    private final String eventType; // 等同于消息队列中的topic

    EventType(String sourceID,  String eventType) {
        this.sourceID = sourceID;
        this.eventType = eventType;
    }

    public String getSourceID() {
        return sourceID;
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public static EventType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

    public static EventType fromType(String eventType) {
        for (EventType op : EventType.values()) {
            if (op.getEventType().equals(eventType)) {
                return op;
            }
        }
        throw new IllegalArgumentException("No enum constant with type " + eventType);
    }

    public static String[] getTopics() {
        return Arrays.stream(EventType.values())
                .map(EventType::getEventType)
                .toArray(String[]::new);
    }
}
