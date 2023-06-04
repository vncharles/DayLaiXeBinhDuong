package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certificate {

    @EmbeddedId
    private StudentDegreeID id;

    private Date finishDay;
    private int totalKmDAT;
    private double totalHoursRunning;
    private boolean status;

}
