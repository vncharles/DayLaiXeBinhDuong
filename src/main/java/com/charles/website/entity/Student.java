package com.charles.website.entity;

import com.charles.website.model.request.StudentRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseDomain {
    @NotNull
    private String fullName;
    @NotNull
    private String phoneNumber;
    private String address;
    private Date birthday;

    public Student(Long studentId) {
        super.setId(studentId);
    }

    public Student(StudentRequest request) {
        this.setFullName(request.getFullName());
        this.setPhoneNumber(request.getPhoneNumber());
        this.setAddress(request.getAddress());
        this.setBirthday(request.getBirthday());
    }
}
