package com.charles.website.model.request;

//import com.charles.website.model.AbstractDTO;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String role;
}
