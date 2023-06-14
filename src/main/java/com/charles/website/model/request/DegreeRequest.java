package com.charles.website.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DegreeRequest {
    private String title;
    private String description;
    private Double price;
    private Integer allowAge;
    private String rating;
    private Double studyTime;
    private String categoryCar;
    private Integer DAT;
    private String advantage;
}
