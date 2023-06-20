package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Document extends BaseDomain{
    @NotNull
    private String title;
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
    @NotNull
    private String image;
    @NotNull
    private String link;
}
