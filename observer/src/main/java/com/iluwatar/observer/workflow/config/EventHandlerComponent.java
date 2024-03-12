package com.iluwatar.observer.workflow.config;

import com.iluwatar.observer.workflow.enums.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandlerComponent {
    EventType value();
}

