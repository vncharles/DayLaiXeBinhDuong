package com.charles.website.model.request;

import lombok.Data;

@Data
public class AccountUpdateRequest {
    private Long studentId;
    private String password;
}
