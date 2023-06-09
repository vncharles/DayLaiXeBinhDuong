package com.charles.website.model.response;

import com.charles.website.entity.Account;
import com.charles.website.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AccountResponse {
    private Long id;
    private String username;
    private String role;
    private boolean active;
    private Student student;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.role = account.getRoles().getName().name();
        this.student = account.getStudent();
        this.active = account.isActive();
    }
}
