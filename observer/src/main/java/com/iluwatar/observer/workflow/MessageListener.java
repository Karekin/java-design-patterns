package com.iluwatar.observer.workflow;

import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;

public interface MessageListener {
    void onMessageReceived(Event event, ResponseType responseType, ResponseMode responseMode);
}
