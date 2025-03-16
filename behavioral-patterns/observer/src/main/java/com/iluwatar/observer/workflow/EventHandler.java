package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.model.GenericEvent;

public interface EventHandler {
    void handle(GenericEvent event);
}
