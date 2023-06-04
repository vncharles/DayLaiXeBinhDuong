package com.charles.website.controller.admin;

import com.charles.website.entity.Account;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.response.RegisterResponse;
import com.charles.website.model.request.LoginRequest;
import com.charles.website.model.request.ResetPassRequest;
import com.charles.website.model.response.UserResponse;
import com.charles.website.services.AccountService;
import com.charles.website.utils.Authen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody RegisterResponse registerResponse,
                                          HttpServletRequest request) throws MessagingException {

        accountService.createAccount(registerResponse);

        return ResponseEntity.ok(new MessageResponse("Register success. "));
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(HttpServletRequest request,
                                                  @RequestParam("token") String token,
                                                  @RequestBody ResetPassRequest forgot) {

//        User user = userService.getByResetPasswordToken(token);
        String password = forgot.getPassword();

//        if (user == null) {
//            throw new BadRequestException(1402, "token wrong");
//        }
//
//        userService.updatePassword(user, password);

        return ResponseEntity.ok(new MessageResponse("Reset password success"));
    }

    @PostMapping("update_info")
    public ResponseEntity<?> updateInfo(@Valid @RequestBody RegisterResponse request){
        Authen.check();

//        userService.updateInfo(request);

        return ResponseEntity.ok(new MessageResponse("Sửa thành công"));
    }

    @GetMapping("info-detail")
    public ResponseEntity<?> infoDetail() {
        Authen.check();

        Account account = accountService.infoDetail();

        return ResponseEntity.ok(new UserResponse(account));
    }
}
