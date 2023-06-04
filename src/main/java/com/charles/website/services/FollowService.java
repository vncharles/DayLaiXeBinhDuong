package com.charles.website.services;

import com.charles.website.entity.Follow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowService {
    List<Follow> listFollowPerson();
}
