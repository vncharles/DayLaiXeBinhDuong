package com.charles.website.services.impl;

import com.charles.website.entity.*;
import com.charles.website.exception.BadRequestException;
import com.charles.website.exception.ForbiddenException;
import com.charles.website.repository.AccountRepository;
import com.charles.website.repository.FollowRepository;
import com.charles.website.services.FollowService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public Page<Follow> listFollowPerson(Pageable pageable) {
        Account account = accountRepository.findByUsername(Authen.username());

        return followRepository.findAllByStudent(account.getStudent().getId(), pageable);
    }

    @Override
    public Follow getFollowDetailPerson(Long studentId, Long degreeId) {
        Account account = accountRepository.findByUsername(Authen.username());
        if(account.getStudent().getId()!=studentId) throw new ForbiddenException(403, "You don't have permission to see other people's followers");

        StudentDegreeID studentDegreeID = new StudentDegreeID(new Student(studentId), new Degree(degreeId));

        Follow follow = null;
        try {
            follow = followRepository.findById(studentDegreeID).get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(400, "ID follow is not found!");
        }

        return follow;
    }
}
