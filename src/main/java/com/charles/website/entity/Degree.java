package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Degree extends BaseDomain {

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private double price;
    private int allowAge;
    @NotNull
    private String rating;
    @NotNull
    private double studyTime;
    private String categoryCar;
    @NotNull
    private int DAT;
    private String advantage;

    public Degree(Long degreeId) {
        super.setId(degreeId);
    }
}
