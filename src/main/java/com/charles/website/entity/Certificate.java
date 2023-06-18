package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certificate {

    @EmbeddedId
    private StudentDegreeID id;

    private LocalDate finishDay;
    private double totalHoursRunning;
    private int kmDAT;
    private double theotyTestScore;
    private double simulatedTestScore;
    private boolean status;

    public Certificate(StudentDegreeID id) {
        this.id = id;
    }
}
