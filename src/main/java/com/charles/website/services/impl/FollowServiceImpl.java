package com.charles.website.services.impl;

import com.charles.website.entity.Account;
import com.charles.website.entity.Follow;
import com.charles.website.repository.AccountRepository;
import com.charles.website.repository.FollowRepository;
import com.charles.website.services.FollowService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public List<Follow> listFollowPerson() {
        Account account = accountRepository.findByUsername(Authen.username());

        return followRepository.findAllByStudent(account.getStudent().getId());
    }
}
