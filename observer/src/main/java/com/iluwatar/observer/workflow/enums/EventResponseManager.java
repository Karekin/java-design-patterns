package com.iluwatar.observer.workflow.enums;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventResponseManager {
    private static final List<EventResponseAssociation> associations = new ArrayList<>();

    static {
        // 初始化关联，同时指定响应模式
        associations.add(new EventResponseAssociation(EventType.SUBMIT, ResponseType.SEND_TODO, ResponseMode.ASYNCHRONOUS));
        // 可以根据需要添加更多的关联规则
    }

    // 获取给定事件类型对应的响应类型列表，现在包含响应模式
    public static List<Pair<ResponseType, ResponseMode>> getAssociationsForEvent(EventType eventType) {
        return associations.stream()
                .filter(association -> association.eventType == eventType)
                .map(association -> new Pair<>(association.responseType, association.responseMode))
                .collect(Collectors.toList());
    }

    // 添加新的事件、响应和响应模式关联
    public static void addAssociation(EventType eventType, ResponseType responseType, ResponseMode responseMode) {
        associations.add(new EventResponseAssociation(eventType, responseType, responseMode));
    }

    // 内部类，用于表示事件与响应之间的关联，现在包括响应模式
    private static class EventResponseAssociation {
        final EventType eventType;
        final ResponseType responseType;
        final ResponseMode responseMode; // 新增的响应模式属性

        public EventResponseAssociation(EventType eventType, ResponseType responseType, ResponseMode responseMode) {
            this.eventType = eventType;
            this.responseType = responseType;
            this.responseMode = responseMode;
        }
    }
}

