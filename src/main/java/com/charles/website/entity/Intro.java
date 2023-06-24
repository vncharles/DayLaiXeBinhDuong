package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Intro extends BaseDomain {

    @Column(name = "link", columnDefinition = "TEXT", nullable = false)
    private String link;

}
