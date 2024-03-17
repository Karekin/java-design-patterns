package com.iluwatar.observer.workflow.config;

import com.iluwatar.observer.workflow.enums.ResponseMode;
import com.iluwatar.observer.workflow.enums.ResponseType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandlerComponent {
    ResponseType type();
    ResponseMode mode() default ResponseMode.SYNC_SINGLE;
}

