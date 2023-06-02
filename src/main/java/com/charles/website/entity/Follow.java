package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {

    @EmbeddedId
    private StudentDegreeID id;

    @NotNull
    private String course;
    private double hoursRunningDAT;
    private double nightRunningHours;
    private double automaticRunningHours;
    private int kmDAT;
    private double theotyTestScore;
    private double simulatedTestScore;
    @NotNull
    private String techer;
    private String note;

}
