package com.charles.website.security.service;

import java.util.*;

import com.charles.website.entity.Account;
import com.charles.website.entity.Student;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private boolean active;
    private Student student;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password, boolean active,
                           Collection<? extends GrantedAuthority> authorities, Student student) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.active = active;
        this.student = student;
    }

    public static UserDetailsImpl build(Account account) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(account.getRoles().getName().name()));

        return new UserDetailsImpl(
                account.getId(),
                account.getUsername(),
                account.getPassword(),
                account.isActive(),
                authorities,
                account.getStudent());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
