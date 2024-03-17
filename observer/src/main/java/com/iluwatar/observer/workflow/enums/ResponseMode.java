package com.iluwatar.observer.workflow.enums;

public enum ResponseMode {
    SYNC_SINGLE("单实例同步"),
    ASYNC_SINGLE("单实例异步"),
    SYNC_MULTI("多实例同步"),
    ASYNC_MULTI("多实例异步");

    private final String description;

    ResponseMode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


