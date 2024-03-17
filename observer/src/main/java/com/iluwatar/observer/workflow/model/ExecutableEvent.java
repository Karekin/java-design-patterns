package com.iluwatar.observer.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecutableEvent {
    private EventType eventType;
    private String message;
    private Object source;
    private ResponseType responseType;
    private ResponseMode responseMode;

    public ExecutableEvent() {
    }

    public ExecutableEvent(EventType eventType, String message, Object source
            , ResponseType responseType, ResponseMode responseMode) {

        this.eventType = eventType;
        this.message = message;
        this.source = source;
        this.responseType = responseType;
        this.responseMode = responseMode;
    }

    public boolean match(EventType... options) {
        return Arrays.stream(options).anyMatch(e -> e == this.getEventType());
    }

    public MessageQueueEvent toMessageQueueEvent() {
        // 创建一个新的MessageQueueEvent对象，使用ExecutableEvent对象的属性
        return new MessageQueueEvent(
                this.getSource(),
                this.getEventType(),
                this.getMessage()
        );
    }

    /**
     * 将 ExecutableEvent 转换为 SpringGenericEventAdapter。
     * @return 对应的 SpringGenericEventAdapter 实例。
     */
    public SpringGenericEventAdapter toSpringGenericEventAdapter() {
        // 创建一个新的 SpringGenericEventAdapter 对象，使用 ExecutableEvent 对象的属性
        return new SpringGenericEventAdapter(
                this.getSource(),
                this.getEventType(),
                this.getMessage()
        );
    }

}
