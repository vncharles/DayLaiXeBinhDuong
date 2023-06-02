package com.charles.website.model.response;

//import com.charles.website.model.AbstractDTO;
import lombok.Data;

@Data
public class RegisterResponse {
    private String username;
    private String password;
    private String role;
}
