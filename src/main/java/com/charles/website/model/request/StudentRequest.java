package com.charles.website.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class StudentRequest {
    private String fullName;
    private String phoneNumber;
    private String address;
    private Date birthday;
}
