package com.iluwatar.observer.workflow.model;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class UserInfo {
    private String username;
    private String email;
}

