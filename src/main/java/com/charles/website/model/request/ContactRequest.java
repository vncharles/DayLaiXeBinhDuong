package com.charles.website.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ContactRequest {
    private String fullName;
    private String phoneNumber;
    private String note;
    private Boolean status;
}
