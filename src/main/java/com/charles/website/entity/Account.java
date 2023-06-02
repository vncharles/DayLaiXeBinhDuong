package com.charles.website.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Account extends BaseDomain{

    @Column(updatable = false, nullable = false)
    private String username;

    @NotNull
    private String password;
    private boolean active;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role roles;

    @OneToOne
    @JoinColumn(name = "student")
    private Student student;
}
