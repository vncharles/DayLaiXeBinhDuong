package com.charles.website.model.response;

import com.charles.website.entity.Account;
//import com.charles.website.model.AbstractDTO;
import lombok.*;

import java.util.Date;

@Getter @Setter
public class UserResponse{
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Date birthday;
    private String numberPhone;
    private String role;

    public UserResponse(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        role = String.valueOf(account.getRoles().getName());
    }
}
