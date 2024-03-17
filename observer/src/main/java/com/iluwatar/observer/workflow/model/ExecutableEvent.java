package com.iluwatar.observer.workflow.model;

import com.iluwatar.observer.workflow.enums.EventType;
import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
public class ExecutableEvent {
    GenericEvent originalEvent;
    ResponseType responseType;
    ResponseMode responseMode;

    public ExecutableEvent() {
    }

    public ExecutableEvent(GenericEvent originalEvent, ResponseType responseType, ResponseMode responseMode) {
        this.originalEvent = originalEvent;
        this.responseType = responseType;
        this.responseMode = responseMode;
    }

    public boolean match(EventType... options) {
        return Arrays.stream(options).anyMatch(e -> e == this.getOriginalEvent().getEventType());
    }

}
