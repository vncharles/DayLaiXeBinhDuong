package com.charles.website.services;

import com.charles.website.entity.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowService {
    Page<Follow> listFollowPerson(Pageable pageable);

    Follow getFollowDetailPerson(Long studentId, Long degreeId);
}
