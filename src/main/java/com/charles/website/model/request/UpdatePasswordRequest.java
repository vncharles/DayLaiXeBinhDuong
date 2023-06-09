package com.charles.website.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdatePasswordRequest {
    private Long studentId;
    private String password;
}
