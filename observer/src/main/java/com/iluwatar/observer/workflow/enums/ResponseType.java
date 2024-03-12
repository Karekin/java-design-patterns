package com.iluwatar.observer.workflow.enums;

public enum ResponseType {
    // 发送待办
    SEND_TODO("11", "2221"),
    // 发送通知
    SEND_NOTIFICATION("11", "222"),
    // 清除流程模板缓存
    // 清除流程链路缓存
    // 清除树形结构缓存
    // 清除权限缓存
    CLEAR_WF_CACHE("taskflow-refresh-cache", "TASKFLOW_REFRESH");

    private final String nodeCode;
    private final String eventType;

    ResponseType(String nodeCode, String eventType) {
        this.nodeCode = nodeCode;
        this.eventType = eventType;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public static ResponseType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

    public static ResponseType fromType(String nodeCode) {
        for (ResponseType op : ResponseType.values()) {
            if (op.getNodeCode().equals(nodeCode)) {
                return op;
            }
        }
        throw new IllegalArgumentException("No enum constant with type " + nodeCode);
    }
}
