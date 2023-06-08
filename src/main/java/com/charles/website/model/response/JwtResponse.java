package com.charles.website.model.response;

import com.charles.website.entity.Student;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String role;
    private Student student;

    public JwtResponse(String accessToken, Long id, String username, String role, Student student) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.role = role;
        this.student = student;
    }
}
