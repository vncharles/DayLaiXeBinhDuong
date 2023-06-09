package com.charles.website.controller;

import com.charles.website.model.request.LoginRequest;
import com.charles.website.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest login) {
        String username = login.getUsername();
        String password = login.getPassword();
        return ResponseEntity.ok(accountService.loginAccount(username, password));
    }
}
