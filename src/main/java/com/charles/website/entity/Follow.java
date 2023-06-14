package com.charles.website.entity;

import com.charles.website.model.request.FollowRequest;
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
    private String teacher;
    private String note;

    public Follow(StudentDegreeID id, FollowRequest request) {
        this.id = id;
        this.course = request.getCourse();
        this.hoursRunningDAT = request.getHoursRunningDAT();
        this.nightRunningHours = request.getNightRunningHours();
        this.automaticRunningHours = request.getAutomaticRunningHours();
        this.kmDAT = request.getKmDAT();
        this.theotyTestScore = request.getTheotyTestScore();
        this.simulatedTestScore = request.getSimulatedTestScore();
        this.teacher = request.getTeacher();
        this.note = request.getNote();
    }

    public void increaseHoursRunningDAT(double value) {
        hoursRunningDAT = hoursRunningDAT + value;
    }

    public void increaseNightRunningHours(double value) {
        nightRunningHours = nightRunningHours + value;
    }

    public void increaseAutomaticRunningHours(double value) {
        automaticRunningHours = automaticRunningHours + value;
    }
}
