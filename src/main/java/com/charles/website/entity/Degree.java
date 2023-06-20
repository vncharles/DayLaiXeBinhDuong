package com.charles.website.entity;

import com.charles.website.model.request.DegreeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Degree extends BaseDomain {

    @NotNull
    private String title;
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
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

    public Degree(DegreeRequest request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.price = request.getPrice();
        this.allowAge = request.getAllowAge();
        this.rating = request.getRating();
        this.studyTime = request.getStudyTime();
        this.categoryCar = request.getCategoryCar();
        this.DAT = request.getDAT();
        this.advantage = request.getAdvantage();
    }
}
