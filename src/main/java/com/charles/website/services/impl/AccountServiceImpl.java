package com.charles.website.services.impl;

import com.charles.website.entity.ERole;
import com.charles.website.entity.Role;
import com.charles.website.entity.Account;
import com.charles.website.entity.Student;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.request.RegisterRequest;
import com.charles.website.model.response.JwtResponse;
import com.charles.website.model.response.UserResponse;
import com.charles.website.repository.RoleRepository;
import com.charles.website.repository.AccountRepository;
import com.charles.website.repository.StudentRepository;
import com.charles.website.security.jwt.JwtUtils;
import com.charles.website.security.service.UserDetailsImpl;
import com.charles.website.services.AccountService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Account> getListUser(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account getDetailAccout(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Account is not found");
        });

        return account;
    }

    @Override
    public void createAccount(RegisterRequest registerRequest) {
        Account currentAccout = accountRepository.findByUsername(Authen.username());
        if(currentAccout.getRoles().getName().equals(ERole.ROLE_STAFF) &&
                (registerRequest.getRole().equals("admin") || registerRequest.getRole().equals("staff")))
            throw new BadRequestException(400, "No permission create account admin or staff");

        if(registerRequest.getUsername()==null){
            throw new BadRequestException(1000, "username is required");
        }

        if(accountRepository.existsByUsername(registerRequest.getUsername())
                && accountRepository.findByUsername(registerRequest.getUsername()).isActive()) {
            throw new BadRequestException(1001, "username has already existed");
        }

        if(registerRequest.getPassword()==null){
            throw new BadRequestException(1100, "password is required");
        }

        Account account = new Account();
        account.setUsername(registerRequest.getUsername());
        account.setPassword(encoder.encode(registerRequest.getPassword()));
        account.setActive(true);

        Set<Role> roles = new HashSet<>();

        // Nếu user bth không có set role thì set thành ROLE_USER
        Role userRole = null;
        if(registerRequest.getRole().equals("admin")) {
            userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> {throw new NotFoundException(404, "Error: Role is not found");});
        } else if(registerRequest.getRole().equals("staff")) {
            userRole = roleRepository.findByName(ERole.ROLE_STAFF)
                    .orElseThrow(() -> {throw new NotFoundException(404, "Error: Role is not found");});
        } else if(registerRequest.getRole().equals("user")) {
            userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> {throw new NotFoundException(404, "Error: Role is not found");});
        } else throw new NotFoundException(404, "Error: Role is not found");

        account.setRoles(userRole);
        accountRepository.save(account);
    }

    @Override
    public void updatePassword(Long id, Long studentId, String password) {
        Account account = accountRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Account is not found");
        });

        Account currentAccout = accountRepository.findByUsername(Authen.username());
        if(currentAccout.getRoles().getName().equals(ERole.ROLE_STAFF) &&
                (account.getRoles().getName().equals(ERole.ROLE_ADMIN) ||
                        account.getRoles().getName().equals(ERole.ROLE_STAFF)))
            throw new BadRequestException(400, "No permission to change password admin or staff other");

        if(studentId!=null) {
            Student student = studentRepository.findById(studentId).orElseThrow(() -> {
                throw new NotFoundException(404, "Student is not found!");
            });
            account.setStudent(student);
        }

        if(password!=null) account.setPassword(encoder.encode(password));

        accountRepository.save(account);

    }

    @Override
    public void deactive(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "Account is not found");
        });

        Account currentAccout = accountRepository.findByUsername(Authen.username());
        if(currentAccout.getRoles().getName().equals(ERole.ROLE_STAFF) &&
                        (account.getRoles().getName().equals(ERole.ROLE_ADMIN) ||
                            account.getRoles().getName().equals(ERole.ROLE_STAFF)))
            throw new BadRequestException(400, "No permission to change active admin or staff other");

        account.setActive(!account.isActive());
        accountRepository.save(account);
    }

    @Override
    public JwtResponse loginAccount(String username, String password) {
        if(!accountRepository.existsByUsername(username)){
            throw new NotFoundException(1002, "username has not existed");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String roles = String.valueOf(userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0));

            return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    roles,
                    userDetails.getStudent());
        } catch (Exception ex){
            throw new BadRequestException(1102, "wrong password");
        }
    }
}
