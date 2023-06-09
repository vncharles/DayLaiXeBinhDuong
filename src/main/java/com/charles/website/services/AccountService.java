package com.charles.website.services;

import com.charles.website.entity.Account;
import com.charles.website.model.request.RegisterRequest;
import com.charles.website.model.response.JwtResponse;
import com.charles.website.model.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    Page<Account> getListUser(Pageable pageable);

    Account getDetailAccout(Long id);

    void createAccount(RegisterRequest registerRequest);

    void updatePassword(Long id, Long studentId, String password);

    void deactive(Long id);

    JwtResponse loginAccount(String username, String password);
}
