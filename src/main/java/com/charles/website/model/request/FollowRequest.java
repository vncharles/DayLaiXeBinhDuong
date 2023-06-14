package com.charles.website.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FollowRequest {
    private Long studentId;
    private Long degreeId;
    private String course;
    private Double hoursRunningDAT;
    private Double nightRunningHours;
    private Double automaticRunningHours;
    private Integer kmDAT;
    private Double theotyTestScore;
    private Double simulatedTestScore;
    private String teacher;
    private String note;
}
