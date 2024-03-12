package com.iluwatar.observer.workflow.enums;

public enum ResponseMode {
    SYNCHRONOUS("同步执行"),
    ASYNCHRONOUS("异步执行");

    private final String description;

    ResponseMode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

