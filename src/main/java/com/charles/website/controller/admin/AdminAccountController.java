package com.charles.website.controller.admin;

import com.charles.website.entity.Account;
import com.charles.website.entity.Follow;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.request.AccountUpdateRequest;
import com.charles.website.model.request.RegisterRequest;
import com.charles.website.model.request.UpdatePasswordRequest;
import com.charles.website.model.response.AccountResponse;
import com.charles.website.services.AccountService;
import com.charles.website.utils.Authen;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/account")
public class AdminAccountController {
    @Autowired
    private AccountService accountService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("")
    public ResponseEntity<?> listAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(value = "filter", required = false)String filter) {
        Authen.check();

        Pageable pageable = PageRequest.of(page, size);
        Page<Account> result = accountService.getListUser(filter, pageable);

        List<AccountResponse> list = result.getContent().stream().map(account -> new AccountResponse(account)).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("data", list);
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id")Long id) {
        Authen.check();

        Account account = accountService.getDetailAccout(id);

        return ResponseEntity.ok(new AccountResponse(account));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody RegisterRequest registerRequest) {
        Authen.check();
        accountService.createAccount(registerRequest);

        return ResponseEntity.ok(new MessageResponse("Create account is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")Long id,
                                            @RequestBody AccountUpdateRequest request) {
        Authen.check();

        accountService.update(id, request);

        return ResponseEntity.ok(new MessageResponse("Update password account is success"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("/deactive")
    public ResponseEntity<?> deactive(@RequestParam("id")Long id) {
        Authen.check();

        accountService.deactive(id);

        return ResponseEntity.ok(new MessageResponse("Deactive account is success"));
    }
}
