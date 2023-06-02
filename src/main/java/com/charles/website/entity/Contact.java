package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Contact extends BaseDomain{

    @NotNull
    private String fullName;
    @NotNull
    private String phoneNumber;
    private String note;
    private boolean status;
}
