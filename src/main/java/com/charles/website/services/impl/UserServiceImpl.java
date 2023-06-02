package com.charles.website.services.impl;

import com.charles.website.entity.ERole;
import com.charles.website.entity.Role;
import com.charles.website.entity.Account;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.NotFoundException;
import com.charles.website.model.response.RegisterResponse;
import com.charles.website.model.response.JwtResponse;
import com.charles.website.model.response.UserResponse;
import com.charles.website.repository.RoleRepository;
import com.charles.website.repository.UserRepository;
import com.charles.website.security.jwt.JwtUtils;
import com.charles.website.security.service.UserDetailsImpl;
import com.charles.website.services.UserService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public List<UserResponse> getListUser() {
        List<Account> listAccount = userRepository.findAll();
        List<UserResponse> listUserResponse = new ArrayList<>();
        for(Account account : listAccount){
            listUserResponse.add(new UserResponse(account));
        }
        return listUserResponse;
    }

    @Override
    public void createAccount(RegisterResponse registerResponse) {

        if(registerResponse.getUsername()==null){
            throw new BadRequestException(1000, "username is required");
        }

        if(userRepository.existsByUsername(registerResponse.getUsername())
                && userRepository.findByUsername(registerResponse.getUsername()).isActive()) {
            throw new BadRequestException(1001, "username has already existed");
        }

        if(registerResponse.getPassword()==null){
            throw new BadRequestException(1100, "password is required");
        }

        Account account = new Account();
        account.setUsername(registerResponse.getUsername());
        account.setPassword(encoder.encode(registerResponse.getPassword()));
        account.setActive(true);

        Set<Role> roles = new HashSet<>();

        // Nếu user bth không có set role thì set thành ROLE_USER
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new NotFoundException(404, "Error: Role is not found"));
        account.setRoles(userRole);

        userRepository.save(account);
    }

    @Override
    public JwtResponse loginAccount(String username, String password) {
        if(!userRepository.existsByUsername(username)){
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
                    roles);
        } catch (Exception ex){
            throw new BadRequestException(1102, "wrong password");
        }
    }

    @Override
    public Account infoDetail() {
        return userRepository.findByUsername(Authen.username());
    }
}
