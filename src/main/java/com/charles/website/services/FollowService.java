package com.charles.website.services;

import com.charles.website.entity.Follow;
import com.charles.website.model.request.FollowRequest;
import com.charles.website.model.request.IncreaseHoursRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowService {
    Page<Follow> listFollowPerson(Pageable pageable);

    Follow getFollowDetailPerson(Long studentId, Long degreeId);

    Page<Follow> getAll(String filter, Pageable pageable);

    Page<Follow> getAllByDegree(Long degreeId, Pageable pageable);

    Follow getDetail(Long studentId, Long degreeId);

    void create(FollowRequest request);

    void update(Long studentId, Long degreeId, FollowRequest request);

    void delete(Long studentId, Long degreeId);

    void increaseHours(Long studentId, Long degreeId, IncreaseHoursRequest request);
}
