package com.charles.website.services;

import com.charles.website.entity.Account;
import com.charles.website.model.response.RegisterResponse;
import com.charles.website.model.response.JwtResponse;
import com.charles.website.model.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public List<UserResponse> getListUser();

    public void createAccount(RegisterResponse registerResponse);

    public JwtResponse loginAccount(String username, String password);

    Account infoDetail();
}
