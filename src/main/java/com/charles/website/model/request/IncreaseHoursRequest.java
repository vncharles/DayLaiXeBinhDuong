package com.charles.website.model.request;

import lombok.Data;

@Data
public class IncreaseHoursRequest {
    private String category;
    private Double value;
}
